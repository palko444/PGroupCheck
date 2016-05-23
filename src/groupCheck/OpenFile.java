package groupCheck;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OpenFile {

	public static ArrayList<String> readFileAsLines(String file) {
		ArrayList<String> data = new ArrayList<String>();
		String line = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				data.add(line);
			}
			br.close();
		} catch (FileNotFoundException fe) {
			System.out.println("WARNING: File does not exist " + file);
			System.exit(1);
		} catch (IOException ie) {
			System.out.println("WARNING: There is issue with reading a file " + file);
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("WARNING: Cannot close file: " + file);
			}
			System.exit(1);
		}
		return data;
	}
}
