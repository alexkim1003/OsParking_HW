/* ,
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

import com.osparking.global.names.JDBCMySQL;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.osparking.global.names.ConvComboBoxItem;
import static com.osparking.global.names.DB_Access.EBD_blinkCycle;
import static com.osparking.global.names.DB_Access.EBD_flowCycle;
import static com.osparking.global.names.DB_Access.PIC_HEIGHT;
import static com.osparking.global.names.DB_Access.PIC_WIDTH;
import static com.osparking.global.names.DB_Access.deviceIP;
import static com.osparking.global.names.DB_Access.gateCount;
import static com.osparking.global.names.DB_Access.gateNames;
import static com.osparking.global.names.DB_Access.localeIndex;
import static com.osparking.global.names.DB_Access.passingDelayCurrentTotalMs;
import static com.osparking.global.names.DB_Access.maxMaintainDate;
import static com.osparking.global.names.DB_Access.maxMessageLines;
import static com.osparking.global.names.DB_Access.opLoggingIndex;
import static com.osparking.global.names.DB_Access.storePassingDelay;
import static com.osparking.global.names.DB_Access.pwStrengthLevel;
import static com.osparking.global.names.DB_Access.readSettings;
import static com.osparking.global.names.DB_Access.statCount;
import static com.osparking.global.names.DB_Access.passingCountCurrent;
import com.osparking.global.Globals;
import static com.osparking.global.Globals.*; 
import static com.osparking.global.names.OSP_enums.DeviceType.*;
import com.osparking.global.names.OSP_enums.OpLogLevel;
import static com.osparking.global.names.OSP_enums.OpLogLevel.LogAlways;
import static com.osparking.global.names.OSP_enums.OpLogLevel.SettingsChange;
import static com.osparking.global.names.OSP_enums.OpLogLevel.EBDsettingsChange;
import com.osparking.global.names.PasswordValidator;
import com.osparking.global.names.ChangeSettings;
import org.apache.commons.validator.routines.InetAddressValidator;
import com.osparking.attendant.PWHelpJDialog;
import static com.osparking.global.names.ControlEnums.ButtonTypes.*;
import static com.osparking.global.names.ControlEnums.ComboBoxItemTypes.*;
import static com.osparking.global.names.ControlEnums.DialogMSGTypes.*;
import static com.osparking.global.names.ControlEnums.DialogTitleTypes.*;
import static com.osparking.global.names.ControlEnums.LabelTypes.*;
import static com.osparking.global.names.ControlEnums.Languages.KOREAN;
import com.osparking.global.names.ControlEnums.TextType;
import static com.osparking.global.names.ControlEnums.TextType.PASSING_DELAY_AVG_MSG;
import static com.osparking.global.names.ControlEnums.TitleTypes.SYSTEM_SETTINGS_FRAME_TITLE;
import static com.osparking.global.names.ControlEnums.ToolTipTypes.*;
import com.osparking.global.names.DB_Access;
import static com.osparking.global.names.DB_Access.devicePort;
import static com.osparking.global.names.DB_Access.parkingLotName;
import com.osparking.global.names.OSP_enums.DeviceType;
import com.osparking.global.names.OSP_enums.PWStrengthLevel;
import static com.osparking.osparking.ControlGUI.EBD_DisplaySettings;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Provides GUI to set system parameters like image size(pixels), gate count, device IP address, etc.
 * For a stable operation, it is recommended to reboot this system(OS.Parking program) after any setting change.
 * @author Open Source Parking Inc.
 */
public class Settings_System extends javax.swing.JFrame {
    private boolean isStand_Alone = false;
    private static Logger logException = null;
    private static Logger logOperation = null;
    public static ControlGUI mainForm = null;
    private HashMap<String,Component> componentMap = new HashMap<String,Component>();;
    public short maxArrivalCBoxIndex = 0;
    private HashSet<Component> changedControls = new HashSet<Component>();
    
    /**
     * Initialize some controls on the system settings change GUI. 
     */
    public Settings_System(ControlGUI mainForm) {
        initComponents();
        setIconImages(OSPiconList);                
        augmentComponentMap(this, componentMap);
        this.mainForm = mainForm;
        if (mainForm == null)
            isStand_Alone = true;
        addPWStrengthItems();
        addMaxArrivalItems();
        maxArrivalCBoxIndex = findCBoxIndex(MaxDateComboBox, maxMaintainDate);
        addOperationLoggingLevelOptions();
        loadComponentValues();
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
        topPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        StatPopSizeTextField = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        PassingDelayCheckBox = new javax.swing.JCheckBox();
        parkinglotOptionPanel = new javax.swing.JPanel();
        PWStrengthChoiceComboBox = new javax.swing.JComboBox<ConvComboBoxItem>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        OptnLoggingLevelComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        MessageMaxLineComboBox = new javax.swing.JComboBox();
        LanguageSelectionlHelpButton = new javax.swing.JButton();
        LoggingLevelHelpButton = new javax.swing.JButton();
        PWHelpButton = new javax.swing.JButton();
        MaxDateComboBox = new javax.swing.JComboBox<ConvComboBoxItem>();
        jLabel7 = new javax.swing.JLabel();
        GateCountComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TextFieldPicWidth = new javax.swing.JTextField();
        TextFieldPicHeight = new javax.swing.JTextField();
        DateChooserLangCBox = new com.toedter.components.JLocaleChooser();
        jLabel39 = new javax.swing.JLabel();
        lotNameTextField = new javax.swing.JTextField();
        gateSettingPanel = new javax.swing.JPanel();
        GatesTabbedPane = new javax.swing.JTabbedPane();
        gate1Panel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        TextFieldGateName1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        Camera1_IP_TextField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        GateBar1_IP_TextField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        E_Board1_IP_TextField = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        Camera1_Port_TextField = new javax.swing.JTextField();
        GateBar1_Port_TextField = new javax.swing.JTextField();
        E_Board1_Port_TextField = new javax.swing.JTextField();
        gate2Panel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TextFieldGateName2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Camera2_IP_TextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        GateBar2_IP_TextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        E_Board2_IP_TextField = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        Camera2_Port_TextField = new javax.swing.JTextField();
        GateBar2_Port_TextField = new javax.swing.JTextField();
        E_Board2_Port_TextField = new javax.swing.JTextField();
        gate3Panel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        TextFieldGateName3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Camera3_IP_TextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        GateBar3_IP_TextField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        E_Board3_IP_TextField = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        Camera3_Port_TextField = new javax.swing.JTextField();
        GateBar3_Port_TextField = new javax.swing.JTextField();
        E_Board3_Port_TextField = new javax.swing.JTextField();
        gate4Panel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        TextFieldGateName4 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        Camera4_IP_TextField = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        GateBar4_IP_TextField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        E_Board4_IP_TextField = new javax.swing.JTextField();
        Camera4_Port_TextField = new javax.swing.JTextField();
        GateBar4_Port_TextField = new javax.swing.JTextField();
        E_Board4_Port_TextField = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        Camera4_Port_TextField1 = new javax.swing.JTextField();
        GateBar4_Port_TextField1 = new javax.swing.JTextField();
        E_Board4_Port_TextField1 = new javax.swing.JTextField();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(1, 0), new java.awt.Dimension(1, 0), new java.awt.Dimension(1, 32767));
        eBoardSettingPanel = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        EBoardSettingsButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        BlinkingComboBox = new javax.swing.JComboBox();
        FlowingComboBox = new javax.swing.JComboBox();
        bottomPanel = new javax.swing.JPanel();
        SettingsSaveButton = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        SettingsCancelButton = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        SettingsCloseButton = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 20));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(10, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(40, 0), new java.awt.Dimension(10, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 20));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(((String[])Globals.TitleList.get(SYSTEM_SETTINGS_FRAME_TITLE.ordinal()))[ourLang] + " -- OsParking");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(640, 568));
        setPreferredSize(new java.awt.Dimension(660, 580));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                finishSettingsForm(evt);
            }
        });

        wholePanel.setMinimumSize(new java.awt.Dimension(630, 528));
        wholePanel.setPreferredSize(new java.awt.Dimension(462, 443));
        wholePanel.setLayout(new javax.swing.BoxLayout(wholePanel, javax.swing.BoxLayout.PAGE_AXIS));

        topPanel.setPreferredSize(new java.awt.Dimension(460, 35));

        jLabel4.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText(((String[])Globals.LabelsText.get(STATISTICS_SIZE_LABEL.ordinal()))[ourLang]);
        topPanel.add(jLabel4);

        StatPopSizeTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        StatPopSizeTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        StatPopSizeTextField.setMinimumSize(new java.awt.Dimension(6, 25));
        StatPopSizeTextField.setName("StatPopSizeTextField"); // NOI18N
        StatPopSizeTextField.setPreferredSize(new java.awt.Dimension(70, 25));
        StatPopSizeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                StatPopSizeTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                StatPopSizeTextFieldKeyTyped(evt);
            }
        });
        topPanel.add(StatPopSizeTextField);
        topPanel.add(filler1);

        PassingDelayCheckBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        PassingDelayCheckBox.setText(((String[])Globals.LabelsText.get(RECORD_PASSING_LABEL.ordinal()))[ourLang]);
        PassingDelayCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        PassingDelayCheckBox.setName("PassingDelayCheckBox"); // NOI18N
        PassingDelayCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassingDelayCheckBoxActionPerformed(evt);
            }
        });
        topPanel.add(PassingDelayCheckBox);

        wholePanel.add(topPanel);

        parkinglotOptionPanel.setPreferredSize(new java.awt.Dimension(460, 250));
        parkinglotOptionPanel.setLayout(new java.awt.GridBagLayout());

        PWStrengthChoiceComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        PWStrengthChoiceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        PWStrengthChoiceComboBox.setName("PWStrengthChoiceComboBox"); // NOI18N
        PWStrengthChoiceComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                PWStrengthChoiceComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        parkinglotOptionPanel.add(PWStrengthChoiceComboBox, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText(((String[])Globals.LabelsText.get(LOT_NAME_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        parkinglotOptionPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText(((String[])Globals.LabelsText.get(LOGGING_LEVEL_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        parkinglotOptionPanel.add(jLabel2, gridBagConstraints);

        OptnLoggingLevelComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        OptnLoggingLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        OptnLoggingLevelComboBox.setName("OptnLoggingLevelComboBox"); // NOI18N
        OptnLoggingLevelComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                OptnLoggingLevelComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        parkinglotOptionPanel.add(OptnLoggingLevelComboBox, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText(((String[])Globals.LabelsText.get(LANGUAGE_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        parkinglotOptionPanel.add(jLabel3, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText(((String[])Globals.LabelsText.get(MAX_LINE_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        parkinglotOptionPanel.add(jLabel5, gridBagConstraints);

        MessageMaxLineComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        MessageMaxLineComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100", "200", "300", "500", "1000" }));
        MessageMaxLineComboBox.setName("MessageMaxLineComboBox"); // NOI18N
        MessageMaxLineComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                MessageMaxLineComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        parkinglotOptionPanel.add(MessageMaxLineComboBox, gridBagConstraints);

        LanguageSelectionlHelpButton.setBackground(new java.awt.Color(153, 255, 153));
        LanguageSelectionlHelpButton.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        LanguageSelectionlHelpButton.setIcon(getQuest20_Icon());
        LanguageSelectionlHelpButton.setMargin(new java.awt.Insets(2, 4, 2, 4));
        LanguageSelectionlHelpButton.setMinimumSize(new java.awt.Dimension(20, 20));
        LanguageSelectionlHelpButton.setOpaque(false);
        LanguageSelectionlHelpButton.setPreferredSize(new java.awt.Dimension(20, 20));
        LanguageSelectionlHelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LanguageSelectionlHelpButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        parkinglotOptionPanel.add(LanguageSelectionlHelpButton, gridBagConstraints);

        LoggingLevelHelpButton.setBackground(new java.awt.Color(153, 255, 153));
        LoggingLevelHelpButton.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        LoggingLevelHelpButton.setIcon(getQuest20_Icon());
        LoggingLevelHelpButton.setMargin(new java.awt.Insets(2, 4, 2, 4));
        LoggingLevelHelpButton.setMinimumSize(new java.awt.Dimension(20, 20));
        LoggingLevelHelpButton.setOpaque(false);
        LoggingLevelHelpButton.setPreferredSize(new java.awt.Dimension(20, 20));
        LoggingLevelHelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoggingLevelHelpButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        parkinglotOptionPanel.add(LoggingLevelHelpButton, gridBagConstraints);

        PWHelpButton.setBackground(new java.awt.Color(153, 255, 153));
        PWHelpButton.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        PWHelpButton.setIcon(getQuest20_Icon());
        PWHelpButton.setMargin(new java.awt.Insets(2, 4, 2, 4));
        PWHelpButton.setMinimumSize(new java.awt.Dimension(20, 20));
        PWHelpButton.setOpaque(false);
        PWHelpButton.setPreferredSize(new java.awt.Dimension(20, 20));
        PWHelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PWHelpButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        parkinglotOptionPanel.add(PWHelpButton, gridBagConstraints);

        MaxDateComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        MaxDateComboBox.setName("MaxDateComboBox"); // NOI18N
        MaxDateComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                MaxDateComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        parkinglotOptionPanel.add(MaxDateComboBox, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText(((String[])Globals.LabelsText.get(IMG_KEEP_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        parkinglotOptionPanel.add(jLabel7, gridBagConstraints);

        GateCountComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GateCountComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", " " }));
        GateCountComboBox.setName("GateCountComboBox"); // NOI18N
        GateCountComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                GateCountComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 43;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        parkinglotOptionPanel.add(GateCountComboBox, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText(((String[])Globals.LabelsText.get(GATE_NUM_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        parkinglotOptionPanel.add(jLabel6, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText(((String[])Globals.LabelsText.get(VEHICLE_IMG_SIZE_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        parkinglotOptionPanel.add(jLabel19, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText(((String[])Globals.LabelsText.get(VEHICLE_IMG_WIDTH_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        parkinglotOptionPanel.add(jLabel11, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("px");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        parkinglotOptionPanel.add(jLabel13, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText(((String[])Globals.LabelsText.get(VEHICLE_IMG_HEIGHT_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 0);
        parkinglotOptionPanel.add(jLabel12, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("px");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 10, 0);
        parkinglotOptionPanel.add(jLabel14, gridBagConstraints);

        TextFieldPicWidth.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        TextFieldPicWidth.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TextFieldPicWidth.setName("TextFieldPicWidth"); // NOI18N
        TextFieldPicWidth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldPicWidthKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldPicWidthKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 20);
        parkinglotOptionPanel.add(TextFieldPicWidth, gridBagConstraints);

        TextFieldPicHeight.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        TextFieldPicHeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TextFieldPicHeight.setName("TextFieldPicHeight"); // NOI18N
        TextFieldPicHeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldPicHeightKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextFieldPicHeightKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 20);
        parkinglotOptionPanel.add(TextFieldPicHeight, gridBagConstraints);

        DateChooserLangCBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        DateChooserLangCBox.setMinimumSize(new java.awt.Dimension(294, 23));
        DateChooserLangCBox.setPreferredSize(new java.awt.Dimension(280, 27));
        DateChooserLangCBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                DateChooserLangCBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        parkinglotOptionPanel.add(DateChooserLangCBox, gridBagConstraints);

        jLabel39.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText(((String[])Globals.LabelsText.get(PASSWORD_LEVEL_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        parkinglotOptionPanel.add(jLabel39, gridBagConstraints);

        lotNameTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        lotNameTextField.setMaximumSize(new java.awt.Dimension(32767, 32767));
        lotNameTextField.setName("lotNameTextField"); // NOI18N
        lotNameTextField.setPreferredSize(new java.awt.Dimension(200, 27));
        lotNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lotNameTextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        parkinglotOptionPanel.add(lotNameTextField, gridBagConstraints);

        wholePanel.add(parkinglotOptionPanel);

        gateSettingPanel.setMinimumSize(new java.awt.Dimension(660, 220));
        gateSettingPanel.setPreferredSize(new java.awt.Dimension(460, 150));
        gateSettingPanel.setLayout(new javax.swing.BoxLayout(gateSettingPanel, javax.swing.BoxLayout.LINE_AXIS));

        GatesTabbedPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        GatesTabbedPane.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GatesTabbedPane.setMinimumSize(new java.awt.Dimension(350, 215));
        GatesTabbedPane.setPreferredSize(new java.awt.Dimension(400, 200));

        gate1Panel.setEnabled(false);
        gate1Panel.setLayout(new java.awt.GridBagLayout());

        jLabel27.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText(((String[])Globals.LabelsText.get(GATE_NAME_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate1Panel.add(jLabel27, gridBagConstraints);

        TextFieldGateName1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        TextFieldGateName1.setText("1st Gate");
        TextFieldGateName1.setName("TextFieldGateName1"); // NOI18N
        TextFieldGateName1.setPreferredSize(new java.awt.Dimension(30, 23));
        TextFieldGateName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldGateName1KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate1Panel.add(TextFieldGateName1, gridBagConstraints);

        jLabel28.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText(((String[])Globals.LabelsText.get(CAMERA_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        jLabel28.setMaximumSize(new java.awt.Dimension(130, 15));
        jLabel28.setPreferredSize(new java.awt.Dimension(130, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate1Panel.add(jLabel28, gridBagConstraints);

        Camera1_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        Camera1_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        Camera1_IP_TextField.setName("Camera1_IP_TextField"); // NOI18N
        Camera1_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        Camera1_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Camera1_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate1Panel.add(Camera1_IP_TextField, gridBagConstraints);

        jLabel29.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText(((String[])Globals.LabelsText.get(GATE_BAR_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate1Panel.add(jLabel29, gridBagConstraints);

        GateBar1_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GateBar1_IP_TextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        GateBar1_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        GateBar1_IP_TextField.setName("GateBar1_IP_TextField"); // NOI18N
        GateBar1_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        GateBar1_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GateBar1_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate1Panel.add(GateBar1_IP_TextField, gridBagConstraints);

        jLabel30.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText(((String[])Globals.LabelsText.get(E_BOARD_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate1Panel.add(jLabel30, gridBagConstraints);

        E_Board1_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        E_Board1_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        E_Board1_IP_TextField.setName("E_Board1_IP_TextField"); // NOI18N
        E_Board1_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        E_Board1_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                E_Board1_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate1Panel.add(E_Board1_IP_TextField, gridBagConstraints);

        jLabel37.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel37.setText(((String[])Globals.LabelsText.get(PORT_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate1Panel.add(jLabel37, gridBagConstraints);

        Camera1_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        Camera1_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        Camera1_Port_TextField.setName("Camera1_Port_TextField"); // NOI18N
        Camera1_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        Camera1_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Camera1_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Camera1_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate1Panel.add(Camera1_Port_TextField, gridBagConstraints);

        GateBar1_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GateBar1_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        GateBar1_Port_TextField.setName("GateBar1_Port_TextField"); // NOI18N
        GateBar1_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        GateBar1_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GateBar1_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GateBar1_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate1Panel.add(GateBar1_Port_TextField, gridBagConstraints);

        E_Board1_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        E_Board1_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        E_Board1_Port_TextField.setName("E_Board1_Port_TextField"); // NOI18N
        E_Board1_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        E_Board1_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                E_Board1_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                E_Board1_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate1Panel.add(E_Board1_Port_TextField, gridBagConstraints);

        GatesTabbedPane.addTab(((String[])Globals.LabelsText.get(GATE_LABEL.ordinal()))[ourLang] + "1", gate1Panel);

        gate2Panel.setEnabled(false);
        gate2Panel.setLayout(new java.awt.GridBagLayout());

        jLabel15.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText(((String[])Globals.LabelsText.get(GATE_NAME_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate2Panel.add(jLabel15, gridBagConstraints);

        TextFieldGateName2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        TextFieldGateName2.setText("2nd Gate");
        TextFieldGateName2.setName("TextFieldGateName2"); // NOI18N
        TextFieldGateName2.setPreferredSize(new java.awt.Dimension(30, 23));
        TextFieldGateName2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldGateName2KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate2Panel.add(TextFieldGateName2, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText(((String[])Globals.LabelsText.get(CAMERA_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate2Panel.add(jLabel9, gridBagConstraints);

        Camera2_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        Camera2_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        Camera2_IP_TextField.setName("Camera2_IP_TextField"); // NOI18N
        Camera2_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        Camera2_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Camera2_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate2Panel.add(Camera2_IP_TextField, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText(((String[])Globals.LabelsText.get(GATE_BAR_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate2Panel.add(jLabel21, gridBagConstraints);

        GateBar2_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GateBar2_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        GateBar2_IP_TextField.setName("GateBar2_IP_TextField"); // NOI18N
        GateBar2_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        GateBar2_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GateBar2_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate2Panel.add(GateBar2_IP_TextField, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText(((String[])Globals.LabelsText.get(E_BOARD_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate2Panel.add(jLabel22, gridBagConstraints);

        E_Board2_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        E_Board2_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        E_Board2_IP_TextField.setName("E_Board2_IP_TextField"); // NOI18N
        E_Board2_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        E_Board2_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                E_Board2_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate2Panel.add(E_Board2_IP_TextField, gridBagConstraints);

        jLabel36.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel36.setText(((String[])Globals.LabelsText.get(PORT_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate2Panel.add(jLabel36, gridBagConstraints);

        Camera2_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        Camera2_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        Camera2_Port_TextField.setName("Camera2_Port_TextField"); // NOI18N
        Camera2_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        Camera2_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Camera2_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Camera2_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate2Panel.add(Camera2_Port_TextField, gridBagConstraints);

        GateBar2_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GateBar2_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        GateBar2_Port_TextField.setName("GateBar2_Port_TextField"); // NOI18N
        GateBar2_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        GateBar2_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GateBar2_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GateBar2_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate2Panel.add(GateBar2_Port_TextField, gridBagConstraints);

        E_Board2_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        E_Board2_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        E_Board2_Port_TextField.setName("E_Board2_Port_TextField"); // NOI18N
        E_Board2_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        E_Board2_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                E_Board2_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                E_Board2_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate2Panel.add(E_Board2_Port_TextField, gridBagConstraints);

        GatesTabbedPane.addTab(((String[])Globals.LabelsText.get(GATE_LABEL.ordinal()))[ourLang] + "2", gate2Panel);

        gate3Panel.setEnabled(false);
        gate3Panel.setLayout(new java.awt.GridBagLayout());

        jLabel16.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText(((String[])Globals.LabelsText.get(GATE_NAME_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate3Panel.add(jLabel16, gridBagConstraints);

        TextFieldGateName3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        TextFieldGateName3.setText("3rd Gate");
        TextFieldGateName3.setName("TextFieldGateName3"); // NOI18N
        TextFieldGateName3.setPreferredSize(new java.awt.Dimension(30, 23));
        TextFieldGateName3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldGateName3KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate3Panel.add(TextFieldGateName3, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText(((String[])Globals.LabelsText.get(CAMERA_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate3Panel.add(jLabel10, gridBagConstraints);

        Camera3_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        Camera3_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        Camera3_IP_TextField.setName("Camera3_IP_TextField"); // NOI18N
        Camera3_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        Camera3_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Camera3_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate3Panel.add(Camera3_IP_TextField, gridBagConstraints);

        jLabel23.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText(((String[])Globals.LabelsText.get(GATE_BAR_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate3Panel.add(jLabel23, gridBagConstraints);

        GateBar3_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GateBar3_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        GateBar3_IP_TextField.setName("GateBar3_IP_TextField"); // NOI18N
        GateBar3_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        GateBar3_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GateBar3_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate3Panel.add(GateBar3_IP_TextField, gridBagConstraints);

        jLabel24.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText(((String[])Globals.LabelsText.get(E_BOARD_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate3Panel.add(jLabel24, gridBagConstraints);

        E_Board3_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        E_Board3_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        E_Board3_IP_TextField.setName("E_Board3_IP_TextField"); // NOI18N
        E_Board3_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        E_Board3_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                E_Board3_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate3Panel.add(E_Board3_IP_TextField, gridBagConstraints);

        jLabel38.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel38.setText(((String[])Globals.LabelsText.get(PORT_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate3Panel.add(jLabel38, gridBagConstraints);

        Camera3_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        Camera3_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        Camera3_Port_TextField.setName("Camera3_Port_TextField"); // NOI18N
        Camera3_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        Camera3_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Camera3_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Camera3_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate3Panel.add(Camera3_Port_TextField, gridBagConstraints);

        GateBar3_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GateBar3_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        GateBar3_Port_TextField.setName("GateBar3_Port_TextField"); // NOI18N
        GateBar3_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        GateBar3_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GateBar3_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GateBar3_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate3Panel.add(GateBar3_Port_TextField, gridBagConstraints);

        E_Board3_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        E_Board3_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        E_Board3_Port_TextField.setName("E_Board3_Port_TextField"); // NOI18N
        E_Board3_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        E_Board3_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                E_Board3_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                E_Board3_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate3Panel.add(E_Board3_Port_TextField, gridBagConstraints);

        GatesTabbedPane.addTab(((String[])Globals.LabelsText.get(GATE_LABEL.ordinal()))[ourLang] + "3", gate3Panel);

        gate4Panel.setEnabled(false);
        gate4Panel.setLayout(new java.awt.GridBagLayout());

        jLabel17.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText(((String[])Globals.LabelsText.get(GATE_NAME_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(jLabel17, gridBagConstraints);

        TextFieldGateName4.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        TextFieldGateName4.setText("4th Gate");
        TextFieldGateName4.setName("TextFieldGateName4"); // NOI18N
        TextFieldGateName4.setPreferredSize(new java.awt.Dimension(30, 23));
        TextFieldGateName4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldGateName4KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(TextFieldGateName4, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText(((String[])Globals.LabelsText.get(CAMERA_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(jLabel18, gridBagConstraints);

        Camera4_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        Camera4_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        Camera4_IP_TextField.setName("Camera4_IP_TextField"); // NOI18N
        Camera4_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        Camera4_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Camera4_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(Camera4_IP_TextField, gridBagConstraints);

        jLabel25.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText(((String[])Globals.LabelsText.get(GATE_BAR_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(jLabel25, gridBagConstraints);

        GateBar4_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GateBar4_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        GateBar4_IP_TextField.setName("GateBar4_IP_TextField"); // NOI18N
        GateBar4_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        GateBar4_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GateBar4_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(GateBar4_IP_TextField, gridBagConstraints);

        jLabel26.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText(((String[])Globals.LabelsText.get(E_BOARD_IP_ADDRESS_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate4Panel.add(jLabel26, gridBagConstraints);

        E_Board4_IP_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        E_Board4_IP_TextField.setMinimumSize(new java.awt.Dimension(120, 27));
        E_Board4_IP_TextField.setName("E_Board4_IP_TextField"); // NOI18N
        E_Board4_IP_TextField.setPreferredSize(new java.awt.Dimension(90, 23));
        E_Board4_IP_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                E_Board4_IP_TextFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate4Panel.add(E_Board4_IP_TextField, gridBagConstraints);

        Camera4_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        Camera4_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        Camera4_Port_TextField.setName("Camera4_Port_TextField"); // NOI18N
        Camera4_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        Camera4_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Camera4_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Camera4_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(Camera4_Port_TextField, gridBagConstraints);

        GateBar4_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        GateBar4_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        GateBar4_Port_TextField.setName("GateBar4_Port_TextField"); // NOI18N
        GateBar4_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        GateBar4_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GateBar4_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GateBar4_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(GateBar4_Port_TextField, gridBagConstraints);

        E_Board4_Port_TextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        E_Board4_Port_TextField.setMinimumSize(new java.awt.Dimension(50, 27));
        E_Board4_Port_TextField.setName("E_Board4_Port_TextField"); // NOI18N
        E_Board4_Port_TextField.setPreferredSize(new java.awt.Dimension(50, 27));
        E_Board4_Port_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                E_Board4_Port_TextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                E_Board4_Port_TextFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate4Panel.add(E_Board4_Port_TextField, gridBagConstraints);

        jLabel35.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel35.setText(((String[])Globals.LabelsText.get(PORT_LABEL.ordinal()))[ourLang]);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(jLabel35, gridBagConstraints);

        Camera4_Port_TextField1.setMinimumSize(new java.awt.Dimension(40, 27));
        Camera4_Port_TextField1.setPreferredSize(new java.awt.Dimension(40, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(Camera4_Port_TextField1, gridBagConstraints);

        GateBar4_Port_TextField1.setMinimumSize(new java.awt.Dimension(40, 27));
        GateBar4_Port_TextField1.setPreferredSize(new java.awt.Dimension(40, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        gate4Panel.add(GateBar4_Port_TextField1, gridBagConstraints);

        E_Board4_Port_TextField1.setMinimumSize(new java.awt.Dimension(40, 27));
        E_Board4_Port_TextField1.setPreferredSize(new java.awt.Dimension(40, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 10);
        gate4Panel.add(E_Board4_Port_TextField1, gridBagConstraints);

        GatesTabbedPane.addTab(((String[])Globals.LabelsText.get(GATE_LABEL.ordinal()))[ourLang] + "4", gate4Panel);

        gateSettingPanel.add(GatesTabbedPane);
        GatesTabbedPane.getAccessibleContext().setAccessibleName("((String[])Globals.LabelsText.get(GATE_LABEL.ordinal()))[ourLang] + \"1\"");

        gateSettingPanel.add(filler7);

        eBoardSettingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        eBoardSettingPanel.setMinimumSize(new java.awt.Dimension(300, 215));
        eBoardSettingPanel.setLayout(new java.awt.GridBagLayout());

        jLabel34.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText(((String[])Globals.LabelsText.get(BLINGKING_LABEL.ordinal()))[ourLang]);
        jLabel34.setMaximumSize(new java.awt.Dimension(80, 15));
        jLabel34.setPreferredSize(new java.awt.Dimension(60, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        eBoardSettingPanel.add(jLabel34, gridBagConstraints);

        jLabel32.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("ms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        eBoardSettingPanel.add(jLabel32, gridBagConstraints);

        jLabel33.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("ms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        eBoardSettingPanel.add(jLabel33, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText(((String[])Globals.LabelsText.get(ELECTRONIC_DISPLAY_LABEL.ordinal()))[ourLang]);
        jLabel20.setMaximumSize(new java.awt.Dimension(300, 27));
        jLabel20.setPreferredSize(new java.awt.Dimension(250, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        eBoardSettingPanel.add(jLabel20, gridBagConstraints);

        jLabel31.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText(((String[])Globals.LabelsText.get(FLOWING_LABEL.ordinal()))[ourLang]);
        jLabel31.setMaximumSize(new java.awt.Dimension(80, 15));
        jLabel31.setPreferredSize(new java.awt.Dimension(60, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        eBoardSettingPanel.add(jLabel31, gridBagConstraints);

        EBoardSettingsButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        EBoardSettingsButton.setText(((String[])Globals.ButtonLabels.get(E_BOARD_SETTINGS_BTN.ordinal()))[ourLang]);
        EBoardSettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EBoardSettingsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        eBoardSettingPanel.add(EBoardSettingsButton, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel8.setText(((String[])Globals.LabelsText.get(CYCLE_LABEL.ordinal()))[ourLang]);
        jLabel8.setMaximumSize(new java.awt.Dimension(80, 15));
        jLabel8.setMinimumSize(new java.awt.Dimension(80, 15));
        jLabel8.setPreferredSize(new java.awt.Dimension(80, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        eBoardSettingPanel.add(jLabel8, gridBagConstraints);

        BlinkingComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        BlinkingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "500", "750", "1,000", "1,250", "1,500" }));
        BlinkingComboBox.setName("BlinkingComboBox"); // NOI18N
        ((JLabel) BlinkingComboBox.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        BlinkingComboBox.setMinimumSize(new java.awt.Dimension(55, 23));
        BlinkingComboBox.setPreferredSize(new java.awt.Dimension(55, 23));
        BlinkingComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                BlinkingComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        eBoardSettingPanel.add(BlinkingComboBox, gridBagConstraints);

        FlowingComboBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        FlowingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4,000", "6,000", "8,000", "10,000", "12,000" }));
        FlowingComboBox.setMinimumSize(new java.awt.Dimension(55, 23));
        FlowingComboBox.setName("FlowingComboBox"); // NOI18N
        FlowingComboBox.setPreferredSize(new java.awt.Dimension(55, 23));
        ((JLabel) FlowingComboBox.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        FlowingComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                FlowingComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        eBoardSettingPanel.add(FlowingComboBox, gridBagConstraints);

        gateSettingPanel.add(eBoardSettingPanel);

        wholePanel.add(gateSettingPanel);

        bottomPanel.setPreferredSize(new java.awt.Dimension(460, 50));

        SettingsSaveButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        SettingsSaveButton.setMnemonic('s');
        SettingsSaveButton.setText(((String[])Globals.ButtonLabels.get(SAVE_BTN.ordinal()))[ourLang]);
        SettingsSaveButton.setToolTipText("Save your changes");
        SettingsSaveButton.setEnabled(false);
        SettingsSaveButton.setPreferredSize(new java.awt.Dimension(90, 40));
        SettingsSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsSaveButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(SettingsSaveButton);
        bottomPanel.add(filler5);

        SettingsCancelButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        SettingsCancelButton.setMnemonic('c');
        SettingsCancelButton.setText(((String[])Globals.ButtonLabels.get(CANCEL_BTN.ordinal()))[ourLang]);
        SettingsCancelButton.setToolTipText(((String[])Globals.ToolTipLabels.get(SETTINGS_CANCEL_TOOLTIP.ordinal()))[ourLang]);
        SettingsCancelButton.setEnabled(false);
        SettingsCancelButton.setPreferredSize(new java.awt.Dimension(90, 40));
        SettingsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsCancelButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(SettingsCancelButton);
        bottomPanel.add(filler8);

        SettingsCloseButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        SettingsCloseButton.setMnemonic('c');
        SettingsCloseButton.setText(((String[])Globals.ButtonLabels.get(CLOSE_BTN.ordinal()))[ourLang]);
        SettingsCloseButton.setToolTipText(((String[])Globals.ToolTipLabels.get(CLOSE_BTN_TOOLTIP.ordinal()))[ourLang]);
        SettingsCloseButton.setPreferredSize(new java.awt.Dimension(90, 40));
        SettingsCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsCloseButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(SettingsCloseButton);

        wholePanel.add(bottomPanel);

        getContentPane().add(wholePanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(filler2, java.awt.BorderLayout.PAGE_START);
        getContentPane().add(filler4, java.awt.BorderLayout.LINE_START);
        getContentPane().add(filler6, java.awt.BorderLayout.LINE_END);
        getContentPane().add(filler3, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(764, 715));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void StatPopSizeTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatPopSizeTextFieldKeyTyped
        rejectNonNumericKeys(evt);       
    }//GEN-LAST:event_StatPopSizeTextFieldKeyTyped

    private void LanguageSelectionlHelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LanguageSelectionlHelpButtonActionPerformed
        // TODO add your handling code here:
        String helpText = ((String[])Globals.DialogMSGList.get(LANGUAGE_USE_DIALOG.ordinal()))[ourLang];

        JDialog helpDialog = new PWHelpJDialog(this, false,
            ((String[])Globals.DialogTitleList.get(LANGUAGE_SELECT_DIALOGTITLE.ordinal()))[ourLang], helpText);
        locateAndShowHelpDialog(this, helpDialog, LoggingLevelHelpButton);
    }//GEN-LAST:event_LanguageSelectionlHelpButtonActionPerformed

    private void LoggingLevelHelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoggingLevelHelpButtonActionPerformed

        String helpText = null;
        if (OptnLoggingLevelComboBox.getSelectedIndex() == OpLogLevel.LogAlways.ordinal()) {
            helpText = ((String[])Globals.DialogMSGList.get(LOGALWAYS_DAILOG.ordinal()))[ourLang];
        } else
        if (OptnLoggingLevelComboBox.getSelectedIndex() == OpLogLevel.SettingsChange.ordinal()) {
            helpText = ((String[])Globals.DialogMSGList.get(SETTINGSCHANGE_DAILOG.ordinal()))[ourLang];
        } else
        if (OptnLoggingLevelComboBox.getSelectedIndex() == OpLogLevel.EBDsettingsChange.ordinal()) {
            helpText = ((String[])Globals.DialogMSGList.get(EBDSETTINGSCHANGE_DIALOG.ordinal()))[ourLang];
        }

        JDialog helpDialog = new PWHelpJDialog(this, false,
            ((String[])Globals.DialogTitleList.get(LOGGING_DIALOGTITLE.ordinal()))[ourLang], helpText);
        locateAndShowHelpDialog(this, helpDialog, LoggingLevelHelpButton);
    }//GEN-LAST:event_LoggingLevelHelpButtonActionPerformed

    private void PWHelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PWHelpButtonActionPerformed
        PasswordValidator pwValidator = new PasswordValidator();
        short pwPowerLevel = (short)PWStrengthChoiceComboBox.getSelectedIndex();
        String helpText = pwValidator.getWrongPWFormatMsg(pwPowerLevel);

        JDialog helpDialog = new PWHelpJDialog(this, false, 
                ((String[])Globals.DialogTitleList.get(PASSWORD_REQUIR_DIALOGTITLE.ordinal()))[ourLang], helpText);
        Point buttonPoint = new Point();
        PWHelpButton.getLocation(buttonPoint);

        Point framePoint = new Point(); 
        this.getLocation(framePoint);
        Point topLeft = new Point(framePoint.x + buttonPoint.x + 60, framePoint.y + buttonPoint.y - 60);
        helpDialog.setLocation(topLeft);
        helpDialog.setVisible(true);
    }//GEN-LAST:event_PWHelpButtonActionPerformed

    private Settings_EBoard eBDsettings = null;
    
    private void EBoardSettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EBoardSettingsButtonActionPerformed
        setEBDsettings(new Settings_EBoard(mainForm, this));
        
        Point panelPoint = new Point();
        EBoardSettingsButton.getLocation(panelPoint);
        
        Point buttonPoint = new Point();
        eBoardSettingPanel.getLocation(buttonPoint);
        
        Point framePoint = new Point();
        this.getLocation(framePoint);
        Point topLeft = new Point(framePoint.x + buttonPoint.x  + panelPoint.x+ EBoardSettingsButton.getSize().width + 130, 
                framePoint.y + buttonPoint.y + eBDsettings.getSize().height + 120);
        eBDsettings.setLocation(topLeft);
        eBDsettings.setVisible(true);
    }//GEN-LAST:event_EBoardSettingsButtonActionPerformed

    private void SettingsSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsSaveButtonActionPerformed
        Connection conn = null;
        PreparedStatement updateSettings = null;
        int result = -1;
        boolean newStorePassingDelay = PassingDelayCheckBox.isSelected();

        //<editor-fold desc="--check setting input errors">
        if (newStorePassingDelay) {
            if (!TextFieldNumericValueOK(StatPopSizeTextField, 
                    ((String[])Globals.DialogTitleList.get(STATISTICS_INPUT_ERROR_DIALOGTITLE.ordinal()))[ourLang])) {
                return;
            }
        }
        
        if (!TextFieldNumericValueOK(TextFieldPicWidth, 
                 ((String[])Globals.DialogTitleList.get(PHOTO_SIZE_INPUT_ERROR_DIALOGTITLE.ordinal()))[ourLang])) {
            return;
        }

        if (!TextFieldNumericValueOK(TextFieldPicHeight, 
                 ((String[])Globals.DialogTitleList.get(PHOTO_SIZE_INPUT_ERROR_DIALOGTITLE.ordinal()))[ourLang])) {
            return;
        }

        if(Integer.parseInt(TextFieldPicHeight.getText().trim().replace(",","")) < 100){
            TextFieldPicHeight.requestFocusInWindow();
            JOptionPane.showConfirmDialog(this, 
                    ((String[])Globals.DialogMSGList.get(PHOTO_SIZE_INPUT_ERROR_DIALOG.ordinal()))[ourLang],
                    ((String[])Globals.DialogTitleList.get(PHOTO_SIZE_INPUT_ERROR_DIALOGTITLE.ordinal()))[ourLang], 
                    JOptionPane.PLAIN_MESSAGE, WARNING_MESSAGE);
            return;
        }
        
        if(Integer.parseInt(TextFieldPicWidth.getText().trim().replace(",","")) < 100){
            TextFieldPicWidth.requestFocusInWindow();
            JOptionPane.showConfirmDialog(this, 
                    ((String[])Globals.DialogMSGList.get(PHOTO_SIZE_INPUT_ERROR_DIALOG.ordinal()))[ourLang],
                    ((String[])Globals.DialogTitleList.get(PHOTO_SIZE_INPUT_ERROR_DIALOGTITLE.ordinal()))[ourLang], 
                    JOptionPane.PLAIN_MESSAGE, WARNING_MESSAGE);
            return;
        }
        
        if (someIPaddressWrong()) {
            return;
        }
        //</editor-fold>

        String statCountStr = "";
        short pwLevel = -1;
        short optnLogLevel = -1;
        String maxLineStr = "";
        int imageKeepDuration = 0;
        int picWidth = Integer.parseInt(((String) TextFieldPicWidth.getText()).replace(",", ""));
        int picHeight = Integer.parseInt(((String) TextFieldPicHeight.getText()).replace(",", ""));
        int flowCycle = Integer.parseInt(((String) FlowingComboBox.getSelectedItem()).replace(",", ""));
        int blinkCycle = Integer.parseInt(((String) BlinkingComboBox.getSelectedItem()).replace(",", ""));
        try {
            StringBuffer sb = new StringBuffer("Update SettingsTable SET ");
            //<editor-fold desc="--create update statement">
            sb.append("Lot_Name = ?, ");
            sb.append("perfEvalNeeded = ?, PWStrengthLevel = ?, OptnLoggingLevel = ?, ");
            sb.append("languageCode = ?, countryCode = ?, localeIndex = ?, statCount =  ?, ");
            sb.append("MaxMessageLines = ?, GateCount = ?, ");
            sb.append("PictureWidth = ?, PictureHeight = ?, ");
            sb.append("EBD_flow_cycle = ?, EBD_blink_cycle = ?, ");
            sb.append("max_maintain_date = ? ");
            //</editor-fold>

            statCountStr = (StatPopSizeTextField.getText().trim());
            if (newStorePassingDelay) {
                for (int gateID = 1; gateID <= gateCount; gateID++) {
                    initPassingDelayStatIfNeeded(Integer.parseInt(statCountStr), gateID);
                }
            }
            conn = JDBCMySQL.getConnection();
            updateSettings = conn.prepareStatement (sb.toString());

            int pIndex = 1;

            // <editor-fold defaultstate="collapsed" desc="--Provide values to each parameters of the UPDATE statement">
            updateSettings.setString(pIndex++, lotNameTextField.getText().trim());
            if (newStorePassingDelay) {
                updateSettings.setInt(pIndex++, 1);
            } else {
                updateSettings.setInt(pIndex++, 0);
                if (DEBUG) {
                    // Give warning that in debug mode PassingDelay is always recorded.
                    JOptionPane.showMessageDialog(null, "In debug mode, Passing Delay is alway recorded");
                }
            }

            pwLevel = (short)(PWStrengthChoiceComboBox.getSelectedIndex());
            updateSettings.setShort(pIndex++, pwLevel);

            optnLogLevel = (short)(OptnLoggingLevelComboBox.getSelectedIndex());
            updateSettings.setShort(pIndex++, optnLogLevel);

            updateSettings.setString(pIndex++, DateChooserLangCBox.getLocale().getLanguage());
            updateSettings.setString(pIndex++, DateChooserLangCBox.getLocale().getCountry());
            updateSettings.setShort(pIndex++, (short)DateChooserLangCBox.getSelectedIndex());

            if (statCountStr.length() == 0) {
                // Handle the case of wrong value (zero) input.
                statCountStr = "100";
            }
            updateSettings.setInt(pIndex++, Integer.parseInt(statCountStr));
            
            maxLineStr = (String)MessageMaxLineComboBox.getSelectedItem();
            updateSettings.setShort(pIndex++, new Short(maxLineStr));
            updateSettings.setShort(pIndex++, new Short((String)GateCountComboBox.getSelectedItem()));

            updateSettings.setInt(pIndex++, picWidth);
            updateSettings.setInt(pIndex++, picHeight);
            updateSettings.setInt(pIndex++, flowCycle);
            updateSettings.setInt(pIndex++, blinkCycle);

            ConvComboBoxItem item = (ConvComboBoxItem)MaxDateComboBox.getSelectedItem();
            imageKeepDuration = (Integer)(item.getValue());
            updateSettings.setInt(pIndex++, imageKeepDuration);
            // </editor-fold>

            result = updateSettings.executeUpdate();
            if (index2Level(opLoggingIndex) != Level.OFF && index2Level(optnLogLevel) == Level.OFF) {
                Globals.isFinalWishLog = true;
            }
        } catch (SQLException se) {
            Globals.logParkingException(Level.SEVERE, se,
                    "(Save settings: " + (newStorePassingDelay ? "Y" : "N") + ")");
        } finally {
            // <editor-fold defaultstate="collapsed" desc="--Return resources and display the save result">
            closeDBstuff(conn, updateSettings, null, "(Save settings: " + (newStorePassingDelay ? "Y" : "N") + ")");

            if (result == 1) {
                //<editor-fold desc="-- Log system settings change if set to do so">
                if (statCount != Integer.parseInt(statCountStr)) 
                {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Statistics Population Size: " 
                            + statCount + " => " + Integer.parseInt(statCountStr));
                }
               
                if (storePassingDelay != newStorePassingDelay)
                {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Average Passing Delay: " 
                            + storePassingDelay + " => " + newStorePassingDelay);
                }
                
                if (pwStrengthLevel != pwLevel)
                {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Password Strength Level: " 
                            + PWStrengthChoiceComboBox.getItemAt(pwStrengthLevel) + " => " 
                            + PWStrengthChoiceComboBox.getItemAt(pwLevel));
                }
                
                if (opLoggingIndex != optnLogLevel)
                {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Gen' Operation Log Level: " 
                            + OptnLoggingLevelComboBox.getItemAt(opLoggingIndex) + " => " 
                            + OptnLoggingLevelComboBox.getItemAt(optnLogLevel));
                }
                
                
                 short newLanguage = new Short((short) DateChooserLangCBox.getSelectedIndex());
                
                boolean languageChanged = localeIndex != newLanguage;
                if (languageChanged)
                {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Date Chooser Lang': " 
                            + DateChooserLangCBox.getItemAt(localeIndex) + " => " 
                            + DateChooserLangCBox.getItemAt((short)DateChooserLangCBox.getSelectedIndex()));
                }
                
                if (maxMessageLines != new Short(maxLineStr))
                {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Recent Event Line Max: " 
                            + maxMessageLines + " => " + new Short(maxLineStr));
                }
                
                short newGateCount = new Short((String)GateCountComboBox.getSelectedItem());
                
                boolean gateCountChanged = gateCount != newGateCount;
                if (gateCountChanged)
                {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Number of Gates: " 
                            + gateCount + " => " + newGateCount);
                }
                
                if (maxMaintainDate != imageKeepDuration) {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Image Keep Duration: " 
                            + maxMaintainDate + " => " + imageKeepDuration);
                }
                
                if (PIC_WIDTH != picWidth) {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Image width: " 
                            + PIC_WIDTH + " => " + picWidth);
                }
                
                if (PIC_HEIGHT != picHeight) {
                    logParkingOperation(OpLogLevel.SettingsChange, "Settings Change, Image Height: " 
                            + PIC_HEIGHT + " => " + picHeight);
                }
                
                if (EBD_flowCycle != flowCycle)
                {
                    logParkingOperation(OpLogLevel.EBDsettingsChange, "E-Board Settings Change, Cycles--flowing: " 
                            + EBD_flowCycle + " => " + flowCycle);
                }                
                
                if (EBD_blinkCycle != blinkCycle)
                {
                    logParkingOperation(OpLogLevel.EBDsettingsChange, "E-Board Settings Change, Cycles--blinking: " 
                            + EBD_blinkCycle + " => " + blinkCycle);
                }    
                
                if (mainForm != null && (gateCountChanged || languageChanged))
                {
                    JOptionPane.showMessageDialog(mainForm, 
                            ((String[])Globals.DialogMSGList.get(CHANGE_GATE_DAILOG.ordinal()))[ourLang], 
                            ((String[])Globals.DialogTitleList.get(WARING_DIALOGTITLE.ordinal()))[ourLang], 
                            WARNING_MESSAGE, 
                            new javax.swing.ImageIcon(mainForm.getClass().getResource("/restart.png")));
                    mainForm.askUserIntentionOnProgramStop(true);
                }                
                //</editor-fold>
                
                Globals.getOperationLog().setLevel(index2Level(opLoggingIndex));
            } else {
                JOptionPane.showMessageDialog(this, 
                        ((String[])Globals.DialogMSGList.get(FAIL_SAVE_SETTINGS_DIALOG.ordinal()))[ourLang], 
                        ((String[])Globals.DialogTitleList.get(WARING_DIALOGTITLE.ordinal()))[ourLang], 
                        JOptionPane.ERROR_MESSAGE);
            }
            // </editor-fold>
        }
        
        result += saveGateDevices();
        
        if (result == gateCount + 1) {
            readSettings();
            Globals.getOperationLog().setLevel(index2Level(opLoggingIndex));
            JOptionPane.showMessageDialog(this, 
                    ((String[])Globals.DialogMSGList.get(SAVE_SETTINGS_DIALOG.ordinal()))[ourLang], 
                    ((String[])Globals.DialogTitleList.get(SAVE_DIALOGTITLE.ordinal()))[ourLang], 
                    JOptionPane.PLAIN_MESSAGE);
            changedControls.clear();
            ChangeSettings.changeEnabled_of_SaveCancelButtons(SettingsSaveButton, SettingsCancelButton, 
                    SettingsCloseButton, changedControls.size());
        } else {
            JOptionPane.showMessageDialog(this, 
                    ((String[])Globals.DialogMSGList.get(FAIL_SAVE_SETTINGS_DIALOG.ordinal()))[ourLang], 
                    ((String[])Globals.DialogTitleList.get(WARING_DIALOGTITLE.ordinal()))[ourLang], 
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SettingsSaveButtonActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        loadComponentValues();
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void SettingsCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCloseButtonActionPerformed
        tryToCloseSettingsForm();
    }//GEN-LAST:event_SettingsCloseButtonActionPerformed

    private void TextFieldPicHeightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldPicHeightKeyTyped
        rejectNonNumericKeys(evt);
    }//GEN-LAST:event_TextFieldPicHeightKeyTyped

    private void TextFieldPicWidthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldPicWidthKeyTyped
        rejectNonNumericKeys(evt);
    }//GEN-LAST:event_TextFieldPicWidthKeyTyped

    private void finishSettingsForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_finishSettingsForm
        tryToCloseSettingsForm();
    }//GEN-LAST:event_finishSettingsForm

    private void PassingDelayCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassingDelayCheckBoxActionPerformed
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), PassingDelayCheckBox.isSelected(), storePassingDelay);
    }//GEN-LAST:event_PassingDelayCheckBoxActionPerformed

    private void TextFieldGateName1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldGateName1KeyReleased
        checkGateNameChangeAndChangeEnabled(1, (Component) evt.getSource());
    }//GEN-LAST:event_TextFieldGateName1KeyReleased

    private void Camera1_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera1_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(Camera, 1, (Component) evt.getSource());        
    }//GEN-LAST:event_Camera1_IP_TextFieldKeyReleased

    private void GateBar1_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar1_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(GateBar, 1, (Component) evt.getSource());        
    }//GEN-LAST:event_GateBar1_IP_TextFieldKeyReleased

    private void E_Board1_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board1_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(E_Board, 1, (Component) evt.getSource());        
    }//GEN-LAST:event_E_Board1_IP_TextFieldKeyReleased

    private void TextFieldGateName2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldGateName2KeyReleased
        checkGateNameChangeAndChangeEnabled(2, (Component) evt.getSource());
    }//GEN-LAST:event_TextFieldGateName2KeyReleased

    private void Camera2_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera2_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(Camera, 2, (Component) evt.getSource());        
    }//GEN-LAST:event_Camera2_IP_TextFieldKeyReleased

    private void GateBar2_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar2_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(GateBar, 2, (Component) evt.getSource());        
    }//GEN-LAST:event_GateBar2_IP_TextFieldKeyReleased

    private void E_Board2_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board2_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(E_Board, 2, (Component) evt.getSource());        
    }//GEN-LAST:event_E_Board2_IP_TextFieldKeyReleased

    private void TextFieldGateName3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldGateName3KeyReleased
        checkGateNameChangeAndChangeEnabled(3, (Component) evt.getSource());        
    }//GEN-LAST:event_TextFieldGateName3KeyReleased

    private void Camera3_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera3_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(Camera, 3, (Component) evt.getSource());        
    }//GEN-LAST:event_Camera3_IP_TextFieldKeyReleased

    private void GateBar3_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar3_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(GateBar, 3, (Component) evt.getSource());        
    }//GEN-LAST:event_GateBar3_IP_TextFieldKeyReleased

    private void E_Board3_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board3_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(E_Board, 3, (Component) evt.getSource());
    }//GEN-LAST:event_E_Board3_IP_TextFieldKeyReleased

    private void TextFieldGateName4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldGateName4KeyReleased
        checkGateNameChangeAndChangeEnabled(4, (Component) evt.getSource());        
    }//GEN-LAST:event_TextFieldGateName4KeyReleased

    private void Camera4_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera4_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(Camera, 4, (Component) evt.getSource());                
    }//GEN-LAST:event_Camera4_IP_TextFieldKeyReleased

    private void GateBar4_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar4_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(GateBar, 4, (Component) evt.getSource());
    }//GEN-LAST:event_GateBar4_IP_TextFieldKeyReleased

    private void E_Board4_IP_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board4_IP_TextFieldKeyReleased
        checkDeviceIpChangeAndChangeEnabled(E_Board, 4, (Component) evt.getSource());
    }//GEN-LAST:event_E_Board4_IP_TextFieldKeyReleased

    private void MaxDateComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_MaxDateComboBoxPopupMenuWillBecomeInvisible
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), MaxDateComboBox.getSelectedIndex(), maxArrivalCBoxIndex);
    }//GEN-LAST:event_MaxDateComboBoxPopupMenuWillBecomeInvisible

    private void PWStrengthChoiceComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_PWStrengthChoiceComboBoxPopupMenuWillBecomeInvisible
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), PWStrengthChoiceComboBox.getSelectedIndex(), pwStrengthLevel);
    }//GEN-LAST:event_PWStrengthChoiceComboBoxPopupMenuWillBecomeInvisible

    private void MessageMaxLineComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_MessageMaxLineComboBoxPopupMenuWillBecomeInvisible
        short lines = (short) Integer.parseInt((String)MessageMaxLineComboBox.getSelectedItem());
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), lines, maxMessageLines);
    }//GEN-LAST:event_MessageMaxLineComboBoxPopupMenuWillBecomeInvisible

    private void OptnLoggingLevelComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_OptnLoggingLevelComboBoxPopupMenuWillBecomeInvisible
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), OptnLoggingLevelComboBox.getSelectedIndex(), opLoggingIndex);
    }//GEN-LAST:event_OptnLoggingLevelComboBoxPopupMenuWillBecomeInvisible

    private void DateChooserLangCBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_DateChooserLangCBoxPopupMenuWillBecomeInvisible
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), DateChooserLangCBox.getSelectedIndex(), localeIndex);
    }//GEN-LAST:event_DateChooserLangCBoxPopupMenuWillBecomeInvisible

    private void GateCountComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_GateCountComboBoxPopupMenuWillBecomeInvisible
        String gateCountStr = (String)GateCountComboBox.getSelectedItem();
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), Integer.parseInt(gateCountStr), gateCount);
    }//GEN-LAST:event_GateCountComboBoxPopupMenuWillBecomeInvisible

    private void BlinkingComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_BlinkingComboBoxPopupMenuWillBecomeInvisible
        String newBlinkCycleStr = ((String)BlinkingComboBox.getSelectedItem()).replace(",", "");
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), Integer.parseInt(newBlinkCycleStr), EBD_blinkCycle);
    }//GEN-LAST:event_BlinkingComboBoxPopupMenuWillBecomeInvisible

    private void FlowingComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_FlowingComboBoxPopupMenuWillBecomeInvisible
        String newFlowCycleStr =((String) FlowingComboBox.getSelectedItem()).replace(",", "");
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), Integer.parseInt(newFlowCycleStr), EBD_flowCycle);
    }//GEN-LAST:event_FlowingComboBoxPopupMenuWillBecomeInvisible

    private void StatPopSizeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatPopSizeTextFieldKeyReleased
        String newStatPopStr = StatPopSizeTextField.getText().trim();
        if(newStatPopStr.length() == 0){
            newStatPopStr = "0";
         }
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), Integer.parseInt(newStatPopStr), statCount);
    }//GEN-LAST:event_StatPopSizeTextFieldKeyReleased

    private void TextFieldPicWidthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldPicWidthKeyReleased
        String newPicWidthStr = ((String) TextFieldPicWidth.getText()).replace(",", "");
        if(newPicWidthStr.length() == 0){
            newPicWidthStr = "0";
        }
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), Integer.parseInt(newPicWidthStr), PIC_WIDTH);
    }//GEN-LAST:event_TextFieldPicWidthKeyReleased

    private void TextFieldPicHeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldPicHeightKeyReleased
        String newPicHeightStr = ((String) TextFieldPicHeight.getText()).replace(",", "");
        if(newPicHeightStr.length() == 0){
            newPicHeightStr = "0";
         }
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), Integer.parseInt(newPicHeightStr), PIC_HEIGHT);
    }//GEN-LAST:event_TextFieldPicHeightKeyReleased

    private void Camera1_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera1_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(Camera, 1, (Component) evt.getSource());        
    }//GEN-LAST:event_Camera1_Port_TextFieldKeyReleased

    private void Camera1_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera1_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_Camera1_Port_TextFieldKeyTyped

    private void GateBar1_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar1_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(GateBar, 1, (Component) evt.getSource());        
    }//GEN-LAST:event_GateBar1_Port_TextFieldKeyReleased

    private void GateBar1_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar1_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_GateBar1_Port_TextFieldKeyTyped

    private void E_Board1_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board1_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(E_Board, 1, (Component) evt.getSource());        
    }//GEN-LAST:event_E_Board1_Port_TextFieldKeyReleased

    private void E_Board1_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board1_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_E_Board1_Port_TextFieldKeyTyped

    private void Camera2_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera2_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(Camera, 2, (Component) evt.getSource());
    }//GEN-LAST:event_Camera2_Port_TextFieldKeyReleased

    private void Camera2_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera2_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_Camera2_Port_TextFieldKeyTyped

    private void GateBar2_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar2_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(GateBar, 2, (Component) evt.getSource());
    }//GEN-LAST:event_GateBar2_Port_TextFieldKeyReleased

    private void GateBar2_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar2_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_GateBar2_Port_TextFieldKeyTyped

    private void E_Board2_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board2_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(E_Board, 2, (Component) evt.getSource());        
    }//GEN-LAST:event_E_Board2_Port_TextFieldKeyReleased

    private void E_Board2_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board2_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_E_Board2_Port_TextFieldKeyTyped

    private void Camera3_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera3_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(Camera, 3, (Component) evt.getSource());        
    }//GEN-LAST:event_Camera3_Port_TextFieldKeyReleased

    private void Camera3_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera3_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_Camera3_Port_TextFieldKeyTyped

    private void GateBar3_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar3_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(GateBar, 3, (Component) evt.getSource());        
    }//GEN-LAST:event_GateBar3_Port_TextFieldKeyReleased

    private void GateBar3_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar3_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_GateBar3_Port_TextFieldKeyTyped

    private void E_Board3_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board3_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(E_Board, 3, (Component) evt.getSource());        
    }//GEN-LAST:event_E_Board3_Port_TextFieldKeyReleased

    private void E_Board3_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board3_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_E_Board3_Port_TextFieldKeyTyped

    private void Camera4_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera4_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(Camera, 4, (Component) evt.getSource());        
    }//GEN-LAST:event_Camera4_Port_TextFieldKeyReleased

    private void Camera4_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Camera4_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_Camera4_Port_TextFieldKeyTyped

    private void GateBar4_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar4_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(GateBar, 4, (Component) evt.getSource());        
    }//GEN-LAST:event_GateBar4_Port_TextFieldKeyReleased

    private void GateBar4_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GateBar4_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_GateBar4_Port_TextFieldKeyTyped

    private void E_Board4_Port_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board4_Port_TextFieldKeyReleased
        checkDevicePortChangeAndChangeButtonEnabledProperty(E_Board, 4, (Component) evt.getSource());        
        // TODO add your handling code here:
    }//GEN-LAST:event_E_Board4_Port_TextFieldKeyReleased

    private void E_Board4_Port_TextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_E_Board4_Port_TextFieldKeyTyped
        rejectNonNumericKeys(evt);        
    }//GEN-LAST:event_E_Board4_Port_TextFieldKeyTyped

    private void lotNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lotNameTextFieldKeyReleased
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, (Component) evt.getSource(), lotNameTextField.getText().trim(), parkingLotName);
        // TODO add your handling code here:
    }//GEN-LAST:event_lotNameTextFieldKeyReleased
    
    void stopOperation() {
        if(mainForm != null)
            mainForm.setConfigureSettingsForm(null);
        if (eBDsettings != null) 
            eBDsettings.dispose();
        
        if (isStand_Alone) {
            this.setVisible(false);
            System.exit(0);
        } else {
            dispose();
        }
    }    
    
    /**
     * Initiate the configuration settings form of OS.Parking Program by itself.
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
            java.util.logging.Logger.getLogger(Settings_System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings_System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings_System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings_System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        initializeLoggers();
        checkOptions(args);
        readSettings();
        DB_Access.readEBoardSettings(EBD_DisplaySettings);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Settings_System(null).setVisible(true);
            }
        });
    }

    //<editor-fold desc="-- Automatically generated form controls">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox BlinkingComboBox;
    private javax.swing.JTextField Camera1_IP_TextField;
    private javax.swing.JTextField Camera1_Port_TextField;
    private javax.swing.JTextField Camera2_IP_TextField;
    private javax.swing.JTextField Camera2_Port_TextField;
    private javax.swing.JTextField Camera3_IP_TextField;
    private javax.swing.JTextField Camera3_Port_TextField;
    private javax.swing.JTextField Camera4_IP_TextField;
    private javax.swing.JTextField Camera4_Port_TextField;
    private javax.swing.JTextField Camera4_Port_TextField1;
    private com.toedter.components.JLocaleChooser DateChooserLangCBox;
    private javax.swing.JButton EBoardSettingsButton;
    private javax.swing.JTextField E_Board1_IP_TextField;
    private javax.swing.JTextField E_Board1_Port_TextField;
    private javax.swing.JTextField E_Board2_IP_TextField;
    private javax.swing.JTextField E_Board2_Port_TextField;
    private javax.swing.JTextField E_Board3_IP_TextField;
    private javax.swing.JTextField E_Board3_Port_TextField;
    private javax.swing.JTextField E_Board4_IP_TextField;
    private javax.swing.JTextField E_Board4_Port_TextField;
    private javax.swing.JTextField E_Board4_Port_TextField1;
    private javax.swing.JComboBox FlowingComboBox;
    private javax.swing.JTextField GateBar1_IP_TextField;
    private javax.swing.JTextField GateBar1_Port_TextField;
    private javax.swing.JTextField GateBar2_IP_TextField;
    private javax.swing.JTextField GateBar2_Port_TextField;
    private javax.swing.JTextField GateBar3_IP_TextField;
    private javax.swing.JTextField GateBar3_Port_TextField;
    private javax.swing.JTextField GateBar4_IP_TextField;
    private javax.swing.JTextField GateBar4_Port_TextField;
    private javax.swing.JTextField GateBar4_Port_TextField1;
    private javax.swing.JComboBox GateCountComboBox;
    private javax.swing.JTabbedPane GatesTabbedPane;
    private javax.swing.JButton LanguageSelectionlHelpButton;
    private javax.swing.JButton LoggingLevelHelpButton;
    private javax.swing.JComboBox MaxDateComboBox;
    private javax.swing.JComboBox MessageMaxLineComboBox;
    private javax.swing.JComboBox OptnLoggingLevelComboBox;
    private javax.swing.JButton PWHelpButton;
    /*
    private javax.swing.JComboBox PWStrengthChoiceComboBox;
    */
    private javax.swing.JComboBox<ConvComboBoxItem> PWStrengthChoiceComboBox;
    private javax.swing.JCheckBox PassingDelayCheckBox;
    private javax.swing.JButton SettingsCancelButton;
    private javax.swing.JButton SettingsCloseButton;
    private javax.swing.JButton SettingsSaveButton;
    private javax.swing.JTextField StatPopSizeTextField;
    private javax.swing.JTextField TextFieldGateName1;
    private javax.swing.JTextField TextFieldGateName2;
    private javax.swing.JTextField TextFieldGateName3;
    private javax.swing.JTextField TextFieldGateName4;
    private javax.swing.JTextField TextFieldPicHeight;
    private javax.swing.JTextField TextFieldPicWidth;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel eBoardSettingPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JPanel gate1Panel;
    private javax.swing.JPanel gate2Panel;
    private javax.swing.JPanel gate3Panel;
    private javax.swing.JPanel gate4Panel;
    private javax.swing.JPanel gateSettingPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField lotNameTextField;
    private javax.swing.JPanel parkinglotOptionPanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel wholePanel;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>

    /**
     * Read settings values from the DB table and implant form component values using them.
     */
    private void loadComponentValues() {
        lotNameTextField.setText(parkingLotName);
        PassingDelayCheckBox.setSelected(storePassingDelay);
        PWStrengthChoiceComboBox.setSelectedIndex(pwStrengthLevel);
        OptnLoggingLevelComboBox.setSelectedIndex(opLoggingIndex);
        if (localeIndex - 2 >=0 )
            DateChooserLangCBox.setSelectedIndex(localeIndex - 2);
        DateChooserLangCBox.setSelectedIndex(localeIndex);

        StatPopSizeTextField.setText(Integer.toString(statCount));
        MessageMaxLineComboBox.setSelectedItem(String.valueOf(maxMessageLines));
        GateCountComboBox.setSelectedIndex(gateCount - 1);
        MaxDateComboBox.setSelectedIndex(maxArrivalCBoxIndex);

        TextFieldPicWidth.setText(String.valueOf(new DecimalFormat("#,##0").format(PIC_WIDTH)));
        TextFieldPicHeight.setText(String.valueOf(new DecimalFormat("#,##0").format(PIC_HEIGHT)));
        BlinkingComboBox.setSelectedItem(String.valueOf(new DecimalFormat("#,##0").format(EBD_blinkCycle)));
        FlowingComboBox.setSelectedItem(String.valueOf(new DecimalFormat("#,##0").format(EBD_flowCycle)));
        
        for (int i = 0; i < 4; i++) {
            GatesTabbedPane.setEnabledAt(i, false);
        }
        
        for (int i = 0; i < gateCount; i++) {
            GatesTabbedPane.setEnabledAt(i, true);

            // fill gate name textfields
            ((JTextField)getComponentByName(componentMap, "TextFieldGateName" +(i+1))).setText(gateNames[i+1]);
            
            // load 3 device IP address textfields
            ((JTextField)getComponentByName(componentMap, "Camera" +(i+1) + "_IP_TextField"))
                    .setText(deviceIP[Camera.ordinal() ][i+1]);
            ((JTextField)getComponentByName(componentMap, "Camera" +(i+1) + "_IP_TextField")).setColumns(12);
            
            ((JTextField)getComponentByName(componentMap, "GateBar" +(i+1) + "_IP_TextField"))
                    .setText(deviceIP[GateBar.ordinal() ][i+1]);
            ((JTextField)getComponentByName(componentMap, "GateBar" +(i+1) + "_IP_TextField")).setColumns(12);
            
            ((JTextField)getComponentByName(componentMap, "E_Board" +(i+1) + "_IP_TextField"))
                    .setText(deviceIP[E_Board.ordinal() ][i+1]);
            ((JTextField)getComponentByName(componentMap, "E_Board" +(i+1) + "_IP_TextField")).setColumns(12);
            
            // load 3 device port textfields
            ((JTextField)getComponentByName(componentMap, "Camera" +(i+1) + "_Port_TextField"))
                    .setText(devicePort[Camera.ordinal() ][i+1]);
            ((JTextField)getComponentByName(componentMap, "Camera" +(i+1) + "_Port_TextField")).setColumns(6);
            
            ((JTextField)getComponentByName(componentMap, "GateBar" +(i+1) + "_Port_TextField"))
                    .setText(devicePort[GateBar.ordinal() ][i+1]);
            ((JTextField)getComponentByName(componentMap, "GateBar" +(i+1) + "_Port_TextField")).setColumns(6);
            
            ((JTextField)getComponentByName(componentMap, "E_Board" +(i+1) + "_Port_TextField"))
                    .setText(devicePort[E_Board.ordinal() ][i+1]);
            ((JTextField)getComponentByName(componentMap, "E_Board" +(i+1) + "_Port_TextField")).setColumns(6);
        }
        changedControls.clear();
        ChangeSettings.changeEnabled_of_SaveCancelButtons(SettingsSaveButton, SettingsCancelButton, 
                SettingsCloseButton, changedControls.size());
    }

    /**
     * Load password complexity level selection combo box options.
     */
    private void addPWStrengthItems() {
        PWStrengthChoiceComboBox.removeAllItems();
        
        ConvComboBoxItem CBItem =  null; 

        for (PWStrengthLevel level : PWStrengthLevel.values()) {
            switch (level) {
                case FourDigit:
                    CBItem = new ConvComboBoxItem(level, ((String[])Globals.ComboBoxItemList.get(FOUR_DIGIT_CB_ITEM.ordinal()))[ourLang]);
                    break;
                case SixDigit:
                    CBItem = new ConvComboBoxItem(level, ((String[])Globals.ComboBoxItemList.get(SIX_DIGIT_CB_ITEM.ordinal()))[ourLang]);
                    break;
                case Complex:
                    CBItem = new ConvComboBoxItem(level, ((String[])Globals.ComboBoxItemList.get(COMPLEX_CB_ITEM.ordinal()))[ourLang]);
                    break;
                default:
            }
            PWStrengthChoiceComboBox.addItem((ConvComboBoxItem) CBItem);
        }
    }
    
    @SuppressWarnings("unchecked")
    /**
     * Load normal operation logging level combo box options.
     */
    private void addOperationLoggingLevelOptions() {
        OptnLoggingLevelComboBox.removeAllItems();
        
        for (OpLogLevel level : OpLogLevel.values()) {
            switch (level) {
                case LogAlways:
                    OptnLoggingLevelComboBox.addItem(((String[])Globals.ComboBoxItemList.get(NO_LOGGING_CB_ITEM.ordinal()))[ourLang]);
                    break;
                case SettingsChange:
                    OptnLoggingLevelComboBox.addItem(((String[])Globals.ComboBoxItemList.get(SETTING_CHANGE_CB_ITEM.ordinal()))[ourLang]);
                    break;
                case EBDsettingsChange:
                    OptnLoggingLevelComboBox.addItem(((String[])Globals.ComboBoxItemList.get(LOG_E_BOARD_CHANGE_CB_ITEM.ordinal()))[ourLang]);
                    break;
            }
        }        
    }

    /**
     * Checks if natural number is entered and give warning when non-numeric entered.
     * @param textField field whose text is supposed to contain a number
     * @param dialogTitle title of the dialog box that will be shown when non-natural number string is entered
     * @return <b>true</b> when a natural number is in the <code>textField</code>, 
     * <b>false</b> otherwise
     */
    private boolean TextFieldNumericValueOK(JTextField textField, String dialogTitle) {
        // Check if empty string or numeric 0 were entered. 
        String input = textField.getText().trim();
        
        input = input.replace(",", "");
        
        if (input.length() == 0 || Integer.parseInt(input) == 0) {
            JOptionPane.showConfirmDialog(this, 
                    ((String[])Globals.DialogMSGList.get(INPUT_ERROR_DAILOG.ordinal()))[ourLang],
                    dialogTitle, 
                    JOptionPane.PLAIN_MESSAGE, WARNING_MESSAGE);
            textField.requestFocusInWindow();
            textField.select(0, input.length());
            return false;
        }  else {
            return true;
        }      
    }

    private void initPassingDelayStatIfNeeded(int newStatCount, int gateID) {
        if (storePassingDelay) {
            /**
             * Passing Delay Statistics Population Size Changed while Statistics Are Gathered.
             */
            if (newStatCount !=statCount) { 
                if (passingCountCurrent[gateID] != 0) {
                    String secs = String.format("%.3f seconds", 
                            (float)passingDelayCurrentTotalMs[gateID]/passingCountCurrent[gateID]/1000f);
                    
                    String msg4GUI = getTextFor(PASSING_DELAY_AVG_MSG, secs, passingCountCurrent[gateID]);
                    if (DEBUG) {
                        logParkingOperation(OpLogLevel.LogAlways, 
                                msg4GUI + System.getProperty("line.separator"), GENERAL_DEVICE);
                    }
                    if (mainForm != null)
                        addMessageLine(mainForm.getMessageTextArea(), msg4GUI);
                    initializePassingDelayStatistics(gateID);
                }
            } 
        } else {
            initializePassingDelayStatistics(gateID);
        }
    }

    private boolean someIPaddressWrong() {
        InetAddressValidator validator = InetAddressValidator.getInstance();

        for (int i = 0; i < gateCount; i++) {
            JTextField txtField = (JTextField)getComponentByName(componentMap, "Camera" +(i+1) + "_IP_TextField");
            if (!validator.isValidInet4Address( txtField.getText())) {
                GatesTabbedPane.setSelectedIndex(i);
                txtField.requestFocusInWindow();
                JOptionPane.showConfirmDialog(this, 
                        ((String[])Globals.LabelsText.get(CAMERA_LABEL.ordinal()))[ourLang] + " #" + (i+1) 
                                + ((String[])Globals.DialogMSGList.get(IP_FORMAT_ERROR_DAILOG.ordinal()))[ourLang],
                        ((String[])Globals.DialogTitleList.get(IP_FORMAT_ERROR_DAILOGTITLE.ordinal()))[ourLang], 
                        JOptionPane.PLAIN_MESSAGE, WARNING_MESSAGE);
                return true;
            }   
            
            txtField = (JTextField)getComponentByName(componentMap, "GateBar" +(i+1) + "_IP_TextField");
            if (!validator.isValidInet4Address( txtField.getText())) {
                GatesTabbedPane.setSelectedIndex(i);
                txtField.requestFocusInWindow();
                JOptionPane.showConfirmDialog(this, 
                        ((String[])Globals.LabelsText.get(GATE_BAR_LABEL.ordinal()))[ourLang] + " #" + (i+1) 
                                + ((String[])Globals.DialogMSGList.get(IP_FORMAT_ERROR_DAILOG.ordinal()))[ourLang],
                        ((String[])Globals.DialogTitleList.get(IP_FORMAT_ERROR_DAILOGTITLE.ordinal()))[ourLang], 
                        JOptionPane.PLAIN_MESSAGE, WARNING_MESSAGE);
                return true;
            }    
            
            txtField = (JTextField)getComponentByName(componentMap, "E_Board" +(i+1) + "_IP_TextField");
            if (!validator.isValidInet4Address( txtField.getText())) {
                GatesTabbedPane.setSelectedIndex(i);
                txtField.requestFocusInWindow();
                JOptionPane.showConfirmDialog(this, 
                        ((String[])Globals.LabelsText.get(EBOARD_LABEL.ordinal()))[ourLang] + " #" + (i+1) 
                                + ((String[])Globals.DialogMSGList.get(IP_FORMAT_ERROR_DAILOG.ordinal()))[ourLang],
                        ((String[])Globals.DialogTitleList.get(IP_FORMAT_ERROR_DAILOGTITLE.ordinal()))[ourLang], 
                        JOptionPane.PLAIN_MESSAGE, WARNING_MESSAGE);
                
                return true;
            }            
        }        
        
        return false;
    }

    @SuppressWarnings("unchecked")
    private void addMaxArrivalItems() {
        MaxDateComboBox.removeAllItems();
        
        MaxDateComboBox.addItem(new ConvComboBoxItem(new Integer(1), "1 " + ((String[])Globals.ComboBoxItemList.get(DAY_CB_ITEM.ordinal()))[ourLang]));
        MaxDateComboBox.addItem(new ConvComboBoxItem(new Integer(7), "7 " + ((String[])Globals.ComboBoxItemList.get(DAYS_CB_ITEM.ordinal()))[ourLang]));
        MaxDateComboBox.addItem(new ConvComboBoxItem(new Integer(30), "30 " + ((String[])Globals.ComboBoxItemList.get(DAYS_CB_ITEM.ordinal()))[ourLang]));
        MaxDateComboBox.addItem(new ConvComboBoxItem(new Integer(60), "60 " + ((String[])Globals.ComboBoxItemList.get(DAYS_CB_ITEM.ordinal()))[ourLang]));
        MaxDateComboBox.addItem(new ConvComboBoxItem(new Integer(90), "90 " + ((String[])Globals.ComboBoxItemList.get(DAYS_CB_ITEM.ordinal()))[ourLang]));
        MaxDateComboBox.addItem(new ConvComboBoxItem(new Integer(120), "120 " + ((String[])Globals.ComboBoxItemList.get(DAYS_CB_ITEM.ordinal()))[ourLang]));
    }

    private short findCBoxIndex(JComboBox maxArrivalComboBox, int maxArrival) {
        short index = -1;

        Object item;
        for (short idx = 0; idx < maxArrivalComboBox.getItemCount(); idx++) {
            item = maxArrivalComboBox.getItemAt(idx);
            if (item.getClass() == ConvComboBoxItem.class 
                    && (Integer)((ConvComboBoxItem)item).getValue() == maxArrival) {
                index = idx;
                break;
            }
        }
        return index;
    }        

    /**
     * @param eBDsettings the eBDsettings to set
     */
    public void setEBDsettings(Settings_EBoard eBDsettings) {
        this.eBDsettings = eBDsettings;
    }

    private int saveGateDevices() {
        Connection conn = null;
        PreparedStatement updateSettings = null;
        int result = 0;
        int updateRowCount = 0;
        
        //<editor-fold desc="-- Create update statement">
        StringBuffer sb = new StringBuffer("Update gatedevices SET ");
        sb.append("  gatename = ?");
        sb.append("  , cameraIP = ?");
        sb.append("  , e_boardIP = ?");
        sb.append("  , gatebarIP = ? ");
        sb.append("  , cameraPort = ?");
        sb.append("  , e_boardPort = ?");
        sb.append("  , gatebarPort = ? ");
        sb.append("WHERE GateID = ?");
        //</editor-fold>

        for (int gateID = 1; gateID <= gateCount; gateID++) {
            try 
            {
                conn = JDBCMySQL.getConnection();
                updateSettings = conn.prepareStatement (sb.toString());

                int pIndex = 1;

                //<editor-fold defaultstate="collapsed" desc="--Provide actual values to the UPDATE">
                updateSettings.setString(pIndex++, 
                        ((JTextField) componentMap.get("TextFieldGateName" + gateID)).getText().trim());
                updateSettings.setString(pIndex++, 
                        ((JTextField) componentMap.get("Camera" + gateID + "_IP_TextField"))
                                .getText().trim());
                updateSettings.setString(pIndex++, 
                        ((JTextField) componentMap.get("E_Board" + gateID + "_IP_TextField"))
                                .getText().trim());
                updateSettings.setString(pIndex++, 
                        ((JTextField) componentMap.get("GateBar" + gateID + "_IP_TextField"))
                                .getText().trim());
                updateSettings.setString(pIndex++, 
                        ((JTextField) componentMap.get("Camera" + gateID + "_Port_TextField"))
                                .getText().trim());
                updateSettings.setString(pIndex++, 
                        ((JTextField) componentMap.get("E_Board" + gateID + "_Port_TextField"))
                                .getText().trim());
                updateSettings.setString(pIndex++, 
                        ((JTextField) componentMap.get("GateBar" + gateID + "_Port_TextField"))
                                .getText().trim());
                updateSettings.setInt(pIndex++, gateID);
                // </editor-fold>

                result = updateSettings.executeUpdate();
                
            } catch (SQLException se) {
                Globals.logParkingException(Level.SEVERE, se, "(Save gate & device settings)");
            } finally {
                // <editor-fold defaultstate="collapsed" desc="--Return resources and display the save result">
                closeDBstuff(conn, updateSettings, null, "(Save gate & device settings)");

                if (result == 1) {
                    updateRowCount++;
                }
                // </editor-fold>
            }  
        }
        return updateRowCount;
    }

    private void initializePassingDelayStatistics(int gateID) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pStmt = null;    
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE gatedevices ");
        sql.append("SET passingCountCurrent = 0, ");
        sql.append("  passingDelayCurrentTotalMs = 0 ");
        sql.append("WHERE gateid = ?");
        
        try 
        {
            conn = JDBCMySQL.getConnection();
            pStmt = conn.prepareStatement(sql.toString());
            
            int loc = 1;
            pStmt.setInt(loc++, gateID);
            result = pStmt.executeUpdate();
        } catch(Exception e) {
            logParkingException(Level.SEVERE, e, "(init passing delay statistics)");
        } finally {
            if (result != 1)
                logParkingException(Level.SEVERE, null, "(failed initialization of passing delay statistics)");
                
            closeDBstuff(conn, pStmt, null, "(init passing delay statistics)");
        }  
    }
    
    private void changeEnabled_of_SaveCancelButtons(boolean onOff) {
        SettingsSaveButton.setEnabled(onOff);
        SettingsCancelButton.setEnabled(onOff);        
        SettingsCloseButton.setEnabled(!onOff);
    } 
    
    private void tryToCloseSettingsForm() {
        if (SettingsSaveButton.isEnabled()) {
            JOptionPane.showMessageDialog(this, 
                    ((String[])Globals.DialogMSGList.get(SAVE_OR_CANEL_DIALOG.ordinal()))[ourLang],
                    ((String[])Globals.DialogTitleList.get(CONFIRM_DIALOGTITLE.ordinal()))[ourLang], 
                    JOptionPane.WARNING_MESSAGE);
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        } else {
            stopOperation();
        }     
    }    

    private void checkGateNameChangeAndChangeEnabled(int gateNo, Component comp) {
        JTextField gateNameField = (JTextField)componentMap.get("TextFieldGateName" + gateNo);
        
        final String controlName = gateNameField.getName();
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, comp, gateNameField.getText().trim(), gateNames[gateNo]);
    }    
    
    private void checkDeviceIpChangeAndChangeEnabled(DeviceType device, int gateNo, Component comp) {
        JTextField deviceIpField = (JTextField)componentMap.get(device.toString() + gateNo + "_IP_TextField");
        
        final String controlName = deviceIpField.getName();
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, comp, deviceIpField.getText().trim(), deviceIP[device.ordinal()][gateNo]);
    }    
    
    private void checkDevicePortChangeAndChangeButtonEnabledProperty(DeviceType device, int gateNo, Component comp) {
        JTextField devicePortField = (JTextField)componentMap.get(device.toString() + gateNo + "_Port_TextField");
        
        final String controlName = devicePortField.getName();
        ChangeSettings.changeStatus_Manager(SettingsSaveButton, SettingsCancelButton, SettingsCloseButton, 
                changedControls, comp, devicePortField.getText().trim(), devicePort[device.ordinal()][gateNo]);
    }    
    
    private void makeEnterActAsTab() {
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        KeyStroke ctrlTab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, KeyEvent.CTRL_DOWN_MASK);
        HashSet<KeyStroke> keys = new HashSet<>();
        keys.add(enter);
        keys.add(tab);
        keys.add(ctrlTab);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().
                setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keys);    
    }
    
    private void rejectNonNumericKeys(KeyEvent evt) {
        char c = evt.getKeyChar();
        if ( !(
                (c >= '0') && (c <= '9') ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE) ||
                (c == KeyEvent.VK_ENTER)
                ))
        {
            getToolkit().beep();
            evt.consume();
        }    
    }    
    
    
    private static String getTextFor(TextType textType, String str, int integer){
        String text = null;
        
        switch(textType){
            case PASSING_DELAY_AVG_MSG:
                if(ourLang == KOREAN.ordinal()){
                    text = "최근 " + integer + "대 차량의 통과 시간 평균: " + str;
                }
                else{
                    text = "Recent " + integer + " cars passing delay average is: " + str;
                }
                break;
            default :
                break;
        }
        
        return text;
    }
}
