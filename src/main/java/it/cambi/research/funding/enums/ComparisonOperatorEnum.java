/**
 * 
 */
package it.cambi.research.funding.enums;

/**
 * @author luca
 *
 */
public enum ComparisonOperatorEnum {

	ISPRESENT("Property is present"), EQUALS("Property equals to some value"), GT("Property greater than some value"),
	LT("Property less than some value");

	private String description;

	ComparisonOperatorEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
