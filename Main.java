package alternateLanguageAssignment;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for reading user input

        // Path to the CSV file (adjust the path as per your environment)
        String csvFilePath = "C:/Users/epicf/Downloads/cells.csv";

        // Read the CSV file
        List<Cell> cells = CSVReader.readCSV(csvFilePath);

        // Initialize DataManager and add cells
        DataManager dataManager = new DataManager();
        for (Cell cell : cells) {
            dataManager.addCell(cell);
        }

        while (true) {
            // Displaying the menu
            System.out.println("Select an operation:");
            System.out.println("1 - Display Cell Statistics");
            System.out.println("2 - Add a New Cell");
            System.out.println("3 - Remove a Cell");
            System.out.println("4 - Search a Cell");
            System.out.println("5 - Display All Cells");
            System.out.println("6 - Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
	            case 1:
	                // Displaying cell statistics
	                System.out.println();
	                System.out.println("The number of phones in database: " + Cell.numPhones(dataManager));
	                System.out.println("The number of different OEMs included in the database: " + Cell.numOEMs(dataManager));
	                System.out.println("The number of phones per each OEM included in the database: " + Cell.numPhonesPerOEM(dataManager));
	                System.out.println();
	                
	                System.out.println("The number of phones per each Platform OS included in the database: " + Cell.numPhonesByPlatformOS(dataManager));
	                Map.Entry<String, Integer> modePlatformOSResult = Cell.modePlatformOS(dataManager);
	                String modePlatformOS = modePlatformOSResult.getKey();
	                Integer modePlatformOSCount = modePlatformOSResult.getValue();
	                System.out.println("The mode Platform OS of phones included in the database is: " + modePlatformOS + " (occurs " + modePlatformOSCount + " times)");
	                System.out.println("The median number of phones on each Platform OS included in the database: " + Cell.medianPlatformOS(dataManager));
	                System.out.println("The average number of phones on each Platform OS included in the database: " + Cell.averagePlatformOS(dataManager)); 
	                System.out.println();
	                
	                System.out.println("The number of phones per each launch year included in the database: " + Cell.numPhonesByLaunchYear(dataManager));
	                Map.Entry<String, Integer> modeLaunchYearResult = Cell.modeLaunchYear(dataManager);
	                String modeLaunchYear = modeLaunchYearResult.getKey();
	                Integer modeLaunchYearCount = modeLaunchYearResult.getValue();
	                System.out.println("The mode Launch Year of phones included in the database is: " + modeLaunchYear + " (occurs " + modeLaunchYearCount + " times)");
	                System.out.println("The median number of phones launched each year included in the database: " + Cell.medianLaunchYear(dataManager));
	                System.out.println("The average number of phones launched each year included in the database: " + Cell.averageLaunchYear(dataManager)); 
	                System.out.println();
	                
	                System.out.println("The number of phones per each Display Size included in the database: " + Cell.numPhonesByDisplaySize(dataManager));
	                Map.Entry<Float, Integer> modeResult = Cell.modeDisplaySize(dataManager);
	                Float modeDisplaySize = modeResult.getKey();
	                Integer modeCount = modeResult.getValue();
	                System.out.println("The mode Display Size of phones included in the database is: " + modeDisplaySize + " (occurs " + modeCount + " times)");
	                System.out.println("The median Display Size of phones included in the database is: " + Cell.medianDisplaySize(dataManager));
	                System.out.println("The average Display Size of phones included in the database is: " + Cell.averageDisplaySize(dataManager));
	                System.out.println();
	                
	                String[] result = Cell.companyWithHighestAvgWeight(dataManager);
	                System.out.println("Company with the highest average weight of the phone body: " + result[0] + " - Highest average weight: " + result[1]);
	                Map<String, List<String>> phonesAnnouncedAndReleased = Cell.phonesAnnouncedAndReleasedDifferentYear(dataManager);
	                System.out.println("Phones announced in one year and released in another:");
	                    for (Map.Entry<String, List<String>> entry : phonesAnnouncedAndReleased.entrySet()) {
	                        String oem = entry.getKey();
	                        List<String> models = entry.getValue();
	                        for (String model : models) {
	                            System.out.println("OEM: " + oem + ", Model: " + model);
	                        }
	                    }
	                int countPhonesWithOneSensor = Cell.countPhonesWithOneFeatureSensor(dataManager);
	                System.out.println("Number of phones with only one feature sensor: " + countPhonesWithOneSensor);
	                Map.Entry<String, Integer> yearWithMostPhones = Cell.yearWithMostPhonesLaunchedAfter1999(dataManager);
	                System.out.println("Year with most phones launched after 1999: " + yearWithMostPhones.getKey() + " - Number of phones launched: " + yearWithMostPhones.getValue());

	                
	                System.out.println(); 
	                break;
                case 2:
                	// Adding a new cell with extended attributes
                	System.out.println("Enter cell OEM: ");
                	String oem = scanner.nextLine();
                	System.out.println("Enter cell model: ");
                	String model = scanner.nextLine();
                	System.out.println("Enter launch announced year: ");
                	String launchAnnounced = scanner.nextLine();
                	System.out.println("Enter launch status: ");
                	String launchStatus = scanner.nextLine();
                	System.out.println("Enter body dimensions: ");
                	String bodyDimensions = scanner.nextLine();
                	System.out.println("Enter body weight: ");
                	String bodyWeight = scanner.nextLine();
                	System.out.println("Enter body SIM type: ");
                	String bodySim = scanner.nextLine();
                	System.out.println("Enter display type: ");
                	String displayType = scanner.nextLine();
                	System.out.println("Enter display size: ");
                	String displaySize = scanner.nextLine();
                	System.out.println("Enter display resolution: ");
                	String displayResolution = scanner.nextLine();
                	System.out.println("Enter features sensors: ");
                	String featuresSensors = scanner.nextLine();
                	System.out.println("Enter platform OS: ");
                	String platformOs = scanner.nextLine();

                	Cell newCell = new Cell(oem, model, launchAnnounced, launchStatus, bodyDimensions, bodyWeight, bodySim,
                	                        displayType, displaySize, displayResolution, featuresSensors, platformOs);

                	dataManager.addCell(newCell);
                	System.out.println("Cell added successfully.");
	                
                	System.out.println();
                	break;
                case 3:
                    // Removing a cell by ID
                    System.out.println("Enter the ID of the cell to remove(ID starts at 0): ");
                    if (scanner.hasNextInt()) {
                        int cellToRemoveId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character after the number input
                        
                        // Check if the cell exists before attempting to remove
                        if (dataManager.cellExists(cellToRemoveId)) {
                            dataManager.removeCell(cellToRemoveId);
                            System.out.println("Cell removed successfully.");
                        } else {
                            System.out.println("Cell with ID " + cellToRemoveId + " does not exist.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a numeric ID.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                    
	                System.out.println();
                    break;
                case 4:
                    // Searching for a cell by ID
                    System.out.println("Enter the ID of the cell to search(ID starts at 0): ");
                    if (scanner.hasNextInt()) {
                        int cellToSearchId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character after the number input
                        
                        // Check if the cell exists before attempting to search
                        if (dataManager.cellExists(cellToSearchId)) {
                            Cell searchedCell = dataManager.getCellById(cellToSearchId);
                            System.out.println("Cell found:");
                            System.out.println(searchedCell);
                        } else {
                            System.out.println("Cell with ID " + cellToSearchId + " does not exist.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a numeric ID.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                    
                    System.out.println();
                    break;

                case 5:
                    // Displaying all cells present
                    System.out.println("Displaying all cells:");
                    for (Cell cell : dataManager.getCells()) {
                        System.out.println(cell);
                    }
                    
	                System.out.println();
                    break;
                case 6:
                	// Exiting the program and closing the scanner
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                	// If user input is not a number between 1-6
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}

