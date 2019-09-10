/**
 * 
 */
package it.cambi.research.funding.dto;

import java.util.List;

import it.cambi.research.funding.operator.CriteriaOperator;
import it.cambi.research.funding.opportunity.FundingOpportunity;

/**
 * @author luca
 *
 */
public class FundingOpportunityDto {

	private FundingOpportunity fundinOpportunity;
	private List<CriteriaOperator> criteriaOperator;

	public FundingOpportunity getFundinOpportunity() {
		return fundinOpportunity;
	}

	public void setFundinOpportunity(FundingOpportunity fundinOpportunity) {
		this.fundinOpportunity = fundinOpportunity;
	}

	public List<CriteriaOperator> getCriteriaOperator() {
		return criteriaOperator;
	}

	public void setCriteriaOperator(List<CriteriaOperator> criteriaOperator) {
		this.criteriaOperator = criteriaOperator;
	}

}
