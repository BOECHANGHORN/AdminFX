package CSV;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <h1>CSV Class</h1>
 * The CSV class is a class that responsible for reading and writing file from and to CSV
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
public class CSV {

    /**
     * The commaReplace method is addressing the CSV as a comma splice format, so convert English
     * Hankaku comma to CJK Zenkaku comma before writing to the file solve the problem
     * (This method replace)
     *
     * @param str the original string
     * @return the new replaced string
     */
    private static String commaReplace(String str) { //replace Hankaku comma to Zenkaku comma before write
        if (str == null) return null;
        return str.replace(',', '，');
    }

    /**
     * The commaRevert method is addressing the CSV as a comma splice format, so convert English
     * Hankaku comma to CJK Zenkaku comma before writing to the file solve the problem
     * (This method revert)
     *
     * @param str the original string from CSV
     * @return the reverted normalize string
     */
    private static String commaRevert(String str) { //replace Zenkaku comma to Hankaku comma before write
        if (str == null) return null;
        return str.replace('，', ',');
    }

    /**
     * Read CSV and return data as 2D String Array
     *
     * @param file the file to read
     * @return the 2D String Array
     */
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

    /**
     * Write CSV from 2D String Array
     *
     * @param data the 2D String Array to be written
     * @param file the file to write
     */
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

    /**
     * Write new row in CSV from String Array
     *
     * @param data the String Array to be written
     * @param file the file to write
     */
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

    /**
     * Change and replace data at specific row
     *
     * @param data the new replaced String Array data
     * @param pos  the affected row
     * @param file the file to change
     */
    public static void changeCSV(ArrayList<String> data, int pos, File file) {
        StringBuilder writeData = new StringBuilder();

        for (String oldStr : data)
            writeData.append(commaReplace(oldStr)).append(",");

        writeData.setLength(writeData.length() - 1);

        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert lines != null;
        lines.set(pos, writeData.toString());
        try {
            Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Delete specific row
     *
     * @param pos  the affected row
     * @param file the file to delete
     */
    public static void deleteCSV(int pos, File file) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert lines != null;
        lines.remove(pos);
        try {
            Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}