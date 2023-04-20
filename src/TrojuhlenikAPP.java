import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrojuhlenikAPP extends JFrame {
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JButton DoplnA;
    private JButton lzeVytvořitTrojůhelníkButton;
    JPanel panel1;

    public TrojuhlenikAPP(){
        initComponents();
    }
    JFileChooser chooser = new JFileChooser(".");
    List<Strany> seznamStran = new ArrayList<>();
    void initComponents(){
        DoplnA.addActionListener(e-> DoplnHodnotuA());
        lzeVytvořitTrojůhelníkButton.addActionListener(e-> lzeVytvorit());

    }
    public void DoplnHodnotuA(){
        if (textFieldA.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Vyplňte prosím pole A", "Chyba", JOptionPane.ERROR_MESSAGE);
        } else {
            int a = Integer.parseInt(textFieldA.getText());
            textFieldB.setText(String.valueOf(a));
            textFieldC.setText(String.valueOf(a));
        }
    }
    public void lzeVytvorit() {
        try {
            int a = Integer.parseInt(textFieldA.getText());
            int b = Integer.parseInt(textFieldB.getText());
            int c = Integer.parseInt(textFieldC.getText());
            if ((a*a + b*b) == (c*c)) {
                JOptionPane.showMessageDialog(this, "Z těchto hodnot lze vytvořit pravoúhlý trojúhelník.", "Úspěch", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Z těchto hodnot nelze vytvořit pravoúhlý trojúhelník.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Zadané hodnoty nejsou platná čísla.", "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void nacti() {
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                textFieldA.setText(parts[0]);
                textFieldB.setText(parts[1]);
                textFieldC.setText(parts[2]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Zadané hodnoty nejsou platné čísla. Zadejte prosím platné čísla.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void ulozit() {
        int returnVal = chooser.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(file)) {
                int a = Integer.parseInt(textFieldA.getText());
                int b = Integer.parseInt(textFieldB.getText());
                int c = Integer.parseInt(textFieldC.getText());
                writer.printf("%d;%d;%d", a, b, c);
                textFieldA.setText("");
                textFieldB.setText("");
                textFieldC.setText("");
            } catch (IOException e) {
                // zpracování výjimky
                e.printStackTrace();
            } catch (NumberFormatException e) {
                // vstup nebyl v očekávaném formátu, zpracování výjimky
                JOptionPane.showMessageDialog(this, "Zadané hodnoty nejsou platné čísla. Zadejte prosím platné čísla.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void main(String[] args) {
        TrojuhlenikAPP t = new TrojuhlenikAPP();
        t.setVisible(true);
        t.setContentPane(t.panel1);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setSize(400,170);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuSoubor = new JMenu("soubor");
        JMenu menuAkce = new JMenu("akce");
        menuBar.add(menuSoubor);
        menuBar.add(menuAkce);
        JMenuItem uloz = new JMenuItem("ulož");
        JMenuItem nacti = new JMenuItem("načti");
        JMenuItem abc = new JMenuItem("A->(B, C)");
        JMenuItem jetrojuhelnik = new JMenuItem("lze vytvořit trojůhelník");
        menuSoubor.add(uloz);
        menuSoubor.add(nacti);
        menuAkce.add(abc);
        menuAkce.add(jetrojuhelnik);
        t.setJMenuBar(menuBar);
        uloz.addActionListener(e-> t.ulozit());
        nacti.addActionListener(e-> t.nacti());

    }

}
