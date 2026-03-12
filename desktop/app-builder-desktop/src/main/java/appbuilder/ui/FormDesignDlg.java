package appbuilder.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import appbuilder.entity.ComponentType;
import appbuilder.entity.Form;
import appbuilder.entity.FormField;
import framework.presentation.swing.Window;
import framework.swing.Button;
import framework.swing.Label;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class FormDesignDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private List<FormField>fields = new ArrayList<FormField>();

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPanel previewPane;


	
	void criarForm() {
		Form form = new Form();
		
		for (FormField formField : fields) {
			form.addFormField(formField);	
		}
		AppDlg.addForm(form);
		
		this.dispose();
	}


	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}
	
	public List<FormField> getFields() {
		return fields;
	}
	
	public void populatePreviewPane() {
		Dimension prefSize = new Dimension(350, 45);
		
		for (FormField field : fields) {
			ComponentType compType 	= field.getComponentType();
			String fieldLabel		= field.getLabel();
			
			switch (compType.getId()) {
				case ComponentType.CAIXA_DE_TEXTO:
					addTextBox(prefSize, fieldLabel);
					break;
				case ComponentType.AREA_DE_TEXTO:
					addTextArea(fieldLabel);
					break;
				case ComponentType.CAIXA_DE_CHECAGEM:
					addCheckBox(fieldLabel);
					break;
				case ComponentType.LISTA_DE_VALORES:
					addComboBox(prefSize, fieldLabel);
					break;
				default:
					break;
			}
			previewPane.doLayout();
		}
	}

	private void addTextBox(Dimension prefSize, String fieldLabel) {
		Label label = getLabel(fieldLabel);
		JTextField tx = new JTextField();
		tx.setPreferredSize(prefSize);
		tx.setColumns(35);
		addToPreview(tx, label);
	}
	
	private void addComboBox(Dimension prefSize, String fieldLabel) {
		Label label;
		label = getLabel(fieldLabel);
		JComboBox cb = new JComboBox();
		cb.setPreferredSize(
			new Dimension(
				(int)prefSize.getWidth() + 45, 
				(int)prefSize.getHeight()
			)
		);
		addToPreview(cb, label);
	}

	private void addCheckBox(String fieldLabel) {
		Label label;
		label = getLabel(fieldLabel);
		JCheckBox ck = new JCheckBox();
		ck.setSelected(true);
		addToPreview(ck, label);
	}

	private void addTextArea(String fieldLabel) {
		Label label = getLabel(fieldLabel);
		JTextArea ta = new JTextArea();
		ta.setPreferredSize(new Dimension(395, 75));
		addToPreview(ta, label);
	}

	private Label getLabel(String fieldLabel) {
		Label label = new Label(fieldLabel);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setPreferredSize(new Dimension(180, 40));
		
		return label;
	}
	
	private void addToPreview(Component field, Label label) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(750,100));
		
		if (field instanceof JCheckBox) {
			label.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(field);
			panel.add(label);
		} else {
			panel.add(label);
			panel.add(field);
		}
		previewPane.add(panel);
	}
	
	void novoCampoActionPerformed() {
		FieldDlg dialog = new FieldDlg();
		dialog.postConstruct();
		dialog.setParentForm(this);
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
			FormDesignDlg dialog = new FormDesignDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			Window.centralizeWindow(dialog);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormDesignDlg() {
		setBounds(100, 100, 1192, 776);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblInformeONome = new Label("Informe o nome do Formul\u00E1rio");
		lblInformeONome.setBounds(12, 13, 253, 16);
		contentPanel.add(lblInformeONome);
		
		textField = new AppTextField();
		textField.setBounds(12, 42, 760, 35);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 186, 1150, 16);
		contentPanel.add(separator);
		
		JButton novoCampo = new Button("Incluir novo Campo");
		novoCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoCampoActionPerformed();
			}
		});
		novoCampo.setBounds(861, 13, 301, 47);
		contentPanel.add(novoCampo);
		
		JButton cancelar = new Button("Cancelar e Retornar");
		cancelar.setBounds(861, 123, 301, 47);
		contentPanel.add(cancelar);
		
		JButton finalizar = new Button("Finalizar e Retornar");
		finalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarForm();
			}
		});
		finalizar.setBounds(861, 69, 301, 47);
		contentPanel.add(finalizar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(815, 13, 13, 161);
		contentPanel.add(separator_1);
		
		Label lblpreviewDoLayout = new Label("Informe o nome do Formul\u00E1rio");
		lblpreviewDoLayout.setText("\"PREVIEW\" do Layout do Formul\u00E1rio");
		lblpreviewDoLayout.setBounds(12, 202, 390, 16);
		contentPanel.add(lblpreviewDoLayout);
		
		previewPane = new JPanel();
		previewPane.setBounds(12, 243, 1150, 472);
		contentPanel.add(previewPane);
	}

}
