package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import modele.ReservationParking;
import modele.ReservationVoirie;
import modele.dao.DaoReservationParking;
import modele.dao.DaoReservationVoirie;
import modele.dao.MySQLDataSource;
import vue.HistoriquePanel;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControleurHistorique implements ActionListener {

    private final HistoriquePanel vue;
    private final DaoReservationParking dao;
    private final DaoReservationVoirie daoVoirie;
    private final int userId;

    public ControleurHistorique(HistoriquePanel vue, int userId) {
        this.userId = userId;

        MySQLDataSource.creerAcces();

        this.dao = new DaoReservationParking();
        this.daoVoirie = new DaoReservationVoirie();
        this.vue = vue;

        vue.addReloadListener(this);

        vue.setVisible(true);
        
        vue.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                chargerHistorique();
            }
        });
        chargerHistorique();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chargerHistorique();
    }

    public void chargerHistorique() {
        try {
            List<ReservationParking> reservations = dao.findByUserId(userId);
            List<ReservationVoirie> voirie = daoVoirie.findByUserId(userId);
            vue.afficherHistorique(reservations, voirie);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
}

