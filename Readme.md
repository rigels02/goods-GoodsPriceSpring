# GoodsPriceSpring Restful resource Spring-Boot application

Exposing Restful resources by using two repositories:

- IGoodsCrudRepository  by extending  interface CrudRepository
- IGoodsRepositoryPages by extending interface PagingAndSortingRepository

RestController GoodsController is used to expose resources for Url: "/goods"
The spring-boot default Rest API controller is used to expose repository IGoodsRepositoryPages
for Url:"/pgoods".

## JavaScript Clients

Both clients are AngularJS clients.

- GoodsPriceJSClient access resources by URL:  "&lt;host>/goods".
- GoodsPriceJSClientPages access resources by URL: "&lt;host>/pgoods" and has pagenation support.

&lt;host> - http://localhost:8080


