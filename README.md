# NoBrokerAssignment
# Assignment given by NoBroker.

## Common Steps
# Step 1:- Please Downloada and Open the Project in Intellij or Eclipse
# Step 2:- Export all the Maven Repository add in POM.xml
# Step 3:- Change The UDID of the Device in Property File. (Location in Project "PropertyFile/Global.properties")

## Execute the Code (Via Test File)
# Step 1:- Open "NoBrokerTest" (Java_File) and Run it. (Location in Project "src/test/java/TestFrame/NoBrokerTest.java")
# Step 2:- After Execution Report should be generated in 'Reports Folder'.

## Execute the Code (Via TestNG XML)
# Step 1:- Open "NoBrokerTestSuite" (XML_File) and Run it. (Location in Project "TestSuites/NoBrokerTestSuite.xml")
# Step 2:- After Execution Report should be generated in 'Reports Folder'.

## Note: Currently there is only 1 class available in the that's Why listener is added in class level, Collection level listener also added in XML suite but for now commented it.

## Excute the Code (Via Maven Command)
# Step 1:- Open the Terminal change the directory to Project level. (cd "Path_TO_Project")
# Step 2:- Hit "mvn compile" command to compile all the things.
# Step 3:- Hit "mvn clean install" and after this code is executed.
# Step 4:- Report is generated in Reports folder, also if you have allure setup in your machine just hit "allure serve target/surefire-reports"
