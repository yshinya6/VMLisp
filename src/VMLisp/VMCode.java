package VMLisp;

public class VMCode {
	public Executer.command command;
	public int value;

	public VMCode(int value) {
		this.value = value;
	}
	public VMCode(Executer.command command){
		this.command = command;
	}

}
