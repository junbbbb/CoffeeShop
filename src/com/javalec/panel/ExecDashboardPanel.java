package com.javalec.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ExecDashboardPanel extends JPanel {
	private JLabel lblAdminDashboardImage;

	/**
	 * Create the panel.
	 */
	public ExecDashboardPanel() {
		setLayout(null);
		add(getLblAdminDashboardImage());

	}

	private JLabel getLblAdminDashboardImage() {
		if (lblAdminDashboardImage == null) {
			lblAdminDashboardImage = new JLabel("New label");
			lblAdminDashboardImage.setIcon(new ImageIcon(ExecDashboardPanel.class.getResource("/com/javalec/image/ExecDashboardPanel.png")));
			lblAdminDashboardImage.setBounds(0, 0, 1059, 617);
		}
		return lblAdminDashboardImage;
	}
}
