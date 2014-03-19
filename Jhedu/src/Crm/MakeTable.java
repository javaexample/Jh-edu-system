package Crm;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class MakeTable extends JFrame {

   public static JTable makeTab() {
      
      JTable tb;
      int row;
      int col;
      String ColumnName1[] = { "순번", "소스", "날짜", "시간", "연락처(일반)", "연락처(핸드폰)", "이름",
            "성별", "나이", "이메일", "주소", "문의내용", "비고", "담당자", "신청여부", "결제여부",
            "배송여부" };
      int ColumnSize1[] = { 140, 210, 300, 130, 350, 350, 220, 110, 120, 500, 700,
            700, 220, 220, 220, 220, 220 };

      row = 100;
      col = ColumnName1.length;

      tb = new JTable(row, col);

      for (int i = 0; i < ColumnName1.length; i++) { // 열이름설정
         tb.getTableHeader().getColumnModel().getColumn(i)
               .setHeaderValue(ColumnName1[i]);
      }

      for (int i = 0; i < ColumnSize1.length; i++) { // 열크기설정
         tb.getColumnModel().getColumn(i).setPreferredWidth(ColumnSize1[i]);
      }

      tb.getTableHeader().setReorderingAllowed(false); // 열이동불가
      tb.getTableHeader().setResizingAllowed(false); // 열조절불가

      // 테이블 열클릭시 정렬기능
      tb.setAutoCreateRowSorter(true);
      TableRowSorter trs1 = new TableRowSorter(tb.getModel());
      tb.setRowSorter(trs1);

      return tb;

   }

}
