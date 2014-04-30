package VMLisp;

import java.util.LinkedList;

public class Variable {
	public String name;
	public String value;
	public LinkedList<String> stk; // 作業用スタック。Evaluater内で使用。

	public Variable(String name, String value) {
		this.name = name;
		this.value = value;
		stk = new LinkedList<String>();
	}
}
