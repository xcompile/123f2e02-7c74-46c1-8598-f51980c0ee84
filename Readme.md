# 123f2e02-7c74-46c1-8598-f51980c0ee84

## What to Expect
A simple currency monitor
- show currencies with different base currency
- data from https://openexchangerates.org/
- show historic currencies

## How To Run
### Maven

```shell
mvn clean package spring-boot:run -Drest.api.apiKey=<APIKEY>
```


### Docker
Build docker image initially, run
```shell
mvn clean package docker:build
Run: docker run  --rm -e "REST_API_APIKEY=<APIKEY>" -p 8080:8080 mne/currencymonitor 
```

It is my very first GWT application, hope you enjoy
