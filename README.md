# Text_Transformer
Aplikacja dla osób pracujących z danymi tekstowymi nasza aplikacja Text Transformer umożliwi transformacje danych tekstowych (np. zmiana wielkości liter, eliminacja duplikatów, itd.). Aplikacja będzie dostępna poprzez GUI a także zdalne API dzięki czemu będzie można ją zintegrować z istniejącymi narzędziami.

https://docs.google.com/spreadsheets/d/e/2PACX-1vTn6j3M8pmGEzrsQk8mXse7lVHUdhYWkfxbkQiYI23rBtwM4N3bWw0qtupW-gesfCkcYasnZ-eEXl-F/pubhtml#

![example workflow](https://github.com/fmichalski03/Text_Transformer/actions/workflows/ci.yml/badge.svg)

## Building project
```
mvn package
```

## Testing project
```
mvn test
```

## Running application
```
mvn spring-boot:run
```
or
```
java -jar target/TextTransformerApplication-<version>.jar 
```
