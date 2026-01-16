package controleur;

import java.sql.SQLException;
import java.util.List;

import modele.ReservationParking;
import modele.dao.DaoReservationParking;
import modele.dao.MySQLDataSource;
import vue.HistoriquePanel;

public class ControleurHistorique {

    private HistoriquePanel vue;
    private DaoReservationParking dao;
    private int userId;

    public ControleurHistorique(HistoriquePanel vue, int userId) {
    	this.userId = userId;
    
        MySQLDataSource.creerAcces();

        this.dao = new DaoReservationParking();
        this.vue = vue;

        chargerHistorique();
        vue.setVisible(true);
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

