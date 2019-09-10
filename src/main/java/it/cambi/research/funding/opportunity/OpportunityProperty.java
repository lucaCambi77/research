/**
 * 
 */
package it.cambi.research.funding.opportunity;

import it.cambi.research.funding.enums.ComparisonOperatorEnum;

/**
 * @author luca
 *
 */
public class OpportunityProperty<T> {

	private String propertyName;
	private T value;
	private ComparisonOperatorEnum comparisonOperator;
	private Class<T> propertyClass;

	public static class Builder<T> {
		private String propertyName;
		private T value;
		private ComparisonOperatorEnum comparisonOperator;
		private Class<T> propertyClass;

		public Builder<T> withComparisonOperator(ComparisonOperatorEnum comparisonOperator) {
			this.comparisonOperator = comparisonOperator;
			return this;
		}

		public Builder<T> withPropertyName(String propertyName) {
			this.propertyName = propertyName;
			return this;
		}

		public Builder<T> withValue(T value) {
			this.value = value;
			return this;
		}

		public Builder<T> withClass(Class<T> propertyClass) {
			this.propertyClass = propertyClass;
			return this;
		}

		public OpportunityProperty<T> build() {

			OpportunityProperty<T> opportunityProperty = new OpportunityProperty<T>();
			opportunityProperty.comparisonOperator = this.comparisonOperator;
			opportunityProperty.propertyName = this.propertyName;
			opportunityProperty.value = this.value;
			opportunityProperty.propertyClass = this.propertyClass;

			return opportunityProperty;
		}
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ComparisonOperatorEnum getComparisonOperator() {
		return comparisonOperator;
	}

	public void setComparisonOperator(ComparisonOperatorEnum comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}

	public Class<T> getPropertyClass() {
		return propertyClass;
	}

	public void setPropertyClass(Class<T> propertyClass) {
		this.propertyClass = propertyClass;
	}

}
