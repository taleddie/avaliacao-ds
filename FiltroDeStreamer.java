import java.awt.*;
import javax.swing.*;

public class FiltroDeStreamer {
    public static void main(String[] args) {
        
        JFrame janela = new JFrame();
        janela.setSize(250, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0, 1, 5, 5));

        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel();
        label.setText("Filtro de Streamer");

        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label1 = new JLabel();
        label1.setText("Quantidade de inscritos:");
        JTextField textSubs = new JTextField(10);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label2 = new JLabel();
        label2.setText("Valor total das doações:");
        JTextField textDonates = new JTextField(10);

        JPanel linhaCombo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] opcoes = {"Youtube", "Twitch"};
        JComboBox<String> comboPlataforma = new JComboBox<>(opcoes);

        JPanel linhaBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btn = new JButton("Verificar");

        JLabel resultado = new JLabel();

        painel.add(titulo);
        titulo.add(label);

        painel.add(linha1);
        linha1.add(label1);
        linha1.add(textSubs);

        painel.add(linha2);
        linha2.add(label2);
        linha2.add(textDonates);

        painel.add(linhaCombo);
        linhaCombo.add(comboPlataforma);

        painel.add(linhaBtn);
        linhaBtn.add(btn);

        painel.add(resultado);

        janela.add(painel);

        btn.addActionListener(e -> {

            double subs = Double.parseDouble(textSubs.getText());
            double donates = Double.parseDouble(textDonates.getText());
            String plataforma = comboPlataforma.getSelectedItem().toString();

            Valor valor;

            if (plataforma.equalsIgnoreCase("Twitch")) {
                valor = new ValorTwitch(subs);
            } else {
                valor = new ValorYoutube(subs);
            }

            if ((valor.calcularValor()+donates) < 100){
                resultado.setText("<html>Valor total: R$ " + (valor.calcularValor() + donates) + ".<br>Saldo insuficiente para saque mínimo!</html>");
            } else {
                resultado.setText("<html>Valor total: R$ " + (valor.calcularValor() + donates) + ".</html>");
            }

        });

        janela.setVisible(true);

    }
}

class Valor {

    protected double subs;

    public Valor(double subs) {
        this.subs = subs;
    }

    public double calcularValor() {
        double valorSubs = subs * 10;
        return valorSubs;
    }

}

class ValorTwitch extends Valor {

    public ValorTwitch(double subs) {
        super(subs);
    }

    @Override
    public double calcularValor() {
        double valorSubs = super.calcularValor();
        valorSubs = valorSubs*0.5;
        return valorSubs;
    }
}

class ValorYoutube extends Valor {

    public ValorYoutube(double subs) {
        super(subs);
    }

    @Override
    public double calcularValor() {
        double valorSubs = super.calcularValor();
        valorSubs = valorSubs*0.7;
        return valorSubs;
    }
}