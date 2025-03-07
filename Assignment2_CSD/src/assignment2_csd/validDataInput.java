
import java.util.Scanner;

public class validDataInput {
   private Scanner sc; 

   public validDataInput() {
       sc = new Scanner(System.in);
   }
   
   public String getString(String text, String regex) {
       while(true) {
           System.out.print(text);
           String input = sc.nextLine().trim();
           if (input.isEmpty()) {
               System.out.println("Lỗi: Vui lòng không bỏ trống, hãy nhập lại!");
               continue;
           }
           if (input.matches(regex)) {
               return input;
           } else {
               System.out.println("Lỗi: Vui lòng không nhập kí tự đặc biệt, hãy nhập lại!");
           }
       }
   }
   public int getInt(String text) {
        while (true) {
            try {
                System.out.print(text);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên.");
            }
        }
    }
   public double getDouble(String text) {
        while (true) {
            try {
                System.out.print(text);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số thực.");
            }
        }
    }
}
