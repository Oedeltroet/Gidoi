import java.awt.Component;
import java.util.Locale;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class LocaleCellRenderer extends DefaultListCellRenderer {
	
	public LocaleCellRenderer() {}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		Locale locale = (Locale) value;
		setText(locale.getDisplayLanguage(locale));
		
		return this;
	}
}