package com.murphy.jdbc.demo;

import com.murphy.jdbc.entity.Instructor;
import com.murphy.jdbc.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor by primary key / id
            int instructorId = 1;
            System.out.println("Get instructor id=1: ");
            Instructor instructor = session.get(Instructor.class, instructorId);

            System.out.println("Found Instructor: " + instructor);

            // delete the instructors
            if(instructor != null) {
                System.out.println("Deleting the Instructor: " + instructor);

                // Note: this will ALSO delete associated "details" object
                // because of CascadeType.ALL
                session.delete(instructor);
            }

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
