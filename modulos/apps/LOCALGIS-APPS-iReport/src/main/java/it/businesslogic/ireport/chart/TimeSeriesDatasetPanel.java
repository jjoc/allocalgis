/*
 * Copyright (C) 2005 - 2007 JasperSoft Corporation.  All rights reserved. 
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from JasperSoft,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 *
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  USA  02111-1307
 *
 *
 *
 *
 * TimeSeriesDatasetPanel.java
 * 
 * Created on 15 agosto 2005, 17.55
 *
 */

package it.businesslogic.ireport.chart;
import it.businesslogic.ireport.SubDataset;
import it.businesslogic.ireport.gui.sheet.Tag;
import it.businesslogic.ireport.util.I18n;
import it.businesslogic.ireport.util.Misc;

import java.awt.event.ActionEvent;
import java.util.Vector;
/**
 *
 * @author  Administrator
 */
public class TimeSeriesDatasetPanel extends javax.swing.JPanel implements ChartDatasetPanel {
    
    private TimeSeriesDataset timeSeriesDataset = null;
    private boolean init = false;
    private SubDataset subDataset = null;
    
    /** Creates new form PieDatasetPanel */
    public TimeSeriesDatasetPanel() {
        initComponents();
        applyI18n();
        
        // Year | Quarter | Month | Week | Day | Hour | Minute | Second | Milisecond
        this.jComboBoxPeriod.addItem(new Tag("Year",it.businesslogic.ireport.util.I18n.getString("charts.timePeriod.Year","Year")));
        this.jComboBoxPeriod.addItem(new Tag("Quarter",it.businesslogic.ireport.util.I18n.getString("charts.timePeriod.Quarter","Quarter")));
        this.jComboBoxPeriod.addItem(new Tag("Month",it.businesslogic.ireport.util.I18n.getString("charts.timePeriod.Month","Month")));
        this.jComboBoxPeriod.addItem(new Tag("Week",it.businesslogic.ireport.util.I18n.getString("charts.timePeriod.Week","Week")));
        this.jComboBoxPeriod.addItem(new Tag("Day",it.businesslogic.ireport.util.I18n.getString("charts.timePeriod.Day","Day")));
        this.jComboBoxPeriod.addItem(new Tag("Hour",it.businesslogic.ireport.util.I18n.getString("charts.timePeriod.Hour","Hour")));
        this.jComboBoxPeriod.addItem(new Tag("Minute",it.businesslogic.ireport.util.I18n.getString("charts.timePeriod.Minute","Minute")));
        this.jComboBoxPeriod.addItem(new Tag("Second",it.businesslogic.ireport.util.I18n.getString("charts.timePeriod.Second","Second")));
        this.jComboBoxPeriod.addItem(new Tag("Milisecond",it.businesslogic.ireport.util.I18n.getString("charts.timePeriod.Milisecond","Milisecond")));
        
        jList1.setModel( new javax.swing.DefaultListModel());
    }

    public void setTimeSeriesDataset(TimeSeriesDataset timeSeriesDataset) {
        
        init = true;
        this.timeSeriesDataset = timeSeriesDataset;
        
        jButtonModify.setEnabled( false );
        jButtonModify.setEnabled( false );
        javax.swing.DefaultListModel lm = (javax.swing.DefaultListModel)jList1.getModel();
        
        lm.removeAllElements();
        
        Vector v = timeSeriesDataset.getTimeSeries();
                        
        for (int i=0; i< v.size(); ++i)
        {
            lm.addElement(v.elementAt(i) );
        }
        
        Misc.setComboboxSelectedTagValue(jComboBoxPeriod, timeSeriesDataset.getTimePeriod() );
        init = false;
    }

    public TimeSeriesDataset getTimeSeriesDataset() {
        return timeSeriesDataset;
    }
  
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPopupMenuSeries = new javax.swing.JPopupMenu();
        jMenuItemCopy = new javax.swing.JMenuItem();
        jMenuItemPaste = new javax.swing.JMenuItem();
        jPanelPeriod = new javax.swing.JPanel();
        jLabelPeriod = new javax.swing.JLabel();
        jComboBoxPeriod = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonModify = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        jMenuItemCopy.setText("Copy series");
        jMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopyActionPerformed(evt);
            }
        });

        jPopupMenuSeries.add(jMenuItemCopy);

        jMenuItemPaste.setText("Paste series");
        jMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPasteActionPerformed(evt);
            }
        });

        jPopupMenuSeries.add(jMenuItemPaste);

        setLayout(new java.awt.GridBagLayout());

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanelPeriod.setLayout(new java.awt.GridBagLayout());

        jLabelPeriod.setText("Time period");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanelPeriod.add(jLabelPeriod, gridBagConstraints);

        jComboBoxPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPeriodActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 4);
        jPanelPeriod.add(jComboBoxPeriod, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(jPanelPeriod, gridBagConstraints);

        jLabel1.setText("Time series");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        add(jLabel1, gridBagConstraints);

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(jScrollPane1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(100, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 0));
        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        jPanel1.add(jButtonAdd, gridBagConstraints);

        jButtonModify.setText("Modify");
        jButtonModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 4);
        jPanel1.add(jButtonModify, gridBagConstraints);

        jButtonRemove.setText("Remove");
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 4);
        jPanel1.add(jButtonRemove, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 99;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        openExtraWindows();
    }//GEN-LAST:event_formComponentShown

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        if (evt.getClickCount() == 1 && evt.getButton() == evt.BUTTON3)
        {
            jMenuItemCopy.setEnabled(jList1.getSelectedIndex() >= 0);
            jMenuItemPaste.setEnabled( it.businesslogic.ireport.gui.MainFrame.getMainInstance().getChartSeriesClipBoard() != null &&
                                       it.businesslogic.ireport.gui.MainFrame.getMainInstance().getChartSeriesClipBoard().size() > 0);
            
            jPopupMenuSeries.show(jList1, evt.getPoint().x, evt.getPoint().y);
        }
        else if (evt.getClickCount() == 2 && evt.getButton() == evt.BUTTON1)
        {
            jButtonModifyActionPerformed(null);
        } 
    }//GEN-LAST:event_jList1MouseClicked

    private void jMenuItemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPasteActionPerformed
        
        Vector v = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getChartSeriesClipBoard();
        
        if (v != null && v.size() > 0)
        {
            for (int i=0; i<v.size(); ++i)
            {
                if (v.elementAt(i) instanceof TimeSeries)
                {
                    TimeSeries cs = (TimeSeries)v.elementAt(i);
                    cs = cs.cloneMe();
                    getTimeSeriesDataset().getTimeSeries().addElement(cs);
                    ((javax.swing.DefaultListModel)jList1.getModel()).addElement(cs);
                }
            }
            jList1.updateUI();
        }
    }//GEN-LAST:event_jMenuItemPasteActionPerformed

    private void jMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopyActionPerformed
        Object[] values = jList1.getSelectedValues();
        Vector copy_c = new Vector();
        for (int i=0; i<values.length; ++i) copy_c.add( ((TimeSeries)values[i]).cloneMe() );
        it.businesslogic.ireport.gui.MainFrame.getMainInstance().setChartSeriesClipBoard(copy_c);
    }//GEN-LAST:event_jMenuItemCopyActionPerformed

    private void jComboBoxPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPeriodActionPerformed

        if (init || timeSeriesDataset == null) return;
        timeSeriesDataset.setTimePeriod(  ((Tag)jComboBoxPeriod.getSelectedItem()).getValue()+"");
        
    }//GEN-LAST:event_jComboBoxPeriodActionPerformed

    private void jButtonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyActionPerformed
        
        if (jList1.getSelectedIndex() >= 0)
        {
            TimeSeries cs = (TimeSeries)jList1.getSelectedValue();
            TimeSeriesDialog csd = new TimeSeriesDialog(it.businesslogic.ireport.gui.MainFrame.getMainInstance() ,true);
            
            csd.setSeriesExpression( cs.getSeriesExpression() );
            csd.setTimePeriodExpression( cs.getTimePeriodExpression() );
            csd.setValueExpression( cs.getValueExpression() );
            csd.setLabelExpression( cs.getLabelExpression() );
            csd.setSectionItemHyperlink( cs.getSectionItemHyperlink() );
            
            csd.setSubDataset( this.getSubDataset() );
            
            if (newInfo != null)
            {
                csd.setFocusedExpression(newInfo);
            }
            
            csd.setVisible(true);
            
            if (csd.getDialogResult() == javax.swing.JOptionPane.OK_OPTION)
            {
                cs.setSeriesExpression( csd.getSeriesExpression() );
                cs.setTimePeriodExpression( csd.getTimePeriodExpression() );
                cs.setValueExpression( csd.getValueExpression() );
                cs.setLabelExpression( csd.getLabelExpression() );
                cs.setSectionItemHyperlink( csd.getSectionItemHyperlink() );

                jList1.updateUI();
            }
        
        }
    }//GEN-LAST:event_jButtonModifyActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        TimeSeriesDialog csd = new TimeSeriesDialog(it.businesslogic.ireport.gui.MainFrame.getMainInstance() ,true);
        csd.setSubDataset( this.getSubDataset() );
        csd.setVisible(true);
        if (csd.getDialogResult() == javax.swing.JOptionPane.OK_OPTION)
        {
            TimeSeries cs = new TimeSeries();
            cs.setSeriesExpression( csd.getSeriesExpression() );
            cs.setTimePeriodExpression( csd.getTimePeriodExpression() );
            cs.setValueExpression( csd.getValueExpression() );
            cs.setLabelExpression( csd.getLabelExpression() );
            cs.setSectionItemHyperlink( csd.getSectionItemHyperlink() );
            
            getTimeSeriesDataset().getTimeSeries().addElement(cs);
            ((javax.swing.DefaultListModel)jList1.getModel()).addElement(cs);
        }
        
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

        while (jList1.getSelectedIndex() >= 0)
        {
            getTimeSeriesDataset().getTimeSeries().remove( jList1.getSelectedValue() );
            ((javax.swing.DefaultListModel)jList1.getModel()).removeElementAt(jList1.getSelectedIndex());
        }
        
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged

        if (jList1.getSelectedIndex() >= 0)
        {
            jButtonModify.setEnabled( true );
            jButtonModify.setEnabled( true );
        }
        else
        {
            jButtonModify.setEnabled( false );
            jButtonModify.setEnabled( false );
        }
    }//GEN-LAST:event_jList1ValueChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonModify;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JComboBox jComboBoxPeriod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelPeriod;
    private javax.swing.JList jList1;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemPaste;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelPeriod;
    private javax.swing.JPopupMenu jPopupMenuSeries;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
     public void applyI18n()
    {
                // Start autogenerated code ----------------------
                // End autogenerated code ----------------------
                // Start autogenerated code ----------------------
                jLabel1.setText(I18n.getString("timeSeriesDatasetPanel.label1","Time series"));
                // End autogenerated code ----------------------
        jButtonAdd.setText(it.businesslogic.ireport.util.I18n.getString("charts.newseries", "Add series"));
        jButtonModify.setText(it.businesslogic.ireport.util.I18n.getString("charts.modifyseries", "Modify series"));
        jButtonRemove.setText(it.businesslogic.ireport.util.I18n.getString("charts.removeseries", "Remove series"));
        jLabelPeriod.setText(it.businesslogic.ireport.util.I18n.getString("charts.timePeriod", "Time period"));
        
        jMenuItemCopy.setText(it.businesslogic.ireport.util.I18n.getString("charts.copyseries", "Copy series"));
        jMenuItemPaste.setText(it.businesslogic.ireport.util.I18n.getString("charts.pasteseries", "Paste series"));
        
        this.updateUI();
        
    }

    public SubDataset getSubDataset() {
        return subDataset;
    }

    public void setSubDataset(SubDataset subDataset) {
        this.subDataset = subDataset;
    }

    public static final int COMPONENT_NONE=0;
    public static final int COMPONENT_TIME_SERIES_LIST=1;
    
    /**
     * This variable is checked by openExtraWindows() called when the component is shown.
     * If the value is != 0, the modify button will be action-performed.
     */
    public Object[]  newInfo = null;
    /**
     * This method set the focus on a specific component.
     * 
     * For this kind of datasource otherInfo must be:
     * [0] = Fixed to COMPONENT_PERIOD_SERIES_LIST (used for future extensions)
     * [1] = Integer, the category series to edit
     * [2] = The expression id in the category window to focus on 
     * [3] = The expression in the hyperlink...
     * [4] = The hyperlink parameter
     * [5] = The expression of the hyperlink parameter
     */
    public void setFocusedExpression(Object[] expressionInfo)
    {
        if (expressionInfo == null) return;
        int expID = ((Integer)expressionInfo[0]).intValue();
        switch (expID)
        {
            case COMPONENT_TIME_SERIES_LIST:
                int index = ((Integer)expressionInfo[1]).intValue();
                
                if (index >=0 && jList1.getModel().getSize() > index )
                {
                    jList1.setSelectedIndex(index);
                    newInfo = new Object[expressionInfo.length-2];
                    for (int i=2; i< expressionInfo.length; ++i) newInfo[i-2] = expressionInfo[i];
                    break;
                }
        }
    }
    
    /**
     * This method checks for the variable subExpID. It is called when the component is shown.
     * If the value is >= 0, the modify button will be action-performed
     */
    private void openExtraWindows()
    {
        if (newInfo != null)
        {
            jButtonModifyActionPerformed(new ActionEvent(jButtonModify,0,""));
        }
        newInfo = null;
    }
    
    public void containerWindowOpened() {
        openExtraWindows();
    }
     
}