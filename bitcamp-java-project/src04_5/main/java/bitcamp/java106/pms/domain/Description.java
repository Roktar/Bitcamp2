package bitcamp.java106.pms.domain;

import java.util.Scanner;

public abstract class Description {
    public abstract void add(Scanner sc);
    public abstract void set(Scanner sc);
    public abstract int get(int n);
    public abstract String getBase();
    public abstract int getNumber();
}