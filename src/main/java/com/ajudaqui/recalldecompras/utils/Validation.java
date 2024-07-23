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
	
	public static <T,U> T update(T myObject, U objectUpdate) {
		Field[] myFields = myObject.getClass().getDeclaredFields();
		Field[] updateFields = objectUpdate.getClass().getDeclaredFields();
		
		
		for (Field updateField : updateFields) {
			updateField.setAccessible(true);
			try {
	            Object atribute = updateField.get(objectUpdate);
				if (atribute != null && !atribute.toString().isEmpty()) {
					for (Field myField : myFields) {

						if(updateField.getName().equals(myField.getName())) {
							myField.setAccessible(true);
							myField.set(myObject, atribute);//ele pega meu atributo, define classe e o valor
						}
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return myObject;
	}
}
