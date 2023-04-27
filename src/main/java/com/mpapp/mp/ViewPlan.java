package com.mpapp.mp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class ViewPlan {
    public static void ViewList() {
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

        //Queries for mealid, meal name, ingredient name, ingredient amount and ingredient amount measurement
        List mealResults = session.createQuery("SELECT mealID FROM Week").list();
        List mealNameResults = session.createQuery("SELECT name FROM Meals").list();
        List ingResults = session.createQuery("SELECT name FROM Ingredients").list();
        List ingAmtResults = session.createQuery("SELECT amount FROM Ingredients").list();
        List ingMsmtResults = session.createQuery("SELECT amtmsmt FROM Ingredients").list();
        //Adds queries to array for combobox usage
        int[] mID = new int[mealResults.size()];
        int[] iAmt = new int[ingAmtResults.size()];
        String[] mN = new String[mealNameResults.size() + 1];
        String[] iN = new String[ingResults.size()];
        String[] iMsmt = new String[ingMsmtResults.size()];
        OrganizePlan.slisttoArr(ingResults, iN);
        OrganizePlan.slisttoArr(ingMsmtResults, iMsmt);
        OrganizePlan.ilisttoArr(mealResults, mID);
        OrganizePlan.ilisttoArr(ingAmtResults, iAmt);
        slisttoArr(mealNameResults, mN);

        //Window creation
        JFrame f = new JFrame("View Plan"); f.setSize(800, 400);
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        p.setPreferredSize(new Dimension(375, 550));
        p1.setPreferredSize(new Dimension(375, 1000));
        JScrollPane opp = new JScrollPane(p); opp.setBounds(10, 5, 385, 350);
        JScrollPane opp1 = new JScrollPane(p1); opp1.setBounds(395, 5, 385, 350);
        opp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp.setBorder(BorderFactory.createTitledBorder("Plan"));
        opp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp1.setBorder(BorderFactory.createTitledBorder("List"));
        String[] dL = {"Monday - ", "Tuesday - ", "Wednesday - ", "Thursday - ", "Friday - ", "Saturday - ", "Sunday - "};
        String[] mL = {"Breakfast: ", "Lunch: ", "Dinner: "};
        //This for loop creates the meal plan labels with fewer lines of code using math
        int b = 0;
        for(int i = 0; i < 7; i++){
            p.add(new JLabel(dL[i])).setBounds(10, 10 + (i * 75), 75, 25);
            for(int a = 0; a < 3; a++){
                p.add(new JLabel(mL[a])).setBounds(10 + (a * 100), 35 + (i * 75), 100, 25);
                p.add(new JLabel(mN[mID[b]])).setBounds(10 + (a * 100), 60 + (i * 75), 100, 25);
                b++;
            }
        }
        //This for loop adds each ingredient with its amount to the Shopping List text area
        JTextArea shopList = new JTextArea();
        for(int i = 0; i < iN.length; i++){
            shopList.append(iN[i] + ": " + iAmt[i] + " " + iMsmt[i] + "\n");
        }
        shopList.setBounds(10, 10, 335, 980);
        p1.add(shopList);
        f.add(opp); f.add(opp1);
        p.setLayout(null);
        p1.setLayout(null);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static String[] slisttoArr(List l, String[] a){
        //Meal String to Array
        for(int x = 0; x < l.size() + 1; x++)
            if(x == 0)
                a[x] = "No Meal Found";
            else
                a[x] = (String) l.get(x - 1);
        return a;
    }
}
