package VMLisp;

import Node.Node;


public class Function {
	public int startAddress;
	public int argSize;
	public Node funcNode;

	public Function(int startAddress, int argSize){ // 直打ち用デフォルトコンストラクタ.
		this.startAddress = startAddress;
		this.argSize = argSize;
	}

	public Function(int startAddress, int argSize, Node funcNode){
		this.startAddress = startAddress;
		this.argSize = argSize;
		this.funcNode = funcNode;
	}
}
