package org.example;


import org.example.Model.Director;
import org.example.Model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class).addAnnotatedClass(Movie.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

//            1 task

//            Director director = session.createQuery("from Director", Director.class).stream().findAny().orElse(null);
//            if (director.getMovies() != null) {
//                director.getMovies().forEach(System.out::println);
//            }

//            3 task

//            Director director = session.get(Director.class, 1);
//            Movie movie = new Movie("Test movie", director);
//
//            session.persist(movie);

//            director.getMovies().forEach(System.out::println);

//            4 task

//            Movie movie = session.get(Movie.class, 1);
//
//            Director director = new Director("Test director");
//
//            session.persist(director);
//
//            movie.setDirector(director);

//            ---------------------------

            Director director = session.get(Director.class, 1);
            Movie movie1 = new Movie("Test movie1", director);
            Movie movie2 = new Movie("Test movie2", director);
            Movie movie3 = new Movie("Test movie3", director);

            director.getMovies().addAll(List.of(movie1, movie2, movie3));
            director.getMovies().forEach(System.out::println);

            session.getTransaction().commit();
        }
    }
}
