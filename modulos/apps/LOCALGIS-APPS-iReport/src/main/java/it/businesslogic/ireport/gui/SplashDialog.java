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
 * SplashDialog.java
 *
 * Created on 9 ottobre 2004, 11.19
 *
 */

package it.businesslogic.ireport.gui;
import javax.swing.SwingUtilities;

/**
 *
 * @author  Administrator
 */
public class SplashDialog extends javax.swing.JDialog {

    /** Creates new form SplashDialog */
    public SplashDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //if (new java.util.Date().getTime() %2 == 0)
        //{
        //    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/businesslogic/ireport/icons/logo13b.png")));
        //    jLabel1.setIconTextGap(0);
        //}
        
        try {
            java.util.Properties p = MainFrame.getBrandingProperties();
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource( p.getProperty("ireport.splashscreen") )));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        applyI18n();
        //this.setSize(320,261);
        this.pack();
        it.businesslogic.ireport.util.Misc.centerFrame(this);
    }

    public void updateLoadingStatus(int status, String label)
    {
        try {

            final int f_status = status;
            final String f_label = label;
            final Runnable r = new Runnable() {
                    public void run() {
                        jProgressBar1.setValue(f_status);
                        if (f_label != null)
                            jProgressBar1.setString(f_label);
                        }
            };

            if (SwingUtilities.isEventDispatchThread())
            {
                SwingUtilities.invokeLater(r);
            }
            else
            {
                SwingUtilities.invokeAndWait(r);
            }

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/businesslogic/ireport/icons/logo132.png")));
        jLabel1.setIconTextGap(0);
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        jProgressBar1.setMinimumSize(new java.awt.Dimension(10, 21));
        jProgressBar1.setPreferredSize(new java.awt.Dimension(148, 21));
        jProgressBar1.setString("Loading");
        jProgressBar1.setStringPainted(true);
        jPanel1.add(jProgressBar1, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

    public void applyI18n(){
                // Start autogenerated code ----------------------
                // End autogenerated code ----------------------
    }
}