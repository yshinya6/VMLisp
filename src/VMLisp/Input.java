package VMLisp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
	private String string;

	public String input() throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		this.string = br.readLine();

		return this.string;
	}

	public String getString() {
		return this.string;
	}
}
