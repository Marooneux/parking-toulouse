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
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(model);
        this.table.setRowHeight(26);
        this.table.setFillsViewportHeight(true);
        this.table.getTableHeader().setReorderingAllowed(false);

        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(DefaultTheme.BACKGROUND_COLOR);

        add(buildHeader(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);
        add(buildFormPanel(), BorderLayout.SOUTH);
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
        JScrollPane scroll = new JScrollPane(table);
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
        txtImmatriculation.getField().setPreferredSize(new Dimension(140, 30));

        JLabel lblType = new JLabel("Type :");
        cbType.setPreferredSize(new Dimension(150, 30));

        inputs.add(lblImmat);
        inputs.add(txtImmatriculation);
        inputs.add(lblType);
        inputs.add(cbType);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        actions.setOpaque(false);
        actions.add(btnAjouter);
        actions.add(btnSupprimer);
        actions.add(btnRafraichir);

        formPanel.add(inputs, BorderLayout.WEST);
        formPanel.add(actions, BorderLayout.EAST);
        return formPanel;
    }

    public void afficherVehicules(List<Vehicule> vehicules) {
        model.setRowCount(0);
        for (Vehicule v : vehicules) {
            model.addRow(new Object[] {
                    v.getImmatriculation(),
                    v.getType()
            });
        }
    }

    public int getSelectedIndex() {
        return table.getSelectedRow();
    }

    public String getImmatriculationInput() {
        return txtImmatriculation.getText().trim();
    }

    public TypeVehicule getSelectedType() {
        return (TypeVehicule) cbType.getSelectedItem();
    }

    public void clearForm() {
        txtImmatriculation.setText("");
        cbType.setSelectedIndex(0);
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JButton getBtnAjouter() {
        return btnAjouter;
    }

    public JButton getBtnSupprimer() {
        return btnSupprimer;
    }

    public JButton getBtnRafraichir() {
        return btnRafraichir;
    }

    public Vehicule getVehiculeAt(int index, List<Vehicule> source) {
        if (index < 0 || index >= source.size()) {
            return null;
        }
        return source.get(index);
    }
}
