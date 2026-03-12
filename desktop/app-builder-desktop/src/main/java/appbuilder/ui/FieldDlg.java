package appbuilder.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import appbuilder.entity.ComponentType;
import appbuilder.entity.DataType;
import appbuilder.entity.FormField;
import framework.persistence.jpa.PersistenceServiceUtil;
import framework.presentation.swing.Window;
import framework.swing.Button;
import framework.swing.Label;

public class FieldDlg extends JDialog {

	private static final long serialVersionUID = 1L;

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();

	private List<ComponentType>compTypes;
	private List<DataType>types; 		

	private FormDesignDlg parent;
	private JComboBox tipoComponente;
	private Component previewComp;
	private JComboBox tipoDado;
	private JTextField label;
	private JPanel panel;
	

	void confirmarAction() {
		ComponentType compType 	= compTypes.get(tipoComponente.getSelectedIndex() - 1);
		DataType type			= types.get(tipoDado.getSelectedIndex() - 1);
		
		FormField field = new FormField();
		field.setLabel(label.getText());
		field.setComponentType(compType);
		field.setDataType(type);
		
		List<FormField>fields = new ArrayList<FormField>();
		fields.addAll(parent.getFields());
		fields.add(field);
		
		reloadParent(fields);
	}
	
	void reloadParent(List<FormField>fields) {
		this.parent.dispose();
		
		FormDesignDlg parent = new FormDesignDlg();
		parent.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(parent);

		parent.setFields(fields);
		parent.populatePreviewPane();
		
		parent.setModal(true);
		parent.setVisible(true);
		this.dispose();
	}
	
	
	public void setParentForm(FormDesignDlg parent) {
		this.parent = parent;
	}
	
	void preview() {
		if (tipoComponente.getSelectedIndex() == 0) {
			return;
		}
		Dimension prefSize	= new Dimension(375, 45);
		ComponentType type 	= compTypes.get(tipoComponente.getSelectedIndex() - 1);
		int typeId 			= type.getId();  
		
		switch (typeId) {
			case ComponentType.CAIXA_DE_TEXTO:
				addTextBox(prefSize);
				break;
			case ComponentType.AREA_DE_TEXTO:
				addTextArea(prefSize);
				break;
			case ComponentType.CAIXA_DE_CHECAGEM:
				addCheckBox(prefSize);
				break;
			case ComponentType.LISTA_DE_VALORES:
				addComboBox(prefSize);				
				break;
			default:
				break;
		}
		panel.doLayout();
	}

	private void addComboBox(Dimension prefSize) {
		Label label = new Label(this.label.getText());
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setPreferredSize(prefSize);
		JComboBox cb = new JComboBox();
		cb.setPreferredSize(prefSize);
		addPreviewComponent(JComboBox.class, cb, false, label);
	}

	private void addCheckBox(Dimension prefSize) {
		Label label = new Label(this.label.getText());
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setPreferredSize(prefSize);
		JCheckBox ck = new JCheckBox();
		ck.setSelected(true);
		addPreviewComponent(JCheckBox.class, ck, true, label);
	}

	private void addTextArea(Dimension prefSize) {
		Label label = new Label(this.label.getText());
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setPreferredSize(prefSize);
		JTextArea ta = new JTextArea();
		ta.setPreferredSize(new Dimension(375, 75));
		addPreviewComponent(JTextArea.class, ta, false, label);
	}

	private void addTextBox(Dimension prefSize) {
		Label label = new Label(this.label.getText());
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setPreferredSize(prefSize);
		JTextField tx = new JTextField();
		tx.setPreferredSize(prefSize);
		tx.setColumns(25);
		addPreviewComponent(JTextField.class, tx, false, label);
	}

	private void addPreviewComponent(
					Class componentClass, 
					Component component,
					boolean labelAfter,
					JLabel label
				 ) {
		boolean compNotNull = previewComp != null;
		boolean notEquals	= compNotNull && !(
											    previewComp.getClass()
											    		   .getName()
											    		   .equals(
											    			componentClass.getName()
											    		   )
											  );
		if (compNotNull && notEquals) {
			previewComp.setVisible(false);
			panel.removeAll();
			
			previewComp = component;
			
			if (labelAfter) {
				label.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(component);
				panel.add(label);
			} else {
				panel.add(label);
				panel.add(component);
			}
		} else if (previewComp == null) {
			previewComp = component;
			
			if (labelAfter) {
				label.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(component);
				panel.add(label);
			} else {
				panel.add(label);
				panel.add(component);
			}
		}
	}
	
	private void getAllComponentTypes() {
//		try {
//			compTypes = persistence.findAll(ComponentType.class, null);
//		} finally {
//			persistence.close();
//		}
		
		compTypes = new ArrayList<ComponentType>();
		ComponentType type = new ComponentType();
		type.setId(ComponentType.CAIXA_DE_TEXTO);
		type.setTipo("Campo de Texto");
		compTypes.add(type);
	}
	
	private void getAllDataTypes() {
//		try {
//			types = persistence.findAll(DataType.class, null);
//		} finally {
//			persistence.close();
//		} 
		types = new ArrayList<DataType>();
		DataType type = new DataType();
		type.setDescricao("Texto");
		types.add(type);
	}
	
	public void postConstruct() {
		getAllComponentTypes();
		getAllDataTypes();

		tipoComponente.addItem("");
		tipoDado.addItem("");
		
		for (ComponentType type: compTypes) {
			tipoComponente.addItem(type.getTipo());
		}
		for (DataType type: types) {
			tipoDado.addItem(type.getDescricao());
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FieldDlg dialog = new FieldDlg();
					dialog.postConstruct();
					
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public FieldDlg() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 917, 499);
		getContentPane().setLayout(null);
		
		JLabel lblInformeOLabel = new Label("Informe o Label do Campo");
		lblInformeOLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInformeOLabel.setBounds(22, 24, 294, 16);
		getContentPane().add(lblInformeOLabel);
		
		label = new AppTextField();
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(341, 15, 539, 35);
		getContentPane().add(label);
		label.setColumns(10);
		
		Label lblInformeOTipo = new Label("Informe o Label do Campo");
		lblInformeOTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInformeOTipo.setText("Informe o Tipo de Dado do Campo");
		lblInformeOTipo.setBounds(22, 71, 294, 16);
		getContentPane().add(lblInformeOTipo);
		
		Label lblInformeOTipo_2 = new Label("Informe o Label do Campo");
		lblInformeOTipo_2.setText("Informe o Tipo de Componente do Campo");
		lblInformeOTipo_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInformeOTipo_2.setBounds(22, 119, 294, 16);
		getContentPane().add(lblInformeOTipo_2);
		
		tipoDado = new JComboBox();
		tipoDado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tipoDado.setBounds(341, 63, 539, 35);
		getContentPane().add(tipoDado);
		
		tipoComponente = new JComboBox();
		tipoComponente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				preview();
			}
		});
		tipoComponente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tipoComponente.setBounds(341, 111, 539, 35);
		getContentPane().add(tipoComponente);
		
		JLabel lblNewLabel = new Label("Preview do Tipo de Componente");
		lblNewLabel.setText("Preview do Tipo de Componente:");
		lblNewLabel.setBounds(22, 191, 323, 16);
		getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(22, 220, 858, 168);
		getContentPane().add(panel);
		
		JButton confirmar = new Button("Confirmar");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarAction();
			}
		});
		confirmar.setBounds(353, 401, 255, 40);
		getContentPane().add(confirmar);
		
		Button cancelar = new Button("Confirmar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FieldDlg.this.dispose();
			}
		});
		cancelar.setText("Cancelar");
		cancelar.setBounds(625, 401, 255, 40);
		getContentPane().add(cancelar);

	}

}
