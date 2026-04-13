
import java.awt.*;
import javax.swing.*;

public class LogisticaExpress {
    public static void main(String[] args) {

        JFrame janela = new JFrame();
        janela.setSize(200, 250);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setSize(300, 300);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel();
        label.setText("Logística Express");

        JLabel label1 = new JLabel();
        label1.setText("Distância(km)");
        JTextField textDistancia = new JTextField(10);
        textDistancia.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label2 = new JLabel();
        label2.setText("Peso(kg)");
        JTextField textPeso = new JTextField(10);
        textPeso.setAlignmentX(Component.LEFT_ALIGNMENT);

        String[] opcoes = { "Normal", "Expresso" };
        JComboBox<String> comboEnvio = new JComboBox<>(opcoes);
        comboEnvio.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btn = new JButton("Calcular");

        JLabel resultado = new JLabel();

        textDistancia.setMaximumSize(new Dimension(150, 25));
        textPeso.setMaximumSize(new Dimension(150, 25));
        comboEnvio.setMaximumSize(new Dimension(100, 25));

        painel.add(label);

        painel.add(label1);
        painel.add(textDistancia);

        painel.add(label2);
        painel.add(textPeso);

        painel.add(comboEnvio);

        painel.add(btn);

        painel.add(resultado);

        janela.add(painel);

        btn.addActionListener(e -> {

            double distancia = Double.parseDouble(textDistancia.getText());
            double peso = Double.parseDouble(textPeso.getText());
            String envio = comboEnvio.getSelectedItem().toString();

            Frete frete;

            if (envio.equals("Normal")) {
                frete = new Frete(distancia, peso);
            } else {
                frete = new FreteExpresso(distancia, peso);
            }

            double valor = frete.calcularValor();

            resultado.setText("Valor do frete:: " + valor);

        });

        janela.setVisible(true); // final

    }
}

class Frete {
    protected double distancia;
    protected double peso;

    public Frete(double distancia, double peso) {
        this.distancia = distancia;
        this.peso = peso;
    }

    public double calcularValor() {
        double valor = 10;

        valor += 0.5 * distancia;

        if (peso > 20) {
            valor += 30;
        }
        
        return valor;
    }
}

class FreteExpresso extends Frete {

    public FreteExpresso(double distancia, double peso) {
        super(distancia, peso);
    }

    @Override
    public double calcularValor() {
        double valor = super.calcularValor();
        valor += valor * 0.2;
        return valor;
    }
}
