package Controller;

import Visual.FrmLogin;
import javax.swing.UIManager;


public class Main {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        FrmLogin ObjMain = new FrmLogin();
        ObjMain.setVisible(true);
        
    }
    
}
