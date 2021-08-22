package com.clockshop.service.repository;

import com.clockshop.service.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepository {
    List< Order > orderList=new ArrayList<>();

    public void insertInList(Order order){
        int i=0;
        for (Order order1: orderList) {

            if(order1.getOrderId()==order.getOrderId()) {
                orderList.get(i).setCalcPrice(order.getCalcPrice());
                return;
            }
            i++;
        }
        orderList.add(order);
    }
    public void deleteFromList(int orderId){
        int i=0;
        for(Order order:orderList){
            if(order.getOrderId()==orderId)
                break;
            i++;
        }
        orderList.remove(i);
    }
    public List<Order> getOrderList(){
        return orderList;
    }
}
