import java.util.*;

public class respostas_target {
    private static final String JSON_DATA = "[ " +
            "{\"dia\": 1, \"valor\": 1000.50}, " +
            "{\"dia\": 2, \"valor\": 800.00}, " +
            "{\"dia\": 3, \"valor\": 1200.00}, " +
            "{\"dia\": 4, \"valor\": 1400.00}, " +
            "{\"dia\": 5, \"valor\": 2000.00}, " +
            "{\"dia\": 6, \"valor\": 0.0}, " +
            "{\"dia\": 7, \"valor\": 0.0}, " +
            "{\"dia\": 8, \"valor\": 2100.00}, " +
            "{\"dia\": 9, \"valor\": 1300.80}, " +
            "{\"dia\": 10, \"valor\": 1250.00}, " +
            "{\"dia\": 11, \"valor\": 1320.00}, " +
            "{\"dia\": 12, \"valor\": 1000.00}, " +
            "{\"dia\": 13, \"valor\": 0.0}, " +
            "{\"dia\": 14, \"valor\": 0.0}, " +
            "{\"dia\": 15, \"valor\": 1120.00}, " +
            "{\"dia\": 16, \"valor\": 1320.00}, " +
            "{\"dia\": 17, \"valor\": 1500.20}, " +
            "{\"dia\": 18, \"valor\": 1070.00}, " +
            "{\"dia\": 19, \"valor\": 2150.00}, " +
            "{\"dia\": 20, \"valor\": 0.0}, " +
            "{\"dia\": 21, \"valor\": 0.0}, " +
            "{\"dia\": 22, \"valor\": 1340.00}, " +
            "{\"dia\": 23, \"valor\": 1330.00}, " +
            "{\"dia\": 24, \"valor\": 1800.00}, " +
            "{\"dia\": 25, \"valor\": 1900.00}, " +
            "{\"dia\": 26, \"valor\": 1950.00}, " +
            "{\"dia\": 27, \"valor\": 0.0}, " +
            "{\"dia\": 28, \"valor\": 0.0}, " +
            "{\"dia\": 29, \"valor\": 1550.20}, " +
            "{\"dia\": 30, \"valor\": 1520.40} " +
            "]";

    public static void ex1() {
        int indice = 13, soma = 0, k = 0;

        while (k < indice) {
            k = k + 1;
            soma = soma + k;
        }
        System.out.println("Resposta questão 1: " + soma);
    }

    public static boolean ex2_fibonacci(int n) {
        if (n == 0 || n == 1) {
            return true;
        }
        int anterior = 0, proximo = 1, soma;
        while (proximo < n) {
            soma = anterior + proximo;
            anterior = proximo;
            proximo = soma;
        }
        return n == proximo;
    }

    public static void ex3() {
        List<Double> faturamentoDiario = extrairValoresDoJSON(JSON_DATA);

        double menorFaturamento = Double.MAX_VALUE;
        double maiorFaturamento = Double.MIN_VALUE;
        double somaFaturamento = 0;

        for (double valor : faturamentoDiario) {
            if (valor < menorFaturamento) menorFaturamento = valor;
            if (valor > maiorFaturamento) maiorFaturamento = valor;
            somaFaturamento += valor;
        }

        double mediaMensal = somaFaturamento / faturamentoDiario.size();

        long diasAcimaMedia = faturamentoDiario.stream()
                .filter(valor -> valor > mediaMensal)
                .count();

        System.out.println("Resposta questão 3:");
        System.out.println("Menor faturamento: " + menorFaturamento);
        System.out.println("Maior faturamento: " + maiorFaturamento);
        System.out.println("Número de dias acima da média: " + diasAcimaMedia);
    }

    private static List<Double> extrairValoresDoJSON(String json) {
        List<Double> valores = new ArrayList<>();
        String[] objetos = json.substring(1, json.length() - 1).split("},");

        for (String objeto : objetos) {
            int indexValor = objeto.indexOf("\"valor\"");
            if (indexValor != -1) {
                int indexDoisPontos = objeto.indexOf(":", indexValor);
                int indexFimValor = objeto.indexOf("}", indexDoisPontos);
                if (indexFimValor == -1) indexFimValor = objeto.length();
                String valorStr = objeto.substring(indexDoisPontos + 1, indexFimValor).trim();
                double valor = Double.parseDouble(valorStr);
                if (valor > 0) {
                    valores.add(valor);
                }
            }
        }

        return valores;
    }

    public static void ex4() {
        Map<String, Double> faturamentoPorEstado = new LinkedHashMap<>();
        faturamentoPorEstado.put("SP", 67836.43);
        faturamentoPorEstado.put("RJ", 36678.66);
        faturamentoPorEstado.put("MG", 29229.88);
        faturamentoPorEstado.put("ES", 27165.48);
        faturamentoPorEstado.put("Outros", 19849.53);

        double totalFaturamento = 0;
        for (double faturamento : faturamentoPorEstado.values()) {
            totalFaturamento += faturamento;
        }

        System.out.println("Resposta questão 4:");
        for (Map.Entry<String, Double> entry : faturamentoPorEstado.entrySet()) {
            String estado = entry.getKey();
            double faturamento = entry.getValue();
            double percentual = (faturamento / totalFaturamento) * 100;
            System.out.printf("%s: %.2f%%\n", estado, percentual);
        }
    }

    public static void ex5() {
        String entrada = ("inverter");

        char[] charArray = entrada.toCharArray();
        int left = 0;
        int right = charArray.length - 1;

        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            left++;
            right--;
        }

        String stringInvertida = new String(charArray);
        System.out.println("String original: " + entrada);
        System.out.println("String invertida: " + stringInvertida);
    }

    public static void main(String[] args) {
        // Questão 1
        ex1();

        // Questão 2
        int entrada = 5;
        if (ex2_fibonacci(entrada)) {
            System.out.println("Resposta questão 2: " + entrada + " pertence a sequência de Fibonacci");
        } else {
            System.out.println("Resposta questão 2: " + entrada + " não pertence a sequência de Fibonacci");
        }

        // Questão 3
        ex3();

        // Questão 4
        ex4();

        // Questão 5
        ex5();
    }
}