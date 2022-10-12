package com.javalec.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class AdminDashboardPanel extends JPanel {
	private JLabel lblAdminDashboardImage;

	/**
	 * Create the panel.
	 */
	public AdminDashboardPanel() {
		setLayout(null);
		add(getLblAdminDashboardImage());

	}

	private JLabel getLblAdminDashboardImage() {
		if (lblAdminDashboardImage == null) {
			lblAdminDashboardImage = new JLabel("New label");
			lblAdminDashboardImage.setIcon(new ImageIcon(AdminDashboardPanel.class.getResource("/com/javalec/image/AdmDashboard.png")));
			lblAdminDashboardImage.setBounds(0, 0, 1059, 617);
		}
		return lblAdminDashboardImage;
	}
}
