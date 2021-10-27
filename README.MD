User name and password are same:
start servers
1. shurabhi-server -> http://localhost:8761/
2. shurabhi-discount -> http://localhost:9996/discount/getDiscount/50/GET50PER
3. shurabhi-user -> http://localhost:9998/swagger-ui.html#/users-controller
4. shurabhi-offline -> http://localhost:9995/swagger-ui.html#/offline-controller
5. shurabhi-admin -> http://localhost:9999/swagger-ui.html#/admin-controller
 
   
   
   
   
In shurabhi - Offline module:(below sequence)
1. /Order bulkOrder   -> To order in bulk
2. /CheckOut CheckOut -> To generate Bill
3. /Payment payBill  -> To pay bill
4. other module like view bill, book seat, give feedback, view menu etc....

![img.png](img.png)