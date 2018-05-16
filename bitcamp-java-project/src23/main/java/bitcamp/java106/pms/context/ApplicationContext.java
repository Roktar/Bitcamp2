package bitcamp.java106.pms.context;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import bitcamp.java106.pms.annotation.Component;
// 어노테이션을 사용하여 객체의 이름을 지정하기
// @Component 어노테이션이 붙은 클래스만 객체를 생성한다.

public class ApplicationContext {
    private HashMap<String, Object> objPool = new HashMap<>();
        
    public ApplicationContext(String pkgName, HashMap<String, Object> beans) throws Exception {
        // 다른 맵에 들어있는 객체를 이 컨테이너에 복사
        // 즉, 다른 곳에서 선언한 객체를 여기에 가져와서 쓴다.
        if(beans != null)
            objPool.putAll(beans);
        
        ClassLoader cLoader = ClassLoader.getSystemClassLoader();
        URL url = cLoader.getResource(pkgName.replace(".", "/"));
        if(url == null)
            return;
        File dir = new File(url.getPath());
        
        if(!dir.isDirectory())
            return;
        
        findAndIntsantiateClasses(dir, pkgName);
        // 인스턴스 생성 명령
    }
    
    private void findAndIntsantiateClasses(File dir, String pkgName) throws Exception {
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                
                if(pathname.isDirectory() ||
                    (pathname.getName().endsWith(".class") && 
                     !pathname.getName().contains("$")))
                    return true;
                return false;
            }
        }); 
        
        for(File f : files) {
            System.out.println("find-Instantiate - value : " + f.getName());
            if(f.isDirectory()) { 
                findAndIntsantiateClasses(f, pkgName + "." + f.getName());
                continue;
            }
            
            String classname = f.getName();
            Class clazz = Class.forName(pkgName + "." + classname.substring(0, classname.length() -6));
            Object obj = createObject(clazz); // 생성자 종류에 따라 생성 조절
            
            if( objPool.get(clazz.getName()) != null )
                continue; // 이미 만들어진 객체일 경우, 그냥 넘어간다.
            
            if(obj != null)
                objPool.put( getComponentName(clazz), obj );
        }     
    }
    
    private String getComponentName(Class clazz) {
        Component anno = (Component) clazz.getAnnotation(Component.class);
        String label = anno.value();
        if(label.length() == 0)
            return clazz.getName();
        return label;
    }
    
    private Object createObject(Class clazz) throws Exception {
        // @Component가 지정된 클래스만 만들 것이기에 
        // 어노테이션이 붙어있는 지를 우선 확인한다.
        if(!isComponent(clazz))
            return null;
        
        try {
            clazz.getConstructor(); // 여기서 에러 안걸리면 기본생성자가 있단 얘기.
            return clazz.newInstance(); // 그렇기때문에 기본생성자로 만들어서 내보낸다.
        } catch (Exception e) {
            // 기본생성자가 없을 경우의 처리
            Constructor[] constrs = clazz.getConstructors();
            
            for( Constructor con : constrs ) {                
                Object obj = callConstructor(con);
                
                if(obj != null)
                    return obj;
            }
            
            return null;
        } 
    }
    
    private boolean isComponent(Class clazz) throws Exception {
        
        // 어노테이션의 타입을 지정하여 해당클래스에서 그 어노테이션의 정보를 추출
        Component anno = (Component) clazz.getAnnotation(Component.class);
        if(anno == null)
            return false;
        return true;
        // 어노테이션이 적혀있다는 것을 확인한 경우, anno에 Component타입의 객체가 들어오는 듯.
    }
    
    private Object callConstructor(Constructor con) throws Exception {
        // 기본적으로 제공하는 클래스들은 값을 주고 호출해야하기때문에 우선 기본타입을 배제한다
        if(isDefaulTypeContains(con))
            return null;
        // 즉, 기본적으로 제공하는 클래스들의 데이터타입으로는 만들지않겠다.
        
        // 사용자 정의 데이터타입은 처음 생성 시 null로 초기화되기때문에 값을 안줘도된다.
        // 그래서 우선 여기서는 사용자 정의 타입을 만든다.
        Class[] paramTypes = con.getParameterTypes(); // 파라미터 타입 추출
        ArrayList<Object> paramValues = new ArrayList<>(); // 파라미터 값을 보관할 배열
        System.out.println("callConstructor - paramCount : " + paramTypes.length);

        // 파라미터 타입에 해당하는 값을 보관할 배열.
        for(Class paramType : paramTypes) {
            System.out.println("callConstructor - paramTypeValue : " + paramType.getName().toString());
            paramValues.add(findObject(paramType)); //매개변수 객체 값을 준비
        }
        // 파라미터 값이 준비되었으므로 준비한 파라미터값을 가지고 생성자를 호출
        // 인스턴스를 만든 후 리턴한다.
        
        return con.newInstance(paramValues.toArray());
        // 파라미터값은 배열형식으로 넘겨주면 된다.
    }
    
    private Object findObject(Class clazz) throws Exception {
        Object obj = objPool.get(clazz.getName()); // 객체가 생성되있는가를 확인
        if(obj != null)  // 매개변수 타입과 일치하는 객체가 있을 경우
            return obj; // 이미 있으니 바로 반환한다.
                        // 그런 타입의 객체가 없는 경우 새로 생성한다.
            obj = clazz.newInstance(); 
            System.out.println("findObject - value : " + obj.toString());

            objPool.put(clazz.getName(), obj);
            return obj;

    }
    
    private boolean isDefaulTypeContains(Constructor con) {
        Class[] defaultType = { byte.class, short.class, int.class, long.class, float.class ,
                double.class, boolean.class, char.class, Byte.class,
                Short.class, Integer.class, Long.class , Float.class, 
                Double.class, Boolean.class, Character.class, String.class};
        //1) 생성자의 매개변수 정보를 꺼낸다.

        Class[] paramTypes = con.getParameterTypes();
        
        // primitive 타입인 경우, 처음에 값을 주면서 호출해야한다.
        // 하지만 어떤 값을 줘야할 지 알 수 없기때문에 기본타입은 거름.
        for(Class type : paramTypes) {
            for(Class excludedType : defaultType) {
                System.out.println("isDefault - value : " + type.getName());
                if(type == excludedType)
                    return true;
            } 
        }
        return false;
    }
    
    public Object getBean(String name) {
        return objPool.get(name);
    } // 스캐너는 그냥 넣었던 거 그대로 빼와서 쓸 뿐이었네.
    
    public void print() {
        
    }
}
