# An H2 File-based Database Example in Spring Boot

This Spring Boot example shows how a local file-based database can be embedded in a project. The database is H2 and using the parameters set in the application.properties file, you can instantiate a database that will exist in memory, or be written to any accessible file system. In this example, the database files are written to the project's folder as a file in a "data" folder. The location is also determined by settings in the application properties folder.

Also, included is a console for accessing the database as you might access a database with phpMyAdmin or other simple web based tools.

## How to Run the Application

This application provides a REST interface to a table named "users" generated from an entity object model.

Using the following calls in an application such as Postman or curl, you may query the data or add new users (records).

1. Download the ZIP archive or clone the archive from the GitHub repository and import it into Eclipse or STS. 

2. Within Eclipse, run the project as Spring Boot. Try the "sanity check" shown below to verify that the app is running and accessible. Note that it runs on port 9000 so as not to conflict with any Tomcat instance you might have running.

3. As configured by default, no records are created in the database. To pre-populate with data, find the file "firstLoad-data.sql" in the "src/main/resources" folder and copy it to "data.sql" in resources folder. Restart the application. Since low level logging is enabled, you will find in the console display lots of information abut database creation. See the properties file for ways to control table creation and destruction.

4. Test the application trying the examples shown below. If they are successful, remove/delete the "data.sql" file because subsequent "runs" will throw errors when trying to insert records that already exist.


### Sanity Check

```
$ curl -v http://localhost:9000/
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 9000 (#0)
> GET / HTTP/1.1
> Host: localhost:9000
> User-Agent: curl/7.49.1
> Accept: */*
>
< HTTP/1.1 200
< Content-Type: text/plain
< Content-Length: 23
< Date: Thu, 23 Mar 2017 19:53:23 GMT
<
* Connection #0 to host localhost left intact
It's alive! It's alive!

```

### Get All Users
```

$ curl -v http://localhost:9000/getUsers
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 9000 (#0)
> GET /getUsers HTTP/1.1
> Host: localhost:9000
> User-Agent: curl/7.49.1
> Accept: */*
>
< HTTP/1.1 200
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Thu, 23 Mar 2017 20:12:34 GMT
<
* Connection #0 to host localhost left intact
[{"id":1,"username":"wilma","firstName":"Wilma","lastName":"Flintstone","email":"wflintstone@example.com"},{"id":2,"username":"fred","firstName":"Fred","lastName":"Flintstone","email":"fflintstone@example.com"},{"id":3,"username":"barney","firstName":"Barney","lastName":"Rubble","email":"brubble@example.com"},{"id":4,"username":"betty","firstName":"Betty","lastName":"Rubble","email":"brubble2@example.com"}]

```

### Get User with ID=2

```

$ curl -v http://localhost:9000/getUser/2
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 9000 (#0)
> GET /getUser/2 HTTP/1.1
> Host: localhost:9000
> User-Agent: curl/7.49.1
> Accept: */*
>
< HTTP/1.1 200
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Thu, 23 Mar 2017 20:14:32 GMT
<
* Connection #0 to host localhost left intact
{"id":2,"username":"fred","firstName":"Fred","lastName":"Flintstone","email":"fflintstone@example.com"}

```

### Add a User (insert/update)

On success, a POST of the JSON object will return the new record with its generated ID.

```

$ curl -H "Content-Type: application/json" -X POST -d '{"username":"pebbles","firstName":"Pebbles","lastName":"Flintstone","email":"pflintstone@example.com"}'  http://localhost:9000/saveUser
{"id":8,"username":"pebbles","firstName":"Pebbles","lastName":"Flintstone","email":"pflintstone@example.com"}

```

## Using the H2 Console

To access the console for the database, point your browser to "http://localhost:9000/console" and you should see a small login panel. The default username is "sa" and there is no password. Once in there are some useful tools for perfoming some basic tasks.

# End of File
