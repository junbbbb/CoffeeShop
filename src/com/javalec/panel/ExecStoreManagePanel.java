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
import com.javalec.dialog.AdditionStore;
import com.javalec.dialog.UpdateStore;
import com.javalec.dto.DtoEmployeeManage;
import com.javalec.dto.DtoSalesManage;
import com.javalec.dto.DtoStoreManage;
import com.javalec.function.ExecSalesFunction;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExecStoreManagePanel extends JPanel {
	private JComboBox cbSelection;
	public static JTextField tfSelection;
	private JLabel lblNewLabel;
	private JTextField tfTotalStoreNum;
	private JScrollPane scrollPane;
	private JTable innertable;
	public static int sum = 0;
	public static double avg = 0;

	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnSelection;
	public static String conditionQueryColumn = "";

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
		add(getBtnSelection());
		add(getBtnUpdate());
		add(getBtnInsert());
		add(getCbSelection());
		add(getTfSelection());
		add(getTfTotalStoreNum());
		add(getLblNewLabel());
		add(getScrollPane());
		tableInit();
		searchAction();

	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setBounds(0, 11, 92, 30);
			cbSelection.setModel(new DefaultComboBoxModel(new String[] { "매장코드", "매장명", "주소", "지점장명" }));
		}
		return cbSelection;
	}

	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(100, 11, 320, 30);
			tfSelection.setColumns(10);
		}
		return tfSelection;
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
			tfTotalStoreNum.setText("");
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
		Outer_Table.addColumn("지점장ID"); // 5
		Outer_Table.addColumn("지점장명");
		Outer_Table.addColumn("개업일");
		Outer_Table.addColumn("사업자등록번호");

		Outer_Table.setColumnCount(9); // 몇개인지 알려줘야함.

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
		width = 100;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 3;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 280;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 4;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 125;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 5;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 6;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 7;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 110;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 8;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 140;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

	}

	private void searchAction() {

		DaoStoreManage dao = new DaoStoreManage();
		ArrayList<DtoStoreManage> dtoList = dao.selectList();

		int listCount = dtoList.size();
		tfTotalStoreNum.setText("전체: " + listCount + "건");

		for (int index = 0; index < listCount; index++) {
			String wkNo = Integer.toString(index + 1);
			String wkStoreseq2 = dtoList.get(index).getStoreseq2();
			String wkSname = dtoList.get(index).getSname();
			String wkSaddress = dtoList.get(index).getSaddress();
			String wkStelno = dtoList.get(index).getStelno();
			String wkEid = dtoList.get(index).getEid();
			String wkEname = dtoList.get(index).getEname();
			String wkSopendate = dtoList.get(index).getSopendate();
			String wkScrn = dtoList.get(index).getScrn();
			String[] qTxt = { wkNo, wkStoreseq2, wkSname, wkSaddress, wkStelno, wkEid, wkEname, wkSopendate, wkScrn };
			Outer_Table.addRow(qTxt);
		}

	}

	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("등록");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AdditionStore additionStore = new AdditionStore();
					additionStore.setVisible(true);
				}
			});
			btnInsert.setBounds(813, 61, 70, 21);
		}
		return btnInsert;
	}

	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정 및 삭제");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UpdateStore updateStore = new UpdateStore();
					updateStore.setVisible(true);
				}
			});
			btnUpdate.setBounds(895, 61, 108, 21);
		}
		return btnUpdate;
	}

	private JButton getBtnSelection() {
		if (btnSelection == null) {
			btnSelection = new JButton("");
			btnSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnSelection
					.setIcon(new ImageIcon(ExecStoreManagePanel.class.getResource("/com/javalec/image/search.png")));
			btnSelection.setBounds(430, 11, 50, 30);
		}
		return btnSelection;
	}

	private void conditionQuery() { // 콤보박스 결과 값 가져와 테이블 데이터 출력
		int i = cbSelection.getSelectedIndex();
		switch (i) {
		case 0:
			conditionQueryColumn = "storeseq2";
			break;

		case 1:
			conditionQueryColumn = "sname";
			break;

		case 2:
			conditionQueryColumn = "saddress";
			break;

		case 3:
			conditionQueryColumn = "ename";

		default:
			break;

		}

		tableInit();
		conditionQueryAction(conditionQueryColumn);

	}

	private void conditionQueryAction(String conditionQueryColumn) { // 조건 검색

		if (tfSelection.getText().equals("")) {
			tableInit();
			searchAction();

		} else {

			DaoStoreManage dao = new DaoStoreManage();
			ArrayList<DtoStoreManage> dtoList = dao.selectListSalesCondition();

			int listCount = dtoList.size();
			tfTotalStoreNum.setText("전체: " + listCount + "건");

			for (int index = 0; index < listCount; index++) {
				String wkNo = Integer.toString(index + 1);
				String wkStoreseq2 = dtoList.get(index).getStoreseq2();
				String wkSname = dtoList.get(index).getSname();
				String wkSaddress = dtoList.get(index).getSaddress();
				String wkStelno = dtoList.get(index).getStelno();
				String wkEid = dtoList.get(index).getEid();
				String wkEname = dtoList.get(index).getEname();
				String wkSopendate = dtoList.get(index).getSopendate();
				String wkScrn = dtoList.get(index).getScrn();
				String[] qTxt = { wkNo, wkStoreseq2, wkSname, wkSaddress, wkStelno, wkEid, wkEname, wkSopendate,
						wkScrn };
				Outer_Table.addRow(qTxt);
			}
		}
	}

} // End
