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
import java.util.Date;
import java.util.List;

public class OrganizePlan {
    public static void SortPlan(){
        /*
        MAKE ENTIRE CLASS MORE READABLE
        MAKE ENTIRE CLASS MORE READABLE
        */
        //DB Init
        final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata = new MetadataSources(ssr)
                .addAnnotatedClass(Meals.class)
                .addAnnotatedClass(Plan.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory.openSession();
        List results = session.createQuery("SELECT name FROM Meals").list();
        List results2 = session.createQuery("SELECT weekID from Plan").list();
        session.close();
        Session session1 = sessionFactory.openSession();
        if(results2.size() == 0){
            Plan p1 = new Plan();
            session1.beginTransaction();
            p1.setWeekID(newWeek());
            session1.persist(p1);
            session1.getTransaction().commit();
            results2 = session1.createQuery("SELECT weekID from Plan").list();
            session1.close();
        }

        //Window Init
        JFrame f = new JFrame("Organize Meal Plan");
        JPanel p = new JPanel();
        f.setSize(600,300);
        JButton c = new JButton("Confirm");
        c.setBounds(475, 230, 100, 25);
        p.setBounds(0, 0, 400, 1000);
        JScrollPane opp = new JScrollPane(p);
        opp.setBounds(150, 10, 400, 210);
        opp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp.setBorder(BorderFactory.createTitledBorder("Week"));
        String[] days = {"Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"};
        JComboBox day1 = new JComboBox(days);
        day1.setBounds(5, 5, 125, 25);
        String[] meals1 = new String[results.size() + 1];
        String[] meals2 = new String[results.size() + 1];
        String[] meals3 = new String[results.size() + 1];
        meals1[0] = "Breakfast";
        meals2[0] = "Lunch";
        meals3[0] = "Dinner";
        for(int x = 1; x < results.size() + 1; x++)
            meals1[x] = (String) results.get(x - 1);
        for(int y = 1; y < results.size() + 1; y++)
            meals2[y] = (String) results.get(y - 1);
        for(int z = 1; z < results.size() + 1; z++)
            meals3[z] = (String) results.get(z - 1);
        JComboBox meal1 = new JComboBox(meals1);
        JComboBox meal2 = new JComboBox(meals2);
        JComboBox meal3 = new JComboBox(meals3);
        JTextArea mta = new JTextArea("Notes");
        mta.setLineWrap(true);
        mta.setBounds(5, 75, 380, 125);
        meal1.setBounds(5, 40, 90, 25);
        meal2.setBounds(105, 40, 90, 25);
        meal3.setBounds(205, 40, 90, 25);
        String[] weeks = new String[results2.size()];
        for(int a = 0; a < results2.size(); a++) {
            weeks[a] = (String) results2.get(a);
        }
        JComboBox wcb = new JComboBox(weeks);
        JButton nwb = new JButton("New");
        nwb.setToolTipText("Making a new week will permanently save the previous.");
        wcb.setBounds(5, 10, 120, 25);
        nwb.setBounds(5, 45, 60, 25);
        p.add(day1); p.add(meal1); p.add(meal2); p.add(meal3); p.add(mta);
        f.add(opp); f.add(c); f.add(wcb); f.add(nwb);
        p.setLayout(null);
        f.setLayout(null);
        f.setVisible(true);

        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session2 = sessionFactory.openSession();
                session2.beginTransaction();
                for(int i=0; i < 3; i++){
                    Plan p3 = new Plan();
                    p3.setWeekID(weeks[wcb.getSelectedIndex()]);
                    p3.setDay(days[day1.getSelectedIndex()]);
                    p3.setMeal(i);
                    if(i == 0){
                        p3.setMealID(meal1.getSelectedIndex());
                    }
                    if(i == 1){
                        p3.setMealID(meal2.getSelectedIndex());
                    }
                    if(i == 2){
                        p3.setMealID(meal3.getSelectedIndex());
                    }
                    if(mta.getSelectedText() != "Notes")
                        p3.setNotes(mta.getText());
                    session2.persist(p3);
                }
                session2.getTransaction().commit();
                session2.close();
            }
        });

        nwb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.createQuery
                        ("UPDATE Plan SET weekHasInfo = 1 WHERE " +
                                "weekID = " + "\"" + weeks[wcb.getSelectedIndex()] + "\"")
                                .executeUpdate();
                session.getTransaction().commit();
                Plan p = new Plan();
                session.beginTransaction();
                p.setWeekID(newWeek());
                session.persist(p);
                session.getTransaction().commit();
                session1.close();
                sessionFactory.close();
            }
        });
    }
    public static String newWeek(){
        Date date = new Date();
        SimpleDateFormat fmat = new SimpleDateFormat("MM/dd/yyyy");
        return fmat.format(date);
    }
}
