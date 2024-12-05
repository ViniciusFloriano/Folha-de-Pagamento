package Classes.DTO;
public class FuncionarioHorista extends Funcionario {
	private String cargo;
    private double salarioHora;
    private double horasTrabalhadas;
    private double valorHorasExtras;

    public FuncionarioHorista(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status, String cargo, double salarioHora) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
        this.cargo = cargo;
        this.salarioHora = salarioHora;
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

	public void registrarHoras(double horas) {
        this.horasTrabalhadas += horas;
    }

    public void registrarHorasExtras(double horasExtras) {
        this.valorHorasExtras += horasExtras * (salarioHora * 1.5); // Extra: 50% do valor base
    }

    @Override
    public double calcularPagamento() {
        return (salarioHora * horasTrabalhadas) + valorHorasExtras;
    }
}
