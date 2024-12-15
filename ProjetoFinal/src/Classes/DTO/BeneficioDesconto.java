package Classes.DTO;
public class BeneficioDesconto {
    private String descricao;
    private double valor;
    private TipoBenDes tipo;

    public BeneficioDesconto() {
    }
    
    public BeneficioDesconto(String descricao, double valor, TipoBenDes tipo) {
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

    public String getTipo() {
        return tipo.name();
    }

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setTipo(TipoBenDes tipo) {
		this.tipo = tipo;
	}
}
