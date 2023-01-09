package com.collection.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
	
	public static void main(String[] args) {
		
		Map<Integer, String> map= new HashMap<Integer, String>();
		
		map.put(1, "Rohit");
		map.put(2, "Sujit");
		map.put(3, "Mohit");
		
		map.entrySet().stream().forEach(i->System.out.println(i.getKey()+" "+i.getValue()));
	}

}
