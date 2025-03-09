import java.util.Scanner;
import java.io.*;
import java.util.*;
public class studentTree {
    studentNode root;
    Scanner scam = new Scanner(System.in);
    
    
     public studentTree() {
        this.root = null;
    }

    public void clear() {
        this.root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }
    
     public void visit(studentNode p) {
        System.out.println(p.info + " ");
    }
     
//     String scode = scam.nextLine();
//        String name = scam.nextLine();
//        int byear = scam.nextInt();
//        
//        x.scode = scode;
//        x.name = name;
//        x.byear = byear;
    
    public studentNode addStudent(studentNode x, studentNode current) {
        
        //if no list yet
        if (isEmpty()) {
            root = x;
            System.out.println("root = x");
            return root;
        }
        if(current == null){
            current = x;
            System.out.println(x.info);
            return current;
        }
        
            int checkscode = x.info.scode.compareTo(current.info.scode);
            //check if scode exist
            if(checkscode == 0){
                System.out.println("This scode is no available");
                return null;
            }
            if(checkscode < 0 ){
                current.left = addStudent(x,current.left);
            }
            else{
                current.right = addStudent(x,current.right);
            }
        
            System.out.println("student added");
        return null;
    }
    
    void inorder(studentNode x){
        
        if(isEmpty()){
            System.out.println("no tree yet");
            return;
        }
        
        if(x == null){
            System.out.println("x null");
            return;
        }
        
        if(x.left != null){
            System.out.println("left");
            inorder(x.left);
        }
        System.out.println("node");
        visit(x);
        
        if(x.right != null){
            System.out.println("right");
            inorder(x.right);
        }
        
        return;
    }
    
    
    public void loadFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 3) {
                    Student student = new Student(data[0], data[1], Integer.parseInt(data[2]));
                    studentNode current = root;
                    studentNode x = new studentNode(student);
                    addStudent(x,current);
                }
            }
            System.out.println("Students loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   
    public void postOrderSave(studentNode root, BufferedWriter writer) throws IOException {
        if (root != null) {
            postOrderSave(root.left, writer);
            postOrderSave(root.right, writer);
            writer.write(root.info.toString());
            writer.newLine();
        }
    }

    // Save student tree to file
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            postOrderSave(root, writer);
            System.out.println("Students saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public studentNode studentInput(){
        System.out.print("scode: ");
        String scode = scam.nextLine();
        System.out.print("Name: ");
        String name = scam.nextLine();
        System.out.print("byear: ");
        
        double output;
        int byear;
        while(true){
            String yearInput = scam.nextLine();
            try{
                output = Double.parseDouble(yearInput);
                if(yearInput.contains(".") || yearInput.contains(",")){
                    System.out.println("integer not a float");
                }else if(output < 1975){
                    System.out.println("school established in 2000");
                }else{
                    byear = (int)output;
                    break;
                }
            }
            catch(NumberFormatException e){
                System.out.println("enter a number");
            }
        }
        
        
        Student x = new Student(scode,name,byear);
        studentNode y = new studentNode(x);
        return y;
    }
    
   
    
     Student searchByScode(studentNode x, String scode){
        if (x == null) {
            System.out.println("No scode found");
            return null;
        }

        int checkScode = x.info.scode.compareTo(scode);

        if (checkScode == 0) {
            System.out.println(x.info);
            return x.info; // Found the student
        } else if (checkScode < 0) {
            return searchByScode(x.left, scode); // Search in left subtree
        } else {
            return searchByScode(x.right, scode);
        }
    }
     Student searchByName(studentNode x, String name){
        if (x == null) {
            System.out.println("No name found");
            return null;
        }
         System.out.println("before compare");
        int checkScode = x.info.name.compareTo(name);
         System.out.println("after compare");
        if (checkScode == 0) {
            System.out.println(x.info);
            return x.info; // Found the student
        } else if (checkScode < 0) {
            return searchByScode(x.left, name); // Search in left subtree
        } else {
            return searchByScode(x.right, name);
        }
    }
     
     
    
    boolean checkByScode(studentNode x, String scode){
        if(isEmpty()){
            return false;
        }
        if (x == null) {
            System.out.println("No scode found");
            return false;
        }

        int checkScode = x.info.scode.compareTo(scode);

        if (checkScode == 0) {
            return true; // Found the student
        } else if (checkScode < 0) {
            return checkByScode(x.left, scode); // Search in left subtree
        } else {
            return checkByScode(x.right, scode);
        }
    }
    
    
    public void deleteByCopying(String Scode){
        // check if BSTree is empty
        if(isEmpty()){
            System.out.println("studentTree is empty, no deletion");
            return;
        }
        if(!checkByScode(root, Scode)){
            return;
        }
        // search node to be delete
        studentNode deleteNode;
        studentNode parentOfDeleteNode = null;
        deleteNode = root;
        while (deleteNode != null) {
            int check = deleteNode.info.scode.compareTo(Scode);
            if (check == 0) {
            break; // Found the node
            }
            parentOfDeleteNode = deleteNode;
            if (check < 0) {
                deleteNode = deleteNode.right;
            } else {
                deleteNode = deleteNode.left;
            }
        }

        
        //Case 1: delete leaf node
        if(deleteNode.left == null && deleteNode.right == null){
            //check if deleteNode is root
            if(parentOfDeleteNode == null){
                root = null;
            }
            else{
                if(parentOfDeleteNode.left == deleteNode){
                    parentOfDeleteNode = null;
                }
                else{
                    parentOfDeleteNode.right = null;
                }
            }
            return;
        }
        
        //Case 2: delete node having only left child
        if(deleteNode.left != null && deleteNode.right == null){
            //check if deleteNode is root
            if(parentOfDeleteNode == null){
                root = deleteNode.left;
            }
            else{
                if(parentOfDeleteNode.left == deleteNode){
                    parentOfDeleteNode.left = deleteNode.left;
                }
                else{
                    parentOfDeleteNode.left = deleteNode.left;
                }
            }
            deleteNode.left = null;
            return;
        }
        
        //Case 3: delete node having only right child
        if(deleteNode.left != null && deleteNode.right == null){
        //check if deleteNode is root
            if(parentOfDeleteNode == null){
                root = deleteNode.right;
            }
            else{
                if(parentOfDeleteNode.left == deleteNode){
                    parentOfDeleteNode.left = deleteNode.right;
                }
                else{
                    parentOfDeleteNode.right = deleteNode.right;
                }
            }
            deleteNode.right = null;
            return;
        }
        
        
        //case 4: delete node having both child
        if(deleteNode.left != null && deleteNode.right != null){
            studentNode parentReplaceNode;
            studentNode replaceNode; //the right most node - this will replace deleteNode
            
            //find the right most node on the left sub-tree of deleteNode
            parentReplaceNode = null;
            replaceNode = deleteNode.left;
            while(replaceNode.right != null){
                parentReplaceNode = replaceNode;
                replaceNode = replaceNode.right;
            }
            //copy info of replace node into deletenode
            deleteNode.info = replaceNode.info;
            if(parentReplaceNode == null){
                //replace node is left child of delete node
                 deleteNode.left = replaceNode.left;
            }
            else{
                //replacenode is not left child of deletenode 
                parentReplaceNode.right = replaceNode.left;
            }
            replaceNode.left = null;
            return;
        }
    
    }
    
    
    
    
    
}
