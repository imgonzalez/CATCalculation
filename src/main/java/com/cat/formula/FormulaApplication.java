package com.cat.formula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FormulaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormulaApplication.class, args);
	}

	Double amount;
	Integer payments;
	Double monthlyPayment;
	Double commission;
	Integer annualPeriods;

	@GetMapping("/getCAT")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name,
						   @RequestParam(value = "amount") Double amount,
						   @RequestParam(value = "monthlyPayment") Double monthlyPayment,
						   @RequestParam(value = "commission") Double commission,
						   @RequestParam(value = "payments") Integer payments,
						   @RequestParam(value = "annualPeriods") Integer annualPeriods) {

		final CATCalculation catData = new CATCalculation(amount, payments, monthlyPayment, commission, annualPeriods);

		String catCalculation = catData.getCat();

		return String.format("El CAT es %s!", catCalculation);
	}
}
