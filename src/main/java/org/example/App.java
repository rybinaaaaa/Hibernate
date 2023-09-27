package org.example;


import org.example.Model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();){
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Person person1 = new Person("test1", 1);
            Person person2 = new Person("test2", 2);
            Person person3 = new Person("test3", 3);
            Person person4 = new Person("test4", 4);

            session.persist(person1);
            session.persist(person2);
            session.persist(person3);
            session.persist(person4);

            session.getTransaction().commit();
        }
    }
}
