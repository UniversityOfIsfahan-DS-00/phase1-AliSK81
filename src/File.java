import java.io.*;
import java.util.ArrayList;

abstract public class File {

    public static ArrayList<String[]> readData(String fileName) throws IOException {
        ArrayList<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // read header

            while ((line = br.readLine()) != null)
                data.add(line.split("\t"));
        }
        return data;
    }

    public static void writData(String fileName, String header, ArrayList<String[]> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (String[] line : data)
                bw.write(String.join("\t", line) + "\n");
        }
    }

}
