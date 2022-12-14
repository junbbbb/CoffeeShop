package com.javalec.base;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.javalec.panel.ExecDashboardPanel;
import com.javalec.panel.ExecEmployeeManagePanel;
import com.javalec.panel.ExecMenuManagePanel;
import com.javalec.panel.ExecSalesPanel;
import com.javalec.panel.ExecStoreManagePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExecMainFrame {

	private JFrame frame;
	private JTextField tfCafeName;
	private JTextField tfShow;
	private JLabel lblSearchBar;
	private JTextField tfSearch;
	private JTextField tfTime;

	// panel declaration
	ExecDashboardPanel execDashboardPanel = new ExecDashboardPanel();
	ExecStoreManagePanel execStoreManagePanel = new ExecStoreManagePanel();
	ExecMenuManagePanel execMenuManagePanel = new ExecMenuManagePanel();
	ExecEmployeeManagePanel execEmployeeManagePanel = new ExecEmployeeManagePanel();
	ExecSalesPanel execSalesPanel = new ExecSalesPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExecMainFrame window = new ExecMainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExecMainFrame() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getTfCafeName());
		frame.getContentPane().add(getTfShow());
		frame.getContentPane().add(getTfTime());
		frame.getContentPane().add(getLblSearchBar());
		frame.getContentPane().add(getTfSearch());
		frame.getContentPane().add(getPanelAdminMain());
		frame.getContentPane().add(getTfStoreManage());
		frame.getContentPane().add(getTfEmployeeManage());
		frame.getContentPane().add(getTfSalesManage());
		frame.getContentPane().add(getTfAdManage());
		frame.getContentPane().add(getTfMemberManage());
		frame.getContentPane().add(getTfInventoryManage());
		frame.getContentPane().add(getTfMenuManage());
		frame.getContentPane().add(getTfNoticeManage());

		frame.getContentPane().add(execDashboardPanel);
		frame.getContentPane().add(getLblBackground());
		frame.getContentPane().add(getTfDashboard());
		tfDashboard.setBackground(new Color(226, 161, 101));
		tfDashboard.setForeground(new Color(255, 255, 255));
		panelAdminMain.setVisible(false);
		execDashboardPanel.setVisible(true);
		execDashboardPanel.setBounds(167, 55, 1059, 617);
		execDashboardPanel.setLayout(null);
		frame.getContentPane().add(getLblAlarmIcons());
		frame.getContentPane().add(getLblProfilePicture());
		frame.getContentPane().add(getTfExecName());

	}

	private JTextField getTfCafeName() {
		if (tfCafeName == null) {
			tfCafeName = new JTextField();
			tfCafeName.setEditable(false);
			tfCafeName.setBorder(null);
			tfCafeName.setHorizontalAlignment(SwingConstants.CENTER);
			tfCafeName.setFont(new Font("??????", Font.BOLD, 32));
			tfCafeName.setText("CafeN");
			tfCafeName.setBounds(0, 0, 130, 46);
			tfCafeName.setColumns(10);
		}
		return tfCafeName;
	}

	private JTextField getTfShow() {
		if (tfShow == null) {
			tfShow = new JTextField();
			tfShow.setBorder(null);

			tfShow.setEditable(false);
			tfShow.setFont(new Font("??????", Font.PLAIN, 22));
			tfShow.setHorizontalAlignment(SwingConstants.CENTER);
			tfShow.setText("????????????");
			tfShow.setBounds(138, 8, 95, 36);
			tfShow.setColumns(10);
		}
		return tfShow;
	}

	private JLabel getLblSearchBar() {
		if (lblSearchBar == null) {
			lblSearchBar = new JLabel("New label");
			lblSearchBar
					.setIcon(new ImageIcon(ExecMainFrame.class.getResource("/com/javalec/image/SearchBarIcon.png")));
			lblSearchBar.setBounds(238, 11, 353, 30);
		}
		return lblSearchBar;
	}

	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(241, 11, 330, 30);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}

	private JTextField getTfTime() {
		if (tfTime == null) {
			tfTime = new JTextField();
			tfTime.setFont(new Font("??????", Font.PLAIN, 22));
			tfTime.setEditable(false);
			tfTime.setBounds(750, 11, 226, 30);
			tfTime.setColumns(10);
			tfTime.setText(dtf.format(now));
			tfTime.setBorder(null);
		}
		return tfTime;
	}

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	private JPanel panelAdminMain;

	private JPanel getPanelAdminMain() {
		if (panelAdminMain == null) {
			panelAdminMain = new JPanel();
			panelAdminMain.setBounds(167, 74, 1059, 617);
		}
		return panelAdminMain;
	}

	private JTextField tfDashboard;
	private JTextField tfSalesManage;
	private JTextField tfEmployeeManage;
	private JTextField tfInventoryManage;
	private JTextField tfMemberManage;
	private JTextField tfNoticeManage;
	private JTextField tfMenuManage;
	private JTextField tfAdManage;
	private JTextField tfStoreManage;
	private JPanel pnAdminMain;
	private JLabel lblBackground;
	private JLabel lblSelectionBox;
	private JLabel lblAlarmIcons;
	private JLabel lblProfilePicture;
	private JTextField tfExecName;

	private JTextField getTfDashboard() {
		if (tfDashboard == null) {
			tfDashboard = new JTextField();
			tfDashboard.setBorder(null);
			tfDashboard.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					panelAdminMain.setVisible(false);
					execStoreManagePanel.setVisible(false);
					execMenuManagePanel.setVisible(false);
					execEmployeeManagePanel.setVisible(false);
					execSalesPanel.setVisible(false);

					execDashboardPanel.setVisible(true);
					execDashboardPanel.setBounds(167, 55, 1059, 617);
					execDashboardPanel.setLayout(null);
					frame.getContentPane().add(execDashboardPanel);
					tfSearch.setVisible(true);
					lblSearchBar.setVisible(true);
					tfDashboard.setBackground(new Color(226, 161, 101));
					tfDashboard.setForeground(new Color(255, 255, 255));
					tfStoreManage.setBackground(new Color(240, 240, 240));
					tfStoreManage.setForeground(new Color(0, 0, 0));
					tfMenuManage.setBackground(new Color(240, 240, 240));
					tfMenuManage.setForeground(new Color(0, 0, 0));
					tfEmployeeManage.setBackground(new Color(240, 240, 240));
					tfEmployeeManage.setForeground(new Color(0, 0, 0));
					tfSalesManage.setBackground(new Color(240, 240, 240));
					tfSalesManage.setForeground(new Color(0, 0, 0));

					tfShow.setText("????????????");

				}
			});

			tfDashboard.setEditable(false);
			tfDashboard.setText("????????????");
			tfDashboard.setFont(new Font("??????", Font.PLAIN, 22));
			tfDashboard.setHorizontalAlignment(SwingConstants.CENTER);
			tfDashboard.setBounds(0, 70, 130, 36);
			tfDashboard.setColumns(10);
		}
		return tfDashboard;
	}

	private JTextField getTfStoreManage() {
		if (tfStoreManage == null) {
			tfStoreManage = new JTextField();
			tfStoreManage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					panelAdminMain.setVisible(false);
					execDashboardPanel.setVisible(false);
					execMenuManagePanel.setVisible(false);
					execEmployeeManagePanel.setVisible(false);
					execSalesPanel.setVisible(false);

					execStoreManagePanel.setVisible(true);
					execStoreManagePanel.setBounds(167, 60, 1059, 617);
					execStoreManagePanel.setLayout(null);
					frame.getContentPane().add(execStoreManagePanel);
					tfSearch.setVisible(false);
					lblSearchBar.setVisible(false);
					tfStoreManage.setBackground(new Color(226, 161, 101));
					tfStoreManage.setForeground(new Color(255, 255, 255));
					tfDashboard.setBackground(new Color(240, 240, 240));
					tfDashboard.setForeground(new Color(0, 0, 0));
					tfMenuManage.setBackground(new Color(240, 240, 240));
					tfMenuManage.setForeground(new Color(0, 0, 0));
					tfShow.setText("????????????");
					tfEmployeeManage.setBackground(new Color(240, 240, 240));
					tfEmployeeManage.setForeground(new Color(0, 0, 0));
					tfSalesManage.setBackground(new Color(240, 240, 240));
					tfSalesManage.setForeground(new Color(0, 0, 0));

				}
			});
			tfStoreManage.setText("????????????");
			tfStoreManage.setHorizontalAlignment(SwingConstants.CENTER);
			tfStoreManage.setFont(new Font("??????", Font.PLAIN, 22));
			tfStoreManage.setEditable(false);
			tfStoreManage.setColumns(10);
			tfStoreManage.setBounds(0, 120, 130, 36);
			tfStoreManage.setBorder(null);
		}
		return tfStoreManage;
	}

	private JTextField getTfEmployeeManage() {
		if (tfEmployeeManage == null) {
			tfEmployeeManage = new JTextField();
			tfEmployeeManage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					panelAdminMain.setVisible(false);
					execDashboardPanel.setVisible(false);
					execStoreManagePanel.setVisible(false);
					execMenuManagePanel.setVisible(false);
					execSalesPanel.setVisible(false);

					execEmployeeManagePanel.setVisible(true);
					execEmployeeManagePanel.setBounds(167, 55, 1059, 617);
					execEmployeeManagePanel.setLayout(null);
					frame.getContentPane().add(execEmployeeManagePanel);
					tfSearch.setVisible(false);
					lblSearchBar.setVisible(false);
					tfEmployeeManage.setBackground(new Color(226, 161, 101));
					tfEmployeeManage.setForeground(new Color(255, 255, 255));
					tfStoreManage.setBackground(new Color(240, 240, 240));
					tfStoreManage.setForeground(new Color(0, 0, 0));
					tfDashboard.setBackground(new Color(240, 240, 240));
					tfDashboard.setForeground(new Color(0, 0, 0));
					tfMenuManage.setBackground(new Color(240, 240, 240));
					tfMenuManage.setForeground(new Color(0, 0, 0));
					tfSalesManage.setBackground(new Color(240, 240, 240));
					tfSalesManage.setForeground(new Color(0, 0, 0));

					tfShow.setText("????????????");
				}
			});
			tfEmployeeManage.setEditable(false);
			tfEmployeeManage.setText("????????????");
			tfEmployeeManage.setFont(new Font("??????", Font.PLAIN, 22));
			tfEmployeeManage.setHorizontalAlignment(SwingConstants.CENTER);
			tfEmployeeManage.setColumns(10);
			tfEmployeeManage.setBounds(0, 170, 130, 36);
			tfEmployeeManage.setBorder(null);
		}
		return tfEmployeeManage;
	}

	private JTextField getTfMenuManage() {
		if (tfMenuManage == null) {
			tfMenuManage = new JTextField();
			tfMenuManage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					panelAdminMain.setVisible(false);
					execDashboardPanel.setVisible(false);
					execStoreManagePanel.setVisible(false);
					execEmployeeManagePanel.setVisible(false);
					execSalesPanel.setVisible(false);

					execMenuManagePanel.setVisible(true);
					execMenuManagePanel.setBounds(167, 55, 1059, 617);
					execMenuManagePanel.setLayout(null);
					frame.getContentPane().add(execMenuManagePanel);
					tfSearch.setVisible(false);
					lblSearchBar.setVisible(false);
					tfMenuManage.setBackground(new Color(226, 161, 101));
					tfMenuManage.setForeground(new Color(255, 255, 255));
					tfStoreManage.setBackground(new Color(240, 240, 240));
					tfStoreManage.setForeground(new Color(0, 0, 0));
					tfDashboard.setBackground(new Color(240, 240, 240));
					tfDashboard.setForeground(new Color(0, 0, 0));

					tfEmployeeManage.setBackground(new Color(240, 240, 240));
					tfEmployeeManage.setForeground(new Color(0, 0, 0));
					tfSalesManage.setBackground(new Color(240, 240, 240));
					tfSalesManage.setForeground(new Color(0, 0, 0));

					tfShow.setText("????????????");
				}
			});
			tfMenuManage.setEditable(false);
			tfMenuManage.setText("????????????");
			tfMenuManage.setFont(new Font("??????", Font.PLAIN, 22));
			tfMenuManage.setHorizontalAlignment(SwingConstants.CENTER);
			tfMenuManage.setColumns(10);
			tfMenuManage.setBounds(0, 220, 130, 36);
			tfMenuManage.setBorder(null);
		}
		return tfMenuManage;
	}

	private JTextField getTfSalesManage() {
		if (tfSalesManage == null) {
			tfSalesManage = new JTextField();
			tfSalesManage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					panelAdminMain.setVisible(false);
					execDashboardPanel.setVisible(false);
					execStoreManagePanel.setVisible(false);
					execEmployeeManagePanel.setVisible(false);
					execMenuManagePanel.setVisible(false);

					execSalesPanel.setVisible(true);
					execSalesPanel.setBounds(167, 55, 1059, 617);
					execSalesPanel.setLayout(null);
					frame.getContentPane().add(execSalesPanel);
					tfSearch.setVisible(false);
					lblSearchBar.setVisible(false);

					tfSalesManage.setBackground(new Color(226, 161, 101));
					tfSalesManage.setForeground(new Color(255, 255, 255));
					tfDashboard.setBackground(new Color(240, 240, 240));
					tfDashboard.setForeground(new Color(0, 0, 0));
					tfEmployeeManage.setBackground(new Color(240, 240, 240));
					tfEmployeeManage.setForeground(new Color(0, 0, 0));
					tfMenuManage.setBackground(new Color(240, 240, 240));
					tfMenuManage.setForeground(new Color(0, 0, 0));

					tfShow.setText("????????????");
				}
			});
			tfSalesManage.setEditable(false);
			tfSalesManage.setText("????????????");
			tfSalesManage.setFont(new Font("??????", Font.PLAIN, 22));
			tfSalesManage.setHorizontalAlignment(SwingConstants.CENTER);
			tfSalesManage.setColumns(10);
			tfSalesManage.setBounds(0, 270, 130, 36);
			tfSalesManage.setBorder(null);
		}
		return tfSalesManage;
	}

	private JTextField getTfMemberManage() {
		if (tfMemberManage == null) {
			tfMemberManage = new JTextField();
			tfMemberManage.setEditable(false);
			tfMemberManage.setText("????????????");
			tfMemberManage.setFont(new Font("??????", Font.PLAIN, 22));
			tfMemberManage.setHorizontalAlignment(SwingConstants.CENTER);
			tfMemberManage.setColumns(10);
			tfMemberManage.setBounds(0, 320, 130, 36);
			tfMemberManage.setBorder(null);
		}
		return tfMemberManage;
	}

	private JTextField getTfInventoryManage() {
		if (tfInventoryManage == null) {
			tfInventoryManage = new JTextField();
			tfInventoryManage.setEditable(false);
			tfInventoryManage.setText("????????????");
			tfInventoryManage.setFont(new Font("??????", Font.PLAIN, 22));
			tfInventoryManage.setHorizontalAlignment(SwingConstants.CENTER);
			tfInventoryManage.setColumns(10);
			tfInventoryManage.setBounds(0, 370, 130, 36);
			tfInventoryManage.setBorder(null);
		}
		return tfInventoryManage;
	}

	private JTextField getTfAdManage() {
		if (tfAdManage == null) {
			tfAdManage = new JTextField();
			tfAdManage.setText("????????????");
			tfAdManage.setEditable(false);
			tfAdManage.setFont(new Font("??????", Font.PLAIN, 22));
			tfAdManage.setHorizontalAlignment(SwingConstants.CENTER);
			tfAdManage.setColumns(10);
			tfAdManage.setBounds(0, 420, 130, 36);
			tfAdManage.setBorder(null);
		}
		return tfAdManage;
	}

	private JTextField getTfNoticeManage() {
		if (tfNoticeManage == null) {
			tfNoticeManage = new JTextField();
			tfNoticeManage.setEditable(false);
			tfNoticeManage.setText("????????????");
			tfNoticeManage.setFont(new Font("??????", Font.PLAIN, 22));
			tfNoticeManage.setHorizontalAlignment(SwingConstants.CENTER);
			tfNoticeManage.setColumns(10);
			tfNoticeManage.setBounds(0, 470, 130, 36);
			tfNoticeManage.setBorder(null);
		}
		return tfNoticeManage;
	}

	private JPanel getPnAdminMain() {
		if (pnAdminMain == null) {
			pnAdminMain = new JPanel();
			pnAdminMain.setBounds(167, 74, 1059, 617);
		}
		return pnAdminMain;
	}

	private JLabel getLblBackground() {
		if (lblBackground == null) {
			lblBackground = new JLabel("");
			lblBackground.setBounds(0, 0, 1280, 721);
			lblBackground
					.setIcon(new ImageIcon(ExecMainFrame.class.getResource("/com/javalec/image/ExecFrameLine.png")));
		}
		return lblBackground;
	}

	private JLabel getLblAlarmIcons() {
		if (lblAlarmIcons == null) {
			lblAlarmIcons = new JLabel("");
			lblAlarmIcons.setIcon(new ImageIcon(ExecMainFrame.class.getResource("/com/javalec/image/AlarmIcons.png")));
			lblAlarmIcons.setBounds(978, 10, 130, 30);
		}
		return lblAlarmIcons;
	}

	private JLabel getLblProfilePicture() {
		if (lblProfilePicture == null) {
			lblProfilePicture = new JLabel("");
			lblProfilePicture
					.setIcon(new ImageIcon(ExecMainFrame.class.getResource("/com/javalec/image/SukjinFace.png")));
			lblProfilePicture.setBounds(1110, 4, 34, 41);
		}
		return lblProfilePicture;
	}

	private JTextField getTfExecName() {
		if (tfExecName == null) {
			tfExecName = new JTextField();
			tfExecName.setFont(new Font("??????", Font.PLAIN, 18));
			tfExecName.setText("Sukjin Ko");
			tfExecName.setHorizontalAlignment(SwingConstants.CENTER);
			tfExecName.setBackground(new Color(240, 240, 240));
			tfExecName.setBounds(1140, 13, 106, 21);
			tfExecName.setColumns(10);
			tfExecName.setBorder(null);
		}
		return tfExecName;
	}
} // End
