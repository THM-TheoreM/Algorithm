# ToDo

- encode

We often get a html encoded by utf-8, which should have been encoded by gbk. And also the code below cannot solve the problem perfectly.

```java
String str = "1234高辛格";
str = new String(str.getBytes("gbk"), "utf-8");
System.out.println(new String(str.getBytes("utf-8"), "gbk"));
```

- post method

If we substitute the POST for GET in the code, it will throw an error.  

```java
Response response = Requests.get("http://trains.ctrip.com/TrainBooking/Search.aspx", "GET", 0, 0, query, new Hashtable<String, String>(), false);
```

- session

We can initial `CookieManager` like this

```java
CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
```

or set cookies by ourselves like this

```java
connection.setRequestProperty("Cookie", cookie);
```

- stream

Deal with stream.

- proxy

We have not found anything about proxy in `URLConnection` or `HttpURLConnection`.

- upload files

Deal with uploading.

- error handle

We don't have error exceptions.

- Json

Our Json code is based on the assumption that a json consists of only four types: `String`, `int`, `Hashtable<String, Object>`, `Hashtable<Integer, Object>`. Actually, there may be other types such as `boolean` and `null`.