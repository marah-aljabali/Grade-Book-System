/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java2project;
import java.util.Scanner;

public class Student {
    private int id;
    private String[] name ;
    private int [] grads  ;
    private double avg ;
    private char charGrade ;

    public Student(int id, String[] name, int[] grads) {
        this.id = id;
        this.name = name;
        this.grads = grads;
    }

    public Student(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter student ID:");
        id = in.nextInt();
        System.out.print("Enter student name:");
        name = new String [2];
        name[0] = in.next();
        name[1] = in.next();
        System.out.print("Enter student grades:");
        grads = new int [3];
        grads[0] = in.nextInt();
        grads[1] = in.nextInt();
        grads[2] = in.nextInt();
        calculateAvg(); 
        calculateCharacterGrade();
        System.out.println("The final grade for "+getName()+"(ID#"+getId()
        +") is "+getAvg() + "(" + getCharGrade() + ").");
    }

    @Override
    public String toString() {
        return "Student Record for " +getName()+" (ID#"+id+"):\nExam1:"+grads[0]
                +"\nExam2:"+grads[1]+"\nExam3:"+grads[2]+"\nFinal Grade:"+getAvg()
                +"\nLetter Grade:"+getCharGrade();
        
    }
    
    private double calculateAvg(){
         avg = (0.3*grads[0])+(0.3*grads[1])+(0.4*grads[2]);
         return avg;
       
    }
    
    private char calculateCharacterGrade(){
        if(avg>=90) charGrade = 'A';
        else if(avg>=80) charGrade = 'B';
        else if(avg>=70) charGrade = 'C';
        else if(avg>=60) charGrade = 'D';
        else charGrade = 'F';
        return charGrade;
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name[0]+" "+name[1];
    }

    public double getAvg() {
        calculateAvg();
        return avg;
    }

    public char getCharGrade() {
        calculateCharacterGrade();
        return charGrade;
    }
   
}