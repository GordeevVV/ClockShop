package com.clockshop.service.repository;

import com.clockshop.service.entity.OrderProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderProductRepository {
    List< OrderProduct > orderProductList=new ArrayList<>();
    public OrderProductRepository() {
    }
    public void deleteProductFromOrderProductList(int productId){
        int i=0;
        for(OrderProduct orderProduct:orderProductList){
            if(orderProduct.getProductId()==productId) {
                orderProductList.remove(i);
                break;
            }
            i++;
        }
    }
    public void deleteOrderFromOrderProductList(int orderId){
        int i=0;
        for(OrderProduct orderProduct:orderProductList){
            if(orderProduct.getOrderId()==orderId)
                orderProductList.remove(i);
            i++;
        }
    }
    public List< OrderProduct > getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        orderProductList.add(orderProduct);
    }
}
