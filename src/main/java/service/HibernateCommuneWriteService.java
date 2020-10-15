package service;

import entity.Commune;
import entity.CommunePart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateCommuneWriteService implements CommuneWriteSerivce {
    private SessionFactory sessionFactory;

    public HibernateCommuneWriteService() {
        try {
            sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void writeCommune(Commune commune) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(commune);

            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeCommunePart(CommunePart communePart) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(communePart);

            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
