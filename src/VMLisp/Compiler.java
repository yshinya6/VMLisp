package VMLisp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Node.Node;

public class Compiler {
	public Node tree;
	public int funcCount = 0,flag = 0;
	public Map<String, Integer> funcMap = new HashMap<String, Integer>();
	public Map<String, Integer> argMap = new HashMap<String, Integer>();
	public ArrayList<VMCode> codeList = new ArrayList<VMCode>();
	public ArrayList<Function> funcList = new ArrayList<Function>();

	// 木構造解析メソッド。このメソッドを再帰させることで与えられた木を評価していく。
	public void Analysis(Node tree) {

		switch (tree.token) {
		case "(":
			Node carNode = tree;
			tree = tree.leftNode;
			Analysis(tree);
			tree = carNode;
			if (tree.rightNode != null) { // さらに右の木がある場合には再帰走査。
				tree = tree.rightNode;
				Analysis(tree);
			}
			break;

		case "+":
			genOPCode(tree,Executer.command.ADD);
			break;
		case "-":
			genOPCode(tree,Executer.command.SUB);
			break;
		case "*":
			genOPCode(tree,Executer.command.MUL);
			break;
		case "/":
			genOPCode(tree,Executer.command.DIV);
			break;
		case ">":
			genOPCode(tree,Executer.command.GT);
			break;
		case "=":
			genOPCode(tree,Executer.command.EQ);
			break;
		case "<":
			genOPCode(tree,Executer.command.LT);
			break;
		case "<=":
			genOPCode(tree,Executer.command.LTEQ);
			break;
		case ">=":
			genOPCode(tree,Executer.command.GTEQ);
			break;

		case "if":
			ifCompile(tree);
			break;

		case "defun":
			defun(tree);
			break;

		default:
			if (check(tree.token)){		 // 数字の場合.
				genNumCode(tree.token);
			}
			else { // 文字の場合.
				if (argMap.get(tree.token) != null) { // 引数の場合.
					genArgCode(tree.token);
				}else if (funcMap.get(tree.token) != null) { // 関数の場合.
					switch (flag) {
					case 0: // 呼び出し一回目
						flag = 1;
						funcCompile(tree, funcMap.get(tree.token));

						break;
					default: // 二回目以降
						genFuncCode(tree, funcMap.get(tree.token));
						break;
					}

				}else{ // それ以外の文字(変数定義には未対応)
					System.out.println("COMPILE ERROR !! (I DON'T KNOW "+ tree.token + " .)");
					break;
				}
			}

			if (tree.leftNode != null ) {
				Analysis(tree.leftNode);
			}
			break;
		}
	}






	// 関数定義メソッド
	public void defun(Node tree) {

		tree = tree.rightNode;
		Node carNode = tree;
		tree = tree.leftNode;

		funcMap.put(tree.token, funcCount++); // 関数名の登録.

		carNode = carNode.rightNode;
		tree = carNode.leftNode;

		int i = 0;
		while (tree != null) { // 引数の登録
			argMap.put(tree.token, i++);
			tree = tree.leftNode;
		}

		carNode = carNode.rightNode; // 式部分の括弧を指定する。
		tree = carNode;

		funcList.add(new Function(0, i, tree));


	}

	//数字コード生成メソッド
	public void genNumCode(String value){
		codeList.add(new VMCode(Executer.command.PUSH));
		codeList.add(new VMCode(Integer.valueOf(value)));
	}

	//引数コード生成メソッド.
	public void genArgCode(String argName){
		codeList.add(new VMCode(Executer.command.LOAD_ARG));
		codeList.add(new VMCode(argMap.get(argName)));
	}

	//演算子コード生成メソッド
	public void genOPCode(Node tree, Executer.command OP){
		if (tree.leftNode != null){
			Node preNode = tree;
			tree = tree.leftNode;
			Analysis(tree);
			tree = preNode;
		}
		if (tree.rightNode != null){
			Node preNode = tree;
			tree = tree.rightNode;
			Analysis(tree);
			tree = preNode;
		}
		codeList.add(new VMCode(OP));
	}
	//関数呼び出しコード生成メソッド
	public void genFuncCode(Node tree, int funcNum){
		tree = tree.rightNode;
		Analysis(tree.leftNode);
		codeList.add(new VMCode(Executer.command.CALL));
		codeList.add(new VMCode(funcNum));
		if (tree.rightNode != null) {
			Analysis(tree.rightNode);
		}
	}

	// if文コンパイルメソッド
	public void ifCompile(Node tree){
		tree = tree.rightNode;
		Node carNode = tree;
		Analysis(carNode.leftNode); // 条件式の読み込み.
		codeList.add(new VMCode(Executer.command.IF_JUMP));
		codeList.add(new VMCode(0)); // JUMP先の番号はまだ決定していないので0としておき、領域だけ確保しておく.
		int index = codeList.size() - 1;
		tree = tree.rightNode;
		carNode = tree;
		Analysis(carNode.leftNode); // true式の読み込み.
		codeList.add(new VMCode(Executer.command.RET));
		codeList.set(index,  new VMCode(codeList.size())); // JUMP先のコード番号をセットする.
		tree = tree.rightNode;
		carNode = tree;
		Analysis(carNode.leftNode); // false式の読み込み.
		if (flag != 1) {
		codeList.add(new VMCode(Executer.command.RET));
		}
	}
	// 関数コンパイルメソッド
	public void funcCompile(Node tree, int funcNum){
		tree = tree.rightNode;
		Analysis(tree);
		codeList.add(new VMCode(Executer.command.CALL));
		codeList.add(new VMCode(funcNum));
		codeList.add(new VMCode(Executer.command.PRINT));
		codeList.add(new VMCode(Executer.command.RET));
		funcList.get(funcNum).startAddress = codeList.size();
		tree = funcList.get(funcNum).funcNode;
		Analysis(tree);
		codeList.add(new VMCode(Executer.command.RET));



	}

	// 文字列が数字であるかどうか判別するメソッド
	public boolean check(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
