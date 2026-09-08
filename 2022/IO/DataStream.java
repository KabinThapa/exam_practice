import java.io.*;

public class DataStream {
    public static void main(String[] args){
        File file = new File("student.dat");

        try(DataOutputStream write = new DataOutputStream(new FileOutputStream("student.dat"))){
            write.writeInt(101);
            write.writeUTF("Ram");
            write.writeDouble(87.5);
            write.writeBoolean(true);
        }catch(Exception e){
            System.out.println("");
        }

    try(DataInputStream read = new DataInputStream(new FileInputStream("student.dat"))){
        while(true){
            int id = read.readInt();
            String name = read.readUTF();
            double marks = read.readDouble();
            boolean passed = read.readBoolean();

            System.out.println(id + " " + name + " " + marks + " " + passed);

        }

    } catch(EOFException ex) {
        System.out.print("");
    } catch (java.lang.Exception e) {
        throw new RuntimeException(e);
    }
    }
}