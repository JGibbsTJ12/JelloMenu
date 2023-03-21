
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class EditMealList extends Meals{
    public static void EditList() {
        //Window Initialization
        JFrame f = new JFrame();
        JTextArea tf = new JTextArea();
        JLabel l1 = new JLabel
                ("Use the Text Box for Notes/Meals, Highlight Meal Names to Add Them to Plan");
        l1.setBounds(30, 5, 600, 25);
        tf.setBounds(0, 30, 600, 150);
        JButton bAddIng = new JButton("Add Ingredients");
        JButton bAddMeal = new JButton("Add Meal");
        bAddIng.setBounds(200, 200, 200, 25);
        bAddMeal.setBounds(200, 230, 200, 25);
        f.setSize(600,300);
        f.add(tf); f.add(bAddIng); f.add(l1); f.add(bAddMeal);
        f.setLayout(null);
        f.setVisible(true);

        //Add Meal Button Functionality
        bAddMeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tf.getSelectedText() != "" | tf.getSelectedText() != null){
                    addMeal(tf.getSelectedText());
                }
            }
        });

        //Add Ingredients Functionality
        bAddIng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void addMeal(String m){
        //Add Meal Functionality
        JFrame amf = new JFrame();
        amf.setSize(300,150);
        JLabel aml1 = new JLabel("Add Meal: " + m + "?");
        JButton y = new JButton("Yes");
        JButton n = new JButton("No");
        aml1.setBounds(25, 10, 200, 25);
        y.setBounds(10, 40, 60, 25);
        n.setBounds(80, 40, 60, 25);
        amf.add(aml1); amf.add(y); amf.add(n);
        amf.setLayout(null);
        amf.setVisible(true);

        y.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meals nm = new Meals();
                nm.setMealName(m);
                amf.setVisible(false);
                amf.dispose();
                System.out.println(nm);
            }
        });

        n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amf.setVisible(false);
                amf.dispose();
            }
        });
    }
}
