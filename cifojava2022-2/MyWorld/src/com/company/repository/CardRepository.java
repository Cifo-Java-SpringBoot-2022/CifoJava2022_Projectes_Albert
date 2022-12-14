package com.company.repository;

import com.company.model.Card;
import com.company.utils.EntityManagerFactoryUtils;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CardRepository {

    public static  void create(Card cardToCreate){
        //create a manager to do all the CRUD operations with student object
        //i can create manager cause I created EntityManagerFactoryUtils
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        //manager call Transaction, that is, it is a state to persist
        EntityTransaction transaction = manager.getTransaction();
        //let s start with begin the operations, thanks to transaction object
        transaction.begin();
        //prepares the operation to be done
        manager.persist(cardToCreate);
        //this operation WRITES the object on the actual table
        transaction.commit();
        manager.close();
    }

    public static Card getCardById(long cardNumber) {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Card cardFound = manager.find( Card.class, cardNumber);
        //this operation WRITES the object on the actual table
        transaction.commit();
        manager.close();

        return  cardFound;
    }

    public static Card update(Card cardToUpdate) {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Card cardUpdated = manager.merge(cardToUpdate);

        transaction.commit();
        manager.close();


        return cardUpdated;
    }
}
