import java.util.Arrays;

public class Test {

   public static void main(String args[]) {
      String Str = new String("x + y");
      String Str1 = new String("y + x");
      System.out.println(Str.matches("(.*)"+Str1+"(.*)"));
      System.out.println("" + Str1.hashCode() );
   }
   
}
