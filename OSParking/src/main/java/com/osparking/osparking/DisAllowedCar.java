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
import static com.osparking.global.Globals.initializeLoggers;

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
        
        // define shortcut keys for 3 buttons
        JComponent pane = (JComponent) this.getContentPane();
        pane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 
                java.awt.event.InputEvent.ALT_DOWN_MASK), "controlTheBar");
        pane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 
                java.awt.event.InputEvent.ALT_DOWN_MASK), "controlTheBar");
        pane.getActionMap().put("controlTheBar", new AbstractAction("controlTheBar") {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                switch (cmd.charAt(0)) {
                    case 'O': case 'o':
                        openBarButtonActionPerformed(null);
                        break;
                    case 'C': case 'c':
                        closeFormButtonActionPerformed(null);
                        break;
                    default:
                        break;
                }
            }
        });
        
        openBarButtonActionPerformed(null);
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

        openBarButton = new javax.swing.JButton();
        closeGateButton = new javax.swing.JButton();
        closeFormButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        recogTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        regisTextField = new javax.swing.JTextField();
        WarningSignTBox = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        disAllowReasonTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        openBarButton.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        openBarButton.setText("Open(O)");
        openBarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBarButtonActionPerformed(evt);
            }
        });

        closeGateButton.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        closeGateButton.setText("Close Bar");
        closeGateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeGateButtonActionPerformed(evt);
            }
        });

        closeFormButton.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        closeFormButton.setText("Close Form");
        closeFormButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeFormButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dotum", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Tag Recognized");

        recogTextField.setEditable(false);
        recogTextField.setFont(new java.awt.Font("Dotum", 1, 18)); // NOI18N
        recogTextField.setText("30MO8186");

        jLabel2.setFont(new java.awt.Font("Dotum", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Tag Registered");

        regisTextField.setEditable(false);
        regisTextField.setFont(new java.awt.Font("Dotum", 1, 18)); // NOI18N
        regisTextField.setText("30MO8186");

        WarningSignTBox.setFont(new java.awt.Font("Dotum", 1, 18)); // NOI18N
        WarningSignTBox.setForeground(new java.awt.Color(255, 0, 0));
        WarningSignTBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WarningSignTBox.setText("Car Temporarily Not Permitted");

        jLabel4.setFont(new java.awt.Font("Dotum", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nonpermission Reason");

        disAllowReasonTextField.setEditable(false);
        disAllowReasonTextField.setFont(new java.awt.Font("Dotum", 1, 18)); // NOI18N
        disAllowReasonTextField.setText("Parking Violation");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(recogTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(regisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(WarningSignTBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(disAllowReasonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(recogTextField)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regisTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WarningSignTBox)
                .addGap(25, 25, 25)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disAllowReasonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(openBarButton)
                        .addGap(18, 18, 18)
                        .addComponent(closeGateButton)
                        .addGap(18, 18, 18)
                        .addComponent(closeFormButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {closeFormButton, closeGateButton, openBarButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openBarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeGateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openBarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBarButtonActionPerformed
        parent.raiseGateBar(gateNo, Integer.MAX_VALUE, delay);
        
        long arrSeqNo = parent.insertDBrecord(gateNo, arrivalTm, tagRecognized, tagEnteredAs,
                filenameModified, bImg, -1, -1, null, BarOperation.MANUAL);
        timer.cancel();
        timer.purge();
        parent.isGateBusy[gateNo] = false;
        parent.updateMainForm(gateNo, tagRecognized, arrSeqNo, BarOperation.MANUAL);
        dispose();
    }//GEN-LAST:event_openBarButtonActionPerformed

    private void closeGateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeGateButtonActionPerformed
        long arrSeqNo = parent.insertDBrecord(gateNo, arrivalTm, tagRecognized, tagEnteredAs,
                filenameModified, bImg,  -1, -1, null, BarOperation.REMAIN_CLOSED);        
        timer.cancel();
        timer.purge();
        parent.isGateBusy[gateNo] = false;
        parent.updateMainForm(gateNo, tagRecognized, arrSeqNo, BarOperation.REMAIN_CLOSED);
        dispose();
    }//GEN-LAST:event_closeGateButtonActionPerformed

    private void closeFormButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeFormButtonActionPerformed
        long arrSeqNo = parent.insertDBrecord(gateNo, arrivalTm, tagRecognized, tagEnteredAs,
                filenameModified, bImg, -1, -1, null, BarOperation.REMAIN_CLOSED);   
        timer.cancel();
        timer.purge();
        parent.isGateBusy[gateNo] = false;
        parent.updateMainForm(gateNo, tagRecognized, arrSeqNo, BarOperation.REMAIN_CLOSED);
        dispose();
    }//GEN-LAST:event_closeFormButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        long arrSeqNo = parent.insertDBrecord(gateNo, arrivalTm, tagRecognized, tagEnteredAs,
                filenameModified, bImg, -1, -1, null, BarOperation.REMAIN_CLOSED);   
        timer.cancel();
        timer.purge();
        parent.isGateBusy[gateNo] = false;
        parent.updateMainForm(gateNo, tagRecognized, arrSeqNo, BarOperation.REMAIN_CLOSED);        
    }//GEN-LAST:event_formWindowClosing

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

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
    private javax.swing.JButton closeFormButton;
    private javax.swing.JButton closeGateButton;
    private javax.swing.JTextField disAllowReasonTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton openBarButton;
    private javax.swing.JTextField recogTextField;
    private javax.swing.JTextField regisTextField;
    // End of variables declaration//GEN-END:variables
}
