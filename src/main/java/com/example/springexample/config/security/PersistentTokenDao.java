package com.example.springexample.config.security;

import com.example.springexample.entity.Logins;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository("persistentTokenRepository")
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
public class PersistentTokenDao implements PersistentTokenRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        Logins logins = new Logins();

        logins.setUsername(persistentRememberMeToken.getUsername());
        logins.setSerie(persistentRememberMeToken.getSeries());
        logins.setToken(persistentRememberMeToken.getTokenValue());
        logins.setUsed(persistentRememberMeToken.getDate());

        this.entityManager.persist(logins);
        flushAndClear();
    }

    @Override
    public void updateToken(String s, String s1, Date date) {

        String JPQL = "select a from Logins a where a.serie = :serie";

        Logins logins = (Logins) entityManager.createQuery(JPQL)
                .setParameter("serie", s)
                .getSingleResult();

        logins.setToken(s1);
        logins.setUsed(date);

        this.entityManager.merge(logins);
        flushAndClear();
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        String JPQL = "select a from Logins a where a.serie = :serie";

        Logins logins = (Logins) entityManager.createQuery(JPQL)
                .setParameter("serie", s)
                .getSingleResult();

        if(logins != null) {
            return new PersistentRememberMeToken(
                    logins.getUsername(),
                    logins.getSerie(),
                    logins.getToken(),
                    logins.getUsed());
        }

        return null;

    }

    @Override
    public void removeUserTokens(String s) {

        String JPQL = "delete from Logins where username = :username";

        entityManager.createQuery(JPQL)
                .setParameter("username", s)
                .executeUpdate();

        flushAndClear();
    }
}
