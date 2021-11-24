package com.surabi.restaurants.serviceimpl;

import com.surabi.restaurants.DTO.*;
import com.surabi.restaurants.Enum.City;
import com.surabi.restaurants.entity.*;
import com.surabi.restaurants.repository.BillRepository;
import com.surabi.restaurants.repository.MenuRepository;
import com.surabi.restaurants.repository.OrderDetailsRepository;
import com.surabi.restaurants.repository.OrderRepository;
import com.surabi.restaurants.service.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImpl implements RestaurantsService {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BillRepository billRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Menu> viewAllMenu() {
        return menuRepository.findAll();
    }

    @Override
    public Optional<Menu> getMenuById(int menuID) {

        if (menuRepository.existsById(menuID)) {
            return menuRepository.findById(menuID);
        } else {
            return Optional.of(new Menu("Menu not found"));
        }
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public int createOrder(int menuID, int qty) {

        int savedOrderID = 0;
        if (menuRepository.existsById(menuID)) {
            Date date = new Date();
            Orders orders = new Orders();
            OrderDetails orderDetails = new OrderDetails();
            User user = new User();
            orders.setOrderDate(date);
            user.setUsername("ram");
            orders.setUser(user);
            orderDetails.setQuantity(qty);
            orderDetails.setOrders(orders);
            Menu menu = menuRepository.findAll().get(menuID);
            orderDetails.setMenu(menu);
            orderDetails.setItemTotalprice(qty * menu.getPrice());
            Orders savedOrder = orderRepository.save(orders);
            savedOrderID = savedOrder.getOrderId();
            orderDetailsRepository.save(orderDetails);
            return savedOrderID;
        } else {
            System.out.println("No menu with provided ID");
        }
        return savedOrderID;
    }

    @Override
    public String createBulkItem(List<OrderBulkDTO> orderBulkDTO, City city) {
        int savedOrderID = 0;
        Date date = new Date();
        Orders orders = new Orders();
        User user = new User();
        orders.setOrderDate(date);
        orders.setCity(city.name());
        user.setUsername("ram");
        orders.setUser(user);
        Orders savedOrder = orderRepository.save(orders);
        savedOrderID = savedOrder.getOrderId();
        for (OrderBulkDTO orderBulkDTOtemp : orderBulkDTO) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setQuantity(orderBulkDTOtemp.getQty());
            orderDetails.setOrders(orders);
            System.out.println("Getting menu id" + orderBulkDTOtemp.getMenuID());
            try {
                Menu menu = menuRepository.getOne(orderBulkDTOtemp.getMenuID());
                orderDetails.setMenu(menu);
                orderDetails.setItemTotalprice(orderBulkDTOtemp.getQty() * menu.getPrice());
                orderDetailsRepository.save(orderDetails);
            } catch (EntityNotFoundException e) {
                return "Wrong menu id: " + orderBulkDTOtemp.getMenuID();
            }
        }
        return "Order ID: " + savedOrderID + " has been created successfully";
    }

    @Override
    public OrderResponse checkOut(int orderId) {
        Orders orders = new Orders();
        Date date = new Date();
        orders.setOrderId(orderId);
        Bill bill = new Bill();
        bill.setBillID(orderId);
        bill.setBillDate(date);
        bill.setOrders(orders);
        if (orderRepository.existsById(orderId)) {
            Query nativeQuery = entityManager.createNativeQuery("select sum(ITEM_TOTALPRICE) from ORDER_DETAILS where ORDER_ID=?1");
            nativeQuery.setParameter(1, orderId);
            List amount = nativeQuery.getResultList();
            double amt = (double) amount.get(0);
            System.out.println("amt: " + amount);
            bill.setBillAmount(amt);
            System.out.println("Bill ID: " + bill.getBillID());
            if (!billRepository.existsById(bill.getBillID())) {
                String loggedUser = "ram";
                String role = "ADMIN";
                System.out.println("Logged user role is "+role);
                Orders dborders = orderRepository.getOne(orderId);
                User orderUserDetails = dborders.getUser();
                String orderUser = "ram";
                if (orderUser == loggedUser || role.equals("[ADMIN]")) {
                    Bill savedBillId = billRepository.save(bill);
                    int billid = savedBillId.getBillID();
                    OrderResponse orderResponse = new OrderResponse();
                    orderResponse.setResponse("Bill saved with ID " + billid);
                    return orderResponse;
                } else {
                    OrderResponse orderResponse = new OrderResponse();
                    orderResponse.setResponse("Order ID: " + orderId + " does not belongs to you, cant checkout!");
                    return orderResponse;
                }
            }
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setResponse("Bill with given order already generated");
            return orderResponse;
        } else {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setResponse("Order ID: " + orderId + " does not exist");
            return orderResponse;
        }
    }

    @Override
    public BillOrderDetailsDTO viewMyBill(int billID) {
        List<BillDetailsDTO> billDetailsDTOS = new ArrayList<>();
        if (billRepository.existsById(billID)) {
            Orders orders = orderRepository.getOne(billID);
            String user = "ram";
            User orderUser = orders.getUser();
            String orderUser1 = orderUser.getUsername();
            System.out.println("users from DB for order is: " + orderUser);
            if (user.equals(orderUser1)) {
                Query nativeQuery = entityManager.createNativeQuery("select b.BILLID as BILL_ID,  u.USERNAME as USERNAME, m.ITEM as ITEM,  d.QUANTITY as QTY, m.PRICE as PRICE, d.ITEM_TOTALPRICE as ITEM_TOTALPRICE,b.BILL_AMOUNT as BILL_AMOUNT, b.PAID_BY as PAID_BY from menu m, orders o, ORDER_DETAILS d, users u , BILL b where m.menu_id=d.menu_id  and o.ORDER_ID=d.ORDER_ID and u.USERNAME=o.USERNAME  \n" +
                        "                        and b.ORDER_ID=O.ORDER_ID\n" +
                        "                        and o.ORDER_ID=?1", "BillDTOMapping");
                nativeQuery.setParameter(1, billID);
                List<BillDetailsDTO> list = nativeQuery.getResultList();
                BillOrderDetailsDTO billOrderDetailsDTO = new BillOrderDetailsDTO();
                BillDetailsDTO billDetailsDTO = list.get(0);
                billOrderDetailsDTO.setBILL_ID(billDetailsDTO.getBILL_ID());
                billOrderDetailsDTO.setUSERNAME("ram");
                billOrderDetailsDTO.setBILL_AMOUNT(billDetailsDTO.getBILL_AMOUNT());
                billOrderDetailsDTO.setPAYMENT_MODE(billDetailsDTO.getPAID_BY());
                List<BillDetailDTO> orderDetailDTO = new ArrayList<>();
                for (BillDetailsDTO billDetailsDTO1 : list) {
                    BillDetailDTO billDetailDTO1 = new BillDetailDTO();
                    billDetailDTO1.setITEM(billDetailsDTO1.getITEM());
                    billDetailDTO1.setQTY(billDetailsDTO1.getQTY());
                    billDetailDTO1.setPRICE(billDetailsDTO1.getPRICE());
                    billDetailDTO1.setITEM_TOTALPRICE(billDetailsDTO1.getITEM_TOTALPRICE());
                    orderDetailDTO.add(billDetailDTO1);
                }
                billOrderDetailsDTO.setBillDetailDTO(orderDetailDTO);
                return billOrderDetailsDTO;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getUserDetails() {
        return null;
    }

    @Override
    public List<OrderDetailsDTO> getAllOrder() {
        Query nativeQuery = entityManager.createNativeQuery("select ORDER_ID,ORDER_DATE,USERNAME,QUANTITY,UNIT_PRICE,ITEM_TOTALPRICE,MENU_ID from All_orders", "OrderDTOMapping");
        List<OrderDetailsDTO> orders = nativeQuery.getResultList();
        return orders;
    }

    @Override
    public List<OrderDetailsDTO> getOrderByDate(String date) {
        Query nativeQuery = entityManager.createNativeQuery("select ORDER_ID,ORDER_DATE,USERNAME,QUANTITY,UNIT_PRICE,ITEM_TOTALPRICE,MENU_ID from All_orders where TO_DATE(to_char(order_date,'YYYY-MM-DD'),'YYYY-MM-DD') = to_timestamp(?1, 'YYYY-MM-DD')", "OrderDTOMapping");
        nativeQuery.setParameter(1, date);
        List<OrderDetailsDTO> ordersByDate = nativeQuery.getResultList();
        return ordersByDate;
    }

    @Override
    public List<OrderDetailsDTO> getOrderByPrice(Double price) {
        Query nativeQuery = entityManager.createNativeQuery("select ORDER_ID,ORDER_DATE,USERNAME,QUANTITY,UNIT_PRICE,ITEM_TOTALPRICE,MENU_ID from All_orders where ITEM_TOTALPRICE>= ?1", "OrderDTOMapping");
        nativeQuery.setParameter(1, price);
        List<OrderDetailsDTO> ordersByPrice = nativeQuery.getResultList();
        return ordersByPrice;
    }


}

