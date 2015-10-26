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

import com.osparking.global.names.JDBCMySQL;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static com.osparking.global.names.DB_Access.parkingLotLocale;
import static com.osparking.global.names.DB_Access.readSettings;
import static com.osparking.global.Globals.*;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author Park, Jongbum <Park, Jongbum at Open Source Parking Inc.>
 */
public class RunRecordList extends javax.swing.JFrame {
    private static Logger logException = null;
    String searchCondition = "";
    
    /**
     * Creates new form RunRecordList
     */
    public RunRecordList() {
        initComponents();
        getContentPane().setBackground(PopUpBackground);
        BeginDateChooser.setLocale(parkingLotLocale);
        EndDateChooser.setLocale(parkingLotLocale);
        Date today = new Date();
        BeginDateChooser.setDate(getDateFromGivenDate(today, -7));
        EndDateChooser.setDate(today);
        
        RunRecordTable.setAutoCreateRowSorter(true);
        RefreshTableContents(BeginDateChooser.getDate(), EndDateChooser.getDate());        
    }
    
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        RunRecordTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        HideFormButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        searchBootRecordButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BeginDateChooser = new JDateChooser();
        EndDateChooser = new JDateChooser();

        RunRecordTable.setFont(new java.awt.Font("Dotum", 0, 14)); // NOI18N
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
        RunRecordTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        RunRecordTable.setRowHeight(28);
        jScrollPane1.setViewportView(RunRecordTable);

        jLabel3.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("OSParking Program Run Record");
        jLabel3.setFocusable(false);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        HideFormButton.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        HideFormButton.setText("Close");
        HideFormButton.setToolTipText("");
        HideFormButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HideFormButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("~");
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        searchBootRecordButton.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        searchBootRecordButton.setText("Search");
        searchBootRecordButton.setToolTipText("");
        searchBootRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBootRecordButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dotum", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Search Period");
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(HideFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BeginDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBootRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HideFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))
                        .addComponent(EndDateChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchBootRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BeginDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addGap(66, 66, 66))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBootRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBootRecordButtonActionPerformed

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
    }//GEN-LAST:event_searchBootRecordButtonActionPerformed

    private void HideFormButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HideFormButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_HideFormButtonActionPerformed

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
    private JDateChooser BeginDateChooser;
    private JDateChooser EndDateChooser;
    private javax.swing.JButton HideFormButton;
    private javax.swing.JTable RunRecordTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchBootRecordButton;
    // End of variables declaration//GEN-END:variables

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
