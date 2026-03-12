package appbuilder.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class BackGroundComponent extends JComponent {

	private static final long serialVersionUID = 1L;

	private JPanel panel;

	public BackGroundComponent(JPanel panel) {
		super();
		this.panel = panel;
	}

	public void paintComponent(Graphics g) {
		try {
			Graphics2D g2 = (Graphics2D) g;
			InputStream imgStream = getClass().getResourceAsStream("/apps.jpeg");
			BufferedImage image = ImageIO.read(imgStream);
			g2.drawImage(image, 0, 0, 500, 300, panel);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}








