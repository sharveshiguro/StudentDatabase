# StudentDatabase
Java project with MongoDB to store student information with image 
DOWNLOADS REQUIRED:
1.Java Installation: 
Check in cmd line: java -version
If not there check path if not downloaded : [https://download.oracle.com/java/23/latest/jdk-23_linux-aarch64_bin.tar.gz](url)
2.MongoDB Server Installation: Community server for MongoDB [https://www.mongodb.com/try/download/community](url)
3.Eclipse IDE Installation: [https://www.eclipse.org/downloads/packages](url)
PROCESS:
1.	Open Eclipse IDE -> File -> New -> Other -> Maven Project -> Add Achetype -> 
2.	Archetype Group Id and Archetype Artifact Id = project name 
3.	Under workspace directory locate to pom.xml and add the following code before the closing tag of project (</project>): 
<dependencies>
  	<dependency>
  		<groupId>org.mongodb</groupId>
  		<artifactId>mongo-java-driver</artifactId>
  		<version>3.8.1</version>
  	</dependency>
  </dependencies>
4.	Locate to src/main/java and and create a new class by right clicking on it 
5.	Paste the source code
6.	Open MongoDB folder in ProgramFiles of C Drive and locate to “C:\Program Files\MongoDB\Server\8.0\bin”
7.	Locate to the above path in cmd line and type the following code:
8.	mongod
9.	Open MongoCompass application and establish connection
10.	Run the Java source code and give inputs
11.	Restart MongoCompass and connect again to see values stored in collection
