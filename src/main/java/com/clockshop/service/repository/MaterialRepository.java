package com.clockshop.service.repository;

import com.clockshop.service.entity.Material;
import com.clockshop.service.entity.Stamp;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MaterialRepository {
    List< Material > list=new ArrayList<>();

    public MaterialRepository() {
        Material material1=new Material(1,"Пластик");
        Material material2=new Material(2,"Аллой");
        Material material3=new Material(3,"Золото");
        Material material4=new Material(4,"Латунь");
        Material material5=new Material(5,"Сталь");
        Material material6=new Material(6,"Титан");
        list.add(material1);
        list.add(material2);
        list.add(material3);
        list.add(material4);
        list.add(material5);
        list.add(material6);
    }
    public List< Material > getList(){
        return list;
    }
}
