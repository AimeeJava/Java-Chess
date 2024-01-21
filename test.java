package Assignment.A4;

public class test {
    public static void main(String[] args) {
        //char '1' to int 1, '1' - '0'
        char a = '1';
        int b = a - '0';
        System.out.println(b);
        //char 'a' to int, cast (int) 'a'
        char c = 'b';
        int d = (int) c - 97;
        System.out.println(d);

        //int to char
        int w = 99;
        char x = (char) w;

        //String to char, str.charAt(0)
        String e = "c";
        char f = e.charAt(0);

        //String to int, Integer.parseInt(str)
        String g = "5";
        int h = Integer.parseInt(g);

        //int to String, String.valueOf(num)
        int i = 7;
        String k = String.valueOf(i);
    }
}
