package com.collection.arraylist;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		
		list.add(100);
		list.add(200);
		list.add(300);
		
		list.stream().forEach(i->System.out.println(i));
		
	}

}
