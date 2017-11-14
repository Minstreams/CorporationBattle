package Model;

/**
 * 人员类
 */
public class Person {
    private String name = "";
    private String department = "";
    private String classroom = "";

    public Person(String name, String department, String classroom) {
        this.name = name;
        this.department = department;
        this.classroom = classroom;
    }

    public Person(String input) {
        Set(input);
    }

    public void Set(String input) {
        String[] data = input.split(",");
        name = data[0];
        department = data[1];
        classroom = data[2];
    }

    public String toString() {
        String out = name + "," + department + "," + classroom;
        return out;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
