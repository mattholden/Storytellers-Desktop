/*
 * Created on Nov 11, 2005
 *
 */
package org.slage.util;

import java.util.LinkedList;

public class SoftCachingMap<K, V> extends SoftHashMap<K, V> {
	private int hardSize;

	/** The FIFO list of hard references, order of last access. */
	private final LinkedList hardCache = new LinkedList();

	public SoftCachingMap() {
		this(10);
	}

	public SoftCachingMap(int aLimit) {
		setHardCacheSize(aLimit);
	}
	
	public void setHardCacheSize(int aLimit){
		if(aLimit < 0){
			aLimit = 0;
		}
		hardSize = aLimit;
	}
	
	public int getHardCacheSize(){
		return hardSize;
	}

	@Override
	public V get(Object aKey) {
		V result = super.get(aKey);
		// We now add this object to the beginning of the hard
		// reference queue. One reference can occur more than
		// once, because lookups of the FIFO queue are slow, so
		// we don't want to search through it each time to remove
		// duplicates.
		if (result != null) {
			hardCache.addFirst(result);
			while (hardCache.size() > hardSize) {
				// Remove the last entry if list longer than hardSize
				hardCache.removeLast();
			}
		}
		return result;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
		hardCache.clear();
	}
	public V remove(Object aKey){
		hardCache.remove(aKey);
		return super.remove(aKey);
	}
}
