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
    public static void SortPlan() {
        fillInfo();
        //Window Init
        JFrame f = new JFrame("Organize Meal Plan");
        JPanel p = new JPanel(); f.setSize(600, 300);
        JButton c = new JButton("Confirm"); c.setBounds(475, 230, 100, 25);
        p.setBounds(0, 0, 400, 1000);
        JScrollPane opp = new JScrollPane(p); opp.setBounds(150, 10, 400, 210);
        opp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp.setBorder(BorderFactory.createTitledBorder("Week"));
        String[] days = {"Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"};
        JComboBox day1 = new JComboBox(days);
        day1.setBounds(5, 5, 125, 25);
        String[] meals1 = new String[mName.size() + 1];
        String[] meals2 = new String[mName.size() + 1];
        String[] meals3 = new String[mName.size() + 1];
        meals1[0] = "Breakfast";
        meals2[0] = "Lunch";
        meals3[0] = "Dinner";
        for (int x = 1; x < mName.size() + 1; x++)
            meals1[x] = (String) mName.get(x - 1);
        for (int y = 1; y < mName.size() + 1; y++)
            meals2[y] = (String) mName.get(y - 1);
        for (int z = 1; z < mName.size() + 1; z++)
            meals3[z] = (String) mName.get(z - 1);
        JComboBox meal1 = new JComboBox(meals1);
        JComboBox meal2 = new JComboBox(meals2);
        JComboBox meal3 = new JComboBox(meals3);
        JTextArea mta = new JTextArea("Notes");
        mta.setLineWrap(true);
        mta.setBounds(5, 75, 380, 125);
        meal1.setBounds(5, 40, 90, 25);
        meal2.setBounds(105, 40, 90, 25);
        meal3.setBounds(205, 40, 90, 25);

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
    }

    public static void fillInfo(){

    }

    public static String newWeek(){
        Date date = new Date();
        SimpleDateFormat fmat = new SimpleDateFormat("MM/dd/yyyy");
        return fmat.format(date);
    }
}
