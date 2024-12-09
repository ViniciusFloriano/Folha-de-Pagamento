package Classes.DTO;
public class Banco {
    private Funcionario funcionario;
    private String nomeBanco;
    private String agencia;
    private String numeroConta;
    private TipoConta tipoConta; // Corrente ou Poupança

    // Construtor
    public Banco(Funcionario funcionario, String nomeBanco, String agencia, String numeroConta, TipoConta tipoConta) {
        this.funcionario = funcionario;
        this.nomeBanco = nomeBanco;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.tipoConta = tipoConta;
    }

	// Getters e Setters
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getTipoConta() {
		return tipoConta.name();
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
    
}