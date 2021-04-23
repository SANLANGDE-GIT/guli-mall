package com.atguigu.gulimall.order.test;

import com.atguigu.gulimall.order.entity.OrderEntity;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {

    static List<OrderEntity> orderEntityList = new ArrayList<>();

    static {
        OrderEntity orderEntity1=new OrderEntity();
        orderEntity1.setId(1L);
        orderEntity1.setMemberId(2L);
        orderEntity1.setOrderSn(UUID.randomUUID().toString());

        OrderEntity orderEntity2=new OrderEntity();
        orderEntity2.setId(2L);
        orderEntity2.setMemberId(2L);
        orderEntity2.setOrderSn(UUID.randomUUID().toString());

        OrderEntity orderEntity3=new OrderEntity();
        orderEntity3.setId(3L);
        orderEntity3.setMemberId(3L);
        orderEntity3.setOrderSn(UUID.randomUUID().toString());

        OrderEntity orderEntity4=new OrderEntity();
        orderEntity4.setId(4L);
        orderEntity4.setMemberId(3L);
        orderEntity4.setOrderSn(UUID.randomUUID().toString());

        orderEntityList.add(orderEntity1);
        orderEntityList.add(orderEntity2);
        orderEntityList.add(orderEntity3);
        orderEntityList.add(orderEntity4);
    }

    public static void main(String[] args) {
        Set<Long> collect = StreamDemo.orderEntityList.parallelStream().map(OrderEntity::getMemberId).collect(Collectors.toSet());
        collect.forEach(System.out::println);

        Map<Long, List<OrderEntity>> longListMap = orderEntityList.parallelStream().collect(Collectors.groupingBy(OrderEntity::getMemberId));

        longListMap.entrySet().forEach(entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });

        /*Map<Long,List<OrderEntity>> map=new HashMap<>();
        orderEntityList.forEach(orderEntity -> {
            List<OrderEntity> list=new ArrayList<>();
            if (map.containsKey(orderEntity.getMemberId())) {
                list=map.get(orderEntity.getMemberId());
                list.add(orderEntity);
                map.put(orderEntity.getMemberId(),list);
            }else {
                list.add(orderEntity);
                map.put(orderEntity.getMemberId(),list);
            }
        });

        map.entrySet().forEach(maps->{
            System.out.println(maps.getKey());
            System.out.println(maps.getValue());
        });*/

    }
}
