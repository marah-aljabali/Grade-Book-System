/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gradebook_project;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GradeBook {
    private int courseNo ;
    private String courseName ;
    private ArrayList<Student> list;

    public GradeBook() {
        list = new ArrayList<>();  
    }

    public int getCourseNo() {
        return courseNo;
    }
    
    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public int getNumberOfStudents() {
        return list.size();
    }
    
    public void addRecord(Student s){
       list.add(s);
    }
    
    public String addRecordsfromFile(String fname )throws Exception{
        File file= new File(fname);
        if (!file.exists()){
            throw new FileNotFoundException ("file does not exist");
        }

            Scanner in = new Scanner(file);
            String s = "";
            int i = 0 ;
            while (in.hasNext()){
                int id = in.nextInt();
                String [] name = {in.next() , in.next()};
                int[] grade = {in.nextInt() , in.nextInt() , in.nextInt()};
                list.add(new Student(id, name,grade));
                s+="Student Record for " + name[0] + " " + name[1]+
                        "ID # " + id + "\nExam 1 : "+ grade[0] + "\nExam 2 : "+ grade[1]
                        + "\nFinal Exam : "+ grade[2] + "\nFinal Grade : " + list.get(i).getAvg()+
                        "\nLetter  Grade : " + list.get(i).getCharGrade() ;
                i++;
            }
            return s ;
    }
    
    
    public Student searchRecord(int id){
        for (int i =0 ; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return list.get(i);
            }
        }
        return null;
    }
    
    public int[] countCharGrades(){
        int [] count=new int[5];
        for (int i =0 ; i <list.size() ; i++){
            if (list.get(i).getCharGrade() == 'F'){
            count[(list.get(i).getCharGrade()-'A') -1 ]++;
            }
            else
                count[list.get(i).getCharGrade()-'A']++;
        }
        return count;
    }
    
    public Student maxRecord(){
        double max= list.get(0).getAvg();
        int index=0;
        for(int i =1 ; i<list.size() ; i++){
            if(max < list.get(i).getAvg()){
                max= list.get(i).getAvg();
                index= i;
            }
        }
        return list.get(index);
        
    }
    
    public Student minRecord(){
        double min= list.get(0).getAvg();
        int index=0;
        for(int i =1 ; i<list.size() ; i++){
            if(min > list.get(i).getAvg()){
                min = list.get(i).getAvg();
                index= i;
            }
        }
        return list.get(index);
    }
    
    public String printStatistics(){
        double avarage=0;
        if( ! (list.isEmpty())){
        for(int i =0 ; i<list.size(); i++){
            avarage+= list.get(i).getAvg();
        }
        String s = "Total number of student records: "+getNumberOfStudents()
            +String.format("\nAverage Score: %.2f\nHighest Score: %.2f\nLowest Score: %.2f"
            ,(avarage/list.size())
            ,maxRecord().getAvg()
            ,minRecord().getAvg());
        
        int i = 0;
            for (   ; i< 4  ; i++){
            s+="\nTotal '"+(char)('A'+i)+"' Grades: "+countCharGrades()[i]+"("
                    +String.format("%.2f",countCharGrades()[i]*100.0/list.size())+"% of class)";
            }
            s+="\nTotal 'F' Grades: "+countCharGrades()[i]+"("
                    +String.format("%.2f",countCharGrades()[i]*100.0/list.size())+"% of class)";
            
        return s;
        }
        
        else{
            String s= "Total number of student records: 0 "
            +"\nAverage Score: 0.00 " 
            +"\nHighest Score: 0.00 "
            +"\nLowest Score: 0.00 " ;
            for (int i =0 ; i< 6 ; i++){
                if(i== 4 ) 
                    continue;
            s+="\nTotal '"+(char)('A'+i)+"' Grades: 0 (0% of class )";      
            }
        return s;
        }  
    }
}

