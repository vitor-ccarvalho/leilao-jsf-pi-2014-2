package model;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class Key implements Comparable, Serializable {
	private static final long serialVersionUID = 1L;
	private Integer value;

	public Key(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public int compareTo(Object o) {
		if (o == null || !(o instanceof Key)) {
			return 1;
		}

		Key other = (Key) o;

		if (value < other.value) {
			return -1;
		} else if (value == other.value) {
			return 0;
		}

		return 1;
	}

	@Override
	public String toString() {
		return value.toString();
	}

}