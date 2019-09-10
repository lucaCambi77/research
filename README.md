
## Task ##

Design and implement some Java code which can be used to determine if a Funding Opportunity matches a set of criteria.

See pdf in /pdf/Research Coding Exercise Offline.pdf

## Install and run the application
To use the application you need java 8 installed in your machine. This is a spring boot application with embedded tomcat

There is a default profiles with which you can run the application

To install 

	- mvn clean install

To run  

	- java -jar target/*.jar
		

After the application is running, order api is exposed :

## (POST) http://localhost:8080/order
To request an order to the application with following json as sample (application/json is required as content type in header request) :

{"itemToCountMap":{"CF":13,"MB11":14,"VS5":10}}

If the order is completed, you will receive a report with all the information about prices and packaging

