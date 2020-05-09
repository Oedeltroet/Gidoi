import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ScreenplaySelectionListener implements ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent arg0) {

		Main.gui.refreshScreenplayEditor();
	}
}