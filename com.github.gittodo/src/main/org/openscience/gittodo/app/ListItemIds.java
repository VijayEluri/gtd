/*
 * Copyright 2008  Egon Willighagen <egonw@users.sf.net>
 * 
 * License: LGPL v3
 */
package org.openscience.gittodo.app;

import org.openscience.gittodo.model.IGTDRepository;
import org.openscience.gittodo.model.Item;
import org.openscience.gittodo.model.Repository;

public class ListItemIds {
	
	public static void main(String[] args) {
		IGTDRepository repos = new Repository();
		for (Item item : repos.items().values()) {
			System.out.println(item.hashCode());
		}
	}
	
}
