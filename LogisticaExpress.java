
import javax.swing.*;

public class LogisticaExpress {
    public static void main(String[] args) {

        JFrame janela = new JFrame();
        janela.setSize(300, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setSize(300, 300);

        JLabel label = new JLabel();
        label.setText("Logística Express");

        JLabel label1 = new JLabel();
        label1.setText("Distância(km)");
        JTextField textDistancia = new JTextField(10);

        JLabel label2 = new JLabel();
        label2.setText("Peso(kg)");
        JTextField textPeso = new JTextField(10);

        String[] opcoes = { "Normal", "Expresso" };
        JComboBox<String> comboEnvio = new JComboBox<>(opcoes);

        JButton btn = new JButton("Calcular");

        JLabel resultado = new JLabel();

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
