/**
 * 
 */
package it.cambi.research.funding.operator;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import it.cambi.research.funding.dto.OpportunityPropertyDto;
import it.cambi.research.funding.enums.LogicalOperatorEnum;

/**
 * @author luca
 * A logical operator can have multiple property conditions like AND(property > value) OR (property = value), see {@link LogicalOperatorEnum}
 */
public class CriteriaOperator {

	private LogicalOperatorEnum logicalOperator;
	private List<OpportunityPropertyDto> opportunityProperty;

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

	@JsonIgnoreType
	public static class Builder {
		private LogicalOperatorEnum logicalOperator;
		private List<OpportunityPropertyDto> opportunityProperty;

		public Builder withLogicalOperator(LogicalOperatorEnum logicalOperator) {
			this.logicalOperator = logicalOperator;
			return this;
		}

		public Builder withOpportunityProperty(List<OpportunityPropertyDto> opportunityProperty) {
			this.opportunityProperty = opportunityProperty;
			return this;
		}

		public CriteriaOperator build() {

			CriteriaOperator criteriaOperator = new CriteriaOperator();
			criteriaOperator.logicalOperator = this.logicalOperator;
			criteriaOperator.opportunityProperty = opportunityProperty;

			return criteriaOperator;
		}
	}
}
