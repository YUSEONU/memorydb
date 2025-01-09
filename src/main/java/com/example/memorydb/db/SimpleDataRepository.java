package com.example.memorydb.db;

import com.example.memorydb.entity.Entity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

abstract public class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {

    private List<T> dataList = new ArrayList<T>();

    private static long index = 0;

    private Comparator<T> sort = new Comparator<T>() {

        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    //create , update


    @Override
    public T save(T data) {

        if(Objects.isNull(data)){
            throw new RuntimeException("Data is null");
        }

        //db에 데이터가 있는가?
        var prevData = dataList.stream()
                .filter(it -> {
                    return it.getId().equals(data.getId());
                })
                .findFirst();

        if(prevData.isPresent()){
            //기존 데이터가 있는 경우 업데이트
            dataList.remove(prevData.get()); //prevData는 Optional 타입이므로 get으로 반환해야함.
            dataList.add(data);
        } else {
            //없는 경우 create
            index++;
            data.setId(index);
            dataList.add(data);
        }

        return data;
    }

    //read
    @Override
    public Optional<T> findById(ID id) {
        return dataList.stream()
                .filter(it -> {
                        return (it.getId().equals(id));
                })
                .findFirst();
    }
    @Override
    public List<T> findAll() {
        return dataList
                .stream()
                .sorted(sort)
                .collect(Collectors.toList());
    }

    //delete
    @Override
    public void delete(ID id) {
        var deleteEntity = dataList.stream()
                .filter(it -> {
                    return (it.getId().equals(id));
                })
                .findFirst(); //findFirst의 반환 타입이 Optional이기 때문.
        if(deleteEntity.isPresent()) {
            dataList.remove(deleteEntity.get());//deleteEntity도 마찬가지임.
        }
    }
}
