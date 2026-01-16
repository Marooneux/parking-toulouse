package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.ReservationParking;

public class ConfirmationPaiementParking extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JButton btnTerminer;

	public ConfirmationPaiementParking(ReservationParking reservation, double prix) {
		this.setBackground(new Color(255, 255, 255));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setBackground(new Color(255, 255, 255));
		panelCenterContainer.setBorder(new EmptyBorder(40, 100, 40, 100));
		this.add(panelCenterContainer, BorderLayout.CENTER);
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
		this.add(panelFooter, BorderLayout.SOUTH);

		JButton btnTerminerLocal = new JButton("Terminer et Quitter");
		this.btnTerminer = btnTerminerLocal;
		btnTerminerLocal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTerminerLocal.setForeground(Color.WHITE);
		btnTerminerLocal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTerminerLocal.setBackground(new Color(0, 123, 255));
		btnTerminerLocal.setFocusPainted(false);
		btnTerminerLocal.setBorderPainted(false);
		btnTerminerLocal.setPreferredSize(new Dimension(250, 45));

		panelFooter.add(btnTerminerLocal);
	}

	public JButton getBtnTerminer() {
		return this.btnTerminer;
	}
}