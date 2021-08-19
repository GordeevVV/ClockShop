package com.clockshop.service.repository;

import com.clockshop.service.entity.MechType;
import com.clockshop.service.entity.Stamp;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MechTypeRepository {
    List<MechType> list=new ArrayList<>();

    public MechTypeRepository() {
        MechType mechType1=new MechType(1,"Электронные");
        MechType mechType2=new MechType(2,"Кварцевые");
        MechType mechType3=new MechType(3,"Механические");
        MechType mechType4=new MechType(4,"Гибридные");
        list.add(mechType1);
        list.add(mechType2);
        list.add(mechType3);
        list.add(mechType4);

    }
    public List< MechType > getList(){
        return list;
    }
}
