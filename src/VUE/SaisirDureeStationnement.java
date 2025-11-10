package VUE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;

public class SaisirDureeStationnement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaisirDureeStationnement frame = new SaisirDureeStationnement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaisirDureeStationnement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		FlowLayout flowLayout = (FlowLayout) header.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel lblIcon = new JLabel("Icon");
		header.add(lblIcon);
		
		JPanel texte = new JPanel();
		header.add(texte);
		texte.setLayout(new BoxLayout(texte, BoxLayout.Y_AXIS));
		
		JLabel lblTitre = new JLabel("Démarrer le Stationnement");
		texte.add(lblTitre);
		
		JLabel lblSousTitre = new JLabel("Enregistrez votre arrivée au parking");
		texte.add(lblSousTitre);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		JPanel detailsParking = new JPanel();
		body.add(detailsParking);
		detailsParking.setLayout(new BoxLayout(detailsParking, BoxLayout.Y_AXIS));
		
		JLabel lblParking = new JLabel("Parking Sélectionné");
		detailsParking.add(lblParking);
		
		JLabel lblNomParking = new JLabel("Parking Capitole");
		detailsParking.add(lblNomParking);
		
		JPanel detailsVoiture = new JPanel();
		body.add(detailsVoiture);
		detailsVoiture.setLayout(new BoxLayout(detailsVoiture, BoxLayout.Y_AXIS));
		
		JLabel lblInfoVehicule = new JLabel("Informations du Véhicule");
		detailsVoiture.add(lblInfoVehicule);
		
		JPanel detailsVehicule = new JPanel();
		detailsVoiture.add(detailsVehicule);
		detailsVehicule.setLayout(new BoxLayout(detailsVehicule, BoxLayout.Y_AXIS));
		
		JLabel lblNbImatricule = new JLabel("Numéro de Plaque d'Immatriculation");
		detailsVehicule.add(lblNbImatricule);
		
		JLabel NbImatriculation = new JLabel("AB-123-CD");
		detailsVehicule.add(NbImatriculation);
		
		JLabel lblInfoImatricule = new JLabel("Nécessaire pour l'entrée et la sortie automatisées");
		detailsVehicule.add(lblInfoImatricule);
		
		JPanel detailsHeureArrivé = new JPanel();
		body.add(detailsHeureArrivé);
		detailsHeureArrivé.setLayout(new BoxLayout(detailsHeureArrivé, BoxLayout.Y_AXIS));
		
		JLabel lblTitreHeureArrive = new JLabel("Heure d'Arrivée");
		detailsHeureArrivé.add(lblTitreHeureArrive);
		
		JPanel panel = new JPanel();
		detailsHeureArrivé.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblHeureArrive = new JLabel("Sélectionnez votre heure d'arrivé");
		panel.add(lblHeureArrive);
		
		JPanel heureArrive = new JPanel();
		heureArrive.setMaximumSize(new Dimension(32767, 32));
		panel.add(heureArrive);
		heureArrive.setLayout(new BoxLayout(heureArrive, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		heureArrive.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Maintenant");
		heureArrive.add(btnNewButton);
		
		JLabel lblInfoHeureArrive = new JLabel("Vous pourrez quitter l'application et revenir plus tard pour enregistrer votre départ");
		panel.add(lblInfoHeureArrive);
		
		JPanel informationProcedure = new JPanel();
		body.add(informationProcedure);
		
		JPanel aside = new JPanel();
		contentPane.add(aside, BorderLayout.EAST);
		
		JPanel button = new JPanel();
		contentPane.add(button, BorderLayout.SOUTH);

	}

}
