import java.io.*;

public class lvl1 {
    public static void main(String[] args){
        File file = new File("college.txt");

        try(FileWriter in = new FileWriter("college.txt",true)){
            in.write("Write a program to append two more lines to an existing file college.txt without deleting its previous contents.");
        }catch(IOException ignored){}

        try(FileReader out = new FileReader("college.txt")){
            int line;
            int count=0;
            while(( line = out.read())!=-1){
                count++;
                System.out.print((char)line);
            }
            System.out.println("\nNumber of characters: "+count);
        }catch(IOException ignored){}
    }
}