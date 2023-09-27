package org.example;


import org.example.Model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();){
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            List<Person> personList = session.createQuery("from Person").getResultList();

            personList.stream().forEach(System.out::println);

            session.getTransaction().commit();
        }
    }
}
