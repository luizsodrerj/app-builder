package appbuilder.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import framework.persistence.jpa.PersistenceServiceUtil;
import framework.presentation.swing.Window;
import framework.swing.BaseFrame;
import framework.swing.Button;
import java.awt.Color;
import java.awt.Font;

public class MainFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	
	
	private BackGroundComponent background = null;
	private JPanel contentPane;
	private JPanel panel = null;

	

	public void setBackGroundImage() {
		background = new BackGroundComponent(panel);
		background.setBounds(250, 50, 750, 550); 
		panel.setLayout(null);
		panel.add(background);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PersistenceServiceUtil persis = new PersistenceServiceUtil();
				
				try {
					MainFrame frame = new MainFrame();
					Window.setNimbusLookAndFeel();
					Window.centralizeWindow(frame);
					
					frame.setResizable(false);
					frame.setBackGroundImage();
					frame.setVisible(true);
					
					//persis.connect();
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					persis.close();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Construtor de Aplicativos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 739);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCriarUmaAplicao = new Button("Criar uma Aplica\u00E7\u00E3o");
		btnCriarUmaAplicao.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCriarUmaAplicao.setBackground(Color.DARK_GRAY);
		btnCriarUmaAplicao.setForeground(Color.WHITE);
		btnCriarUmaAplicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarApp();
			}
		});
		btnCriarUmaAplicao.setBounds(12, 28, 1003, 50);
		contentPane.add(btnCriarUmaAplicao);
		
		JButton btnImportarPlanilha = new Button("Importar Planilha");
		btnImportarPlanilha.setForeground(Color.WHITE);
		btnImportarPlanilha.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnImportarPlanilha.setBackground(Color.DARK_GRAY);
		btnImportarPlanilha.setText("Configurar uma Aplica\u00E7\u00E3o");
		btnImportarPlanilha.setBounds(12, 108, 1003, 49);
		contentPane.add(btnImportarPlanilha);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 198, 1003, 469);
		contentPane.add(panel);
	}
	
	void criarApp() {
		AppDlg app = new AppDlg();
		app.setModal(true);
		app.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Window.centralizeWindow(app);
		app.setVisible(true);
	}
}
