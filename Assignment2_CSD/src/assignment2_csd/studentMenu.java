import java.util.Scanner;
import java.io.*;
import java.util.*;
public class studentMenu {
    studentTree st = new studentTree();
    Scanner scam = new Scanner(System.in);
    boolean run = true;
    
    
    Student x = new Student("S001", "John Doe", 2018);
    Student x2 = new Student("S002", "Alice Smith", 2019);
    Student x3 = new Student("S003", "Bob Johnson", 2020);
        
    
    
    
    
    
    void menu(){
        String studentFile = "students.txt"; // File name

        try {
            File file = new File(studentFile);
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

    studentNode y1 = new studentNode(x);
    studentNode y2 = new studentNode(x2);
    studentNode y3 = new studentNode(x3);
    st.addStudent(y2, st.root);    
    st.addStudent(y3, st.root);
    st.addStudent(y1, st.root);
    st.saveToFile(studentFile);
        while(run){
            System.out.println("1. Load data from file");
            System.out.println("2. Input & add to the tree ");
            System.out.println("3. Display data by in-order traversal");
            System.out.println("4. Save student tree to file by post-order traversal");
            System.out.println("5. Search by scode");
            System.out.println("6. Delete by scode by copying");
            System.out.println("7. Search by name (student name) ");
            System.out.println("8. Search registered courses by scode");
            System.out.println("9. Exit");
            Scanner scam = new Scanner(System.in);
            System.out.print("Enter: ");
            String input = scam.nextLine();
            int choice;
            try{
                if(input.equals("1")){
                    choice = 1;
                }
                choice = Integer.parseInt(input);
                
                switch(choice){
                    case 1:
                        st.loadFromFile(studentFile);
                        break;
                    case 2:
                        studentNode x = st.studentInput();
                        st.addStudent(x, st.root);
                        break;
                    case 3:
                        st.inorder(st.root);
                        break;
                    case 4:
                        st.saveToFile(studentFile);
                        break;
                    case 5:
                        String Scode = scam.nextLine();
                        st.searchByScode(st.root, Scode);
                        break;
                    case 6:
                        Scode = scam.nextLine();
                        st.deleteByCopying(Scode);
                        break;
                    case 7:
                        String Name = scam.nextLine();
                        st.searchByName(st.root, Name);
                        break;
                    case 8:
                        break;
                    case 9:
                        run = false;
                        return;
                    default:
                        System.out.println("Number no available ");
                        break;
                }
            }
            catch(NumberFormatException e){
                System.out.println("enter number");
            }
        }
    }
}
