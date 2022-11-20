# Zilch-coding-excersice

Hello!

This is a sample application called Payments, it provides functionalities such as :
- Creating account
- Making a Purchase
- Making a payment for that purchase
- Generating a summary report of made purchases 

It is supposed to mimic a sort of a buy now pay later concept in a very minimalistic way.
In this app I wanted to show how I like to approach testing and code structure.

Code is organised using hexagonal architecture:
- inbound and oubound connections implemented in "boundary" package
- domain entities and business logic inside of domain package
- configuration and application services inside application package

Testing is separated to 3 approaches :
- integration testing - Controllers, Repositories - implemented as Slice tests
- use case tests - BDD style testing - with in memory implementations of repositories etc
- unit testing

Main data source is MongoDB, with additin of ElasticSearch

With Elasticsearch wanted to briefly show a concept of CQRS. Asynchronously after making a purchase,
information about it is being sent to Elasticserach which holds the read model. 
Elasticsearch is specialised in full text search, so if we would require such functionality it would perform much better at a larger scale.
