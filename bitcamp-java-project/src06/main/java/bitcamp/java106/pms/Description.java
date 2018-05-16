package bitcamp.java106.pms;

import java.util.Scanner;

public abstract class Description {
    public abstract void add(Scanner sc);
    public abstract void update(Scanner sc);
    public abstract int get(int n);
    public abstract String getBase();
    public abstract int getNumber();
    public abstract Description call_by_value();
}