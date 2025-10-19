/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package gradebook_project;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;


public class testFX_GradeBook extends Application {
    GradeBook gb1= new GradeBook();
    TextField txinstractor = new TextField ();
    TextField txcourseNo = new TextField ();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // تنسيق الواجهة
        Text welcome = new Text("Welcome to the IUG Grade book\nplease give us some basic information ...\n");
        welcome.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,15));
        BorderPane pane = new BorderPane ();
        pane.setPadding(new Insets(15));
        pane.setTop(welcome);
        Label instractor = new Label ("What is the name of the instractor:");
        Label courseNo = new Label ("What is the course number for your class:"); 
        
        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(10);
        grid.add(instractor ,0,0);
        grid.add(txinstractor , 1,0);
        grid.add(courseNo,0,1);
        grid.add(txcourseNo,1,1);
        Text thank = new Text("Thank you. Processing...\n");
        thank.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,15));
        grid.add(thank, 0, 2);       
        GridPane.setHalignment(txinstractor, HPos.RIGHT);
        GridPane.setHalignment(txcourseNo, HPos.RIGHT);
        pane.setCenter(grid);  
        VBox vbox = new VBox(10);
        Button addStudentRecord = new Button("Add a student record");
        addStudentRecord.setPrefWidth(200);
        Button  addStudentRecordFromFile = new Button("Add a student records from file");
        addStudentRecordFromFile.setPrefWidth(200);
        Button displayStudentRecord = new Button("Display a student record");
        displayStudentRecord.setPrefWidth(200);
        Button displayStatistical = new Button("Display statistical results of class");
        displayStatistical.setPrefWidth(200);
        Button quit = new Button("Quit");
        quit.setPrefWidth(200);
        vbox.getChildren().addAll(addStudentRecord,addStudentRecordFromFile,displayStudentRecord,
            displayStatistical,quit);
        vbox.setAlignment(Pos.CENTER);
        pane.setBottom(vbox);
        Scene scene = new Scene(pane,450,350);
        
        primaryStage.setTitle("GradeBook");
        primaryStage.setScene(scene);
        primaryStage.show();
        // برمجة الأزرار
        // أحنا عملنا ميثود مساعدة هي إلي تبرمج المعلومات و الأزار 
        addStudentRecord.setOnAction(e ->{
            subStage("Add a student record" ,primaryStage) ;  
        });
        addStudentRecordFromFile.setOnAction (e ->{
            subStage("Add a student records from file" ,primaryStage) ;
        });
        displayStudentRecord.setOnAction(e ->{
            subStage("Display a student record",primaryStage) ;
        });
        displayStatistical.setOnAction(e ->{
            subStage("Display statistical results of class" ,primaryStage) ;
        });
        quit.setOnAction(e ->{
            System.exit(0);
        }); 
    }

    // عملناها عشان تسهل الشغل علينا ببرمجة الأزار
     
    // بعتتها عشان هي مش مرئية عند الميثود
    public void subStage(String buttonName , Stage  primaryStage) {
        primaryStage.close();
        Stage s = new Stage();
        String[] array = txcourseNo.getText().split(" ");
        gb1.setCourseName(array[0]);
        gb1.setCourseNo(Integer.parseInt(array[1]));
        switch(buttonName) {
            case("Add a student record"):{
                // stage
                HBox pane1 = new HBox();
                pane1.setPadding(new Insets(8));
                pane1.setSpacing(5);
                TextField id = new TextField();
                id.setPrefColumnCount(5);
                TextField firstName = new TextField();
                firstName.setPrefColumnCount(5);
                TextField lastName = new TextField();
                lastName.setPrefColumnCount(5);
                pane1.getChildren().addAll(new Label("Enter the student’s ID and then name : " ), id , firstName , lastName);
                HBox pane2 = new HBox();
                pane2.setSpacing(5);
                pane2.setPadding(new Insets(8));
                TextField grade1 = new TextField();
                grade1.setPrefColumnCount(3);
                TextField grade2 = new TextField();
                grade2.setPrefColumnCount(3);
                TextField grade3 = new TextField();
                grade3.setPrefColumnCount(3);
                pane2.getChildren().addAll(new Label("Enter the student’s exam grades : "), grade1 , grade2, grade3);
                HBox pane3 = new HBox();
                pane3.setSpacing(4);
                Button ok = new Button("ok");
                ok.setPrefWidth(150);
                Button close = new Button("close");
                pane3.setAlignment(Pos.CENTER);
                pane3.getChildren().addAll(ok, close);
                HBox pane4 = new HBox();
                TextArea information = new TextArea();
                information.setEditable(false);
                information.setPadding(new Insets(20, 40, 20 , 40));
                pane4.getChildren().add(information);
                pane4.setPadding(new Insets(6));
                VBox root = new VBox();
                root.setSpacing(1);
                root.getChildren().addAll(pane1,pane2,pane3, pane4);
                Scene scene = new Scene(root);
                s.setTitle("Add Record");
                s.setScene(scene);
                s.show();
                
                //برمجة  الازرار
                ok.setOnAction(e -> {
                    Student student = new Student(Integer.parseInt(id.getText()),
                    new String []{firstName.getText(),lastName.getText()},
                    new int[]{Integer.parseInt(grade1.getText()),Integer.parseInt(grade2.getText()),
                    Integer.parseInt(grade3.getText())});
                    gb1.addRecord(student);
                    information.setText(student.toString());
                });
                close.setOnAction(e -> {
                    s.close();
                    primaryStage.show();
                });
                };
                break;
                
            case("Add a student records from file"):{
                // stage
                HBox pane1 = new HBox();
                pane1.setPadding(new Insets(10));
                pane1.setSpacing(5);
                pane1.setAlignment(Pos.CENTER);
                TextField fileName = new TextField();
                pane1.getChildren().addAll(new Label("Enter the File Name : "), fileName);
                HBox pane2 = new HBox();
                pane2.setSpacing(5);
                pane2.setAlignment(Pos.CENTER);
                Button ok = new Button("ok");
                Button close = new Button("close");
                pane2.getChildren().addAll(ok,close);
                HBox pane3 = new HBox();
                pane3.setPadding(new Insets(8));
                pane3.setSpacing(5);
                TextArea information = new TextArea();
                information.setEditable(false);
                information.setPadding(new Insets(20, 40, 20 , 40));
                pane3.getChildren().add(information);
                VBox root = new VBox();
                root.getChildren().addAll(pane1 , pane2 , pane3);
                Scene scene = new Scene(root);
                s.setTitle("Add From File");
                s.setScene(scene);
                s.show();
                //برمجة  الازرار
                ok.setOnAction(e->{
                    try {
                        information.setText(gb1.addRecordsfromFile(fileName.getText()));
                    } 
                    catch (Exception ex) {
                        Logger.getLogger(testFX_GradeBook.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                close.setOnAction(e -> {
                    s.close();
                    primaryStage.show();
                
                });
                };
                break;
                
            case("Display a student record"):{
                // stage
                HBox pane1 = new HBox();
                pane1.setPadding(new Insets(10));
                pane1.setSpacing(5);
                pane1.setAlignment(Pos.CENTER);
                TextField id = new TextField();
                pane1.getChildren().addAll(new Label("Enter the ID Number : "), id);
                HBox pane2 = new HBox();
                pane2.setSpacing(5);
                pane2.setAlignment(Pos.CENTER);
                Button ok = new Button("ok");
                Button close = new Button("close");
                pane2.getChildren().addAll(ok,close);
                HBox pane3 = new HBox();
                pane3.setPadding(new Insets(8));
                pane3.setSpacing(5);
                TextArea information = new TextArea();
                information.setEditable(false);
                information.setPadding(new Insets(20, 40, 20 , 40));
                pane3.getChildren().add(information);
                VBox root = new VBox();
                root.getChildren().addAll(pane1 , pane2 , pane3);
                Scene scene = new Scene(root);
                s.setTitle("Display Student Record");
                s.setScene(scene);
                s.show();
                // برمجة الأزرار
                ok.setOnAction(e ->{
                    int num =Integer.parseInt(id.getText());
                    if(gb1.searchRecord(num)!=null) {
                        information.setText(gb1.searchRecord(num).toString());    
                    }
                    else information.setText("ERORR: there is no record for student ID #"+ num);
                });
                close.setOnAction(e -> {
                    s.close();
                    primaryStage.show();
                });
                };
                break; 
                
            case("Display statistical results of class"):{
                HBox pane1 = new HBox();
                pane1.setPadding(new Insets(10));
                pane1.setSpacing(5);
                pane1.setAlignment(Pos.CENTER);
                Button ok = new Button("ok");
                pane1.getChildren().addAll(new Label("To print statistical results of class >> Enter "), ok);
                HBox pane2 = new HBox();
                pane2.setPadding(new Insets(10));
                pane2.setSpacing(5);
                TextArea information = new TextArea();
                information.setEditable(false);
                information.setPadding(new Insets(20, 40, 20 , 40));
                pane2.getChildren().add(information);
                HBox pane3 = new HBox();
                pane3.setPadding(new Insets(8));
                pane3.setSpacing(5);
                pane3.setAlignment(Pos.CENTER);
                Button close = new Button("close");
                pane3.getChildren().add(close);
                VBox root = new VBox();
                root.getChildren().addAll(pane1 , pane2 , pane3);
                Scene scene = new Scene(root);
                s.setTitle("Display statistical results of class");
                s.setScene(scene);
                s.show();
                // برمجةالأزار 
                ok.setOnAction(e ->{
                    information.setText(gb1.printStatistics());
                });
                close.setOnAction(e -> {
                    s.close();
                    primaryStage.show();
                });
                };
                break;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

   
