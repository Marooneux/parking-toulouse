package VUE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ChoixTypeStationnement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ChoixTypeStationnement frame = new ChoixTypeStationnement();
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
	public ChoixTypeStationnement() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 255, 255));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblTitre = new JLabel("Bienvenue");
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.contentPane.add(lblTitre, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		this.contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 10, 0));

		JPanel panel_parking = new JPanel();
		panel.add(panel_parking);
		panel_parking.setLayout(new GridLayout(0, 1, 5, 0));

		JPanel panel_lblChoisirParking = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_lblChoisirParking.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_parking.add(panel_lblChoisirParking);

		JLabel lblChoisirParking = new JLabel("Stationnement parking");
		lblChoisirParking.setHorizontalAlignment(SwingConstants.LEFT);
		panel_lblChoisirParking.add(lblChoisirParking);
		lblChoisirParking.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JPanel panel_lblInfoParking = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_lblInfoParking.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_parking.add(panel_lblInfoParking);

		JLabel lblInfoParking = new JLabel("Stationner dans un parking au choix");
		panel_lblInfoParking.add(lblInfoParking);
		lblInfoParking.setFont(new Font("Dialog", Font.PLAIN, 12));

		JPanel panel_btnChoisirParking = new JPanel();
		panel_parking.add(panel_btnChoisirParking);
		panel_btnChoisirParking.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

		JButton btnChoisirParking = new JButton("Trouver un parking");
		btnChoisirParking.setHorizontalAlignment(SwingConstants.LEFT);
		panel_btnChoisirParking.add(btnChoisirParking);
		btnChoisirParking.setForeground(new Color(255, 255, 255));
		btnChoisirParking.setBackground(new Color(0, 128, 255));
		btnChoisirParking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChoisirParking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JPanel panel_voirie = new JPanel();
		panel.add(panel_voirie);
		panel_voirie.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel panel_lblChoisirVoirie = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_lblChoisirVoirie.getLayout();
		flowLayout_3.setVgap(10);
		flowLayout_3.setHgap(10);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_voirie.add(panel_lblChoisirVoirie);

		JLabel lblChoisirVoirie = new JLabel("Stationnement en voirie");
		panel_lblChoisirVoirie.add(lblChoisirVoirie);
		lblChoisirVoirie.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JPanel panel_lblInfoVoirie = new JPanel();
		FlowLayout fl_panel_lblInfoVoirie = (FlowLayout) panel_lblInfoVoirie.getLayout();
		fl_panel_lblInfoVoirie.setHgap(10);
		fl_panel_lblInfoVoirie.setAlignment(FlowLayout.LEFT);
		fl_panel_lblInfoVoirie.setVgap(10);
		panel_voirie.add(panel_lblInfoVoirie);

		JLabel lblInfoVoirie = new JLabel("Stationner en voirie dans une zone au choix");
		panel_lblInfoVoirie.add(lblInfoVoirie);
		lblInfoVoirie.setFont(new Font("Dialog", Font.PLAIN, 12));

		JPanel panel_btnChoisirVoirie = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_btnChoisirVoirie.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setHgap(10);
		panel_voirie.add(panel_btnChoisirVoirie);

		JButton btnChoisirVoirie = new JButton("Trouver un emplacement");
		btnChoisirVoirie.setHorizontalAlignment(SwingConstants.LEFT);
		panel_btnChoisirVoirie.add(btnChoisirVoirie);
		btnChoisirVoirie.setForeground(new Color(255, 255, 255));
		btnChoisirVoirie.setBackground(new Color(0, 128, 255));
		btnChoisirVoirie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChoisirVoirie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

	}

}
