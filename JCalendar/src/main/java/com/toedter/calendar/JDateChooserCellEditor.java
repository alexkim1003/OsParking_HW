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
package com.toedter.calendar;

import java.awt.Component;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * A CellEditor for tables, using a JDateChooser.
 * 
 * @author Kai Toedter
 * @version $LastChangedRevision: 100 $
 * @version $LastChangedDate: 2006-06-04 14:36:06 +0200 (So, 04 Jun 2006) $
 */
public class JDateChooserCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = 917881575221755609L;

	private JDateChooser dateChooser = new JDateChooser();

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		Date date = null;
		if (value instanceof Date)
			date = (Date) value;

		dateChooser.setDate(date);

		return dateChooser;
	}

	public Object getCellEditorValue() {
		return dateChooser.getDate();
	}
}