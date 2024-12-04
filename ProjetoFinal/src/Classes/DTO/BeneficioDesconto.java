package Classes.DTO;
public class BeneficioDesconto {
    private String descricao;
    private double valor;
    private Tipo tipo;

    public BeneficioDesconto(String descricao, double valor, Tipo tipo) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public Tipo getTipo() {
        return tipo;
    }
}

enum Tipo {
    BENEFICIO, DESCONTO
}

