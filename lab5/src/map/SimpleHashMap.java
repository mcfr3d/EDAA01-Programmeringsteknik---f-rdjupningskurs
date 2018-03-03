package map;

public class SimpleHashMap<K, V> implements Map<K, V> {

	private Entry<K, V>[] table;
	private int startCapacity;
	private double loadTreshHold;
	private int numKeys;

	public static class Entry<K, V> implements Map.Entry<K, V> {

		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;

		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;

		}

		@Override
		public String toString() {
			return key + " = " + value;
		}
	}

	public SimpleHashMap() {
		startCapacity = 16;
		loadTreshHold = 0.75;
		table = (Entry<K, V>[]) new Entry[startCapacity];
	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			Entry<K,V> entry = table[i];
			sb.append(i + "\t");
			while (entry != null) {
				sb.append(entry + " ");
				entry = entry.next;
			}
			sb.append("\n");
		}
		return sb.toString();}
	
	public SimpleHashMap(int capacity) {
		startCapacity = capacity;
		loadTreshHold = 0.75;
		table = (Entry<K, V>[]) new Entry[startCapacity];
	}

	@Override
	public V get(Object object) {
		K key = (K) object;
		int index = index(key);
		if (table[index] == null) {
			return null;
		}
		if (find(index, key) != null) {
			return find(index, key).value;
		}

		return null;
	}

	// @Override
	// public V get(Object arg0) {
	// K key = (K)arg0;
	// Entry<K,V> e = find(index(key), key);
	// if(e != null) return e.getValue();
	// return null;
	// }

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numKeys == 0;
	}
   
	
	public V put(K key, V value) {
        int i = index(key);
        if(table[i] == null) {
            table[i] = new Entry<K,V>(key, value);
        } else {
            Entry<K,V> e = find(i, key);
            if(e != null) {
                V old_val = e.getValue();
                e.setValue(value);
                return old_val;
            }
            e = table[i];
            while(e.next != null) {
                e = e.next;
            }
            e.next = new Entry<K,V>(key, value);
        }
        numKeys++;
        if (numKeys > (loadTreshHold * table.length)) {
			rehash();
		}
        return null;
    }

	@Override
	public V remove(Object arg0) {
		K key = (K)arg0;
		int i = index(key);
		if(table[i]==null){
			return null;
		}
		if(table[i].key==key){
			V oldValue = table[i].value;
			table[i]=null;
			numKeys--;
			return oldValue;
			
		}
		if( find(i, key)!= null) {
			Entry<K, V> eTemp = table[i];
			Entry<K, V> eTempPrev = null;
			while(!eTemp.key.equals(key)){
				eTempPrev = eTemp;
				eTemp= eTemp.next;
			}
			V oldValue = eTemp.value;
			eTempPrev.next=eTemp.next;
			numKeys--;
			return oldValue;
			}
		
		
		return null;
	}
//	  @Override
//	    public V remove(Object arg0) {
//	        K key = (K)arg0;
//	        int i = index(key);
//	        if(table[i] == null) return null;
//	        else if(table[i].getKey().equals(key)) {
//	            Entry<K,V> e = table[i];
//	            table[i] = e.next;
//	            numKeys--;
//	            return e.getValue();
//	        } else {
//	            Entry<K,V> e_prev = table[i];
//	            Entry<K,V> e_curr = table[i].next;
//	            while(e_curr != null) {
//	                if(e_curr.getKey().equals(key)) {
//	                    e_prev.next = e_curr.next;
//	                    numKeys--;
//	                    return e_curr.getValue();
//	                }
//	                e_prev = e_curr;
//	                e_curr = e_curr.next;
//	            }
//	            return null;
//	        }
//	    }
	
	private void rehash() {
		Entry<K, V>[] oldTable = table;
		table = (Entry<K, V>[]) new Entry[2 * oldTable.length + 1];
		numKeys = 0;
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] != null) {
				put(oldTable[i].key, oldTable[i].value);
				Entry<K, V> temp = oldTable[i];
				while (temp.next != null) {
					put(temp.next.key, temp.next.value);
					temp = temp.next;
				}

			}
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numKeys;
	}

	private int index(K key) {
		return Math.abs(key.hashCode() % table.length);
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> e = table[index];
		while (e != null) {
			if (e.getKey().equals(key)) {
				return e;
			}
			e = e.next;
		}
		return e;
	}

}
