import java.io.*;

public class lvl2 {
    public static void main(String[] args){
        File file = new File("Student.txt");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Student.txt"))){
            writer.write(101 + ", " + "Ram" + ", " + 87.5);
            writer.newLine();
            writer.write(102 + ", " + "Sita" + ", " + 92.0);
            writer.newLine();
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
        try(BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))){
            String line;
            System.out.println("Student result");
            while((line = reader.readLine())!=null){
                line.split(",");
                System.out.println("Name: "+line[1]+"\nMarks: "+line[2]+"\nRoll no: "+line[0]);
            }
        }catch(Exception ignored){};
    }


}