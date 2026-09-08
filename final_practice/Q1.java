/*
Q. Define encapsulation and polymorphism. Show runtime polymorphism with a meaningful example using method overriding
==> Encapsulation and polymorphism are two of the fundamental pillar of OOP concept
OOP in Java is all about security, reliability, and portability of code and encapsulation allows security of code and
polymorphism enhances code by allowing code reusability.

Encapsulation is the concept of covering sets of variables and methods inside
a wrapper and only allowing certain ways to access these inner components via wrapper(class and its object instance)
This with wisely choosen access modifier totally brings the most use of encapsulation with security.

Polymorphism allows the programmer to keep the code consistent by allowing
user to use the same piece of name for multiple components working for same task in different algorithm/pathway
this can be clssified into compile time and runtime polymorphism.
Compile time being method overloading and run time being runtime polymorphism as
overloaded methods are predetermined during compilation whereas the instance of overridden moethod is only
confirmed just befor the user starts the application, that presents a sense of method being choosen right during compile time.

now a useful example of method overriding using dynamic method dispatch
 */

public interface Employee{
    void calculateBonus(double salary);
    double getBonus();
}
class Manager implements Employee{
    private double Salary;
    private double Bonus;
    @Override
    public void calculateBonus(double Salary){
        Bonus = Salary * 0.25;
    }
    @Override
    public double getBonus(){
        return Bonus;
    }
}
class Worker implements Employee{
    private double Salary;
    private double Bonus;
    @Override
    public void calculateBonus(double Salary){
        Bonus = Salary * 0.15;
    }
    @Override
    public double getBonus(){
        return Bonus;
    }
}
class Clerk implements Employee{
    private double Salary;
    private double Bonus;
    @Override
    public void calculateBonus(double Salary){
        Bonus = Salary * 0.05;
    }
    @Override
    public double getBonus(){
        return Bonus;
    }
}

class Q1{
    public static void main(String[] args){
        Employee r1;

        r1 = new Manager();
        r1.calculateBonus(50000);
        System.out.println("Bonus:" + r1.getBonus());

        r1 = new Worker();
        r1.calculateBonus(25000);
        System.out.println("Bonus:" + r1.getBonus());

        r1 = new Clerk();
        r1.calculateBonus(15000);
        System.out.println("Bonus:" + r1.getBonus());
    }
}


class Person{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
}

class Employee extends Person{
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary){
        super(name,age);
        this.department = department;
        this.salary = salary;
    }
    public String getDepartment(){
        return Department;
    }
    public double getSalary(){
        return salary;
    }
}

class Manager extends Employee{
    private double bonus;
    private dobule totalAmount;
    public Manager(String name, int age, String department,double salary,double bonus){
        super(name,age,department,salary);
        this.bonus = bonus;
    }

    public double calculateTotal(){
        totalAmount =  getSalary()+ bonus;
    }
    public double getBonus(){
        return totalAmount;
    }
}

public class InvalidSalaryException extends Exception{
    public InvalidSalaryException(String msg){
        super(msg);
    }
}
public static void main(String[] args){
    Person p1 = new Manager("Kabin",20,"IT",-10,10000)// add all methods
    try{
        if((int sal = p1.getSalary()) < 0){
            throw new InvalidSalaryException("Salary cannot be less that 0");
        }catch(InvalidSalaryException e){
            System.out.println("Error: "+e);
        }
    }
}