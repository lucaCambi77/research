/**
 * 
 */
package it.cambi.research.funding.operator;

import java.util.List;

import it.cambi.research.funding.dto.OpportunityPropertyDto;
import it.cambi.research.funding.enums.LogicalOperatorEnum;

/**
 * @author luca
 *
 */
public class CriteriaOperator {

	private LogicalOperatorEnum logicalOperator;
	private List<OpportunityPropertyDto> opportunityProperty;

	/**
	 * @param logicalOperator
	 */
	public CriteriaOperator(LogicalOperatorEnum logicalOperator, List<OpportunityPropertyDto> opportunityProperty) {
		super();
		this.logicalOperator = logicalOperator;
		this.opportunityProperty = opportunityProperty;
	}

	public LogicalOperatorEnum getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(LogicalOperatorEnum logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	public List<OpportunityPropertyDto> getOpportunityProperty() {
		return opportunityProperty;
	}

	public void setOpportunityProperty(List<OpportunityPropertyDto> opportunityProperty) {
		this.opportunityProperty = opportunityProperty;
	}

}
