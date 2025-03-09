
public class Student {
    String scode; //primarykey, student code
    String name;
    int byear;
    
    public Student(String scode, String name, int byear) {
        this.scode = scode;
        this.name = name;
        this.byear = byear;
    }

    @Override
    public String toString() {
        return "Student{" + "scode=" + scode + ", name=" + name + ", byear=" + byear + '}';
    }
    
}
