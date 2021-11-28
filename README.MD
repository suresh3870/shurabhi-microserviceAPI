User name and password are same:

Week11:
1. User and micro service updated with postgre DB, Quesries are in SQL file
2. start servers

shurabhi-server -> http://localhost:8761/
shurabhi-discount -> http://localhost:9996/discount/getDiscount/50/GET50PER
shurabhi-user -> http://localhost:9998/swagger-ui.html#/users-controller
shurabhi-offline -> http://localhost:9995/swagger-ui.html#/offline-controller
shurabhi-admin -> http://localhost:9999/swagger-ui.html#/admin-controller
In shurabhi - Offline module:(below sequence)

/Order bulkOrder -> To order in bulk
/CheckOut CheckOut -> To generate Bill
/Payment payBill -> To pay bill
other module like view bill, book seat, give feedback, view menu etc....
![img_2.png](img_2.png)
   ![img_3.png](img_3.png)
![img_1.png](img_1.png)
 
In shurabhi - Offline module:(below sequence)
1. /Order bulkOrder   -> To order in bulk
2. /CheckOut CheckOut -> To generate Bill
3. /Payment payBill  -> To pay bill
4. other module like view bill, book seat, give feedback, view menu etc....

![img.png](img.png)

