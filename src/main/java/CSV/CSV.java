package CSV;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV {

    private static String commaReplace(String str) { //replace Hankaku comma to Zenkaku comma before write
        if (str == null) return null;
        return str.replace(',', '，');
    }

    private static String commaRevert(String str) { //replace Zenkaku comma to Hankaku comma before write
        if (str == null) return null;
        return str.replace('，', ',');
    }


    public static ArrayList<ArrayList<String>> readCSV(File file) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try {
            Scanner fileInput = new Scanner(new BufferedReader(new FileReader(file, StandardCharsets.UTF_8)));
            System.out.println(file);
            while (fileInput.hasNextLine()) {
                String innerData = fileInput.nextLine();

                if (innerData.isEmpty()) //last empty row, so skip
                    break;

                String[] output = innerData.split(",");

                ArrayList<String> newRow = new ArrayList<>();

                for (String oldStr : output)
                    newRow.add(commaRevert(oldStr));

                data.add(newRow);
            }
            fileInput.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void writeCSV(ArrayList<ArrayList<String>> data, File file) {
        StringBuilder writeData = new StringBuilder();

        for (ArrayList<String> innerData : data) {
            StringBuilder rowData = new StringBuilder();

            for (String oldStr : innerData)
                rowData.append(commaReplace(oldStr)).append(",");

            rowData.setLength(rowData.length() - 1);

            writeData.append(rowData).append("\n");
        }

        try {
            FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);
            writer.write(writeData.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    public static void appendCSV(ArrayList<String> data, File file) {
        StringBuilder writeData = new StringBuilder();

        for (String oldStr : data)
            writeData.append(commaReplace(oldStr)).append(",");

        writeData.setLength(writeData.length() - 1);

        try {
            FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(writeData.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}