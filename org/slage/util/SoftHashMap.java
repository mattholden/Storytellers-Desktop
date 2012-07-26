package org.slage.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SoftHashMap<K, V> extends AbstractMap<K, V> {
	/** The internal HashMap that will hold the SoftReference. */
	private final Map<Object, SoftValue<K, V>> hash;
	/** Reference queue for cleared SoftReference objects. */
	private final ReferenceQueue queue = new ReferenceQueue();

	public SoftHashMap() {
		hash = new HashMap<Object, SoftValue<K, V>>();
	}

	@Override
	public V get(Object key) {
		V result = null;
		// We get the SoftReference represented by that key
		SoftValue<K, V> soft_ref = hash.get(key);
		if (soft_ref != null) {
			// From the SoftReference we get the value, which can be
			// null if it was not in the map, or it was removed in
			// the processQueue() method defined below
			result = soft_ref.get();
			if (result == null) {
				// If the value has been garbage collected, remove the
				// entry from the HashMap.
				hash.remove(key);
			}
		}
		return result;
	}

	/**
	 * We define our own subclass of SoftReference which contains not only the
	 * value but also the key to make it easier to find the entry in the HashMap
	 * after it's been garbage collected.
	 */
	private static class SoftValue<Ka, Va> extends SoftReference<Va> {
		@SuppressWarnings("unused")
		private final Ka key; // always make data member final

		/**
		 * Did you know that an outer class can access private data members and
		 * methods of an inner class? I didn't know that! I thought it was only the
		 * inner class who could access the outer class's private information. An
		 * outer class can also access private members of an inner class inside its
		 * inner class.
		 */
		private SoftValue(Ka aKey, Va aValue, ReferenceQueue q) {
			super(aValue, q);
			this.key = aKey;
		}
	}

	/**
	 * Here we go through the ReferenceQueue and remove garbage collected
	 * SoftValue objects from the HashMap by looking them up using the
	 * SoftValue.key data member.
	 */
	private void processQueue() {
		SoftValue sv;
		while ((sv = (SoftValue) queue.poll()) != null) {
			hash.remove(sv.key);
		}
	}

	/**
	 * Here we put the key, value pair into the HashMap using a SoftValue object.
	 */
	@Override
	public V put(K key, V value) {
		processQueue(); // throw out garbage collected values first
		hash.put(key, new SoftValue(key, value, queue));
		return value;
	}

	@Override
	public V remove(Object key) {
		processQueue(); // throw out garbage collected values first
		return hash.remove(key).get();
	}

	@Override
	public void clear() {
		processQueue(); // throw out garbage collected values
		hash.clear();
	}

	@Override
	public int size() {
		processQueue(); // throw out garbage collected values first
		return hash.size();
	}

	@Override
	public Set entrySet() {
		// no, no, you may NOT do that!!! GRRR
		throw new UnsupportedOperationException();
	}
}