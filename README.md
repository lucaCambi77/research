
## Task ##

Design and implement some Java code which can be used to determine if a Funding Opportunity matches a set of criteria.

See pdf in /pdf/Research Coding Exercise Offline.pdf

## Modeling ##

Modeling idea of the project is based on two parts :

- To assert a match for a funding opportunity, we build a CriteriaOperator object composed by a logical operator and a list of opportunity properties (see json example below). This way we can assert a match combining all boolean results of all the conditions from requested Criteria.

- We call a Criteria service to assert the match: inside the service a Criteria object is used for the actual match. A matches method is creating predicates to filter the funding opportunity map. Criteria is the core of the application and can be further customized for more complex data structures.


## Questions ##

1. What enhancements might you make to your solution given more time?

Criteria class could be better defined with informations about data types of the properties.
Match criterias operators could be added, for example based on regular expressions or advanced search on numbers or dates. Only string is considered as value's property data type.
Nested conditions could be implemented with recursive criteria operators.

2. What considerations would you take if releasing this as a production application?

Well first of all we need to know who is going to use the application ad where/how is going to be installed. 
If some client is going to use it, a wiki or some shared library should be created in order to expose information on how to call the api. 
Performances should be tested as well, but can't see issues in this release.

## Install and run the application
To use the application you need java 8 installed in your machine. This is a spring boot application with embedded tomcat

There is a default profiles with which you can run the application

To install 

	- mvn clean install

To run  

	- java -jar target/*.jar
		

After the application is running, match api is exposed :

## (POST) http://localhost:8080/funding/match
To request a match to the application with following json as sample (application/json is required as content type in header request) :

{
  "fundingOpportunity" : {
    "fundingOpportunity" : {
      "amount" : "10000",
      "name" : "Healthcare technology research",
      "deadline" : "20191020"
    }
  },
  "criteriaOperator" : [ {
    "logicalOperator" : "AND",
    "opportunityProperty" : [ {
      "propertyName" : "name",
      "comparisonOperator" : "ISPRESENT"
    }, {
      "propertyName" : "amount",
      "value" : "10000",
      "comparisonOperator" : "EQUALS"
    } ]
  }, {
    "logicalOperator" : "OR",
    "opportunityProperty" : [ {
      "propertyName" : "deadline",
      "value" : "20191020",
      "comparisonOperator" : "EQUALS"
    } ]
  } ]
}


If the opportunity is matched, rest service return true, else false.

