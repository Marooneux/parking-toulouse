package VUE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ListePlacesParking extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PlaceParking> placesDeParking;
	private JPanel listeParkings;

	public ListePlacesParking(List<PlaceParking> placesDeParking) {		
        this.listeParkings = new JPanel();
        this.listeParkings.setBackground(new Color(250, 250, 250));
        listeParkings.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        
        for(PlaceParking place : placesDeParking) {
        	listeParkings.add(place);
        }
        
        this.add(listeParkings, BorderLayout.CENTER);
	}
	
	public void ajouterParking(PlaceParking placeParking) {
		this.placesDeParking.add(placeParking);
		this.listeParkings.add(placesDeParking.get(0));
	}
	
	public void enleverParking(PlaceParking placeParking) {
		this.placesDeParking.remove(placeParking);
	}
}