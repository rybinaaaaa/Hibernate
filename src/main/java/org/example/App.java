package org.example;


import org.example.Model.Actor;
import org.example.Model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


           Actor actor = session.get(Actor.class, 15);

//            System.out.println(actor.getMovies());
            actor.getMovies().forEach(System.out::println);

            Movie movieToRemove = actor.getMovies().get(0);

//            1 сторона

            actor.getMovies().remove(0);

//            Надо перенастроить hash-code и equals
//            2 сторона
            movieToRemove.getActorList().remove(actor);

            session.getTransaction().commit();
        }
    }
}
