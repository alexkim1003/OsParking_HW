/*
 * Copyright (C) 2015 Open Source Parking Inc.
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
package com.osparking.osparking.device.LEDnotice;

import com.osparking.global.Globals;
import static com.osparking.global.Globals.OSPiconList;
import static com.osparking.global.Globals.checkOptions;
import static com.osparking.global.Globals.font_Size;
import static com.osparking.global.Globals.font_Style;
import static com.osparking.global.Globals.font_Type;
import static com.osparking.global.Globals.getQuest20_Icon;
import static com.osparking.global.Globals.initializeLoggers;
import static com.osparking.global.names.DB_Access.gateCount;
import static com.osparking.global.names.DB_Access.readSettings;
import com.osparking.global.names.OSP_enums;
import static com.osparking.global.names.OSP_enums.DeviceType.E_Board;
import com.osparking.global.names.OSP_enums.EBD_DisplayUsage;
import static com.osparking.global.names.OSP_enums.EBD_DisplayUsage.DEFAULT_TOP_ROW;
import com.osparking.global.names.ParkingTimer;
import com.osparking.osparking.ControlGUI;
import com.osparking.osparking.Settings_System;
import com.osparking.osparking.device.ConnectDeviceTask;
import static com.osparking.osparking.device.LEDnotice.LEDnoticeManager.ledNoticeSettings;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.EffectType;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.EffectType.NONE;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.getViewWidth;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Open Source Parking Inc.
 */
public class Settings_LEDnotice extends javax.swing.JFrame {
    public static ControlGUI mainForm = null;
    Settings_System parent = null;
    LEDnoticeManager manager;
    OSP_enums.FormMode formMode = OSP_enums.FormMode.SEARCHING;

    private HashMap<String,Component> componentMap;
    
    /**
     * Creates new form NewJFrame
     */
    public Settings_LEDnotice(
            ControlGUI mainForm, Settings_System parent, LEDnoticeManager manager, int gateNo)
    {
        initComponents();
        this.mainForm = mainForm;
        this.parent = parent; 
        if (mainForm == null) {
            this.manager = manager;
        } else {
            this.manager = (LEDnoticeManager)mainForm.getDeviceManagers()[E_Board.ordinal()][gateNo];
        }
        setIconImages(OSPiconList);
        makeComponentMap();   
        
        // delete after development completion
        setLocation(1200, 350);
        initEffectComboBoxes();
        initTypeComboBox();
        initColorComboBox();
        initFontComboBox();    
        initE_BoardTypeCBox();        
    }
    

    private void initE_BoardTypeCBox() {
        for (int gateNo = 1; gateNo < gateCount; gateNo++) {
            JComboBox ebdTypeBox = (JComboBox)componentMap.get("EBD" + gateNo + "_TypeComboBox");

            if (ebdTypeBox != null) {
                ebdTypeBox.removeAllItems();
                for (OSP_enums.E_BoardType type : OSP_enums.E_BoardType.values()) {
                    ebdTypeBox.addItem(type.getLabel());
                }
            }
        }
    }

    private void demoCurrentSetting(int tabIndex) {
        
        int gateNo = findGateNoUsingLEDnotice();
        
        if (gateNo == -1) {
            JOptionPane.showMessageDialog(this, "설정된 LEDnotice 장치가 없습니다.");
            return;
        } else {
            if (mainForm == null) {
                JOptionPane.showMessageDialog(this, "메인 창이 열려있지 않습니다.");
            } else {
                LEDnoticeManager manager = (LEDnoticeManager)mainForm
                        .getDeviceManagers()[E_Board.ordinal()][gateNo];           
                
                JComboBox typeBox = (JComboBox)componentMap.get("contentTypeBox" + tabIndex);
                JTextField strField = (JTextField)componentMap.get("tf_VerbatimContent" + tabIndex);
                JComboBox startEffectBox = (JComboBox)componentMap.get("combo_StartEffect" + tabIndex);
                JComboBox pauseTimeBox = (JComboBox)componentMap.get("combo_Stoptime" + tabIndex);
                JComboBox finishEffectBox = (JComboBox)componentMap.get("combo_EndEffect" + tabIndex);
                JComboBox colorBox = (JComboBox)componentMap.get("charColor" + tabIndex);
                JComboBox fontBox = (JComboBox)componentMap.get("charFont" + tabIndex);
                
                manager.showCurrentEffect(tabIndex, 
                        typeBox.getSelectedIndex(), strField.getText(),
                        startEffectBox.getSelectedIndex(), pauseTimeBox.getSelectedIndex(),
                        finishEffectBox.getSelectedIndex(), colorBox.getSelectedIndex(),
                        fontBox.getSelectedIndex());
            }
        }           
    }    

    private void finishAllEffectDemo(int index) {
        int gateNo = findGateNoUsingLEDnotice();
        
        LEDnoticeManager manager = (LEDnoticeManager)mainForm
                .getDeviceManagers()[E_Board.ordinal()][gateNo];
                         

        manager.finishShowingDemoEffect(index);
    }

    private void demoAllEffects(int tabIndex, int stopIndex, int colorIdx, int fontIdx) {
        int gateNo = findGateNoUsingLEDnotice();
        
        if (gateNo == -1) {
            JOptionPane.showMessageDialog(this, "설정된 LEDnotice 장치가 없습니다.");
            return;
        } else {
            if (mainForm == null) {
                JOptionPane.showMessageDialog(this, "메인 창이 열려있지 않습니다.");
            } else {
                LEDnoticeManager manager = (LEDnoticeManager)mainForm
                        .getDeviceManagers()[E_Board.ordinal()][gateNo];

                manager.showAllEffects(tabIndex, stopIndex, colorIdx, fontIdx);
            }
        }    
    }
    
    
    private void initColorComboBox() {
        for (EBD_DisplayUsage usage : EBD_DisplayUsage.values()) {
            JComboBox colorBox = (JComboBox)componentMap.get("charColor" + usage.ordinal());
            
            if (colorBox == null)
                continue;
            colorBox.removeAllItems();
            for (LEDnotice_enums.ColorBox aColor : LEDnotice_enums.ColorBox.values()) {
                colorBox.addItem(aColor.getLabel());
            }  
        }
    }
    
    private void initFontComboBox() {
        for (EBD_DisplayUsage usage : EBD_DisplayUsage.values()) {
            JComboBox fontBox = (JComboBox)componentMap.get("charFont" + usage.ordinal());
            
            if (fontBox == null)
                continue;
            fontBox.removeAllItems();
            for (LEDnotice_enums.FontBox aFont : LEDnotice_enums.FontBox.values()) {
                fontBox.addItem(aFont.getLabel());
            }  
        }
    }    
    

    private void initTypeComboBox() {
        for (EBD_DisplayUsage usage : EBD_DisplayUsage.values()) {
            JComboBox typeBox = (JComboBox)componentMap.get("contentTypeBox" + usage.ordinal());

            if (typeBox == null)
                continue;
            typeBox.removeAllItems();
            for (LEDnotice_enums.LEDnoticeDefaultContentType aFont : LEDnotice_enums.LEDnoticeDefaultContentType.values()) {
                typeBox.addItem(aFont.getLabel());
            }  
        }
    }    
    
    private void initEffectComboBoxes() {
        for (EBD_DisplayUsage usage : EBD_DisplayUsage.values()) {
            JComboBox typeBox = (JComboBox)componentMap.get("combo_StartEffect" + usage.ordinal());

            if (typeBox == null)
                continue;
            typeBox.removeAllItems();
            for (EffectType effect : EffectType.values()) {
                if (effect == NONE) 
                    continue;
                typeBox.addItem(effect.getLabel());
            }  
            
            typeBox = (JComboBox)componentMap.get("combo_EndEffect" + usage.ordinal());

            if (typeBox == null)
                continue;
            typeBox.removeAllItems();
            for (EffectType effect : EffectType.values()) {
                typeBox.addItem(effect.getLabel());
            }              
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        ledNoticePanel = new javax.swing.JPanel();
        wholePanel1 = new javax.swing.JPanel();
        ledNoticeTabbedPane = new javax.swing.JTabbedPane();
        ledNoticePanelDefault = new javax.swing.JTabbedPane();
        ledNoticePanel0 = new javax.swing.JPanel();
        label_MSG0 = new javax.swing.JLabel();
        label_Color0 = new javax.swing.JLabel();
        label_Font0 = new javax.swing.JLabel();
        label_ContentType0 = new javax.swing.JLabel();
        contentTypeBox0 = new javax.swing.JComboBox();
        tf_VerbatimContent0 = new javax.swing.JTextField();
        charColor0 = new javax.swing.JComboBox();
        charFont0 = new javax.swing.JComboBox();
        combo_StartEffect0 = new javax.swing.JComboBox();
        combo_EndEffect0 = new javax.swing.JComboBox();
        label_Color4 = new javax.swing.JLabel();
        label_Color5 = new javax.swing.JLabel();
        label_Color6 = new javax.swing.JLabel();
        label_Color7 = new javax.swing.JLabel();
        combo_Stoptime0 = new javax.swing.JComboBox();
        useLEDnoticeCBox0 = new javax.swing.JCheckBox();
        demoButton0 = new javax.swing.JButton();
        demoFinishButton0 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        startEffectHelpButton0 = new javax.swing.JButton();
        demoCurrHelpButton0 = new javax.swing.JButton();
        demoAllHelpButton0 = new javax.swing.JButton();
        endEffectHelpButton0 = new javax.swing.JButton();
        demoAllButton0 = new javax.swing.JButton();
        btn_Save0 = new javax.swing.JButton();
        btn_Cancel0 = new javax.swing.JButton();
        ledNoticePanel1 = new javax.swing.JPanel();
        label_MSG1 = new javax.swing.JLabel();
        label_Color1 = new javax.swing.JLabel();
        label_Font1 = new javax.swing.JLabel();
        label_ContentType1 = new javax.swing.JLabel();
        contentTypeBox1 = new javax.swing.JComboBox();
        tf_VerbatimContent1 = new javax.swing.JTextField();
        charColor1 = new javax.swing.JComboBox();
        charFont1 = new javax.swing.JComboBox();
        combo_StartEffect1 = new javax.swing.JComboBox();
        combo_EndEffect1 = new javax.swing.JComboBox();
        label_Color8 = new javax.swing.JLabel();
        label_Color9 = new javax.swing.JLabel();
        label_Color10 = new javax.swing.JLabel();
        label_Color11 = new javax.swing.JLabel();
        combo_Stoptime1 = new javax.swing.JComboBox();
        useLEDnoticeCBox1 = new javax.swing.JCheckBox();
        demoButton1 = new javax.swing.JButton();
        demoFinishButton1 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        startEffectHelpButton1 = new javax.swing.JButton();
        demoCurrHelpButton1 = new javax.swing.JButton();
        demoAllHelpButton1 = new javax.swing.JButton();
        endEffectHelpButton1 = new javax.swing.JButton();
        demoAllButton1 = new javax.swing.JButton();
        btn_Save1 = new javax.swing.JButton();
        btn_Cancel1 = new javax.swing.JButton();
        ledNoticePanelVehicle = new javax.swing.JTabbedPane();
        ledNoticePanel2 = new javax.swing.JPanel();
        label_MSG2 = new javax.swing.JLabel();
        label_Effect2 = new javax.swing.JLabel();
        label_Color2 = new javax.swing.JLabel();
        label_Font2 = new javax.swing.JLabel();
        label_ContentType2 = new javax.swing.JLabel();
        ledNoticePanel3 = new javax.swing.JPanel();
        label_MSG3 = new javax.swing.JLabel();
        label_Effect3 = new javax.swing.JLabel();
        label_Color3 = new javax.swing.JLabel();
        label_Font3 = new javax.swing.JLabel();
        label_ContentType3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_Exit = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("LEDnotice Settings");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        wholePanel1.setLayout(new java.awt.BorderLayout());

        ledNoticeTabbedPane.setToolTipText("");
        ledNoticeTabbedPane.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        ledNoticeTabbedPane.setMinimumSize(new java.awt.Dimension(521, 268));
        ledNoticeTabbedPane.setName("ledNoticeTabbedPane"); // NOI18N
        ledNoticeTabbedPane.setPreferredSize(new java.awt.Dimension(506, 270));

        ledNoticePanelDefault.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        ledNoticePanelDefault.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        ledNoticePanelDefault.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        ledNoticePanelDefault.setMinimumSize(new java.awt.Dimension(300, 198));
        ledNoticePanelDefault.setName("Default_Panel"); // NOI18N

        ledNoticePanel0.setName("eBoard" + EBD_DisplayUsage.DEFAULT_TOP_ROW.getVal());
        ledNoticePanel0.setLayout(new java.awt.GridBagLayout());

        label_MSG0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_MSG0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_MSG0.setText("문자열");
        label_MSG0.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ledNoticePanel0.add(label_MSG0, gridBagConstraints);

        label_Color0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color0.setText("중간멈춤");
        label_Color0.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ledNoticePanel0.add(label_Color0, gridBagConstraints);

        label_Font0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Font0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Font0.setText("폰트");
        label_Font0.setMaximumSize(new java.awt.Dimension(100, 15));
        label_Font0.setPreferredSize(new java.awt.Dimension(38, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel0.add(label_Font0, gridBagConstraints);

        label_ContentType0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_ContentType0.setText("표시유형");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ledNoticePanel0.add(label_ContentType0, gridBagConstraints);

        contentTypeBox0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        contentTypeBox0.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "문구 자체", "주차장 이름" }));
        contentTypeBox0.setMinimumSize(new java.awt.Dimension(123, 23));
        contentTypeBox0.setName("contentTypeBox0"); // NOI18N
        contentTypeBox0.setPreferredSize(new java.awt.Dimension(123, 25));
        contentTypeBox0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentTypeBox0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        ledNoticePanel0.add(contentTypeBox0, gridBagConstraints);

        tf_VerbatimContent0.setColumns(23);
        tf_VerbatimContent0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        tf_VerbatimContent0.setMinimumSize(new java.awt.Dimension(250, 23));
        tf_VerbatimContent0.setName("tf_VerbatimContent0"); // NOI18N
        tf_VerbatimContent0.setPreferredSize(new java.awt.Dimension(250, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        ledNoticePanel0.add(tf_VerbatimContent0, gridBagConstraints);

        charColor0.setMinimumSize(new java.awt.Dimension(63, 23));
        charColor0.setName("charColor0"); // NOI18N
        charColor0.setPreferredSize(new java.awt.Dimension(70, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        ledNoticePanel0.add(charColor0, gridBagConstraints);

        charFont0.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "굴림체", "궁서체" }));
        charFont0.setName("charFont0"); // NOI18N
        charFont0.setPreferredSize(new java.awt.Dimension(70, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        ledNoticePanel0.add(charFont0, gridBagConstraints);

        combo_StartEffect0.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "왼쪽흐름", "오른쪽흐름", "위로흐름", "아래로흐름", "정지", "깜빡임", "반전", "플레싱", "블라인드", "레이저", "중앙이동", "펼침", "좌흐름적색깜빡임", "우흐름적색깜빡임", "좌흐름녹색깜빡임", "우흐름녹색깜빡임", "회전", "좌우열기", "좌우닫기", "상하열기", "상하닫기", "모듈별이동", "모듈별회전", "상하색분리", "좌우색분리", "테두리이동", "확대", "세로확대", "가로확대", "줄깜빡임", "가로쌓기", "흩뿌리기" }));
        combo_StartEffect0.setName("combo_StartEffect0"); // NOI18N
        combo_StartEffect0.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_StartEffect0PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        ledNoticePanel0.add(combo_StartEffect0, gridBagConstraints);

        combo_EndEffect0.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "효과없음", "왼쪽흐름", "오른쪽흐름", "위로흐름", "아래로흐름", "정지", "깜빡임", "반전", "플레싱", "블라인드", "레이저", "중앙이동", "펼침", "좌흐름적색깜빡임", "우흐름적색깜빡임", "좌흐름녹색깜빡임", "우흐름녹색깜빡임", "회전", "좌우열기", "좌우닫기", "상하열기", "상하닫기", "모듈별이동", "모듈별회전", "상하색분리", "좌우색분리", "테두리이동", "확대", "세로확대", "가로확대", "줄깜빡임", "가로쌓기", "흩뿌리기" }));
        combo_EndEffect0.setName("combo_EndEffect0"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        ledNoticePanel0.add(combo_EndEffect0, gridBagConstraints);

        label_Color4.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color4.setText("색상");
        label_Color4.setMaximumSize(new java.awt.Dimension(100, 15));
        label_Color4.setPreferredSize(new java.awt.Dimension(38, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel0.add(label_Color4, gridBagConstraints);

        label_Color5.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color5.setText("마침효과");
        label_Color5.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        ledNoticePanel0.add(label_Color5, gridBagConstraints);

        label_Color6.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color6.setText("시작효과");
        label_Color6.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ledNoticePanel0.add(label_Color6, gridBagConstraints);

        label_Color7.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color7.setText("초");
        label_Color7.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        ledNoticePanel0.add(label_Color7, gridBagConstraints);

        combo_Stoptime0.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        combo_Stoptime0.setMinimumSize(new java.awt.Dimension(70, 23));
        combo_Stoptime0.setName("combo_Stoptime0"); // NOI18N
        combo_Stoptime0.setPreferredSize(new java.awt.Dimension(70, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        ledNoticePanel0.add(combo_Stoptime0, gridBagConstraints);

        useLEDnoticeCBox0.setSelected(true);
        useLEDnoticeCBox0.setText("사용");
        useLEDnoticeCBox0.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        useLEDnoticeCBox0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useLEDnoticeCBox0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        ledNoticePanel0.add(useLEDnoticeCBox0, gridBagConstraints);

        demoButton0.setText("현재");
        demoButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoButton0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel0.add(demoButton0, gridBagConstraints);

        demoFinishButton0.setText("그만");
        demoFinishButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoFinishButton0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 20, 0);
        ledNoticePanel0.add(demoFinishButton0, gridBagConstraints);

        jLabel41.setText("시연보기");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel0.add(jLabel41, gridBagConstraints);

        startEffectHelpButton0.setBackground(new java.awt.Color(153, 255, 153));
        startEffectHelpButton0.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        startEffectHelpButton0.setIcon(getQuest20_Icon());
        startEffectHelpButton0.setMargin(new java.awt.Insets(2, 4, 2, 4));
        startEffectHelpButton0.setMinimumSize(new java.awt.Dimension(20, 20));
        startEffectHelpButton0.setOpaque(false);
        startEffectHelpButton0.setPreferredSize(new java.awt.Dimension(20, 20));
        startEffectHelpButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startEffectHelpButton0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        ledNoticePanel0.add(startEffectHelpButton0, gridBagConstraints);

        demoCurrHelpButton0.setBackground(new java.awt.Color(153, 255, 153));
        demoCurrHelpButton0.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        demoCurrHelpButton0.setIcon(getQuest20_Icon());
        demoCurrHelpButton0.setMargin(new java.awt.Insets(2, 4, 2, 4));
        demoCurrHelpButton0.setMinimumSize(new java.awt.Dimension(20, 20));
        demoCurrHelpButton0.setOpaque(false);
        demoCurrHelpButton0.setPreferredSize(new java.awt.Dimension(20, 20));
        demoCurrHelpButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoCurrHelpButton0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        ledNoticePanel0.add(demoCurrHelpButton0, gridBagConstraints);

        demoAllHelpButton0.setBackground(new java.awt.Color(153, 255, 153));
        demoAllHelpButton0.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        demoAllHelpButton0.setIcon(getQuest20_Icon());
        demoAllHelpButton0.setMargin(new java.awt.Insets(2, 4, 2, 4));
        demoAllHelpButton0.setMinimumSize(new java.awt.Dimension(20, 20));
        demoAllHelpButton0.setOpaque(false);
        demoAllHelpButton0.setPreferredSize(new java.awt.Dimension(20, 20));
        demoAllHelpButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoAllHelpButton0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        ledNoticePanel0.add(demoAllHelpButton0, gridBagConstraints);

        endEffectHelpButton0.setBackground(new java.awt.Color(153, 255, 153));
        endEffectHelpButton0.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        endEffectHelpButton0.setIcon(getQuest20_Icon());
        endEffectHelpButton0.setMargin(new java.awt.Insets(2, 4, 2, 4));
        endEffectHelpButton0.setMinimumSize(new java.awt.Dimension(20, 20));
        endEffectHelpButton0.setOpaque(false);
        endEffectHelpButton0.setPreferredSize(new java.awt.Dimension(20, 20));
        endEffectHelpButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endEffectHelpButton0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        ledNoticePanel0.add(endEffectHelpButton0, gridBagConstraints);

        demoAllButton0.setText("전체");
        demoAllButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoAllButton0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel0.add(demoAllButton0, gridBagConstraints);

        btn_Save0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Save0.setMnemonic('s');
        btn_Save0.setText("Save");
        btn_Save0.setEnabled(false);
        btn_Save0.setInheritsPopupMenu(true);
        btn_Save0.setMaximumSize(new java.awt.Dimension(73, 35));
        btn_Save0.setMinimumSize(new java.awt.Dimension(85, 35));
        btn_Save0.setName("btn_Save" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        btn_Save0.setPreferredSize(new java.awt.Dimension(85, 30));
        btn_Save0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Save0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        ledNoticePanel0.add(btn_Save0, gridBagConstraints);

        btn_Cancel0.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Cancel0.setMnemonic('c');
        btn_Cancel0.setText("Cancel");
        btn_Cancel0.setEnabled(false);
        btn_Cancel0.setMaximumSize(new java.awt.Dimension(85, 35));
        btn_Cancel0.setMinimumSize(new java.awt.Dimension(85, 35));
        btn_Cancel0.setName("btn_Cancel" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        btn_Cancel0.setPreferredSize(new java.awt.Dimension(85, 30));
        btn_Cancel0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancel0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        ledNoticePanel0.add(btn_Cancel0, gridBagConstraints);

        ledNoticePanelDefault.addTab("상단", ledNoticePanel0);

        ledNoticePanel1.setName("eBoard" + EBD_DisplayUsage.DEFAULT_TOP_ROW.getVal());
        ledNoticePanel1.setLayout(new java.awt.GridBagLayout());

        label_MSG1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_MSG1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_MSG1.setText("문자열");
        label_MSG1.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ledNoticePanel1.add(label_MSG1, gridBagConstraints);

        label_Color1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color1.setText("중간멈춤");
        label_Color1.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ledNoticePanel1.add(label_Color1, gridBagConstraints);

        label_Font1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Font1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Font1.setText("폰트");
        label_Font1.setMaximumSize(new java.awt.Dimension(100, 15));
        label_Font1.setPreferredSize(new java.awt.Dimension(38, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel1.add(label_Font1, gridBagConstraints);

        label_ContentType1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_ContentType1.setText("표시유형");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ledNoticePanel1.add(label_ContentType1, gridBagConstraints);

        contentTypeBox1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        contentTypeBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "문구 자체", "주차장 이름" }));
        contentTypeBox1.setMinimumSize(new java.awt.Dimension(123, 23));
        contentTypeBox1.setName("contentTypeBox0"); // NOI18N
        contentTypeBox1.setPreferredSize(new java.awt.Dimension(123, 25));
        contentTypeBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentTypeBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        ledNoticePanel1.add(contentTypeBox1, gridBagConstraints);

        tf_VerbatimContent1.setColumns(23);
        tf_VerbatimContent1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        tf_VerbatimContent1.setMinimumSize(new java.awt.Dimension(250, 23));
        tf_VerbatimContent1.setName("tf_VerbatimContent0"); // NOI18N
        tf_VerbatimContent1.setPreferredSize(new java.awt.Dimension(250, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        ledNoticePanel1.add(tf_VerbatimContent1, gridBagConstraints);

        charColor1.setMinimumSize(new java.awt.Dimension(63, 23));
        charColor1.setName("charColor0"); // NOI18N
        charColor1.setPreferredSize(new java.awt.Dimension(70, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        ledNoticePanel1.add(charColor1, gridBagConstraints);

        charFont1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "굴림체", "궁서체" }));
        charFont1.setName("charFont0"); // NOI18N
        charFont1.setPreferredSize(new java.awt.Dimension(70, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        ledNoticePanel1.add(charFont1, gridBagConstraints);

        combo_StartEffect1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "왼쪽흐름", "오른쪽흐름", "위로흐름", "아래로흐름", "정지", "깜빡임", "반전", "플레싱", "블라인드", "레이저", "중앙이동", "펼침", "좌흐름적색깜빡임", "우흐름적색깜빡임", "좌흐름녹색깜빡임", "우흐름녹색깜빡임", "회전", "좌우열기", "좌우닫기", "상하열기", "상하닫기", "모듈별이동", "모듈별회전", "상하색분리", "좌우색분리", "테두리이동", "확대", "세로확대", "가로확대", "줄깜빡임", "가로쌓기", "흩뿌리기" }));
        combo_StartEffect1.setName("combo_StartEffect0"); // NOI18N
        combo_StartEffect1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_StartEffect1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        ledNoticePanel1.add(combo_StartEffect1, gridBagConstraints);

        combo_EndEffect1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "효과없음", "왼쪽흐름", "오른쪽흐름", "위로흐름", "아래로흐름", "정지", "깜빡임", "반전", "플레싱", "블라인드", "레이저", "중앙이동", "펼침", "좌흐름적색깜빡임", "우흐름적색깜빡임", "좌흐름녹색깜빡임", "우흐름녹색깜빡임", "회전", "좌우열기", "좌우닫기", "상하열기", "상하닫기", "모듈별이동", "모듈별회전", "상하색분리", "좌우색분리", "테두리이동", "확대", "세로확대", "가로확대", "줄깜빡임", "가로쌓기", "흩뿌리기" }));
        combo_EndEffect1.setName("combo_EndEffect0"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        ledNoticePanel1.add(combo_EndEffect1, gridBagConstraints);

        label_Color8.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color8.setText("색상");
        label_Color8.setMaximumSize(new java.awt.Dimension(100, 15));
        label_Color8.setPreferredSize(new java.awt.Dimension(38, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel1.add(label_Color8, gridBagConstraints);

        label_Color9.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color9.setText("마침효과");
        label_Color9.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        ledNoticePanel1.add(label_Color9, gridBagConstraints);

        label_Color10.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color10.setText("시작효과");
        label_Color10.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ledNoticePanel1.add(label_Color10, gridBagConstraints);

        label_Color11.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color11.setText("초");
        label_Color11.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        ledNoticePanel1.add(label_Color11, gridBagConstraints);

        combo_Stoptime1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        combo_Stoptime1.setMinimumSize(new java.awt.Dimension(70, 23));
        combo_Stoptime1.setName("combo_Stoptime0"); // NOI18N
        combo_Stoptime1.setPreferredSize(new java.awt.Dimension(70, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        ledNoticePanel1.add(combo_Stoptime1, gridBagConstraints);

        useLEDnoticeCBox1.setSelected(true);
        useLEDnoticeCBox1.setText("사용");
        useLEDnoticeCBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        useLEDnoticeCBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useLEDnoticeCBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        ledNoticePanel1.add(useLEDnoticeCBox1, gridBagConstraints);

        demoButton1.setText("현재");
        demoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel1.add(demoButton1, gridBagConstraints);

        demoFinishButton1.setText("그만");
        demoFinishButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoFinishButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 0);
        ledNoticePanel1.add(demoFinishButton1, gridBagConstraints);

        jLabel42.setText("시연보기");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel1.add(jLabel42, gridBagConstraints);

        startEffectHelpButton1.setBackground(new java.awt.Color(153, 255, 153));
        startEffectHelpButton1.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        startEffectHelpButton1.setIcon(getQuest20_Icon());
        startEffectHelpButton1.setMargin(new java.awt.Insets(2, 4, 2, 4));
        startEffectHelpButton1.setMinimumSize(new java.awt.Dimension(20, 20));
        startEffectHelpButton1.setOpaque(false);
        startEffectHelpButton1.setPreferredSize(new java.awt.Dimension(20, 20));
        startEffectHelpButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startEffectHelpButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        ledNoticePanel1.add(startEffectHelpButton1, gridBagConstraints);

        demoCurrHelpButton1.setBackground(new java.awt.Color(153, 255, 153));
        demoCurrHelpButton1.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        demoCurrHelpButton1.setIcon(getQuest20_Icon());
        demoCurrHelpButton1.setMargin(new java.awt.Insets(2, 4, 2, 4));
        demoCurrHelpButton1.setMinimumSize(new java.awt.Dimension(20, 20));
        demoCurrHelpButton1.setOpaque(false);
        demoCurrHelpButton1.setPreferredSize(new java.awt.Dimension(20, 20));
        demoCurrHelpButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoCurrHelpButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        ledNoticePanel1.add(demoCurrHelpButton1, gridBagConstraints);

        demoAllHelpButton1.setBackground(new java.awt.Color(153, 255, 153));
        demoAllHelpButton1.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        demoAllHelpButton1.setIcon(getQuest20_Icon());
        demoAllHelpButton1.setMargin(new java.awt.Insets(2, 4, 2, 4));
        demoAllHelpButton1.setMinimumSize(new java.awt.Dimension(20, 20));
        demoAllHelpButton1.setOpaque(false);
        demoAllHelpButton1.setPreferredSize(new java.awt.Dimension(20, 20));
        demoAllHelpButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoAllHelpButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        ledNoticePanel1.add(demoAllHelpButton1, gridBagConstraints);

        endEffectHelpButton1.setBackground(new java.awt.Color(153, 255, 153));
        endEffectHelpButton1.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        endEffectHelpButton1.setIcon(getQuest20_Icon());
        endEffectHelpButton1.setMargin(new java.awt.Insets(2, 4, 2, 4));
        endEffectHelpButton1.setMinimumSize(new java.awt.Dimension(20, 20));
        endEffectHelpButton1.setOpaque(false);
        endEffectHelpButton1.setPreferredSize(new java.awt.Dimension(20, 20));
        endEffectHelpButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endEffectHelpButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        ledNoticePanel1.add(endEffectHelpButton1, gridBagConstraints);

        demoAllButton1.setText("전체");
        demoAllButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoAllButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        ledNoticePanel1.add(demoAllButton1, gridBagConstraints);

        btn_Save1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Save1.setMnemonic('s');
        btn_Save1.setText("Save");
        btn_Save1.setEnabled(false);
        btn_Save1.setInheritsPopupMenu(true);
        btn_Save1.setMaximumSize(new java.awt.Dimension(85, 35));
        btn_Save1.setMinimumSize(new java.awt.Dimension(85, 35));
        btn_Save1.setName("btn_Save" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        btn_Save1.setPreferredSize(new java.awt.Dimension(85, 35));
        btn_Save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Save1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        ledNoticePanel1.add(btn_Save1, gridBagConstraints);

        btn_Cancel1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Cancel1.setMnemonic('c');
        btn_Cancel1.setText("Cancel");
        btn_Cancel1.setEnabled(false);
        btn_Cancel1.setMaximumSize(new java.awt.Dimension(85, 35));
        btn_Cancel1.setMinimumSize(new java.awt.Dimension(85, 35));
        btn_Cancel1.setName("btn_Cancel" + EBD_DisplayUsage.DEFAULT_TOP_ROW.ordinal());
        btn_Cancel1.setPreferredSize(new java.awt.Dimension(85, 35));
        btn_Cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancel1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        ledNoticePanel1.add(btn_Cancel1, gridBagConstraints);

        ledNoticePanelDefault.addTab("하단", ledNoticePanel1);

        ledNoticeTabbedPane.addTab("기본", ledNoticePanelDefault);

        ledNoticePanelVehicle.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        ledNoticePanelVehicle.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        ledNoticePanelVehicle.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        ledNoticePanelVehicle.setName("Vehicle_Panel"); // NOI18N

        ledNoticePanel2.setName("eBoard" + EBD_DisplayUsage.CAR_ENTRY_TOP_ROW.getVal());
        ledNoticePanel2.setLayout(new java.awt.GridBagLayout());

        label_MSG2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_MSG2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_MSG2.setText("Message");
        label_MSG2.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        ledNoticePanel2.add(label_MSG2, gridBagConstraints);

        label_Effect2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Effect2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Effect2.setText("Effect");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        ledNoticePanel2.add(label_Effect2, gridBagConstraints);

        label_Color2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color2.setText("Color");
        label_Color2.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        ledNoticePanel2.add(label_Color2, gridBagConstraints);

        label_Font2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Font2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Font2.setText("Font");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        ledNoticePanel2.add(label_Font2, gridBagConstraints);

        label_ContentType2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_ContentType2.setText("Content Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        ledNoticePanel2.add(label_ContentType2, gridBagConstraints);

        ledNoticePanelVehicle.addTab("상단", ledNoticePanel2);

        ledNoticePanel3.setName("eBoard" + EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW.getVal());
        ledNoticePanel3.setLayout(new java.awt.GridBagLayout());

        label_MSG3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_MSG3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_MSG3.setText("Message");
        label_MSG3.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        ledNoticePanel3.add(label_MSG3, gridBagConstraints);

        label_Effect3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Effect3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Effect3.setText("Effect");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        ledNoticePanel3.add(label_Effect3, gridBagConstraints);

        label_Color3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Color3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Color3.setText("Color");
        label_Color3.setPreferredSize(new java.awt.Dimension(76, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        ledNoticePanel3.add(label_Color3, gridBagConstraints);

        label_Font3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_Font3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_Font3.setText("Font");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        ledNoticePanel3.add(label_Font3, gridBagConstraints);

        label_ContentType3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        label_ContentType3.setText("Content Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        ledNoticePanel3.add(label_ContentType3, gridBagConstraints);

        ledNoticePanelVehicle.addTab("하단", ledNoticePanel3);

        ledNoticeTabbedPane.addTab("차량", ledNoticePanelVehicle);

        wholePanel1.add(ledNoticeTabbedPane, java.awt.BorderLayout.PAGE_START);

        ledNoticePanel.add(wholePanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btn_Exit.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        btn_Exit.setMnemonic('c');
        btn_Exit.setText("Close");
        btn_Exit.setMaximumSize(new java.awt.Dimension(85, 35));
        btn_Exit.setMinimumSize(new java.awt.Dimension(85, 35));
        btn_Exit.setPreferredSize(new java.awt.Dimension(85, 35));
        btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(ledNoticePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(285, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(ledNoticePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 50, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_StartEffect0PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_StartEffect0PopupMenuWillBecomeInvisible
        // 길이가 길면 좌로 흐름으로 설정하고 알림창을 띄운다
        // 그리고 항목 선택도 자동 변경한다.
        // 만일 값이 변경된 경우 Save 버튼을 활성화 한다.
        String content = tf_VerbatimContent0.getText().trim();
        if (getViewWidth(content) > LedProtocol.LED_COLUMN_CNT * 2) {
            String selectedLabel = (String)combo_StartEffect0.getSelectedItem();
            if ( ! selectedLabel.equals(EffectType.FLOW_RtoL.getLabel()) &&
                ! selectedLabel.equals(EffectType.FLOW_LtoR.getLabel())) {
                JOptionPane.showMessageDialog(this, "한글6(영숫자 12) 자 이상 긴 문자열은" +
                    System.lineSeparator() + "시작 효과가 '좌/우로 흐름'이 아닌 경우," +
                    System.lineSeparator() + "'좌로 흐름'으로 자동 설정됩니다.");
                combo_StartEffect0.setSelectedIndex(EffectType.FLOW_RtoL.ordinal() - 1);
                //                combo_StartEffect0.setSelectedItem(EffectType.FLOW_RtoL.getLabel());
            }
        }
    }//GEN-LAST:event_combo_StartEffect0PopupMenuWillBecomeInvisible

    private void useLEDnoticeCBox0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useLEDnoticeCBox0ActionPerformed
        checkLEDnoticeRowUsageChangeAndChangeButtonEnabled(DEFAULT_TOP_ROW);
    }//GEN-LAST:event_useLEDnoticeCBox0ActionPerformed

    private void demoButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoButton0ActionPerformed
        demoCurrentSetting(0);
    }//GEN-LAST:event_demoButton0ActionPerformed

    private void demoFinishButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoFinishButton0ActionPerformed
        finishAllEffectDemo(0);
    }//GEN-LAST:event_demoFinishButton0ActionPerformed

    private void startEffectHelpButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startEffectHelpButton0ActionPerformed
        JOptionPane.showMessageDialog(this,
            "한글 6 (영숫자 12) 자 이상이면" + System.lineSeparator() + "자동으로 '왼쪽흐름' 설정됨");
    }//GEN-LAST:event_startEffectHelpButton0ActionPerformed

    private void demoCurrHelpButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoCurrHelpButton0ActionPerformed
        JOptionPane.showMessageDialog(this, "현재 설정 상태를 시연함" + System.lineSeparator()
            + "[그만] 버튼 사용으로 시연 종료!");
    }//GEN-LAST:event_demoCurrHelpButton0ActionPerformed

    private void demoAllHelpButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoAllHelpButton0ActionPerformed
        int count = EffectType.values().length;
        JOptionPane.showMessageDialog(this, "총 " + count + " 개의 효과를" + System.lineSeparator()
            + "효과명을 사용하여 전체 시연함" + System.lineSeparator()
            + "[그만] 버튼 사용으로 시연 종료!");
    }//GEN-LAST:event_demoAllHelpButton0ActionPerformed

    private void endEffectHelpButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endEffectHelpButton0ActionPerformed
        JOptionPane.showMessageDialog(this,
            "하단 사용[V] 될 경우," + System.lineSeparator() + "자동으로 '효과 없음' 설정됨");
    }//GEN-LAST:event_endEffectHelpButton0ActionPerformed

    private void demoAllButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoAllButton0ActionPerformed
        int tabIndex = 0;
        int stopIndex = ((JComboBox)componentMap.get("combo_Stoptime" + tabIndex)).getSelectedIndex();
        int colorIndex = ((JComboBox)componentMap.get("charColor" + tabIndex)).getSelectedIndex();
        int fontIndex = ((JComboBox)componentMap.get("charFont" + tabIndex)).getSelectedIndex();
        demoAllEffects(0, stopIndex, colorIndex, fontIndex);
    }//GEN-LAST:event_demoAllButton0ActionPerformed

    private void btn_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExitActionPerformed
        tryToCloseEBDSettingsForm();
    }//GEN-LAST:event_btn_ExitActionPerformed

    private void btn_Save0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save0ActionPerformed
//        showDialog(currentTab);
    }//GEN-LAST:event_btn_Save0ActionPerformed

    private void btn_Cancel0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel0ActionPerformed
//        cancelBtnClick();
    }//GEN-LAST:event_btn_Cancel0ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        tryToCloseEBDSettingsForm();
    }//GEN-LAST:event_formWindowClosing

    private void contentTypeBox0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentTypeBox0ActionPerformed
        checkContentType(0);
    }//GEN-LAST:event_contentTypeBox0ActionPerformed

    private void contentTypeBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentTypeBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contentTypeBox1ActionPerformed

    private void combo_StartEffect1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_StartEffect1PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_StartEffect1PopupMenuWillBecomeInvisible

    private void useLEDnoticeCBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useLEDnoticeCBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_useLEDnoticeCBox1ActionPerformed

    private void demoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_demoButton1ActionPerformed

    private void demoFinishButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoFinishButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_demoFinishButton1ActionPerformed

    private void startEffectHelpButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startEffectHelpButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startEffectHelpButton1ActionPerformed

    private void demoCurrHelpButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoCurrHelpButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_demoCurrHelpButton1ActionPerformed

    private void demoAllHelpButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoAllHelpButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_demoAllHelpButton1ActionPerformed

    private void endEffectHelpButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endEffectHelpButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endEffectHelpButton1ActionPerformed

    private void demoAllButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoAllButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_demoAllButton1ActionPerformed

    private void btn_Save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Save1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Save1ActionPerformed

    private void btn_Cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Cancel1ActionPerformed

    /**
     *  Decide whether to use the verbatim text field after checking the content type.
     */
    public void checkContentType(int index){
        if (((JComboBox)getComponentByName("contentTypeBox" + index)).getSelectedIndex() 
                == OSP_enums.EBD_ContentType.VERBATIM.ordinal())
        {   // tf_VerbatimContent0
            ((JTextField) getComponentByName("tf_VerbatimContent" + index)).setEnabled(true);
            if (getComponentByName("tf_VerbatimContent" + index) == null) {
                System.out.println("not found: " + "tf_VerbatimContent" + index);
                return;
            }
            ((JTextField) getComponentByName("tf_VerbatimContent" + index)).setText(
                ledNoticeSettings[index].verbatimContent);
        }
        else
        {
            ((JTextField) getComponentByName("tf_VerbatimContent" + index)).setEnabled(false);
            ((JTextField) getComponentByName("tf_VerbatimContent" + index)).setText(null);
        }
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
            java.util.logging.Logger.getLogger(Settings_LEDnotice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings_LEDnotice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings_LEDnotice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings_LEDnotice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        final ParkingTimer connectDeviceTimer = new ParkingTimer("ospConnect_LEDnoticeTimer", false); 
        final byte gateNo = 1;
        
        initializeLoggers();
        checkOptions(args);
        readSettings();              
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("ResultOfObjectAllocationIgnored")
            public void run() {
                ControlGUI controlGUI = new ControlGUI();
                connectDeviceTimer.runOnce(new ConnectDeviceTask(controlGUI, E_Board, gateNo));
                LEDnoticeManager manager = new LEDnoticeManager(controlGUI, gateNo);
                new Settings_LEDnotice(controlGUI, null, manager, gateNo).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancel0;
    private javax.swing.JButton btn_Cancel1;
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_Save0;
    private javax.swing.JButton btn_Save1;
    private javax.swing.JComboBox charColor0;
    private javax.swing.JComboBox charColor1;
    private javax.swing.JComboBox charFont0;
    private javax.swing.JComboBox charFont1;
    private javax.swing.JComboBox combo_EndEffect0;
    private javax.swing.JComboBox combo_EndEffect1;
    private javax.swing.JComboBox combo_StartEffect0;
    private javax.swing.JComboBox combo_StartEffect1;
    private javax.swing.JComboBox combo_Stoptime0;
    private javax.swing.JComboBox combo_Stoptime1;
    private javax.swing.JComboBox contentTypeBox0;
    private javax.swing.JComboBox contentTypeBox1;
    private javax.swing.JButton demoAllButton0;
    private javax.swing.JButton demoAllButton1;
    private javax.swing.JButton demoAllHelpButton0;
    private javax.swing.JButton demoAllHelpButton1;
    private javax.swing.JButton demoButton0;
    private javax.swing.JButton demoButton1;
    private javax.swing.JButton demoCurrHelpButton0;
    private javax.swing.JButton demoCurrHelpButton1;
    private javax.swing.JButton demoFinishButton0;
    private javax.swing.JButton demoFinishButton1;
    private javax.swing.JButton endEffectHelpButton0;
    private javax.swing.JButton endEffectHelpButton1;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel label_Color0;
    private javax.swing.JLabel label_Color1;
    private javax.swing.JLabel label_Color10;
    private javax.swing.JLabel label_Color11;
    private javax.swing.JLabel label_Color2;
    private javax.swing.JLabel label_Color3;
    private javax.swing.JLabel label_Color4;
    private javax.swing.JLabel label_Color5;
    private javax.swing.JLabel label_Color6;
    private javax.swing.JLabel label_Color7;
    private javax.swing.JLabel label_Color8;
    private javax.swing.JLabel label_Color9;
    private javax.swing.JLabel label_ContentType0;
    private javax.swing.JLabel label_ContentType1;
    private javax.swing.JLabel label_ContentType2;
    private javax.swing.JLabel label_ContentType3;
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
    private javax.swing.JPanel ledNoticePanel;
    private javax.swing.JPanel ledNoticePanel0;
    private javax.swing.JPanel ledNoticePanel1;
    private javax.swing.JPanel ledNoticePanel2;
    private javax.swing.JPanel ledNoticePanel3;
    private javax.swing.JTabbedPane ledNoticePanelDefault;
    private javax.swing.JTabbedPane ledNoticePanelVehicle;
    private javax.swing.JTabbedPane ledNoticeTabbedPane;
    private javax.swing.JButton startEffectHelpButton0;
    private javax.swing.JButton startEffectHelpButton1;
    private javax.swing.JTextField tf_VerbatimContent0;
    private javax.swing.JTextField tf_VerbatimContent1;
    private javax.swing.JCheckBox useLEDnoticeCBox0;
    private javax.swing.JCheckBox useLEDnoticeCBox1;
    private javax.swing.JPanel wholePanel1;
    // End of variables declaration//GEN-END:variables

    private int findGateNoUsingLEDnotice() {
        int index = -1;
        
        for (int gateNo = 1; gateNo <= gateCount; gateNo++) {
            if (Globals.gateDeviceTypes[gateNo].eBoardType == OSP_enums.E_BoardType.LEDnotice) {
                index = gateNo;
            }
        }
        return index;
    }    
    
    private void checkLEDnoticeRowUsageChangeAndChangeButtonEnabled(EBD_DisplayUsage usage ) {
        if (LEDnoticeManager.ledNoticeSettings[usage.ordinal()].isUsed &&
                !useLEDnoticeCBox0.isSelected() ||
                !LEDnoticeManager.ledNoticeSettings[usage.ordinal()].isUsed &&
                useLEDnoticeCBox0.isSelected()) 
        {
            changeEnabled_of_SaveCancelButtons(true);
        } else {
            changeEnabled_of_SaveCancelButtons(false);
        }    
    }    

    private void changeEnabled_of_SaveCancelButtons(boolean onOff) {
        btn_Save0.setEnabled(onOff);
        btn_Cancel0.setEnabled(onOff);        
        btn_Exit.setEnabled(!onOff);
    }     

    private void makeComponentMap() {
        componentMap = new HashMap<String,Component>();
        
        for (Component defVehPane : ledNoticeTabbedPane.getComponents()) {
            for (Component upDnDefVehiche : ((JTabbedPane) defVehPane).getComponents()) {
                for (Component leafCompo : ((JPanel) upDnDefVehiche).getComponents()) {
                    if (leafCompo instanceof JTextField || leafCompo instanceof JComboBox) {
                        componentMap.put(leafCompo.getName(), leafCompo);
                        System.out.println("added: " + leafCompo.getName());
                    }
                }
            }
        }
    }

    private void tryToCloseEBDSettingsForm() {
        if (formMode == OSP_enums.FormMode.MODIFICATION) {
            JOptionPane.showMessageDialog(this, 
                    "E-Board settings is being modified," + System.lineSeparator()
                            + "Either [Save] or [Cancel] current changes!"); 
        } else {
            if (parent != null)
                parent.setEBDsettings(null);    
            this.dispose();    
        }    
    }
}

