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

import java.awt.Color;
import java.util.Date;

public class MinMaxDateEvaluator implements IDateEvaluator {

	private DateUtil dateUtil = new DateUtil();
	
	public boolean isSpecial(Date date) {
		return false;
	}

	public Color getSpecialForegroundColor() {
		return null;
	}

	public Color getSpecialBackroundColor() {
		return null;
	}

	public String getSpecialTooltip() {
		return null;
	}

	public boolean isInvalid(Date date) {
		return !dateUtil.checkDate(date);
	}

	public Color getInvalidForegroundColor() {
		return null;
	}

	public Color getInvalidBackroundColor() {
		return null;
	}

	public String getInvalidTooltip() {
		return null;
	}

	/**
	 * Sets the maximum selectable date. If null, the date 01\01\9999 will be
	 * set instead.
	 * 
	 * @param max
	 *            the maximum selectable date
	 * 
	 * @return the maximum selectable date
	 */
	public Date setMaxSelectableDate(Date max) {
		return dateUtil.setMaxSelectableDate(max);
	}

	/**
	 * Sets the minimum selectable date. If null, the date 01\01\0001 will be
	 * set instead.
	 * 
	 * @param min
	 *            the minimum selectable date
	 * 
	 * @return the minimum selectable date
	 */
	public Date setMinSelectableDate(Date min) {
		return dateUtil.setMinSelectableDate(min);
	}

	/**
	 * Gets the maximum selectable date.
	 * 
	 * @return the maximum selectable date
	 */
	public Date getMaxSelectableDate() {
		return dateUtil.getMaxSelectableDate();
	}

	/**
	 * Gets the minimum selectable date.
	 * 
	 * @return the minimum selectable date
	 */
	public Date getMinSelectableDate() {
		return dateUtil.getMinSelectableDate();
	}

}
