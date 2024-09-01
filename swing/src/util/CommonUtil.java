package util;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.List;

public class CommonUtil {
    public static Object[][] toArray(List<Object[]> list) {
        Object[][] result = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static boolean isNotEmpty(String text) {
        if (text == null || text.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String text) {
        if (text == null || text.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static void hide(JTable table, int... cols){
        TableColumnModel tcm = table.getColumnModel();
        for(int col: cols){
            TableColumn tc = tcm.getColumn(col);
            tc.setMaxWidth(0);
            tc.setPreferredWidth(0);
            tc.setWidth(0);
            tc.setMinWidth(0);
            tcm = table.getTableHeader().getColumnModel();
            tc = tcm.getColumn(col);
            tc.setMaxWidth(0);
            tc.setPreferredWidth(0);
            tc.setWidth(0);
            tc.setMinWidth(0);
        }
    }
}
