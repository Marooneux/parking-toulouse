package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controleur.ControleurConfirmationPaiementParking;
import modele.ReservationParking;

public class ConfirmationPaiementParking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					//ConfirmationPaiementParking frame = new ConfirmationPaiementParking(15);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ConfirmationPaiementParking(ReservationParking reservation, double prix) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 750, 550);
		this.setTitle("Paiement validé");

		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 255, 255));
		this.contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setBackground(new Color(255, 255, 255));
		panelCenterContainer.setBorder(new EmptyBorder(40, 100, 40, 100));
		this.contentPane.add(panelCenterContainer, BorderLayout.CENTER);
		panelCenterContainer.setLayout(new BorderLayout(0, 0));

		JPanel panelCard = new JPanel();
		panelCard.setBorder(new LineBorder(new Color(222, 226, 230), 1, true));
		panelCard.setBackground(new Color(255, 255, 255));
		panelCenterContainer.add(panelCard);
		panelCard.setLayout(new BorderLayout(0, 0));

		JPanel panelInnerContent = new JPanel();
		panelInnerContent.setBackground(Color.WHITE);
		panelInnerContent.setBorder(new EmptyBorder(30, 20, 30, 20));
		panelCard.add(panelInnerContent, BorderLayout.CENTER);
		panelInnerContent.setLayout(new GridLayout(5, 1, 0, 10));

		JLabel lblIconSuccess = new JLabel("✔");
		lblIconSuccess.setForeground(new Color(40, 167, 69));
		lblIconSuccess.setFont(new Font("Segoe UI Symbol", Font.BOLD, 50));
		lblIconSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		panelInnerContent.add(lblIconSuccess);

		JLabel lblTitre = new JLabel("Paiement Validé !");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setForeground(new Color(40, 167, 69));
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 26));
		panelInnerContent.add(lblTitre);

		JLabel lblMerci = new JLabel("Merci de votre visite");
		lblMerci.setHorizontalAlignment(SwingConstants.CENTER);
		lblMerci.setForeground(new Color(100, 100, 100));
		lblMerci.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelInnerContent.add(lblMerci);

		JLabel lblMontant = new JLabel("Montant réglé : " + String.format("%.2f €", prix));
		lblMontant.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontant.setForeground(new Color(33, 37, 41));
		lblMontant.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panelInnerContent.add(lblMontant);

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(new Color(255, 255, 255));
		panelFooter.setBorder(new EmptyBorder(10, 0, 20, 0));
		this.contentPane.add(panelFooter, BorderLayout.SOUTH);

		JButton btnTerminer = new JButton("Terminer et Quitter");
		btnTerminer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		ControleurConfirmationPaiementParking controleur = new ControleurConfirmationPaiementParking(this, reservation, prix);
		btnTerminer.addActionListener(controleur);
		btnTerminer.setForeground(Color.WHITE);
		btnTerminer.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTerminer.setBackground(new Color(0, 123, 255));
		btnTerminer.setFocusPainted(false);
		btnTerminer.setBorderPainted(false);
		btnTerminer.setPreferredSize(new Dimension(250, 45));

		panelFooter.add(btnTerminer);
	}
}