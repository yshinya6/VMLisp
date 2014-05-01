package VMLisp;

import java.util.ArrayList;


public class Main {
	public static void main(String[] args){
		VM vm = new VM();
		ArrayList<VMCode> fibCode = new ArrayList<VMCode>();
		ArrayList<VMCode> takCode = new ArrayList<VMCode>();
		Executer executer = new Executer();

	//fibCode
		fibCode.add(new VMCode(Executer.command.PUSH));
		fibCode.add(new VMCode(36));
		fibCode.add(new VMCode(Executer.command.CALL));
		fibCode.add(new VMCode(1));
		fibCode.add(new VMCode(Executer.command.PRINT));
		fibCode.add(new VMCode(Executer.command.RET));
		//executer.funcList.add(new Function(fibCode.size(), 1));
		fibCode.add(new VMCode(Executer.command.LOAD_ARG));
		fibCode.add(new VMCode(1));
		fibCode.add(new VMCode(Executer.command.PUSH));
		fibCode.add(new VMCode(3));
		fibCode.add(new VMCode(Executer.command.LT));
		fibCode.add(new VMCode(Executer.command.IF_JUMP));
		fibCode.add(new VMCode(16));
		fibCode.add(new VMCode(Executer.command.PUSH));
		fibCode.add(new VMCode(1));
		fibCode.add(new VMCode(Executer.command.RET));
		fibCode.add(new VMCode(Executer.command.LOAD_ARG));
		fibCode.add(new VMCode(1));
		fibCode.add(new VMCode(Executer.command.PUSH));
		fibCode.add(new VMCode(1));
		fibCode.add(new VMCode(Executer.command.SUB));
		fibCode.add(new VMCode(Executer.command.CALL));
		fibCode.add(new VMCode(1));
		fibCode.add(new VMCode(Executer.command.LOAD_ARG));
		fibCode.add(new VMCode(1));
		fibCode.add(new VMCode(Executer.command.PUSH));
		fibCode.add(new VMCode(2));
		fibCode.add(new VMCode(Executer.command.SUB));
		fibCode.add(new VMCode(Executer.command.CALL));
		fibCode.add(new VMCode(1));
		fibCode.add(new VMCode(Executer.command.ADD));
		fibCode.add(new VMCode(Executer.command.RET));


		//takCode
		takCode.add(new VMCode(Executer.command.PUSH));
		takCode.add(new VMCode(12));
		takCode.add(new VMCode(Executer.command.PUSH));
		takCode.add(new VMCode(6));
		takCode.add(new VMCode(Executer.command.PUSH));
		takCode.add(new VMCode(0));
		takCode.add(new VMCode(Executer.command.CALL));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.PRINT));
		takCode.add(new VMCode(Executer.command.RET));
		executer.funcList.add(new Function(takCode.size(), 3));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(2));
		takCode.add(new VMCode(Executer.command.LTEQ));
		takCode.add(new VMCode(Executer.command.IF_JUMP));
		takCode.add(new VMCode(20));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(2));
		takCode.add(new VMCode(Executer.command.RET));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.PUSH));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.SUB));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(2));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(3));
		takCode.add(new VMCode(Executer.command.CALL));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(2));
		takCode.add(new VMCode(Executer.command.PUSH));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.SUB));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(3));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.CALL));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(3));
		takCode.add(new VMCode(Executer.command.PUSH));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.SUB));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.LOAD_ARG));
		takCode.add(new VMCode(2));
		takCode.add(new VMCode(Executer.command.CALL));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.CALL));
		takCode.add(new VMCode(1));
		takCode.add(new VMCode(Executer.command.RET));

		executer.execute(vm, takCode);
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
}