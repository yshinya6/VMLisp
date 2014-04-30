package VMLisp;

import java.util.ArrayList;
import java.util.List;

//字句解析クラス
public class Lexer {
	public String expr;
	public String[] s, token;
	public List<String> tokenList;
	public int start = 0;
	public int end = 0;
	public char c;

	public Lexer() {
		this.tokenList = new ArrayList<String>();
	}

	// 字句分解メソッド
	public List<String> tokenize(String expr) {
		for (int i = 0; i < expr.length(); i++) {

			c = expr.charAt(i);
			if (c == ' ') { // スペースが読み込まれたとき、開始番号を進める。
				if ('0' <= expr.charAt(i + 1) && expr.charAt(i + 1) <= '9') {
					start = i + 1;
				}
				continue;
			}

			if ('0' <= c && c <= '9') { // 数字の場合は終端番号を更新する。
				end = i;
				if (expr.charAt(i - 1) == ' ') {
					start = i; // ひとつ前の文字がスペースならそれは数字列の開始とみなす。
				}
				if (expr.charAt(i + 1) == ' ' || expr.charAt(i + 1) == ')'
						|| expr.charAt(i + 1) == '(') {
					end = i + 1; // ひとつ後がスペースか括弧なら数字列の終端とみなす。
					tokenList.add(expr.substring(start, end));
				}
			}

			if ('a' <= c && c <= 'z') { // 文字の場合は終端番号を更新する。
				end = i;
				if (expr.charAt(i - 1) == ' ') {
					start = i; // ひとつ前の文字がスペースならそれは文字列の開始とみなす。
				}
				if (expr.charAt(i + 1) == ' ' || expr.charAt(i + 1) == ')'
						|| expr.charAt(i + 1) == '(') {
					end = i + 1; // ひとつ後がスペースなら文字列の終端とみなす。
					tokenList.add(expr.substring(start, end));
				}
			}

			switch (c) {
			case '+':
			case '-':
			case '/':
			case '*':
			case '>':
			case '=':
			case '<':

				if (expr.charAt(i + 1) == ' ') { // 後ろがスペース＝演算子の場合
					tokenList.add(expr.substring(i, i + 1)); // その文字だけをトークンリストに追加
					start = i + 1;
				} else if (('0' <= c && c <= '9') || ('a' <= c && c <= 'z')) { // 符号の場合
					end = i; // 終端番号を更新
				} else { // それ以外は演算子であるから、まとめて格納してしまう。
					tokenList.add(expr.substring(i, i + 2));
					start = i + 2;
					i++;
				}
				break;

			default:
				break;
			}

			if (c == '(' || c == ')') { // 括弧が出てきた場合は
				tokenList.add(expr.substring(i, i + 1)); // 格納して開始番号を進める。
				start = i + 1;
			}
		}
		return tokenList;
	}

}
