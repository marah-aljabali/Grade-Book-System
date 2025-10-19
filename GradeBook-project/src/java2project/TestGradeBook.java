/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package java2project;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestGradeBook {
    public static void main(String [] args){
        Scanner input = new Scanner (System.in);
        
        GradeBook gb1= new GradeBook();
        System.out.print("Welcome to the IUG Grade Book. \n" +
        "Please give us some basic information... \n" +
        "What is the full course name, and course number for your class " + "put space between name and num: ");
        gb1.setCourseName(input.next());
        gb1.setCourseNo(input.nextInt());
        
        System.out.print("What is the name of the instructor: " );
        String con1 = input.next();
        String con2 = input.next();
        System.out.println("Thank you. Processing...");
        
        showMenue();
        int i = input.nextInt();
        while (i != 5){
            try {
                switch (i) {
                    case 1:
                        gb1.addRecord();
                        break;
                        
                    case 2:
                        System.out.print("Enter your file name:");
                        
                        try {
                            gb1.addRecordsfromFile(input.next());
                        }
                        catch (FileNotFoundException ex){
                            System.out.println("File does not exist!!");
                        }  
                        break;
                        
                    case 3:
                        System.out.print("Enter an ID: ");
                        int num =input.nextInt();
                        if(gb1.searchRecord(num)!=null) {
                            System.out.println(gb1.searchRecord(num).toString());
                        }
                        else System.out.println("ERORR: there is no record for student ID #"+ num);
                        break;
                        
                    case 4:
                        System.out.println("Statistical Results of "+gb1.getCourseName()+" "+gb1.getCourseNo() + 
                                 " (Instructor: "+ con1 +" " + con2 +")");
                        System.out.println(gb1.printStatistics());
                        break;
                    default:
                        System.out.println("Invalid choice: please try again.");
                        break;
                }
                
                showMenue();
                i = input.nextInt();
            }
                catch (Exception ex) {
                    Logger.getLogger(TestGradeBook.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            System.out.println("Thank you for using the IUG Grade Book.\nGoodBye.");
                    System.exit(0);
    }
    public static void showMenue(){
        System.out.println("--------------------------------------------------");
        System.out.println(String.format("%30s", "IUG Grade Book"));
        System.out.println("--------------------------------------------------");
        System.out.print("1.  Add a student record \n2.  Add student records from file"
                + "\n3.  Display a student record\n4.  Display statistical results of class" 
                +"\n5.  Quit\nEnter Your Choice:");
    }
    
}