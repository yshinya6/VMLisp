package VMLisp;



public class VM {
	int stackTop;
	int[] stack; // スタックの配列
	int pc; // プログラムカウンタ.現在実行している命令のアドレスを持つ.
	int fp; // 関数ポインタの役割を果たす.
	public VM(){
		this.stackTop = 0;
		this.pc = 0;
		this.fp = 0;
		this.stack = new int[1000];
	}

	public void push(int value){
			stack[++stackTop] = value;
	}

	public int pop(){
		int value = stack[stackTop];
		stack[stackTop] = 0;
		stackTop--;
		return value;
		}
	}

