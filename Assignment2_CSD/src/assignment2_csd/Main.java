
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        courseTree ctree = new courseTree();
        studentMenu stmenu = new studentMenu();
        //registerList registerList = new registerList(); // Commented out as it's not used
        Scanner sc = new Scanner(System.in);
        String Schoice;
        int choice;

        do {
            System.out.println("\n|=== Course Management System ===|");
            System.out.println("| 1. Course Tree                 |");               
            System.out.println("| 2. Student Tree                |");
            System.out.println("| 3. Registering List            |");
            System.out.println("| 0. Tắt chương trình            |");
            System.out.println("|================================|");
            System.out.print("Chọn chức năng: ");

            try {
                Schoice = sc.nextLine().trim();
                if (Schoice.isEmpty()) {
                    System.out.println("Lỗi: Input không được bỏ trống. Vui lòng nhập lại!");
                    continue;
                }
                choice = Integer.parseInt(Schoice);
                if (choice == 0) {
                    System.out.println("@%&#*&@#%$%|==========|$#@#%%&@#*$");
                    System.out.println("##@%@%&#&@$|Đã tắt CMS|#*@%$%$@#$#");
                    System.out.println("#&^@%#$@$@@|==========|##*@&#%$@@&");
                    break;
                }

                switch (choice) {
                    case 1:
                        ctree.courseMain();
                        break;
                    case 2:
                        stmenu.menu();
                        break;
                    case 3:
                        //registerList.registerMenu(); // Commented out
                        break;
                    default:
                        System.out.println("Lỗi: Vui lòng chọn menu (1-3) hoặc (0) để tắt chương trình!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên.");
            }
        } while (true);
    }
}

//
//        int choice;
//        do {
//            System.out.println("\n--- Course Management System ---");
//            System.out.println("1. Course Tree");
//            System.out.println("2. Student Tree");
//            System.out.println("3. Registering List");
//            System.out.println("0. Exit");
//            System.out.print("Chọn chức năng: ");
//            choice = scanner.nextInt();
//            scanner.nextLine(); // consume newline
//
//            switch (choice) {
//                case 1:
//                    ctree.courseMain();
//                    break;
//                case 2:
//                    stmenu.menu();
//                    break;
//                //case 3:
//                    //registerList.registerMenu();
//                    //break;
//                case 0:
//                    System.out.println("Exiting CMS. Goodbye!");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        } while (choice != 0);
//
//        scanner.close();
//        //studentMenu menu = new studentMenu();
//        //menu.menu();
//        courseTree ctree = new courseTree();
//        ctree.addCourse(new Courses("4", "CS102", "Data Structures and Algorithms", "Spring", "2024", 50, 0, 300.00));
//        ctree.addCourse(new Courses("1", "CS101", "Introduction to Programming", "Fall", "2023", 50, 0, 250.00));
//        ctree.addCourse(new Courses("9", "ST101", "Statistics", "Fall", "2023", 60, 0, 230.00));
//        ctree.addCourse(new Courses("6", "CH101", "Chemistry I", "Fall", "2023", 40, 0, 210.75));
//        ctree.addCourse(new Courses("3", "PH101", "Physics I", "Fall", "2023", 45, 0, 220.50));
//        ctree.addCourse(new Courses("2", "MA101", "Calculus I", "Fall", "2023", 60, 0, 200.00));
//        ctree.addCourse(new Courses("8", "EC101", "Microeconomics", "Spring", "2024", 70, 0, 240.00));
//        ctree.addCourse(new Courses("5", "MA102", "Calculus II", "Spring", "2024", 55, 0, 220.00));
//        ctree.addCourse(new Courses("7", "CS201", "Database Systems", "Fall", "2024", 45, 0, 350.00));
//        ctree.courseMain();
//    }
