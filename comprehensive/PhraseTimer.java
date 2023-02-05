package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class PhraseTimer {

	public static void main(String[] args) throws FileNotFoundException {
		int timesToLoop = 1000;
		int incr = 1000;
		int probSize = 1000;
		Random rng = new Random(55);

		long startTime, midPointTime, stopTime;
		
		File file = new File("testing.g");
		Database database = new Database (file);
		
		for (int j = 0; j < 10; j++) {
			startTime = System.nanoTime();
			for (int i = 0; i < probSize; i++) {
				database.buildPhrase();
			}
		
			stopTime = System.nanoTime();
			double averageTime = ((stopTime) - (startTime)) / (double) 1;
			System.out.println(probSize + "  " + averageTime);
			probSize += incr;
		}
	}
}
