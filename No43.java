package practice.leetcode;

public class No43 {
    public static void main(String[] args) {
        Solution43 s = new Solution43();
        System.out.println(s.multiply("123", "3"));

    }
}

class Solution43 {
    //优化竖式
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }



/*    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] arr = new int[len1 + len2];
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num1).reverse().toString();
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int x = num1.charAt(i) - '0';
                int y = num2.charAt(j) - '0';
                int tem = x * y;
                arr[i + j] = tem % 10;
                if (tem >= 10) {
                    arr[i + j + 1] += tem / 10;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int carry;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] > 10) {
                carry = arr[j] / 10;
                arr[j] = (arr[j] + carry) / 10;
            }
            sb.append(arr[j]);
        }


        return sb.reverse().toString();
    }*/

/*
    //普通竖式乘积累加
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String res = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            int n2 = num2.charAt(i) - '0';
            for (int j = 0; j < num2.length() - i - 1; j++) {
                sb.append(0);
            }
            for (int j = num1.length() - 1; j >= 0 || carry > 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int tem = (n1 * n2 + carry) % 10;
                sb.append(tem);
                carry = (n1 * n2 + carry) / 10;
            }
            res = addStr(res, sb.reverse().toString());
        }
        return res;
    }

    static String addStr(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = s1.length() - 1, j = s2.length() - 1; i >= 0 || j >= 0 || carry > 0; i--, j--) {
            int x = i < 0 ? 0 : s1.charAt(i) - '0';
            int y = j < 0 ? 0 : s2.charAt(j) - '0';
            int tem = (x + y + carry) % 10;
            sb.append(tem);
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }*/
}



