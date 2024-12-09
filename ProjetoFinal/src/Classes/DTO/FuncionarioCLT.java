package Classes.DTO;
import java.util.ArrayList;
import java.util.List;
public class FuncionarioCLT extends Funcionario {
	private String cargo;
    private double salarioMensal;
    private List<BeneficioDesconto> beneficiosDescontos;

    public FuncionarioCLT(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status, String cargo, double salarioMensal) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
        this.cargo = cargo;
        this.salarioMensal = salarioMensal;
        this.beneficiosDescontos = new ArrayList<>();
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

	public void adicionarBeneficio(String descricao, double valor) {
        this.beneficiosDescontos.add(new BeneficioDesconto(descricao, valor, Tipo.BENEFICIO));
    }

    public void adicionarDesconto(String descricao, double valor) {
        this.beneficiosDescontos.add(new BeneficioDesconto(descricao, valor, Tipo.DESCONTO));
    }

    public void resetarBeneficiosDescontos() {
        this.beneficiosDescontos.clear();
    }

    @Override
    public double calcularPagamento() {
        double totalBeneficios = beneficiosDescontos.stream()
                .filter(bd -> bd.getTipo().equals("BENEFICIO"))
                .mapToDouble(BeneficioDesconto::getValor)
                .sum();

        double totalDescontos = beneficiosDescontos.stream()
                .filter(bd -> bd.getTipo().equals("DESCONTO"))
                .mapToDouble(BeneficioDesconto::getValor)
                .sum();

        return (salarioMensal + totalBeneficios) - totalDescontos;
    }
}