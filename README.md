# Welcome to Marvel Characters API 👋
![Version](https://img.shields.io/badge/0.0.1-SNAPSHOT-blue.svg?cacheSeconds=2592000)

> The Marvel Characters API uses the resources of the Marvel Comics API allows developers to access information about Marvel's vast library of comics to retrive the characters ids and allow the the user get an specific charater by it's ID receving the id, name, description and trumbnail data.

## Install

```sh
1.Install and configure Java and Maven:
◦Java: https://www.oracle.com/technetwork/java/javase/downloads/index.html
◦Maven: https://maven.apache.org/install.html
2.Command: mvn clean install spring-boot:run
◦All required dependencies are managed by Maven
3.Go to http://localhost:8080/
4.Check Usage section to know to url and response to each endpoint.
```

## Usage

```sh
1. Serve an endpoint / characters that returns all the Marvel character ids only, in a
JSON array of numbers. 
GET: localhost:port/characters/ 
----------------------------------------------------------------------------------------
2. Serve an endpoint /characters/{characterId} that contains the real-time data from the
Marvel API /v1/public/characters/{characterId}, but containing only the following
information about each character: id, name, description, thumbnail. 
3. It will accept a query parameter with the language ISO-639-1 code to do the translations in real-time:
GET: localhost:port/characters/{characterId}?language={languageCode}
---------------------------------------------------------------------------------------
4.Create a Swagger specification for your Characters API that can be viewed with Swagger
UI or imported to Postman
http://localhost:port/swagger-ui.html
```
## Third party API used:

```sh
1.IBM Watson LanguageTranslator - Real Time Language Translator API
◦Documentation and Demo: https://cloud.ibm.com/apidocs/language-translator
2.Swagger2 -  allows you to describe the structure of your APIs
◦Documentation: https://swagger.io/docs/
```
## Important Informations

The Java content to create the API is based on a github solution by the user KatieSanderson.(https://github.com/KatieSanderson)
Project reference link: https://github.com/KatieSanderson/marvelCharactersAPI

# A Personal Note about this project
"This was a code challenge, search for answers and learn from others are in my personal opinion part of the developer path to get better and better. Do not just copy and paste but try improve and understand."

## Author

👤 **Andre Luis Sabino**

* Github: [@alsabino](https://github.com/alsabino)
* LinkedIn: [@andrelsabino](https://linkedin.com/in/andrelsabino)

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_