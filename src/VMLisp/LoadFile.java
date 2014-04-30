package VMLisp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadFile {
	private String inputString;

	public LoadFile(String path) throws IOException {
		try {
			File file = new File(path);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			StringBuilder stringInFile = new StringBuilder();
			System.out.println("\n==========FILE MODE===========\n");
			while ((s = br.readLine()) != null) {
				stringInFile.append(s); // ビルダに保存する.
				System.out.println(s); // 確認のため表示する.
			}
			this.inputString = new String(stringInFile);
			br.close();
			System.out.println("\n==============================\n");

		} catch (FileNotFoundException e) {
			System.out.println("Can't Open this File.");
		}
	}

	public String getinputString() {
		return this.inputString;
	}
}
