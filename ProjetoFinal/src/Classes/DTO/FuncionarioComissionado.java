package Classes.DTO;
public class FuncionarioComissionado extends Funcionario {
    private double salarioBase;
    private double comissao; // Percentual de comissão
    private double vendasRealizadas;
    private double bonus;

    public FuncionarioComissionado(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status, String cargo, double salarioBase, double comissao) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, cargo);
        this.salarioBase = salarioBase;
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
