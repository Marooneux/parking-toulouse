package controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vue.ChoixParking;
import vue.ParkingPanel;
import vue.SaisirHeureArriveParking;

public class ControleurChoixParking implements ActionListener {

    private final ChoixParking vue;
    private final JButton btnChoisirParking;

    public ControleurChoixParking(ChoixParking vue) {
        this.vue = vue;
        this.btnChoisirParking = vue.getBtnChoisirParking();
        this.btnChoisirParking.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != btnChoisirParking) return;

        handleEtatHeure();
    }

    private void handleEtatHeure() {
        ParkingPanel selectedParking = vue.getParkingSelectionne();

        if (!isParkingSelected(selectedParking)) return;

        ouvrirSaisirHeure(selectedParking);
    }

    private boolean isParkingSelected(ParkingPanel parking) {
        if (parking == null) {
            JOptionPane.showMessageDialog(vue, "Veuillez sélectionner un parking.");
            return false;
        }
        return true;
    }

    private void ouvrirSaisirHeure(ParkingPanel parking) {
        SaisirHeureArriveParking nextVue = new SaisirHeureArriveParking(parking);
        nextVue.getLblParkingInfo().setText(parking.getNomPlace());

        new ControleurSaisirHeureArriveParking(nextVue);
        nextVue.setVisible(true);
        vue.dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ChoixParking vue = new ChoixParking();
            new ControleurChoixParking(vue);
            vue.setVisible(true);
        });
    }
}
