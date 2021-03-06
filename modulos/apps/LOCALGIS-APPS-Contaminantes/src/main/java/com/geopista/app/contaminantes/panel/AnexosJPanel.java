/**
 * AnexosJPanel.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * AnexosJPanel.java
 *
 * Created on 26 de octubre de 2004, 16:26
 */

package com.geopista.app.contaminantes.panel;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import com.geopista.app.contaminantes.CListaAnexosTableModel;
import com.geopista.app.contaminantes.ComboBoxTableEditor;
import com.geopista.app.contaminantes.Estructuras;
import com.geopista.app.contaminantes.TextFieldRenderer;
import com.geopista.app.contaminantes.TextFieldTableEditor;
import com.geopista.app.contaminantes.UtilsContaminantes;
import com.geopista.app.contaminantes.utils.CUtilidades;
import com.geopista.app.utilidades.CUtilidadesComponentes;
import com.geopista.app.utilidades.estructuras.ComboBoxEstructuras;
import com.geopista.app.utilidades.estructuras.ComboBoxRendererEstructuras;
import com.geopista.protocol.contaminantes.CAnexo;
import com.geopista.protocol.contaminantes.CConstantes;
import com.geopista.protocol.contaminantes.Inspeccion;
import com.geopista.protocol.contaminantes.tipos.CTipoAnexo;

/**
 *
 * @author  charo
 */
public class AnexosJPanel extends javax.swing.JPanel {

    private ResourceBundle _messages;
    private Inspeccion _inspeccion= null;

    private DefaultTableModel _listaAnexosTableModel;

    Hashtable _hAnexosAnnadidos= new Hashtable();
    Hashtable _hAnexosInspeccion= new Hashtable();

    public boolean _excedeSizeFilesUploaded= false;

Logger logger = Logger.getLogger(AnexosJPanel.class);

    /** Creates new form AnexosJPanel */
    public AnexosJPanel() {
        initComponents();
    }


    /** Creates new form AnexosJPanel */
    public AnexosJPanel(ResourceBundle messages) {
        _messages= messages;

        initComponents();
        configureComponents();
    }

    public void setInspeccion(Inspeccion inspeccion){
        _inspeccion= inspeccion;
        _hAnexosInspeccion= new Hashtable();

        if (_inspeccion != null){
            if (_inspeccion.getAnexos() != null){
                Vector v= _inspeccion.getAnexos();
                Enumeration en = v.elements();
                while (en.hasMoreElements()) {
                    CAnexo anexo = (CAnexo) en.nextElement();
                    /** Insertamos en _hAnexosInspeccion */
                    /** Esta estructura nos servira para hacer las b�squedas de los anexos por nombre,
                     *  en la construccion del vector con los anexos marcados como borrados y annadidos
                     */
                    _hAnexosInspeccion.put(anexo.getFileName(), anexo);

                    /** rellenamos la tabla de anexos */
                    TableColumn col1 = listaAnexosJTable.getColumnModel().getColumn(1);
                    // Annadimos a la tabla el editor TextField en la tercera columna (descripcion)
                    TableColumn col2 = listaAnexosJTable.getColumnModel().getColumn(2);

                    col1.setCellEditor(new ComboBoxTableEditor(new ComboBoxEstructuras(Estructuras.getListaTipoAnexos(), null, com.geopista.app.contaminantes.Constantes.Locale.toString(), false)));
                    col1.setCellRenderer(new ComboBoxRendererEstructuras(Estructuras.getListaTipoAnexos(), null, com.geopista.app.contaminantes.Constantes.Locale.toString(), false));

                    col2.setCellEditor(new TextFieldTableEditor());
                    col2.setCellRenderer(new TextFieldRenderer());

                    ComboBoxEstructuras combox = (ComboBoxEstructuras) ((ComboBoxTableEditor) listaAnexosJTable.getCellEditor(_listaAnexosTableModel.getRowCount(), 1)).getComponent();
                    combox.setSelectedPatron(new Integer(anexo.getTipoAnexo().getIdTipoAnexo()).toString());
                    String descTipo= Estructuras.getListaTipoAnexos().getDomainNode(combox.getSelectedPatron()).getTerm(com.geopista.app.contaminantes.Constantes.Locale.toString());

                    /** comprobamos que no este marcado como borrado */
                    if (anexo.getEstado() != com.geopista.app.licencias.CConstantesLicencias.CMD_ANEXO_DELETED){
                        String dataColumn0= anexo.getFileName();
                        if (anexo.getIdAnexo() == -1){
                            dataColumn0= anexo.getPath();
                        }

                        Object[] rowData = {dataColumn0, combox.getSelectedItem(), anexo.getObservacion()};
                        _listaAnexosTableModel.addRow(rowData);
                    }
                }
            }
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        annadirJButton = new javax.swing.JButton();
        eliminarJButton = new javax.swing.JButton();
        listaAnexosJScrollPane = new javax.swing.JScrollPane();
        listaAnexosJTable = new javax.swing.JTable();
        abrirJButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        annadirJButton.setText("");
        annadirJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annadirJButtonActionPerformed(evt);
            }
        });

        add(annadirJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 20, 20));

        eliminarJButton.setText("");
        eliminarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarJButtonActionPerformed(evt);
            }
        });

        add(eliminarJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 20, 20));

        listaAnexosJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Tipo", "Fichero", "Observacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        listaAnexosJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaAnexosJTableMouseClicked(evt);
            }
        });

        listaAnexosJScrollPane.setViewportView(listaAnexosJTable);

        add(listaAnexosJScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 80));

        abrirJButton.setText("");
        abrirJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirJButtonActionPerformed(evt);
            }
        });

        add(abrirJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 20, 20));

    }//GEN-END:initComponents

    private void listaAnexosJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaAnexosJTableMouseClicked
        // TODO add your handling code here:
        int row = listaAnexosJTable.getSelectedRow();
        logger.info("row: " + row);

        if (row != -1) {
            abrirJButton.setEnabled(true);
            annadirJButton.setEnabled(true);
            eliminarJButton.setEnabled(true);
        }


    }//GEN-LAST:event_listaAnexosJTableMouseClicked

    private void eliminarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarJButtonActionPerformed
        // TODO add your handling code here:
        if (eliminarJButton.isEnabled()) {
            int selected = listaAnexosJTable.getSelectedRow();
            if (selected != -1) {
                int ok= JOptionPane.showConfirmDialog(this, _messages.getString("Licencias.confirmarBorrado"), _messages.getString("Licencias.tittle"), JOptionPane.YES_NO_OPTION);
                if (ok == JOptionPane.NO_OPTION) return;

                /** Marcamos el fichero como borrado */
                /*
                String nomFichero= (String)_listaAnexosTableModel.getValueAt(selected, 0);
                int i= 0;
                boolean encontrado= false;
                while ((!encontrado) && (i<_vAnexosSolicitud.size())){
                    String nom= (String)_vAnexosSolicitud.get(i);
                    if (nom.equalsIgnoreCase(nomFichero)){
                        CAnexo anexo= (CAnexo)_vAnexosSolicitud.get(i);
                        anexo.setEstado(CConstantesLicencias.CMD_ANEXO_DELETED);
                        _vAnexosSolicitud.add(i, anexo);
                        encontrado= true;
                    }
                    i++;
                }
                */
                _listaAnexosTableModel.removeRow(selected);
                /** Comprobamos si algun item de la lista queda seleccionado.
                 *  Si es asi, habilitamos el boton Eliminar, si no, le deshabilitamos
                 */
                if (listaAnexosJTable.getModel().getRowCount() > 0) {
                    if (listaAnexosJTable.getSelectedRow() != -1) {
                        eliminarJButton.setEnabled(true);
                        abrirJButton.setEnabled(true);
                    } else {
                        eliminarJButton.setEnabled(false);
                        abrirJButton.setEnabled(false);
                    }
                } else {
                    eliminarJButton.setEnabled(false);
                    abrirJButton.setEnabled(false);
                }
            } else { // no ha seleccionado ningun item
                Toolkit.getDefaultToolkit().beep();
                eliminarJButton.setEnabled(false);
                abrirJButton.setEnabled(false);
            }
        }

    }//GEN-LAST:event_eliminarJButtonActionPerformed

    private void annadirJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annadirJButtonActionPerformed
        // TODO add your handling code here:
        /** Permite annadir varios elementos a la lista */
        listaAnexosJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JFileChooser chooser = new JFileChooser();
        com.geopista.app.utilidades.GeoPistaFileFilter filter = new com.geopista.app.utilidades.GeoPistaFileFilter();
        filter.addExtension("doc");
        filter.addExtension("txt");
        filter.setDescription(_messages.getString("AnexosJPanel.mensaje4"));
        chooser.setFileFilter(filter);
        /** Permite multiples selecciones */
        chooser.setMultiSelectionEnabled(true);

        int returnVal = chooser.showOpenDialog(this.getParent());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = chooser.getSelectedFiles();
            //System.out.println("Fichero(s) seleccionado " + selectedFiles.length);
            if (selectedFiles.length > 0) {
                for (int i = 0; i < selectedFiles.length; i++) {
                    for (int j = 0; j < _listaAnexosTableModel.getRowCount(); j++) {
                        String nombreAnexo = (String) _listaAnexosTableModel.getValueAt(j, 0);
                        File file = new File(nombreAnexo);
                        if (file.isAbsolute()) {
                            nombreAnexo = file.getName();
                        }

                        if (nombreAnexo.equalsIgnoreCase(selectedFiles[i].getName())) {
                            /** Ya existe un anexo con ese nombre */
                            UtilsContaminantes.mostrarMensaje(this.getParent(), _messages.getString("AnexosJPanel.mensaje.tittle"), _messages.getString("AnexosJPanel.mensaje3"));
                            return;
                        }
                    }
                    /** No se repite la entrada */
                    /** Por defecto selected el primer tipo */
                    // Damos valores para la combo de la primera columna
                    // Annadimos a la tabla el editor ComboBox en la segunda columna (tipo)
                    int vColIndexCB = 1;
                    TableColumn col1 = listaAnexosJTable.getColumnModel().getColumn(vColIndexCB);
                    // Annadimos a la tabla el editor TextField en la tercera columna (descripcion)
                    TableColumn col2 = listaAnexosJTable.getColumnModel().getColumn(2);

                    col1.setCellEditor(new ComboBoxTableEditor(new ComboBoxEstructuras(Estructuras.getListaTipoAnexos(), null, com.geopista.app.contaminantes.Constantes.Locale.toString(), false)));
                    col1.setCellRenderer(new ComboBoxRendererEstructuras(Estructuras.getListaTipoAnexos(), null, com.geopista.app.contaminantes.Constantes.Locale.toString(), false));

                    col2.setCellEditor(new TextFieldTableEditor());
                    col2.setCellRenderer(new TextFieldRenderer());

                    ComboBoxEstructuras combox = (ComboBoxEstructuras) ((ComboBoxTableEditor) listaAnexosJTable.getCellEditor(_listaAnexosTableModel.getRowCount(), 1)).getComponent();
                    String descTipo= Estructuras.getListaTipoAnexos().getDomainNode(combox.getSelectedPatron()).getTerm(com.geopista.app.contaminantes.Constantes.Locale.toString());
                    Object[] rowData = {selectedFiles[i].getAbsolutePath(), descTipo, ""};
                    _listaAnexosTableModel.addRow(rowData);

                    /** Marcamos el fichero como annadido y lo insertamos en un vector auxiliar de annadidos*/
                    if ((_hAnexosAnnadidos.get(selectedFiles[i].getName()) == null)) {
                        CAnexo anexo = new CAnexo(new CTipoAnexo(), selectedFiles[i].getName(), "");
                        anexo.setEstado(com.geopista.app.licencias.CConstantesLicencias.CMD_ANEXO_ADDED);
                        _hAnexosAnnadidos.put(selectedFiles[i].getName(), anexo);
                    }
                }
            }
        }

        /** Solo se podra seleccionar un elemento de la lista */
        listaAnexosJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }//GEN-LAST:event_annadirJButtonActionPerformed

    private void abrirJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirJButtonActionPerformed
        // TODO add your handling code here:
        int row = listaAnexosJTable.getSelectedRow();
        logger.info("row: " + row);

        if (row == -1) {
            return;
        }

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        String fileName = (String) listaAnexosJTable.getValueAt(row, 0);
        logger.info("fileName: " + fileName);

        String tmpFile= "";
        File f= new File(fileName);

        if (!f.isAbsolute()){
            /** Dialogo para seleccionar donde dejar el fichero */
            JFileChooser chooser = new JFileChooser();

            /** Permite multiples selecciones */
            chooser.setMultiSelectionEnabled(false);
            chooser.setSelectedFile(f);

            int returnVal = chooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile= chooser.getSelectedFile();
                if (selectedFile != null){
                    String tmpDir= "";
                    tmpFile= selectedFile.getAbsolutePath();
                    int index= tmpFile.lastIndexOf(selectedFile.getName());
                    if (index != -1){
                        tmpDir= tmpFile.substring(0, index);
                    }

                   /** Comprobamos si existe el directorio. */
                    try {
                        File dir = new File(tmpDir);
                        logger.info("dir: " + dir);

                        if (!dir.exists()) {
                            logger.warn("Directorio temporal creado.");
                            dir.mkdirs();
                        }
                    } catch (Exception ex) {
                        logger.error("Exception: " + ex.toString());
                    }


                    boolean resultado = CUtilidadesComponentes.GetURLFile(CConstantes.anexosActividadesContaminantesUrl + _inspeccion.getId() + "/" + fileName, tmpFile, "", 0);
                    if (!resultado) {
                    	UtilsContaminantes.mostrarMensaje(this.getParent(), _messages.getString("AnexosJPanel.mensaje.tittle"), _messages.getString("AnexosJPanel.mensaje1"));
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        return;
                    }
                }else{
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    return;                    
                }
            }else{
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                return;
            }
        }else{
            tmpFile= f.getAbsolutePath();
        }

        /** Visualizamos el fichero descargado si SO es Windows. */
        if (CUtilidades.isWindows()){
            try {
                Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \"" + tmpFile + "\"");

            } catch (Exception ex) {
                logger.warn("Exception: " + ex.toString());
                UtilsContaminantes.mostrarMensaje(this.getParent(), _messages.getString("AnexosJPanel.mensaje.tittle"), _messages.getString("AnexosJPanel.mensaje2") + " " + tmpFile);
            }
        }
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_abrirJButtonActionPerformed



    public void enabled(boolean bValor)
    {
        //listaAnexosJScrollPane.setEnabled(bValor);
        listaAnexosJTable.setEnabled(bValor);
        annadirJButton.setEnabled(bValor);
        /** dependera de que se haya o no seleccionado un anexo de la tabla */
        abrirJButton.setEnabled(false);
        eliminarJButton.setEnabled(false);
    }

    public void configureComponents(){
        abrirJButton.setEnabled(false);
        eliminarJButton.setEnabled(false);
        annadirJButton.setEnabled(true);
        try
        {
            annadirJButton.setToolTipText(_messages.getString("AnexosJPanel.annadirJButton.toolTipText.text"));
            eliminarJButton.setToolTipText(_messages.getString("AnexosJPanel.eliminarJButton.toolTipText.text"));
            abrirJButton.setToolTipText(_messages.getString("AnexosJPanel.abrirJButton.toolTipText.text"));
        }catch(Exception e)
        {
            logger.error("Faltan recursos:",e);
        }
        abrirJButton.setIcon(com.geopista.app.contaminantes.utils.CUtilidades.iconoAbrir);
        annadirJButton.setIcon(com.geopista.app.contaminantes.utils.CUtilidades.iconoAdd);
        eliminarJButton.setIcon(com.geopista.app.contaminantes.utils.CUtilidades.iconoRemove);

        /** Anexos */
        try
        {
            String[] columnNamesAnexos = {_messages.getString("AnexosJPanel.column1"),
                                      _messages.getString("AnexosJPanel.column2"),
                                      _messages.getString("AnexosJPanel.column3")};

            CListaAnexosTableModel.setColumnNames(columnNamesAnexos);
             }catch(Exception e)
        {
            logger.error("Faltan recursos:",e);
        }
        _listaAnexosTableModel = new CListaAnexosTableModel();

        listaAnexosJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAnexosJTable.setModel(_listaAnexosTableModel);
        listaAnexosJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        listaAnexosJTable.getTableHeader().setReorderingAllowed(false);
        for (int j=0; j< listaAnexosJTable.getColumnCount(); j++){
            TableColumn column = listaAnexosJTable.getColumnModel().getColumn(j);
            if (j==2){
                column.setMinWidth(350);
                column.setMaxWidth(400);
                column.setWidth(350);
                column.setPreferredWidth(350);
            }else{
                column.setMinWidth(150);
                column.setMaxWidth(300);
                column.setWidth(150);
                column.setPreferredWidth(150);
            }
            column.setResizable(true);
        }
    }


    public Vector getListaAnexos(){
        // Recogemos la lista de documentos anexados a la solicitud
        Vector vListaAnexos= new Vector();
        Hashtable hAnexosAux = new Hashtable();

        try{
            Vector vAnexosUploaded= getAnexosTableModel();
            if (!sizeFilesUploadedExceeded(vAnexosUploaded)){
                for (Enumeration e= vAnexosUploaded.elements(); e.hasMoreElements();){
                    CAnexo anexo= (CAnexo)e.nextElement();

                    /** Comprobamos si se ha marcado como annadido o como borrado */
                    if ((_hAnexosInspeccion.get(anexo.getFileName()) != null) && (_hAnexosAnnadidos.get(anexo.getFileName()) != null)){
                        /** Ha sido borrado y posteriormente annadido (actualizado) */
                        /** marcado como borrado */
                        anexo.setEstado(com.geopista.app.licencias.CConstantesLicencias.CMD_ANEXO_DELETED);
                        vListaAnexos.add(anexo);

                        /** Necesario, ya que te guarda una referencia al tratarse del mismo objeto*/
                        CAnexo nuevo = new CAnexo(anexo.getTipoAnexo(), anexo.getFileName(), anexo.getObservacion());
                        nuevo.setPath(anexo.getPath());
                        /** marcado como annadido */
                        nuevo.setEstado(com.geopista.app.licencias.CConstantesLicencias.CMD_ANEXO_ADDED);
                        /** -- MultipartPostMethod: comentamos para no enviar el contenido. Enviamos el file directamente. */
                        /*
                        byte[] content = CUtilidades.getBytesFromFile(file);
                        nuevo.setContent(content);
                        */
                        vListaAnexos.add(nuevo);
                        hAnexosAux.put(nuevo.getFileName(), nuevo);
                    } else if ((_hAnexosInspeccion.get(anexo.getFileName()) == null) && (_hAnexosAnnadidos.get(anexo.getFileName()) != null)) {
                        /** El anexo ha sido annadido */
                        //System.out.println(anexo.getFileName() + " ** El anexo ha sido annadido *");
                        anexo.setEstado(com.geopista.app.licencias.CConstantesLicencias.CMD_ANEXO_ADDED);
                        /** -- MultipartPostMethod: comentamos para no enviar el contenido. Enviamos el file directamente. */
                        /*
                        byte[] content = CUtilidades.getBytesFromFile(file);
                        anexo.setContent(content);
                        */
                        vListaAnexos.addElement(anexo);
                        hAnexosAux.put(anexo.getFileName(), anexo);

                    }else if ((_hAnexosInspeccion.get(anexo.getFileName()) != null) && (_hAnexosAnnadidos.get(anexo.getFileName()) == null)) {
                        /* Se pueden dar 2 casos:
                            1.- El anexo no ha sufrido cambios. Puede que solo haya cambiado el tipo y la observacion.
                            2.- Se annadieron varios.
                                Alguno de ellos excedia el tam. maximo del servidor.
                                Este se ha eliminado, manteniendo los anteriormente annadidos ->
                                La hash _hAnexosAnnadidos tiene tamanno 0 (al abrir un AnexosJPanel se ha destruido la hash)
                        */

                        CAnexo existente= (CAnexo)_hAnexosInspeccion.get(anexo.getFileName());
                        if (existente.getEstado() == com.geopista.app.licencias.CConstantesLicencias.CMD_ANEXO_ADDED){
                            /** caso 2.- Se annade un nuevo anexo.  */
                            /** marcado como annadido */
                            anexo.setEstado(com.geopista.app.licencias.CConstantesLicencias.CMD_ANEXO_ADDED);
                            vListaAnexos.addElement(anexo);
                            hAnexosAux.put(anexo.getFileName(), anexo);

                        }else{
                            /** caso 1.- El anexo no ha sufrido cambios. Puede que solo haya cambiado el tipo y la observacion. */
                            anexo.setIdAnexo(existente.getIdAnexo());
                            vListaAnexos.addElement(anexo);
                            hAnexosAux.put(anexo.getFileName(), anexo);
                        }
                    } else if ((_hAnexosInspeccion.get(anexo.getFileName()) == null) && (_hAnexosAnnadidos.get(anexo.getFileName()) == null)) {
                        /** Este caso no se puede dar */
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Exception: " + ex.toString());
        }

        /** Comprobamos aquellos que han sido marcados como borrados */
        Enumeration enumerationElement = _hAnexosInspeccion.elements();
        while (enumerationElement.hasMoreElements()) {
            CAnexo a = (CAnexo) enumerationElement.nextElement();
            if (hAnexosAux.get(a.getFileName()) == null) {
                a.setEstado(com.geopista.app.licencias.CConstantesLicencias.CMD_ANEXO_DELETED);
                vListaAnexos.addElement(a);
            }
        }

        if (vListaAnexos.size() > 0)
            return vListaAnexos;
        else return null;
    }

    public Vector getAnexosTableModel(){
        // Recogemos la lista de los nuevos documentos anexados a la solicitud, (aquellos
        // que tienen path completo).
        Vector vAnexos= new Vector();

        try{
            int numRows = listaAnexosJTable.getRowCount();

            if (numRows > 0){
                for (int i = 0; i < numRows; i++) {
                    String nomFichero = (String) listaAnexosJTable.getValueAt(i, 0);
                   /*
                    ComboBoxRenderer renderBox = (ComboBoxRenderer) listaAnexosJTable.getCellRenderer(i, 1);
                    listaAnexosJTable.prepareRenderer(renderBox, i, 1);
                    int index = renderBox.getSelectedIndex();
                   */
                    ComboBoxRendererEstructuras renderBox = (ComboBoxRendererEstructuras) listaAnexosJTable.getCellRenderer(i, 1);
                    listaAnexosJTable.prepareRenderer(renderBox, i, 1);
                    int index = new Integer(renderBox.getSelectedPatron()).intValue();

                    TextFieldRenderer renderText = (TextFieldRenderer) listaAnexosJTable.getCellRenderer(i, 2);
                    listaAnexosJTable.prepareRenderer(renderText, i, 2);
                    String descripcion = renderText.getText();

                    CTipoAnexo tipoAnexo = new CTipoAnexo(index, "", "");
                    CAnexo anexo = new CAnexo(tipoAnexo, nomFichero, descripcion);

                    /** Lectura del contenido del anexo */
                    File file = new File(nomFichero);
                    if (file.isAbsolute()) {
                        /* MultiPartPost */
                        nomFichero = file.getName();
                        anexo.setFileName(nomFichero);
                        /* MultipartPostMethod - en lugar de enviar el contenido, enviamos el path absoluto del fichero -*/
                        anexo.setPath(file.getAbsolutePath());
                        /**/
                    }
                    vAnexos.add(anexo);
                }
            }
        } catch (Exception ex) {
            logger.error("Exception: " + ex.toString());
        }
        return vAnexos;
    }


    public boolean sizeFilesUploadedExceeded(Vector vAnexos){

        long totalSizeFilesUploaded= 0;

        if (vAnexos != null){
            for (Enumeration e= vAnexos.elements(); e.hasMoreElements();) {
                com.geopista.protocol.contaminantes.CAnexo anexo= (com.geopista.protocol.contaminantes.CAnexo)e.nextElement();

                try {
                    if ((anexo.getPath() != null) && (!anexo.getPath().trim().equalsIgnoreCase(""))){
                        File file = new File(anexo.getPath());
                        if (file.isAbsolute()) {
                            /* MultiPartPost */
                            /** comprobamos que no excedan el tamanno maximo permitido */
                            totalSizeFilesUploaded=+file.length();
                            if (totalSizeFilesUploaded > com.geopista.app.contaminantes.Constantes.totalMaxSizeFilesUploaded){
                                JOptionPane optionPane= new JOptionPane(_messages.getString("AnexosJPanel.Message2")+": " +com.geopista.app.contaminantes.Constantes.totalMaxSizeFilesUploaded+" bytes", JOptionPane.ERROR_MESSAGE);
                                JDialog dialog =optionPane.createDialog(this,"ERROR");
                                dialog.show();
                                return true;
                            }
                        }
                    }

                } catch (Exception ex) {
                    logger.error("Exception: " + ex.toString());
                }
            }
        }

        return false;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirJButton;
    private javax.swing.JButton annadirJButton;
    private javax.swing.JButton eliminarJButton;
    private javax.swing.JScrollPane listaAnexosJScrollPane;
    private javax.swing.JTable listaAnexosJTable;
    // End of variables declaration//GEN-END:variables
    
}
