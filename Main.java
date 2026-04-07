
import javax.swing.*;

public class Main {
    public static void main(String[] args){

        JFrame janela = new JFrame();
	    janela.setSize(300,400);
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

        JLabel label3 = new JLabel();
        label3.setText("Tipo de envio (1 = Normal / 2 = Expresso)");
        JTextField textEnvio = new JTextField(10);

        JButton btn = new JButton("Calcular");

        JLabel resultado = new JLabel();
	    

        
        
        painel.add(label);

        painel.add(label1);
        painel.add(textDistancia);

        painel.add(label2);
        painel.add(textPeso);

        painel.add(label3);
        painel.add(textEnvio);

        painel.add(btn);

        painel.add(resultado);

        janela.add(painel);

        btn.addActionListener(e -> {

            double a = Double.parseDouble(textDistancia.getText());
            double b = Double.parseDouble(textPeso.getText());
            int c = Integer.parseInt(textEnvio.getText());

            double valor = 10;

            valor += 0.50*a;

            if (b > 20){
              valor += 30;
            } if (c == 2) {
             valor += (valor*(20/100));
            } 

            resultado.setText("Valor do frete:: "+valor);
            
        });

        janela.setVisible(true); //final

    }
}

