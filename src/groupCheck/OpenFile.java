package groupCheck;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OpenFile {
	String file;

	public OpenFile(String file) {
		this.file = file;
	}

	public ArrayList<String> getOMs() {
		ArrayList<String> data = new ArrayList<String>();
		String line = null;
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				data.add(line);
			}
			br.close();
		} catch (FileNotFoundException fe) {
			System.out.println("File does not exist " + file);
			System.exit(1);
		} catch (IOException ie) {
			System.out.println("There is issue with reading a file " + file);
			System.exit(1);

		}
		return data;
	}
}
