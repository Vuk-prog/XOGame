package GUI;

import javax.swing.JButton;

public class MyButton extends JButton {
	
	private int i;
	private int j;
	
	public int getI() {
		return i;
	}
	public int getJ() {
		return j;
	}
	
	public MyButton(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	

}
