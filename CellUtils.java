package alternateLanguageAssignment;

import java.util.regex.*;

public class CellUtils {
	public static String transformLaunchYear(String launchYear) {
	    if (launchYear == null || launchYear.trim().isEmpty() || launchYear.equals("-")) {
	        return null;
	    }
	    Pattern yearPattern = Pattern.compile("(\\d{4})"); // The regex remains the same
	    Matcher matcher = yearPattern.matcher(launchYear);
	    if (matcher.find()) {
	        return matcher.group(1); // Return the year as a string
	    }
	    return null; // Return null if no valid year is found
	}


    
	public static String transformLaunchStatus(String launchStatus) {
	    if (launchStatus == null || launchStatus.trim().isEmpty() || launchStatus.equals("-")) {
	        return null;
	    }
	    // Check for 'Discontinued' or 'Cancelled' first to avoid unnecessary regex processing
	    if (launchStatus.equalsIgnoreCase("Discontinued") || launchStatus.equalsIgnoreCase("Cancelled")) {
	        return launchStatus;
	    }
	    Pattern yearPattern = Pattern.compile("(\\d{4})"); // Ensure the digits are in a capturing group
	    Matcher matcher = yearPattern.matcher(launchStatus);
	    if (matcher.find()) {
	        return matcher.group(1); // Correctly references the first capturing group
	    }
	    return null; // Return null if no year or specific status is found
	}


	public static float transformWeight(String weight) {
	    Pattern weightPattern = Pattern.compile("(\\d+(\\.\\d*)?)\\s*g");
	    Matcher matcher = weightPattern.matcher(weight);
	    if (matcher.find()) {
	        return Float.parseFloat(matcher.group(1));
	    }
	    return Float.NaN; // Return NaN if no weight is found
	}
	
	public static String transformWeightString(String weight) {
	    Pattern weightPattern = Pattern.compile("(\\d+(\\.\\d*)?)\\s*g");
	    Matcher matcher = weightPattern.matcher(weight);
	    if (matcher.find()) {
	        String trailingInput = weight.substring(matcher.end()).trim();
	        if (!trailingInput.isEmpty()) {
	            return " g" + trailingInput;
	        }
	    }
	    return null; // Return null if no trailing string is found
	}


    public static String transformBodySim(String bodySim) {
        if (bodySim == null || bodySim.trim().isEmpty() || bodySim.equals("-") || bodySim.equalsIgnoreCase("No") || bodySim.equalsIgnoreCase("Yes")) {
            return null;
        }
        return bodySim;
    }

    
	public static float transformDisplaySize(String size) {
	    Pattern sizePattern = Pattern.compile("(\\d+(\\.\\d*)?)\\s*inches");
	    Matcher matcher = sizePattern.matcher(size);
	    if (matcher.find()) {
	        return Float.parseFloat(matcher.group(1));
	    }
	    return Float.NaN; // Return NaN if no weight is found
	}
	
	public static String transformDisplaySizeString(String size) {
	    Pattern sizePattern = Pattern.compile("(\\d+(\\.\\d*)?)\\s*inches");
	    Matcher matcher = sizePattern.matcher(size);
	    if (matcher.find()) {
	        String trailingInput = size.substring(matcher.end()).trim();
	        if (!trailingInput.isEmpty()) {
	            return " inches" + trailingInput;
	        }
	    }
	    return null; // Return null if no trailing string is found
	}


    public static String transformPlatformOS(String platformOS) {
    	if (platformOS == null || platformOS.trim().isEmpty() || platformOS.equals("-")) {
            return null;
        }
    	/*
    	if (platformOS.matches("^-?\\d+(\\.\\d+)?$")) {
            return null;
        }
        *
        * I'm unsure if we should be filtering for values that are "invalid data" i.e. int/float or that we just need to include the string value "Android"
        *
        */
        return platformOS.split(",")[0].trim();
    }

    // Utility method for string validation, assuming it's needed
    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty() && !str.equals("-");
        /*
    	if (platformOS.matches("^-?\\d+(\\.\\d+)?$")) {
            return null;
        }
        *
        * I'm unsure if we should be filtering for values that are considered "invalid data" i.e. int/float or that we just need to include the string value for features
        *
        */
    }
}
