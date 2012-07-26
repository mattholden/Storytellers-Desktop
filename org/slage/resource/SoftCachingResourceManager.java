/*
 * Created on Nov 12, 2005
 *
 */
package org.slage.resource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slage.util.SoftCachingMap;

public class SoftCachingResourceManager extends AbstractResourceManager implements CachingResourceManager {

	private final SoftCachingMap<String, byte[]> caches;
	private final Map<String, int[]> syncs = new HashMap<String, int[]>();

	public SoftCachingResourceManager() {
		caches = new SoftCachingMap();
	}

	public SoftCachingResourceManager(int aHardCacheSize) {
		if (aHardCacheSize > 0) {
			caches = new SoftCachingMap(aHardCacheSize);
		} else {
			caches = new SoftCachingMap();
		}
	}

	public int getCacheSize() {
		return caches.getHardCacheSize();
	}

	public void setCacheSize(int aLimit) {
		caches.setHardCacheSize(aLimit);
	}

	public byte[] getResource(String aName) throws ResourceException {
		int[] s;
		synchronized (syncs) {
			s = syncs.get(aName);
			if (s == null) {
				s = new int[] {};
				syncs.put(aName, s);
			}
		}
		synchronized (s) {
			try {
				byte[] bytes = caches.get(aName);
				if (bytes == null) {
					bytes = loadResourceIntoMemory(aName);
					caches.put(aName, bytes);
				}
				return bytes;
			} finally {
				syncs.remove(aName);
			}
		}
	}

	public byte[] getResource(File aFile) throws ResourceException {
		int[] s;
		synchronized (syncs) {
			s = syncs.get(aFile.getName());
			if (s == null) {
				s = new int[] {};
				syncs.put(aFile.getName(), s);
			}
		}
		synchronized (s) {
			try {
				byte[] bytes = caches.get(aFile.getName());
				if (bytes == null) {
					bytes = loadFileIntoMemory(aFile);
					caches.put(aFile.getName(), bytes);
				}
				return bytes;
			} finally {
				syncs.remove(aFile.getName());
			}
		}
	}

	public InputStream getResourceAsStream(String aName) throws ResourceException {
		return new ByteArrayInputStream(getResource(aName));
	}

	public InputStream getResourceAsStream(File aFile) throws ResourceException {
		return new ByteArrayInputStream(getResource(aFile));
	}

}
