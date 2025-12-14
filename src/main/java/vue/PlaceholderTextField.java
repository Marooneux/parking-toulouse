package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class PlaceholderTextField extends JTextField {
	private String placeholder;

	public PlaceholderTextField(String placeholder) {
		this.placeholder = placeholder;
	}

	public PlaceholderTextField(String placeholder, int columns) {
		super(columns);
		this.placeholder = placeholder;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (this.getText().isEmpty() && this.placeholder != null) {
			Graphics2D g2 = (Graphics2D) g.create();

			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setColor(Color.GRAY);
			int padding = (this.getHeight() - g.getFontMetrics().getHeight()) / 2;
			g2.drawString(this.placeholder, this.getInsets().left,
					this.getHeight() - padding - this.getInsets().bottom);

			g2.dispose();
		}
	}
}