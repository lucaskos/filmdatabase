package com.luke.films.cache;

import com.luke.films.dictionaries.PersonRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Luke on 01.11.2017.
 */
@Component
public class PersonRoleDaoImpl implements DictionaryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Cacheable("dictionary")
    public List<PersonRole> getAll() {
        List resultList = entityManager.createQuery("select r from PersonRole r").setHint("org.hibernate.cacheable", true)
                .getResultList();
        return resultList;
    }
}
