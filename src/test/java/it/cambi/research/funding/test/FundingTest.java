/**
 * 
 */
package it.cambi.research.funding.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.cambi.research.funding.application.AppConfiguration;
import it.cambi.research.funding.application.Application;
import it.cambi.research.funding.dto.FundingOpportunityDto;
import it.cambi.research.funding.dto.OpportunityPropertyDto;
import it.cambi.research.funding.enums.ComparisonOperatorEnum;
import it.cambi.research.funding.enums.LogicalOperatorEnum;
import it.cambi.research.funding.operator.CriteriaOperator;
import it.cambi.research.funding.opportunity.FundingOpportunity;

/**
 * @author luca
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class, AppConfiguration.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class FundingTest {

	@SuppressWarnings("serial")
	private FundingOpportunity fundingOpportunity = new FundingOpportunity.Builder()
			.withFundingOpportunity(new HashMap<String, String>() {
				{
					put("name", "Healthcare technology research");
					put("amount", "10000");
					put("deadline", "20191020");
				}
			}).build();

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@LocalServerPort
	private int port;

	@Test
	@Order(1)
	public void testRestGreeting() throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	@Order(2)
	public void testRestBakeryNullOrder() throws Exception {

		FundingOpportunityDto fundingOpportunityDto = new FundingOpportunityDto();

		OpportunityPropertyDto opportunityProperty = new OpportunityPropertyDto.Builder()
				.withComparisonOperator(ComparisonOperatorEnum.ISPRESENT).withPropertyName("name").build();

		OpportunityPropertyDto opportunityProperty1 = new OpportunityPropertyDto.Builder()
				.withComparisonOperator(ComparisonOperatorEnum.EQUALS).withPropertyName("amount").withValue("10000")
				.build();

		OpportunityPropertyDto opportunityProperty2 = new OpportunityPropertyDto.Builder()
				.withComparisonOperator(ComparisonOperatorEnum.EQUALS).withPropertyName("deadline")
				.withValue("20191020").build();

		@SuppressWarnings("serial")
		List<CriteriaOperator> criteriaOperators = new ArrayList<CriteriaOperator>() {
			{
				add(new CriteriaOperator.Builder().withLogicalOperator(LogicalOperatorEnum.AND)
						.withOpportunityProperty(new ArrayList<OpportunityPropertyDto>() {
							{
								add(opportunityProperty);
								add(opportunityProperty1);

							}
						}).build());

				add(new CriteriaOperator.Builder().withLogicalOperator(LogicalOperatorEnum.OR)
						.withOpportunityProperty(new ArrayList<OpportunityPropertyDto>() {
							{
								add(opportunityProperty2);

							}
						}).build());
			}
		};

		fundingOpportunityDto.setCriteriaOperator(criteriaOperators);
		fundingOpportunityDto.setFundingOpportunity(fundingOpportunity);

		System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fundingOpportunityDto));
		HttpEntity<FundingOpportunityDto> request = new HttpEntity<FundingOpportunityDto>(fundingOpportunityDto);

		ResponseEntity<Boolean> entity = restTemplate.postForEntity("http://localhost:" + this.port + "/search",
				request, Boolean.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals(true, Boolean.valueOf(entity.getBody()));

	}
}
