import java.io.*;

class Student implements Serializable{
    private static final long serialVersionUID = 1L;

    int roll;
    String name;
    double marks;
    Student(int roll, String name, double marks){
        this.roll = roll;
        this.name=name;
        this.marks=marks;
    }
}

public class Serial {
    public static void main(String[] args){
        File file = new File("Student.ser");
        Student s1 = new Student(101,"Kabin",100.0);

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Student.ser"))){
            out.writeObject(s1);
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("Student.ser"))){
            Student student = (Student) in.readObject();
            System.out.println("Roll: " + student.roll);
            System.out.println("Name: " + student.name);
            System.out.println("Marks: " + student.marks);

        }catch (FileNotFoundException e) {
            System.out.println("Error: was not found.");
        } catch (IOException e) {
            System.out.println("I/O Error during reading data: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class structure definition could not be resolved: " + e.getMessage());
        }

    }
}