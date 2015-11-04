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
package com.osparking.osparking;

import static com.osparking.global.Globals.OSPiconList;
import static com.osparking.global.Globals.SetAColumnWidth;
import static com.osparking.global.Globals.buildTableModel;
import static com.osparking.global.Globals.checkOptions;
import static com.osparking.global.Globals.closeDBstuff;
import static com.osparking.global.Globals.font_Size;
import static com.osparking.global.Globals.font_Style;
import static com.osparking.global.Globals.font_Type;
import static com.osparking.global.Globals.getDateFromGivenDate;
import static com.osparking.global.Globals.initializeLoggers;
import static com.osparking.global.Globals.logParkingException;
import static com.osparking.global.names.DB_Access.parkingLotLocale;
import static com.osparking.global.names.DB_Access.readSettings;
import com.osparking.global.names.JDBCMySQL;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author YongSeok
 */
public class RunRecordList extends javax.swing.JFrame {

    /**
     * Creates new form RunRecordList2
     */
    public RunRecordList() {
        initComponents();
        
        setIconImages(OSPiconList);
        
        BeginDateChooser.setLocale(parkingLotLocale);
        EndDateChooser.setLocale(parkingLotLocale);
        Date today = new Date();
        BeginDateChooser.setDate(getDateFromGivenDate(today, -7));
        EndDateChooser.setDate(today);
        
        RunRecordTable.setAutoCreateRowSorter(true);
        RefreshTableContents(BeginDateChooser.getDate(), EndDateChooser.getDate());  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0));
        wholePanel = new javax.swing.JPanel();
        closePanel = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        CloseFormButton = new javax.swing.JButton();
        LoginRecordListTopPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        datePanel = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0));
        BeginDateChooser = new com.toedter.calendar.JDateChooser();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0));
        jLabel2 = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0));
        EndDateChooser = new com.toedter.calendar.JDateChooser();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0));
        SearchLoginRecordButton = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jScrollPane1 = new javax.swing.JScrollPane();
        RunRecordTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("OSParking Program Run Record");
        getContentPane().add(filler9, java.awt.BorderLayout.SOUTH);
        getContentPane().add(filler8, java.awt.BorderLayout.PAGE_START);
        getContentPane().add(filler11, java.awt.BorderLayout.LINE_START);
        getContentPane().add(filler10, java.awt.BorderLayout.LINE_END);

        wholePanel.setLayout(new javax.swing.BoxLayout(wholePanel, javax.swing.BoxLayout.PAGE_AXIS));

        closePanel.setLayout(new javax.swing.BoxLayout(closePanel, javax.swing.BoxLayout.LINE_AXIS));
        closePanel.add(filler1);

        CloseFormButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        CloseFormButton.setMnemonic('c');
        CloseFormButton.setText("Close");
        CloseFormButton.setMaximumSize(new java.awt.Dimension(90, 40));
        CloseFormButton.setMinimumSize(new java.awt.Dimension(90, 40));
        CloseFormButton.setPreferredSize(new java.awt.Dimension(90, 40));
        CloseFormButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseFormButtonActionPerformed(evt);
            }
        });
        closePanel.add(CloseFormButton);

        wholePanel.add(closePanel);

        LoginRecordListTopPanel.setLayout(new javax.swing.BoxLayout(LoginRecordListTopPanel, javax.swing.BoxLayout.Y_AXIS));

        titlePanel.setLayout(new javax.swing.BoxLayout(titlePanel, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("OSParking Program Run Record");
        jLabel3.setFocusable(false);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setMaximumSize(new java.awt.Dimension(32767, 28));
        titlePanel.add(jLabel3);

        LoginRecordListTopPanel.add(titlePanel);

        datePanel.setLayout(new javax.swing.BoxLayout(datePanel, javax.swing.BoxLayout.LINE_AXIS));
        datePanel.add(filler3);

        jLabel1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel1.setText("Search Period");
        datePanel.add(jLabel1);
        datePanel.add(filler4);

        BeginDateChooser.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        BeginDateChooser.setMaximumSize(new java.awt.Dimension(32767, 28));
        datePanel.add(BeginDateChooser);
        datePanel.add(filler5);

        jLabel2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("~");
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        datePanel.add(jLabel2);
        datePanel.add(filler6);

        EndDateChooser.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        EndDateChooser.setMaximumSize(new java.awt.Dimension(32767, 28));
        datePanel.add(EndDateChooser);
        datePanel.add(filler7);

        SearchLoginRecordButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        SearchLoginRecordButton.setMnemonic('s');
        SearchLoginRecordButton.setText("Search");
        SearchLoginRecordButton.setMaximumSize(new java.awt.Dimension(90, 40));
        SearchLoginRecordButton.setMinimumSize(new java.awt.Dimension(90, 40));
        SearchLoginRecordButton.setPreferredSize(new java.awt.Dimension(90, 40));
        SearchLoginRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchLoginRecordButtonActionPerformed(evt);
            }
        });
        datePanel.add(SearchLoginRecordButton);
        datePanel.add(filler2);

        LoginRecordListTopPanel.add(datePanel);

        wholePanel.add(LoginRecordListTopPanel);

        RunRecordTable.setAutoCreateRowSorter(true);
        RunRecordTable.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        RunRecordTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        RunRecordTable.setRowHeight(28);
        RunRecordTable.getTableHeader().setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        jScrollPane1.setViewportView(RunRecordTable);

        wholePanel.add(jScrollPane1);

        getContentPane().add(wholePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseFormButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseFormButtonActionPerformed
        dispose();
    }//GEN-LAST:event_CloseFormButtonActionPerformed

    private void SearchLoginRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchLoginRecordButtonActionPerformed
        Date beginDate = BeginDateChooser.getDate();
        Date endDate = EndDateChooser.getDate();
        
        // Check if both starting and ending dates are entered
        if (beginDate == null || endDate == null) {
            JOptionPane.showConfirmDialog(this, "Please, enter both starting and ending dates!",
                    "Search Range Error", JOptionPane.PLAIN_MESSAGE, WARNING_MESSAGE);             
        } else {
            // Check if dates are chronologically wrong.
            if (beginDate.after(endDate)) {
                JOptionPane.showConfirmDialog(this, "Ending date can't precede starting date!" + 
                        System.lineSeparator() +
                        "Please, change search range(dates).", "Wrong Search Range", 
                        JOptionPane.PLAIN_MESSAGE, WARNING_MESSAGE);             
            } else {
                RefreshTableContents(beginDate, endDate);
            }
        }
    }//GEN-LAST:event_SearchLoginRecordButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RunRecordList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RunRecordList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RunRecordList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RunRecordList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new RunRecordList().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser BeginDateChooser;
    private javax.swing.JButton CloseFormButton;
    private com.toedter.calendar.JDateChooser EndDateChooser;
    private javax.swing.JPanel LoginRecordListTopPanel;
    private javax.swing.JTable RunRecordTable;
    private javax.swing.JButton SearchLoginRecordButton;
    private javax.swing.JPanel closePanel;
    private javax.swing.JPanel datePanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel wholePanel;
    // End of variables declaration//GEN-END:variables
    private void RefreshTableContents(Date beginDate, Date endDate) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = dateFormat.format(beginDate);
     
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null; 
        StringBuffer sb = new StringBuffer();
        sb.append("Select recNo as 'Order', ");
        
        sb.append(" concat(date_format(stopTm, '%Y-%m-%d '), ");
        sb.append(" if(date_format(stopTm, '%p') ='AM', 'AM', 'PM'),");
        sb.append(" date_format(stopTm, ' %h:%i:%s')) as 'Shutdown', ");
        sb.append(" concat(date_format(startTm, '%Y-%m-%d '), ");
        sb.append(" if(date_format(startTm, '%p') ='AM', 'AM', 'PM'),");
        sb.append(" date_format(startTm, ' %h:%i:%s')) as 'Start Up', ");
        
        sb.append(" concat( ");
        sb.append("   lpad(timestampdiff(HOUR, stopTm, startTm),");
        sb.append(    " if (timestampdiff(HOUR, stopTm, startTm) > 9999, 5,");
        sb.append(    " if (timestampdiff(HOUR, stopTm, startTm) > 999, 4,");
        sb.append(    " if (timestampdiff(HOUR, stopTm, startTm) > 99, 3, 2))), '0'), ':',");        
        sb.append("   lpad(mod(timestampdiff(MINUTE, stopTm, startTm), 60), 2, '0'), '.', ");
        sb.append("   lpad(mod(timestampdiff(SECOND, stopTm, startTm), 60), 2, '0')) as 'Stopped For(hh:mm:ss)' ");
        sb.append("FROM SystemRun Where ('");
        sb.append(startDate);
        sb.append("' <= date(stopTm) or date(stopTm) is null) and ('");
        sb.append(startDate);
        sb.append("' <= date(startTm) or date(startTm) is null)");
        sb.append(" order by recNo desc");
        
        try {
            conn = JDBCMySQL.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sb.toString());
            RunRecordTable.setModel(buildTableModel(rs)); // DbUtils.resultSetToTableModel(rs));
            int dimX = RunRecordTable.getPreferredSize().width;
            int rowHeight = RunRecordTable.getRowHeight();
            RunRecordTable.setPreferredSize(new Dimension(dimX, 
                    rowHeight * RunRecordTable.getRowCount()));
            SetTableColumnWidth();           
        } catch (Exception ex) {
            logParkingException(Level.SEVERE, ex, "(System Operation Record List: Content Refresh Module)");
        } finally {
            closeDBstuff(conn, stmt, rs, "(System Operation Record List: Content Refresh Module)");
        }
        
        /**
         * Sets a correct <code>comparator</code> method for the first <code>id</code>column
         * Without it, the <code>id</code>(an integral type identifier) field of the base table
         * (<code>RunRecordTable</code>) is sorted as strings where a row with '2' 
         * being greater (appears later in the ascending order sorting) than a row with '10'.
         */
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(RunRecordTable.getModel());
        sorter.setComparator(0, com.osparking.global.Globals.comparator);
        RunRecordTable.setRowSorter(sorter);          
    }

    
     private void SetTableColumnWidth() {
        TableColumnModel tcm = RunRecordTable.getColumnModel();
        
        ((DefaultTableCellRenderer)RunRecordTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);        
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tcm.getColumn(0).setCellRenderer(rightRenderer);  // order : right alignment 
 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tcm.getColumn(1).setCellRenderer(centerRenderer);  // dates
        tcm.getColumn(2).setCellRenderer(centerRenderer);  
        tcm.getColumn(3).setCellRenderer(centerRenderer);  
        
        // Adjust column width one by one
        SetAColumnWidth(tcm.getColumn(0), 90, 90, 90); // line number
//        SetAColumnWidth(tcm.getColumn(3), 120, 120, 120); // line number
    }    
}
