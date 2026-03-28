package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modele.Vehicule;
import modele.Vehicule.TypeVehicule;
import ui.theme.DefaultTheme;

public class VehiculesPanel extends JPanel {

	private static final long serialVersionUID = -8022824972795723757L;
	private final TemplateSaisie txtImmatriculation;
	private final JComboBox<TypeVehicule> cbType;
	private final JButton btnAjouter;
	private final JButton btnSupprimer;
	private final JButton btnRafraichir;
	private final JTable table;
	private final DefaultTableModel model;

	public VehiculesPanel(modele.Utilisateur utilisateur) {
		this.txtImmatriculation = new TemplateSaisie("Immatriculation", "AA-000-AA", false, false);
		this.cbType = new JComboBox<>(TypeVehicule.values());
		this.btnAjouter = new JButton("Ajouter");
		this.btnSupprimer = new JButton("Supprimer");
		this.btnRafraichir = new JButton("Rafraîchir");
		this.model = new DefaultTableModel(new String[] { "Immatriculation", "Type" }, 0) {

			private static final long serialVersionUID = 7491196675859485904L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.table = new JTable(this.model);
		this.table.setRowHeight(26);
		this.table.setFillsViewportHeight(true);
		this.table.getTableHeader().setReorderingAllowed(false);

		this.setLayout(new BorderLayout(15, 15));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setBackground(DefaultTheme.BACKGROUND_COLOR);

		this.add(this.buildHeader(), BorderLayout.NORTH);
		this.add(this.buildTablePanel(), BorderLayout.CENTER);
		this.add(this.buildFormPanel(), BorderLayout.SOUTH);
	}

	private JPanel buildHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(DefaultTheme.BACKGROUND_COLOR);

		JLabel title = new JLabel("Mes véhicules");
		title.setFont(DefaultTheme.FONT_HERO_TITLE);
		title.setForeground(DefaultTheme.TEXT_COLOR);

		JLabel subtitle = new JLabel("Ajoutez vos plaques pour les réutiliser rapidement");
		subtitle.setFont(DefaultTheme.FONT_BODY);
		subtitle.setForeground(Color.GRAY);

		JPanel texts = new JPanel();
		texts.setOpaque(false);
		texts.setLayout(new BoxLayout(texts, BoxLayout.Y_AXIS));
		texts.add(title);
		texts.add(Box.createVerticalStrut(5));
		texts.add(subtitle);

		header.add(texts, BorderLayout.WEST);
		return header;
	}

	private JScrollPane buildTablePanel() {
		JScrollPane scroll = new JScrollPane(this.table);
		scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
		scroll.setPreferredSize(new Dimension(500, 240));
		return scroll;
	}

	private JPanel buildFormPanel() {
		JPanel formPanel = new JPanel(new BorderLayout());
		formPanel.setBackground(Color.WHITE);
		formPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(230, 230, 230)),
				new EmptyBorder(12, 12, 12, 12)));

		JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		inputs.setOpaque(false);

		JLabel lblImmat = new JLabel("Immatriculation :");
		this.txtImmatriculation.getField().setPreferredSize(new Dimension(140, 30));

		JLabel lblType = new JLabel("Type :");
		this.cbType.setPreferredSize(new Dimension(150, 30));

		inputs.add(lblImmat);
		inputs.add(this.txtImmatriculation);
		inputs.add(lblType);
		inputs.add(this.cbType);

		JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		actions.setOpaque(false);
		actions.add(this.btnAjouter);
		actions.add(this.btnSupprimer);
		actions.add(this.btnRafraichir);

		formPanel.add(inputs, BorderLayout.WEST);
		formPanel.add(actions, BorderLayout.EAST);
		return formPanel;
	}

	public void afficherVehicules(List<Vehicule> vehicules) {
		this.model.setRowCount(0);
		for (Vehicule v : vehicules) {
			this.model.addRow(new Object[] {
					v.getImmatriculation(),
					v.getType()
			});
		}
	}

	public int getSelectedIndex() {
		return this.table.getSelectedRow();
	}

	public String getImmatriculationInput() {
		return this.txtImmatriculation.getText().trim();
	}

	public TypeVehicule getSelectedType() {
		return (TypeVehicule) this.cbType.getSelectedItem();
	}

	public void clearForm() {
		this.txtImmatriculation.setText("");
		this.cbType.setSelectedIndex(0);
	}

	public void afficherMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public JButton getBtnAjouter() {
		return this.btnAjouter;
	}

	public JButton getBtnSupprimer() {
		return this.btnSupprimer;
	}

	public JButton getBtnRafraichir() {
		return this.btnRafraichir;
	}

	public Vehicule getVehiculeAt(int index, List<Vehicule> source) {
		if (index < 0 || index >= source.size()) {
			return null;
		}
		return source.get(index);
	}
}
