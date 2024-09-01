package util;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemVerifier {

    public static InputVerifier integerVerify(String fieldName, Integer min, Integer max, String defaultText) {
        return new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                input.setVerifyInputWhenFocusTarget(false);
                String text = ((JTextComponent) input).getText();
                try {
                    Integer d = Integer.parseInt(text);
                    if(min != null && d < min){
                        JOptionPane.showMessageDialog(null, fieldName + "不能小于" + min, "系统提示", JOptionPane.WARNING_MESSAGE);
                        ((JTextComponent) input).setText(defaultText);
                        return false;
                    }
                    if(max != null && d > max){
                        JOptionPane.showMessageDialog(null, fieldName + "不能大于" + max, "系统提示", JOptionPane.WARNING_MESSAGE);
                        ((JTextComponent) input).setText(defaultText);
                        return false;
                    }
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, fieldName + "格式错误", "系统提示", JOptionPane.WARNING_MESSAGE);
                    ((JTextComponent) input).setText(defaultText);
                    return false;
                }
                return true;
            }
        };
    }

    public static InputVerifier doubleVerify(String fieldName, Double min, Double max, String defaultText) {
        return new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                input.setVerifyInputWhenFocusTarget(false);
                String text = ((JTextComponent) input).getText();
                try {
                    Double d = Double.parseDouble(text);
                    if(min != null && d < min){
                        JOptionPane.showMessageDialog(null, fieldName + "不能小于" + min, "系统提示", JOptionPane.WARNING_MESSAGE);
                        ((JTextComponent) input).setText(defaultText);
                        return false;
                    }
                    if(max != null && d > max){
                        JOptionPane.showMessageDialog(null, fieldName + "不能大于" + max, "系统提示", JOptionPane.WARNING_MESSAGE);
                        ((JTextComponent) input).setText(defaultText);
                        return false;
                    }
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, fieldName + "格式错误", "系统提示", JOptionPane.WARNING_MESSAGE);
                    ((JTextComponent) input).setText(defaultText);
                    return false;
                }
                return true;
            }
        };
    }

    public static InputVerifier emptyVerify(String fieldName, Integer minLen, Integer maxLen) {
        return new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                input.setVerifyInputWhenFocusTarget(false);
                String text = ((JTextComponent) input).getText();

                int len = text.trim().length();
                if(len == 0) {
                    JOptionPane.showMessageDialog(null, fieldName + "不能为空", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                if(minLen != null && len < minLen){
                    JOptionPane.showMessageDialog(null, fieldName + "长度不能小于" + minLen, "系统提示", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                if(maxLen != null && len > maxLen){
                    JOptionPane.showMessageDialog(null, fieldName + "长度不能大于" + maxLen, "系统提示", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                return true;
            }
        };
    }

    public static InputVerifier dateVerify(String fieldName, String pattern, Date min, Date max) {
        return new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                input.setVerifyInputWhenFocusTarget(false);
                String text = ((JTextComponent) input).getText();
                int len = text.trim().length();
                if (len == 0) {
                    JOptionPane.showMessageDialog(null, fieldName + "不能为空", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                try {
                    SimpleDateFormat format = new SimpleDateFormat(pattern);
                    Date date = format.parse(text);
                    if (min != null && date.before(min)) {
                        JOptionPane.showMessageDialog(null, fieldName + "不能早于" + format.format(min), "系统提示", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    if (max != null && date.after(max)) {
                        JOptionPane.showMessageDialog(null, fieldName + "不能晚于" + format.format(max), "系统提示", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, fieldName + "格式错误", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                return true;
            }
        };
    }
}
