# investedU services


#### User
**Username**
Preferential data points for each industry & company <br/>
**Dislike**
Tolerate <br/>
**Neutral** (Default) <br/>
**Like**<br/>
**Love**<br/>
**Dollars to invest**
Selection of Portfolio Designer to follow (If no Portfolio Designer selected, default to Portfolio SPX)

Portfolio Designer
Username/Portfolio Name
List of tickers/weights in each portfolio

For the Users & Portfolio designers, we will want a point in time display which shows:
Portfolio Value
Total return to date
Risk adjusted return to date
Alpha generated (if any)




http://investedu.herokuapp.com/v1.0/register
Type = POST
Headers:
Content-Type : application/json
{
  "username" : "linux", "password" : "qwerty", "name" : "Linus", "email": "linus@linux.com"

}

http://investedu.herokuapp.com/v1.0/login
Type = POST
Headers:
Content-Type : application/json
{
  "username" : "linux", "password" : "qwerty"

}

http://investedu.herokuapp.com/v1.0/logout
Type = POST
Headers:
Content-Type : application/json


{
  "username" : "bob"

}


https://api.iextrading.com/1.0/stock/market/batch?symbols=
