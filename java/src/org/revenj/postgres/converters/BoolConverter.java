package org.revenj.postgres.converters;

import org.revenj.postgres.PostgresReader;
import org.revenj.postgres.PostgresWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BoolConverter {

	public static int serializeURI(char[] buf, int pos, Boolean value) {
		if (value == null) return pos;
		if (value) {
			buf[pos] = 't';
			buf[pos + 1] = 'r';
			buf[pos + 2] = 'u';
			buf[pos + 3] = 'e';
			return pos + 4;
		} else {
			buf[pos] = 'f';
			buf[pos + 1] = 'a';
			buf[pos + 2] = 'l';
			buf[pos + 3] = 's';
			buf[pos + 4] = 'e';
			return pos + 5;
		}
	}

	public static Boolean parseNullable(PostgresReader reader) {
		int cur = reader.read();
		if (cur == ',' || cur == ')') {
			return null;
		}
		reader.read();
		return cur == 't';
	}

	public static boolean parse(PostgresReader reader) {
		int cur = reader.read();
		if (cur == ',' || cur == ')') {
			return false;
		}
		reader.read();
		return cur == 't';
	}

	public static List<Boolean> parseCollection(PostgresReader reader, int context, boolean allowNulls) {
		int cur = reader.read();
		if (cur == ',' || cur == ')') {
			return null;
		}
		boolean espaced = cur != '{';
		if (espaced) {
			reader.read(context);
		}
		List<Boolean> list = new ArrayList<>();
		cur = reader.peek();
		if (cur == '}') {
			reader.read();
		}
		Boolean defaultValue = allowNulls ? null : false;
		while (cur != -1 && cur != '}') {
			cur = reader.read();
			if (cur == 't') {
				list.add(true);
			} else if (cur == 'f') {
				list.add(false);
			} else {
				reader.read(3);
				list.add(defaultValue);
			}
			cur = reader.read();
		}
		if (espaced) {
			reader.read(context + 1);
		} else {
			reader.read();
		}
		return list;
	}

	public static PostgresTuple toTuple(Boolean value) {
		if (value == null) return null;
		return new BoolTuple(value);
	}

	public static PostgresTuple toTuple(boolean value) {
		return new BoolTuple(value);
	}

	static class BoolTuple extends PostgresTuple {
		private final char value;

		public BoolTuple(boolean value) {
			this.value = value ? 't' : 'f';
		}

		public boolean mustEscapeRecord() {
			return false;
		}

		public boolean mustEscapeArray() {
			return false;
		}

		public void insertRecord(PostgresWriter sw, String escaping, Mapping mappings) {
			sw.write(value);
		}

		public void insertArray(PostgresWriter sw, String escaping, Mapping mappings) {
			sw.write(value);
		}

		public String buildTuple(boolean quote) {
			return quote ? "'" + value + "'" : Character.toString(value);
		}
	}
}
