FROM java:8

EXPOSE 5000

ADD target/docker-expensemanagerapi.jar docker-expensemanagerapi.jar

ENTRYPOINT ["java","-jar","/docker-expensemanagerapi.jar"]
