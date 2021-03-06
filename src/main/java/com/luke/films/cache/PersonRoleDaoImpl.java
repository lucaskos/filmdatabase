package com.luke.films.cache;

import com.luke.films.common.CacheConstants;
import com.luke.films.common.QualifierConstants;
import com.luke.films.cache.dictionaries.PersonRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Luke on 01.11.2017.
 */
@Component(QualifierConstants.PERSON_DIC_DAO)
public class PersonRoleDaoImpl implements DictionaryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Cacheable(CacheConstants.CACHE_DICTIONARY)
    public List<PersonRole> getAll() {

        CriteriaQuery<PersonRole> query = entityManager.getCriteriaBuilder().createQuery(PersonRole.class);
        Root<PersonRole> root = query.from(PersonRole.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }
}
