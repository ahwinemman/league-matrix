# League - Matrix Application

## Requirements
Application requirements include:
* Java 17
* Maven 3.8.3

## Running Project (Terminal)
1. Go into project directory
2. Run **mvn clean package** to compile jar
3. Run the generated jar **league.jar** with below command
   `java -jar target/league.jar`
4. The server will start running on port 8080.

### Sending Request
- Echo as an example
```
curl -F 'file=@/path/matrix.csv' -X GET "localhost:8080/echo"
```

## End the program
1. Ctrl-c will end the program
