package Classes.DTO;
//import java.util.ArrayList;
import java.util.List;
import Classes.BO.BeneficioDescontoBO;
public class FuncionarioCLT extends Funcionario {
	private String cargo;
    private double salarioMensal;

    public FuncionarioCLT(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
    }

    
    public FuncionarioCLT(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status, String cargo, double salarioMensal) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
        this.cargo = cargo;
        this.salarioMensal = salarioMensal;
    }

    // Getters e Setters
    public String getCargo() { 
    	return cargo; 
    }
    
    public void setCargo(String cargo) { 
    	this.cargo = cargo; 
    }
    
    public double getSalarioMensal() {
		return salarioMensal;
	}

	public void setSalarioMensal(double salarioMensal) {
		this.salarioMensal = salarioMensal;
	}

    @Override
    public double calcularPagamento(int idFuncionario) {
        BeneficioDescontoBO beneficioDescontoBO = new BeneficioDescontoBO();
        List<BeneficioDesconto> descontos = beneficioDescontoBO.buscarDescontosPorFuncionario(idFuncionario);

        double totalDescontos = 0.0;
        for (BeneficioDesconto desconto : descontos) {
            totalDescontos += desconto.getValor();
        }

        // Salário final = Salário mensal - total de descontos
        return this.salarioMensal - totalDescontos;
    }
}