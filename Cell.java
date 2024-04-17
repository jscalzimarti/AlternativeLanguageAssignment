package alternateLanguageAssignment;

import java.util.*;

public class Cell {
    private String oem;
    private String model;
    private String launch_announced;
    private String launch_status;
    private String body_dimensions;
    private Float body_weight;
    private String weight_string;
    private String body_sim;
    private String display_type;
    private Float display_size;
    private String display_size_string;
    private String display_resolution;
    private String features_sensors;
    private String platform_os;

    public Cell(String oem, String model, String launch_announced, String launch_status, String body_dimensions,
                String body_weight, String body_sim, String display_type, String display_size,
                String display_resolution, String features_sensors, String platform_os) {
        this.oem = CellUtils.isValidString(oem) ? oem : null;
        this.model = CellUtils.isValidString(model) ? model : null;
        this.launch_announced = CellUtils.transformLaunchYear(launch_announced);
        this.launch_status = CellUtils.transformLaunchStatus(launch_status);
        this.body_dimensions = CellUtils.isValidString(body_dimensions) ? body_dimensions : null;
        this.body_weight = CellUtils.transformWeight(body_weight);
        this.weight_string = CellUtils.transformWeightString(body_weight);
        this.body_sim = CellUtils.transformBodySim(body_sim);
        this.display_type = CellUtils.isValidString(display_type) ? display_type : null;
        this.display_size = CellUtils.transformDisplaySize(display_size);
        this.display_size_string = CellUtils.transformDisplaySizeString(display_size);
        this.display_resolution = CellUtils.isValidString(display_resolution) ? display_resolution : null;
        this.features_sensors = CellUtils.isValidString(features_sensors) ? features_sensors : null;
        this.platform_os = CellUtils.transformPlatformOS(platform_os);
    }

    public String getOem() {
        return oem;
    }
    
    public String getPlatformOS() {
    	return platform_os;
    }
    
    public String getLaunchAnnounced() {
    	return launch_announced;
    }
    
    public Float getDisplaySize() {
    	return display_size;
    }
    
    

    @Override
    public String toString() {
        return "Cell{" +
                "oem='" + oem + '\'' +
                ", model='" + model + '\'' +
                ", launch_announced='" + launch_announced + '\'' +
                ", launch_status='" + launch_status + '\'' +
                ", body_dimensions='" + body_dimensions + '\'' +
                ", body_weight='" + body_weight + weight_string + '\'' +
                ", body_sim='" + body_sim + '\'' +
                ", display_type='" + display_type + '\'' +
                ", display_size='" + display_size + display_size_string + '\'' +
                ", display_resolution='" + display_resolution + '\'' +
                ", features_sensors='" + features_sensors + '\'' +
                ", platform_os='" + platform_os + '\'' +
                '}';
    }
    
    
    // Method to calculate the total number of phones in the database
    public static int numPhones(DataManager dataManager) {
        return dataManager.getCells().size();
    }

    // Method to calculate the number of different OEMs included in the database
    public static int numOEMs(DataManager dataManager) {
        return dataManager.getUniqueOEMs().size();
    }

    // Method to calculate the number of phones per OEM included in the database
    public static Map<String, Integer> numPhonesPerOEM(DataManager dataManager) {
        Map<String, Integer> phonesPerOEM = new HashMap<>();
        for (Cell cell : dataManager.getCells()) {
            String oem = cell.getOem();
            phonesPerOEM.put(oem, phonesPerOEM.getOrDefault(oem, 0) + 1);
        }
        return phonesPerOEM;
    } 
    
    // Method to calculate the count of phones by platform OS
    public static Map<String, Integer> numPhonesByPlatformOS(DataManager dataManager) {
        Map<String, Integer> phonesByPlatformOS = new HashMap<>();
        for (Cell cell : dataManager.getCells()) {
            String platformOS = cell.getPlatformOS();
            phonesByPlatformOS.put(platformOS, phonesByPlatformOS.getOrDefault(platformOS, 0) + 1);
        }
        return phonesByPlatformOS;
    }
    
    public static Map.Entry<String, Integer> modePlatformOS(DataManager dataManager) {
        Map<String, Integer> phonesByPlatformOS = numPhonesByPlatformOS(dataManager);
        int maxCount = 0;
        String modePlatformOS = null;
        
        for (Map.Entry<String, Integer> entry : phonesByPlatformOS.entrySet()) {
            String platformOS = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                modePlatformOS = platformOS;
            }
        }
        return new AbstractMap.SimpleEntry<>(modePlatformOS, maxCount);
    }

    
    public static double medianPlatformOS(DataManager dataManager) {
        Map<String, Integer> phonesByPlatformOS = numPhonesByPlatformOS(dataManager);
        List<Integer> counts = new ArrayList<>(phonesByPlatformOS.values());
        Collections.sort(counts);

        int size = counts.size();
        if (size % 2 == 0) {
            // If the size is even, return the average of the two middle values
            int middleIndex1 = size / 2 - 1;
            int middleIndex2 = size / 2;
            return (counts.get(middleIndex1) + counts.get(middleIndex2)) / 2.0;
        } else {
            // If the size is odd, return the middle value
            int middleIndex = size / 2;
            return counts.get(middleIndex);
        }
    }

    public static double averagePlatformOS(final DataManager dataManager) {
        Map<String, Integer> phonesByPlatformOS = numPhonesByPlatformOS(dataManager);
        int totalCount = 0;
        for (int count : phonesByPlatformOS.values()) {
            totalCount += count;
        }
        return (double) totalCount / phonesByPlatformOS.size();
    }

    public static Map<String, Integer> numPhonesByLaunchYear(DataManager dataManager) {
        Map<String, Integer> phonesByLaunchYear = new HashMap<>();
        for (Cell cell : dataManager.getCells()) {
            String launchYear = cell.getLaunchAnnounced();
            phonesByLaunchYear.put(launchYear, phonesByLaunchYear.getOrDefault(launchYear, 0) + 1);
        }
        return phonesByLaunchYear;
    }

    public static Map.Entry<String, Integer> modeLaunchYear(DataManager dataManager) {
        Map<String, Integer> phonesByLaunchYear = numPhonesByLaunchYear(dataManager);
        int maxCount = 0;
        String modeLaunchYear = null;
        
        for (Map.Entry<String, Integer> entry : phonesByLaunchYear.entrySet()) {
            String launchYear = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                modeLaunchYear = launchYear;
            }
        }
        return new AbstractMap.SimpleEntry<>(modeLaunchYear, maxCount);
    }


    public static double medianLaunchYear(DataManager dataManager) {
        Map<String, Integer> phonesByLaunchYear = numPhonesByLaunchYear(dataManager);
        List<Integer> counts = new ArrayList<>(phonesByLaunchYear.values());
        Collections.sort(counts);

        int size = counts.size();
        if (size % 2 == 0) {
            // If the size is even, return the average of the two middle values
            int middleIndex1 = size / 2 - 1;
            int middleIndex2 = size / 2;
            return (counts.get(middleIndex1) + counts.get(middleIndex2)) / 2.0;
        } else {
            // If the size is odd, return the middle value
            int middleIndex = size / 2;
            return counts.get(middleIndex);
        }
    }

    public static double averageLaunchYear(final DataManager dataManager) {
        Map<String, Integer> phonesByLaunchYear = numPhonesByLaunchYear(dataManager);
        int totalCount = 0;
        for (int count : phonesByLaunchYear.values()) {
            totalCount += count;
        }
        return (double) totalCount / phonesByLaunchYear.size();
    }

 // Method to calculate the count of phones by display size
    public static Map<Float, Integer> numPhonesByDisplaySize(DataManager dataManager) {
        Map<Float, Integer> phonesByDisplaySize = new HashMap<>();
        for (Cell cell : dataManager.getCells()) {
            Float displaySize = cell.getDisplaySize();
            phonesByDisplaySize.put(displaySize, phonesByDisplaySize.getOrDefault(displaySize, 0) + 1);
        }
        return phonesByDisplaySize;
    }

    public static Map.Entry<Float, Integer> modeDisplaySize(DataManager dataManager) {
        Map<Float, Integer> phonesByDisplaySize = numPhonesByDisplaySize(dataManager);
        int maxCount = 0;
        Float modeDisplaySize = null;
        
        for (Map.Entry<Float, Integer> entry : phonesByDisplaySize.entrySet()) {
            Float displaySize = entry.getKey();
            if (!Float.isNaN(displaySize)) {
                int count = entry.getValue();
                if (count > maxCount) {
                    maxCount = count;
                    modeDisplaySize = displaySize;
                }
            }
        }
        return new AbstractMap.SimpleEntry<>(modeDisplaySize, maxCount);
    }

    public static double medianDisplaySize(DataManager dataManager) {
        Map<Float, Integer> phonesByDisplaySize = numPhonesByDisplaySize(dataManager);
        List<Float> sizes = new ArrayList<>(phonesByDisplaySize.keySet());
        Collections.sort(sizes);

        int size = sizes.size();
        if (size % 2 == 0) {
            // If the size is even, return the average of the two middle values
            int middleIndex1 = size / 2 - 1;
            int middleIndex2 = size / 2;
            return (sizes.get(middleIndex1) + sizes.get(middleIndex2)) / 2.0;
        } else {
            // If the size is odd, return the middle value
            int middleIndex = size / 2;
            return sizes.get(middleIndex);
        }
    }

    public static double averageDisplaySize(final DataManager dataManager) {
        Map<Float, Integer> phonesByDisplaySize = numPhonesByDisplaySize(dataManager);
        int totalCount = 0;
        double totalSize = 0;
        for (Map.Entry<Float, Integer> entry : phonesByDisplaySize.entrySet()) {
            Float size = entry.getKey();
            if (!Float.isNaN(size)) {
                int count = entry.getValue();
                totalCount += count;
                totalSize += size * count;
            }
        }
        return totalCount == 0 ? Double.NaN : totalSize / totalCount;
    }

    
}
