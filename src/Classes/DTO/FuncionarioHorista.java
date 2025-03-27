package Classes.DTO;
public class FuncionarioHorista extends Funcionario {
	private String cargo;
    private double salarioHora;
    private double horasTrabalhadas;
    private double horasExtras;
    private double valorHorasExtras;

    public FuncionarioHorista(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
    }
    
    public FuncionarioHorista(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status, String cargo, double salarioHora, double horasTrabalhadas, double horasExtras) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
        this.cargo = cargo;
        this.salarioHora = salarioHora;
        this.horasTrabalhadas = horasTrabalhadas;
        this.horasExtras = horasExtras;
        this.getValorHorasExtras();
    }

    //Getters e Setters
    public String getCargo() { 
    	return cargo; 
    }
    
    public void setCargo(String cargo) { 
    	this.cargo = cargo; 
    }
    
    public double getSalarioHora() {
		return salarioHora;
	}

	public void setSalarioHora(double salarioHora) {
		this.salarioHora = salarioHora;
	}

    public double getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(double horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public double getHorasExtras() {
		return horasExtras;
	}

	public void setHorasExtras(double horasExtras) {
		this.horasExtras = horasExtras;
	}

	public double getValorHorasExtras() {
		return valorHorasExtras = horasExtras * (salarioHora * 0.5);// Extra: 50% do valor base
	}

    @Override
    public double calcularPagamento(int idFuncionario) {
        return (salarioHora * horasTrabalhadas) + valorHorasExtras;
    }
}
