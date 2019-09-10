/**
 * 
 */
package it.cambi.research.funding.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import it.cambi.research.funding.enums.ComparisonOperatorEnum;

/**
 * @author luca
 */
public class OpportunityPropertyDto {

	private String propertyName;
	private String value;
	private ComparisonOperatorEnum comparisonOperator;

	@JsonIgnoreType
	public static class Builder {
		private String propertyName;
		private String value;
		private ComparisonOperatorEnum comparisonOperator;

		public Builder withComparisonOperator(ComparisonOperatorEnum comparisonOperator) {
			this.comparisonOperator = comparisonOperator;
			return this;
		}

		public Builder withPropertyName(String propertyName) {
			this.propertyName = propertyName;
			return this;
		}

		public Builder withValue(String value) {
			this.value = value;
			return this;
		}

		public OpportunityPropertyDto build() {

			OpportunityPropertyDto opportunityProperty = new OpportunityPropertyDto();
			opportunityProperty.comparisonOperator = this.comparisonOperator;
			opportunityProperty.propertyName = this.propertyName;
			opportunityProperty.value = this.value;

			return opportunityProperty;
		}
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ComparisonOperatorEnum getComparisonOperator() {
		return comparisonOperator;
	}

	public void setComparisonOperator(ComparisonOperatorEnum comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}
}
