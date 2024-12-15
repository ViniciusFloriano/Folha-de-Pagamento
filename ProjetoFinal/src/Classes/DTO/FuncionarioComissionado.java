package Classes.DTO;
public class FuncionarioComissionado extends Funcionario {
	private String cargo;
    private double salarioBase;
    private double comissao; // Percentual de comissão
    private double vendasRealizadas;
    private double bonus;

    public FuncionarioComissionado(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
    }
    
    public FuncionarioComissionado(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status, String cargo, double salarioBase, double comissao, double vendasRealizadas, double bonus) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.comissao = comissao;
        this.vendasRealizadas = vendasRealizadas;
        this.bonus = bonus;
    }

    //Getters e Setters
    public String getCargo() { 
    	return cargo; 
    }
    
    public void setCargo(String cargo) { 
    	this.cargo = cargo; 
    }
    
    public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}
	
	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	
    public double getVendasRealizadas() {
		return vendasRealizadas;
	}

	public void setVendasRealizadas(double vendasRealizadas) {
		this.vendasRealizadas = vendasRealizadas;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
    public double calcularPagamento(int idFuncionario) {
        return salarioBase + (vendasRealizadas * comissao) + bonus;
    }
}
