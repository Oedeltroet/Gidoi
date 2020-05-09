import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SceneSelectionListener implements ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent arg0) {

		Main.gui.refreshSceneEditor();
	}
}