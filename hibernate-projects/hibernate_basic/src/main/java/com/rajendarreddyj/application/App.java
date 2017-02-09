package com.rajendarreddyj.application;

import org.hibernate.Session;

import com.rajendarreddyj.hibernate.model.Stock;
import com.rajendarreddyj.hibernate.persistence.HibernateUtil;

public class App {

    public static void main(final String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Stock stock = new Stock();
        stock.setStockId(1);
        stock.setStockCode("123");
        stock.setStockName("RAJ1");
        session.save(stock);

        stock = new Stock();
        stock.setStockId(2);
        stock.setStockCode("124");
        stock.setStockName("RAJ2");

        session.save(stock);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

}
