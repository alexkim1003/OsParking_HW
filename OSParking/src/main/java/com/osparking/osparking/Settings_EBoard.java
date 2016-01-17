/* 
 * Copyright (C) 2015 Open Source Parking Inc.(www.osparking.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.osparking.osparking;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static com.osparking.global.names.DB_Access.readEBoardSettings;
import com.osparking.global.Globals;
import static com.osparking.global.Globals.*;
<<<<<<< HEAD
=======
import com.osparking.global.names.ControlEnums;
import static com.osparking.global.names.ControlEnums.ButtonTypes.*;
import static com.osparking.global.names.ControlEnums.ComboBoxItemTypes.*;
import static com.osparking.global.names.ControlEnums.DialogMSGTypes.*;
import static com.osparking.global.names.ControlEnums.DialogTitleTypes.*;
import static com.osparking.global.names.ControlEnums.LabelTypes.*;
import static com.osparking.global.names.ControlEnums.TitleTypes.*;
import com.osparking.global.names.ChangeSettings;
>>>>>>> osparking/master
import com.osparking.global.names.ConvComboBoxItem;
import com.osparking.global.names.JDBCMySQL;
import com.osparking.global.names.OSP_enums;
import static com.osparking.global.names.OSP_enums.DeviceType.E_Board;
import com.osparking.global.names.OSP_enums.EBD_Colors;
import com.osparking.global.names.OSP_enums.EBD_ContentType;
import com.osparking.global.names.OSP_enums.EBD_DisplayUsage;
import static com.osparking.global.names.OSP_enums.EBD_DisplayUsage.*;
import com.osparking.global.names.OSP_enums.EBD_Fonts;
import com.osparking.global.names.OSP_enums.EBD_Effects;
import static com.osparking.osparking.device.EBoardManager.sendEBoardDefaultSetting;
import static com.osparking.global.names.DB_Access.gateCount;
<<<<<<< HEAD
import com.osparking.global.names.OSP_enums.FormMode;
import com.osparking.global.names.OSP_enums.OpLogLevel;
import com.osparking.global.names.IDevice;
=======
import com.osparking.global.names.OSP_enums.DisplayArea;
import static com.osparking.global.names.OSP_enums.DisplayArea.BOTTOM_ROW;
import static com.osparking.global.names.OSP_enums.DisplayArea.TOP_ROW;
import com.osparking.global.names.OSP_enums.FormMode;
import com.osparking.global.names.OSP_enums.OpLogLevel;
import java.util.HashSet;
>>>>>>> osparking/master

/**
 *
 * @author YongSeok
 */
public class Settings_EBoard extends javax.swing.JFrame {
    public static ControlGUI mainForm = null;
<<<<<<< HEAD
    private HashMap<String,Component> componentMap = new HashMap<String,Component>();
    private EBD_DisplayUsage currentTab = DEFAULT_TOP_ROW, previousTab = DEFAULT_TOP_ROW;
    Settings_System parent = null;
    FormMode formMode = FormMode.SEARCHING;
=======
    private HashMap<String,Component> componentMap;
    private EBD_DisplayUsage currentTab = DEFAULT_TOP_ROW, previousTab = DEFAULT_TOP_ROW;
    Settings_System parent = null;
    OSP_enums.FormMode formMode = OSP_enums.FormMode.SEARCHING;
    private HashSet<Component> changedControls = new HashSet<Component>();
>>>>>>> osparking/master
    /**
     * Creates new form TestDisplay
     */
    public Settings_EBoard(ControlGUI mainForm, Settings_System parent) {
        initComponents();
        this.mainForm = mainForm;
        this.parent = parent;
        setResizable(false);
        
<<<<<<< HEAD
=======
        componentMap = new HashMap<String,Component>();
>>>>>>> osparking/master
        augmentComponentMap(this, componentMap);
        
        addContentTypeItems();
        addDisplayEffectItems();
        addTextColorItems();
        addTextFontItems();
        
<<<<<<< HEAD
        selectSpecificTab(currentTab);
        
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                previousTab = currentTab;
                int index =  (eboardTabbedPanel.getSelectedIndex() * 2) 
                    + ((JTabbedPane) eboardTabbedPanel.getSelectedComponent()).getSelectedIndex();
                currentTab = EBD_DisplayUsage.values()[index];
                selectSpecificTab(currentTab);
                showDialog(previousTab);
            }
        };
        eboardTabbedPanel.addChangeListener(changeListener);
        eBoardTabPane1.addChangeListener(changeListener);
        eBoardTabPane2.addChangeListener(changeListener);
        
        /**
         * Set icon for the simulated camera program
         */
        setIconImages(OSPiconList);                  
=======
        eboardTabbedPanelStateChanged(null);
        /**
         * Set icon for the simulated camera program
         */
        setIconImages(OSPiconList);                
>>>>>>> osparking/master
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        wholePanel = new javax.swing.JPanel();
<<<<<<< HEAD
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
=======
>>>>>>> osparking/master
        eboardTabbedPanel = new javax.swing.JTabbedPane();
        eBoardTabPane1 = new javax.swing.JTabbedPane();
        eBoardPanel0 = new javax.swing.JPanel();
        label_MSG0 = new javax.swing.JLabel();
        tf_VerbatimContent0 = new javax.swing.JTextField();
        label_Effect0 = new javax.swing.JLabel();
        label_Color0 = new javax.swing.JLabel();
        label_Font0 = new javax.swing.JLabel();
        combo_DisplayEffect0 = new javax.swing.JComboBox();
        combo_TextColor0 = new javax.swing.JComboBox();
        combo_TextFont0 = new javax.swing.JComboBox();
        label_ContentType0 = new javax.swing.JLabel();
        combo_ContentType0 = new javax.swing.JComboBox();
        btn_Save0 = new javax.swing.JButton();
        btn_Cancel0 = new javax.swing.JButton();
        eBoardPanel1 = new javax.swing.JPanel();
        label_MSG1 = new javax.swing.JLabel();
        tf_VerbatimContent1 = new javax.swing.JTextField();
        label_Effect1 = new javax.swing.JLabel();
        label_Color1 = new javax.swing.JLabel();
        label_Font1 = new javax.swing.JLabel();
        combo_DisplayEffect1 = new javax.swing.JComboBox();
        combo_TextColor1 = new javax.swing.JComboBox();
        combo_TextFont1 = new javax.swing.JComboBox();
        label_ContentType1 = new javax.swing.JLabel();
        combo_ContentType1 = new javax.swing.JComboBox();
        btn_Save1 = new javax.swing.JButton();
        btn_Cancel1 = new javax.swing.JButton();
        eBoardTabPane2 = new javax.swing.JTabbedPane();
        eBoardPanel2 = new javax.swing.JPanel();
        label_MSG2 = new javax.swing.JLabel();
        tf_VerbatimContent2 = new javax.swing.JTextField();
        label_Effect2 = new javax.swing.JLabel();
        label_Color2 = new javax.swing.JLabel();
        label_Font2 = new javax.swing.JLabel();
        combo_DisplayEffect2 = new javax.swing.JComboBox();
        combo_TextColor2 = new javax.swing.JComboBox();
        combo_TextFont2 = new javax.swing.JComboBox();
        label_ContentType2 = new javax.swing.JLabel();
        combo_ContentType2 = new javax.swing.JComboBox();
        btn_Save2 = new javax.swing.JButton();
        btn_Cancel2 = new javax.swing.JButton();
        eBoardPanel3 = new javax.swing.JPanel();
        label_MSG3 = new javax.swing.JLabel();
        tf_VerbatimContent3 = new javax.swing.JTextField();
        label_Effect3 = new javax.swing.JLabel();
        label_Color3 = new javax.swing.JLabel();
        label_Font3 = new javax.swing.JLabel();
        combo_DisplayEffect3 = new javax.swing.JComboBox();
        combo_TextColor3 = new javax.swing.JComboBox();
        combo_TextFont3 = new javax.swing.JComboBox();
        label_ContentType3 = new javax.swing.JLabel();
        combo_ContentType3 = new javax.swing.JComboBox();
        btn_Save3 = new javax.swing.JButton();
        btn_Cancel3 = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        btn_Exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
<<<<<<< HEAD
        setTitle("Electronic Display Settings");
=======
        setTitle(((String[])Globals.TitleList.get(E_BOARD_SETTINGS_FRAME_TITLE.ordinal()))[ourLang]);
>>>>>>> osparking/master
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formClosing(evt);
            }
        });

<<<<<<< HEAD
        wholePanel.setLayout(new javax.swing.BoxLayout(wholePanel, javax.swing.BoxLayout.PAGE_AXIS));
        wholePanel.add(filler1);
        wholePanel.add(filler2);

        eboardTabbedPanel.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        eboardTabbedPanel.setName("eboardTabbedPanel"); // NOI18N
=======
        wholePanel.setLayout(new java.awt.BorderLayout());

        eboardTabbedPanel.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        eboardTabbedPanel.setName("eboardTabbedPanel"); // NOI18N
        eboardTabbedPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                eboardTabbedPanelStateChanged(evt);
            }
        });
>>>>>>> osparking/master

        eBoardTabPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        eBoardTabPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        eBoardTabPane1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        eBoardTabPane1.setMinimumSize(new java.awt.Dimension(300, 198));
        eBoardTabPane1.setName("Default_Panel"); // NOI18N
<<<<<<< HEAD
=======
        eBoardTabPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                eBoardTabPane1StateChanged(evt);
            }
        });
>>>>>>> osparking/master

        eBoardPanel0.setName("eBoard" + EBD_DisplayUsage.DEFAULT_TOP_ROW.getVal());
        eBoardPanel0.setLayout(new java.awt.GridBagLayout());

        label_MSG0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_MSG0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_MSG0.setText("Message");
        label_MSG0.setPreferredSize(new java.awt.Dimension(76, 15));
=======
        label_MSG0.setText(((String[])Globals.LabelsText.get(MSG_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel0.add(label_MSG0, gridBagConstraints);

        tf_VerbatimContent0.setColumns(23);
        tf_VerbatimContent0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        tf_VerbatimContent0.setMinimumSize(new java.awt.Dimension(250, 18));
        tf_VerbatimContent0.setName("tf_VerbatimContent" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        tf_VerbatimContent0.setPreferredSize(new java.awt.Dimension(250, 25));
        tf_VerbatimContent0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_VerbatimContent0KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        eBoardPanel0.add(tf_VerbatimContent0, gridBagConstraints);

        label_Effect0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Effect0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Effect0.setText("Effect");
=======
        label_Effect0.setText(((String[])Globals.LabelsText.get(EFFECT_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        eBoardPanel0.add(label_Effect0, gridBagConstraints);

        label_Color0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Color0.setText("Color");
        label_Color0.setPreferredSize(new java.awt.Dimension(76, 15));
=======
        label_Color0.setText(((String[])Globals.LabelsText.get(COLOR_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel0.add(label_Color0, gridBagConstraints);

        label_Font0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Font0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Font0.setText("Font");
=======
        label_Font0.setText(((String[])Globals.LabelsText.get(FONT_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel0.add(label_Font0, gridBagConstraints);

        combo_DisplayEffect0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_DisplayEffect0.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_DisplayEffect0.setName("combo_DisplayEffect" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        combo_DisplayEffect0.setPreferredSize(new java.awt.Dimension(100, 25));
        combo_DisplayEffect0.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_DisplayEffect0PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel0.add(combo_DisplayEffect0, gridBagConstraints);

        combo_TextColor0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_TextColor0.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_TextColor0.setName("combo_TextColor" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        combo_TextColor0.setPreferredSize(new java.awt.Dimension(100, 25));
        combo_TextColor0.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_TextColor0PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel0.add(combo_TextColor0, gridBagConstraints);

        combo_TextFont0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_TextFont0.setMinimumSize(new java.awt.Dimension(158, 21));
        combo_TextFont0.setName("combo_TextFont" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        combo_TextFont0.setPreferredSize(new java.awt.Dimension(143, 25));
        combo_TextFont0.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_TextFont0PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        eBoardPanel0.add(combo_TextFont0, gridBagConstraints);

        label_ContentType0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
<<<<<<< HEAD
        label_ContentType0.setText("Content Type");
=======
        label_ContentType0.setText(((String[])Globals.LabelsText.get(CONTENT_TYPE_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel0.add(label_ContentType0, gridBagConstraints);

        combo_ContentType0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_ContentType0.setName("combo_ContentType" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        combo_ContentType0.setPreferredSize(new java.awt.Dimension(154, 25));
        combo_ContentType0.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_ContentType0PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        combo_ContentType0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_ContentType0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel0.add(combo_ContentType0, gridBagConstraints);

        btn_Save0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Save0.setMnemonic('s');
<<<<<<< HEAD
        btn_Save0.setText("Save");
        btn_Save0.setEnabled(false);
        btn_Save0.setInheritsPopupMenu(true);
        btn_Save0.setMaximumSize(new java.awt.Dimension(85, 35));
        btn_Save0.setMinimumSize(new java.awt.Dimension(85, 35));
        btn_Save0.setName("btn_Save" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        btn_Save0.setPreferredSize(new java.awt.Dimension(85, 35));
=======
        btn_Save0.setText(((String[])Globals.ButtonLabels.get(SAVE_BTN.ordinal()))[ourLang]);
        btn_Save0.setEnabled(false);
        btn_Save0.setInheritsPopupMenu(true);
        btn_Save0.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Save0.setMinimumSize(new java.awt.Dimension(73, 35));
        btn_Save0.setName("btn_Save" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        btn_Save0.setPreferredSize(new java.awt.Dimension(73, 30));
>>>>>>> osparking/master
        btn_Save0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Save0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
<<<<<<< HEAD
=======
        gridBagConstraints.ipadx = 20;
>>>>>>> osparking/master
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 170, 0, 0);
        eBoardPanel0.add(btn_Save0, gridBagConstraints);

        btn_Cancel0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Cancel0.setMnemonic('c');
<<<<<<< HEAD
        btn_Cancel0.setText("Cancel");
        btn_Cancel0.setEnabled(false);
        btn_Cancel0.setMaximumSize(new java.awt.Dimension(85, 35));
        btn_Cancel0.setMinimumSize(new java.awt.Dimension(85, 35));
        btn_Cancel0.setName("btn_Cancel" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        btn_Cancel0.setPreferredSize(new java.awt.Dimension(85, 35));
=======
        btn_Cancel0.setText(((String[])Globals.ButtonLabels.get(CANCEL_BTN.ordinal()))[ourLang]);
        btn_Cancel0.setEnabled(false);
        btn_Cancel0.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Cancel0.setMinimumSize(new java.awt.Dimension(73, 35));
        btn_Cancel0.setName("btn_Cancel" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        btn_Cancel0.setPreferredSize(new java.awt.Dimension(73, 30));
>>>>>>> osparking/master
        btn_Cancel0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancel0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
<<<<<<< HEAD
=======
        gridBagConstraints.ipadx = 20;
>>>>>>> osparking/master
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        eBoardPanel0.add(btn_Cancel0, gridBagConstraints);

<<<<<<< HEAD
        eBoardTabPane1.addTab("TOP", eBoardPanel0);
=======
        eBoardTabPane1.addTab(((String[])Globals.TitleList.get(TOP_PANEL_TITLE.ordinal()))[ourLang], eBoardPanel0);
>>>>>>> osparking/master

        eBoardPanel1.setName("eBoard" + EBD_DisplayUsage.DEFAULT_BOTTOM_ROW.getVal());
        eBoardPanel1.setLayout(new java.awt.GridBagLayout());

        label_MSG1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_MSG1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_MSG1.setText("Message");
        label_MSG1.setPreferredSize(new java.awt.Dimension(76, 15));
=======
        label_MSG1.setText(((String[])Globals.LabelsText.get(MSG_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel1.add(label_MSG1, gridBagConstraints);

        tf_VerbatimContent1.setColumns(23);
        tf_VerbatimContent1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        tf_VerbatimContent1.setMinimumSize(new java.awt.Dimension(250, 18));
        tf_VerbatimContent1.setName("tf_VerbatimContent" + EBD_DisplayUsage.DEFAULT_BOTTOM_ROW.ordinal());
        tf_VerbatimContent1.setPreferredSize(new java.awt.Dimension(250, 25));
        tf_VerbatimContent1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_VerbatimContent1KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        eBoardPanel1.add(tf_VerbatimContent1, gridBagConstraints);

        label_Effect1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Effect1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Effect1.setText("Effect");
=======
        label_Effect1.setText(((String[])Globals.LabelsText.get(EFFECT_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        eBoardPanel1.add(label_Effect1, gridBagConstraints);

        label_Color1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Color1.setText("Color");
        label_Color1.setPreferredSize(new java.awt.Dimension(76, 15));
=======
        label_Color1.setText(((String[])Globals.LabelsText.get(COLOR_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel1.add(label_Color1, gridBagConstraints);

        label_Font1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Font1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Font1.setText("Font");
=======
        label_Font1.setText(((String[])Globals.LabelsText.get(FONT_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel1.add(label_Font1, gridBagConstraints);

        combo_DisplayEffect1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_DisplayEffect1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L to R Flow", "R to L Flow", "Still Frame", "Blinking" }));
        combo_DisplayEffect1.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_DisplayEffect1.setName("combo_DisplayEffect" + EBD_DisplayUsage.DEFAULT_BOTTOM_ROW.ordinal());
        combo_DisplayEffect1.setPreferredSize(new java.awt.Dimension(100, 25));
        combo_DisplayEffect1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_DisplayEffect1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel1.add(combo_DisplayEffect1, gridBagConstraints);

        combo_TextColor1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_TextColor1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RED", "ORANGE", "GREEN", "BLACK", "BLUE" }));
        combo_TextColor1.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_TextColor1.setName("combo_TextColor" + EBD_DisplayUsage.DEFAULT_BOTTOM_ROW.ordinal());
        combo_TextColor1.setPreferredSize(new java.awt.Dimension(100, 25));
        combo_TextColor1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_TextColor1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel1.add(combo_TextColor1, gridBagConstraints);

        combo_TextFont1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_TextFont1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dialog", "DialogInput", "Microsoft_NeoGothic", "Monospaced", "Sans_Serif" }));
        combo_TextFont1.setMinimumSize(new java.awt.Dimension(158, 21));
        combo_TextFont1.setName("combo_TextFont" + EBD_DisplayUsage.DEFAULT_BOTTOM_ROW.ordinal());
        combo_TextFont1.setPreferredSize(new java.awt.Dimension(143, 25));
        combo_TextFont1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_TextFont1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        eBoardPanel1.add(combo_TextFont1, gridBagConstraints);

        label_ContentType1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
<<<<<<< HEAD
        label_ContentType1.setText("Content Type");
=======
        label_ContentType1.setText(((String[])Globals.LabelsText.get(CONTENT_TYPE_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel1.add(label_ContentType1, gridBagConstraints);

        combo_ContentType1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_ContentType1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VERBATIM", "VEHICLE TAG", "REGISTRATION STAT", "GATE NAME", "CURRENT DATE", "CURRENT TIME", "CURRENT DATE TIME" }));
        combo_ContentType1.setName("combo_ContentType" + EBD_DisplayUsage.DEFAULT_BOTTOM_ROW.ordinal());
        combo_ContentType1.setPreferredSize(new java.awt.Dimension(154, 25));
        combo_ContentType1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_ContentType1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        combo_ContentType1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_ContentType1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel1.add(combo_ContentType1, gridBagConstraints);

        btn_Save1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Save1.setMnemonic('s');
<<<<<<< HEAD
        btn_Save1.setText("Save");
=======
        btn_Save1.setText(((String[])Globals.ButtonLabels.get(SAVE_BTN.ordinal()))[ourLang]);
>>>>>>> osparking/master
        btn_Save1.setEnabled(false);
        btn_Save1.setInheritsPopupMenu(true);
        btn_Save1.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Save1.setMinimumSize(new java.awt.Dimension(73, 35));
        btn_Save1.setName("btn_Save" + EBD_DisplayUsage.DEFAULT_BOTTOM_ROW.ordinal());
        btn_Save1.setPreferredSize(new java.awt.Dimension(73, 30));
        btn_Save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Save1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 170, 0, 0);
        eBoardPanel1.add(btn_Save1, gridBagConstraints);

        btn_Cancel1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
<<<<<<< HEAD
        btn_Cancel1.setText("Cancel");
=======
        btn_Cancel1.setMnemonic('c');
        btn_Cancel1.setText(((String[])Globals.ButtonLabels.get(CANCEL_BTN.ordinal()))[ourLang]);
        btn_Cancel1.setEnabled(false);
>>>>>>> osparking/master
        btn_Cancel1.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Cancel1.setMinimumSize(new java.awt.Dimension(73, 35));
        btn_Cancel1.setName("btn_Cancel" + EBD_DisplayUsage.DEFAULT_BOTTOM_ROW.ordinal());
        btn_Cancel1.setPreferredSize(new java.awt.Dimension(73, 30));
        btn_Cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancel1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        eBoardPanel1.add(btn_Cancel1, gridBagConstraints);

<<<<<<< HEAD
        eBoardTabPane1.addTab("BOTTOM", eBoardPanel1);

        eboardTabbedPanel.addTab("Default", eBoardTabPane1);
=======
        eBoardTabPane1.addTab(((String[])Globals.TitleList.get(BOTTOM_PANEL_TITLE.ordinal()))[ourLang], eBoardPanel1);

        eboardTabbedPanel.addTab(((String[])Globals.TitleList.get(DEFAULT_PANEL_TITLE.ordinal()))[ourLang], eBoardTabPane1);
>>>>>>> osparking/master

        eBoardTabPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        eBoardTabPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        eBoardTabPane2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        eBoardTabPane2.setName("Vehicle_Panel"); // NOI18N
<<<<<<< HEAD
=======
        eBoardTabPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                eBoardTabPane2StateChanged(evt);
            }
        });
>>>>>>> osparking/master

        eBoardPanel2.setName("eBoard" + EBD_DisplayUsage.CAR_ENTRY_TOP_ROW.getVal());
        eBoardPanel2.setLayout(new java.awt.GridBagLayout());

        label_MSG2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_MSG2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_MSG2.setText("Message");
        label_MSG2.setPreferredSize(new java.awt.Dimension(76, 15));
=======
        label_MSG2.setText(((String[])Globals.LabelsText.get(MSG_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel2.add(label_MSG2, gridBagConstraints);

        tf_VerbatimContent2.setColumns(23);
        tf_VerbatimContent2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        tf_VerbatimContent2.setMinimumSize(new java.awt.Dimension(250, 18));
        tf_VerbatimContent2.setName("tf_VerbatimContent" + EBD_DisplayUsage.CAR_ENTRY_TOP_ROW.ordinal());
        tf_VerbatimContent2.setPreferredSize(new java.awt.Dimension(250, 25));
        tf_VerbatimContent2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_VerbatimContent2KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        eBoardPanel2.add(tf_VerbatimContent2, gridBagConstraints);

        label_Effect2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Effect2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Effect2.setText("Effect");
=======
        label_Effect2.setText(((String[])Globals.LabelsText.get(EFFECT_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        eBoardPanel2.add(label_Effect2, gridBagConstraints);

        label_Color2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Color2.setText("Color");
        label_Color2.setPreferredSize(new java.awt.Dimension(76, 15));
=======
        label_Color2.setText(((String[])Globals.LabelsText.get(COLOR_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel2.add(label_Color2, gridBagConstraints);

        label_Font2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Font2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Font2.setText("Font");
=======
        label_Font2.setText(((String[])Globals.LabelsText.get(FONT_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel2.add(label_Font2, gridBagConstraints);

        combo_DisplayEffect2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_DisplayEffect2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L to R Flow", "R to L Flow", "Still Frame", "Blinking" }));
        combo_DisplayEffect2.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_DisplayEffect2.setName("combo_DisplayEffect" + EBD_DisplayUsage.CAR_ENTRY_TOP_ROW.ordinal());
        combo_DisplayEffect2.setPreferredSize(new java.awt.Dimension(100, 25));
        combo_DisplayEffect2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_DisplayEffect2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel2.add(combo_DisplayEffect2, gridBagConstraints);

        combo_TextColor2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_TextColor2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RED", "ORANGE", "GREEN", "BLACK", "BLUE" }));
        combo_TextColor2.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_TextColor2.setName("combo_TextColor" + EBD_DisplayUsage.CAR_ENTRY_TOP_ROW.ordinal());
        combo_TextColor2.setPreferredSize(new java.awt.Dimension(100, 25));
        combo_TextColor2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_TextColor2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel2.add(combo_TextColor2, gridBagConstraints);

        combo_TextFont2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_TextFont2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dialog", "DialogInput", "Microsoft_NeoGothic", "Monospaced", "Sans_Serif" }));
        combo_TextFont2.setMinimumSize(new java.awt.Dimension(158, 21));
        combo_TextFont2.setName("combo_TextFont" + EBD_DisplayUsage.CAR_ENTRY_TOP_ROW.ordinal());
        combo_TextFont2.setPreferredSize(new java.awt.Dimension(143, 25));
        combo_TextFont2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_TextFont2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        eBoardPanel2.add(combo_TextFont2, gridBagConstraints);

        label_ContentType2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
<<<<<<< HEAD
        label_ContentType2.setText("Content Type");
=======
        label_ContentType2.setText(((String[])Globals.LabelsText.get(CONTENT_TYPE_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel2.add(label_ContentType2, gridBagConstraints);

        combo_ContentType2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_ContentType2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VERBATIM", "VEHICLE TAG", "REGISTRATION STAT", "GATE NAME", "CURRENT DATE", "CURRENT TIME", "CURRENT DATE TIME" }));
        combo_ContentType2.setName("combo_ContentType" + EBD_DisplayUsage.CAR_ENTRY_TOP_ROW.ordinal());
        combo_ContentType2.setPreferredSize(new java.awt.Dimension(154, 25));
        combo_ContentType2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_ContentType2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        combo_ContentType2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_ContentType2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel2.add(combo_ContentType2, gridBagConstraints);

        btn_Save2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Save2.setMnemonic('s');
<<<<<<< HEAD
        btn_Save2.setText("Save");
=======
        btn_Save2.setText(((String[])Globals.ButtonLabels.get(SAVE_BTN.ordinal()))[ourLang]);
>>>>>>> osparking/master
        btn_Save2.setEnabled(false);
        btn_Save2.setInheritsPopupMenu(true);
        btn_Save2.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Save2.setMinimumSize(new java.awt.Dimension(73, 35));
        btn_Save2.setName("btn_Save" + EBD_DisplayUsage.CAR_ENTRY_TOP_ROW.ordinal());
        btn_Save2.setPreferredSize(new java.awt.Dimension(73, 30));
        btn_Save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Save2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 170, 0, 0);
        eBoardPanel2.add(btn_Save2, gridBagConstraints);

        btn_Cancel2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
<<<<<<< HEAD
        btn_Cancel2.setText("Cancel");
=======
        btn_Cancel2.setMnemonic('c');
        btn_Cancel2.setText(((String[])Globals.ButtonLabels.get(CANCEL_BTN.ordinal()))[ourLang]);
        btn_Cancel2.setEnabled(false);
>>>>>>> osparking/master
        btn_Cancel2.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Cancel2.setMinimumSize(new java.awt.Dimension(73, 35));
        btn_Cancel2.setName("btn_Cancel" + EBD_DisplayUsage.CAR_ENTRY_TOP_ROW.ordinal());
        btn_Cancel2.setPreferredSize(new java.awt.Dimension(73, 30));
        btn_Cancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancel2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        eBoardPanel2.add(btn_Cancel2, gridBagConstraints);

<<<<<<< HEAD
        eBoardTabPane2.addTab("TOP", eBoardPanel2);
=======
        eBoardTabPane2.addTab(((String[])Globals.TitleList.get(TOP_PANEL_TITLE.ordinal()))[ourLang], eBoardPanel2);
>>>>>>> osparking/master

        eBoardPanel3.setName("eBoard" + EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW.getVal());
        eBoardPanel3.setLayout(new java.awt.GridBagLayout());

        label_MSG3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_MSG3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_MSG3.setText("Message");
        label_MSG3.setPreferredSize(new java.awt.Dimension(76, 15));
=======
        label_MSG3.setText(((String[])Globals.LabelsText.get(MSG_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel3.add(label_MSG3, gridBagConstraints);

        tf_VerbatimContent3.setColumns(23);
        tf_VerbatimContent3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        tf_VerbatimContent3.setMinimumSize(new java.awt.Dimension(250, 18));
        tf_VerbatimContent3.setName("tf_VerbatimContent" + EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW.ordinal());
        tf_VerbatimContent3.setPreferredSize(new java.awt.Dimension(250, 25));
        tf_VerbatimContent3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_VerbatimContent3KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        eBoardPanel3.add(tf_VerbatimContent3, gridBagConstraints);

        label_Effect3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Effect3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Effect3.setText("Effect");
=======
        label_Effect3.setText(((String[])Globals.LabelsText.get(EFFECT_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        eBoardPanel3.add(label_Effect3, gridBagConstraints);

        label_Color3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Color3.setText("Color");
        label_Color3.setPreferredSize(new java.awt.Dimension(76, 15));
=======
        label_Color3.setText(((String[])Globals.LabelsText.get(COLOR_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel3.add(label_Color3, gridBagConstraints);

        label_Font3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Font3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
<<<<<<< HEAD
        label_Font3.setText("Font");
=======
        label_Font3.setText(((String[])Globals.LabelsText.get(FONT_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel3.add(label_Font3, gridBagConstraints);

        combo_DisplayEffect3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_DisplayEffect3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L to R Flow", "R to L Flow", "Still Frame", "Blinking" }));
        combo_DisplayEffect3.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_DisplayEffect3.setName("combo_DisplayEffect" + EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW.ordinal());
        combo_DisplayEffect3.setPreferredSize(new java.awt.Dimension(100, 25));
        combo_DisplayEffect3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_DisplayEffect3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel3.add(combo_DisplayEffect3, gridBagConstraints);

        combo_TextColor3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_TextColor3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RED", "ORANGE", "GREEN", "BLACK", "BLUE" }));
        combo_TextColor3.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_TextColor3.setName("combo_TextColor" + EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW.ordinal());
        combo_TextColor3.setPreferredSize(new java.awt.Dimension(100, 25));
        combo_TextColor3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_TextColor3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel3.add(combo_TextColor3, gridBagConstraints);

        combo_TextFont3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_TextFont3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dialog", "DialogInput", "Microsoft_NeoGothic", "Monospaced", "Sans_Serif" }));
        combo_TextFont3.setMinimumSize(new java.awt.Dimension(158, 21));
        combo_TextFont3.setName("combo_TextFont" + EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW.ordinal());
        combo_TextFont3.setPreferredSize(new java.awt.Dimension(143, 25));
        combo_TextFont3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_TextFont3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        eBoardPanel3.add(combo_TextFont3, gridBagConstraints);

        label_ContentType3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
<<<<<<< HEAD
        label_ContentType3.setText("Content Type");
=======
        label_ContentType3.setText(((String[])Globals.LabelsText.get(CONTENT_TYPE_LABEL.ordinal()))[ourLang]);
>>>>>>> osparking/master
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardPanel3.add(label_ContentType3, gridBagConstraints);

        combo_ContentType3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        combo_ContentType3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VERBATIM", "VEHICLE TAG", "REGISTRATION STAT", "GATE NAME", "CURRENT DATE", "CURRENT TIME", "CURRENT DATE TIME" }));
        combo_ContentType3.setName("combo_ContentType" + EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW.ordinal());
        combo_ContentType3.setPreferredSize(new java.awt.Dimension(154, 25));
        combo_ContentType3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_ContentType3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        combo_ContentType3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_ContentType3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        eBoardPanel3.add(combo_ContentType3, gridBagConstraints);

        btn_Save3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Save3.setMnemonic('s');
<<<<<<< HEAD
        btn_Save3.setText("Save");
=======
        btn_Save3.setText(((String[])Globals.ButtonLabels.get(SAVE_BTN.ordinal()))[ourLang]);
>>>>>>> osparking/master
        btn_Save3.setEnabled(false);
        btn_Save3.setInheritsPopupMenu(true);
        btn_Save3.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Save3.setMinimumSize(new java.awt.Dimension(73, 35));
        btn_Save3.setName("btn_Save" + EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW.ordinal());
        btn_Save3.setPreferredSize(new java.awt.Dimension(73, 30));
        btn_Save3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Save3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 170, 0, 0);
        eBoardPanel3.add(btn_Save3, gridBagConstraints);

        btn_Cancel3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
<<<<<<< HEAD
        btn_Cancel3.setText("Cancel");
=======
        btn_Cancel3.setMnemonic('c');
        btn_Cancel3.setText(((String[])Globals.ButtonLabels.get(CANCEL_BTN.ordinal()))[ourLang]);
        btn_Cancel3.setEnabled(false);
>>>>>>> osparking/master
        btn_Cancel3.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Cancel3.setMinimumSize(new java.awt.Dimension(73, 35));
        btn_Cancel3.setName("btn_Cancel" + EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW.ordinal());
        btn_Cancel3.setPreferredSize(new java.awt.Dimension(73, 30));
        btn_Cancel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancel3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        eBoardPanel3.add(btn_Cancel3, gridBagConstraints);

<<<<<<< HEAD
        eBoardTabPane2.addTab("BOTTOM", eBoardPanel3);

        eboardTabbedPanel.addTab("Vehicle", eBoardTabPane2);

        btn_Exit.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Exit.setMnemonic('c');
        btn_Exit.setText("Close");
        btn_Exit.setMaximumSize(new java.awt.Dimension(85, 35));
        btn_Exit.setMinimumSize(new java.awt.Dimension(85, 35));
        btn_Exit.setPreferredSize(new java.awt.Dimension(85, 35));
=======
        eBoardTabPane2.addTab(((String[])Globals.TitleList.get(BOTTOM_PANEL_TITLE.ordinal()))[ourLang], eBoardPanel3);

        eboardTabbedPanel.addTab(((String[])Globals.TitleList.get(VEHICLE_PANEL_TITLE.ordinal()))[ourLang], eBoardTabPane2);

        wholePanel.add(eboardTabbedPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        btn_Exit.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Exit.setMnemonic('c');
        btn_Exit.setText(((String[])Globals.ButtonLabels.get(CLOSE_BTN.ordinal()))[ourLang]);
        btn_Exit.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Exit.setMinimumSize(new java.awt.Dimension(73, 35));
        btn_Exit.setPreferredSize(new java.awt.Dimension(73, 30));
>>>>>>> osparking/master
        btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitActionPerformed(evt);
            }
        });
<<<<<<< HEAD

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Exit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eboardTabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(wholePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(eboardTabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wholePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void changeEnabled_of_SaveCancelButtons(boolean onOff) {
        if (onOff) {
            formMode = FormMode.MODIFICATION;
        } else {
            formMode = FormMode.SEARCHING;
        }
        ((JButton) getComponentByName("btn_Save" + currentTab.ordinal())).setEnabled(onOff);
        ((JButton) getComponentByName("btn_Cancel" + currentTab.ordinal())).setEnabled(onOff);        
        btn_Exit.setEnabled(!onOff);
    }    
    
    private void btn_Save0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save0ActionPerformed
        showDialog(currentTab);
    }//GEN-LAST:event_btn_Save0ActionPerformed

    private void btn_Cancel0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel0ActionPerformed
        cancelBtnClick();
=======
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 450, 0, 0);
        buttonPanel.add(btn_Exit, gridBagConstraints);

        wholePanel.add(buttonPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(wholePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_VerbatimContent0KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_VerbatimContent0KeyReleased
        changeButtonEnabled_IfVebarimChanged(0);
    }//GEN-LAST:event_tf_VerbatimContent0KeyReleased

    private void combo_ContentType0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ContentType0ActionPerformed
        checkContentType(0);
    }//GEN-LAST:event_combo_ContentType0ActionPerformed

    private void btn_Save0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save0ActionPerformed
        showDialog(DEFAULT_TOP_ROW);
    }//GEN-LAST:event_btn_Save0ActionPerformed

    private void btn_Cancel0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel0ActionPerformed
        cancelBtnClick(DEFAULT_TOP_ROW);
>>>>>>> osparking/master
    }//GEN-LAST:event_btn_Cancel0ActionPerformed

    private void btn_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExitActionPerformed
        tryToCloseEBDSettingsForm();
    }//GEN-LAST:event_btn_ExitActionPerformed

    private void btn_Cancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel2ActionPerformed
<<<<<<< HEAD
        cancelBtnClick();
    }//GEN-LAST:event_btn_Cancel2ActionPerformed

    private void btn_Save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save2ActionPerformed
        showDialog(currentTab);
    }//GEN-LAST:event_btn_Save2ActionPerformed

    private void btn_Cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel1ActionPerformed
        cancelBtnClick();
    }//GEN-LAST:event_btn_Cancel1ActionPerformed

    private void btn_Save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save1ActionPerformed
        showDialog(currentTab);
    }//GEN-LAST:event_btn_Save1ActionPerformed

    private void btn_Save3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save3ActionPerformed
        showDialog(currentTab);
    }//GEN-LAST:event_btn_Save3ActionPerformed

    private void btn_Cancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel3ActionPerformed
        cancelBtnClick();
=======
        cancelBtnClick(CAR_ENTRY_TOP_ROW);
    }//GEN-LAST:event_btn_Cancel2ActionPerformed

    private void btn_Save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save2ActionPerformed
        showDialog(CAR_ENTRY_TOP_ROW);
    }//GEN-LAST:event_btn_Save2ActionPerformed

    private void combo_ContentType2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ContentType2ActionPerformed
        checkContentType(2);
    }//GEN-LAST:event_combo_ContentType2ActionPerformed

    private void tf_VerbatimContent2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_VerbatimContent2KeyReleased
        changeButtonEnabled_IfVebarimChanged(2);
    }//GEN-LAST:event_tf_VerbatimContent2KeyReleased

    private void btn_Cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel1ActionPerformed
        cancelBtnClick(DEFAULT_BOTTOM_ROW);
    }//GEN-LAST:event_btn_Cancel1ActionPerformed

    private void btn_Save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save1ActionPerformed
        showDialog(DEFAULT_BOTTOM_ROW);
    }//GEN-LAST:event_btn_Save1ActionPerformed

    private void combo_ContentType1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ContentType1ActionPerformed
        checkContentType(1);
    }//GEN-LAST:event_combo_ContentType1ActionPerformed

    private void tf_VerbatimContent1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_VerbatimContent1KeyReleased
        changeButtonEnabled_IfVebarimChanged(1);
    }//GEN-LAST:event_tf_VerbatimContent1KeyReleased

    private void tf_VerbatimContent3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_VerbatimContent3KeyReleased
        changeButtonEnabled_IfVebarimChanged(3);
    }//GEN-LAST:event_tf_VerbatimContent3KeyReleased

    private void combo_ContentType3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ContentType3ActionPerformed
        checkContentType(3);
    }//GEN-LAST:event_combo_ContentType3ActionPerformed

    private void btn_Save3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save3ActionPerformed
        showDialog(CAR_ENTRY_BOTTOM_ROW);
    }//GEN-LAST:event_btn_Save3ActionPerformed

    private void btn_Cancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel3ActionPerformed
        cancelBtnClick(CAR_ENTRY_BOTTOM_ROW);
>>>>>>> osparking/master
    }//GEN-LAST:event_btn_Cancel3ActionPerformed

    private void formClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formClosing
        tryToCloseEBDSettingsForm();        
    }//GEN-LAST:event_formClosing

<<<<<<< HEAD
    private void combo_ContentType3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_ContentType3PopupMenuWillBecomeInvisible
        setButtonEnabledIfContentTypeChanged(EBD_DisplayUsage.values()[3]);
    }//GEN-LAST:event_combo_ContentType3PopupMenuWillBecomeInvisible

    private void combo_ContentType0PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_ContentType0PopupMenuWillBecomeInvisible
        setButtonEnabledIfContentTypeChanged(EBD_DisplayUsage.values()[0]);
    }//GEN-LAST:event_combo_ContentType0PopupMenuWillBecomeInvisible

    private void combo_ContentType1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_ContentType1PopupMenuWillBecomeInvisible
        setButtonEnabledIfContentTypeChanged(EBD_DisplayUsage.values()[1]);
    }//GEN-LAST:event_combo_ContentType1PopupMenuWillBecomeInvisible

    private void combo_ContentType2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_ContentType2PopupMenuWillBecomeInvisible
        setButtonEnabledIfContentTypeChanged(EBD_DisplayUsage.values()[2]);
    }//GEN-LAST:event_combo_ContentType2PopupMenuWillBecomeInvisible

    private void combo_TextColor0PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextColor0PopupMenuWillBecomeInvisible
        setButtonEnabledIfColorChanged(EBD_DisplayUsage.values()[0]);
    }//GEN-LAST:event_combo_TextColor0PopupMenuWillBecomeInvisible

    private void combo_TextColor1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextColor1PopupMenuWillBecomeInvisible
        setButtonEnabledIfColorChanged(EBD_DisplayUsage.values()[1]);
    }//GEN-LAST:event_combo_TextColor1PopupMenuWillBecomeInvisible

    private void combo_TextColor2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextColor2PopupMenuWillBecomeInvisible
        setButtonEnabledIfColorChanged(EBD_DisplayUsage.values()[2]);
    }//GEN-LAST:event_combo_TextColor2PopupMenuWillBecomeInvisible

    private void combo_TextColor3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextColor3PopupMenuWillBecomeInvisible
        setButtonEnabledIfColorChanged(EBD_DisplayUsage.values()[3]);
    }//GEN-LAST:event_combo_TextColor3PopupMenuWillBecomeInvisible

    private void combo_DisplayEffect0PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_DisplayEffect0PopupMenuWillBecomeInvisible
        setButtonEnabledIfEffectChanged(EBD_DisplayUsage.values()[0]);
    }//GEN-LAST:event_combo_DisplayEffect0PopupMenuWillBecomeInvisible

    private void combo_DisplayEffect1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_DisplayEffect1PopupMenuWillBecomeInvisible
        setButtonEnabledIfEffectChanged(EBD_DisplayUsage.values()[1]);
    }//GEN-LAST:event_combo_DisplayEffect1PopupMenuWillBecomeInvisible

    private void combo_DisplayEffect2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_DisplayEffect2PopupMenuWillBecomeInvisible
        setButtonEnabledIfEffectChanged(EBD_DisplayUsage.values()[2]);
    }//GEN-LAST:event_combo_DisplayEffect2PopupMenuWillBecomeInvisible

    private void combo_DisplayEffect3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_DisplayEffect3PopupMenuWillBecomeInvisible
        setButtonEnabledIfEffectChanged(EBD_DisplayUsage.values()[3]);
    }//GEN-LAST:event_combo_DisplayEffect3PopupMenuWillBecomeInvisible

    private void combo_TextFont0PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextFont0PopupMenuWillBecomeInvisible
        setButtonEnabledIfFontChanged(EBD_DisplayUsage.values()[0]);
    }//GEN-LAST:event_combo_TextFont0PopupMenuWillBecomeInvisible

    private void combo_TextFont1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextFont1PopupMenuWillBecomeInvisible
        setButtonEnabledIfFontChanged(EBD_DisplayUsage.values()[1]);
    }//GEN-LAST:event_combo_TextFont1PopupMenuWillBecomeInvisible

    private void combo_TextFont2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextFont2PopupMenuWillBecomeInvisible
        setButtonEnabledIfFontChanged(EBD_DisplayUsage.values()[2]);
    }//GEN-LAST:event_combo_TextFont2PopupMenuWillBecomeInvisible

    private void combo_TextFont3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextFont3PopupMenuWillBecomeInvisible
        setButtonEnabledIfFontChanged(EBD_DisplayUsage.values()[3]);
    }//GEN-LAST:event_combo_TextFont3PopupMenuWillBecomeInvisible

    private void tf_VerbatimContent0KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_VerbatimContent0KeyReleased
        changeButtonEnabled_IfVebarimChanged(0);
    }//GEN-LAST:event_tf_VerbatimContent0KeyReleased

    private void tf_VerbatimContent1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_VerbatimContent1KeyReleased
        changeButtonEnabled_IfVebarimChanged(1);
    }//GEN-LAST:event_tf_VerbatimContent1KeyReleased

    private void tf_VerbatimContent2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_VerbatimContent2KeyReleased
        changeButtonEnabled_IfVebarimChanged(2);
    }//GEN-LAST:event_tf_VerbatimContent2KeyReleased

    private void tf_VerbatimContent3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_VerbatimContent3KeyReleased
        changeButtonEnabled_IfVebarimChanged(3);
    }//GEN-LAST:event_tf_VerbatimContent3KeyReleased

    private void combo_ContentType1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ContentType1ActionPerformed
        checkContentType(1);
    }//GEN-LAST:event_combo_ContentType1ActionPerformed

    private void combo_ContentType0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ContentType0ActionPerformed
        checkContentType(0);
    }//GEN-LAST:event_combo_ContentType0ActionPerformed

    private void combo_ContentType3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ContentType3ActionPerformed
        checkContentType(3);
    }//GEN-LAST:event_combo_ContentType3ActionPerformed

    private void combo_ContentType2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ContentType2ActionPerformed
        checkContentType(2);
    }//GEN-LAST:event_combo_ContentType2ActionPerformed
=======
    private void combo_ContentType0PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_ContentType0PopupMenuWillBecomeInvisible
        setButtonEnabledIfContentTypeChanged(EBD_DisplayUsage.values()[0]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_ContentType0PopupMenuWillBecomeInvisible

    private void combo_TextColor0PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextColor0PopupMenuWillBecomeInvisible
        setButtonEnabledIfColorChanged(EBD_DisplayUsage.values()[0]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_TextColor0PopupMenuWillBecomeInvisible

    private void combo_DisplayEffect0PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_DisplayEffect0PopupMenuWillBecomeInvisible
        setButtonEnabledIfEffectChanged(EBD_DisplayUsage.values()[0]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_DisplayEffect0PopupMenuWillBecomeInvisible

    private void combo_TextFont0PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextFont0PopupMenuWillBecomeInvisible
        setButtonEnabledIfFontChanged(EBD_DisplayUsage.values()[0]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_TextFont0PopupMenuWillBecomeInvisible

    private void combo_ContentType1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_ContentType1PopupMenuWillBecomeInvisible
        setButtonEnabledIfContentTypeChanged(EBD_DisplayUsage.values()[1]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_ContentType1PopupMenuWillBecomeInvisible

    private void combo_TextColor1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextColor1PopupMenuWillBecomeInvisible
        setButtonEnabledIfColorChanged(EBD_DisplayUsage.values()[1]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_TextColor1PopupMenuWillBecomeInvisible

    private void combo_DisplayEffect1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_DisplayEffect1PopupMenuWillBecomeInvisible
        setButtonEnabledIfEffectChanged(EBD_DisplayUsage.values()[1]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_DisplayEffect1PopupMenuWillBecomeInvisible

    private void combo_TextFont1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextFont1PopupMenuWillBecomeInvisible
        setButtonEnabledIfFontChanged(EBD_DisplayUsage.values()[1]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_TextFont1PopupMenuWillBecomeInvisible

    private void combo_ContentType2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_ContentType2PopupMenuWillBecomeInvisible
        setButtonEnabledIfContentTypeChanged(EBD_DisplayUsage.values()[2]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_ContentType2PopupMenuWillBecomeInvisible

    private void combo_TextColor2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextColor2PopupMenuWillBecomeInvisible
        setButtonEnabledIfColorChanged(EBD_DisplayUsage.values()[2]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_TextColor2PopupMenuWillBecomeInvisible

    private void combo_DisplayEffect2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_DisplayEffect2PopupMenuWillBecomeInvisible
        setButtonEnabledIfEffectChanged(EBD_DisplayUsage.values()[2]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_DisplayEffect2PopupMenuWillBecomeInvisible

    private void combo_TextFont2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextFont2PopupMenuWillBecomeInvisible
        setButtonEnabledIfFontChanged(EBD_DisplayUsage.values()[2]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_TextFont2PopupMenuWillBecomeInvisible

    private void combo_ContentType3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_ContentType3PopupMenuWillBecomeInvisible
        setButtonEnabledIfContentTypeChanged(EBD_DisplayUsage.values()[3]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_ContentType3PopupMenuWillBecomeInvisible

    private void combo_TextColor3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextColor3PopupMenuWillBecomeInvisible
        setButtonEnabledIfColorChanged(EBD_DisplayUsage.values()[3]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_TextColor3PopupMenuWillBecomeInvisible

    private void combo_DisplayEffect3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_DisplayEffect3PopupMenuWillBecomeInvisible
        setButtonEnabledIfEffectChanged(EBD_DisplayUsage.values()[3]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_DisplayEffect3PopupMenuWillBecomeInvisible

    private void combo_TextFont3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_TextFont3PopupMenuWillBecomeInvisible
        setButtonEnabledIfFontChanged(EBD_DisplayUsage.values()[3]);
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_TextFont3PopupMenuWillBecomeInvisible

    private void eboardTabbedPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_eboardTabbedPanelStateChanged
        // TODO add your handling code here:
        if (componentMap == null)
            return;
        
        final int column = eboardTabbedPanel.getSelectedIndex();
        int prevTabIdx = (column == 0 ? 1 : 0);
        JButton saveButtonTop = (JButton)componentMap.get("btn_Save" + (prevTabIdx * 2));
        JButton saveButtonBottom = (JButton)componentMap.get("btn_Save" + (prevTabIdx * 2 + 1));
        final int row = ((JTabbedPane)eboardTabbedPanel.getSelectedComponent()).getSelectedIndex();
        if (saveButtonTop.isEnabled() || saveButtonBottom.isEnabled()) {
            JOptionPane.showMessageDialog(this, 
                    ((String[])Globals.DialogMSGList.get(SAVE_OR_CANEL_DIALOG.ordinal()))[ourLang]);
            eboardTabbedPanel.setSelectedIndex(prevTabIdx);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                selectSpecificTab(row, column);
            }
        });
    }//GEN-LAST:event_eboardTabbedPanelStateChanged

    private void eBoardTabPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_eBoardTabPane1StateChanged
        // TODO add your handling code here:
        if (componentMap == null)
            return;
        
        final int row = eBoardTabPane1.getSelectedIndex();
        int prevTabIdx = (row == 0 ? 1 : 0);
        JButton saveButton = (JButton)componentMap.get("btn_Save" + prevTabIdx);
        if (saveButton.isEnabled()) {
            JOptionPane.showMessageDialog(this, 
                    ((String[])Globals.DialogMSGList.get(SAVE_OR_CANEL_DIALOG.ordinal()))[ourLang]);
            eBoardTabPane1.setSelectedIndex(prevTabIdx);
        }
         
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                selectSpecificTab(row, 0);
            }
        });
    }//GEN-LAST:event_eBoardTabPane1StateChanged

    private void eBoardTabPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_eBoardTabPane2StateChanged
        // TODO add your handling code here:
        if (componentMap == null)
            return;
        
        final int row = eBoardTabPane2.getSelectedIndex();
        int prevTabIdx = (row == 0 ? 1 : 0);
        JButton saveButton = (JButton)componentMap.get("btn_Save" + (2 + prevTabIdx));
        if (saveButton.isEnabled()) {
            JOptionPane.showMessageDialog(this, 
                    ((String[])Globals.DialogMSGList.get(SAVE_OR_CANEL_DIALOG.ordinal()))[ourLang]);
            eBoardTabPane2.setSelectedIndex(prevTabIdx);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                selectSpecificTab(row, 1);
            }
        });
    }//GEN-LAST:event_eBoardTabPane2StateChanged
>>>>>>> osparking/master

     // <editor-fold defaultstate="collapsed" desc="Generated Code">  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancel0;
    private javax.swing.JButton btn_Cancel1;
    private javax.swing.JButton btn_Cancel2;
    private javax.swing.JButton btn_Cancel3;
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_Save0;
    private javax.swing.JButton btn_Save1;
    private javax.swing.JButton btn_Save2;
    private javax.swing.JButton btn_Save3;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JComboBox combo_ContentType0;
    private javax.swing.JComboBox combo_ContentType1;
    private javax.swing.JComboBox combo_ContentType2;
    private javax.swing.JComboBox combo_ContentType3;
    private javax.swing.JComboBox combo_DisplayEffect0;
    private javax.swing.JComboBox combo_DisplayEffect1;
    private javax.swing.JComboBox combo_DisplayEffect2;
    private javax.swing.JComboBox combo_DisplayEffect3;
    private javax.swing.JComboBox combo_TextColor0;
    private javax.swing.JComboBox combo_TextColor1;
    private javax.swing.JComboBox combo_TextColor2;
    private javax.swing.JComboBox combo_TextColor3;
    private javax.swing.JComboBox combo_TextFont0;
    private javax.swing.JComboBox combo_TextFont1;
    private javax.swing.JComboBox combo_TextFont2;
    private javax.swing.JComboBox combo_TextFont3;
    private javax.swing.JPanel eBoardPanel0;
    private javax.swing.JPanel eBoardPanel1;
    private javax.swing.JPanel eBoardPanel2;
    private javax.swing.JPanel eBoardPanel3;
    private javax.swing.JTabbedPane eBoardTabPane1;
    private javax.swing.JTabbedPane eBoardTabPane2;
    private javax.swing.JTabbedPane eboardTabbedPanel;
<<<<<<< HEAD
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
=======
>>>>>>> osparking/master
    private javax.swing.JLabel label_Color0;
    private javax.swing.JLabel label_Color1;
    private javax.swing.JLabel label_Color2;
    private javax.swing.JLabel label_Color3;
    private javax.swing.JLabel label_ContentType0;
    private javax.swing.JLabel label_ContentType1;
    private javax.swing.JLabel label_ContentType2;
    private javax.swing.JLabel label_ContentType3;
    private javax.swing.JLabel label_Effect0;
    private javax.swing.JLabel label_Effect1;
    private javax.swing.JLabel label_Effect2;
    private javax.swing.JLabel label_Effect3;
    private javax.swing.JLabel label_Font0;
    private javax.swing.JLabel label_Font1;
    private javax.swing.JLabel label_Font2;
    private javax.swing.JLabel label_Font3;
    private javax.swing.JLabel label_MSG0;
    private javax.swing.JLabel label_MSG1;
    private javax.swing.JLabel label_MSG2;
    private javax.swing.JLabel label_MSG3;
    private javax.swing.JTextField tf_VerbatimContent0;
    private javax.swing.JTextField tf_VerbatimContent1;
    private javax.swing.JTextField tf_VerbatimContent2;
    private javax.swing.JTextField tf_VerbatimContent3;
    private javax.swing.JPanel wholePanel;
    // End of variables declaration//GEN-END:variables
   // </editor-fold>
    
    /**
<<<<<<< HEAD
     *  Decide whether to use the verbatim text field after checking the content type.
     */
    public void checkContentType(int index){
        if (((JComboBox)getComponentByName("combo_ContentType" + index)).getSelectedIndex() 
                == EBD_ContentType.VERBATIM.ordinal())
        {
            ((JTextField) getComponentByName("tf_VerbatimContent" + index)).setEnabled(true);
            ((JTextField) getComponentByName("tf_VerbatimContent" + index)).setText(
=======
     *  decide whether or not to use the TextField to compare the contentType.
     */
    public void checkContentType(int index){
        if(((JComboBox) getComponentByName(componentMap, "combo_ContentType" + index)).getSelectedIndex() 
                == EBD_ContentType.VERBATIM.ordinal())
        {
            ((JTextField) getComponentByName(componentMap, "tf_VerbatimContent" + index)).setEnabled(true);
            ((JTextField) getComponentByName(componentMap, "tf_VerbatimContent" + index)).setText(
>>>>>>> osparking/master
                ControlGUI.EBD_DisplaySettings[index].verbatimContent);
        }
        else
        {
<<<<<<< HEAD
            ((JTextField) getComponentByName("tf_VerbatimContent" + index)).setEnabled(false);
            ((JTextField) getComponentByName("tf_VerbatimContent" + index)).setText(null);
=======
            ((JTextField) getComponentByName(componentMap, "tf_VerbatimContent" + index)).setEnabled(false);
            ((JTextField) getComponentByName(componentMap, "tf_VerbatimContent" + index)).setText(null);
>>>>>>> osparking/master
        }
    }
    
    /**
     * Check the error of the panel.
     * 
     * @param usage_row  Panel that is currently selected
     * @return  <b>true</b> When there is no error in the data inputted to the  <code>panel</code>, 
     * <b>false</b> otherwise
     */
    public boolean checkPanel(EBD_DisplayUsage usage_row){
        boolean result = false;
        
        byte contentType = (byte) ((JComboBox)  getComponentByName(
<<<<<<< HEAD
                "combo_ContentType"+ usage_row.ordinal())).getSelectedIndex();
        
        String verbatimContent = ((JTextField) getComponentByName(
                "tf_VerbatimContent" + usage_row.ordinal())).getText().trim();
        
        byte displayPattern = (byte) ((JComboBox)  getComponentByName(
                "combo_DisplayEffect" + usage_row.ordinal())).getSelectedIndex();

        byte textFont = (byte) ((JComboBox)  getComponentByName(
                "combo_TextFont" + usage_row.ordinal())).getSelectedIndex();
        
        
        byte textColor = (byte) ((JComboBox)  getComponentByName(
                "combo_TextColor" + usage_row.ordinal())).getSelectedIndex();
=======
                componentMap, "combo_ContentType"+ usage_row.ordinal())).getSelectedIndex();
        
        String verbatimContent = ((JTextField) getComponentByName(
                componentMap, "tf_VerbatimContent" + usage_row.ordinal())).getText().trim();
        
        byte displayPattern = (byte) ((JComboBox)  getComponentByName(
                componentMap, "combo_DisplayEffect" + usage_row.ordinal())).getSelectedIndex();

        byte textFont = (byte) ((JComboBox)  getComponentByName(
                componentMap, "combo_TextFont" + usage_row.ordinal())).getSelectedIndex();
        
        
        byte textColor = (byte) ((JComboBox)  getComponentByName(
                componentMap, "combo_TextColor" + usage_row.ordinal())).getSelectedIndex();
>>>>>>> osparking/master

        
        if(contentType != ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].contentType.ordinal())
            result = true;
        if(!verbatimContent.equals(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].verbatimContent))
            result = true;
        if(displayPattern != ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].displayPattern.ordinal())
            result = true;
        if(textFont != ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textFont.ordinal())
            result = true;
        if(textColor != ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textColor.ordinal()) 
            result = true;

        return result;
    }
    
    /**
     * Enter the panel has been selected as the contents that were stored in the Database.
     * 
     * @param currentTab  Panel that is currently selected
     */
<<<<<<< HEAD
    public void cancelBtnClick(){
        ((JComboBox) getComponentByName("combo_ContentType" + currentTab.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].contentType.ordinal());
        ((JTextField) getComponentByName("tf_VerbatimContent" + currentTab.ordinal()))
                .setText(ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].verbatimContent);
        ((JComboBox) getComponentByName("combo_TextColor" + currentTab.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].textColor.ordinal());
        ((JComboBox) getComponentByName("combo_DisplayEffect" + currentTab.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].displayPattern.ordinal());
        ((JComboBox) getComponentByName("combo_TextFont" + currentTab.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].textFont.ordinal());
        
        changeEnabled_of_SaveCancelButtons(false);
    }
    
    /**
     * When another panel doeeot select the property value to match the corresponding panel setting.
     * 
     * @param usage_row  Panel that is currently selected
     */
    public void selectSpecificTab(EBD_DisplayUsage usage_row){
        
        ((JComboBox) getComponentByName("combo_ContentType"+ usage_row.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].contentType.ordinal());
        
        if(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].contentType.ordinal() 
                == OSP_enums.EBD_ContentType.VERBATIM.ordinal())
            ((JTextField) getComponentByName("tf_VerbatimContent"+ usage_row.ordinal())).setEnabled(true);
        else
            ((JTextField) getComponentByName("tf_VerbatimContent"+ usage_row.ordinal())).setEnabled(false);
        
        
        ((JTextField) getComponentByName("tf_VerbatimContent"+ usage_row.ordinal()))
                .setText(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].verbatimContent);
        
        ((JComboBox)  getComponentByName("combo_DisplayEffect"+ usage_row.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].displayPattern.ordinal());
        
        ((JComboBox)  getComponentByName("combo_TextColor"+ usage_row.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textColor.ordinal());
        
        ((JComboBox)  getComponentByName("combo_TextFont"+ usage_row.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textFont.ordinal());
        
        changeEnabled_of_SaveCancelButtons(false);
    }
    
    /**
     *  
     * 
     * @param name
     * @return 
     */
    private Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) 
            return (Component) componentMap.get(name);
        else 
            return null;
    }  
=======
    public void cancelBtnClick(EBD_DisplayUsage usage_row){
        ((JComboBox) getComponentByName(componentMap, "combo_ContentType" + usage_row.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].contentType.ordinal());
        ((JTextField) getComponentByName(componentMap, "tf_VerbatimContent" + usage_row.ordinal()))
                .setText(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].verbatimContent);
        ((JComboBox) getComponentByName(componentMap, "combo_TextColor" + usage_row.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textColor.ordinal());
        ((JComboBox) getComponentByName(componentMap, "combo_DisplayEffect" + usage_row.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].displayPattern.ordinal());
        ((JComboBox) getComponentByName(componentMap, "combo_TextFont" + usage_row.ordinal()))
                .setSelectedIndex(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textFont.ordinal());
        changedControls.clear();
        ChangeSettings.changeEnabled_of_SaveCancelButtons(
                (JButton) getComponentByName(componentMap, "btn_Save" + usage_row.ordinal()),
                (JButton) getComponentByName(componentMap, "btn_Cancel" + usage_row.ordinal()), btn_Exit, changedControls.size());
    }
    
    /**
     * When another panel doeeot select the property value to match the corresponding panel setting.
     * 
     * @param usage_row  Panel that is currently selected
     */
    public void selectSpecificTab(int row, int column){
        int ordinal = row + column * 2;
        JButton saveButton = (JButton)componentMap.get("btn_Save" + ordinal);
        if (!saveButton.isEnabled()) { 
            ((JComboBox) getComponentByName(componentMap, "combo_ContentType"+ ordinal))
                    .setSelectedIndex(ControlGUI.EBD_DisplaySettings[ordinal].contentType.ordinal());

            if(ControlGUI.EBD_DisplaySettings[ordinal].contentType.ordinal() 
                    == OSP_enums.EBD_ContentType.VERBATIM.ordinal())
                ((JTextField) getComponentByName(componentMap, "tf_VerbatimContent"+ ordinal)).setEnabled(true);
            else
                ((JTextField) getComponentByName(componentMap, "tf_VerbatimContent"+ ordinal)).setEnabled(false);


            ((JTextField) getComponentByName(componentMap, "tf_VerbatimContent"+ ordinal))
                    .setText(ControlGUI.EBD_DisplaySettings[ordinal].verbatimContent);

            ((JComboBox)  getComponentByName(componentMap, "combo_DisplayEffect"+ ordinal))
                    .setSelectedIndex(ControlGUI.EBD_DisplaySettings[ordinal].displayPattern.ordinal());

            ((JComboBox)  getComponentByName(componentMap, "combo_TextColor"+ ordinal))
                    .setSelectedIndex(ControlGUI.EBD_DisplaySettings[ordinal].textColor.ordinal());

            ((JComboBox)  getComponentByName(componentMap, "combo_TextFont"+ ordinal))
                    .setSelectedIndex(ControlGUI.EBD_DisplaySettings[ordinal].textFont.ordinal());

        }
    }
>>>>>>> osparking/master
    
    /**
     * Stored in the database.
     * 
     * @param usage_row Panel that is currently selected
     */
    public void saveDataBase(EBD_DisplayUsage usage_row){
        JComboBox comboBox = null;
        Connection conn = null;
        PreparedStatement updateSettings = null;
        
        String verbatimStr 
<<<<<<< HEAD
                = ((JTextField) getComponentByName("tf_VerbatimContent" + usage_row.ordinal())).getText().trim();
        
        comboBox = (JComboBox)getComponentByName("combo_ContentType" + usage_row.ordinal());
=======
                = ((JTextField) getComponentByName(componentMap, "tf_VerbatimContent" + usage_row.ordinal())).getText().trim();
        
        comboBox = (JComboBox)getComponentByName(componentMap, "combo_ContentType" + usage_row.ordinal());
>>>>>>> osparking/master
        
        EBD_ContentType typeItem = (EBD_ContentType)
                (((ConvComboBoxItem)comboBox.getSelectedItem()).getValue());
        
<<<<<<< HEAD
        comboBox = (JComboBox) getComponentByName("combo_DisplayEffect" + usage_row.ordinal());
        EBD_Effects patternItem = (EBD_Effects)
                (((ConvComboBoxItem)comboBox.getSelectedItem()).getValue());
        
        comboBox = (JComboBox) getComponentByName("combo_TextColor" + usage_row.ordinal());
        EBD_Colors colorItem = (EBD_Colors)
                (((ConvComboBoxItem)comboBox.getSelectedItem()).getValue());
        
        comboBox = (JComboBox) getComponentByName("combo_TextFont" + usage_row.ordinal());
=======
        comboBox = (JComboBox) getComponentByName(componentMap, "combo_DisplayEffect" + usage_row.ordinal());
        EBD_Effects patternItem = (EBD_Effects)
                (((ConvComboBoxItem)comboBox.getSelectedItem()).getValue());
        
        comboBox = (JComboBox) getComponentByName(componentMap, "combo_TextColor" + usage_row.ordinal());
        EBD_Colors colorItem = (EBD_Colors)
                (((ConvComboBoxItem)comboBox.getSelectedItem()).getValue());
        
        comboBox = (JComboBox) getComponentByName(componentMap, "combo_TextFont" + usage_row.ordinal());
>>>>>>> osparking/master
        EBD_Fonts fontItem = (EBD_Fonts)
                (((ConvComboBoxItem)comboBox.getSelectedItem()).getValue());
        
        int result = 0;
        try {
            StringBuilder sb = new StringBuilder("Update eboard_settings SET ");
            sb.append("verbatim_content = ?, content_type = ?, display_pattern = ?");
            sb.append(", text_color = ?, text_font = ? WHERE usage_row = ?");

            conn = JDBCMySQL.getConnection();
            updateSettings = conn.prepareStatement(sb.toString());

            int pIndex = 1;
            updateSettings.setString(pIndex++, verbatimStr);
            updateSettings.setInt(pIndex++, typeItem.ordinal());
            updateSettings.setInt(pIndex++, patternItem.ordinal());
            updateSettings.setInt(pIndex++, colorItem.ordinal());
            updateSettings.setInt(pIndex++, fontItem.ordinal());
            updateSettings.setInt(pIndex++, usage_row.getVal());

            result = updateSettings.executeUpdate();
             
        } catch (SQLException ex) {
            Globals.logParkingException(Level.SEVERE, ex, "while saving e-board settings");  
        } finally{
            closeDBstuff(conn, updateSettings, null, "e-board settings modification");
            if (result == 1) {
                //<editor-fold desc="-- Log system settings change if set to do so">
                String currVerbatimStr = ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].verbatimContent;
                if (typeItem == EBD_ContentType.VERBATIM && !currVerbatimStr.equals(verbatimStr)) {
                    logParkingOperation(OpLogLevel.EBDsettingsChange,
                            "E-Board Settings Change, Verbatim Message: " + currVerbatimStr + " => " + verbatimStr);
                }
                //</editor-fold>
                ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].verbatimContent = verbatimStr;
                ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].contentType = typeItem;
                ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].displayPattern = patternItem;
                ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textColor = colorItem;
                ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textFont = fontItem;
                
            } else {
<<<<<<< HEAD
                JOptionPane.showMessageDialog(this, "This e-board settings update saving DB operation failed.",
                    "DB Update Operation Failure", JOptionPane.ERROR_MESSAGE);
=======
                JOptionPane.showMessageDialog(this, 
                        ((String[])Globals.DialogMSGList.get(E_BOARD_SAVE_FAIL_DIALOG.ordinal()))[ourLang],
                        ((String[])Globals.DialogTitleList.get(WARING_DIALOGTITLE.ordinal()))[ourLang], 
                        JOptionPane.ERROR_MESSAGE);
>>>>>>> osparking/master
            }
        }

        readEBoardSettings(ControlGUI.EBD_DisplaySettings);

        if (mainForm != null) { // when settings frame invoked alone, main form is null
            if (usage_row.ordinal() == DEFAULT_TOP_ROW.ordinal() 
                    || usage_row.ordinal() == DEFAULT_BOTTOM_ROW.ordinal()) 
            {
                for (byte gateNo = 1; gateNo <= gateCount; gateNo++) {
<<<<<<< HEAD
                    if (IDevice.isConnected(mainForm.getDeviceManagers()[E_Board.ordinal()][gateNo], E_Board, gateNo)) 
                    {
                        OSP_enums.EBD_Row row = OSP_enums.EBD_Row.BOTTOM;
                        
                        if (usage_row.ordinal() == DEFAULT_TOP_ROW.ordinal())
                            row = OSP_enums.EBD_Row.TOP;
                        
                        sendEBoardDefaultSetting(mainForm, gateNo, row);
=======
                    if (isConnected(mainForm.getDeviceManagers()[E_Board.ordinal()][gateNo].getSocket())) {
                        sendEBoardDefaultSetting(mainForm, gateNo, (byte) usage_row.ordinal());
>>>>>>> osparking/master
                    }
                }
            }
        }
    }
    
    /**
     * Generating the notification window.
     * @param usage_row information on usage(normal time vs. car arrival) and E-Board row number.
     */
    public void showDialog(EBD_DisplayUsage usage_row){
        String[] message = new String[1];

<<<<<<< HEAD
        if (textFieldCheck(message, usage_row))
        {
            if (!overlapCheck(message, usage_row))
            {
                Object[] options = {"Save", "Cancel"};
                Object[] save_Options = {"Confirm"};
                int n = JOptionPane.showOptionDialog(
                            rootPane, 
                            message, 
                            "Confirmation", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, 
                            null, 
                            options, 
                            options[0]);
=======
        if(textFieldCheck(message, usage_row))
        {
            if (!overlapCheck(message, usage_row))
            {
                int n = JOptionPane.showOptionDialog(
                            rootPane, 
                            message, 
                            ((String[])Globals.DialogTitleList.get(SAVE_DIALOGTITLE.ordinal()))[ourLang], 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, 
                            null, 
                            null, 
                            null);
>>>>>>> osparking/master

                if(n == JOptionPane.YES_OPTION)
                {
                    saveDataBase(usage_row);
                    JOptionPane.showOptionDialog(
                        rootPane, 
<<<<<<< HEAD
                        "Saved", 
                        "Confirm", 
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        save_Options,
                        save_Options[0]);
                    changeEnabled_of_SaveCancelButtons(false);
                }
                else if(n == JOptionPane.NO_OPTION) {
                    eboardTabbedPanel.setSelectedIndex(usage_row.ordinal() < 2 ? 0 : 1);
                    if (usage_row.ordinal() % 2 == 0)
                    {
                        ((JTabbedPane) eboardTabbedPanel
                                .getSelectedComponent()).setSelectedIndex(OSP_enums.EBD_Row.TOP.getValue());
=======
                        ((String[])Globals.DialogMSGList.get(SAVE_DIALOG.ordinal()))[ourLang], 
                        ((String[])Globals.DialogTitleList.get(CONFIRM_DIALOGTITLE.ordinal()))[ourLang], 
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                    );
                    changedControls.clear();
                    ChangeSettings.changeEnabled_of_SaveCancelButtons(
                            (JButton) getComponentByName(componentMap, "btn_Save" + usage_row.ordinal()),
                            (JButton) getComponentByName(componentMap, "btn_Cancel" + usage_row.ordinal()), btn_Exit, changedControls.size());
                }
                else if(n == JOptionPane.NO_OPTION) {
                    eboardTabbedPanel.setSelectedIndex(usage_row.ordinal() <= 2 ? 0 : 1);
                    if(usage_row.ordinal()%2 == 1)
                    {
                        ((JTabbedPane) eboardTabbedPanel
                                .getSelectedComponent()).setSelectedIndex(TOP_ROW.ordinal());
>>>>>>> osparking/master
                    }
                    else
                    {
                        ((JTabbedPane) eboardTabbedPanel
<<<<<<< HEAD
                                .getSelectedComponent()).setSelectedIndex(OSP_enums.EBD_Row.BOTTOM.getValue());
=======
                                .getSelectedComponent()).setSelectedIndex(BOTTOM_ROW.ordinal());
>>>>>>> osparking/master
                    }
                }
            }
        }
        else
        {
            JOptionPane.showOptionDialog(
                    rootPane, 
                    message[0], 
<<<<<<< HEAD
                    "Error", 
=======
                    ((String[])Globals.DialogMSGList.get(WARING_DIALOGTITLE.ordinal()))[ourLang], 
>>>>>>> osparking/master
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    null,
                    null);
            eboardTabbedPanel.setSelectedIndex(usage_row.ordinal() < 2 ? 0 : 1);
            if(usage_row.ordinal() % 2 == 0) // top rows
                ((JTabbedPane) eboardTabbedPanel.getSelectedComponent())
<<<<<<< HEAD
                        .setSelectedIndex(OSP_enums.EBD_Row.TOP.ordinal());
            else
                ((JTabbedPane) eboardTabbedPanel.getSelectedComponent())
                        .setSelectedIndex(OSP_enums.EBD_Row.BOTTOM.ordinal());
=======
                        .setSelectedIndex(DisplayArea.TOP_ROW.ordinal());
            else
                ((JTabbedPane) eboardTabbedPanel.getSelectedComponent())
                        .setSelectedIndex(DisplayArea.BOTTOM_ROW.ordinal());
>>>>>>> osparking/master
        }
    }
    
    /**
     * Checks if any field of E-Board settings panel changed and accumulates changed fields' contents.
     * 
     * @param errorMsg  the contents of the error             
     * @param usage_row Panel that is currently selected
     * @return  true when 
     */
    private boolean overlapCheck(String[] errorMsg, EBD_DisplayUsage usage_row){
        boolean result;
        StringBuilder wrongFields = new StringBuilder();
        
        byte contentType = (byte) ((JComboBox)  getComponentByName(
<<<<<<< HEAD
                "combo_ContentType"+ usage_row.ordinal())).getSelectedIndex();
        
        String verbatimContent = ((JTextField) getComponentByName(
                "tf_VerbatimContent" + usage_row.ordinal())).getText();
        
        byte displayPattern = (byte) ((JComboBox)  getComponentByName(
                "combo_DisplayEffect" + usage_row.ordinal())).getSelectedIndex();

        byte textFont = (byte) ((JComboBox)  getComponentByName(
                "combo_TextFont" + usage_row.ordinal())).getSelectedIndex();
        
        
        byte textColor = (byte) ((JComboBox)  getComponentByName(
                "combo_TextColor" + usage_row.ordinal())).getSelectedIndex();

        String wrongText = " Changes \n\n";
        
        if (contentType != ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].contentType.ordinal()) 
        {
             wrongFields.append(" * Content Type \n"); 
             wrongFields.append("    - Current   : " 
                     + ((JComboBox)  getComponentByName("combo_ContentType" + usage_row.ordinal()))
                             .getItemAt(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()]
                                     .contentType.ordinal()) +"\n");
             wrongFields.append("    - Modified : " 
                     + ((JComboBox)  getComponentByName("combo_ContentType" + usage_row.ordinal()))
=======
                componentMap, "combo_ContentType"+ usage_row.ordinal())).getSelectedIndex();
        
        String verbatimContent = ((JTextField) getComponentByName(
                componentMap, "tf_VerbatimContent" + usage_row.ordinal())).getText();
        
        byte displayPattern = (byte) ((JComboBox)  getComponentByName(
                componentMap, "combo_DisplayEffect" + usage_row.ordinal())).getSelectedIndex();

        byte textFont = (byte) ((JComboBox)  getComponentByName(
                componentMap, "combo_TextFont" + usage_row.ordinal())).getSelectedIndex();
        
        
        byte textColor = (byte) ((JComboBox)  getComponentByName(
                componentMap, "combo_TextColor" + usage_row.ordinal())).getSelectedIndex();

        String wrongText = ((String[])Globals.DialogMSGList.get(CHANGE_DIALOG.ordinal()))[ourLang] + "\n\n";
        
        if (contentType != ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].contentType.ordinal()) 
        {
             wrongFields.append(" * "+ ((String[])Globals.LabelsText.get(CONTENT_TYPE_LABEL.ordinal()))[ourLang] +" \n"); 
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(CURRENT_DIALOG.ordinal()))[ourLang] +" : " 
                     + ((JComboBox)  getComponentByName(componentMap, "combo_ContentType" + usage_row.ordinal()))
                             .getItemAt(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()]
                                     .contentType.ordinal()) +"\n");
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(MODIFY_DIALOG.ordinal()))[ourLang] +" : " 
                     + ((JComboBox)  getComponentByName(componentMap, "combo_ContentType" + usage_row.ordinal()))
>>>>>>> osparking/master
                             .getItemAt(contentType) +"\n");
             wrongFields.append("\n");
        }
        
        if(!verbatimContent.equals(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].verbatimContent)
                && contentType == OSP_enums.EBD_ContentType.VERBATIM.ordinal()) 
        {
<<<<<<< HEAD
             wrongFields.append(" * Message \n"); 
             wrongFields.append("    - Current   : ")
                     .append(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].verbatimContent).append("\n");
             wrongFields.append("    - Modified : ").append(verbatimContent).append("\n");
=======
             wrongFields.append(" * "+ ((String[])Globals.LabelsText.get(MSG_LABEL.ordinal()))[ourLang] +" \n"); 
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(CURRENT_DIALOG.ordinal()))[ourLang] +" : ")
                     .append(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].verbatimContent).append("\n");
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(MODIFY_DIALOG.ordinal()))[ourLang] +" : ").append(verbatimContent).append("\n");
>>>>>>> osparking/master
             wrongFields.append("\n");
        }

        if(displayPattern != ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].displayPattern.ordinal())
        {
<<<<<<< HEAD
             wrongFields.append(" * Effect \n"); 
             wrongFields.append("    - Current   : " 
                    + ((JComboBox)  getComponentByName("combo_DisplayEffect"
                            + usage_row.ordinal())).getItemAt(
                                    ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].displayPattern.ordinal()) +"\n");
             wrongFields.append("    - Modified : " 
                    + ((JComboBox)  getComponentByName("combo_DisplayEffect" + usage_row.ordinal()))
=======
             wrongFields.append(" * "+ ((String[])Globals.LabelsText.get(EFFECT_LABEL.ordinal()))[ourLang] +" \n"); 
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(CURRENT_DIALOG.ordinal()))[ourLang] +" : " 
                    + ((JComboBox)  getComponentByName(componentMap, "combo_DisplayEffect"
                            + usage_row.ordinal())).getItemAt(
                                    ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].displayPattern.ordinal()) +"\n");
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(MODIFY_DIALOG.ordinal()))[ourLang] +" : " 
                    + ((JComboBox)  getComponentByName(componentMap, "combo_DisplayEffect" + usage_row.ordinal()))
>>>>>>> osparking/master
                            .getItemAt(displayPattern) +"\n");
            wrongFields.append("\n");
        }

        if(textFont != ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textFont.ordinal())
        {
<<<<<<< HEAD
             wrongFields.append(" * Font \n"); 
             wrongFields.append("    - Current   : " 
                    + ((JComboBox)  getComponentByName("combo_TextFont" + usage_row.ordinal()))
                            .getItemAt(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textFont.ordinal()) +"\n");
             wrongFields.append("    - Modified : " 
                    + ((JComboBox)  getComponentByName("combo_TextFont" + usage_row.ordinal()))
=======
             wrongFields.append(" * "+ ((String[])Globals.LabelsText.get(FONT_LABEL.ordinal()))[ourLang] +" \n"); 
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(CURRENT_DIALOG.ordinal()))[ourLang] +" : " 
                    + ((JComboBox)  getComponentByName(componentMap, "combo_TextFont" + usage_row.ordinal()))
                            .getItemAt(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textFont.ordinal()) +"\n");
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(MODIFY_DIALOG.ordinal()))[ourLang] +" : " 
                    + ((JComboBox)  getComponentByName(componentMap, "combo_TextFont" + usage_row.ordinal()))
>>>>>>> osparking/master
                            .getItemAt(textFont) +"\n");
            wrongFields.append("\n");
        }

        if(textColor != ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textColor.ordinal())
        {
<<<<<<< HEAD
             wrongFields.append(" * Color \n"); 
             wrongFields.append("    - Current   : " 
                    + ((JComboBox)  getComponentByName("combo_TextColor" + usage_row.ordinal()))
                            .getItemAt(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textColor.ordinal()) +"\n");
             wrongFields.append("    - Modified : " 
                    + ((JComboBox)  getComponentByName("combo_TextColor" + usage_row.ordinal()))
=======
             wrongFields.append(" * "+ ((String[])Globals.LabelsText.get(COLOR_LABEL.ordinal()))[ourLang] +" \n"); 
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(CURRENT_DIALOG.ordinal()))[ourLang] +" : " 
                    + ((JComboBox)  getComponentByName(componentMap, "combo_TextColor" + usage_row.ordinal()))
                            .getItemAt(ControlGUI.EBD_DisplaySettings[usage_row.ordinal()].textColor.ordinal()) +"\n");
             wrongFields.append("    - "+ ((String[])Globals.DialogMSGList.get(MODIFY_DIALOG.ordinal()))[ourLang] +" : " 
                    + ((JComboBox)  getComponentByName(componentMap, "combo_TextColor" + usage_row.ordinal()))
>>>>>>> osparking/master
                            .getItemAt(textColor) +"\n");
            wrongFields.append("\n");
        }
        
        if (wrongFields.length() == 0)
        {
            result = true;
        } 
        else 
        {
            result = false;
            wrongFields = wrongFields.insert(0, wrongText);
        }
        errorMsg[0] = wrongFields.toString();
        return result;
    }
    
    /**
     * verbatimContent input checking.
     * @param errorMsg      the contents of the error
     * @param usage_row     Panel that is currently selected
     * @return  errorMsg
     */
    private boolean textFieldCheck(String[] errorMsg, EBD_DisplayUsage usage_row){
        boolean result = false;
        
        
        StringBuilder wrongFields = new StringBuilder();
        
<<<<<<< HEAD
        if(((JComboBox) getComponentByName("combo_ContentType" + usage_row.ordinal()))
                .getSelectedIndex() == OSP_enums.EBD_ContentType.VERBATIM.ordinal()){
            String message = ((JTextField) getComponentByName(
                    "tf_VerbatimContent" +  usage_row.ordinal())).getText().trim();
            if (message.length() <= 0)
            {
                wrongFields.append("  - Please enter a message\n");
                ((JTextField) getComponentByName(
                    "tf_VerbatimContent" +  usage_row.ordinal())).requestFocus();
=======
        if(((JComboBox) getComponentByName(componentMap, "combo_ContentType" + usage_row.ordinal()))
                .getSelectedIndex() == OSP_enums.EBD_ContentType.VERBATIM.ordinal()){
            String message = ((JTextField) getComponentByName(
                    componentMap, "tf_VerbatimContent" +  usage_row.ordinal())).getText().trim();
            if (message.length() <= 0)
            {
                wrongFields.append(((String[])Globals.DialogMSGList.get(E_BOARD_NO_INPUT_MSG_DIALOG.ordinal()))[ourLang] + "\n");
                ((JTextField) getComponentByName(
                    componentMap, "tf_VerbatimContent" +  usage_row.ordinal())).requestFocus();
>>>>>>> osparking/master
            }
        }
        if (wrongFields.length() == 0) 
        {
            result = true;
        } 
        errorMsg[0] = wrongFields.toString();
        return result;
    }    

    private void addContentTypeItems() {
        for (EBD_DisplayUsage usage_row : EBD_DisplayUsage.values()) {
            JComboBox comboBox = ((JComboBox) getComponentByName(
<<<<<<< HEAD
                    "combo_ContentType"+ usage_row.ordinal()));
=======
                    componentMap, "combo_ContentType"+ usage_row.ordinal()));
>>>>>>> osparking/master
            
            comboBox.removeAllItems();
            for (EBD_ContentType aType : EBD_ContentType.values()) {
                comboBox.addItem(new ConvComboBoxItem(aType, aType.getLabel()));
<<<<<<< HEAD
            }
        }
    }
=======
                }
            }
        }
>>>>>>> osparking/master

    private void addTextColorItems() {
        for (EBD_DisplayUsage usage_row : EBD_DisplayUsage.values()) {
            JComboBox comboBox = ((JComboBox) getComponentByName(
<<<<<<< HEAD
                    "combo_TextColor"+ usage_row.ordinal()));
            
            comboBox.removeAllItems();
            for (EBD_Colors aColor : EBD_Colors.values()) {                                
                comboBox.addItem(new ConvComboBoxItem(aColor, aColor.name()));
=======
                    componentMap, "combo_TextColor"+ usage_row.ordinal()));
            
            comboBox.removeAllItems();
            for (EBD_Colors aColor : EBD_Colors.values()) {                                
                comboBox.addItem(new ConvComboBoxItem(aColor, aColor.getLabel()));
>>>>>>> osparking/master
            }
        }
    }

    private void addDisplayEffectItems() {
        for (EBD_DisplayUsage usage_row : EBD_DisplayUsage.values()) {
            JComboBox comboBox = ((JComboBox) getComponentByName(
<<<<<<< HEAD
                    "combo_DisplayEffect"+ usage_row.ordinal()));
=======
                    componentMap, "combo_DisplayEffect"+ usage_row.ordinal()));
>>>>>>> osparking/master
            
            comboBox.removeAllItems();
            for (EBD_Effects anEffect : EBD_Effects.values()) {

                //<editor-fold desc="-- determine label for each item value">
                String label;
                
                switch (anEffect) {
                    case BLINKING:
<<<<<<< HEAD
                        label = "Blinking";
                        break;
                        
                    case LTOR_FLOW:
                        label = "L to R Flow";
                        break;
                        
                    case RTOL_FLOW:
                        label = "R to L Flow";
                        break;
                        
                    case STILL_FRAME:
                        label = "Still Frame";
=======
                        label = ((String[])Globals.ComboBoxItemList.get(BLINKING_CB_ITEM.ordinal()))[ourLang];
                        break;
                        
                    case LTOR_FLOW:
                        label = ((String[])Globals.ComboBoxItemList.get(LTOR_CB_ITEM.ordinal()))[ourLang];
                        break;
                        
                    case RTOL_FLOW:
                        label = ((String[])Globals.ComboBoxItemList.get(RTOL_CB_ITEM.ordinal()))[ourLang];
                        break;
                        
                    case STILL_FRAME:
                        label = ((String[])Globals.ComboBoxItemList.get(STILL_FRAME_CB_ITEM.ordinal()))[ourLang];
>>>>>>> osparking/master
                        break;
                        
                    default:
                        label = "";
                        break;
                }
                //</editor-fold>
                            
                comboBox.addItem(new ConvComboBoxItem(anEffect, label));
            }
        }
    }

    private void addTextFontItems() {
        for (EBD_DisplayUsage usage_row : EBD_DisplayUsage.values()) {
            JComboBox comboBox = ((JComboBox) getComponentByName(
<<<<<<< HEAD
                    "combo_TextFont"+ usage_row.ordinal()));
=======
                    componentMap, "combo_TextFont"+ usage_row.ordinal()));
>>>>>>> osparking/master
            
            comboBox.removeAllItems();
            for (EBD_Fonts aFont : EBD_Fonts.values()) {
                
                //<editor-fold desc="-- determine label for each item value">
                String label = "";
                
                switch (aFont) {
                    case Dialog:
                        label = "Dialog";
                        break;
                        
                    case DialogInput:
                        label = "Dialog Input";
                        break;
                        
                    case Microsoft_NeoGothic:
                        label = "Microsoft Neo Gothic";
                        break;
                        
                    case Monospaced:
                        label = "Monospaced";
                        break;
                        
                    case Sans_Serif:
                        label = "Sans Serif";
                        break;
                        
                    default:
                        label = "";
                        break;
                }
                //</editor-fold>                
                comboBox.addItem(new ConvComboBoxItem(aFont, label));
            }
        }
    }

    private void setButtonEnabledIfContentTypeChanged(EBD_DisplayUsage usage) {
<<<<<<< HEAD
        JComboBox typeCBox = (JComboBox) getComponentByName("combo_ContentType" + usage.ordinal());
        EBD_ContentType selectedType 
                = (EBD_ContentType) ((ConvComboBoxItem)typeCBox.getSelectedItem()).getValue();
        if (selectedType == ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].contentType) {
            changeEnabled_of_SaveCancelButtons(false);
        }
        else {
            changeEnabled_of_SaveCancelButtons(true);
        }
    }

    private void setButtonEnabledIfColorChanged(EBD_DisplayUsage usage) {
        JComboBox colorCBox = (JComboBox) getComponentByName("combo_TextColor" + usage.ordinal());
        EBD_Colors selectedColor = (EBD_Colors) ((ConvComboBoxItem)colorCBox.getSelectedItem()).getValue();
        
        if (selectedColor == ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].textColor) {
            changeEnabled_of_SaveCancelButtons(false);
        }
        else {
            changeEnabled_of_SaveCancelButtons(true);
        }
    }

    private void setButtonEnabledIfEffectChanged(EBD_DisplayUsage usage) {
        JComboBox effectCBox = (JComboBox) getComponentByName("combo_DisplayEffect" + usage.ordinal());
        EBD_Effects selectedEffect = (EBD_Effects) ((ConvComboBoxItem)effectCBox.getSelectedItem()).getValue();
        
        if (selectedEffect == ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].displayPattern) {
            changeEnabled_of_SaveCancelButtons(false);
        }
        else {
            changeEnabled_of_SaveCancelButtons(true);
        }
    }

    private void setButtonEnabledIfFontChanged(EBD_DisplayUsage usage) {
        JComboBox fontCBox = (JComboBox) getComponentByName("combo_TextFont" + usage.ordinal());
        EBD_Fonts selectedFont = (EBD_Fonts) ((ConvComboBoxItem)fontCBox.getSelectedItem()).getValue();
        
        if (selectedFont == ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].textFont) {
            changeEnabled_of_SaveCancelButtons(false);
        }
        else {
            changeEnabled_of_SaveCancelButtons(true);
        }
    }

    private void changeButtonEnabled_IfVebarimChanged(int index) {
        JTextField verbatimContent = (JTextField) getComponentByName("tf_VerbatimContent" + index);
        String content = verbatimContent.getText().trim();
        if (content.equals(ControlGUI.EBD_DisplaySettings[currentTab.ordinal()].verbatimContent)) {
            changeEnabled_of_SaveCancelButtons(false);
        } else {
            changeEnabled_of_SaveCancelButtons(true);
        }
    }

    private void tryToCloseEBDSettingsForm() {
        if (formMode == FormMode.MODIFICATION) {
            JOptionPane.showMessageDialog(this, 
                    "E-Board settings is being modified," + System.lineSeparator()
                            + "Either [Save] or [Cancel] current changes!"); 
=======
        JComboBox typeCBox = (JComboBox) getComponentByName(componentMap, "combo_ContentType" + usage.ordinal());
        EBD_ContentType selectedType 
                = (EBD_ContentType) ((ConvComboBoxItem)typeCBox.getSelectedItem()).getValue();
        final String controlName = typeCBox.getName();
        
        if(((JComboBox) getComponentByName(componentMap, "combo_ContentType" + usage.ordinal())).getSelectedIndex() 
                != EBD_ContentType.VERBATIM.ordinal())
        {
            changedControls.remove(((JTextField) getComponentByName(componentMap, "tf_VerbatimContent" + usage.ordinal())).getName());
        }
        
        ChangeSettings.changeStatus_Manager(
                (JButton) getComponentByName(componentMap, "btn_Save" + usage.ordinal()),
                (JButton) getComponentByName(componentMap, "btn_Cancel" + usage.ordinal()), btn_Exit, 
                changedControls, typeCBox, selectedType.ordinal(), 
                ControlGUI.EBD_DisplaySettings[usage.ordinal()].contentType.ordinal());
    }

    private void setButtonEnabledIfColorChanged(EBD_DisplayUsage usage) {
        JComboBox colorCBox = (JComboBox) getComponentByName(componentMap, "combo_TextColor" + usage.ordinal());
        EBD_Colors selectedColor = (EBD_Colors) ((ConvComboBoxItem)colorCBox.getSelectedItem()).getValue();
        final String controlName = colorCBox.getName();
        ChangeSettings.changeStatus_Manager(
                (JButton) getComponentByName(componentMap, "btn_Save" + usage.ordinal()),
                (JButton) getComponentByName(componentMap, "btn_Cancel" + usage.ordinal()), btn_Exit, 
                changedControls, colorCBox, selectedColor.ordinal(), 
                ControlGUI.EBD_DisplaySettings[usage.ordinal()].textColor.ordinal());
    }

    private void setButtonEnabledIfEffectChanged(EBD_DisplayUsage usage) {
        JComboBox effectCBox = (JComboBox) getComponentByName(componentMap, "combo_DisplayEffect" + usage.ordinal());
        EBD_Effects selectedEffect = (EBD_Effects) ((ConvComboBoxItem)effectCBox.getSelectedItem()).getValue();
        final String controlName = effectCBox.getName();
        ChangeSettings.changeStatus_Manager(
                (JButton) getComponentByName(componentMap, "btn_Save" + usage.ordinal()),
                (JButton) getComponentByName(componentMap, "btn_Cancel" + usage.ordinal()), btn_Exit, 
                changedControls, effectCBox, selectedEffect.ordinal(), 
                ControlGUI.EBD_DisplaySettings[usage.ordinal()].displayPattern.ordinal());
    }

    private void setButtonEnabledIfFontChanged(EBD_DisplayUsage usage) {
        JComboBox fontCBox = (JComboBox) getComponentByName(componentMap, "combo_TextFont" + usage.ordinal());
        EBD_Fonts selectedFont = (EBD_Fonts) ((ConvComboBoxItem)fontCBox.getSelectedItem()).getValue();
        final String controlName = fontCBox.getName();
        ChangeSettings.changeStatus_Manager(
                (JButton) getComponentByName(componentMap, "btn_Save" + usage.ordinal()),
                (JButton) getComponentByName(componentMap, "btn_Cancel" + usage.ordinal()), btn_Exit, 
                changedControls, fontCBox, selectedFont.ordinal(), 
                ControlGUI.EBD_DisplaySettings[usage.ordinal()].textFont.ordinal());
    }

    private void changeButtonEnabled_IfVebarimChanged(int index) {
        JTextField verbatimContent = (JTextField) getComponentByName(componentMap, "tf_VerbatimContent" + index);
        String content = verbatimContent.getText().trim();
        final String controlName = verbatimContent.getName();
        ChangeSettings.changeStatus_Manager(
                (JButton) getComponentByName(componentMap, "btn_Save" + index),
                (JButton) getComponentByName(componentMap, "btn_Cancel" + index), btn_Exit, 
                changedControls, verbatimContent, content, 
                ControlGUI.EBD_DisplaySettings[index].verbatimContent);
    }

    private void tryToCloseEBDSettingsForm() {
        if (!btn_Exit.isEnabled()) {
            JOptionPane.showMessageDialog(this, 
                    ((String[])Globals.DialogMSGList.get(SAVE_OR_CANEL_DIALOG.ordinal()))[ourLang]); 
>>>>>>> osparking/master
        } else {
            parent.setEBDsettings(null);    
            this.dispose();    
        }
    }
}
