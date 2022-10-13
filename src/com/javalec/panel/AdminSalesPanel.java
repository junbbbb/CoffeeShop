package com.javalec.panel;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.base.ExecMainFrame;
import com.javalec.dao.DaoMenuManage;
import com.javalec.dto.DtoMenuManage;

public class AdminSalesPanel extends JPanel {

	private JComboBox comboBox;
	private JLabel lblSearchBar;
	private JTextField tfSearch;
	private JLabel lblNewLabel;
	private JTextField tfTotalMenuNum;
	private JScrollPane scrollPane;
	private JTable innertable;
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

	/**
	 * Create the panel.
	 */
	public AdminSalesPanel() {
		setLayout(null);
		add(getComboBox());
		add(getLblSearchBar());
		add(getTfSearch());
		add(getTfTotalMenuNum());
		add(getLblNewLabel());
		add(getScrollPane());
		tableInit();
		searchAction();
		// TODO Auto-generated constructor stub
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(0, 11, 92, 30);
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "직원ID", "판매금액" }));
		}
		return comboBox;
	}

	private JLabel getLblSearchBar() {
		if (lblSearchBar == null) {
			lblSearchBar = new JLabel("New label");
			lblSearchBar.setBounds(100, 11, 353, 30);
			lblSearchBar
					.setIcon(new ImageIcon(ExecMainFrame.class.getResource("/com/javalec/image/SearchBarIcon.png")));
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
					ExecStoreManagePanel.class.getResource("/com/javalec/image/ExecStoreManagerPanelBrownbar.png")));
		}
		return lblNewLabel;
	}

	private JTextField getTfTotalMenuNum() {
		if (tfTotalMenuNum == null) {
			tfTotalMenuNum = new JTextField();
			tfTotalMenuNum.setBounds(5, 61, 116, 21);
			tfTotalMenuNum.setBorder(null);
			tfTotalMenuNum.setEditable(false);
			tfTotalMenuNum.setBackground(new Color(226, 161, 101));
			tfTotalMenuNum.setText("전체: 6건");
			tfTotalMenuNum.setColumns(10);
		}
		return tfTotalMenuNum;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 92, 1025, 626);
			scrollPane.setViewportView(getInnertable());
		}
		innertable.setModel(Outer_Table);
		return scrollPane;
	}

	private JTable getInnertable() {
		if (innertable == null) {
			innertable = new JTable();
			innertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return innertable;
	}

	private void tableInit() {

		Outer_Table.addColumn("No");
		Outer_Table.addColumn("주문ID");
		Outer_Table.addColumn("직원ID");
		Outer_Table.addColumn("고객ID");
		Outer_Table.addColumn("메뉴ID");
		Outer_Table.addColumn("메뉴명");
		Outer_Table.addColumn("판매가");
		Outer_Table.addColumn("주문수량");
		Outer_Table.addColumn("주문날짜");
		Outer_Table.addColumn("주문처리");
		Outer_Table.setColumnCount(10);

		int i = Outer_Table.getRowCount(); // 현재 몇줄이냐? 중간 삭제 변경 일때 위에있는 모양이 바뀌니깐. 기존에 있는걸 지울려고 가져옴
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0); // 하나씩 떙겨오면서 지운다.
		}

		innertable.setAutoResizeMode(innertable.AUTO_RESIZE_OFF);
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		int vColIndex = 0;
		TableColumn col = innertable.getColumnModel().getColumn(vColIndex);
		int width = 40;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 1;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
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
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);
		
		vColIndex = 5;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 230;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);
		
		vColIndex = 6;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 90;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);
		
		vColIndex = 7;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 90;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);
		
		vColIndex = 8;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 152;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);
		
		vColIndex = 9;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);
		
		
	}

	private void searchAction() {
		DaoMenuManage dao = new DaoMenuManage();
		ArrayList<DtoMenuManage> dtoList = dao.selectListAdminMenuManage();

		int listCount = dtoList.size();

		for (int index = 0; index < listCount; index++) {

			String wkNo = Integer.toString(index + 1);
			String wkMenuid = dtoList.get(index).getMenuid();

			String[] qTxt = { wkNo, wkMenuid };
			Outer_Table.addRow(qTxt);

		}

	}

} // End
