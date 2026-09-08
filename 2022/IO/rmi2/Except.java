class NewExcept extends Exception{
    public NewExcept(String msg){
        super(msg);
    }
}

public class Except {
    public static void main(String[] args){
        int age=10;
        try{
           if(age<18){
               throw new NewExcept("User cannot be under 18 to participate");
           }
        }catch(NewExcept e){
            System.out.println("Error"+e);
        }
    }
}