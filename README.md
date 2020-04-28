# TestStore

This project uses java version 8, mvn 3.6.3


## Run mongo db server
Please run mongo db server on port 27017

## Fill db with data
Before first start please fill db with data: 
• Add db `test-store` and new collection `products`
• Import data form `test-store/resources/products.json` with MongoDB compass ("Add data"->"Insert document" (copy all content to input form)) 

## Run server

`mvn install` and `mvn spring-boot:run` or just run spring boot application witg your IDE
