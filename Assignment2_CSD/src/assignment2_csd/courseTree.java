
import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
public class courseTree {
    courseNode root;
    validDataInput vd = new validDataInput();

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
        if (current == null) {
            return null;
        }
        int compareValue = Ccode.compareTo(current.course.getCcode());
        if (compareValue == 0) {
            return current;
        }
        if (compareValue < 0) {
            return searchByCcodeHelper(current.left, Ccode);
        } else {
            return searchByCcodeHelper(current.right, Ccode);
        }
    }
    public void deleteByCopying(String Ccode) {
        courseNode deleteNode;
        courseNode parentOfDeleteNode;
        deleteNode = root;
        parentOfDeleteNode = null;
        
        //Tìm Node cần xóa theo Ccode.
        while (deleteNode != null) {
            //Case Node cần xóa đầu tiên là Root.
            int compareResult = Ccode.compareToIgnoreCase(deleteNode.course.getCcode());
            if (compareResult == 0) {
                break; //Trả về Node chứa Ccode giống với UserInput.
            } 
            if (compareResult < 0) {           
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.left;
            } else {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.right;
            }              
        }
        //Không tồn tại Ccode
        if (deleteNode == null) {
            System.out.println("Mã khóa học: " + Ccode + " không tồn tại trong BSTree!");
            return;
        }           
        //TH1: Ccode cần xóa là Note không có con nào.
        if (deleteNode.left == null && deleteNode.right == null) {
            // BSTree chỉ có 1 Node (Chính nó là Root cha)
            if (parentOfDeleteNode == null) {
                if (deleteNode.course.getRegistered() == 0) {
                    root = null;
                    System.out.println("Đã xóa khóa học " + deleteNode.course.getCcode() +  " thành công.");
                } else {
                    System.out.println("Lỗi: Khóa học đã có " + deleteNode.course.getRegistered() + " sinh viên đăng ký, Không thể xóa.");
                }      
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = null;
                } else {
                    parentOfDeleteNode.right = null;                   
                }
            }
        }        
        //TH2: Ccode cần xóa là Node có 1 con bên trái.
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
                            if (emptyTree()) {
                                System.out.println("Cây BSTree không có dữ liệu. Vui lòng nạp dữ liệu vào trước!");
                                break;
                            }
                            System.out.println("=====================================");
                            displayPreOrder();
                            break;
                        case 4:
                            System.out.println("=====================================");
                            saveCourseToFilePostOrder();
                            break;
                        case 5:
                            if (emptyTree()) {
                                System.out.println("Cây BSTree không có dữ liệu. Vui lòng nạp dữ liệu vào trước!");
                                break;
                            }
                            courseNode result = searchByCcode(getUserCcode());                          
                            
                            if (result == null) {
                                System.out.println("=====================================");
                                System.out.println("Ccode không tồn tại!");
                                break;
                            } else {
                                System.out.println("------------>Đã tìm thấy<------------");
                                System.out.println("=====================================");
                                result.course.displayCourse();
                                break;
                            } 
                        case 6:
                            if (emptyTree()) {
                                System.out.println("Thông báo: Cây BStree chưa có dữ liệu, Vui lòng đổ dữ liệu vào cây BSTree!");
                                break;
                            }
                            System.out.println("=====================================");
                            deleteByCopying(getUserDeleteCcode());
                            break;
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
        String ccode, scode, sname, semester, year;
        int seats, registered;
        double price;
        ccode = vd.getString("Nhập mã khóa học (ccode): ", "^[a-zA-Z0-9]+$");
        scode = vd.getString("Nhập mã môn học (scode): ", "^[a-zA-Z0-9]+$");
        sname = vd.getString("Nhập tên môn học (sname): ", "^[^,]*$"); // Không lấy dấu "," tránh bị lỗi khi save vào file và read lại file.
        semester = vd.getString("Nhập học kỳ (semester): ", "^[a-zA-Z0-9]+$");
        year = vd.getString("Nhập năm (year): ", "^[a-zA-Z0-9]+$");
        
        while (true) {
            int getSeats = vd.getInt("Nhập số ghế ngồi (seats): ");
            if (getSeats <=0) {
                System.out.println("Số ghế ngồi phải lớn hơn 0. Vui lòng nhập lại!");
                continue;
            }
            seats = getSeats;
            break;
        }
        
        while (true) {
             int getRegistered = vd.getInt("Nhập số lượng đã đăng ký (Registered): ");
             if (getRegistered > seats) {
                 System.out.println("Lỗi: Số lượng đã đăng ký không thể lớn hơn số ghế ngồi. Vui lòng nhập lại!");
                 continue;
             }
             if (getRegistered < 0) {
                 System.out.println("Lỗi: Số lượng đã đăng ký không thể âm. Vui lòng nhập lại!");
                 continue;
             }
             registered = getRegistered;
             break;
        }

        while (true) {
           double getPrice = vd.getDouble("Nhập giá (price): ");
           if (getPrice == 0) {
               System.out.println("Trên đời này làm gì có cái gì miễn phí ?");
               continue;
           }
           if (getPrice < 0) {
               System.out.println("Lỗi: Giá tiền không thể nào âm. Vui lòng nhập lại!");
               continue;
           }
           price = getPrice;
           break;
        }
        
        return new Courses(ccode, scode, sname, semester, year, seats, registered, price);
    }

    private String getUserCcode() {
        String ccode = vd.getString("Nhập mã khóa học cần tìm (ccode): ", "^[a-zA-Z0-9]+$");
        return ccode;
    }
    private String getUserDeleteCcode() {
        String ccode = vd.getString("Nhập mã khóa học cần xóa (ccode): ", "^[a-zA-Z0-9]+$");
        return ccode;
    }

  
}
