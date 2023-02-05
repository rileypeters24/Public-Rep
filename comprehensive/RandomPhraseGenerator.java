package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Create the designated number of phrases from a given Grammar file provided by the user
 * 
 * @author Riley Peters
 * @version 4/26/22
 */
public class RandomPhraseGenerator {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File(args[0]);  //File provided by user
		
		Database database = new Database(file);  //Creates database from given file
		
		for (int i = 0; i < Integer.parseInt(args[1]); i++) {  //Print desired number of phrases
			System.out.println(database.buildPhrase());
		}
	}

}