package links.ui.link;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.inject.Singleton;

@Singleton
public class LinkEditComponents {

	private JFrame frame = new JFrame();
	private JPanel buttonPanel = new JPanel();

	private JLabel nameLabel = new JLabel("Name");
	private JLabel urlLabel = new JLabel("URL");
	private JLabel categoryLabel = new JLabel("Category");
	private JLabel userLabel = new JLabel("User");
	private JLabel passwordLabel = new JLabel("Password");

	private JTextField nameField = new JTextField();
	private JTextField urlField = new JTextField();
	private JTextField categoryField = new JTextField();
	private JTextField userField = new JTextField();
	private JTextField passwordField = new JTextField();

	private JButton browserButton = new JButton("Open");
	private JButton saveButton = new JButton("Save");

	public JFrame getJFrame() {
		return frame;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JLabel getUrlLabel() {
		return urlLabel;
	}

	public JLabel getCategoryLabel() {
		return categoryLabel;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getUrlField() {
		return urlField;
	}

	public JTextField getCategoryField() {
		return categoryField;
	}

	public JButton getBrowserButton() {
		return browserButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public JLabel getUserLabel() {
		return userLabel;
	}

	public JTextField getUserField() {
		return userField;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}
}
