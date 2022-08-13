package j12;

import javax.sound.midi.Soundbank;
import java.util.*;

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
         this.left = left;
        this.right = right;
   }
}


public class j13 {

    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] args) {
        String psw = "123456";
        System.out.println("MD5加密" + "\n" + "明文：" + psw + "\n" + "无盐密文：" + MD5WithoutSalt(psw));
        System.out.println("带盐密文：" + MD5WithSalt(psw));
    }

    private static String MD5WithoutSalt(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byte2HexStr(md.digest(data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private static String MD5WithSalt(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String salt = salt();
            // 这边是简单的拼合，可以用其他复杂的方式混入
            String inputWithSalt = data + salt;
            String hashResult = byte2HexStr(md.digest(inputWithSalt.getBytes()));
            System.out.println("加盐密文：" + hashResult);
            char[] cs = new char[48];
            // 输出带盐，存储盐到hash值中; 每两个hash字符中间插入一个盐字符
            for (int i = 0; i < 48; i += 3) {
                cs[i] = hashResult.charAt(i / 3 * 2);
                cs[i + 1] = salt.charAt(i / 3);
                cs[i + 2] = hashResult.charAt(i / 3 * 2 + 1);
            }
            return new String(cs);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public static String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(hex[random.nextInt(16)]);
        }
        return sb.toString();
    }

    private static String byte2HexStr(byte[] bytes) {
        int len = bytes.length;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            byte byte0 = bytes[i];
            result.append(hex[byte0 >>> 4 & 0xf]);
            result.append(hex[byte0 & 0xf]);
        }
        return result.toString();
    }
}