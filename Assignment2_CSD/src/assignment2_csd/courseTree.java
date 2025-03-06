
public class courseTree {
    courseNode root;

    public courseTree() {
        root = null;
    }


    public void addCourse(Courses course) {
        root = insertCourse(root, course);
    }

    private courseNode insertCourse(courseNode current, Courses course) {
        if (current == null) {
            return new courseNode(course); // Nếu current == null thì gán luôn course mới là làm node root.
        }
        int compareResult = course.getCcode().compareTo(current.course.getCcode());
        if (compareResult == 0) {
            System.out.println("Mã lớp học này đã tồn tại!");
            return null;
        }
        if (compareResult < 0) {
            current.left = insertCourse(current.left, course);
        } else {
            current.right = insertCourse(current.right, course);
        }
        return current;
    }

    public void display() {
        displayHelper(root);
    }

    private void displayHelper(courseNode root) {
        if (root != null) {
            displayHelper(root.left);
            root.course.displayCourse();
            displayHelper(root.right);
        }
    }

}
