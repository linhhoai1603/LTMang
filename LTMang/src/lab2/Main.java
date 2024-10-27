package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter a line: ");
            String line = userIn.readLine();
            StringTokenizer userInTokenizer = new StringTokenizer(line);

            // Example: Print each token
            while (userInTokenizer.hasMoreTokens()) {
                System.out.println(userInTokenizer.nextToken());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
