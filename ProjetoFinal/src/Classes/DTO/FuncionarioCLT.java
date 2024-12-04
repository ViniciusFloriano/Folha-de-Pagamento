package Classes.DTO;
import java.util.ArrayList;
import java.util.List;
public class FuncionarioCLT extends Funcionario {
    private double salarioMensal;
    private List<BeneficioDesconto> beneficiosDescontos;

    public FuncionarioCLT(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status, String cargo, double salarioMensal) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo);
        this.salarioMensal = salarioMensal;
        this.beneficiosDescontos = new ArrayList<>();
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
                .filter(bd -> bd.getTipo() == Tipo.BENEFICIO)
                .mapToDouble(BeneficioDesconto::getValor)
                .sum();

        double totalDescontos = beneficiosDescontos.stream()
                .filter(bd -> bd.getTipo() == Tipo.DESCONTO)
                .mapToDouble(BeneficioDesconto::getValor)
                .sum();

        return (salarioMensal + totalBeneficios) - totalDescontos;
    }
}