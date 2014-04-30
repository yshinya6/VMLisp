package VMLisp;

import java.util.ArrayList;
import java.util.LinkedList;

public class Executer {
	int arg1, arg2, arg3,count = 0;
	public ArrayList<Function> funcList = new ArrayList<Function>(); // 関数の開始番号を関数ごとに格納したリスト.
	public ArrayList<VMCode> codeList = new ArrayList<VMCode>(); // 入力コードのリスト.
	public LinkedList<Integer> retAdd = new LinkedList<Integer>();

	public enum command {
		RET, // スタックのトップを返り値として関数呼び出しを終了する.
		POP, // スタックから1つだけPOPする.
		PUSH, // 数字をPUSHする.
		ADD, // スタックの上二つをPOPして足し算を実行し、結果をプッシュする.
		SUB, // 引き算
		MUL, // 乗算
		DIV, // 除算
		PRINT, // print文を実行する
		EQ, // "=="スタックの上二つに対して比較しtrueなら1,falseなら0を返す.
		NEQ, // "!="比較演算
		LT, // "<"比較演算
		LTEQ, // "<="比較演算
		GT, // ">"比較演算
		GTEQ, // ">="比較演算
		IF_JUMP, // if文にジャンプ.
		CALL, // 関数呼び出し
		LOAD_ARG, // 引数のPUSH.
	}


	command c;

	public void execute(VM vm, ArrayList<VMCode> codeList) {

		while ((c = codeList.get(vm.pc).command) != null) {
			switch (c) {
			case RET:
				if (count == 0) {
					return;
				} else {
					vm.pc = retAdd.pop() - 1;
					count--;
				}
				break;


			case POP:
				vm.pop();
				break;

			case PUSH:
				vm.push(codeList.get(++vm.pc).value);
				break;

			case ADD:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 + arg2);
				break;
			case SUB:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 - arg2);
				break;
			case MUL:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 * arg2);
				break;
			case DIV:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 / arg2);
				break;
			case PRINT:
				System.out.println(vm.stack[vm.stackTop]);
				break;
			case EQ:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 == arg2 ? 1 : 0); // trueなら1を、falseなら2を返す。以下同様。
				break;
			case NEQ:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 != arg2 ? 1 : 0);
				break;
			case LT:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 < arg2 ? 1 : 0);
				break;
			case LTEQ:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 <= arg2 ? 1 : 0);
				break;
			case GT:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 > arg2 ? 1 : 0);
				break;
			case GTEQ:
				arg2 = vm.pop();
				arg1 = vm.pop();
				vm.push(arg1 >= arg2 ? 1 : 0);
				break;
			case IF_JUMP:
				if (vm.stack[vm.stackTop] != 1) { // 一つ前で行った比較演算の結果を参照して、falseの場合.
					vm.pc = codeList.get(vm.pc + 1).value - 1; // 次に格納されているはずのコマンドナンバーに照準を合わせる.
					// -1は実際の格納番号に合わせるため.
				} else { // trueの場合はそのまま命令文を読み進める.
					vm.pc++;
				}
				break;
			case CALL:
				retAdd.push(vm.pc + 2);	// 関数呼び出し命令の次の命令を格納したアドレスをスタックしておく.
				vm.fp = vm.stackTop;	// 現在のスタックトップが関数ポインターとなる.
				vm.pc = funcList.get(codeList.get(vm.pc + 1).value - 1).startAddress; // program counterを関数の開始アドレスに合わせる.
				vm.pc--;	 // 次のインクリメントで丁度開始アドレスになるように調整しておく.
				count++;
				break;
			case LOAD_ARG:
				int num = codeList.get(++vm.pc).value;	 // 引数の番号を読み取る.
				int arg = vm.stack[vm.fp - num]; // 引数を呼び出す."-2"は上で加えたスタックの分.そこからnum分引くことで引数を得られることになる.
				vm.push(arg);
				break;
			}
			vm.pc++;
		}
	}

}
