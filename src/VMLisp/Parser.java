package VMLisp;

import java.util.List;

// 構文解析クラス
public class Parser {
	public List<String> tokens;
	public int i = 0;
	public Node node;

	public Parser(List<String> tokens) {
		this.tokens = tokens;
	}

	// 木構造生成メソッド。右の枝は次の"(",左の枝は次の要素を参照できるように生成していく。
	public Node maketree() {
		Node node = null;
		if (i < tokens.size()) {
			switch (tokens.get(i)) {
			case "(":
				node = new Node(tokens.get(i));
				i++;
				node.leftNode = maketree();
				node.rightNode = maketree();
				break;

			case ")":
				i++;
				break;

			default:
				node = new Node(tokens.get(i));
				i++;
				if (i < tokens.size()) {
					if (tokens.get(i).equals("(")) {
						node.rightNode = maketree();

					} else {
						node.leftNode = maketree();
					}
					break;
				}
			}
		}
		return node;
	}

	// テスト用
	public void printtree(Node node) {
		this.node = node;
		System.out.println(node.token);

		if (node.leftNode != null)
			printtree(node.leftNode);
		if (node.rightNode != null)
			printtree(node.rightNode);

	}
}
