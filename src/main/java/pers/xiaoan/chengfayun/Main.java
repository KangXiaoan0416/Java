package pers.xiaoan.chengfayun;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            StringBuilder sb = new StringBuilder();
            int index = 0;
            int length = b-a+1;
            for(int i = a; i <= b; i++ ) {
                if(i % 3 == 0 && i % 5 == 0) {
                    sb.append("foobar");
                }else if(i % 3 == 0) {
                    sb.append("foo");
                }else if(i % 5 == 0) {
                    sb.append("bar");
                }else {
                    sb.append(i);
                }
                index++;
                if(index < length) {
                    sb.append("\n");
                }
            }
            System.out.println(sb.toString());
        }
    }
}
