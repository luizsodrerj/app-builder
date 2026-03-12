package appbuilder.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import appbuilder.entity.App;
import appbuilder.entity.Form;
import framework.presentation.swing.Window;
import framework.swing.Button;
import framework.swing.Label;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AppDlg extends JDialog {

	private static final long serialVersionUID = 1L;

	private static List<Form>forms = new ArrayList<Form>();

	private static List<App>apps = new ArrayList<App>();
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	
	public static void addForm(Form form) {
		forms.add(form);
	}

	void saveApplication() {
		App app = new App();
		
		for (Form form : forms) {
			app.addForm(form);	
		}
		apps.add(app);
	}
	
	void createFormAction() {
		FormDesignDlg dialog = new FormDesignDlg();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(dialog);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AppDlg dialog = new AppDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AppDlg() {
		setTitle("Criar Aplica\u00E7\u00E3o");
		setBounds(100, 100, 787, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblInformeONome = new Label("Informe o nome da sua Aplica\u00E7\u00E3o");
		lblInformeONome.setBounds(12, 13, 254, 16);
		contentPanel.add(lblInformeONome);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(12, 42, 447, 42);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton save = new Button("Salvar e Finalizar");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveApplication();
			}
		});
		save.setBounds(507, 23, 250, 42);
		contentPanel.add(save);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(479, 13, 16, 297);
		contentPanel.add(separator);
		
		JButton cancel = new Button("Cancelar");
		cancel.setBounds(507, 78, 250, 42);
		contentPanel.add(cancel);
		
		JButton createForm = new Button("Criar primeiro Formul\u00E1rio");
		createForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFormAction();
			}
		});
		createForm.setBounds(507, 133, 250, 42);
		contentPanel.add(createForm);
	}


}
