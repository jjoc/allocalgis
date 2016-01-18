/**
 * TableSorted.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.app.utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


/**
 * Created by IntelliJ IDEA.
 * User: angeles
 * Date: 26-may-2004
 * Time: 19:14:20
 */
public class TableSorted  extends AbstractTableModel {
        protected TableModel tableModel;

        public static final int DESCENDING = -1;
        public static final int NOT_SORTED = 0;
        public static final int ASCENDING = 1;

        private static Directive EMPTY_DIRECTIVE = new Directive(-1, NOT_SORTED);

        public static final Comparator COMPARABLE_COMPARATOR = new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) o1).compareTo(o2);
            }
        };
        public static final Comparator LEXICAL_COMPARATOR = new Comparator() {
            public int compare(Object o1, Object o2) {
                return o1.toString().compareTo(o2.toString());
            }
        };

        private Row[] viewToModel;
        private int[] modelToView;

        private JTableHeader tableHeader;
        private MouseListener mouseListener;
        private TableModelListener tableModelListener;
        private java.util.Map columnComparators = new HashMap();
        private List sortingColumns = new ArrayList();
        private boolean disableSorting = false;
        
        private java.util.Map columnComparatorsByColumn = new HashMap();

        


        public TableSorted() {
            this.mouseListener = new MouseHandler();
            this.tableModelListener = new TableModelHandler();
        }

        public TableSorted(TableModel tableModel) {
            this();
            setTableModel(tableModel);
        }

        public TableSorted(TableModel tableModel, JTableHeader tableHeader) {
            this();
            setTableHeader(tableHeader);
            setTableModel(tableModel);
        }

        private void clearSortingState() {
        	viewToModel = null;
        	modelToView = null;                        
        }

        public TableModel getTableModel() {
            return tableModel;
        }

        public void setTableModel(TableModel tableModel) {
            if (this.tableModel != null) {
                this.tableModel.removeTableModelListener(tableModelListener);
            }

            this.tableModel = tableModel;
            if (this.tableModel != null) {
                this.tableModel.addTableModelListener(tableModelListener);
            }

            clearSortingState();
            fireTableStructureChanged();
        }

        public JTableHeader getTableHeader() {
            return tableHeader;
        }

        public void setTableHeader(JTableHeader tableHeader) {
            if (this.tableHeader != null) {
                this.tableHeader.removeMouseListener(mouseListener);
                TableCellRenderer defaultRenderer = this.tableHeader.getDefaultRenderer();
                if (defaultRenderer instanceof SortableHeaderRenderer) {
                    this.tableHeader.setDefaultRenderer(((SortableHeaderRenderer) defaultRenderer).tableCellRenderer);
                }
            }
            this.tableHeader = tableHeader;
            if (this.tableHeader != null) {
                this.tableHeader.addMouseListener(mouseListener);
                this.tableHeader.setDefaultRenderer(
                        new SortableHeaderRenderer(this.tableHeader.getDefaultRenderer()));
            }
        }

        public boolean isSorting() {
            return sortingColumns.size() != 0;
        }

        private Directive getDirective(int column) {
            for (int i = 0; i < sortingColumns.size(); i++) {
                Directive directive = (Directive)sortingColumns.get(i);
                if (directive.column == column) {
                    return directive;
                }
            }
            return EMPTY_DIRECTIVE;
        }

        public int getSortingStatus(int column) {
            return getDirective(column).direction;
        }

//        private void sortingStatusChanged() {
        public void sortingStatusChanged() {
            clearSortingState();
            fireTableDataChanged();
            if (tableHeader != null) {
                tableHeader.repaint();
            }
        }

        public void setSortingStatus(int column, int status) {
            Directive directive = getDirective(column);
            if (directive != EMPTY_DIRECTIVE) {
                sortingColumns.remove(directive);
            }
            if (status != NOT_SORTED) {
                sortingColumns.add(new Directive(column, status));
            }
            sortingStatusChanged();
        }

        protected Icon getHeaderRendererIcon(int column, int size) {
            Directive directive = getDirective(column);
            if (directive == EMPTY_DIRECTIVE) {
                return null;
            }
            return new Arrow(directive.direction == DESCENDING, size, sortingColumns.indexOf(directive));
        }

        private void cancelSorting() {
            sortingColumns.clear();
            sortingStatusChanged();
        }

        public void setColumnComparator(Class type, Comparator comparator) {
            if (comparator == null) {
                columnComparators.remove(type);
            } else {
                columnComparators.put(type, comparator);
            }
        }

        public void setColumnComparatorByColumn(int idColumn, Comparator comparator) {          
                columnComparators.put(idColumn, comparator);
        }

        
        protected Comparator getComparator(int column) {
            Class columnType = tableModel.getColumnClass(column);
            Comparator comparator = (Comparator) columnComparators.get(columnType);
            if (comparator != null) {
                return comparator;
            }
            
            if (columnComparators.get(column)!=null){
            	return (Comparator)columnComparators.get(column);
            }
            
            if (Comparable.class.isAssignableFrom(columnType)) {
                return COMPARABLE_COMPARATOR;
            }
            
         
            return LEXICAL_COMPARATOR;
        }

        private Row[] getViewToModel() {
        	if (viewToModel == null) {
        		
        		int tableModelRowCount = tableModel.getRowCount();
        		viewToModel = new Row[tableModelRowCount];
        		
        		for (int row = 0; row < tableModelRowCount; row++) {
        			
        			try {
						viewToModel[row] = new Row(row);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
        			
        		}

        		
        		if (isSorting()) {
        			Arrays.sort(viewToModel);
                }
            }
            return viewToModel;
        }

        public int modelIndex(int viewIndex) {
        	if(viewIndex >= getViewToModel().length)return 0;
        	//if (viewIndex>0)
        		return getViewToModel()[viewIndex].modelIndex;
        	//else
        		//return 0;
        }

        private int[] getModelToView() {
            if (modelToView == null) {
                int n = getViewToModel().length;
                modelToView = new int[n];
                for (int i = 0; i < n; i++) {
                    modelToView[modelIndex(i)] = i;
                }
            }
            return modelToView;
        }

        // TableModel interface methods

        public int getRowCount() {
            return (tableModel == null) ? 0 : tableModel.getRowCount();
        }

        public int getColumnCount() {
            return (tableModel == null) ? 0 : tableModel.getColumnCount();
        }

        public String getColumnName(int column) {
            return tableModel.getColumnName(column);
        }

        public Class getColumnClass(int column) {
            return tableModel.getColumnClass(column);
        }

        public boolean isCellEditable(int row, int column) {
            return tableModel.isCellEditable(modelIndex(row), column);
        }

        public Object getValueAt(int row, int column) {
        	try {
				return tableModel.getValueAt(modelIndex(row), column);
			} catch (Exception e) {
				return null;
			}
        }

        public void setValueAt(Object aValue, int row, int column) {
            tableModel.setValueAt(aValue, modelIndex(row), column);
        }

        public void removeRow(int row){
            ((DefaultTableModel)tableModel).removeRow(modelIndex(row));
        }

        public void insertRow(int row, Object[] data){
            ((DefaultTableModel)tableModel).insertRow(modelIndex(row), data);
        }

        public void addRow(Object[] data){
            ((DefaultTableModel)tableModel).addRow(data);
        }
    

        // Helper classes

        private class Row implements Comparable {
            private int modelIndex;

            public Row(int index) {
                this.modelIndex = index;
            }

            public int compareTo(Object o) {
                int row1 = modelIndex;
                int row2 = ((Row) o).modelIndex;

                for (Iterator it = sortingColumns.iterator(); it.hasNext();) {
                    Directive directive = (Directive) it.next();
                    int column = directive.column;
                    Object o1 = tableModel.getValueAt(row1, column);
                    Object o2 = tableModel.getValueAt(row2, column);

                    int comparison = 0;
                    // Define null less than everything, except null.
                    if (o1 == null && o2 == null) {
                        comparison = 0;
                    } else if (o1 == null) {
                        comparison = -1;
                    } else if (o2 == null) {
                        comparison = 1;
                    } else {
                        comparison = getComparator(column).compare(o1, o2);
                    }
                    if (comparison != 0) {
                        return directive.direction == DESCENDING ? -comparison : comparison;
                    }
                }
                return 0;
            }
        }

        private class TableModelHandler implements TableModelListener {
            public void tableChanged(TableModelEvent e) {
                // If we're not sorting by anything, just pass the event along.
                if (!isSorting()) {
                    clearSortingState();
                    fireTableChanged(e);
                    return;
                }

                // If the table structure has changed, cancel the sorting; the
                // sorting columns may have been either moved or deleted from
                // the model.
                if (e.getFirstRow() == TableModelEvent.HEADER_ROW) {
                    cancelSorting();
                    fireTableChanged(e);
                    return;
                }

                // We can map a cell event through to the view without widening
                // when the following conditions apply:
                //
                // a) all the changes are on one row (e.getFirstRow() == e.getLastRow()) and,
                // b) all the changes are in one column (column != TableModelEvent.ALL_COLUMNS) and,
                // c) we are not sorting on that column (getSortingStatus(column) == NOT_SORTED) and,
                // d) a reverse lookup will not trigger a sort (modelToView != null)
                //
                // Note: INSERT and DELETE events fail this test as they have column == ALL_COLUMNS.
                //
                // The last check, for (modelToView != null) is to see if modelToView
                // is already allocated. If we don't do this check; sorting can become
                // a performance bottleneck for applications where cells
                // change rapidly in different parts of the table. If cells
                // change alternately in the sorting column and then outside of
                // it this class can end up re-sorting on alternate cell updates -
                // which can be a performance problem for large tables. The last
                // clause avoids this problem.
                int column = e.getColumn();
                if (e.getFirstRow() == e.getLastRow()
                        && column != TableModelEvent.ALL_COLUMNS
                        && getSortingStatus(column) == NOT_SORTED
                        && modelToView != null) {
                    int viewIndex = getModelToView()[e.getFirstRow()];
                    fireTableChanged(new TableModelEvent(TableSorted.this,
                                                         viewIndex, viewIndex,
                                                         column, e.getType()));
                    return;
                }

                // Something has happened to the data that may have invalidated the row order.
                
                	clearSortingState();
                	fireTableDataChanged();
                
                return;
            }
        }

        private class MouseHandler extends MouseAdapter {
            public void mouseClicked(MouseEvent e) {
                JTableHeader h = (JTableHeader) e.getSource();
                TableColumnModel columnModel = h.getColumnModel();
                int viewColumn = columnModel.getColumnIndexAtX(e.getX());
                int column = columnModel.getColumn(viewColumn).getModelIndex();
                if (column != -1) {
                    int status = getSortingStatus(column);
                    if (!e.isControlDown()) {
                        cancelSorting();
                    }
                    // Cycle the sorting states through {NOT_SORTED, ASCENDING, DESCENDING} or
                    // {NOT_SORTED, DESCENDING, ASCENDING} depending on whether shift is pressed.
                    
                    //SATEC (No permitimos sin ordenar). Ver si puede afectar en algo
                    if (status==-1)
                    	status=1;
                    else if (status==1)
                    	status=-1;
                    else
                    	status=1;
                    	
                    /*status = status + (e.isShiftDown() ? -1 : 1);
                    status = (status + 4) % 3 - 1; // signed mod, returning {-1, 0, 1}*/
                     
                    setSortingStatus(column, status);
                }
            }
        }

        private static class Arrow implements Icon {
            private boolean descending;
            private int size;
            private int priority;

            public Arrow(boolean descending, int size, int priority) {
                this.descending = descending;
                this.size = size;
                this.priority = priority;
            }

            public void paintIcon(Component c, Graphics g, int x, int y) {
                Color color = c == null ? Color.GRAY : c.getBackground();
                // In a compound sort, make each succesive triangle 20%
                // smaller than the previous one.
                int dx = (int)(size/2*Math.pow(0.8, priority));
                int dy = descending ? dx : -dx;
                // Align icon (roughly) with font baseline.
                y = y + 5*size/6 + (descending ? -dy : 0);
                int shift = descending ? 1 : -1;
                g.translate(x, y);

                // Right diagonal.
                g.setColor(color.darker());
                g.drawLine(dx / 2, dy, 0, 0);
                g.drawLine(dx / 2, dy + shift, 0, shift);

                // Left diagonal.
                g.setColor(color.brighter());
                g.drawLine(dx / 2, dy, dx, 0);
                g.drawLine(dx / 2, dy + shift, dx, shift);

                // Horizontal line.
                if (descending) {
                    g.setColor(color.darker().darker());
                } else {
                    g.setColor(color.brighter().brighter());
                }
                g.drawLine(dx, 0, 0, 0);

                g.setColor(color);
                g.translate(-x, -y);
            }

            public int getIconWidth() {
                return size;
            }

            public int getIconHeight() {
                return size;
            }
        }

        private class SortableHeaderRenderer implements TableCellRenderer {
            private TableCellRenderer tableCellRenderer;

            public SortableHeaderRenderer(TableCellRenderer tableCellRenderer) {
                this.tableCellRenderer = tableCellRenderer;
            }

            public Component getTableCellRendererComponent(JTable table,
                                                           Object value,
                                                           boolean isSelected,
                                                           boolean hasFocus,
                                                           int row,
                                                           int column) {
                Component c = tableCellRenderer.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    JLabel l = (JLabel) c;
                    l.setHorizontalTextPosition(JLabel.LEFT);
                    int modelColumn = table.convertColumnIndexToModel(column);
                    l.setIcon(getHeaderRendererIcon(modelColumn, l.getFont().getSize()));
                }
                return c;
            }
        }

        private static class Directive {
            private int column;
            private int direction;

            public Directive(int column, int direction) {
                this.column = column;
                this.direction = direction;
            }
        }

		private boolean isDisableSorting() {
			return disableSorting;
		}

		private void setDisableSorting(boolean disableSorting) {
			this.disableSorting = disableSorting;
		}
		
	
		
    }