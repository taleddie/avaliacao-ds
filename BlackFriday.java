
import javax.swing.*;

public class BlackFriday {
    public static void main(String[] args) {

        JFrame janela = new JFrame();
        janela.setSize(300, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setSize(300, 300);

        JLabel label = new JLabel();
        label.setText("Black Friday");

        JLabel label1 = new JLabel();
        label1.setText("Valor da compra");
        JTextField textCompra = new JTextField(10);

        JLabel label2 = new JLabel();
        label2.setText("Cupom");
        JTextField textCupom = new JTextField(10);

        JButton btn = new JButton("Calcular");

        JLabel resultado = new JLabel();

        painel.add(label);

        painel.add(label1);
        painel.add(textCompra);

        painel.add(label2);
        painel.add(textCupom);

        painel.add(btn);

        painel.add(resultado);

        janela.add(painel);

        btn.addActionListener(e -> {

            double compra = Double.parseDouble(textCompra.getText());
            String cupom = textCupom.getText();

            Desconto desconto;

            if (cupom.equalsIgnoreCase("AMIGAO10")) {
                desconto = new DescontoCupom(compra, cupom);
            } else {
                desconto = new Desconto(compra, cupom);
            }

            double valor = desconto.calcularValor();

            resultado.setText("Valor do frete:: " + valor);

        });

        janela.setVisible(true); // final

    }
}

class Desconto {
    protected double compra;
    protected String cupom;

    public Desconto(double compra, String cupom) {
        this.compra = compra;
        this.cupom = cupom;
    }

    public double calcularValor() {
        double valor = 0;

        valor += compra;

        if (valor > 500) {
            valor -= valor*0.10;
        }
        
        return valor;
    }
}

class DescontoCupom extends Desconto {

    public DescontoCupom(double compra, String cupom) {
        super(compra, cupom);
    }

    @Override
    public double calcularValor() {
        double valor = super.calcularValor();
        valor -= 10;
        return valor;
    }
}
