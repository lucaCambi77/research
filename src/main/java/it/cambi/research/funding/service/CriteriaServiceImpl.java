/**i
 * 
 */
package it.cambi.research.funding.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import it.cambi.research.funding.criteria.Criteria;
import it.cambi.research.funding.dto.OpportunityPropertyDto;
import it.cambi.research.funding.enums.LogicalOperatorEnum;
import it.cambi.research.funding.operator.CriteriaOperator;
import it.cambi.research.funding.opportunity.FundingOpportunity;
import it.cambi.research.funding.opportunity.OpportunityProperty;

/**
 * @author luca
 *
 */
@Service
public class CriteriaServiceImpl {

	public boolean matches(List<CriteriaOperator> criteriaOperators, FundingOpportunity fundingOpportunity) {

		Map<LogicalOperatorEnum, List<Boolean>> result = new HashMap<LogicalOperatorEnum, List<Boolean>>();

		for (CriteriaOperator criteriaOperator : criteriaOperators) {

			List<Boolean> criteriaOperatorToMatch = result.getOrDefault(criteriaOperator.getLogicalOperator(),
					new ArrayList<Boolean>());

			for (OpportunityPropertyDto opportunity : criteriaOperator.getOpportunityProperty())
				criteriaOperatorToMatch.add(matchesCriteria(opportunity, fundingOpportunity));

			result.put(criteriaOperator.getLogicalOperator(), criteriaOperatorToMatch);

		}

		return areCriteriaMatched(result);

	}

	/**
	 * @param result
	 * @return
	 */
	private boolean areCriteriaMatched(Map<LogicalOperatorEnum, List<Boolean>> result) {

		boolean isMatched = true;
		for (Entry<LogicalOperatorEnum, List<Boolean>> element : result.entrySet()) {

			switch (element.getKey()) {
			/**
			 * If at least one OR condition is true, we return true because is dominant over
			 * other conditions
			 */
			case OR:

				boolean isMatchedTmp = element.getValue().stream().filter(e -> e == true).count() > 0 ? true : false;

				if (isMatchedTmp)
					return true;

				isMatched = isMatched && false;
				
				break;

			case NOT:

				for (Boolean match : element.getValue())
					isMatched = isMatched && !match;

				break;

			default:

				for (Boolean match : element.getValue())
					isMatched = isMatched && match;

				break;
			}
		}
		return isMatched;
	}

	public boolean matchesCriteria(OpportunityPropertyDto opportunityDto, FundingOpportunity fundingOpportunity) {

		Criteria<String> criteria = new Criteria<String>();

		OpportunityProperty<String> opportunityProperty = new OpportunityProperty.Builder<String>()
				.withComparisonOperator(opportunityDto.getComparisonOperator())
				.withPropertyName(opportunityDto.getPropertyName()).withValue(opportunityDto.getValue())
				.withClass(String.class).build();

		return criteria.setOpportunityProperty(opportunityProperty).matches(fundingOpportunity);
	}
}
