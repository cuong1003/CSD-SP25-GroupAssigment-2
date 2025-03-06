
public class Courses {
    private String ccode; //Mã lớp học (Khóa chính duyệt cây theo khóa chính)
    private String scode; //Mã môn học
    private String sname; //Tên môn học
    private String semester; //Học kỳ
    private String year; //Năm học
    private int seats; // Số lượng chỗ ngồi trong lớp học
    private int registered; // Số lượng chỗ đã được đăng ký
    private double price; // Giá đăng ký

    public Courses() {
    }

    public Courses(String ccode, String scode, String sname, String semester, String year, int seats, int registered, double price) {
        this.ccode = ccode;
        this.scode = scode;
        this.sname = sname;
        this.semester = semester;
        this.year = year;
        this.seats = seats;
        this.registered = registered;
        this.price = price;
    }

    public void displayCourse() {
        System.out.println("Ccode: " + ccode);
        System.out.println("Scode: " + scode);
        System.out.println("Sname: " + sname);
        System.out.println("Semester: " + semester);
        System.out.println("Year: " + year);
        System.out.println("Seats: " + seats);
        System.out.println("Registered: " + registered);
        System.out.println("Price: " + price);
        System.out.println("=====================================");
    }

    public String getCcode() {
        return ccode;
    }

    public String getScode() {
        return scode;
    }

    public String getSname() {
        return sname;
    }

    public String getSemester() {
        return semester;
    }

    public String getYear() {
        return year;
    }

    public int getSeats() {
        return seats;
    }

    public int getRegistered() {
        return registered;
    }

    public double getPrice() {
        return price;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setRegistered(int registered) {
        this.registered = registered;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Courses{" + "ccode=" + ccode + ", scode=" + scode + ", sname=" + sname + ", semester=" + semester + ", year=" + year + ", seats=" + seats + ", registered=" + registered + ", price=" + price + '}';
    }


}
