import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Customer {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberofinputs = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberofinputs; i++) {
            System.out.println("the current count is " + (i+1));
        }
    }


    // two main cone
}
