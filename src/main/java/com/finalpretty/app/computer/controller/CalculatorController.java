package com.finalpretty.app.computer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculatorController {

	@GetMapping("/metabolic")
	public String metabolic() {
		return "computer/calculator";
	}
}
