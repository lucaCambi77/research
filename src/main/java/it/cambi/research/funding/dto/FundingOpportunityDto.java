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

	private FundingOpportunity fundingOpportunity;
	private List<CriteriaOperator> criteriaOperator;

	public FundingOpportunity getFundingOpportunity() {
		return fundingOpportunity;
	}

	public void setFundingOpportunity(FundingOpportunity fundinOpportunity) {
		this.fundingOpportunity = fundinOpportunity;
	}

	public List<CriteriaOperator> getCriteriaOperator() {
		return criteriaOperator;
	}

	public void setCriteriaOperator(List<CriteriaOperator> criteriaOperator) {
		this.criteriaOperator = criteriaOperator;
	}

}
