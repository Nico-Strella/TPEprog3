/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramacionIII.util;

import java.util.Iterator;

public class MySimpleLinkedList implements Iterable<Object> {

	protected Node first;
	protected int nodeCount;
	protected Node cursor;

	public class MyIterator implements Iterator<Object> {

		private Node cursor;

		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public Object next() {
			Node tmp = cursor;
			cursor = cursor.getNext();
			return tmp.getInfo();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
		}

		private MyIterator(Node first) {
			cursor = first;
		}
	}
	
	public MySimpleLinkedList() {
		first = null;
		nodeCount = 0;
	}

	public void insert(Object o) {
		Node tmp = new Node(o, null);
		tmp.setNext(first);
		first = tmp;
		nodeCount++;
	}

	public Object extract() {
		Node tmp = first;
		first = first.getNext();

		nodeCount--;
		return tmp.getInfo();
	}

	public Object first() {
		return first.getInfo();
	}

	public void print(int n) {
		Node tmp = first;
		for(int i=1; i<n; i++)
			tmp = tmp.getNext();

		System.out.println(tmp.getInfo());
	}

	public boolean isEmpty() {
		return (nodeCount == 0);
	}

	public int size() {
		return nodeCount;
	}

	public void resetCursor() {
		cursor = first;
	}

	public Object get() {
		Object element = cursor.getInfo();
		cursor = cursor.getNext();
		return element;
	}

	public boolean cursorHasNext() {
		return cursor != null;
	}

	@Override
	public MyIterator iterator() {
		return new MyIterator(first);
	}
	
	public int indexOf(Object o) {
		int index = -1;
		Node tmp = first;
		int current = 0;
		while ((tmp != null) && (index == -1)) {
			if (tmp.getInfo().equals(o))
				index = current;
			current++;
		}

		return index;
	}

	public boolean removeAt(int index) {
		boolean canRemove = false;
		if ((index >= 0) && (index <= this.size() - 1)) {
			canRemove = true;
			if (index == 0) {
				Node tmp = first;
				first = first.getNext();
				tmp.setNext(null);
			} else { 
				Node tmp = first;
				int current = 0;
				while (current < index - 1) { 
					tmp = tmp.getNext();
					current++;
				}
				
				Node remove = tmp.getNext();
				tmp.setNext(remove.getNext());
				remove.setNext(null);
			}
			nodeCount--;
		}
		return canRemove;
	}
}
