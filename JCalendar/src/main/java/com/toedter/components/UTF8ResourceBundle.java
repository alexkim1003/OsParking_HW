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
package com.toedter.components;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * This class is a hack to read UTF-8 encoded property files. The implementation
 * is based on http://www.thoughtsabout.net/blog/archives/000044.html
 * 
 * @author Kai Toedter
 * 
 */
public abstract class UTF8ResourceBundle {

	public static final ResourceBundle getBundle(String baseName, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
		if (!(bundle instanceof PropertyResourceBundle))
			return bundle;

		return new UTF8PropertyResourceBundle((PropertyResourceBundle) bundle);
	}

	private static class UTF8PropertyResourceBundle extends ResourceBundle {
		PropertyResourceBundle propertyResourceBundle;

		private UTF8PropertyResourceBundle(PropertyResourceBundle bundle) {
			this.propertyResourceBundle = bundle;
		}

		public Enumeration getKeys() {
			return propertyResourceBundle.getKeys();
		}

		protected Object handleGetObject(String key) {
			String value = (String) propertyResourceBundle.handleGetObject(key);
			if (value != null) {
				try {
					return new String(value.getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException exception) {
					throw new RuntimeException(
							"UTF-8 encoding is not supported.", exception);
				}
			}
			return null;
		}
	}
}
