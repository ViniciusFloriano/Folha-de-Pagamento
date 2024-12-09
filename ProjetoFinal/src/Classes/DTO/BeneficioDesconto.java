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

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
    
    
    
    
}
