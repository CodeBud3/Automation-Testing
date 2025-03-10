# Automation-Testing

# Check if Java is installed

java --version

# If java is installed, skip below step to install JAVA

# Setup JAVA

Download JDK from oracle and install
Setup the environment variables
JAVA_HOME = {path where java is installed(\jdk-23.0.1)}
PATH = {add the path of jdk bin folder}

# Check if maven is intalled

mvn -version

# if maven is installed, skip below step to install

# setup maven

Download maven from maven org page
Setup the environment variables
MAVEN_HOME = {path where maven is installed}
PATH = {add the path of maven bin folder}

Create new project in Eclipse
If there is any error in pom.xml file, change http to https
run below command
mvn clean install

in eclipse -> Project -> Clean

Go to maven repository and search for Selenium Java, copy the Maven Dependancy
Paste the Dependancy in the project pom.xml file
