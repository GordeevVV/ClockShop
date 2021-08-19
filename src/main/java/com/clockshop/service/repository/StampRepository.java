package com.clockshop.service.repository;

import com.clockshop.service.entity.Stamp;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StampRepository {
    List< Stamp > list=new ArrayList<>();

    public StampRepository() {
        Stamp stamp1=new Stamp(1,"Casio");
        Stamp stamp2=new Stamp(2,"Seiko");
        Stamp stamp3=new Stamp(3,"Titan");
        Stamp stamp4=new Stamp(4,"Omega");
        Stamp stamp5=new Stamp(5,"Timex");
        Stamp stamp6=new Stamp(6,"Citizen");
        Stamp stamp7=new Stamp(7,"Teg Heuer");
        Stamp stamp8=new Stamp(8,"Patek Phillipe");
        Stamp stamp9=new Stamp(9,"Oakley");
        Stamp stamp10=new Stamp(10,"Rolex");
        list.add(stamp1);
        list.add(stamp2);
        list.add(stamp3);
        list.add(stamp4);
        list.add(stamp5);
        list.add(stamp6);
        list.add(stamp7);
        list.add(stamp8);
        list.add(stamp9);
        list.add(stamp10);
    }
    public List<Stamp> getList(){
        return list;
    }
}
