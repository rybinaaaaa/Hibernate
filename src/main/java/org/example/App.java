package org.example;


import org.example.Model.Principal;
import org.example.Model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Principal.class).addAnnotatedClass(School.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession();
        ) {
            session.beginTransaction();

            // 1 ex

            Principal principal =  session.createQuery("from Principal where school != null", Principal.class).stream().findAny().orElse(null);
            School school0 = principal.getSchool();

            // 2 ex
            school0.setPrincipal(null);

            Principal principal1 = new Principal("test", 99);
            School school = new School(99, principal1);

            session.persist(school);

//          для  hibernate кеша

            principal1.setSchool(school);
            school.setPrincipal(principal);

            principal.setSchool(school);

            session.getTransaction().commit();
        }
    }
}
