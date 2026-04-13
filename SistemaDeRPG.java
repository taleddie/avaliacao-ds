import java.awt.*;
import javax.swing.*;

public class SistemaDeRPG {
    public static void main(String[] args) {

        JFrame janela = new JFrame();
        janela.setSize(200, 250);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setSize(200, 250);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); //poe na vertical, mas preenche o espaco inteiro

        JLabel label = new JLabel();
        label.setText("<html>Calculador de XP</html>");

        JLabel label1 = new JLabel();
        label1.setText("Nivel atual");
        JTextField nivel = new JTextField(10);
        nivel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label2 = new JLabel();
        label2.setText("XP acumulado");
        JTextField xpAcumulado = new JTextField(10);
        xpAcumulado.setAlignmentX(Component.LEFT_ALIGNMENT);

        //seletor
        String[] opcoes = { "Fácil", "Médio", "Difícil" }; //cria array
        JComboBox<String> dificuldade = new JComboBox<>(opcoes); //poe tudo num jcombo(componente do swing)
        dificuldade.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btn = new JButton("Calcular");

        JLabel resultado = new JLabel();

        //define o tamanhom maximo das entradas usando o awt
        nivel.setMaximumSize(new Dimension(150, 25));
        xpAcumulado.setMaximumSize(new Dimension(150, 25));
        dificuldade.setMaximumSize(new Dimension(100, 25));

        //ordem dos elementos adicionados ao painel
        painel.add(label);

        painel.add(label1);
        painel.add(nivel);

        painel.add(label2);
        painel.add(xpAcumulado);

        painel.add(dificuldade);

        painel.add(btn);

        painel.add(resultado);

        janela.add(painel);

        //açao do botao
        btn.addActionListener(e -> {

            double nv = Double.parseDouble(nivel.getText());
            double xp = Double.parseDouble(xpAcumulado.getText());
            String dif = dificuldade.getSelectedItem().toString();

            Nivel nivelObj;

            //switch case diminuido recomendado pelo copilot
            nivelObj = switch (dif) {
                case "Médio" -> new NivelMedio(nv, xp);
                case "Difícil" -> new NivelDificil(nv, xp);
                default -> new Nivel(nv, xp);
            };

            double xpGanho = nivelObj.calcularXP();
            double novoXP = xp + xpGanho;

            //imprime o resultado e zera o contador de xp
            if (novoXP >= 1000) {
                nv++;
                novoXP -= 1000;

                resultado.setText("<html>PARABÉNS! Você subiu para o nível " + (int) nv +"<br>XP restante: " + novoXP + "</html>");
            } else {
                resultado.setText("<html>XP total: " + novoXP + "</html>");
            }

            //muda os valores que estao dentro da entrada de dados (muito legalll)
            nivel.setText(String.valueOf((int) nv));
            xpAcumulado.setText(String.valueOf(novoXP));

        });

        janela.setVisible(true); // final

    }
}

//esquema de herança simplezao e pa
class Nivel {
    protected double nv;
    protected double xp;

    public Nivel(double nv, double xp) {
        this.nv = nv;
        this.xp = xp;
    }

    public double calcularXP() {
        return 100;
    }
}

class NivelMedio extends Nivel {

    public NivelMedio(double nv, double xp) {
        super(nv, xp);
    }

    @Override
    public double calcularXP() {
        return 100 * 1.5;
    }
}

class NivelDificil extends Nivel {

    public NivelDificil(double nv, double xp) {
        super(nv, xp);
    }

    @Override
    public double calcularXP() {
        return 100 * 2.0;
    }
}