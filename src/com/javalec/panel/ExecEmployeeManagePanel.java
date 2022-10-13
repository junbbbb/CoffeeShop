package com.javalec.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.sql.Connection;
import java.sql.DriverManager;

import com.javalec.base.AdminMainFrame;
import com.javalec.dao.DaoEmployeeManage;
import com.javalec.dto.DtoEmployeeManage;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ExecEmployeeManagePanel extends JPanel {
	private JComboBox comboBox;
	private JLabel lblSearchBar;
	private JTextField tfSearch;
	private JLabel lblNewLabel;
	private JTextField tfTotalEmployeeNum;
	private JScrollPane scrollPane;
	private JTable innertable;

	private final DefaultTableModel Outer_Table = new DefaultTableModel();

	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	private JTextField tfEmployeeManageMsg;

	/**
	 * Create the panel.
	 */
	public ExecEmployeeManagePanel() {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		setLayout(null);
		add(getComboBox());
		add(getLblSearchBar());
		add(getTfSearch());
		add(getTfTotalEmployeeNum());
		add(getScrollPane());
		add(getTfEmployeeManageMsg());
		add(getLblNewLabel());
		tableInit();
		searchAction();

	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(0, 11, 92, 30);
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "직원ID", "직급", "전화번호", "입사일" }));
		}
		return comboBox;
	}

	private JLabel getLblSearchBar() {
		if (lblSearchBar == null) {
			lblSearchBar = new JLabel("New label");
			lblSearchBar.setBounds(100, 11, 353, 30);
			lblSearchBar
					.setIcon(new ImageIcon(AdminMainFrame.class.getResource("/com/javalec/image/SearchBarIcon.png")));
		}
		return lblSearchBar;
	}

	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(100, 11, 320, 30);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(0, 51, 1024, 40);
			lblNewLabel.setBackground(new Color(226, 161, 101));
			lblNewLabel.setIcon(new ImageIcon(
					ExecEmployeeManagePanel.class.getResource("/com/javalec/image/ExecStoreManagerPanelBrownbar.png")));
		}
		return lblNewLabel;
	}

	private JTextField getTfTotalEmployeeNum() {
		if (tfTotalEmployeeNum == null) {
			tfTotalEmployeeNum = new JTextField();
			tfTotalEmployeeNum.setBounds(5, 61, 116, 21);
			tfTotalEmployeeNum.setBorder(null);
			tfTotalEmployeeNum.setEditable(false);
			tfTotalEmployeeNum.setBackground(new Color(226, 161, 101));
			tfTotalEmployeeNum.setText("전체: 13건");
			tfTotalEmployeeNum.setColumns(10);
		}
		return tfTotalEmployeeNum;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 92, 1025, 626);
			scrollPane.setViewportView(getInnertable());
		}
		return scrollPane;
	}

	private JTable getInnertable() {
		if (innertable == null) {
			innertable = new JTable();
			innertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		innertable.setModel(Outer_Table);
		return innertable;
	}

	// TableInit
	private void tableInit() {

		Outer_Table.addColumn("No");
		Outer_Table.addColumn("직원ID");
		Outer_Table.addColumn("이름");
		Outer_Table.addColumn("직급");
		Outer_Table.addColumn("전화번호");
		Outer_Table.addColumn("주소"); // 5
		Outer_Table.addColumn("이메일");
		Outer_Table.addColumn("입사일");

		Outer_Table.setColumnCount(8); // 몇개인지 알려줘야함.

		int i = Outer_Table.getRowCount(); // 현재 몇줄이냐? 중간 삭제 변경 일때 위에있는 모양이 바뀌니깐. 기존에 있는걸 지울려고 가져옴
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0); // 하나씩 떙겨오면서 지운다.
		}

		innertable.setAutoResizeMode(innertable.AUTO_RESIZE_OFF); // 태이블끼리 왓다갓다하면 따른짓을 많이해야해서 끈다.
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		int vColIndex = 0;
		TableColumn col = innertable.getColumnModel().getColumn(vColIndex);
		int width = 40;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 1;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 70;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 2;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 3;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 4;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 130;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 5;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 342;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 6;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 170;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);
		
		vColIndex = 7;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 110;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);


	}

	private void searchAction() {
		DaoEmployeeManage dao = new DaoEmployeeManage();
		ArrayList<DtoEmployeeManage> dtoList = dao.selectList();

		int listCount = dtoList.size();

		for (int index = 0; index < listCount; index++) {
			String wkNo = Integer.toString(index + 1);
			String wkEid = dtoList.get(index).getEid();
			String wkEname = dtoList.get(index).getEname();
			String wkErank = dtoList.get(index).getErank();
			String wkEtelno = dtoList.get(index).getEtelno();
			String wkEaddress = dtoList.get(index).getEaddress();
			String wkEemail = dtoList.get(index).getEemail();
			String wkEindate = dtoList.get(index).getEindate();

			String[] qTxt = { wkNo, wkEid, wkEname, wkErank, wkEtelno, wkEaddress, wkEemail, wkEindate };
			Outer_Table.addRow(qTxt);
		}

	}

	private JTextField getTfEmployeeManageMsg() {
		if (tfEmployeeManageMsg == null) {
			tfEmployeeManageMsg = new JTextField();
			tfEmployeeManageMsg.setText("계좌정보 및 상세정보는 해당 직원을 클릭하여 확인하세요.");
			tfEmployeeManageMsg.setBorder(null);
			tfEmployeeManageMsg.setBackground(new Color(226, 161, 101));
			tfEmployeeManageMsg.setBounds(700, 61, 320, 21);
			tfEmployeeManageMsg.setColumns(10);
		}
		return tfEmployeeManageMsg;
	}
} // End
