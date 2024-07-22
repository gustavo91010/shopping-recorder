package com.ajudaqui.recalldecompras.utils;

import java.lang.reflect.Field;

import com.ajudaqui.recalldecompras.exception.MsgException;

public class Validation {
	public static <T> void isPresencialValues(T object) {
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String fieldName = field.getName();
			try {
				if (field.get(object) == null || field.get(object).toString().isEmpty()) {
					String msg = String.format("O campo %s não pode estar vazio", fieldName);
					throw new MsgException(msg);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
