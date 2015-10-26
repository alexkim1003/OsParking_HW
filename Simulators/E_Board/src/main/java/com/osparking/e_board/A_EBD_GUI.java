/* 
 * E_Board, Simulator Program--Part of OSParking Software 
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
package com.osparking.e_board;

import com.osparking.deviceglobal.AcceptManagerTask;
import com.osparking.deviceglobal.DeviceGUI;
import static com.osparking.deviceglobal.DeviceGlobals.setIconList;
import com.osparking.global.names.EBD_DisplaySetting;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.osparking.global.names.DeviceReader;
import static com.osparking.global.Globals.*;

import com.osparking.global.names.ParkingTimer;
import com.osparking.global.names.ToleranceLevel;
import static com.osparking.global.names.OSP_enums.EBD_Colors.*;
import static com.osparking.global.names.OSP_enums.EBD_ContentType.*;
import static com.osparking.global.names.OSP_enums.EBD_DisplayUsage.*;
import static com.osparking.global.names.OSP_enums.EBD_Fonts.*;
import static com.osparking.global.names.OSP_enums.EBD_Effects.*;
import static com.osparking.global.names.DB_Access.*;
import com.osparking.global.names.OSP_enums.DeviceType;
import static com.osparking.global.names.OSP_enums.DeviceType.E_Board;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * E-Board Simulator GUI -- Part of OSParking simulator package which is developed by Open Source 
 * Parking Inc.
 * Electronic Board simulator consists of a socket reader and display form module.
 * <p>Company Web Site : <a href="http://www.osparking.com">http://www.osparking.com</a><p>
 * <p>(Company logo: <img src ="doc-files/64px.png"/>)</p>
 * 
 * @author Song, YongSeok <Song, YongSeok at Open Source Parking Inc.>
 */
public class A_EBD_GUI extends javax.swing.JFrame implements DeviceGUI {
    ParkingTimer[] eBoardOuterTimer =  null;
    ParkingTimer[] eBoardInnerTimer = null;
    ParkingTimer[] eBoardRestoreTimer = null;
    
    OuterCycleTask[] outerCycleTask = null;
    InnerCycleTask[] innerCycleTask = null;
    public TimerTask displayDefaultTask = null;
    
    private byte ID = 0;
    
    private ParkingTimer acceptManagerTimer = null;
    private DeviceReader reader = null;
    
    private ToleranceLevel tolerance = new ToleranceLevel();
    private Object socketMUTEX = new Object();
    
    
    public EBD_DisplaySetting defaultDisplaySettings[] = new EBD_DisplaySetting[2]; 
    public int[] prevMsgSN = new int[2]; // the Serial Number of the most recently processed display message
    
    boolean finishingOperation = false;
    
    /**
     * Creates new form Display
     */
    public A_EBD_GUI(byte displayID) {
        initComponents();
        this.ID = displayID;
        
        setResizable(false);
        List<Image> iconList = new ArrayList<Image>();
        String[] iconFilenames = {
            "/e16px.png",             
            "/e32px.png",             
            "/e48px.png",             
            "/e64px.png", 
        };          
        setIconList(iconFilenames, iconList);        
        setIconImages(iconList);              
        
        setTitle("E-Board #" + displayID);
        IDtextField.setText(Integer.toString(displayID));
        
        // put this frme at the top/bottom right corner
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(E_BOARD_WIDTH, E_BOARD_HEIGHT));
        
        if (ID == 1)
            setLocation(new Point(GATE_BAR_WIDTH, 
                    screen.height - E_BOARD_HEIGHT - TASK_BAR_HEIGHT));
        else {
            setLocation(new Point(GATE_BAR_WIDTH * 2 + E_BOARD_WIDTH, 
                    screen.height - E_BOARD_HEIGHT - TASK_BAR_HEIGHT));
        }                     
        String a = null;
            try {
            String myIP = Inet4Address.getLocalHost().getHostAddress();
            IPaddrTextField.setText(myIP);
        } catch (UnknownHostException e) {}
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        PID_Label.setText("(PID:" + processName.substring(0, processName.indexOf("@")) + ")");
        
        eBoardOuterTimer = new ParkingTimer[2];
        eBoardInnerTimer = new ParkingTimer[2];
        eBoardRestoreTimer = new ParkingTimer[2];
        
        for (int rowNum = 0; rowNum <= 1; rowNum++) {
            eBoardOuterTimer[rowNum] = 
                    new ParkingTimer("E_Board" + ID + "_Row" + rowNum + "_outerTimer", false);
            eBoardInnerTimer[rowNum] = 
                    new ParkingTimer("E_Board" + ID + "_Row" + rowNum + "_innerTimer", false);
            eBoardRestoreTimer[rowNum] =
                    new ParkingTimer("E_Board" + ID + "row_" + rowNum + "_displayRestoreTimer", false);
        }        
        
        outerCycleTask = new OuterCycleTask[2];
        innerCycleTask = new InnerCycleTask[2];
        
        defaultDisplaySettings[TOP_ROW] = readEBoardUsageSettings(DEFAULT_TOP_ROW);
        defaultDisplaySettings[BOTTOM_ROW] = readEBoardUsageSettings(DEFAULT_BOTTOM_ROW);        
        
        changeE_BoardDisplay(TOP_ROW, defaultDisplaySettings[TOP_ROW]);
        changeE_BoardDisplay(BOTTOM_ROW, defaultDisplaySettings[BOTTOM_ROW]);
        
        if (DEBUG)
            System.out.println("E Board #" + ID + " started");
        
        reader = new EBoardReader(this);
        reader.start();
        
        /**
         * Start electrical board socket listener.
         */
        acceptManagerTimer = new ParkingTimer("E_BoardAcceptManagerTimer", false, null, 0, PULSE_PERIOD);
        acceptManagerTimer.runOnce(new AcceptManagerTask(this, E_Board));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        torbBG = new javax.swing.ButtonGroup();
        wholePanel = new javax.swing.JPanel();
        topTextField = new javax.swing.JTextField();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        botTextField = new javax.swing.JTextField();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        settingPanel = new javax.swing.JPanel();
        PID_Label1 = new javax.swing.JLabel();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(10, 32767));
        IDtextField = new javax.swing.JTextField();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(10, 32767));
        PID_Label2 = new javax.swing.JLabel();
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(10, 32767));
        IPaddrTextField = new javax.swing.JTextField();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        errorPanel = new javax.swing.JPanel();
        PID_Label = new javax.swing.JLabel();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(10, 32767));
        errorCheckBox = new javax.swing.JCheckBox();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        errIncButton = new javax.swing.JButton();
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        errDecButton = new javax.swing.JButton();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        connectionLED = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        aboutPanel = new javax.swing.JPanel();
        seeLicenseButton = new javax.swing.JButton();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        criticalInfoTextField = new javax.swing.JTextField();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        wholePanel.setLayout(new javax.swing.BoxLayout(wholePanel, javax.swing.BoxLayout.Y_AXIS));

        topTextField.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        topTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        topTextField.setText("E-Board Top Row");
        topTextField.setEnabled(false);
        topTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        topTextField.setVerifyInputWhenFocusTarget(false);
        wholePanel.add(topTextField);
        wholePanel.add(filler6);

        botTextField.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        botTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        botTextField.setText("E-Board Bottom Row");
        botTextField.setEnabled(false);
        botTextField.setPreferredSize(new java.awt.Dimension(300, 35));
        wholePanel.add(botTextField);
        wholePanel.add(filler7);

        settingPanel.setLayout(new javax.swing.BoxLayout(settingPanel, javax.swing.BoxLayout.LINE_AXIS));

        PID_Label1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        PID_Label1.setText("ID");
        settingPanel.add(PID_Label1);
        settingPanel.add(filler17);

        IDtextField.setEditable(false);
        IDtextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        IDtextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IDtextField.setText("1");
        IDtextField.setEnabled(false);
        IDtextField.setMaximumSize(new java.awt.Dimension(30, 28));
        IDtextField.setMinimumSize(new java.awt.Dimension(30, 28));
        IDtextField.setPreferredSize(new java.awt.Dimension(30, 28));
        settingPanel.add(IDtextField);
        settingPanel.add(filler16);

        PID_Label2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        PID_Label2.setText("E-Board IP");
        settingPanel.add(PID_Label2);
        settingPanel.add(filler19);

        IPaddrTextField.setEditable(false);
        IPaddrTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        IPaddrTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IPaddrTextField.setText("127.0.0.1");
        IPaddrTextField.setEnabled(false);
        IPaddrTextField.setMaximumSize(new java.awt.Dimension(150, 28));
        IPaddrTextField.setMinimumSize(new java.awt.Dimension(150, 28));
        IPaddrTextField.setPreferredSize(new java.awt.Dimension(150, 28));
        settingPanel.add(IPaddrTextField);
        settingPanel.add(filler12);

        wholePanel.add(settingPanel);
        wholePanel.add(filler8);

        errorPanel.setMaximumSize(new java.awt.Dimension(300, 38));
        errorPanel.setMinimumSize(new java.awt.Dimension(300, 38));
        errorPanel.setPreferredSize(new java.awt.Dimension(300, 30));
        errorPanel.setLayout(new javax.swing.BoxLayout(errorPanel, javax.swing.BoxLayout.LINE_AXIS));

        PID_Label.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        PID_Label.setText("(PID)");
        errorPanel.add(PID_Label);
        errorPanel.add(filler18);

        errorCheckBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        errorCheckBox.setText("error");
        errorCheckBox.setMaximumSize(new java.awt.Dimension(100, 28));
        errorCheckBox.setMinimumSize(new java.awt.Dimension(100, 28));
        errorCheckBox.setPreferredSize(new java.awt.Dimension(100, 28));
        errorCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorCheckBoxActionPerformed(evt);
            }
        });
        errorPanel.add(errorCheckBox);
        errorPanel.add(filler13);

        errIncButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        errIncButton.setIcon(getPlusIcon());
        errIncButton.setBorderPainted(false);
        errIncButton.setContentAreaFilled(false);
        errIncButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        errIncButton.setMaximumSize(new java.awt.Dimension(20, 20));
        errIncButton.setMinimumSize(new java.awt.Dimension(20, 20));
        errIncButton.setPreferredSize(new java.awt.Dimension(20, 20));
        errorPanel.add(errIncButton);
        errIncButton.setBorder(null);
        errIncButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errIncButtonActionPerformed(e);
            }

            private void errIncButtonActionPerformed(ActionEvent e) {
                if (errorCheckBox.isSelected()) {
                    if (ERROR_RATE < 0.9) {
                        ERROR_RATE += 0.1f;
                    } else {
                        criticalInfoTextField.setText("current error rate(="
                            + getFormattedRealNumber(ERROR_RATE, 2) + ") is max!");
                    }
                    errorCheckBox.setText("error : " + getFormattedRealNumber(ERROR_RATE, 2));
                } else {
                    criticalInfoTextField.setText("First, select error check box, OK?");
                }
            }
        });
        errorPanel.add(filler14);

        errDecButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        errDecButton.setIcon(getMinusIcon());
        errDecButton.setBorderPainted(false);
        errDecButton.setContentAreaFilled(false);
        errDecButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        errDecButton.setMaximumSize(new java.awt.Dimension(20, 20));
        errDecButton.setMinimumSize(new java.awt.Dimension(20, 20));
        errDecButton.setPreferredSize(new java.awt.Dimension(20, 20));
        errorPanel.add(errDecButton);
        errDecButton.setBorder(null);
        errDecButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errDecButtonActionPerformed(e);
            }

            private void errDecButtonActionPerformed(ActionEvent e) {
                if (errorCheckBox.isSelected()) {
                    if (ERROR_RATE > 0.10) {
                        ERROR_RATE -= 0.1f;
                    } else {
                        criticalInfoTextField.setText("current error rate(="
                            + getFormattedRealNumber(ERROR_RATE, 2) + ") is min!");
                    }
                    errorCheckBox.setText("error : " + getFormattedRealNumber(ERROR_RATE, 2));
                } else {
                    criticalInfoTextField.setText("First, select error check box, OK?");
                }
            }
        });
        errorPanel.add(filler15);

        connectionLED.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        connectionLED.setForeground(new java.awt.Color(255, 0, 0));
        connectionLED.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectionLED.setText("X");
        connectionLED.setToolTipText("");
        connectionLED.setPreferredSize(new java.awt.Dimension(25, 25));
        errorPanel.add(connectionLED);
        errorPanel.add(filler1);

        wholePanel.add(errorPanel);
        wholePanel.add(filler9);

        aboutPanel.setLayout(new javax.swing.BoxLayout(aboutPanel, javax.swing.BoxLayout.LINE_AXIS));

        seeLicenseButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        seeLicenseButton.setText("About");
        seeLicenseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeLicenseButtonActionPerformed(evt);
            }
        });
        aboutPanel.add(seeLicenseButton);
        aboutPanel.add(filler11);

        wholePanel.add(aboutPanel);
        wholePanel.add(filler10);

        criticalInfoTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size-2));
        criticalInfoTextField.setMaximumSize(new java.awt.Dimension(2147483647, 28));
        criticalInfoTextField.setMinimumSize(new java.awt.Dimension(6, 28));
        criticalInfoTextField.setPreferredSize(new java.awt.Dimension(6, 28));
        wholePanel.add(criticalInfoTextField);

        getContentPane().add(wholePanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(filler3, java.awt.BorderLayout.NORTH);
        getContentPane().add(filler2, java.awt.BorderLayout.WEST);
        getContentPane().add(filler4, java.awt.BorderLayout.EAST);
        getContentPane().add(filler5, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        finishingOperation = true;
        closeSocket(reader.getManagerSocket(), "form window is closing");
        if (acceptManagerTimer != null)
            acceptManagerTimer.cancel();
        
        if (reader != null)
            reader.stopOperation("form window is closing");
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void seeLicenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeLicenseButtonActionPerformed
        showLicensePanel(this, "License Notice on E-Board Simulator");
    }//GEN-LAST:event_seeLicenseButtonActionPerformed

    private void errorCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorCheckBoxActionPerformed
        // TODO add your handling code here:
        if(!errorCheckBox.isSelected())
            errorCheckBox.setText("error");
        else
            errorCheckBox.setText("error : " + getFormattedRealNumber(ERROR_RATE, 2));
    }//GEN-LAST:event_errorCheckBoxActionPerformed
    
    public static int getStrHeight(JTextField jTextField){
        int TextHeight;
        
        TextHeight = jTextField.getFont().getSize();
        
        return TextHeight;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
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
            java.util.logging.Logger.getLogger(A_EBD_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(A_EBD_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(A_EBD_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(A_EBD_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        initializeLoggers();
        checkOptions(args);
        readSettings();        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Byte displayID = getUniqueGateBarID(DeviceType.E_Board);

                if (displayID > gateCount ) {
                    JOptionPane.showMessageDialog(null, 
                            "Currently " + gateCount + " E-Board programs are running" + System.lineSeparator() 
                            + "No more e-board programs can run", "E-Board Count Exceeded", 
                            JOptionPane.OK_OPTION);
                    return;
                }

                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);                
                A_EBD_GUI mainGUI = new A_EBD_GUI(displayID);
                mainGUI.setVisible(true);
                shortLicenseDialog(mainGUI, "E-Board Simulator Program", "upper left");                
            }
        });
    }
    
    /**
     * @return the criticalInfoTextField
     */
    public javax.swing.JTextField getCriticalInfoTextField() {
        return criticalInfoTextField;
    }
    
    
    public void carEnter(){
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField IDtextField;
    public javax.swing.JTextField IPaddrTextField;
    private javax.swing.JLabel PID_Label;
    private javax.swing.JLabel PID_Label1;
    private javax.swing.JLabel PID_Label2;
    private javax.swing.JPanel aboutPanel;
    public javax.swing.JTextField botTextField;
    public javax.swing.JLabel connectionLED;
    public javax.swing.JTextField criticalInfoTextField;
    private javax.swing.JButton errDecButton;
    private javax.swing.JButton errIncButton;
    javax.swing.JCheckBox errorCheckBox;
    private javax.swing.JPanel errorPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton seeLicenseButton;
    private javax.swing.JPanel settingPanel;
    public javax.swing.JTextField topTextField;
    private javax.swing.ButtonGroup torbBG;
    private javax.swing.JPanel wholePanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public byte getID() {
        return ID;
    }

    @Override
    public JTextField getManagerIPaddr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ToleranceLevel getTolerance() {
        return tolerance;
    }

    public void setTolerance(ToleranceLevel tolerance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JTextArea getMessageTextArea() {
        return null;
    }

    @Override
    public Object getSocketConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDeviceType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getSocketMUTEX() {
        return socketMUTEX;
    }

    @Override
    public DeviceReader getReader() {
        return reader;
    }

    @Override
    public boolean isSHUT_DOWN() {
        return finishingOperation;
    }

    synchronized void  changeE_BoardDisplay(byte row, EBD_DisplaySetting rowSetting)
    {
        JTextField rowTextField = (row == TOP_ROW ? topTextField : botTextField);
         
        rowTextField.setText(rowSetting.verbatimContent);
        
        //<editor-fold desc="-- set new font">
        int currStyle = rowTextField.getFont().getStyle();
        int currSize = rowTextField.getFont().getSize();
        
        switch (rowSetting.textFont) {
            case Dialog:
                rowTextField.setFont(new Font("Dialog", currStyle, currSize));
                break;
                
            case Microsoft_NeoGothic:
                rowTextField.setFont(new Font("Microsoft NeoGothic", currStyle, currSize));
                break;
                
            case Monospaced:
                rowTextField.setFont(new Font("Monospaced", currStyle, currSize));
                break;
                
            case Sans_Serif:
                rowTextField.setFont(new Font("Sans Serif", currStyle, currSize));
                break;
                
            default:
                break;
        }   
        //</editor-fold>        
        
        if (eBoardOuterTimer[row].hasTask()) {
            eBoardOuterTimer[row].cancelTask();
        }
    
        ParkingTimer innerTaskTimer = eBoardInnerTimer[row]; 
        if (innerTaskTimer.hasTask()) {
            innerTaskTimer.cancelTask();
        }        
        
        if (rowSetting.displayPattern == STILL_FRAME && rowSetting.contentType == VERBATIM) {
            rowTextField.setMargin(new Insets(2, 2, 2, 2) );            
            rowTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        } else {
            outerCycleTask[row] = new OuterCycleTask(this, row, rowSetting);
            if (rowSetting.displayPattern == BLINKING) {
                rowTextField.setMargin(new Insets(2, 2, 2, 2) );            
                rowTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);            
                eBoardOuterTimer[row].reschedule(outerCycleTask[row], 0, rowSetting.displayCycle);
            } else {
                eBoardOuterTimer[row].runOnce(outerCycleTask[row]);
            }
        }
        
        //<editor-fold desc="-- set text color">
        switch (rowSetting.textColor) {
            case RED : rowTextField.setDisabledTextColor(new java.awt.Color(255, 0, 0)); break;
            case ORANGE : rowTextField.setDisabledTextColor(new java.awt.Color(255, 125, 0)); break;
            case GREEN : rowTextField.setDisabledTextColor(new java.awt.Color(0, 175, 56)); break;
            case BLACK : rowTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0)); break;
            case BLUE : rowTextField.setDisabledTextColor(new java.awt.Color(0, 0, 255)); break;
        }
        //</editor-fold>
    }

    /**
     * @return the acceptManagerTimer
     */
    public ParkingTimer getAcceptManagerTimer() {
        return acceptManagerTimer;
    }

    /**
     * @param deviceReader the deviceReader to set
     */
    public void setReader(DeviceReader deviceReader) {
        this.reader = deviceReader;
    }

    /**
     * 
     * @return a timer which restores e-board to default messages
     */
    public ParkingTimer[] getDisplayRestoreTimer() {
        return eBoardRestoreTimer;
    }

    /**
     * @return the defaultDisplaySettings
     */
    public EBD_DisplaySetting[] getDefaultDisplaySettings() {
        return defaultDisplaySettings;
    }

    @Override
    public JLabel getConnectionLED() {
        return connectionLED;
    }
}