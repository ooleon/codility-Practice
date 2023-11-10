package localhost;

import java.io.*;
import java.net.*;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodilityDemoApplicationTests {

	@Test
	void bkMainPathTest() throws IOException, URISyntaxException {
		// Reading from directory test/resourse
		URL resource = getClass().getClassLoader().getResource("test-input.txt");
		if (resource == null) {
			throw new IllegalArgumentException("file not found!");
		} else {

			File file = new File(resource.toURI());
			FileInputStream fileIS = new FileInputStream(file);
//			System.setIn(new ByteArrayInputStream( fileIS.readAllBytes()));
			InputStream in = fileIS;
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();

			int linesInFile = (int) Files.lines(file.toPath()).count();
			System.out.println(linesInFile);

			int[] intArray = new int[linesInFile];

			String read;
			String[] sArray = { "" };
			while ((read = br.readLine()) != null) {
				// System.out.println(read);
				sb.append(read);
				sArray = read.split(",");
			}

			br.close();

			int[] a = new int[sArray.length];
			for (int i = 0; i < sArray.length; i++) {
				a[i] = Integer.valueOf(sArray[i].trim());
			}

//			https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java

			Solution s = new Solution();

			System.out.println(s.solution(a));
		}
	}

}
