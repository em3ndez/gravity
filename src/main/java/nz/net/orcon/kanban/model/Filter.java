/**
 * GRAVITY WORKFLOW AUTOMATION
 * (C) Copyright 2015 Orcon Limited
 * 
 * This file is part of Gravity Workflow Automation.
 *
 * Gravity Workflow Automation is free software: you can redistribute it 
 * and/or modify it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * Gravity Workflow Automation is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *    
 * You should have received a copy of the GNU General Public License
 * along with Gravity Workflow Automation.  
 * If not, see <http://www.gnu.org/licenses/>. 
 */

package nz.net.orcon.kanban.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.jackrabbit.ocm.manager.enumconverter.EnumTypeConverter;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Collection;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;

@Node
public class Filter extends AbstractNamedModelClass implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Field
	private String expression;
	
	@Field
	private String owner;
	
	@Field(converter=EnumTypeConverter.class)
	private AccessType access;
	
	@Collection(jcrMandatory=false)
	private Map<String,Condition> conditions;

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	public void setConditions(Map<String,Condition> conditions) {
		this.conditions = conditions;
	}

	public Map<String,Condition> getConditions() {
		return conditions;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setAccess(AccessType access) {
		this.access = access;
	}

	public AccessType getAccess() {
		return access;
	}	
}
