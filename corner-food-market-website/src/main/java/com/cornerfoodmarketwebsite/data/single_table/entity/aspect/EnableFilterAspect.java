package com.cornerfoodmarketwebsite.data.single_table.entity.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.hibernate.Session;

import javax.persistence.EntityManager;

//@Aspect
//@Component
class EnableFilterAspect {

    @AfterReturning(
            pointcut = "bean(entityManagerFactory) && execution(* createEntityManager(..))",
            returning = "retVal")
    public void getSessionAfter(JoinPoint joinPoint, Object retVal) {
        if (retVal != null && EntityManager.class.isInstance(retVal)) {
            Session session = ((EntityManager) retVal).unwrap(Session.class);
            session.enableFilter("filterByCustomerId").setParameter("customerId",(short)1);//get from security context holder
        }
    }

}
