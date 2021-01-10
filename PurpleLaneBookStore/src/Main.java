import java.awt.GraphicsEnvironment;

import controllers.*;
import views.*;

public class Main {

	public Main() {
		Controller.getInstance().showHomeView();
	}

	public static void main(String[] args) {
		new Main();

	}
}
