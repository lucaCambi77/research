/**
 * 
 */
package it.cambi.research.funding.opportunity;

import java.util.Map;

/**
 * @author luca
 *
 */
public class FundingOpportunity {

	private Map<String, String> fundingOpportunity;

	/**
	 * @param fundingOpportunity
	 */
	public FundingOpportunity(Map<String, String> fundingOpportunity) {
		super();
		this.fundingOpportunity = fundingOpportunity;
	}

	public Map<String, String> getFundingOpportunity() {
		return fundingOpportunity;
	}

	public void setFundingOpportunity(Map<String, String> fundingOpportunity) {
		this.fundingOpportunity = fundingOpportunity;
	}
}
