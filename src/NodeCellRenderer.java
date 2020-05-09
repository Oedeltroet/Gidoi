import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class NodeCellRenderer extends DefaultListCellRenderer {
	
	public NodeCellRenderer() {}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		Node node = (Node) value;
		
		if (node.getNodeType() == Node.ELEMENT_NODE) {
		
			Element element = (Element) node;
			
			if (element.getNodeName().equals("scene")) {
				
				String text = (index + 1) + ". ";
				
				if (element.hasAttribute("place")) {
					
					text += element.getAttribute("place") + " ";
				}
				
				if (element.hasAttribute("location")) {
					
					text += element.getAttribute("location");
				}
				
				if (element.hasAttribute("mod-location")) {
					
					text += " (" + element.getAttribute("mod-location") + ")";
				}
				
				if (element.hasAttribute("time")) {
					
					text += " - " + element.getAttribute("time");
				}
				
				setText(text.toUpperCase());
			}
			
			else if (element.getNodeName().equals("action")) {
				
				setText("[ACTION] " + element.getTextContent());
			}
			
			else if (element.getNodeName().equals("dialogue")) {
				
				String text = "[DIALOGUE] ";
				
				text += element.getAttribute("character");
				
				if (element.hasAttribute("wrylies"))  {
					
					if (element.getAttribute("wrylies") != "") {
						
						text += " (" + element.getAttribute("wrylies") + ")";
					}
				}
				
				text += ": '" + element.getTextContent() + "'";
				
				setText(text);
			}
			
			else {
				
				setText(element.getTextContent());
			}
		}
		
		return this;
	}
}