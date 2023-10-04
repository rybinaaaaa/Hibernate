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


            Movie movie = new Movie("Reservoie Dogs");
            Actor actor = session.get(Actor.class, 14);

            movie.setActorList(new ArrayList<>(Collections.singletonList(actor)));

//            actor.setMovies(new ArrayList<>());
            actor.getMovies().add(movie);

            session.persist(movie);

            session.getTransaction().commit();
        }
    }
}
