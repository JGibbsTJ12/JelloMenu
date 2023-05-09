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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class OrganizePlan {
    public static void SortPlan() {
        //Hibernate session creation
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

        //Query to receive weekids and meal names from database for further use
        List results1 = session.createQuery("SELECT DISTINCT weekID FROM Week").list();
        List results2 = session.createQuery("SELECT name FROM Meals").list();
        //Converts lists into arrays for combo box usage
        ArrayList<String> weekList = new ArrayList<String>();
        weekList.addAll(results1);
        String[] mealList = new String[results2.size()];
        slisttoArr(results2, mealList);
        if(weekList.size() == 0){
            weekList.add(newWeek());
        }

        //Array creation for combo boxes
        int l = mealList.length + 1;
        String[] meals1 = new String[l]; meals1[0] = "Breakfast";
        String[] meals2 = new String[l]; meals2[0] = "Lunch";
        String[] meals3 = new String[l]; meals3[0] = "Dinner";
        for(int a = 0; a < mealList.length; a++){
            meals1[a + 1] = mealList[a];
            meals2[a + 1] = mealList[a];
            meals3[a + 1] = mealList[a];
        }

        //Creates 21 week objects for each meal and each day
        String[] days = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] menu = new String[] {"Breakfast", "Lunch", "Dinner"};

        //Window Setup
        JFrame f = new JFrame("Organize Meal Plan"); f.setSize(600, 300);
        JPanel p = new JPanel();
        JButton cf = new JButton("Confirm"); cf.setBounds(475, 230, 100, 25);
        p.setBounds(0, 0, 400, 1000);
        JScrollPane opp = new JScrollPane(p); opp.setBounds(150, 10, 400, 210);
        opp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp.setBorder(BorderFactory.createTitledBorder("Week"));
        JComboBox day1 = new JComboBox(days);
        day1.setBounds(5, 5, 125, 25);
        JComboBox meal1 = new JComboBox(meals1);
        JComboBox meal2 = new JComboBox(meals2);
        JComboBox meal3 = new JComboBox(meals3);
        JTextArea mta = new JTextArea("Notes");
        mta.setLineWrap(true);
        mta.setBounds(5, 75, 380, 125);
        meal1.setBounds(5, 40, 90, 25);
        meal2.setBounds(105, 40, 90, 25);
        meal3.setBounds(205, 40, 90, 25);
        JComboBox wcb = new JComboBox();
        JButton nwb = new JButton("New");
        wcb.setBounds(5, 10, 120, 25);
        nwb.setBounds(5, 45, 60, 25);
        p.add(day1); p.add(meal1); p.add(meal2); p.add(meal3); p.add(mta);
        f.add(opp); f.add(cf); f.add(wcb); f.add(nwb);
        p.setLayout(null);
        f.setLayout(null);
        f.setVisible(true);

        if(weekList.size() == 1){
            session.beginTransaction();
            int b = 0;
            int c = 0;
            for(int a = 0; a < 21; a++) {
                Week w = new Week();
                w.setWeekID(weekList.get(0));
                if (b == 7) {
                    b = 0;
                }
                w.setDay(days[b]);
                if (c == 2)
                    b++;
                if (c == 3) {
                    c = 0;
                }
                w.setMenu(menu[c]);
                c++;
                session.persist(w);
            }
            session.getTransaction().commit();
            wcb.addItem(weekList.get(0));
        }

        nwb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weekList.add(newWeek());
                session.beginTransaction();
                int b = 0;
                int c = 0;
                for(int a = 0; a < 21; a++) {
                    Week w = new Week();
                    w.setWeekID(weekList.get(weekList.size() - 1));
                    if (b == 7) {
                        b = 0;
                    }
                    w.setDay(days[b]);
                    if (c == 2)
                        b++;
                    if (c == 3) {
                        c = 0;
                    }
                    w.setMenu(menu[c]);
                    c++;
                    session.persist(w);
                }
                session.getTransaction().commit();
                wcb.addItem(weekList.get(weekList.size() - 1));
                wcb.setSelectedIndex(weekList.size() - 1);
            }
        });
        cf.addActionListener(new ActionListener() {
            /* Confirm Meal Button
            Starts a hibernate session, uses math with dayids in the database to properly index and input meals into the database*/
            @Override
            public void actionPerformed(ActionEvent e) {
                //Index "Algorithm" x * 3 + (y + 1) = z
                session.beginTransaction();
                int dbIndexb = (day1.getSelectedIndex() * 3) + 1;
                int dbIndexl = (day1.getSelectedIndex() * 3) + 2;
                int dbIndexd = (day1.getSelectedIndex() * 3) + 3;
                Week w = session.load(Week.class, dbIndexb);
                Week w1 = session.load(Week.class, dbIndexl);
                Week w2 = session.load(Week.class, dbIndexd);
                w.setMealID(meal1.getSelectedIndex());
                w1.setMealID(meal2.getSelectedIndex());
                w2.setMealID(meal3.getSelectedIndex());
                session.merge(w);
                session.merge(w1);
                session.merge(w2);
                session.getTransaction().commit();
            }
        });

        f.addWindowListener(new WindowListener() {
            //Window listener to ensure hibernate session is properly closed
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                session.close();
                sessionFactory.close();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public static String newWeek(){
        //Creates a date for the next monday from system date
        LocalDate date = LocalDate.now();
        date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        return date.toString();
    }

    public static String[] slisttoArr(List l, String[] a){
        //Converts a string list into a string array
        for(int x = 0; x < l.size(); x++)
            a[x] = (String) l.get(x);
        return a;
    }

    public static int[] ilisttoArr(List l, int[] a){
        //Converts an integer list into an integer array
        for(int x = 0; x < l.size(); x++)
            a[x] = (int) l.get(x);
        return a;
    }
}
