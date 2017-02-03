package project4;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class is used to determine the percentage of babies that were born
 * between a range of years with name the user decides to input. This
 * information is printed out with an additional histogram, with 1 bar
 * representing every .01 percent for a percentage.
 *
 * @author Thomas Guo
 *
 */
public class BabyNames {
	public static void main(String[] args) {
		// sets default range to 1880 and 2015
		int yearOne = 1880;
		int yearTwo = 2015;

		if (args.length >= 2) {
			try {
				yearOne = Integer.parseInt(args[0]);
				yearTwo = Integer.parseInt(args[1]);
			}
			// if user does not enter years as arguments, exits the program
			catch (NumberFormatException ex) {
				System.err.println("invalid year");
				System.exit(0);
			}
			// if user enters years that are out of bounds, exits the program
			if (yearOne < 1880 || yearOne > 2015 || yearTwo < 1880 || yearTwo > 2015) {
				System.err.println("year out of bounds");
				System.exit(0);
			}
			// if the second year is less than the first, exits the program
			if (yearTwo < yearOne) {
				System.err.println("year two is less than year one");
				System.exit(0);
			}
		}

		// if there are other than 0 or 2 or more arguments, exits the program
		else if (args.length != 0) {
			System.err.println("invalid parameters");
			System.exit(0);
		}

		Scanner input = new Scanner(System.in);

		// creates an ArrayList containing BSTs
		ArrayList<YearNames> allYears = new ArrayList<YearNames>();

		// populates the BSTs with Name objects for each year
		try {
			allYears = fileSetup(yearOne, yearTwo);
		}
		// if any file cannot be found, exits the program
		catch (FileNotFoundException ex) {
			System.err.println("No file found");
			System.exit(0);
		}

		boolean keepGoing = true;

		while (keepGoing) {

			// boolean keeps track if a given name is ever found
			boolean nameFound = false;

			/*
			 * Creates an array of type double that stores both the year and
			 * percentage of baby names for a given baby name. Since there are
			 * two entries per year, the array is twice the length of that of
			 * the ArrayList of YearNames BSTs.
			 */
			double[] output = new double[(yearTwo - yearOne + 1) * 2];

			// asks user to input name to be found
			System.out.println("Enter a name or press Q/q to quit:");
			String name = input.nextLine();
			if (name == null) {
				System.err.println("null name");
				continue;
			}

			// exits the program if the user enters "Q" or "q".
			if (name.equals("Q") || name.equals("q")) {
				System.out.println("Program Terminated");
				keepGoing = false;
			} else {
				// prompts user to enter a gender
				System.out.println("Enter a gender: M/m or F/f: ");
				String gender = input.nextLine();

				if (!(gender.equals("F") || gender.equals("f") || gender.equals("M") || gender.equals("m"))) {
					System.err.println("invalid gender");
					continue;
				}

				for (int i = 0; i <= yearTwo - yearOne; i++) {
					/*
					 * fills every even index in the array with the year and
					 * every odd index with the percentage of baby names.
					 */

					output[2 * i] = i + yearOne;

					output[2 * i + 1] = allYears.get(i).getFractionByName(name, gender);

					/*
					 * if the percentage of baby names does not equal zero, then
					 * a name was found and the boolean is set to true.
					 */
					if (output[2 * i + 1] != 0)
						nameFound = true;
				}
				// prints the array and histogram if a name was found
				if (nameFound) {
					printNames(output);
					// if the name is not found, tells the user that no name
					// was
					// found.
				} else {
					System.err.println("No such name in the dataset.");
					continue;
				}
			}

		}
		input.close();
	}

	/**
	 * Populates YearNames BSTs with data from given years and puts these
	 * objects into an ArrayList. If a file does not exist or is incorrectly
	 * named, the program will terminate with an error message.
	 *
	 * @param yearOne
	 *            year indicating the lower bound of the range of years
	 * @param yearTwo
	 *            year indicating the upper bound of the range of years
	 *
	 * @return ArrayList containing all the YearNames BSTs in the given year
	 *         range.
	 * @throws FileNotFoundException
	 *             if a required file cannot be found
	 */
	private static ArrayList<YearNames> fileSetup(int yearOne, int yearTwo) throws FileNotFoundException {
		ArrayList<YearNames> allYears = new ArrayList<YearNames>();

		for (int i = yearOne; i <= yearTwo; i++) {
			allYears.add(new YearNames(i));
			File namesFile = new File("BabyNames/data/yob" + i + ".txt");
			if (!(namesFile.exists())) {
				System.err.println("No file found");
				System.exit(0);
			}

			Scanner namesScanner = new Scanner(namesFile);
			String textline;

			while (namesScanner.hasNext()) {
				textline = namesScanner.nextLine();
				String name = "";
				String gender = "";
				String count = "";
				// counter determines which data field the Scanner is on.
				int counter = 0;
				for (int j = 0; j < textline.length(); j++) {
					// if the Scanner hits a comma, move onto next data field.
					if (textline.charAt(j) == ',') {
						counter++;
					} else if (counter == 0) {
						name += textline.charAt(j);
					} else if (counter == 1) {
						gender += textline.charAt(j);
					} else {
						count += textline.charAt(j);
					}
				}

				/*
				 * use a try catch block to see if any variables used to create
				 * the Name object have invalid inputs or if a name added to the
				 * YearNames data field already exists
				 */
				try {
					Name n = new Name(name, gender, Integer.parseInt(count));
					allYears.get(i - yearOne).add(n);

				}
				// does not add a Name object if it is invalid
				catch (IllegalArgumentException ex) {
				}

			}
			namesScanner.close();
		}
		return allYears;
	}

	/**
	 * Prints the array containing the year and percentage of baby names for a
	 * given name as well as printing a series of bars corresponding to the
	 * percentage of baby names.
	 *
	 * @param output
	 *            array to be printed
	 */
	private static void printNames(double[] output) {
		/*
		 * Prints out the array and formats the percentages. After each year and
		 * percentage is printed, also calculates the number of bars that needs
		 * to be printed out for the histogram and prints those.
		 */
		for (int i = 0; i < output.length / 2; i++) {
			System.out.print((int) output[2 * i]);
			System.out.print(" ");
			System.out.printf("(%5.4f):  ", output[2 * i + 1]);
			int numberOfBars = (int) Math.ceil(output[2 * i + 1] / .01);
			for (int j = 0; j < numberOfBars; j++) {
				System.out.print("|");
			}
			System.out.println();
		}
	}
}
