User name and password are same:

Week12:

In order to test the chat feature:
1. Start shurabhi-server
2. Create a postgres DB ("shurabhi21"), script in SQL file in the root path.
3. please start zookeeper and kafak brokers (localhost:9092)
4. Create one admin user in DB, user name and password are same in this script.(INSERT INTO USERS VALUES ('ram', 'USER', TRUE, '$2a$10$CEowIBoP73DKZf./TVVLqO.Z2034Rk717xxfecnvxyt8HHVFLLe0a');)
5. start shurabhi-chat server.
6. You can register new users via chat swagger API. http://localhost:9994/swagger-ui.html#/user-registration-controller/resisterUsingPOST
7. In order to send, you can need to login(user or admin)
8. you can send message to admin or user
9. To send message to admin, to='ADMIN' sample below
   {
   "content": "Hey Admin",
   "from": "Suresh",
   "timestamp": "string",
   "to": "ADMIN"
   }
   
10. To send msg to other user, you can specify the user name in to perimeter
11. to consume admin message, you need to have a admin role, login with admin users to see admin message
12. to consumer the message of a specific user, you need to login with that user(if i want to read message sent to Ramesh, i need to login first as Ramesh to see my messages)

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

