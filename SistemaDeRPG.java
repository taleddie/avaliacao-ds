import javax.swing.*;

public class SistemaDeRPG {
    public static void main(String[] args) {

        JFrame janela = new JFrame();
        janela.setSize(300, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setSize(300, 300);

        JLabel label = new JLabel();
        label.setText("-----");

        JLabel label1 = new JLabel();
        label1.setText("Nivel atual");
        JTextField nivel = new JTextField(10);

        JLabel label2 = new JLabel();
        label2.setText("XP acumulado");
        JTextField xpAcumulado = new JTextField(10);

        String[] opcoes = { "Fácil", "Médio", "Difícil" };
        JComboBox<String> dificuldade = new JComboBox<>(opcoes);

        JButton btn = new JButton("Calcular");

        JLabel resultado = new JLabel();

        painel.add(label);

        painel.add(label1);
        painel.add(nivel);

        painel.add(label2);
        painel.add(xpAcumulado);

        painel.add(dificuldade);

        painel.add(btn);

        painel.add(resultado);

        janela.add(painel);

        btn.addActionListener(e -> {

            double nv = Double.parseDouble(nivel.getText());
            double xp = Double.parseDouble(xpAcumulado.getText());
            String dif = dificuldade.getSelectedItem().toString();

            Nivel nivelObj;

            if (dif.equals("Fácil")) {
                nivelObj = new Nivel(nv, xp);
            } else if (dif.equals("Médio")) {
                nivelObj = new NivelMedio(nv, xp);
            } else {
                nivelObj = new NivelDificil(nv, xp);
            }

            double valor = nivelObj.calcularValor();

            // Corrige a exibição do resultado usando HTML
            if (valor >= 1000) {
                nv++;
                valor -= 1000;
                resultado.setText("<html>PARABÉNS! Você subiu para o nível " + (int) nv +
                                  "<br>XP adquirido: " + valor + "</html>");
            } else {
                resultado.setText("<html>XP adquirido: " + valor + "</html>");
            }

            nivel.setText(String.valueOf((int) nv));
            xpAcumulado.setText(String.valueOf(valor+xp));

        });

        janela.setVisible(true); // final

    }
}

class Nivel {
    protected double nv;
    protected double xp;

    public Nivel(double nv, double xp) {
        this.nv = nv;
        this.xp = xp;
    }

    public double calcularValor() {
        return xp + 100;
    }
}

class NivelMedio extends Nivel {

    public NivelMedio(double nv, double xp) {
        super(nv, xp);
    }

    @Override
    public double calcularValor() {
        return xp + 100 * 1.5;
    }
}

class NivelDificil extends Nivel {

    public NivelDificil(double nv, double xp) {
        super(nv, xp);
    }

    @Override
    public double calcularValor() {
        return xp + 100 * 2;
    }
}