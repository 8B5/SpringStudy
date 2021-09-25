package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity>
        implements MemoryDbRepositoryifs<T>{

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
        //getIndex()는 MemoryDbEntity에 정의된 index를 뜻함
    }

    @Override
    public T save(T entity) {

        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if(optionalEntity.isEmpty()){
            //db에 데이터가 없는 경우
            index++;
            entity.setIndex(index);
            db.add(entity); //저장
            return entity;
        }else {
            //db에 데이터가 있는 경우
            var preIndex = optionalEntity.get().getIndex(); //이전의 데이터 index가져와서
            entity.setIndex(preIndex); //해당값세팅
            deleteById(preIndex); //이전 데이터 삭제
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if(optionalEntity.isPresent()){
            //데이터가 존재한다면
            db.remove(optionalEntity.get()); //해당 entity와 같은 것 찾아서 지움
        }
        
    }

    @Override
    public List<T> listAll() {
        return db; //db모든 내용 리턴
    }
}
