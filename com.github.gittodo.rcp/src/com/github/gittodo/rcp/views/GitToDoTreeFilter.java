/*
 * Copyright 2008  Egon Willighagen <egonw@users.sf.net>
 *
 * License: LGPL v3
 */
package com.github.gittodo.rcp.views;

import org.openscience.gittodo.model.Item;
import org.openscience.gittodo.model.Item.BOX;

public class GitToDoTreeFilter {

    private Item.CONTEXT contextFilter;
    private Item.BOX     box;
    
    public GitToDoTreeFilter(BOX box) {
		this.box = box;
	}

	public Item.CONTEXT getContextFilter() {
    
        return contextFilter;
    }

    
    public void setContextFilter( Item.CONTEXT contextFilter ) {
    
        this.contextFilter = contextFilter;
    }

    
    public Item.PRIORITY getPriorityFilter() {
    
        return priorityFilter;
    }

    
    public void setPriorityFilter( Item.PRIORITY priorityFilter ) {
    
        this.priorityFilter = priorityFilter;
    }

    
    public String getSubstringFilter() {
    
        return substringFilter;
    }

    
    public void setSubstringFilter( String substringFilter ) {
    
        this.substringFilter = substringFilter;
    }

    
    public String getProjectFilter() {
    
        return projectFilter;
    }

    
    public void setProjectFilter( String projectFilter ) {
    
        this.projectFilter = projectFilter;
    }

    private Item.PRIORITY priorityFilter;
    private String substringFilter;
    private String projectFilter;
    
    public void reset() {
        contextFilter = null;
        priorityFilter = null;
        substringFilter = null;
        projectFilter = null;
        isCaseSenstive = true;
    }

    private boolean isCaseSenstive = false; // default match case insensitive 
	private boolean withDeadline;
    
    public boolean matches(Item item) {
        if (item.getState() == Item.STATE.CLOSED) return false;
        if (item.getBox() != this.box) return false;
        
        if (withDeadline()) {
        	if (item.getDeadline() == null) return false;
        }

        if (contextFilter != null && item.getContext() != null &&
            item.getContext() != contextFilter) return false;
        if (contextFilter != null && item.getContext() == null) return false;
        if (priorityFilter != null && item.getPriority() != null &&
                item.getPriority() != priorityFilter) return false;
        if (substringFilter != null && substringFilter.length() > 0) {
        	if (item.getText() != null) {
        		if (isCaseSenstive) {
        			if (!item.getText().contains(substringFilter)) return false;
        		} else {
        			if (!item.getText().toLowerCase().contains(substringFilter.toLowerCase()))
        				return false;
        		}        			
        	}
        }
        if (projectFilter != null && projectFilter.length() > 0) {
        	if (item.getProject() != null) {
        		if (isCaseSenstive) {
        			if (!item.getProject().contains(projectFilter)) return false;
        		} else {
        			if (!item.getProject().toLowerCase().contains(projectFilter.toLowerCase()))
        				return false;
        		}        			
        	}
        }
        return true;
    }


	public void setCaseSenstive(boolean isCaseSenstive) {
		this.isCaseSenstive = isCaseSenstive;
	}


	public boolean isCaseSenstive() {
		return isCaseSenstive;
	}

	public void setWithDeadline(boolean withDeadline) {
		this.withDeadline = withDeadline;
	}

	public boolean withDeadline() {
		return this.withDeadline;
	}
}
