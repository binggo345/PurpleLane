package views;

import javax.swing.JFrame;

public abstract class View extends JFrame {

	protected int width;
	protected int height;
	
	public View() {
		initialize();
		addComponent();
		addListener();
	}
	
	public void showForm() {
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public abstract void initialize();
	public abstract void addComponent();
	public abstract void addListener();
}
