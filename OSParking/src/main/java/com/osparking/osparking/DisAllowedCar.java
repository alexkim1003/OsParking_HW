/* 
 * OSParking, Parking Lot Management Software
 * Copyright (C) 2015 Open Source Parking Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.osparking.osparking;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import com.osparking.global.names.OSP_enums.BarOperation;
import static com.osparking.global.Globals.getTopLeftPointToPutThisFrameAtScreenCenter;
import static com.osparking.global.Globals.OSPiconList;
import static com.osparking.global.names.DB_Access.readSettings;
import static com.osparking.global.Globals.checkOptions;
import static com.osparking.global.Globals.font_Size;
import static com.osparking.global.Globals.font_Style;
import static com.osparking.global.Globals.font_Type;
import static com.osparking.global.Globals.initializeLoggers;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Park, Jongbum <Park, Jongbum at Open Source Parking Inc.>
 */
public class DisAllowedCar extends javax.swing.JFrame {

    Toolkit toolkit;
    Timer timer;
    ControlGUI parent = null;
    byte gateNo;
    String tagRecognized;
    Date arrivalTm;
    String tagEnteredAs;
    String filename = null;
    String filenameModified = null;
    BufferedImage bImg = null;
    int delay;
    
    /**
     * Constructor of a window frame for a registered vehicle but parking is not allowed.
     * 
     * @param parent parent window that opened this window
     * @param tagRecognized tag number recognized by a image processing software
     * @param arrivalTm vehicle arrival time at the gate
     * @param tagEnteredAs tag number stored in the registered vehicle database table
     * @param remark description why the car is disallowed to park
     * @param gateNo gate ID where the car arrived
     * @param filename 
     * @param filenameModified
     * @param bImg
     * @param delay 
     */
    public DisAllowedCar(ControlGUI parent, String tagRecognized, Date arrivalTm, 
            String tagEnteredAs, String remark, byte gateNo, String filename, String filenameModified, 
            BufferedImage bImg, int delay) {
        
        initComponents();
        this.parent = parent;
        this.tagRecognized = tagRecognized;
        this.arrivalTm = arrivalTm;
        this.tagEnteredAs = tagEnteredAs;
        this.gateNo = gateNo;
        this.filename = filename;
        this.filenameModified = filenameModified;
        this.bImg = bImg;
        this.delay = delay;
        
        setIconImages(OSPiconList);
        Point screenCenter = getTopLeftPointToPutThisFrameAtScreenCenter(this);
        setLocation(screenCenter);        
        
        recogTextField.setText(tagRecognized);
        regisTextField.setText(tagEnteredAs);
        disAllowReasonTextField.setText(remark);
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, //initial delay
        1 * 1000);   
        
        openBarButton.requestFocus();
        
        // define shortcut keys for 3 buttons
//        JComponent pane = (JComponent) this.getContentPane();
//        pane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 
//                java.awt.event.InputEvent.ALT_DOWN_MASK), "controlTheBar");
//        pane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 
//                java.awt.event.InputEvent.ALT_DOWN_MASK), "controlTheBar");
//        pane.getActionMap().put("controlTheBar", new AbstractAction("controlTheBar") {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String cmd = e.getActionCommand();
//                switch (cmd.charAt(0)) {
//                    case 'O': case 'o':
//                        openBarButtonActionPerformed(null);
//                        break;
//                    case 'C': case 'c':
//                        closeFormButtonActionPerformed(null);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//        openBarButtonActionPerformed(null);
    }
    
    class RemindTask extends TimerTask {
        public void run() {
            toolkit.beep();
            if (WarningSignTBox.getForeground() == Color.red)
                WarningSignTBox.setForeground(WarningSignTBox.getBackground());
            else 
                WarningSignTBox.setForeground(Color.red);
        }
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        wholePanel = new javax.swing.JPanel();
        firstPanel = new javax.swing.JPanel();
        tag_Reco_Panel = new javax.swing.JPanel();
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel1 = new javax.swing.JLabel();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        recogTextField = new javax.swing.JTextField();
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        tag_Regi_Panel = new javax.swing.JPanel();
        filler21 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel2 = new javax.swing.JLabel();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        regisTextField = new javax.swing.JTextField();
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 20));
        warnningPanel = new javax.swing.JPanel();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 32767));
        WarningSignTBox = new javax.swing.JLabel();
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        reasonPanel = new javax.swing.JPanel();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 32767));
        jLabel4 = new javax.swing.JLabel();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 20));
        disallowReasonPanel = new javax.swing.JPanel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 32767));
        disAllowReasonTextField = new javax.swing.JTextField();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 20));
        buttonPanel = new javax.swing.JPanel();
        openBarButton = new javax.swing.JButton();
        closeGateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DisAllowed Car");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().add(filler1, java.awt.BorderLayout.PAGE_START);
        getContentPane().add(filler2, java.awt.BorderLayout.PAGE_END);
        getContentPane().add(filler3, java.awt.BorderLayout.EAST);
        getContentPane().add(filler4, java.awt.BorderLayout.WEST);

        wholePanel.setPreferredSize(new java.awt.Dimension(513, 450));
        wholePanel.setLayout(new javax.swing.BoxLayout(wholePanel, javax.swing.BoxLayout.PAGE_AXIS));

        firstPanel.setLayout(new javax.swing.BoxLayout(firstPanel, javax.swing.BoxLayout.PAGE_AXIS));

        tag_Reco_Panel.setLayout(new javax.swing.BoxLayout(tag_Reco_Panel, javax.swing.BoxLayout.LINE_AXIS));
        tag_Reco_Panel.add(filler20);

        jLabel1.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Tag Recognized");
        jLabel1.setMaximumSize(new java.awt.Dimension(150, 50));
        jLabel1.setMinimumSize(new java.awt.Dimension(150, 50));
        jLabel1.setPreferredSize(new java.awt.Dimension(180, 50));
        tag_Reco_Panel.add(jLabel1);
        tag_Reco_Panel.add(filler11);

        recogTextField.setEditable(false);
        recogTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size+12));
        recogTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recogTextField.setText("30MO8186");
        recogTextField.setMaximumSize(new java.awt.Dimension(200, 50));
        recogTextField.setMinimumSize(new java.awt.Dimension(200, 50));
        recogTextField.setPreferredSize(new java.awt.Dimension(200, 50));
        tag_Reco_Panel.add(recogTextField);
        tag_Reco_Panel.add(filler19);

        firstPanel.add(tag_Reco_Panel);
        firstPanel.add(filler5);

        tag_Regi_Panel.setLayout(new javax.swing.BoxLayout(tag_Regi_Panel, javax.swing.BoxLayout.LINE_AXIS));
        tag_Regi_Panel.add(filler21);

        jLabel2.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel2.setForeground(java.awt.Color.gray);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Tag Registered");
        jLabel2.setMaximumSize(new java.awt.Dimension(150, 50));
        jLabel2.setMinimumSize(new java.awt.Dimension(150, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(180, 50));
        tag_Regi_Panel.add(jLabel2);
        tag_Regi_Panel.add(filler12);

        regisTextField.setEditable(false);
        regisTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size+12));
        regisTextField.setForeground(java.awt.Color.gray);
        regisTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        regisTextField.setText("30MO8186");
        regisTextField.setMaximumSize(new java.awt.Dimension(200, 50));
        regisTextField.setMinimumSize(new java.awt.Dimension(200, 50));
        regisTextField.setPreferredSize(new java.awt.Dimension(200, 50));
        tag_Regi_Panel.add(regisTextField);
        tag_Regi_Panel.add(filler22);

        firstPanel.add(tag_Regi_Panel);
        firstPanel.add(filler6);

        warnningPanel.setLayout(new javax.swing.BoxLayout(warnningPanel, javax.swing.BoxLayout.LINE_AXIS));
        warnningPanel.add(filler17);

        WarningSignTBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        WarningSignTBox.setForeground(new java.awt.Color(255, 0, 0));
        WarningSignTBox.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        WarningSignTBox.setText("Car Temporarily Not Permitted");
        WarningSignTBox.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        WarningSignTBox.setPreferredSize(new java.awt.Dimension(133, 50));
        warnningPanel.add(WarningSignTBox);
        warnningPanel.add(filler14);

        firstPanel.add(warnningPanel);

        reasonPanel.setLayout(new javax.swing.BoxLayout(reasonPanel, javax.swing.BoxLayout.LINE_AXIS));
        reasonPanel.add(filler10);

        jLabel4.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Disallowance Reason");
        jLabel4.setMaximumSize(new java.awt.Dimension(133, 50));
        jLabel4.setMinimumSize(new java.awt.Dimension(133, 50));
        jLabel4.setPreferredSize(new java.awt.Dimension(230, 50));
        reasonPanel.add(jLabel4);
        reasonPanel.add(filler16);

        firstPanel.add(reasonPanel);
        firstPanel.add(filler9);

        disallowReasonPanel.setLayout(new javax.swing.BoxLayout(disallowReasonPanel, javax.swing.BoxLayout.LINE_AXIS));
        disallowReasonPanel.add(filler8);

        disAllowReasonTextField.setEditable(false);
        disAllowReasonTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        disAllowReasonTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        disAllowReasonTextField.setText("012345678901234567890123456789");
        disAllowReasonTextField.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        disAllowReasonTextField.setPreferredSize(new java.awt.Dimension(133, 50));
        disallowReasonPanel.add(disAllowReasonTextField);
        disallowReasonPanel.add(filler15);

        firstPanel.add(disallowReasonPanel);
        firstPanel.add(filler7);

        wholePanel.add(firstPanel);

        buttonPanel.setPreferredSize(new java.awt.Dimension(784, 70));

        openBarButton.setBackground(new java.awt.Color(102, 255, 102));
        openBarButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size+2));
        openBarButton.setMnemonic('o');
        openBarButton.setText("Open Bar");
        openBarButton.setMaximumSize(new java.awt.Dimension(140, 40));
        openBarButton.setMinimumSize(new java.awt.Dimension(140, 40));
        openBarButton.setPreferredSize(new java.awt.Dimension(140, 60));
        openBarButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                openBarButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                openBarButtonFocusLost(evt);
            }
        });
        openBarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBarButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(openBarButton);

        closeGateButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size+2));
        closeGateButton.setMnemonic('c');
        closeGateButton.setText("Close Bar");
        closeGateButton.setMaximumSize(new java.awt.Dimension(140, 40));
        closeGateButton.setMinimumSize(new java.awt.Dimension(140, 40));
        closeGateButton.setPreferredSize(new java.awt.Dimension(140, 60));
        closeGateButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                closeGateButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                closeGateButtonFocusLost(evt);
            }
        });
        closeGateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeGateButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeGateButton);

        wholePanel.add(buttonPanel);

        getContentPane().add(wholePanel, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(516, 446));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void openBarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBarButtonActionPerformed
        if(parent != null){
            parent.raiseGateBar(gateNo, Integer.MAX_VALUE, delay);

            long arrSeqNo = parent.insertDBrecord(gateNo, arrivalTm, tagRecognized, tagEnteredAs,
                    filenameModified, bImg, -1, -1, null, BarOperation.MANUAL);
            timer.cancel();
            timer.purge();
            parent.isGateBusy[gateNo] = false;
            parent.updateMainForm(gateNo, tagRecognized, arrSeqNo, BarOperation.MANUAL);
        }
        dispose();
    }//GEN-LAST:event_openBarButtonActionPerformed

    private void closeGateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeGateButtonActionPerformed
        if(parent != null){  
            long arrSeqNo = parent.insertDBrecord(gateNo, arrivalTm, tagRecognized, tagEnteredAs,
                    filenameModified, bImg,  -1, -1, null, BarOperation.REMAIN_CLOSED);        
            timer.cancel();
            timer.purge();
            parent.isGateBusy[gateNo] = false;
            parent.updateMainForm(gateNo, tagRecognized, arrSeqNo, BarOperation.REMAIN_CLOSED);
        }
        dispose();
    }//GEN-LAST:event_closeGateButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(parent != null){
            long arrSeqNo = parent.insertDBrecord(gateNo, arrivalTm, tagRecognized, tagEnteredAs,
                    filenameModified, bImg, -1, -1, null, BarOperation.REMAIN_CLOSED);   
            timer.cancel();
            timer.purge();
            parent.isGateBusy[gateNo] = false;
            parent.updateMainForm(gateNo, tagRecognized, arrSeqNo, BarOperation.REMAIN_CLOSED);        
        }
    }//GEN-LAST:event_formWindowClosing

    private void closeGateButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_closeGateButtonFocusGained
        // TODO add your handling code here:
        closeGateButton.setBackground((new java.awt.Color(102, 255, 102)));
    }//GEN-LAST:event_closeGateButtonFocusGained

    private void openBarButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_openBarButtonFocusGained
        // TODO add your handling code here:
        openBarButton.setBackground((new java.awt.Color(102, 255, 102)));
    }//GEN-LAST:event_openBarButtonFocusGained

    private void openBarButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_openBarButtonFocusLost
        // TODO add your handling code here:
        openBarButton.setBackground((new java.awt.Color(240, 240, 240)));
    }//GEN-LAST:event_openBarButtonFocusLost

    private void closeGateButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_closeGateButtonFocusLost
        // TODO add your handling code here:
        closeGateButton.setBackground((new java.awt.Color(240, 240, 240)));
    }//GEN-LAST:event_closeGateButtonFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DisAllowedCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DisAllowedCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DisAllowedCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DisAllowedCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        initializeLoggers();
        checkOptions(args);
        readSettings();        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DisAllowedCar(null, "30MO8186", new Date(), "30MO8186", "testing",
                    (byte)1, "abc.jpg", null, null, 8000).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel WarningSignTBox;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeGateButton;
    private javax.swing.JTextField disAllowReasonTextField;
    private javax.swing.JPanel disallowReasonPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler21;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JPanel firstPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton openBarButton;
    private javax.swing.JPanel reasonPanel;
    private javax.swing.JTextField recogTextField;
    private javax.swing.JTextField regisTextField;
    private javax.swing.JPanel tag_Reco_Panel;
    private javax.swing.JPanel tag_Regi_Panel;
    private javax.swing.JPanel warnningPanel;
    private javax.swing.JPanel wholePanel;
    // End of variables declaration//GEN-END:variables
}
