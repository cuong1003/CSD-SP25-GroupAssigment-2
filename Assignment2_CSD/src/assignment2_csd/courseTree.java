import java.io.*;
public class courseTree {
    courseNode root;

    public courseTree() {
        root = null;
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
    
    public void loadFromCourseFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/assignment2_csd/Courses.txt"))) {
            String line;
            br.readLine(); // Skip header line
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
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading courses from file: " + e.getMessage());
        }
        return;
    }


}
