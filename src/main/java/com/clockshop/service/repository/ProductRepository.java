package com.clockshop.service.repository;

import com.clockshop.service.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    List< Product > list=new ArrayList<>();

    public ProductRepository() {
        Product product1=new Product(1,"Часы CASIO EF-125D-2A", //сталь элекронные
                "https://disk.yandex.ru/i/BXC1g4Ihpjb-8Q",5,1,1,5390);
        Product product2=new Product(2,"Seiko 5 Sports", //мехаические сталь
                "https://disk.yandex.ru/i/02fULOmh1aJcsw",5,2,3,27500);
        Product product3=new Product(3,"Смарт-часы Geozon Titan Black/Black (G-SM06BLK)", //пластик электронные
                "https://disk.yandex.ru/i/a11LWni3JWNPLA",1,3,1,1790);
        Product product4=new Product(4,"OMEGA 13110286005001",//кварцевые  сталь
                "https://disk.yandex.ru/i/1aUs38zaKa70yg",5,4,2,285000);
        Product product5=new Product(5,"Timex TW2R96400VN", //кварцевые сталь
                "https://disk.yandex.ru/i/MqIe_g9E6N_8mw",5,5,2,9450);
        Product product6=new Product(6,"Citizen EM0643-84X", //кварцевые золото
                "https://disk.yandex.ru/i/M3kJ2OwfKigQCA",3,6,2,40350);
        Product product7=new Product(7,"TAG Heuer CBL2111.BA0644", //механический сталь
                "https://disk.yandex.ru/i/_9pJqb3J8RnQqg",5,7,3,495000);
        Product product8=new Product(8,"Patek Philippe Grand Complications 5372P-010", //механика аллойные
                "https://disk.yandex.ru/i/em1SWz1rEZGLMAw",2,8,3,3250000);
        Product product9=new Product(9,"Oakley 10-071 12 Gauge Titanium", //Кварцевые титан
                "https://disk.yandex.ru/i/Q84VmSc5K6kDDw",6,9,2,23400);
        Product product10=new Product(10,"Oyster Perpetual Cosmograph Daytona", // механика золото
                "https://disk.yandex.ru/i/hy6Qg6AHlMn49g",3,10,3,2340000);
        list.add(product1);
        list.add(product2);
        list.add(product3);
        list.add(product4);
        list.add(product5);
        list.add(product6);
        list.add(product7);
        list.add(product8);
        list.add(product9);
        list.add(product10);
    }
    public List<Product> findAllByMechId(int mechId){
        List<Product> res=new ArrayList<>();
        for (Product product: list) {
            if(product.getMechId()==mechId)
                res.add(product);
        }
        return res;
    }
    public List<Product> findAllByStampId(int stampId){
        List<Product> res=new ArrayList<>();
        for (Product product: list) {
            if(product.getStampId()==stampId)
                res.add(product);
        }
        return res;
    }
    public List<Product> findAllByMaterialId(int materialId){
        List<Product> res=new ArrayList<>();
        for (Product product: list) {
            if(product.getMaterialId()==materialId)
                res.add(product);
        }
        return res;
    }
}
