package com.luke.films.cache;

import com.luke.films.common.CacheConstants;
import com.luke.films.common.QualifierConstants;
import com.luke.films.cache.dictionaries.GenresDictionary;
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
@Component(QualifierConstants.GENRES_DIC_DAO)
public class GenresDaoImpl implements DictionaryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Cacheable(CacheConstants.CACHE_DICTIONARY)
    public List<GenresDictionary> getAll() {

        CriteriaQuery<GenresDictionary> query = entityManager.getCriteriaBuilder().createQuery(GenresDictionary.class);
        Root<GenresDictionary> root = query.from(GenresDictionary.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

}
