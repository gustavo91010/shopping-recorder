package com.ajudaqui.recalldecompras.utils.enuns;

import com.ajudaqui.recalldecompras.exception.MsgException;

public enum EMeasurementUnit {
	KG("Quilograma"), G("Grama"), MG("Miligrama"), M("Metro"), CM("Centímetro"), MM("Milímetro"), L("Litro"),
	ML("Mililitro"),U("Unidade");

	private final String descricao;

	EMeasurementUnit(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}

	public static String findByName(String name) {
		try {
			return EMeasurementUnit.valueOf(name.toUpperCase()).toString();
		} catch (IllegalArgumentException e) {
			throw new MsgException("O tipo selecionado não está registrado, tente outro.");
		}
	}
}
