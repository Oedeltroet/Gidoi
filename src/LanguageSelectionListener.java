import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class LanguageSelectionListener implements ItemListener {
	
	private boolean enabled = true;
	
	public void setEnabled(boolean enabled) {
		
		this.enabled = enabled;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {

		if (enabled) {
		
			Settings.language = (Locale) arg0.getItem();
			Settings.localisation = ResourceBundle.getBundle(Settings.baseBundleName, Settings.language);
			Locale.setDefault(Settings.language);
			JOptionPane.setDefaultLocale(Settings.language);
			
			Main.saveSettings();
			
			Main.gui.refreshToolbar();
			Main.gui.status.setText("Language changed to " + Settings.language.getDisplayLanguage());
			Main.gui.dialogSettings.dispose();
		}
	}
}