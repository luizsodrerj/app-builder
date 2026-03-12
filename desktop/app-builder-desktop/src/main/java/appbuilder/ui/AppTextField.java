package appbuilder.ui;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class AppTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	
	public AppTextField() {
		setFont(new Font("Tahoma", Font.PLAIN, 14));
	}

	public AppTextField(String text) {
		super(text);
	}

	public AppTextField(int columns) {
		super(columns);
	}

	public AppTextField(String text, int columns) {
		super(text, columns);
	}

	public AppTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
	}

}
