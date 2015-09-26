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

package nz.net.orcon.kanban.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Scheduled;

abstract public class CacheImpl<T> implements Cache<T> {

	private Map<String, T> cacheMap = new ConcurrentHashMap<String,T>();
	
	private Map<String,String> cacheList = null;
		
	@Override
	public void invalidate(String itemId) {
		this.cacheMap.remove(itemId);
		this.cacheList = null;
	}

	@Override
	public T getItem(String itemId) throws Exception {
		T item = cacheMap.get(itemId);
		if( item!=null){
			return item;
		}
		
		item = getFromStore(itemId);

		if(item==null){
			throw new ResourceNotFoundException();
		}

		this.cacheMap.put(itemId, item);
		return item;
	}
	
	public Map<String,String> list() throws Exception{
		if( this.cacheList == null){
			this.cacheList = this.getListFromStore();
		}
		return new HashMap<String,String>(this.cacheList);
	}

	@Override
	public void storeItem(String itemId, T item) throws Exception {
		this.cacheMap.put(itemId, item);
	}
	
	@Scheduled(cron = "${cache.clearschedule}")	
	public void clearCache() {
		this.cacheMap.clear();
		this.cacheList = null;
	}
	
	abstract protected T getFromStore( String itemId ) throws Exception;
	
	abstract protected Map<String,String> getListFromStore() throws Exception;
}