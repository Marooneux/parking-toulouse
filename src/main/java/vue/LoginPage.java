package main.java.vue;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.controleur.ControleurLoginPage;

public class LoginPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginField;
	private JTextField passwdField;
	private JButton btnValider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		ControleurLoginPage controleur = new ControleurLoginPage(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 112, 0, 50 };
		gbl_contentPane.rowHeights = new int[] { 32, 32, 32, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);

		JLabel loginLabel = new JLabel("Utilisateur");
		GridBagConstraints gbc_loginLabel = new GridBagConstraints();
		gbc_loginLabel.insets = new Insets(0, 0, 5, 5);
		gbc_loginLabel.gridx = 0;
		gbc_loginLabel.gridy = 0;
		this.contentPane.add(loginLabel, gbc_loginLabel);

		this.loginField = new JTextField();
		GridBagConstraints gbc_loginField = new GridBagConstraints();
		gbc_loginField.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginField.insets = new Insets(0, 0, 5, 0);
		gbc_loginField.gridx = 1;
		gbc_loginField.gridy = 0;
		this.contentPane.add(this.loginField, gbc_loginField);
		this.loginField.setColumns(10);

		JLabel passwdLabel = new JLabel("Password");
		GridBagConstraints gbc_passwdLabel = new GridBagConstraints();
		gbc_passwdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwdLabel.gridx = 0;
		gbc_passwdLabel.gridy = 1;
		this.contentPane.add(passwdLabel, gbc_passwdLabel);

		this.passwdField = new JTextField();
		GridBagConstraints gbc_passwdField = new GridBagConstraints();
		gbc_passwdField.insets = new Insets(0, 0, 5, 0);
		gbc_passwdField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwdField.gridx = 1;
		gbc_passwdField.gridy = 1;
		this.contentPane.add(this.passwdField, gbc_passwdField);
		this.passwdField.setColumns(10);

		this.btnValider = new JButton("Se connecter");
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.anchor = GridBagConstraints.WEST;
		gbc_passwdField.insets = new Insets(0, 0, 5, 0);
		gbc_btnValider.gridx = 1;
		gbc_btnValider.gridy = 2;
		this.contentPane.add(this.btnValider, gbc_btnValider);
		this.btnValider.addActionListener(controleur);
	}

	public String getLogin() {
		return this.loginField.getText();
	}

	public String getMdp() {
		return this.passwdField.getText();
	}

	public void setActifBoutonValider(Boolean b) {
		this.btnValider.setEnabled(b);
	}

	public void viderChampMdp() {
		this.passwdField.setText("");
	}

}
