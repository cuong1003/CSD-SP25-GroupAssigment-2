import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
public class courseTree {
    courseNode root;

    public courseTree() {
        root = null;
    }
    public boolean emptyTree() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    public void addCourse(Courses course) {
        root = insertCourse(root, course);
    }

    private courseNode insertCourse(courseNode root, Courses course) {
        if (root == null) {
            return new courseNode(course); // Nếu current == null thì gán luôn course mới là làm node root.
        }
        int compareResult = course.getCcode().compareTo(root.course.getCcode());
        if (compareResult == 0) {
            System.out.println("Mã lớp học này đã tồn tại!");
            return null;
        }
        if (compareResult < 0) {
            root.left = insertCourse(root.left, course);
        } else {
            root.right = insertCourse(root.right, course);
        }
        return root;
    }

    public void displayInorder() {
        displayInorderHelper(root);
    }

    private void displayInorderHelper(courseNode root) {
        if (root != null) {
            displayInorderHelper(root.left);
            root.course.displayCourse();
            displayInorderHelper(root.right);
        }
    }
    
    public void displayPostOrder() {
        displayPostOrderHelper(root);
    }
    
    private void displayPostOrderHelper(courseNode root) {
        if (root != null) {
            displayPostOrderHelper(root.left);
            displayPostOrderHelper(root.right);
            root.course.displayCourse();
        }
    }
    
    public void displayPreOrder() {
        displayPreOrderHelper(root);
    }
    
    private void displayPreOrderHelper(courseNode root) {
        if (root != null) {
            root.course.displayCourse();
            displayPreOrderHelper(root.left);
            displayPreOrderHelper(root.right);
        }       
    }
    
    public void loadCourseFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/assignment2_csd/Courses.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 8) { // Ensure all fields are present
                    String ccode = values[0];
                    String scode = values[1];
                    String sname = values[2];
                    String semester = values[3];
                    String year = values[4];
                    int seats = Integer.parseInt(values[5]);
                    int registered = Integer.parseInt(values[6]);
                    double price = Double.parseDouble(values[7]);

                    Courses course = new Courses(ccode, scode, sname, semester, year, seats, registered, price);
                    addCourse(course);
                } else {
                    System.err.println("Invalid line in file: " + line);
                }
            }
            System.out.println("Đã Load File thành công!");
            System.out.println("=====================================");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading courses from file: " + e.getMessage());
        }
    }
    
    public void saveCourseToFilePostOrder() {
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/assignment2_csd/Courses.txt"))) {
            saveCourseToFilePostOrderHelper(root, bw);
            System.out.println("=====================================");
            System.out.println("Đã Save File thành công!");
            
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu khóa học vào tệp: " + e.getMessage());
        }
    }

    private void saveCourseToFilePostOrderHelper(courseNode root, BufferedWriter bw) throws IOException {
        if (root != null) {
            saveCourseToFilePostOrderHelper(root.left, bw);
            saveCourseToFilePostOrderHelper(root.right, bw);
            bw.write(root.course.getCcode() + "," + root.course.getScode() + "," + root.course.getSname() + "," +
                    root.course.getSemester() + "," + root.course.getYear() + "," + root.course.getSeats() + "," +
                    root.course.getRegistered() + "," + root.course.getPrice());
            bw.newLine();
        }
    }
    
    public courseNode searchByCcode(String Ccode) {
        courseNode result = searchByCcodeHelper(root,Ccode);
        if (result != null) {
            return result;
        }
        return null;     
    }
    
    private courseNode searchByCcodeHelper(courseNode current, String Ccode) {
        int compareValue = current.course.getCcode().compareTo(Ccode);
        if (compareValue == 0) {
            return current;
        }
        if (compareValue < 0) {
            return searchByCcodeHelper(current.left, Ccode);
        } else {
            return searchByCcodeHelper(current.right, Ccode);
        }
    }
    
    public void courseMain() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1.1 Load data from file");
            System.out.println("1.2 Input & add to the tree");
            System.out.println("1.3 Display data by pre-order traversal");
            System.out.println("1.4 Save course tree to file by post-order traversal");
            System.out.println("1.5 Search by ccode");
            System.out.println("1.6 Delete by bcode by copying");
            System.out.println("1.7 Delete by bcode by copying"); 
            System.out.println("1.8 Simply balancing");
            System.out.println("1.9 Display data by breadth-first traversalCount the number of courses");
            System.out.println("1.10 Count the number of courses");
            System.out.println("1.11 Search course by name");
            System.out.println("1.12 Search course by ccode");
            System.out.println("0. Exit to main menu");
            System.out.print("Enter your choice: ");
            try {
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (choice >= 0 && choice <= 13) {
                    switch (choice) {
                        case 0:
                            System.out.println("Quay về Menu chính...");
                            break;
                        case 1:
                            loadCourseFromFile();
                            break;
                        case 2:
                            System.out.println("=====================================");
                            addCourse(getUserInput());
                            break;
                        case 3:
                            System.out.println("=====================================");
                            displayPreOrder();
                            break;
                        case 4:
                            System.out.println("=====================================");
                            saveCourseToFilePostOrder();
                            break;
                        case 5:
                            courseNode result = searchByCcode(getUserCcode());
                            if (result == null) {
                                System.out.println("Ccode không tồn tại");
                            } else {
                                result.course.displayCourse();
                            }                           
                        case 13:
                            System.out.println("=====================================");
                            displayPostOrder();
                            break;
                            
                        default:
                            System.out.println("vui lòng chọn menu (1-12) hoặc (0) để quay về Menu chính!");
                    }
                } else {
                    System.out.println("Invalid choice. Please enter a number between 0 and 12.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine(); // Clear invalid input
            }
        } while (true);
    }

    public Courses getUserInput() {
        Scanner sc = new Scanner(System.in);
        String ccode, scode, sname, semester, year;
        int seats, registered;
        double price;

        while (true) {
            System.out.print("Nhập mã khóa học (ccode): ");
            ccode = sc.nextLine().trim(); //trim() để loại bỏ khoảng trắng đầu cuối.
            if (!ccode.isEmpty()) {
                break;
            } else {
                System.out.println("Mã khóa học không được để trống!");
            }
        }
        while (true) {
            System.out.print("Nhập mã môn học (scode): ");
            scode = sc.nextLine().trim();
             if (!scode.isEmpty()) {
                break;
            } else {
                System.out.println("Mã môn học không được để trống!");
            }
        }
        while (true) {
            System.out.print("Nhập tên môn học (sname): ");
            sname = sc.nextLine().trim();
             if (!sname.isEmpty()) {
                break;
            } else {
                System.out.println("Tên môn học không được để trống!");
            }
        }
        while (true) {
            System.out.print("Nhập học kỳ: ");
            semester = sc.nextLine().trim();
             if (!semester.isEmpty()) {
                break;
            } else {
                System.out.println("Học kỳ không được để trống!");
            }
        }
        while (true) {
            System.out.print("Nhập năm: ");
            year = sc.nextLine().trim();
             if (!year.isEmpty()) {
                break;
            } else {
                System.out.println("Năm không được để trống!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhập số chỗ: ");
                seats = Integer.parseInt(sc.nextLine());
                if(seats >= 0 ){
                     break;
                } else{
                     System.out.println("Số chỗ không được âm");
                }
            } catch (NumberFormatException e) {
                System.out.println("Số chỗ phải là số nguyên!");
            }

        }
        while (true) {
             try {
                System.out.print("Nhập số lượng đã đăng ký: ");
                registered = Integer.parseInt(sc.nextLine());
                 if(registered >= 0 ){
                     break;
                } else{
                     System.out.println("Số lượng đăng ký không được âm");
                }
            } catch (NumberFormatException e) {
                System.out.println("Số lượng đã đăng ký phải là số nguyên!");
            }
        }

        while (true) {
           try{
               System.out.print("Nhập giá: ");
               price = Double.parseDouble(sc.nextLine());
               if(price>=0){
                    break;
               } else{
                    System.out.println("Giá không được âm");
               }

           } catch (NumberFormatException e){
                System.out.println("Giá phải là số thực!");
           }
        }
        
        return new Courses(ccode, scode, sname, semester, year, seats, registered, price);
    }

    private String getUserCcode() {
        Scanner sc = new Scanner(System.in);
        String ccode;
        while (true) {
            System.out.print("Nhập mã khóa học cần tìm (ccode): ");
            ccode = sc.nextLine().trim(); //trim() để loại bỏ khoảng trắng đầu cuối.
            if (!ccode.isEmpty()) {
                break;
            } else {
                System.out.println("Mã khóa học không được để trống!");
            }
        }
        return ccode;
    }

  
}
