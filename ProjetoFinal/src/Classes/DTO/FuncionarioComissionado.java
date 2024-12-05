package Classes.DTO;
public class FuncionarioComissionado extends Funcionario {
	private String cargo;
    private double salarioBase;
    private double comissao; // Percentual de comissão
    private double vendasRealizadas;
    private double bonus;

    public FuncionarioComissionado(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status, String cargo, double salarioBase, double comissao) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.comissao = comissao;
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

	public void registrarVendas(double vendas) {
        this.vendasRealizadas += vendas;
    }

    public void registrarBonus(double bonus) {
        this.bonus += bonus;
    }

    @Override
    public double calcularPagamento() {
        return salarioBase + (vendasRealizadas * comissao / 100) + bonus;
    }
}
