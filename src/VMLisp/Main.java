package VMLisp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Node.Node;



public class Main {
	public static void main(String[] args) throws IOException{
		Input in = new Input();
		Compiler compiler = new Compiler();
		Executer executer = new Executer();
		System.out.println("\n Input Command \n");
		System.out.println("IN: ");
		LoadFile loadFile;


		System.out.println("======Welcome to my Lisp=======");
		if (args.length != 0) { // ファイルの指定があった場合.
			loadFile = new LoadFile(args[0]);
			Lexer lexer = new Lexer();
			List<String> tokenresult = lexer
					.tokenize(loadFile.getinputString());
			Parser syntactic = new Parser(tokenresult);
			Node result = syntactic.maketree();
			compiler.Analysis(result);
			executer.funcList = compiler.funcList;
			VM vm = new VM();
			executer.execute(vm, compiler.codeList);
			compiler.codeList = new ArrayList<VMCode>();
			compiler.flag = 0;


		} else {
		while (!in.input().equals("quit")) {
			Lexer lexer = new Lexer();
			List<String> tokenresult = lexer.tokenize(in.getString());
			Parser syntactic = new Parser(tokenresult);
			Node result = syntactic.maketree();

			compiler.Analysis(result);
			if (compiler.codeList.size() != 0) {
				//for test dump
				/*for (int i = 0; i < compiler.codeList.size(); i++) {
					if (compiler.codeList.get(i).command != null){
						System.out.println(i + ":" + compiler.codeList.get(i).command);
					}
					else {
						System.out.println(i + ":" +  compiler.codeList.get(i).value);
					}
				}
				*/
				executer.funcList = compiler.funcList;
				VM vm = new VM();
				executer.execute(vm, compiler.codeList);
				compiler.codeList = new ArrayList<VMCode>();
				compiler.flag = 0;
			}

			System.out.println("IN: ");
		}
		}
		System.out.println("\n==============END=============");
	}
}








	/*public static void main(String[] args) throws IOException {

		Input in = new Input();
		LoadFile loadFile;

		System.out.println("======Welcome to my Lisp=======");
		if (args.length != 0) { // ファイルの指定があった場合.
			loadFile = new LoadFile(args[0]);
			Lexer lexer = new Lexer();
			List<String> tokenresult = lexer
					.tokenize(loadFile.getinputString());
			Parser syntactic = new Parser(tokenresult);
			Node result = syntactic.maketree();

		} else {
			System.out.println("\n Input Command \n");
			System.out.println("IN: ");
			while (!in.input().equals("quit")) {
				Lexer lexer = new Lexer();
				List<String> tokenresult = lexer.tokenize(in.getString());
				Parser syntactic = new Parser(tokenresult);
				Node result = syntactic.maketree();
				System.out.println("IN: ");
			}
		}
		System.out.println("\n==============END=============");
	}
	*/
