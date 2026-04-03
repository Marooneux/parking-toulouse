package vue.adminParking;

import modele.Parking;

public class ModifierParking extends AbstractParkingForm {

    private final Parking parking;

    public ModifierParking(Parking parking) {
        super("Modification du parking");
        this.parking = parking;
        remplirChamps();
    }

    private void remplirChamps() {
        groupNom.setText(parking.getNom());
        groupNumero.setText(String.valueOf(parking.getAdresse().getNumero()));
        groupRue.setText(parking.getAdresse().getRue());
        groupCP.setText(String.valueOf(parking.getAdresse().getCodePostal()));
        groupVille.setText(parking.getAdresse().getVille());
        groupTarif.setText(String.valueOf(parking.getTarif()));
        groupHauteur.setText(String.valueOf(parking.getHauteur()));
        groupPlacesMax.setText(String.valueOf(parking.getNbPlacesMax()));
        groupOuverture.setText(parking.getHoraireOuverture().toString());
        groupFermeture.setText(parking.getHoraireFermeture().toString());
        chkMoto.setSelected(parking.isContientPlacesMoto());
    }
}
