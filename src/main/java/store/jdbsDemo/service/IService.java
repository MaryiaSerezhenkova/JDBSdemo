package store.jdbsDemo.service;

import java.util.List;

public interface IService <ENTITY, DTO> {
    DTO create(DTO item);
    DTO read(long id);
    List<ENTITY> get();
    
}