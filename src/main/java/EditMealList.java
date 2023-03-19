import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class EditMealList {
    public static void EditList() {
        JFrame f = new JFrame();
        JTextArea tf = new JTextArea();
        tf.setBounds(0, 50, 600, 150);
        JButton addIng = new JButton("Add Ingredients");
        addIng.setBounds(200, 170, 200, 25);
        f.setSize(600,300);
        f.add(tf); f.add(addIng);
        f.setLayout(null);
        f.setVisible(true);
    }
}

class meal {
    public String name;

    meal(String name){
        this.name = name;
    }
}
