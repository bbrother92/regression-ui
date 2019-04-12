package mid.util;

import java.util.Scanner;

public class Utils {
    //todo cant end input
    public static void debug() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Press any key to continue\n");
        String st = reader.next();
        System.out.println("You entered" + st);
    }
}
