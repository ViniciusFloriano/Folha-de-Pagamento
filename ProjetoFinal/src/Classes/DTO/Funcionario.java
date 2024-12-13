package Classes.DTO;
public abstract class Funcionario extends Usuario {
    
    // Construtor
    public Funcionario(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
    }

    public abstract double calcularPagamento(int idFuncionario);

}
