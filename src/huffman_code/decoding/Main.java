package huffman_code.decoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private String encodedLine;
    int qty;
    int length;
    Map<String,String> codeTable;
    String[] lines;
    String part;
    StringBuilder result = new StringBuilder();
    StringBuilder temp = new StringBuilder();

    private void run() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] header = br.readLine().split(" ");
            qty = Integer.parseInt(header[0]);
            length = Integer.parseInt(header[1]);
            codeTable = new HashMap<>(length);

            for (int i = 0; i < qty; i++) {
                lines = br.readLine().split(": ");
                codeTable.put(lines[1], lines[0]);
            }

            encodedLine = br.readLine();

            for (int i = 0; i < length; i++) {

                temp.append(encodedLine.charAt(i));
                if ((part = codeTable.get(temp.toString())) != null) {
                    result.append(part);
                    temp.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
    }

    public static void main(String[] args){
            new Main().run();
    }
}
