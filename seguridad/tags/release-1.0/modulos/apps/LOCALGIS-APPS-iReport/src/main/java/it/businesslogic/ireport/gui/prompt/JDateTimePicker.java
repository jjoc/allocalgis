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
 * JDateTimePicker.java
 * 
 * Created on August 30, 2006, 12:33 PM
 *
 */

package it.businesslogic.ireport.gui.prompt;

import it.businesslogic.ireport.util.I18n;
import it.businesslogic.ireport.util.LanguageChangedEvent;
import it.businesslogic.ireport.util.LanguageChangedListener;
import java.awt.BorderLayout;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author  gtoffoli
 */
public class JDateTimePicker extends javax.swing.JPanel implements LanguageChangedListener {
    
    private Locale locale = null;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker = null;
    
    /** Creates new form NewJPanel */
    public JDateTimePicker() {
        initComponents();
        
        jSpinnerH.setModel( new javax.swing.SpinnerNumberModel(0,0,23,1));
        jSpinnerM.setModel( new javax.swing.SpinnerNumberModel(0,0,59,1));
        jSpinnerS.setModel( new javax.swing.SpinnerNumberModel(0,0,59,1));
        
        datePicker = new com.michaelbaranov.microba.calendar.DatePicker();
        jPanel1.add(datePicker, BorderLayout.CENTER);
        setLocale( I18n.getCurrentLocale() );
        setDate(new java.util.Date());
        
        it.businesslogic.ireport.util.I18n.addOnLanguageChangedListener(this);
        applyI18n();
    }

    
    public void setLocale(Locale locale) {
        this.locale = locale;
        datePicker.setLocale(locale );
    }

    public Locale getLocale() {
        return locale;
    }
    
    public void setDate(java.util.Date d)
    {
        if (d == null) return;
        GregorianCalendar gc = null;
        
        if (getLocale() != null)
         gc = new GregorianCalendar(getLocale());
        else
         gc = new GregorianCalendar();
        
        gc.setTime( d );
        
        jSpinnerH.setValue( new Integer(gc.get( gc.HOUR_OF_DAY)) );
        jSpinnerM.setValue( new Integer(gc.get( gc.MINUTE)) );
        jSpinnerS.setValue( new Integer(gc.get( gc.SECOND)) );
        try {
            datePicker.setDate( d );
        } catch (Exception ex)
        {
            
        }
    }

    public java.util.Date getDate()
    {
         if (datePicker.getDate() == null) return null;
         GregorianCalendar gc = new GregorianCalendar();
         gc.setTime( datePicker.getDate()  );
         gc.set( gc.HOUR_OF_DAY, ((SpinnerNumberModel)jSpinnerH.getModel()).getNumber().intValue() );
         gc.set( gc.MINUTE, ((SpinnerNumberModel)jSpinnerM.getModel()).getNumber().intValue());
         gc.set( gc.MINUTE, ((SpinnerNumberModel)jSpinnerS.getModel()).getNumber().intValue() );
         
         return gc.getTime();
    }
    
    public void languageChanged(LanguageChangedEvent evt) {
        applyI18n();
    }
    
    public void applyI18n() {
                // Start autogenerated code ----------------------
                jLabelMin.setText(I18n.getString("jDateTimePicker.labelMin",":"));
                jLabelSec.setText(I18n.getString("jDateTimePicker.labelSec",":"));
                // End autogenerated code ----------------------
        jLabelH.setText(it.businesslogic.ireport.util.I18n.getString("time_picker", "Time"));
      }
        
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabelH = new javax.swing.JLabel();
        jSpinnerH = new javax.swing.JSpinner();
        jLabelMin = new javax.swing.JLabel();
        jSpinnerM = new javax.swing.JSpinner();
        jLabelSec = new javax.swing.JLabel();
        jSpinnerS = new javax.swing.JSpinner();

        setLayout(new java.awt.GridBagLayout());

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        add(jPanel1, gridBagConstraints);

        jLabelH.setText("Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        add(jLabelH, gridBagConstraints);

        jSpinnerH.setMinimumSize(new java.awt.Dimension(35, 18));
        jSpinnerH.setPreferredSize(new java.awt.Dimension(35, 18));
        add(jSpinnerH, new java.awt.GridBagConstraints());

        jLabelMin.setText(":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        add(jLabelMin, gridBagConstraints);

        jSpinnerM.setMinimumSize(new java.awt.Dimension(35, 18));
        jSpinnerM.setPreferredSize(new java.awt.Dimension(35, 18));
        add(jSpinnerM, new java.awt.GridBagConstraints());

        jLabelSec.setText(":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        add(jLabelSec, gridBagConstraints);

        jSpinnerS.setMinimumSize(new java.awt.Dimension(35, 18));
        jSpinnerS.setPreferredSize(new java.awt.Dimension(35, 18));
        add(jSpinnerS, new java.awt.GridBagConstraints());

    }// </editor-fold>//GEN-END:initComponents
    
    
    
    // Variables declaration - do not modify
//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelH;
    private javax.swing.JLabel jLabelMin;
    private javax.swing.JLabel jLabelSec;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinnerH;
    private javax.swing.JSpinner jSpinnerM;
    private javax.swing.JSpinner jSpinnerS;
    // End of variables declaration//GEN-END:variables
    
}