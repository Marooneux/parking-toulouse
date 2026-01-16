package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import modele.ReservationParking;
import modele.dao.DaoReservationParking;
import modele.dao.MySQLDataSource;
import vue.HistoriquePanel;

public class ControleurHistorique implements ActionListener {

    private final HistoriquePanel vue;
    private final DaoReservationParking dao;
    private final int userId;

    public ControleurHistorique(HistoriquePanel vue, int userId) {
        this.userId = userId;

        MySQLDataSource.creerAcces();

        this.dao = new DaoReservationParking();
        this.vue = vue;

        vue.addReloadListener(this);
        chargerHistorique();
        vue.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chargerHistorique();
    }

    public void chargerHistorique() {
        try {
            List<ReservationParking> reservations = dao.findByUserId(userId);
            vue.afficherHistorique(reservations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

