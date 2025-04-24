# Inventory App

## Description
This app is a simple inventory management tool using a custom rules-based engine to update items.

## Testing
Currently, being tested through Postman:
- POST request full functional.
- GET request has a bug.

All unit and integration tests running successfully.
Tests running independently, but not through command line build. Default directory needs to be redirected. 
## Installation

## Instructions
Build & run application:

```mvn springboot:run```

POST Request URI: http://localhost:8081


Example request body:

```[
    {"name":"Aged Brie","sellIn":1,"quality":1},
    {"name":"Backstage passes","sellIn":-1,"quality":2},
    {"name":"Backstage passes","sellIn":9,"quality":2},
    {"name":"Sulfuras","sellIn":2,"quality":2},
    {"name":"Normal Item","sellIn":-1,"quality":55},
    {"name":"Normal Item","sellIn":2,"quality":2},
    {"name":"INVALID ITEM","sellIn":2,"quality":2},
    {"name":"Conjured","sellIn":2,"quality":2},
    {"name":"Conjured","sellIn":-1,"quality":5}
]```