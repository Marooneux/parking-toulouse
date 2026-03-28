package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ui.theme.DefaultTheme;

public final class NavigationFrame extends JFrame {

	private static final long serialVersionUID = -5149350946563757318L;

	private static NavigationFrame INSTANCE;

	private final CardLayout cardLayout = new CardLayout();
	private final JPanel cardPanel = new JPanel(this.cardLayout);
	private final Map<String, Component> pages = new HashMap<>();
	private final Deque<String> backStack = new ArrayDeque<>();
	private final Deque<String> forwardStack = new ArrayDeque<>();

	private String currentKey;
	private final JButton btnBack;
	private final JButton btnForward;
	private final JLabel titleLabel;

	private NavigationFrame() {
		super("Smart Parking");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(DefaultTheme.BACKGROUND_COLOR);

		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		header.setBorder(new EmptyBorder(14, 18, 14, 18));
		header.setBackground(Color.WHITE);
		header.setPreferredSize(new Dimension(1200, 64));
		header.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(234, 236, 239), 1),
				new EmptyBorder(12, 14, 12, 14)));

		JPanel navGroup = new JPanel();
		navGroup.setOpaque(false);
		navGroup.setLayout(new BoxLayout(navGroup, BoxLayout.X_AXIS));

		this.btnBack = this.buildNavButton("←");
		this.btnForward = this.buildNavButton("→");

		navGroup.add(this.btnBack);
		navGroup.add(Box.createHorizontalStrut(10));
		navGroup.add(this.btnForward);

		header.add(navGroup, BorderLayout.WEST);

		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(Box.createHorizontalStrut(10));

		this.titleLabel = new JLabel("Smart Parking");
		this.titleLabel.setFont(DefaultTheme.FONT_TITLE_SMALL);
		this.titleLabel.setForeground(DefaultTheme.TEXT_COLOR);
		titlePanel.add(this.titleLabel);
		titlePanel.add(Box.createHorizontalGlue());

		header.add(titlePanel, BorderLayout.CENTER);

		this.getContentPane().add(header, BorderLayout.NORTH);
		this.getContentPane().add(this.cardPanel, BorderLayout.CENTER);

		this.btnBack.addActionListener(e -> this.goBack());
		this.btnForward.addActionListener(e -> this.goForward());
		this.updateNavButtons();
	}

	private JButton buildNavButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(DefaultTheme.FONT_BUTTON);
		btn.setForeground(new Color(52, 58, 64));
		btn.setBackground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(DefaultTheme.BORDER_BUTTON, 1),
				new EmptyBorder(8, 14, 8, 14)));
		btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		return btn;
	}

	public static NavigationFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new NavigationFrame();
		}
		return INSTANCE;
	}

	public void showPage(String key, Supplier<? extends Component> factory, String title) {
		this.showPage(key, factory, title, false);
	}

	public void showPage(String key, Supplier<? extends Component> factory, String title, boolean forceRefresh) {
		SwingUtilities.invokeLater(() -> {
			if (this.currentKey != null && !this.currentKey.equals(key)) {
				this.backStack.push(this.currentKey);
				this.forwardStack.clear();
			}

			if (forceRefresh) {
				Component existing = this.pages.remove(key);
				if (existing != null) {
					this.cardPanel.remove(existing);
				}
			}

			Component page = this.pages.computeIfAbsent(key, k -> factory.get());
			if (page.getParent() == null) {
				this.cardPanel.add(page, key);
			}

			this.currentKey = key;
			this.cardLayout.show(this.cardPanel, key);
			this.setTitle(title);
			this.titleLabel.setText(title);
			this.cardPanel.revalidate();
			this.cardPanel.repaint();
			this.updateNavButtons();
		});
	}

	private void goBack() {
		if (this.backStack.isEmpty()) {
			return;
		}
		String target = this.backStack.pop();
		if (this.currentKey != null) {
			this.forwardStack.push(this.currentKey);
		}
		this.currentKey = target;
		this.cardLayout.show(this.cardPanel, target);
		this.titleLabel.setText(target);
		this.updateNavButtons();
	}

	private void goForward() {
		if (this.forwardStack.isEmpty()) {
			return;
		}
		String target = this.forwardStack.pop();
		if (this.currentKey != null) {
			this.backStack.push(this.currentKey);
		}
		this.currentKey = target;
		this.cardLayout.show(this.cardPanel, target);
		this.titleLabel.setText(target);
		this.updateNavButtons();
	}

	private void updateNavButtons() {
		this.btnBack.setEnabled(!this.backStack.isEmpty());
		this.btnForward.setEnabled(!this.forwardStack.isEmpty());
	}
}