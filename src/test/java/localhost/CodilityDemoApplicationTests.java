package localhost;

import java.io.*;
import java.net.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodilityDemoApplicationTests {

	@Test
	void bkMainPathTest() throws IOException, URISyntaxException {
		URL resource = getClass().getClassLoader().getResource("test-input.txt");
		if (resource == null) {
			throw new IllegalArgumentException("file not found!");
		} else {

			File file = new File(resource.toURI());
			FileInputStream fileIS = new FileInputStream(file);
			System.setIn(new ByteArrayInputStream( fileIS.readAllBytes()));
//			TOREAD
//			https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
//			StringBufferInputStream bi = new StringBufferInputStream( fileIS.readAllBytes());
//			StringBufferInputStream bi = new StringBufferInputStream(fileIS. );
			Solution s = new Solution();
//			bi.
			int[] a = {1, 3, 6, 4, 1, 2};
			System.out.println(s.solution(a));
		}
	}

}
