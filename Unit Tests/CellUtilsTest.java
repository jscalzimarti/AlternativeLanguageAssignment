//Do not include this file in repl.it testing. Repl.it uses a different method of running Unit Tests than my IDE does.

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import alternateLanguageAssignment.CSVReader;
import alternateLanguageAssignment.Cell;
import alternateLanguageAssignment.CellUtils;
import alternateLanguageAssignment.DataManager;

public class CellUtilsTest {

    @Test
    public void testTransformLaunchYear() {
        assertEquals(String.valueOf("2010"), CellUtils.transformLaunchYear("2010, January. Released 2010, March"));
        assertNull(CellUtils.transformLaunchYear("-"));
        assertNull(CellUtils.transformLaunchYear(null));
        assertNull(CellUtils.transformLaunchYear("Not officially announced yet"));
    }

    @Test
    public void testTransformWeight() {
        assertEquals(190.0f, CellUtils.transformWeight("190 g (6.70 oz)"), 0.001f);
        assertEquals(Float.NaN, CellUtils.transformWeight("-"));
        assertEquals(Float.NaN, CellUtils.transformWeight(""));
        assertEquals(Float.NaN, CellUtils.transformWeight("oz"));
    }

    @Test
    public void testTransformDisplaySize() {
        assertEquals(Float.valueOf(3.5f), CellUtils.transformDisplaySize("3.5 inches, 34.9 cm"));
        assertEquals(Float.NaN, CellUtils.transformDisplaySize("-"));
        assertEquals(Float.NaN, CellUtils.transformDisplaySize(""));
        assertEquals(Float.NaN, CellUtils.transformDisplaySize("oz"));
    }

    @Test
    public void testTransformPlatformOS() {
        assertEquals("Android 10", CellUtils.transformPlatformOS("Android 10, upgradable to Android 11"));
        assertNull(CellUtils.transformPlatformOS("-"));
        assertNull(CellUtils.transformPlatformOS(null));
        assertEquals("Microsoft Windows Mobile 6.5.3 Professional", CellUtils.transformPlatformOS("Microsoft Windows Mobile 6.5.3 Professional"));
    }

    // Test for reading the CSV file to ensure it's not empty
    @Test
    public void testCSVFileNotEmpty() {
        List<Cell> cells = CSVReader.readCSV("C:/Users/epicf/Downloads/cells.csv");
        assertFalse(cells.isEmpty());
    }
    
    @Test
    public void testNumPhones() {
        DataManager dataManager = new DataManager();
        assertEquals(0, Cell.numPhones(dataManager));
        
        // Adding some cells for testing
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));
        assertEquals(4, Cell.numPhones(dataManager));
    }

    @Test
    public void testNumOEMs() {
        DataManager dataManager = new DataManager();
        assertEquals(0, Cell.numOEMs(dataManager));
        
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));
        assertEquals(3, Cell.numOEMs(dataManager));
    }

    @Test
    public void testNumPhonesPerOEM() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        Map<String, Integer> expected = new HashMap<>();
        expected.put("OEM1", 1);
        expected.put("OEM2", 1);
        expected.put("OEM3", 2);
        assertEquals(expected, Cell.numPhonesPerOEM(dataManager));
    }

    @Test
    public void testNumPhonesByPlatformOS() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        Map<String, Integer> expected = new HashMap<>();
        expected.put("Android", 3);
        expected.put("iOS", 1);
        assertEquals(expected, Cell.numPhonesByPlatformOS(dataManager));
    }

    @Test
    public void testModePlatformOS() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        Map.Entry<String, Integer> expected = new AbstractMap.SimpleEntry<>("Android", 3);
        assertEquals(expected, Cell.modePlatformOS(dataManager));
    }

    @Test
    public void testMedianPlatformOS() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        assertEquals(2.0, Cell.medianPlatformOS(dataManager), 0.01);
    }

    @Test
    public void testAveragePlatformOS() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        assertEquals(2.0, Cell.averagePlatformOS(dataManager), 0.01);
    }

    @Test
    public void testNumPhonesByLaunchYear() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        Map<String, Integer> expected = new HashMap<>();
        expected.put("2022", 1);
        expected.put("2023", 2);
        expected.put("2024", 1);
        assertEquals(expected, Cell.numPhonesByLaunchYear(dataManager));
    }

    @Test
    public void testModeLaunchYear() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        Map.Entry<String, Integer> expected = new AbstractMap.SimpleEntry<>("2023", 2);
        assertEquals(expected, Cell.modeLaunchYear(dataManager));
    }

    @Test
    public void testMedianLaunchYear() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        assertEquals(1, Cell.medianLaunchYear(dataManager));
    }

    @Test
    public void testAverageLaunchYear() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2025", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0 inches", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        assertEquals(1.0, Cell.averageLaunchYear(dataManager), 0.01);
    }

    @Test
    public void testNumPhonesByDisplaySize() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0 inches", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        Map<Float, Integer> expected = new HashMap<>();
        expected.put(5.0f, 1);
        expected.put(6.0f, 1);
        expected.put(7.0f, 1);
        expected.put(8.0f, 1);
        assertEquals(expected, Cell.numPhonesByDisplaySize(dataManager));
    }

    @Test
    public void testModeDisplaySize() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "5.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        Map.Entry<Float, Integer> expected = new AbstractMap.SimpleEntry<>(5.0f, 1);
        assertEquals(expected, Cell.modeDisplaySize(dataManager));
    }

    @Test
    public void testMedianDisplaySize() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        assertEquals(7.5, Cell.medianDisplaySize(dataManager), 0.01);
    }

    @Test
    public void testAverageDisplaySize() {
        DataManager dataManager = new DataManager();
        dataManager.addCell(new Cell("OEM1", "Model1", "2022", "Announced", "1x2x3", "150 g", "SIM1", "LCD", "5.0 inches", "1080x1920", "Sensor1", "Android"));
        dataManager.addCell(new Cell("OEM2", "Model2", "2023", "Launched", "2x3x4", "180 g", "SIM2", "AMOLED", "6.0 inches", "1440x2560", "Sensor2", "iOS"));
        dataManager.addCell(new Cell("OEM3", "Model3", "2023", "Announced", "3x4x5", "160 g", "SIM3", "IPS", "7.0 inches", "720x1280", "Sensor3", "Android"));
        dataManager.addCell(new Cell("OEM3", "Model4", "2024", "Announced", "3x4x5", "160 g", "SIM4", "IPS", "8.0 inches", "720x1280", "Sensor4", "Android"));

        assertEquals(6.5, Cell.averageDisplaySize(dataManager), 0.01);
    }
}
