package VUE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Paiement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldNumCarte;
	private JTextField textFieldExpiration;
	private JTextField textFieldCVC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Paiement frame = new Paiement();
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
	public Paiement() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblTitre = new JLabel("Paiement");
		lblTitre.setFont(new Font("Dialog", Font.BOLD, 20));
		this.contentPane.add(lblTitre, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(216, 221, 226));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 1, 0, 0));

		JPanel panel_nom = new JPanel();
		panel_nom.setBackground(new Color(216, 221, 226));
		panel.add(panel_nom);
		panel_nom.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNom = new JLabel("Nom");
		panel_nom.add(lblNom);

		JPanel panel_textFieldNom = new JPanel();
		panel_textFieldNom.setBackground(new Color(216, 221, 226));
		panel_nom.add(panel_textFieldNom);
		panel_textFieldNom.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		this.textFieldNom = new JTextField();
		this.textFieldNom.setForeground(new Color(179, 179, 179));
		this.textFieldNom.setText("Nom Prénom");
		panel_textFieldNom.add(this.textFieldNom);
		this.textFieldNom.setColumns(30);

		JPanel panel_numCarte = new JPanel();
		panel_numCarte.setBackground(new Color(216, 221, 226));
		panel.add(panel_numCarte);
		panel_numCarte.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNumCarte = new JLabel("Numéro de carte");
		panel_numCarte.add(lblNumCarte);

		JPanel panel_textFieldNumCarte = new JPanel();
		panel_textFieldNumCarte.setBackground(new Color(216, 221, 226));
		panel_numCarte.add(panel_textFieldNumCarte);
		panel_textFieldNumCarte.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		this.textFieldNumCarte = new JTextField();
		this.textFieldNumCarte.setForeground(new Color(179, 179, 179));
		this.textFieldNumCarte.setText("1234 5678 9012 3456");
		panel_textFieldNumCarte.add(this.textFieldNumCarte);
		this.textFieldNumCarte.setColumns(30);

		JPanel panel_dateExp_CVC = new JPanel();
		panel_dateExp_CVC.setBackground(new Color(216, 221, 226));
		panel.add(panel_dateExp_CVC);
		panel_dateExp_CVC.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_dateExp = new JPanel();
		panel_dateExp.setBackground(new Color(216, 221, 226));
		panel_dateExp_CVC.add(panel_dateExp);
		panel_dateExp.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblExpiration = new JLabel("Date d'expiration");
		panel_dateExp.add(lblExpiration);

		JPanel panel_textFieldExpiration = new JPanel();
		panel_textFieldExpiration.setBackground(new Color(216, 221, 226));
		FlowLayout fl_panel_textFieldExpiration = (FlowLayout) panel_textFieldExpiration.getLayout();
		fl_panel_textFieldExpiration.setAlignment(FlowLayout.LEFT);
		panel_dateExp.add(panel_textFieldExpiration);

		this.textFieldExpiration = new JTextField();
		this.textFieldExpiration.setForeground(new Color(179, 179, 179));
		this.textFieldExpiration.setText("MM/YY");
		panel_textFieldExpiration.add(this.textFieldExpiration);
		this.textFieldExpiration.setColumns(15);

		JPanel panel_dateExpiration = new JPanel();
		panel_dateExpiration.setBackground(new Color(216, 221, 226));
		panel_dateExp_CVC.add(panel_dateExpiration);
		panel_dateExpiration.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblCVC = new JLabel("CVC");
		panel_dateExpiration.add(lblCVC);

		JPanel panel_textFieldCVC = new JPanel();
		panel_textFieldCVC.setBackground(new Color(216, 221, 226));
		FlowLayout flowLayout = (FlowLayout) panel_textFieldCVC.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_dateExpiration.add(panel_textFieldCVC);

		this.textFieldCVC = new JTextField();
		this.textFieldCVC.setForeground(new Color(179, 179, 179));
		this.textFieldCVC.setText("123");
		panel_textFieldCVC.add(this.textFieldCVC);
		this.textFieldCVC.setColumns(15);

		JPanel panel_payer = new JPanel();
		panel_payer.setBackground(new Color(216, 221, 226));
		panel.add(panel_payer);
		panel_payer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnPayer = new JButton("Payer");
		btnPayer.setMargin(new Insets(2, 50, 2, 50));
		btnPayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_payer.add(btnPayer);
		btnPayer.setForeground(new Color(255, 255, 255));
		btnPayer.setBackground(new Color(0, 128, 255));

	}

}
