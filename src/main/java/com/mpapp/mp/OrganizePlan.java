package com.mpapp.mp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

public class OrganizePlan {
    public static void SortPlan() {
        fillInfo();
    }

    public static void fillInfo(){
        final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata = new MetadataSources(ssr)
                .addAnnotatedClass(Meals.class)
                .addAnnotatedClass(Ingredients.class)
                .addAnnotatedClass(Week.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory.openSession();

        List results1 = session.createQuery("SELECT weekID from Week").list();
        List results2 = session.createQuery("SELECT name FROM Meals").list();
        String[] weekList = new String[results1.size() + 1];
        String[] mealList = new String[results2.size()];
        listtoArr(results1, weekList);
        listtoArr(results2, mealList);
        weekList[weekList.length - 1] = newWeek();
        session.beginTransaction();
            Week w1 = new Week(); Week w2 = new Week(); Week w3 = new Week(); Week w4 = new Week(); Week w5 = new Week(); Week w6 = new Week(); Week w7 = new Week();
            Week[] wa = new Week[]  {w1, w2, w3, w4, w5, w6, w7};
            String[] days = new String[]    {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            for(int a = 0; a < 7; a++){
                wa[a].setWeekID(weekList[weekList.length - 1]);
            }
            for(int b = 0; b < 7; b++){
                wa[b].setDay(days[b]);
            }
            for(int c = 0; c < 7; c++){
                session.persist(wa[c]);
            }
            session.getTransaction().commit();
            session.close();
    }

    public static String newWeek(){
        LocalDate date = LocalDate.now();
        date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        SimpleDateFormat fmat = new SimpleDateFormat("MM/dd/yyyy");
        return date.toString();
    }

    public static String[] listtoArr(List l, String[] a){
        for(int x = 0; x < l.size(); x++)
            a[x] = (String) l.get(x);
        return a;
    }
}
