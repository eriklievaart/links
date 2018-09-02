package links.ui.main;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import links.model.Link;

import com.google.inject.Singleton;

@Singleton
public class MainComponents {

	private JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
	private JPanel listPanel = new JPanel(new GridLayout(1, 0));
	private JFrame frame = new JFrame();
	private JList<Link> linkList = new JList<>();
	private JList<String> categoryList = new JList<>();
	private JButton addButton = new JButton("Add");
	private JButton editButton = new JButton("Edit");
	private JButton removeButton = new JButton("Remove");
	private JButton passwordButton = new JButton("Password");

	public JList<Link> getLinkList() {
		return linkList;
	}

	public JList<String> getCategoryList() {
		return categoryList;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public JButton getPasswordButton() {
		return passwordButton;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getListPanel() {
		return listPanel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}
}
