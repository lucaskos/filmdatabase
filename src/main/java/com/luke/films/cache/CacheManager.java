package com.luke.films.cache;

import com.luke.films.config.CachingConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luke on 30.10.2017.
 */
@Component
@EnableCaching
public class CacheManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Cacheable("GenresDictionary")
    public List getObjectByKey() {
        List resultList = entityManager.createQuery("select r from GenresDictionary r").setHint("org.hibernate.cacheable", true)
                .getResultList();
        return resultList;
    }

}
