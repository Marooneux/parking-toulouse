package vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import modele.ZoneVoirie;
import ui.theme.DefaultTheme;
import vue.PaiementVoirie.LimiteCaracteresFilter;

public class SaisirDureeStationnement extends SaisirStationnementBase {

	private static final long serialVersionUID = -1213099304739348672L;
	private TemplateSaisie textFieldNom;
	private ZoneVoirie zone;

	public SaisirDureeStationnement(ZoneVoirie zone) {
		this.zone = zone;
		String bouton = zone.getCouleur().equals("bleue")
				? "Confirmer votre stationnement"
				: "Continuer vers le paiement";
		this.buildUI("Veuillez confirmez votre stationnement en voirie", bouton);
	}

	@Override
	protected String getTitreObjet() {
		return "Zone Sélectionnée";
	}

	@Override
	protected JPanel buildDetailsObjet() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		String minsGratuites = "rouge".equals(this.zone.getCouleur()) ? " (30 minutes gratuites)" : "";
		JLabel lblZone = new JLabel("Zone " + this.zone.getCouleur() + minsGratuites);
		lblZone.setFont(DefaultTheme.FONT_LABEL);
		lblZone.setForeground(new Color(50, 50, 50));
		p.add(lblZone);

		JLabel lblDureeMax = new JLabel("Durée maximum : " + this.zone.minsToHeures());
		lblDureeMax.setFont(DefaultTheme.FONT_LABEL);
		lblDureeMax.setForeground(new Color(50, 50, 50));
		p.add(lblDureeMax);

		return p;
	}

	@Override
	protected String getTitreInput() {
		return "Durée du stationnement";
	}

	@Override
	protected JPanel buildDetailsInput() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(Color.WHITE);
		p.setBorder(new EmptyBorder(15, 15, 15, 15));

		JLabel lbl = new JLabel("Saisissez la durée de stationnement (en minutes)");
		lbl.setFont(DefaultTheme.FONT_LABEL);
		lbl.setForeground(new Color(50, 50, 50));
		p.add(lbl);

		p.add(Box.createRigidArea(new Dimension(0, 8)));

		this.textFieldNom = new TemplateSaisie("Duree", "minutes", false, false);
		((AbstractDocument) this.textFieldNom.getField().getDocument())
				.setDocumentFilter(new LimiteCaracteresFilter(20));
		this.textFieldNom.getField().setPreferredSize(new Dimension(250, 30));
		p.add(this.textFieldNom);

		return p;
	}

	public String getDureeSaisie() {
		return this.textFieldNom.getText();
	}
}
