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
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

public class OrganizePlan {
    public static void SortPlan() {
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
        Week w8 = new Week(); Week w9 = new Week(); Week w10 = new Week(); Week w11 = new Week(); Week w12 = new Week(); Week w13 = new Week(); Week w14 = new Week();
        Week w15 = new Week(); Week w16 = new Week(); Week w17 = new Week(); Week w18 = new Week(); Week w19 = new Week(); Week w20 = new Week(); Week w21 = new Week();
        Week[] wa = new Week[]  {w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, w13, w14, w15, w16, w17, w18, w19, w20, w21};
        String[] days = new String[]    {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] menu = new String[] {"Breakfast", "Lunch", "Dinner"};
        int b = 0;
        int c = 0;
        for(int a = 0; a < 21; a++) {
            wa[a].setWeekID(weekList[weekList.length - 1]);
            if (b == 7) {
                b = 0;
            }
            wa[a].setDay(days[b]);
            if (c == 2)
                b++;
            if (c == 3) {
                c = 0;
            }
            wa[a].setMenu(menu[c]);
            c++;
            session.persist(wa[a]);
        }
        session.getTransaction().commit();

        int l = mealList.length + 1;
        String[] meals1 = new String[l]; meals1[0] = "Breakfast";
        String[] meals2 = new String[l]; meals2[0] = "Lunch";
        String[] meals3 = new String[l]; meals3[0] = "Dinner";
        for(int a = 0; a < mealList.length; a++){
            meals1[a + 1] = mealList[a];
            meals2[a + 1] = mealList[a];
            meals3[a + 1] = mealList[a];
        }

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
        JComboBox wcb = new JComboBox(weekList);
        JButton nwb = new JButton("New");
        nwb.setToolTipText("Making a new week will permanently save the previous.");
        wcb.setBounds(5, 10, 120, 25);
        nwb.setBounds(5, 45, 60, 25);
        p.add(day1); p.add(meal1); p.add(meal2); p.add(meal3); p.add(mta);
        f.add(opp); f.add(cf); f.add(wcb); f.add(nwb);
        p.setLayout(null);
        f.setLayout(null);
        f.setVisible(true);

        cf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.beginTransaction();
                //Index "Algorithm" x * 3 + (y + 1) = z
                int dbIndexb = (day1.getSelectedIndex() * 3);
                int dbIndexl = (day1.getSelectedIndex() * 3) + 1;
                int dbIndexd = (day1.getSelectedIndex() * 3) + 2;
                wa[dbIndexb].setMealID(meal1.getSelectedIndex());
                wa[dbIndexl].setMealID(meal2.getSelectedIndex());
                wa[dbIndexd].setMealID(meal3.getSelectedIndex());
                session.merge(wa[dbIndexb]);
                session.merge(wa[dbIndexl]);
                session.merge(wa[dbIndexd]);
                session.getTransaction().commit();
            }
        });

        f.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                session.close();
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
        LocalDate date = LocalDate.now();
        date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        return date.toString();
    }

    public static String[] listtoArr(List l, String[] a){
        for(int x = 0; x < l.size(); x++)
            a[x] = (String) l.get(x);
        return a;
    }
}
