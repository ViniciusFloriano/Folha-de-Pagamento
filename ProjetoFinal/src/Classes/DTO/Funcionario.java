package Classes.DTO;
public abstract class Funcionario extends Usuario {
    private String cargo;

    // Construtor
    public Funcionario(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status, String cargo) {
        super(nome, email, senha, TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
        this.cargo = cargo;
    }

    // Getters e Setters
    public String getCargo() { 
    	return cargo; 
    }
    
    public void setCargo(String cargo) { 
    	this.cargo = cargo; 
    }

    public abstract double calcularPagamento();

}
