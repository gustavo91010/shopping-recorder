package com.ajudaqui.recalldecompras.utils.enuns;

public enum EMeasurementUnit {
	KG("Quilograma"), G("Grama"), MG("Miligrama"), M("Metro"), CM("Centímetro"), MM("Milímetro"), L("Litro"),
	ML("Mililitro");

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

}
