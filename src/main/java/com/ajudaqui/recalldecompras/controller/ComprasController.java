package com.ajudaqui.recalldecompras.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class ComprasController {

	@GetMapping
	public String teste() {
		System.err.println("Chamou!");
		return "Apareceu??";
	}
	@GetMapping("/teste")
	public String teste2() {
		System.err.println("Chamou o teste?");
		return "Apareceu algo ai??";
	}

}
