package org.example;


import org.example.Model.Item;
import org.example.Model.Passport;
import org.example.Model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class).addAnnotatedClass(Passport.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Person person = new Person("new test oneToOne", 99);

            Passport passport = new Passport(person, 123456);

//            установление связи со 2 стороны

            person.setPassport(passport);

// обязательно утсанрвить связь надо со стороны, где используется аннотация @JoinColumn

            session.persist(person);

            session.getTransaction().commit();
        }
    }
}
