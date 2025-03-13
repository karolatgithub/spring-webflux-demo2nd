# spring-webflux-demo2nd

## budowanie
bedac w katalogu zrodel wykonac polecenie

```
mvn -U clean package
```

## zainstalowac bazde danych MongoDb z domyslnym portem 27017

## uruchomienie
bedac w katalogu zrodel przejsc do katalogu

```
deploy\spring-webflux-demo2nd
```

wykonac polecenie

```
java -jar spring-webflux-demo2nd-0.0.1-SNAPSHOT.jar
```

powinien sie wyswietlic napis na konsoli

```
13:19:09.361 [main] INFO  o.s.b.w.e.n.NettyWebServer - Netty started on port(s): 8080
```

oznaczajacy uruchomnienie lokalnie serwera aplikacji ktora mozna teraz
testowac

## operacje http

wyszukanie rekordow wh poczatkowej nazwy autoraigorujac wielkie/male znaki
```
GET http://localhost:8080//api/articles/author/KAROL2
```
