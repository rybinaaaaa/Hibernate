package org.example;


import org.example.Model.Item;
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
                .addAnnotatedClass(Item.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            Person person = session.get(Person.class, 1);

            Item newItem = new Item("Item from Hibername", person);

            session.persist(newItem);

//            Hibernate уже добавил это в базу данных, но в его кеше у родителя(оwner) еще нет этого айтема
//            Поэтому мы дополнительно добавляем этот айтем владельцу с список всех айтемов, на
//            sql бд это никак не повлияет

            person.getItems().add(newItem);

            session.getTransaction().commit();
        }
    }
}
