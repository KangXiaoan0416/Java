package pers.xiaoan.chengfayun;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 路径简化程序
 * @author: xiaoan
 * @createDate: 2019/12/3 18:09
 */
public class SimplePath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        String beforeFolderChar = ".";
        String splitChar = "/";

        if(!path.isEmpty() && path.length() > 0) {
            if(path.indexOf(splitChar) < 0) {
                System.out.println(path);
                return;
            }
            if(!path.contains(beforeFolderChar)) {
                System.out.println(path);
                return;
            }else {
                String[] items = path.trim().split(splitChar);
                LinkedList<String> linkedList = new LinkedList();
                int index = 0;
                for (String item : items) {
                    if(item.isEmpty()) {
                        continue;
                    }
                    if(index == 0 && item.indexOf(beforeFolderChar) > -1) {
                        continue;
                    }
                    int itemLength = item.length();
                    if(item.indexOf(beforeFolderChar) > -1) {
                        if(itemLength == 1) {
                            continue;
                        }else {
                            int linkLength = linkedList.size();
                            if(linkLength >= 1) {
                                linkedList.remove(linkLength - 1);
                            }
                        }
                    }else {
                        linkedList.add(item);
                    }
                    index++;
                }
                for(String str : linkedList) {
                    sb.append(splitChar);
                    sb.append(str);
                }
            }
        }
        System.out.println(sb.toString());
     }
}





























