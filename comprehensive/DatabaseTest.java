package comprehensive;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatabaseTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() throws FileNotFoundException {
		File file = new File("abc.g");
		Database database = new Database(file);
		database.buildPhrase();
	}

}
