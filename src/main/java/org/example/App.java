package org.example;


import org.example.Model.Actor;
import org.example.Model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Movie movie = new Movie("Pulp Fiction");
            Actor actor1 = new Actor("Hervey Keitel");
            Actor actor2 = new Actor("Samuel Jackson");

//            List.of - вообще неизсеняемый
//            Arrays.asList - изменяемый но фиксированого размера

//            1 Сторона

            movie.setActorList(new ArrayList<Actor>(List.of(actor1, actor2)));

//            2 стороона

//            просто создает список из одного элемента
            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));


            session.persist(movie);

            session.persist(actor1);
            session.persist(actor2);

            session.getTransaction().commit();
        }
    }
}
