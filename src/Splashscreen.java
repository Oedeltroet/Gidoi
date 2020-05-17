import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Splashscreen extends JWindow {
	
	public JProgressBar progressBar;
	
	public Splashscreen() {
		
		super();
		
		ImageIcon splash = new ImageIcon(this.getClass().getResource("/gfx/splash.jpg"));
		
		int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - splash.getIconWidth() / 2;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - splash.getIconHeight() / 2;
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		
		this.add(new JLabel(splash));
		this.add(progressBar, BorderLayout.SOUTH);
		this.setBounds(x, y, splash.getIconWidth(), splash.getIconHeight());
		this.pack();
		this.setVisible(true);
	}
}