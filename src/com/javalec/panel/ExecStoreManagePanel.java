package com.javalec.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.sql.Connection;
import java.sql.DriverManager;

import com.javalec.base.ExecMainFrame;
import com.javalec.dao.DaoEmployeeManage;
import com.javalec.dao.DaoStoreManage;
import com.javalec.dto.DtoEmployeeManage;
import com.javalec.dto.DtoStoreManage;

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

public class ExecStoreManagePanel extends JPanel {
	private JComboBox comboBox;
	private JLabel lblSearchBar;
	private JTextField tfSearch;
	private JLabel lblNewLabel;
	private JTextField tfTotalStoreNum;
	private JScrollPane scrollPane;
	private JTable innertable;

	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

	/**
	 * Create the panel.
	 */
	public ExecStoreManagePanel() {
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
		add(getTfTotalStoreNum());
		add(getLblNewLabel());
		add(getScrollPane());
		tableInit();
		searchAction();

	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(0, 11, 92, 30);
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "매장코드", "매장명", "주소", "지점장" }));
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

	private JTextField getTfTotalStoreNum() {
		if (tfTotalStoreNum == null) {
			tfTotalStoreNum = new JTextField();
			tfTotalStoreNum.setBounds(5, 61, 116, 21);
			tfTotalStoreNum.setBorder(null);
			tfTotalStoreNum.setEditable(false);
			tfTotalStoreNum.setBackground(new Color(226, 161, 101));
			tfTotalStoreNum.setText("전체: 208건");
			tfTotalStoreNum.setColumns(10);
		}
		return tfTotalStoreNum;
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
		Outer_Table.addColumn("매장코드");
		Outer_Table.addColumn("매장명");
		Outer_Table.addColumn("주소");
		Outer_Table.addColumn("전화번호");
		Outer_Table.addColumn("지점장ID");
		Outer_Table.addColumn("개업일");
		Outer_Table.addColumn("사업자등록번호");

		Outer_Table.setColumnCount(8); // 몇개인지 알려줘야함.

		int i = Outer_Table.getRowCount(); // 현재 몇줄이냐? 중간 삭제 변경 일때 위에있는 모양이 바뀌니깐. 기존에 있는걸 지울려고 가져옴
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0); // 하나씩 떙겨오면서 지운다.
		}

		innertable.setAutoResizeMode(innertable.AUTO_RESIZE_OFF); // 태이블끼리 왓다갓다하면 따른짓을 많이해야해서 끈다.
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		int vColIndex = 0;
		TableColumn col = innertable.getColumnModel().getColumn(vColIndex);
		int width = 50;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 1;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 2;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 3;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 250;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 4;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 160;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 5;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 90;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 6;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);
		
		vColIndex = 7;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 180;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);


	}

	private void searchAction() {
		
		DaoStoreManage dao = new DaoStoreManage();
		ArrayList<DtoStoreManage> dtoList = dao.selectList();

		DaoEmployeeManage dao2 = new DaoEmployeeManage();
		ArrayList<DtoEmployeeManage> dtoList2 = dao2.selectList();
		
		int listCount = dtoList.size();

		for (int index = 0; index < listCount; index++) {
			String wkNo = Integer.toString(index+1);
			String wkStoreseq = dtoList.get(index).getStoreseq();
			String wkSname = dtoList.get(index).getSname();
			String wkSaddress = dtoList.get(index).getSaddress();
			String wkStelno = dtoList.get(index).getStelno();
			String wkEid = dtoList2.get(index).getEid(); // 지점장 직원ID 불러오기 * for문은 listCount만큼 돎. index <= listCount 시, 에러
			String wkSopendate = dtoList.get(index).getSopendate();
			String wkScrn = dtoList.get(index).getScrn();
			String[] qTxt = { wkNo,wkStoreseq, wkSname, wkSaddress, wkStelno, wkEid, wkSopendate, wkScrn };
			Outer_Table.addRow(qTxt);
		}

	}

} // End
