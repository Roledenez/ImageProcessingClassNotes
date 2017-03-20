package database;

/**
 * Created by Roledene on 3/20/2017.
 */
public class Patient {
    private int pid;
    private String nic;
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "age=" + age +
                ", pid=" + pid +
                ", nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
