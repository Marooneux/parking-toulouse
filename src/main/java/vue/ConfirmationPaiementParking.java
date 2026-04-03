package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modele.ReservationParking;
import ui.theme.DefaultTheme;

public class ConfirmationPaiementParking extends JPanel {

	private static final long serialVersionUID = -1104275223803452119L;
	private final JButton btnTerminer;

	public ConfirmationPaiementParking(ReservationParking reservation) {
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterContainer = new JPanel();
		panelCenterContainer.setBackground(Color.WHITE);
		panelCenterContainer.setBorder(new EmptyBorder(40, 100, 40, 100));
		this.add(panelCenterContainer, BorderLayout.CENTER);
		panelCenterContainer.setLayout(new BorderLayout(0, 0));

		JPanel panelCard = new JPanel();
		panelCard.setBorder(new LineBorder(DefaultTheme.COLOR_BORDER_BUTTON, 1, true));
		panelCard.setBackground(Color.WHITE);
		panelCenterContainer.add(panelCard);
		panelCard.setLayout(new BorderLayout(0, 0));

		JPanel panelInnerContent = new JPanel();
		panelInnerContent.setBackground(Color.WHITE);
		panelInnerContent.setBorder(new EmptyBorder(30, 20, 30, 20));
		panelCard.add(panelInnerContent, BorderLayout.CENTER);
		panelInnerContent.setLayout(new GridLayout(5, 1, 0, 10));

		JLabel lblIconSuccess = new JLabel("✔");
		lblIconSuccess.setForeground(new Color(40, 167, 69));
		lblIconSuccess.setFont(DefaultTheme.FONT_SYMBOL_XL);
		lblIconSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		panelInnerContent.add(lblIconSuccess);

		JLabel lblTitre = new JLabel("Paiement Validé !");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setForeground(new Color(40, 167, 69));
		lblTitre.setFont(DefaultTheme.FONT_TITLE_XL);
		panelInnerContent.add(lblTitre);

		JLabel lblMerci = new JLabel("Merci de votre visite");
		lblMerci.setHorizontalAlignment(SwingConstants.CENTER);
		lblMerci.setForeground(new Color(100, 100, 100));
		lblMerci.setFont(DefaultTheme.FONT_LABEL);
		panelInnerContent.add(lblMerci);

		JLabel lblMontant = new JLabel("Montant réglé : " + String.format("%.2f €", reservation.getPrixPaye()));
		lblMontant.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontant.setForeground(DefaultTheme.COLOR_TEXT_PRIMARY);
		lblMontant.setFont(DefaultTheme.FONT_CARD_LABEL);
		panelInnerContent.add(lblMontant);

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(Color.WHITE);
		panelFooter.setBorder(new EmptyBorder(10, 0, 20, 0));
		this.add(panelFooter, BorderLayout.SOUTH);

		JButton btnTerminerLocal = new JButton("Terminer et Quitter");
		this.btnTerminer = btnTerminerLocal;
		btnTerminerLocal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTerminerLocal.setForeground(Color.WHITE);
		btnTerminerLocal.setFont(DefaultTheme.FONT_TITLE_XS);
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