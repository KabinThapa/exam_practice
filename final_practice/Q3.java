/*
Data output stream is the IO stream used for storing data in file
when we are using the file to store binary format text. we generally wrap the
stream with FileOutputStream to access and perform the IO operation efficiently.
And we use this when we use a .dat file and want to store native primary datatypes
and we use writeUTF, writeInt, writeDouble etc to get the next write

ObjectOutputStream whereas writes a single object to a file in a .ser file,
we generally serialize the content before printing it to the file. We store a single object as a single reference in the
file and extract the instance as a complete object after deserialization of the file
 */

import java.io.*;
class Student implements Serializable {
     int roll;
     String name;
     double marks;
    public Student(){};

    public Student(int roll, String name, double marks){
        this.roll = roll;
        this.name=name;
        this.marks=marks;
    }

}

class Demo{
    public static void main(){
        File file = new File("Student.ser");
        Student s1 = new Student(101,"Kabin",100);
        Student s2 = new Student(102,"Alice",50);
        Student s3 = new Student (103,"Bob",25);
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Student.ser"));
            out.writeObject(s1);
            out.writeObject(s2);
            out.writeObject(s3);
        }catch(FileNotFoundException e){
            System.out.println("Unable to trace file path");
        }catch(IOException ex){
            System.out.println("Unable to resolve File IO");
        }
        Student sn=new Student();
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Student.ser"));

            while(true){
                sn = (Student) in.readObject();
                System.out.println("Roll no: "+sn.roll +"\nName: "+sn.name+"\nMarks: "+sn.marks );
            }
        }catch(FileNotFoundException e){
            System.out.println("Unable to trace file path");
        }catch (EOFException ex){
            System.out.println("Content of file read successfully");
        }catch(IOException exx){
            System.out.println("Unable to resolve file IO operation");
        }catch(Exception ignored){
        }

    }
}