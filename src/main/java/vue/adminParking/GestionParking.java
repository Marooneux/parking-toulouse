package vue.adminParking;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Parking;

public class GestionParking extends JPanel {
	private static final long serialVersionUID = 1L;

	public GestionParking(Parking parking) {
		setLayout(new BorderLayout());
		String name = parking != null ? parking.getNom() : "";
		add(new JLabel("Gestion du parking " + name), BorderLayout.CENTER);
	}
}
