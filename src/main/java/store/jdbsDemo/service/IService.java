package store.jdbsDemo.service;

import java.time.LocalDateTime;
import java.util.List;

public interface IService <ENTITY, DTO> {
    ENTITY create(DTO item);
    ENTITY read(long id);
    List<ENTITY> get();
    
}