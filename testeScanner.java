import java.util.*;

public class testeScanner {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        double distancia;
        double peso;
        int envio;
        double valor = 10;

        System.out.println("======== Logistica Express ========");

        System.out.print("Distancia(km):: ");
        distancia = sc.nextDouble();
        System.out.print("Peso(kg):: ");
        peso = sc.nextDouble();
        System.out.print("Tipo de envio (1 = Normal / 2 = Expresso):: ");
        envio = sc.nextInt();

        valor += 0.50*distancia;

        if (peso > 20){
            valor += 30;
        } if (envio == 1) {
            valor += (valor*(20/100));
        }

        System.out.println("Frete:: "+valor);

    }
}
