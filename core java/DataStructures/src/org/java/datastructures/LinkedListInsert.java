package org.java.datastructures;

public class LinkedListInsert {

	public String data;
	public LinkedListInsert next;

	public LinkedListInsert(String data, LinkedListInsert next) {
		super();
		this.data = data;
		this.next = null;
	}

	public LinkedListInsert() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return data;

	}

	public static void main(String[] args) {

		LinkedListInsert name = new LinkedListInsert();
		LinkedListInsert firstNode = new LinkedListInsert("25", null);

		LinkedListInsert second = new LinkedListInsert("2", null);
		LinkedListInsert third = new LinkedListInsert("3", null);
		firstNode.next = second;
		// firstNode.data.next = second;
		second.next = third;
		System.out.println(firstNode + "--->" + second + "--->" + third);

	}

}
