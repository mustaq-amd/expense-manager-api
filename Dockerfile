FROM java:8

EXPOSE 5000

ADD target/expensetrackerapi-0.0.1-SNAPSHOT.jar docker-expensemanagerapi.jar

ENTRYPOINT ["java","-jar","/docker-expensemanagerapi.jar"]
	