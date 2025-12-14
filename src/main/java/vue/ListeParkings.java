package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;

public class ListeParkings extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ParkingPanel> placesDeParking;
	private JPanel listeParkings;

	public ListeParkings(List<ParkingPanel> placesDeParking) {
		this.listeParkings = new JPanel();
		this.listeParkings.setBackground(new Color(250, 250, 250));
		this.listeParkings.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		for (ParkingPanel place : placesDeParking) {
			this.listeParkings.add(place);
		}

		this.add(this.listeParkings, BorderLayout.CENTER);
	}

	public void ajouterParking(ParkingPanel placeParking) {
		this.placesDeParking.add(placeParking);
		this.listeParkings.add(this.placesDeParking.get(0));
	}

	public void enleverParking(ParkingPanel placeParking) {
		this.placesDeParking.remove(placeParking);
	}
}