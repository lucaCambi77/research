/**
 * 
 */
package it.cambi.research.funding.criteria;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import it.cambi.research.funding.opportunity.FundingOpportunity;
import it.cambi.research.funding.opportunity.OpportunityProperty;

/**
 * @author luca
 *
 */
public class Criteria<T> {

	private OpportunityProperty<T> opportunityProperty;

	public Criteria<T> setOpportunityProperty(OpportunityProperty<T> opportunityProperty) {
		this.opportunityProperty = opportunityProperty;
		return this;
	}

	public boolean matches(FundingOpportunity fundingOpportunity) {

		Map<String, String> fundingOpportunityMap = fundingOpportunity.getFundingOpportunity();

		return matches(fundingOpportunityMap, opportunityProperty);

	}

	/**
	 * Predicates are created based on comparison operator and used to apply filter
	 * funding opportunity map. We can add operator here to create more conditions
	 * 
	 * @param fundingOpportunityMap
	 * @param opportunityProperty
	 * @return
	 */
	public boolean matches(Map<String, String> fundingOpportunityMap, OpportunityProperty<?> opportunityProperty) {

		Predicate<Entry<String, String>> predicate = null;
		String opportunityValue = opportunityProperty.getValue() == null ? ""
				: opportunityProperty.getValue().toString();

		switch (opportunityProperty.getComparisonOperator()) {
		case EQUALS:

			predicate = x -> x.getKey().equals(opportunityProperty.getPropertyName())
					&& (opportunityValue.equals(fundingOpportunityMap.get(opportunityProperty.getPropertyName())));

			break;

		case GT:
			predicate = x -> x.getKey().equals(opportunityProperty.getPropertyName())
					&& opportunityValue.compareTo(fundingOpportunityMap.get(x.getKey())) > 0;
			break;

		case LT:
			predicate = x -> x.getKey().equals(opportunityProperty.getPropertyName())
					&& opportunityValue.compareTo(fundingOpportunityMap.get(x.getKey())) < 0;
			break;

		default:
			predicate = x -> x.getKey().equals(opportunityProperty.getPropertyName());
			break;
		}

		return fundingOpportunityMap.entrySet().stream().filter(predicate).count() > 0 ? true : false;

	}

}
