/**
 * RunDatastoreQueryPanel.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.vividsolutions.jump.workbench.ui.plugin.datastore;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.vividsolutions.jump.I18N;
import com.vividsolutions.jump.util.Block;
import com.vividsolutions.jump.util.CollectionUtil;
import com.vividsolutions.jump.workbench.WorkbenchContext;
import com.vividsolutions.jump.workbench.datastore.ConnectionDescriptor;
import com.vividsolutions.jump.workbench.ui.RecordPanel;
import com.vividsolutions.jump.workbench.ui.RecordPanelModel;
import com.vividsolutions.jump.workbench.ui.ValidatingTextField;

public class RunDatastoreQueryPanel
        extends ConnectionPanel
        implements ActionListener, RecordPanelModel {

    private JTextField maxFeaturesTextField;
    private Hashtable queryMap = new Hashtable();
    private ArrayList currentConnectionQueries = new ArrayList();
    private int currentIndex = -1;
    private RecordPanel recordPanel = new RecordPanel(this);


    public RunDatastoreQueryPanel(WorkbenchContext context) {
        super(context);
        initialize();
    }


    public int getRecordCount() {
        int num = 0;
        if ( currentConnectionQueries != null ) {
            num = currentConnectionQueries.size();
        }
        return num;
    }

    public void setCurrentIndex(int index) {
        currentIndex = index;
        String query = null;

        if ( index > -1 ) {
            query = (String) currentConnectionQueries.get(index);
        }

        getQueryTextArea().setText(query);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    private void initialize() {
        addRow(I18N.get("jump.workbench.ui.plugin.datastore.RunDatastoreQueryPanel.Max-Features"), getMaxFeaturesTextField(), null, false);
        addRow(I18N.get("jump.workbench.ui.plugin.datastore.RunDatastoreQueryPanel.Query"), new JScrollPane(getQueryTextArea()) {
            {
                setPreferredSize(new Dimension(MAIN_COLUMN_WIDTH, 100));
            }
        }, null, true);

        //
        // We are not using addRow becaus we want the widgets centered over the
        // OK/Cancel buttons.
        //
        add( recordPanel,
                 new GridBagConstraints( 0, //x
                 3, // y
                 3, // width
                 1, // height
                 1,
                 0,
                 GridBagConstraints.NORTHWEST,
                 GridBagConstraints.HORIZONTAL,
                 INSETS,
                 0,
                 0 )
            );

        getConnectionComboBox().addActionListener( this );
    }

    private JTextField getMaxFeaturesTextField() {
        if (maxFeaturesTextField == null) {
            maxFeaturesTextField = new ValidatingTextField("", 10,
                    new ValidatingTextField.BoundedIntValidator(1,
                            Integer.MAX_VALUE));
        }
        return maxFeaturesTextField;
    }

    private JTextArea getQueryTextArea() {
        if (queryTextArea == null) {
            queryTextArea = new JTextArea();
        }
        return queryTextArea;
    }

    private JTextArea queryTextArea;


    public String validateInput() {
        String errMsg = super.validateInput();

        if ( errMsg == null ) {
            if (getQuery().length() == 0) {
                errMsg = I18N.get("jump.workbench.ui.plugin.datastore.RunDatastoreQueryPanel.Required-field-missing-Query");
            }
        }

        return errMsg;
    }

    public String getQuery() {
        return queryTextArea.getText().trim();
    }


    public void saveQuery() {
        String query = getQuery();
        // maybe we should check for duplicates
        currentConnectionQueries.add(query);
        currentIndex = currentConnectionQueries.size()-1;
    }


    public void actionPerformed( ActionEvent actionEvent ) {
        ConnectionDescriptor cd = getConnectionDescriptor();

        if ( cd != null ) {
            if ( queryMap.containsKey(cd) ) {
                ArrayList prevQueries = (ArrayList) queryMap.get(cd);
                currentConnectionQueries = prevQueries;
            } else {
                currentConnectionQueries = new ArrayList();
                queryMap.put( cd, currentConnectionQueries );
            }

            setCurrentIndex( currentConnectionQueries.size()-1 );
            recordPanel.updateAppearance();
        }
    }


    /**
     * @return null if the user has left the Max Features text field blank.
     */
    public Integer getMaxFeatures() {
        return maxFeaturesTextField.getText().trim().length() > 0 ? new Integer(
                maxFeaturesTextField.getText().trim()) : null;
    }

    protected Collection connectionDescriptors() {
        return CollectionUtil.select(super.connectionDescriptors(),
                new Block() {
                    public Object yield(Object connectionDescriptor) {
                        try {
                            return Boolean
                                    .valueOf(connectionManager()
                                                .getDriver(
                                                    ((ConnectionDescriptor) connectionDescriptor)
                                                            .getDataStoreDriverClassName())
                                            .isAdHocQuerySupported());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }
}
