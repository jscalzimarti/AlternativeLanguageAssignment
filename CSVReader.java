import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

public class CSVReader {
    public static List<Cell> readCSV(String filePath) {
        List<Cell> cells = new ArrayList<>();
        boolean headerSkipped = false; // Flag to track if header line has been skipped
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    // Check if the first entry is "oem" to skip the header line
                    if (line.startsWith("oem")) {
                        headerSkipped = true;
                        continue; // Skip processing the header line
                    }
                }
                String[] cellData = parseCSVLine(line);
                if (cellData.length < 12) {
                    // Handle error or skip this line because it doesn't have enough columns
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }
                Cell cell = new Cell(
                        cellData[0], cellData[1], cellData[2], cellData[3],
                        cellData[4], cellData[5], cellData[6], cellData[7],
                        cellData[8], cellData[9], cellData[10], cellData[11]);
                cells.add(cell);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cells;
    }

    private static String[] parseCSVLine(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                parts.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        parts.add(sb.toString());

        return parts.toArray(new String[0]);
    }
}
