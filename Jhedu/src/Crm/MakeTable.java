package Crm;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class MakeTable extends JFrame {

   public static JTable makeTab() {
      
      JTable tb;
      int row;
      int col;
      String ColumnName1[] = { "����", "�ҽ�", "��¥", "�ð�", "����ó(�Ϲ�)", "����ó(�ڵ���)", "�̸�",
            "����", "����", "�̸���", "�ּ�", "���ǳ���", "���", "�����", "��û����", "��������",
            "��ۿ���" };
      int ColumnSize1[] = { 140, 210, 300, 130, 350, 350, 220, 110, 120, 500, 700,
            700, 220, 220, 220, 220, 220 };

      row = 100;
      col = ColumnName1.length;

      tb = new JTable(row, col);

      for (int i = 0; i < ColumnName1.length; i++) { // ���̸�����
         tb.getTableHeader().getColumnModel().getColumn(i)
               .setHeaderValue(ColumnName1[i]);
      }

      for (int i = 0; i < ColumnSize1.length; i++) { // ��ũ�⼳��
         tb.getColumnModel().getColumn(i).setPreferredWidth(ColumnSize1[i]);
      }

      tb.getTableHeader().setReorderingAllowed(false); // ���̵��Ұ�
      tb.getTableHeader().setResizingAllowed(false); // �������Ұ�

      // ���̺� ��Ŭ���� ���ı��
      tb.setAutoCreateRowSorter(true);
      TableRowSorter trs1 = new TableRowSorter(tb.getModel());
      tb.setRowSorter(trs1);

      return tb;

   }

}
