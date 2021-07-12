FROM openjdk:8-jdk-alpine

COPY basket-inventory-app/build/libs/basket-inventory-app-0.0.1-SNAPSHOT-boot.jar basket-inventory-ms.jar

ENTRYPOINT ["java","-jar","/basket-inventory-ms.jar"]