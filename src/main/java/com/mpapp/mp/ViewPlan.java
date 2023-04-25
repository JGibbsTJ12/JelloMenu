package com.mpapp.mp;

import javax.swing.*;

public class ViewPlan {
    public static void ViewList() {
        JFrame f = new JFrame("View Plan"); f.setSize(800, 400);
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        p.setBounds(0, 0, 200, 1000);
        p1.setBounds(0, 0, 200, 1000);
        JScrollPane opp = new JScrollPane(p); opp.setBounds(10, 10, 385, 300);
        JScrollPane opp1 = new JScrollPane(p1); opp1.setBounds(395, 10, 385, 300);
        opp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp.setBorder(BorderFactory.createTitledBorder("Plan"));
        opp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp1.setBorder(BorderFactory.createTitledBorder("List"));
        f.add(opp); f.add(opp1);
        p.setLayout(null);
        p1.setLayout(null);
        f.setLayout(null);
        f.setVisible(true);
    }
}
