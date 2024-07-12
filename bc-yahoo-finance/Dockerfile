# Use an official OpenJDK runtime as a base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR files into the container
COPY target/bc-yahoo-finance-0.0.1.jar bc-yahoo-finance.jar

# Documentation Puspose ONLY. It does not map or publish ports on the host.
# Expose the ports that the applications will run on
ENTRYPOINT ["java", "-jar"]
CMD ["bc-yahoo-finance.jar"]

# Documentation Puspose ONLY. It does not map or publish ports on the host.
# Expose the ports that the applications will run on
EXPOSE 8110 8100