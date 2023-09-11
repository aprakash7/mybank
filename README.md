# mybank

## A simple web app to make and retrieve transactions.

* Java 21
* Maven
* Tomcat
* Servlet

### To Boot Up Tomcat on port 8090
```
java -jar -Dserver.port=8090 {jarname}.jar'
```

### Endpoints

#### Make a transaction:
```
POST Request: http://localhost:8090/transactions?cust_id=aprakash&amount=100&transaction_details=eating at sharief bhai

Response
{
    "amount": 100,
    "timestamp": "2023-09-12T00:28+0530",
    "transaction_id": "babb471c-a8fd-412f-a260-06f444697ee0",
    "transaction_details": "eating at sharief bhai"
}
```

#### Get all transactions:
```
GET Request: http://localhost:8090/transactions

Response
[
    {
        "amount": 100,
        "timestamp": "2023-09-12T01:56+0530",
        "transaction_id": "594acc2d-9888-4659-8220-2aca89fa41b4",
        "transaction_details": "eating at shareif bhai"
    },
    {
        "amount": 150,
        "timestamp": "2023-09-12T01:57+0530",
        "transaction_id": "f097d5e6-1189-4f20-8ab3-5b3e32345aa7",
        "transaction_details": "bought a book"
    }
]
```