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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.cambi.research.funding.application.AppConfiguration;
import it.cambi.research.funding.application.Application;
import it.cambi.research.funding.dto.OpportunityPropertyDto;
import it.cambi.research.funding.enums.ComparisonOperatorEnum;
import it.cambi.research.funding.enums.LogicalOperatorEnum;
import it.cambi.research.funding.operator.CriteriaOperator;
import it.cambi.research.funding.opportunity.FundingOpportunity;
import it.cambi.research.funding.service.CriteriaServiceImpl;

/**
 * @author luca
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class, AppConfiguration.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class CriteriaTest {

	@Autowired
	private CriteriaServiceImpl criteriaService;

	@SuppressWarnings("serial")
	private FundingOpportunity fundingOpportunity = new FundingOpportunity(new HashMap<String, String>() {
		{
			put("name", "Healthcare technology research");
			put("amount", "10000");
			put("deadline", "20191020");
		}
	});

	@Test
	@Order(1)
	public void criteriaTest1() {

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
				add(new CriteriaOperator(LogicalOperatorEnum.AND, new ArrayList<OpportunityPropertyDto>() {
					{
						add(opportunityProperty);
						add(opportunityProperty1);

					}
				}));
				add(new CriteriaOperator(LogicalOperatorEnum.OR, new ArrayList<OpportunityPropertyDto>() {
					{
						add(opportunityProperty2);

					}
				}));
			}
		};

		assertEquals(true, criteriaService.matches(criteriaOperators, fundingOpportunity));

	}

	@Test
	@Order(2)
	public void criteriaTest2() {

		OpportunityPropertyDto opportunityProperty = new OpportunityPropertyDto.Builder()
				.withComparisonOperator(ComparisonOperatorEnum.ISPRESENT).withPropertyName("nam").build();

		OpportunityPropertyDto opportunityProperty1 = new OpportunityPropertyDto.Builder()
				.withComparisonOperator(ComparisonOperatorEnum.EQUALS).withPropertyName("amount").withValue("90000")
				.build();

		OpportunityPropertyDto opportunityProperty2 = new OpportunityPropertyDto.Builder()
				.withComparisonOperator(ComparisonOperatorEnum.EQUALS).withPropertyName("deadline")
				.withValue("20191020").build();

		@SuppressWarnings("serial")
		List<CriteriaOperator> criteriaOperators = new ArrayList<CriteriaOperator>() {
			{
				add(new CriteriaOperator(LogicalOperatorEnum.OR, new ArrayList<OpportunityPropertyDto>() {
					{
						add(opportunityProperty);
						add(opportunityProperty1);

					}
				}));
				add(new CriteriaOperator(LogicalOperatorEnum.AND, new ArrayList<OpportunityPropertyDto>() {
					{
						add(opportunityProperty2);

					}
				}));
			}
		};

		assertEquals(false, criteriaService.matches(criteriaOperators, fundingOpportunity));

	}

	@Test
	public void testBoolean() {

		assertEquals(true, true || false || false);
		assertEquals(true, (true || false || false) && true);
		assertEquals(false, (true || false || false) && false);
		assertEquals(false, !(true || false || false) && true);

	}
}
