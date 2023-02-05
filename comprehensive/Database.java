package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

/**
 * With a given Grammar file, store all production rules and create phrases from that given file
 * 
 * @author Riley Peters
 * @version 4/26/22
 */
public class Database {
	
	private Hashtable<String, ArrayList<String>> table;
	private Scanner fileScanner;
	private Random rng;
	
	/**
	 * Constructor 
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public Database(File file) throws FileNotFoundException {
		this.fileScanner = new Scanner(file);
		rng = new Random();
		table = new  Hashtable<String, ArrayList<String>>();
		buildDatabase();
	}
	
	/**
	 * Organizes and stores all production rules within the Hashtable
	 */
	private void buildDatabase() {
		while(fileScanner.hasNextLine()) {  
			String line = fileScanner.nextLine();
			
			if (line.isBlank())  //Nothing is on the line or it is only white space
				continue;
			
			while (!line.equals("{"))  //Finds where the beginning of a section is
				line = fileScanner.nextLine();
			
			ArrayList<String> operationsList = new ArrayList<String>();
			line = fileScanner.nextLine();
			
			while (!line.equals("}")) {  //Adds lines into the list until we reach the end
				operationsList.add(line);
				line = fileScanner.nextLine();
			}
			table.put(operationsList.get(0), operationsList);  //Adds the list to the Hashtable using the first entry as the key
		}
		fileScanner.close();
	}

	/**
	 * Builds a phrase
	 * 
	 * @return String Object that contains a phrase
	 */
	public String buildPhrase() {
		boolean hasPeriod = false;  //Keeps track if there is an extra characters at the end of a production rule
		char extra = 0;
		
		String start = findArray("<start>"); //Gets starting production rule
		
		String toReturn = "";  //String variable that will hold the finished phrase
		
		if (start.contains(" ")) {  //Phrase contains spaces
			Scanner scanner = new Scanner(start);
			while (scanner.hasNext())  //Traverse over every word
				toReturn += phraseWithSpace(scanner.next());  //Build the phrase
			scanner.close();
		}
		else
			toReturn += phraseNoSpace(start);  //Phrase doesn't contain phrases
		
		if (hasPeriod)  //Adds back extra character
			toReturn += extra;
		
		if (toReturn.charAt(toReturn.length() - 1) == ' ')  //Removes extra space if it exists
				toReturn = toReturn.substring(0, toReturn.length()-1);
		
		return toReturn;
	}
	
	/**
	 * Recursive method that will continue building the phrase with spaces
	 * 
	 * @param s - Word that gets passed in
	 * @return Part of the completed phrase
	 */
	private String phraseWithSpace (String s) {
		if (s.charAt(0) != '<')  //If the word is a terminal, return it
			return s + " ";
	
		String toReturn = findArray(s);  //Get a random production rule

		Scanner scanner = new Scanner(toReturn);
		String temp = "";  //Temp variable to help build the phrase
		while (scanner.hasNext())  //Traverse over every word
			temp += phraseWithSpace(scanner.next());  //Build the phrase
		scanner.close();

		return temp;
	}
	
	/**
	 * Recursive method that will continue building the phrase with no spaces
	 * 
	 * @param s String object
	 * @return Part of the completed phrase
	 */
	private String phraseNoSpace (String s) {
		String temp = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '<')  //Add each character that isn't the beginning of a non-terminal
				temp += s.charAt(i);
			
			else {
				String nonTerminal = String.valueOf(s.charAt(i));
				int j = i + 1;
				
				while (j < s.length()) {  //Build the non-terminal
					if (s.charAt(j) != '>') {
						nonTerminal += s.charAt(j);
						j++;
					}
					
					if (s.charAt(j) == '>') {  //Reached the end of the non-terminal
						nonTerminal += s.charAt(j);
						break;
					}
				}
				temp += phraseNoSpace(findArray(nonTerminal));  //Recurse with the non-terminal and add to final phrase
				i += nonTerminal.length() - 1;  //Continue traversing in the correct spot
			}
		}
		s = temp;
	return s;
	}
	
	/**
	 * Finds the list of production rules associated with a non-terminal and returns a random production rule
	 * 
	 * @param s non-terminal
	 * @return random production rule
	 */
	private String findArray(String s) {
		boolean hasExtra = false;  //Keeps track if there is an extra characters at the end of a non-terminal
		Character extra = null;
		if (!s.endsWith(">")) {  //Store extra character if it doesn't end with ">"
			extra = s.charAt(s.length() - 1);
			s = s.substring(0, s.length()-1);
			hasExtra = true;
		}
		
		ArrayList<String> listOfStrings = table.get(s);  //Get the list of production rules
		if (hasExtra)
			return listOfStrings.get(rng.nextInt(listOfStrings.size() - 1) + 1) + extra;  //Return a random production rule with the extra character
		
		return listOfStrings.get(rng.nextInt(listOfStrings.size() - 1) + 1);  //Return a random production rule
	}
}
