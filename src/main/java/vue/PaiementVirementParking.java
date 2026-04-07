package vue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;

import modele.ReservationParking;

public class PaiementVirementParking extends PaiementVirement {

	private static final long serialVersionUID = 3249422619928286426L;
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private final transient ReservationParking reservation;

	public PaiementVirementParking(ReservationParking reservation, double prix) {
		super(creerRecap(reservation, prix), prix);
		this.reservation = reservation;
	}

	private static JPanel creerRecap(ReservationParking reservation, double prix) {
		JPanel recap = creerPanelRecapBase();
		recap.add(creerInfoRow("Parking",
				reservation.getParking() != null ? reservation.getParking().getNom() : "Parking"));
		recap.add(creerInfoRow("Arrivee", formatDate(reservation.getDateArrivee())));
		recap.add(creerInfoRow("Depart", formatDate(reservation.getDateDepart())));
		recap.add(creerInfoRow("Montant", String.format("%.2f €", prix)));
		return recap;
	}

	private static String formatDate(LocalDateTime dateTime) {
		return dateTime != null ? DATE_FORMAT.format(dateTime) : "-";
	}

	public ReservationParking getReservation() {
		return this.reservation;
	}
}
