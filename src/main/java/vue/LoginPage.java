package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginField;
	private JTextField passwdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {112, 0, 50};
		gbl_contentPane.rowHeights = new int[]{32, 32, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel loginLabel = new JLabel("Utilisateur");
		GridBagConstraints gbc_loginLabel = new GridBagConstraints();
		gbc_loginLabel.insets = new Insets(0, 0, 5, 5);
		gbc_loginLabel.gridx = 0;
		gbc_loginLabel.gridy = 0;
		contentPane.add(loginLabel, gbc_loginLabel);
		
		loginField = new JTextField();
		GridBagConstraints gbc_loginField = new GridBagConstraints();
		gbc_loginField.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginField.insets = new Insets(0, 0, 5, 0);
		gbc_loginField.gridx = 1;
		gbc_loginField.gridy = 0;
		contentPane.add(loginField, gbc_loginField);
		loginField.setColumns(10);
		
		JLabel passwdLabel = new JLabel("Password");
		GridBagConstraints gbc_passwdLabel = new GridBagConstraints();
		gbc_passwdLabel.insets = new Insets(0, 0, 0, 5);
		gbc_passwdLabel.gridx = 0;
		gbc_passwdLabel.gridy = 1;
		contentPane.add(passwdLabel, gbc_passwdLabel);
		
		passwdField = new JTextField();
		GridBagConstraints gbc_passwdField = new GridBagConstraints();
		gbc_passwdField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwdField.gridx = 1;
		gbc_passwdField.gridy = 1;
		contentPane.add(passwdField, gbc_passwdField);
		passwdField.setColumns(10);
	}

}
