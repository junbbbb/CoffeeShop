package com.javalec.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.base.AdminMainFrame;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	AdminSalesPanel adSale = new AdminSalesPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StatDialog dialog = new StatDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StatDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSum = new JLabel("판매금액 총합:");
			lblSum.setBounds(20, 20, 85, 15);
			contentPanel.add(lblSum);
		}
		{
			JLabel lblAvg = new JLabel("판매금액 평균:");
			lblAvg.setBounds(20, 45, 85, 15);
			contentPanel.add(lblAvg);
		}
		{
			JLabel lblSumResult = new JLabel(Integer.toString(AdminSalesPanel.sum));
			lblSumResult.setBounds(117, 20, 100, 15);
			contentPanel.add(lblSumResult);
		}
		{
			JLabel lblAvgResult = new JLabel(String.valueOf(AdminSalesPanel.avg));
			lblAvgResult.setBounds(117, 45, 100, 15);
			contentPanel.add(lblAvgResult);
		}
		{
			JButton btnExit = new JButton("나가기");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					AdminSalesPanel.sum = 0;
				}
			});
			btnExit.setBounds(329, 220, 95, 23);
			contentPanel.add(btnExit);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
