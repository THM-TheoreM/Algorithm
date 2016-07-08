# public class Response

```java
private HttpURLConnection connection;
public String url;
public int connect_time;
public int read_time;
public Boolean set;
public String response_header;
public String method;
public int status_code;
public String message;
public String encoding;
public String text = "";

Response(HttpURLConnection connection) throws IOException
public String get(String name)
```