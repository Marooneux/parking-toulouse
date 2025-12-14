package controleur;

import vue.ChoixParking;
import vue.SaisirHeureArriveParking;
import modele.Parking;



public class ControleurChoixParking {

    private ChoixParking view;

    public ControleurChoixParking() {
        view = new ChoixParking();

        // Populate parkings with selection logic
        view.populateDefaultParkings(this::onParkingSelected);

        view.setVisible(true);
    }

    private void onParkingSelected(Parking parking) {
    	if (parking == null) {
            return;
        }
        // Example control logic
        SaisirHeureArriveParking saisirHeureView = new SaisirHeureArriveParking(parking);
        saisirHeureView.setVisible(true);

        // Close the current view if needed
        view.dispose();
    }

    public static void main(String[] args) {
        new ControleurChoixParking();
    }
}
 