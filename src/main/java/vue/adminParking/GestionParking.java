package vue.adminParking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import modele.Parking;


public class GestionParking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnConfirmer;
	private JTextField textField;
	private JTextField textFieldDuree;
	private JTextField textFieldPlaque;
    private JTextField textFieldNom;
	private Parking parking;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				//SaisirDureeStationnement frame = new SaisirDureeStationnement();
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GestionParking(Parking parking) {
		this.parking = parking;
		
	}

}
