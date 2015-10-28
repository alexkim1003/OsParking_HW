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

import com.osparking.vehicle.driver.ManageDrivers;
import static com.osparking.vehicle.driver.ManageDrivers.initAffiliationComboBoxes;
import static com.osparking.vehicle.driver.ManageDrivers.loadComboBoxItems;
import static com.osparking.vehicle.driver.ManageDrivers.loadUnitComboBox;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.MutableComboBoxModel;
import com.osparking.global.names.OSP_enums.BarOperation;
import com.osparking.global.names.ConvComboBoxItem;
import static com.osparking.global.names.DB_Access.readSettings;
import static com.osparking.global.Globals.checkOptions;
import static com.osparking.global.Globals.getTopLeftPointToPutThisFrameAtScreenCenter;
import static com.osparking.global.Globals.OSPiconList;
import static com.osparking.global.Globals.font_Size;
import static com.osparking.global.Globals.font_Style;
import static com.osparking.global.Globals.font_Type;
import static com.osparking.global.Globals.initializeLoggers;
import static com.osparking.global.names.DB_Access.gateNames;
import com.osparking.global.names.InnoComboBoxItem;
import com.osparking.global.names.OSP_enums.DriverCol;
import static com.osparking.global.names.OSP_enums.DriverCol.AffiliationL1;
import static com.osparking.global.names.OSP_enums.DriverCol.AffiliationL2;
import static com.osparking.global.names.OSP_enums.DriverCol.BuildingNo;
import static com.osparking.global.names.OSP_enums.DriverCol.UnitNo;
import com.osparking.global.names.PComboBox;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;

/**
 *
 * @author Park, Jongbum <Park, Jongbum at Open Source Parking Inc.>
 */
public class VisitingCar extends javax.swing.JFrame {
    ControlGUI parent = null;
    String tagRecognized;
    Date arrivalTime;
    byte gateNo;
    String filename;
    String filenameModified;
    BufferedImage bImg;
    int delay;
    /**
     * Creates new form VisitingCar
     */
    public VisitingCar(ControlGUI parent, String tagRecognized, Date arrivalTime, byte gateNo, 
            String filename, String filenameModified, BufferedImage bImg, int delay) {
        
        initComponents();
        setIconImages(OSPiconList);
        Point screenCenter = getTopLeftPointToPutThisFrameAtScreenCenter(this);
        setLocation(screenCenter);    
        
        this.parent = parent;
        this.tagRecognized = tagRecognized; 
        this.arrivalTime = arrivalTime; 
        this.gateNo = gateNo; 
        this.filename = filename; 
        this.filenameModified = filenameModified; 
        this.bImg = bImg;
        this.delay = delay;

        recogTextField.setText(tagRecognized);
        visitTimeTextField.setText("'" + new SimpleDateFormat ("a hh:mm:ss").
                format(arrivalTime));
        
        initAffiliationComboBoxes(highLevelComboBox, lowLevelComboBox, 
                buildingComboBox, unitComboBox);
        visitReasonTextField.setText("");
        gateNameTextField.setText(gateNames[gateNo]);
        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                openBarButton.requestFocus();
            }
        });  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wholePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        filler28 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        gateNameTextField = new javax.swing.JTextField();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filler26 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        recogTextField = new javax.swing.JTextField();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jLabel8 = new javax.swing.JLabel();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        visitTimeTextField = new javax.swing.JTextField();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        highLevelComboBox = new PComboBox();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lowLevelComboBox = new PComboBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        buildingComboBox = new PComboBox();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        filler21 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        unitComboBox = new PComboBox();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel8 = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 0), new java.awt.Dimension(80, 32767));
        visitReasonTextField = new javax.swing.JTextField();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel4 = new javax.swing.JPanel();
        openBarButton = new javax.swing.JButton();
        notAllowButton = new javax.swing.JButton();
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        filler24 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        filler25 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visitor Information Entry");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        wholePanel.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        wholePanel.setLayout(new javax.swing.BoxLayout(wholePanel, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel6.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Gate");
        jLabel6.setMaximumSize(null);
        jLabel6.setMinimumSize(new java.awt.Dimension(160, 50));
        jLabel6.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel5.add(jLabel6);
        jPanel5.add(filler28);

        gateNameTextField.setEditable(false);
        gateNameTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size+11));
        gateNameTextField.setText("gateNameTextField");
        gateNameTextField.setMaximumSize(new java.awt.Dimension(210, 50));
        gateNameTextField.setMinimumSize(new java.awt.Dimension(210, 50));
        gateNameTextField.setPreferredSize(new java.awt.Dimension(210, 50));
        jPanel5.add(gateNameTextField);
        jPanel5.add(filler8);
        jPanel5.add(filler10);

        jPanel1.add(jPanel5);
        jPanel1.add(filler4);

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Recognized");
        jLabel1.setMaximumSize(null);
        jLabel1.setMinimumSize(new java.awt.Dimension(160, 50));
        jLabel1.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel6.add(jLabel1);
        jPanel6.add(filler26);

        recogTextField.setEditable(false);
        recogTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size+12));
        recogTextField.setText("12가3456");
        recogTextField.setMaximumSize(null);
        recogTextField.setMinimumSize(new java.awt.Dimension(210, 50));
        recogTextField.setPreferredSize(new java.awt.Dimension(210, 50));
        jPanel6.add(recogTextField);
        jPanel6.add(filler16);

        jLabel8.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Visit Time");
        jLabel8.setMaximumSize(null);
        jLabel8.setMinimumSize(new java.awt.Dimension(130, 50));
        jLabel8.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanel6.add(jLabel8);
        jPanel6.add(filler11);

        visitTimeTextField.setEditable(false);
        visitTimeTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size+11));
        visitTimeTextField.setText("'15.02.05 13:27:04");
        visitTimeTextField.setMaximumSize(new java.awt.Dimension(180, 50));
        visitTimeTextField.setMinimumSize(new java.awt.Dimension(180, 50));
        visitTimeTextField.setPreferredSize(new java.awt.Dimension(210, 50));
        jPanel6.add(visitTimeTextField);
        jPanel6.add(filler9);

        jPanel1.add(jPanel6);
        jPanel1.add(filler15);

        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel2.setForeground(new java.awt.Color(18, 22, 113));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Visit Purpose");
        jLabel2.setMaximumSize(null);
        jLabel2.setMinimumSize(new java.awt.Dimension(130, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(130, 50));
        jPanel10.add(jLabel2);
        jPanel10.add(filler1);

        jPanel1.add(jPanel10);
        jPanel1.add(filler14);

        jPanel7.setMaximumSize(new java.awt.Dimension(65676, 130));
        jPanel7.setPreferredSize(new java.awt.Dimension(1001, 130));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setPreferredSize(new java.awt.Dimension(201, 99));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Higher Affiliation");
        jLabel3.setMaximumSize(null);
        jLabel3.setMinimumSize(new java.awt.Dimension(160, 50));
        jLabel3.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel11.add(jLabel3);
        jPanel11.add(filler18);

        highLevelComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        highLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        highLevelComboBox.setMaximumSize(null);
        highLevelComboBox.setMinimumSize(new java.awt.Dimension(210, 50));
        highLevelComboBox.setPreferredSize(new java.awt.Dimension(210, 50));
        highLevelComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                highLevelComboBoxPopupMenuWillBecomeVisible(evt);
            }
        });
        highLevelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highLevelComboBoxActionPerformed(evt);
            }
        });
        jPanel11.add(highLevelComboBox);

        jPanel2.add(jPanel11);
        jPanel2.add(filler7);

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Lower Affiliation");
        jLabel5.setMaximumSize(null);
        jLabel5.setMinimumSize(new java.awt.Dimension(160, 50));
        jLabel5.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanel12.add(jLabel5);
        jPanel12.add(filler19);

        lowLevelComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        lowLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        lowLevelComboBox.setMaximumSize(null);
        lowLevelComboBox.setMinimumSize(new java.awt.Dimension(210, 50));
        lowLevelComboBox.setPreferredSize(new java.awt.Dimension(210, 50));
        lowLevelComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                lowLevelComboBoxPopupMenuWillBecomeVisible(evt);
            }
        });
        jPanel12.add(lowLevelComboBox);

        jPanel2.add(jPanel12);

        jPanel7.add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(161, 99));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

        jLabel9.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Building");
        jLabel9.setMaximumSize(null);
        jLabel9.setMinimumSize(new java.awt.Dimension(130, 50));
        jLabel9.setPreferredSize(new java.awt.Dimension(130, 50));
        jPanel13.add(jLabel9);
        jPanel13.add(filler20);

        buildingComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        buildingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        buildingComboBox.setMaximumSize(null);
        buildingComboBox.setMinimumSize(new java.awt.Dimension(100, 50));
        buildingComboBox.setPreferredSize(new java.awt.Dimension(100, 50));
        buildingComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                buildingComboBoxPopupMenuWillBecomeVisible(evt);
            }
        });
        buildingComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildingComboBoxActionPerformed(evt);
            }
        });
        jPanel13.add(buildingComboBox);

        jPanel3.add(jPanel13);
        jPanel3.add(filler12);

        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Room/Unit");
        jLabel10.setMaximumSize(null);
        jLabel10.setMinimumSize(new java.awt.Dimension(130, 50));
        jLabel10.setPreferredSize(new java.awt.Dimension(130, 50));
        jPanel14.add(jLabel10);
        jPanel14.add(filler21);

        unitComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        unitComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        unitComboBox.setMaximumSize(null);
        unitComboBox.setMinimumSize(new java.awt.Dimension(100, 50));
        unitComboBox.setPreferredSize(new java.awt.Dimension(100, 50));
        unitComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                unitComboBoxPopupMenuWillBecomeVisible(evt);
            }
        });
        jPanel14.add(unitComboBox);

        jPanel3.add(jPanel14);

        jPanel7.add(jPanel3);

        jPanel1.add(jPanel7);
        jPanel1.add(filler13);

        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        jLabel4.setForeground(new java.awt.Color(18, 22, 113));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Detailed Reason");
        jLabel4.setMaximumSize(null);
        jLabel4.setMinimumSize(new java.awt.Dimension(130, 50));
        jLabel4.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel9.add(jLabel4);
        jPanel9.add(filler2);

        jPanel1.add(jPanel9);
        jPanel1.add(filler5);

        jPanel8.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        jPanel8.setPreferredSize(new java.awt.Dimension(1001, 50));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));
        jPanel8.add(filler3);

        visitReasonTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        visitReasonTextField.setText("Visit coffee shop \"Star Coffee Bean\"");
        visitReasonTextField.setMaximumSize(null);
        visitReasonTextField.setMinimumSize(new java.awt.Dimension(6, 50));
        visitReasonTextField.setPreferredSize(new java.awt.Dimension(207, 50));
        jPanel8.add(visitReasonTextField);

        jPanel1.add(jPanel8);
        jPanel1.add(filler6);

        wholePanel.add(jPanel1);

        openBarButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        openBarButton.setMnemonic('o');
        openBarButton.setText("Open Bar");
        openBarButton.setMaximumSize(new java.awt.Dimension(140, 60));
        openBarButton.setMinimumSize(new java.awt.Dimension(140, 60));
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
        openBarButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                openBarButtonKeyTyped(evt);
            }
        });
        jPanel4.add(openBarButton);

        notAllowButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size+6));
        notAllowButton.setMnemonic('c');
        notAllowButton.setText("Close Bar");
        notAllowButton.setMaximumSize(new java.awt.Dimension(140, 60));
        notAllowButton.setMinimumSize(new java.awt.Dimension(140, 60));
        notAllowButton.setPreferredSize(new java.awt.Dimension(140, 60));
        notAllowButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                notAllowButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                notAllowButtonFocusLost(evt);
            }
        });
        notAllowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notAllowButtonActionPerformed(evt);
            }
        });
        notAllowButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                notAllowButtonKeyTyped(evt);
            }
        });
        jPanel4.add(notAllowButton);

        wholePanel.add(jPanel4);

        getContentPane().add(wholePanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(filler22, java.awt.BorderLayout.PAGE_START);
        getContentPane().add(filler23, java.awt.BorderLayout.PAGE_END);
        getContentPane().add(filler24, java.awt.BorderLayout.EAST);
        getContentPane().add(filler25, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openBarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBarButtonActionPerformed
        welcomeVisitor(true);
    }//GEN-LAST:event_openBarButtonActionPerformed

    private void notAllowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notAllowButtonActionPerformed
        welcomeVisitor(false);
    }//GEN-LAST:event_notAllowButtonActionPerformed

    @SuppressWarnings("unchecked") 
    private void highLevelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highLevelComboBoxActionPerformed
        if (highLevelComboBox.isPopupVisible()) {
            MutableComboBoxModel model 
                    = (MutableComboBoxModel)lowLevelComboBox.getModel();
            model.removeElementAt(0);
//            model.insertElementAt(getPrompter(AffiliationL2), 0);
            model.insertElementAt(ManageDrivers.getPrompter(AffiliationL2, highLevelComboBox), 0);
            lowLevelComboBox.setSelectedIndex(0);            
        }        
    }//GEN-LAST:event_highLevelComboBoxActionPerformed

    @SuppressWarnings("unchecked") 
    private void highLevelComboBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_highLevelComboBoxPopupMenuWillBecomeVisible
        Object selItem = highLevelComboBox.getSelectedItem();

        highLevelComboBox.removeAllItems();
        highLevelComboBox.addItem(ManageDrivers.getPrompter(AffiliationL1, highLevelComboBox));     
        loadComboBoxItems(highLevelComboBox, AffiliationL1, -1);
        highLevelComboBox.setSelectedItem(selItem);         
    }//GEN-LAST:event_highLevelComboBoxPopupMenuWillBecomeVisible

    @SuppressWarnings("unchecked") 
    private void lowLevelComboBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_lowLevelComboBoxPopupMenuWillBecomeVisible
        Object selItem = lowLevelComboBox.getSelectedItem();
        
        ConvComboBoxItem l1Item = (ConvComboBoxItem)highLevelComboBox.getSelectedItem(); 
        int L1No = (Integer) l1Item.getValue();        // normalize child combobox item 
        lowLevelComboBox.removeAllItems();
        lowLevelComboBox.addItem(ManageDrivers.getPrompter(AffiliationL2, highLevelComboBox));     
        loadComboBoxItems(lowLevelComboBox, DriverCol.AffiliationL2, L1No);        
        lowLevelComboBox.setSelectedItem(selItem);           
    }//GEN-LAST:event_lowLevelComboBoxPopupMenuWillBecomeVisible

    @SuppressWarnings("unchecked") 
    private void buildingComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildingComboBoxActionPerformed
        if (buildingComboBox.isPopupVisible()) {
            MutableComboBoxModel model 
                    = (MutableComboBoxModel)unitComboBox.getModel();
            model.removeElementAt(0);
            model.insertElementAt(ManageDrivers.getPrompter(UnitNo, buildingComboBox), 0);
            unitComboBox.setSelectedIndex(0);            
        }
    }//GEN-LAST:event_buildingComboBoxActionPerformed

    @SuppressWarnings("unchecked") 
    private void buildingComboBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_buildingComboBoxPopupMenuWillBecomeVisible
        Object selItem = buildingComboBox.getSelectedItem();
        
        buildingComboBox.removeAllItems();
        buildingComboBox.addItem(ManageDrivers.getPrompter(BuildingNo, null));     
        loadComboBoxItems(buildingComboBox, BuildingNo, -1);
        buildingComboBox.setSelectedItem(selItem);         
    }//GEN-LAST:event_buildingComboBoxPopupMenuWillBecomeVisible

    private void unitComboBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_unitComboBoxPopupMenuWillBecomeVisible
        loadUnitComboBox(highLevelComboBox, buildingComboBox, unitComboBox);               
    }//GEN-LAST:event_unitComboBoxPopupMenuWillBecomeVisible

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(parent != null){
            long arrSeqNo = parent.insertDBrecord(gateNo, arrivalTime, tagRecognized, null,
                filenameModified, bImg, -1, -1, null, BarOperation.REMAIN_CLOSED);
            parent.isGateBusy[gateNo] = false;        
            parent.updateMainForm(gateNo, tagRecognized, arrSeqNo, BarOperation.REMAIN_CLOSED);
        }
        dispose();        
    }//GEN-LAST:event_formWindowClosing

    private void openBarButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_openBarButtonFocusGained
        // TODO add your handling code here:
        openBarButton.setBackground((new java.awt.Color(102, 255, 102)));
    }//GEN-LAST:event_openBarButtonFocusGained

    private void openBarButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_openBarButtonFocusLost
        // TODO add your handling code here:
        openBarButton.setBackground((new java.awt.Color(240, 240, 240)));
    }//GEN-LAST:event_openBarButtonFocusLost

    private void openBarButtonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_openBarButtonKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            openBarButtonActionPerformed(null);
        }
    }//GEN-LAST:event_openBarButtonKeyTyped

    private void notAllowButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notAllowButtonFocusGained
        // TODO add your handling code here:
        notAllowButton.setBackground((new java.awt.Color(102, 255, 102)));
    }//GEN-LAST:event_notAllowButtonFocusGained

    private void notAllowButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notAllowButtonFocusLost
        // TODO add your handling code here:
        notAllowButton.setBackground((new java.awt.Color(240, 240, 240)));
    }//GEN-LAST:event_notAllowButtonFocusLost

    private void notAllowButtonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_notAllowButtonKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            notAllowButtonActionPerformed(null);
        }
    }//GEN-LAST:event_notAllowButtonKeyTyped

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
            java.util.logging.Logger.getLogger(VisitingCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisitingCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisitingCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisitingCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        initializeLoggers();
        checkOptions(args);
        readSettings();        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisitingCar(null, "12가3456", new Date(), (byte)1, "testImage.jpg", 
                        "2015-05-02_18-59-45_testImage.jpg", null, 8000).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox buildingComboBox;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler21;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler24;
    private javax.swing.Box.Filler filler25;
    private javax.swing.Box.Filler filler26;
    private javax.swing.Box.Filler filler28;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JTextField gateNameTextField;
    private javax.swing.JComboBox highLevelComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JComboBox lowLevelComboBox;
    private javax.swing.JButton notAllowButton;
    private javax.swing.JButton openBarButton;
    private javax.swing.JTextField recogTextField;
    private javax.swing.JComboBox unitComboBox;
    private javax.swing.JTextField visitReasonTextField;
    private javax.swing.JTextField visitTimeTextField;
    private javax.swing.JPanel wholePanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Operate the gate bar if ordered so.
     * Store the visitor arrival record into the database.
     * @param openGate when true, open the gate bar to welcome the visitor.
     *                                  otherwise, let the bar remain closed and disallow the visitor to enter.
     */
    private void welcomeVisitor(boolean openGate) {
        int unitSeqNo;
        int l2No;
        if(parent != null){
            if (openGate) {
                parent.raiseGateBar(gateNo, Integer.MAX_VALUE, delay);
            }

            if (lowLevelComboBox.getSelectedIndex() == -1) {
                l2No = -1;
            } else {
                l2No = (Integer)
                        ((InnoComboBoxItem)lowLevelComboBox.getSelectedItem()).getKeys()[0];
            }
            if (unitComboBox.getSelectedIndex() == -1) {
                unitSeqNo = -1;
            } else {
                unitSeqNo = (Integer)
                        ((InnoComboBoxItem)unitComboBox.getSelectedItem()).getKeys()[0];
            }
            BarOperation barOperation = BarOperation.MANUAL;
            if (!openGate) {
                barOperation = BarOperation.REMAIN_CLOSED;
            }
            String reason = visitReasonTextField.getText();
            long arrSeqNo = parent.insertDBrecord(gateNo, arrivalTime, tagRecognized, null, filenameModified, 
                    bImg, unitSeqNo, l2No, reason.length() == 0 ? null : reason , barOperation);
            parent.updateMainForm(gateNo, tagRecognized, arrSeqNo, barOperation);        
            parent.isGateBusy[gateNo] = false; 
        }
        dispose();
    }
}
