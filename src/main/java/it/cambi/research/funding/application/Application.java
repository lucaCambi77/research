package it.cambi.research.funding.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.cambi.research.funding.dto.FundingOpportunityDto;
import it.cambi.research.funding.service.CriteriaServiceImpl;

@SpringBootApplication
@RestController
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CriteriaServiceImpl criteriaService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String order(@RequestBody FundingOpportunityDto fundingOpportunityDto) throws JsonProcessingException {

		log.info("... new search request");

		String response = objectMapper.writeValueAsString(criteriaService
				.matches(fundingOpportunityDto.getCriteriaOperator(), fundingOpportunityDto.getFundinOpportunity()));

		return response;

	}

	@GetMapping("/")
	public String home() {
		return "Hello World";
	}
}
