/**
 * 
 */
package it.cambi.research.funding.opportunity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * @author luca
 *
 */
public class FundingOpportunity {

	private Map<String, String> fundingOpportunity;

	public Map<String, String> getFundingOpportunity() {
		return fundingOpportunity;
	}

	public void setFundingOpportunity(Map<String, String> fundingOpportunity) {
		this.fundingOpportunity = fundingOpportunity;
	}

	@JsonIgnoreType
	public static class Builder {
		private Map<String, String> fundingOpportunity;

		public Builder withFundingOpportunity(Map<String, String> fundingOpportunity) {
			this.fundingOpportunity = fundingOpportunity;
			return this;
		}

		public FundingOpportunity build() {

			FundingOpportunity fundingOpportunity = new FundingOpportunity();
			fundingOpportunity.fundingOpportunity = this.fundingOpportunity;
			
			return fundingOpportunity;
		}
	}
}
