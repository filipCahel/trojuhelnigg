import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main  extends JFrame {
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JButton DoplnA;
    private JButton lzeVytvořitTrojůhelníkButton;
    private JPanel panel1;
    JFileChooser chooser;
    List<Strany> seznamStran;

    public Main() {
        this.$$$setupUI$$$();
        this.chooser = new JFileChooser(".");
        this.seznamStran = new ArrayList();
        this.initComponents();
    }

    private void $$$setupUI$$$() {
    }

    void initComponents() {
        this.DoplnA.addActionListener((e) -> {
            this.DoplnHodnotuA();
        });
        this.lzeVytvořitTrojůhelníkButton.addActionListener((e) -> {
            this.lzeVytvorit();
        });
    }

    public void DoplnHodnotuA() {
        if (this.textFieldA.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "vyplnte A", "chyba", 0);
        } else {
            int a = Integer.parseInt(this.textFieldA.getText());
            this.textFieldB.setText(String.valueOf(a));
            this.textFieldC.setText(String.valueOf(a));
        }

    }

    public void lzeVytvorit() {
        try {
            int a = Integer.parseInt(this.textFieldA.getText());
            int b = Integer.parseInt(this.textFieldB.getText());
            int c = Integer.parseInt(this.textFieldC.getText());
            if (a * a + b * b == c * c) {
                JOptionPane.showMessageDialog(this, "nefunguje", "uspěch", 1);
            } else {
                JOptionPane.showMessageDialog(this, "nefunguje", "chyba", 0);
            }
        } catch (NumberFormatException var4) {
            JOptionPane.showMessageDialog(this, "nefunguje", "chyba", 0);
        }

    }

    public void nacti() {
        int returnVal = this.chooser.showOpenDialog(this);
        if (returnVal == 0) {
            File file = this.chooser.getSelectedFile();

            try {
                Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));

                try {
                    String line = scanner.nextLine();
                    String[] parts = line.split(";");
                    this.textFieldA.setText(parts[0]);
                    this.textFieldB.setText(parts[1]);
                    this.textFieldC.setText(parts[2]);
                } catch (Throwable var7) {
                    try {
                        scanner.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }

                    throw var7;
                }

                scanner.close();
            } catch (IOException var8) {
                var8.printStackTrace();
            } catch (NumberFormatException var9) {
                JOptionPane.showMessageDialog(this, "neni číslo", "chyba", 0);
            }
        }

    }
    public static void main(String[] args) {
        TrojuhlenikAPP t = new TrojuhlenikAPP();
        t.setVisible(true);
        t.setContentPane(t.panel1);
        t.setDefaultCloseOperation(3);
        t.setSize(400, 170);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuSoubor = new JMenu("soubor");
        JMenu menuAkce = new JMenu("akce");
        menuBar.add(menuSoubor);
        menuBar.add(menuAkce);
        JMenuItem uloz = new JMenuItem("uloz");
        JMenuItem nacti = new JMenuItem("nacti");
        JMenuItem abc = new JMenuItem("A-> (B, C)");
        JMenuItem jetrojuhelnik = new JMenuItem("funguje trojuhelnik");
        menuSoubor.add(uloz);
        menuSoubor.add(nacti);
        menuAkce.add(abc);
        menuAkce.add(jetrojuhelnik);
        t.setJMenuBar(menuBar);
        uloz.addActionListener((e) -> {
            t.ulozit();
        });
        nacti.addActionListener((e) -> {
            t.nacti();
        });
    }

    public void ulozit() {
        int returnVal = this.chooser.showSaveDialog(this);
        if (returnVal == 0) {
            File file = this.chooser.getSelectedFile();

            try {
                PrintWriter writer = new PrintWriter(file);

                try {
                    int a = Integer.parseInt(this.textFieldA.getText());
                    int b = Integer.parseInt(this.textFieldB.getText());
                    int c = Integer.parseInt(this.textFieldC.getText());
                    writer.printf("%d;%d;%d", a, b, c);
                    this.textFieldA.setText("");
                    this.textFieldB.setText("");
                    this.textFieldC.setText("");
                } catch (Throwable var8) {
                    try {
                        writer.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }

                    throw var8;
                }

                writer.close();
            } catch (IOException var9) {
                var9.printStackTrace();
            } catch (NumberFormatException var10) {
                JOptionPane.showMessageDialog(this, "neni číslo", "chybička", 0);
            }
        }

    }


}
