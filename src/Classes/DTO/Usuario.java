package Classes.DTO;
public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;
    private StatusUsuario status;

    // Construtor
    public Usuario() {
    	
    }
    
    public Usuario(String nome, String email, String senha, String tipo, String status) {
    	this.nome = nome;
    	this.email = email;
    	this.senha = senha;
    	if (tipo.equals("ADMINISTRADOR")) {
    		this.tipo = TipoUsuario.ADMINISTRADOR;    		
    	} else {
    		this.tipo = TipoUsuario.FUNCIONARIO; 
    	}
    	if (status.equals("ATIVADO")) {
    		this.status = StatusUsuario.ATIVADO;
    	} else {
    		this.status = StatusUsuario.DESATIVADO;
    	}
    }
    
    public Usuario(String nome, String email, TipoUsuario tipo, StatusUsuario status) {
    	this.nome = nome;
    	this.email = email;
        this.tipo = tipo;
        this.status = status;
    }
    
    public Usuario(String email, TipoUsuario tipo, StatusUsuario status) {
        this.email = email;
        this.tipo = tipo;
        this.status = status;
    }
    
    public Usuario(String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.status = status;
    }

    // Getters e Setters
    public String getNome() { 
    	return nome; 
    }
    
    public void setNome(String nome) { 
    	this.nome = nome; 
    }

    public String getEmail() { 
    	return email; 
    }
    
    public void setEmail(String email) { 
    	this.email = email; 
    }

    public String getSenha() { 
    	return senha; 
    }
    
    public void setSenha(String senha) { 
    	this.senha = senha; 
    }

    public String getTipoString() { 
    	return tipo.name();
    }
    
    public TipoUsuario getTipoTipo() { 
    	return tipo;
    }
    
    public void setTipo(TipoUsuario tipo) { 
    	this.tipo = tipo; 
    }

    public String getStatusString() { 
    	return status.name(); 
    }
    
    public StatusUsuario getStatusStatus() { 
    	return status; 
    }
    
    public void setStatus(StatusUsuario status) { 
    	this.status = status; 
    }

	public String mostrarTodosFuncionarios() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nome: ");
		builder.append(nome);
		return builder.toString();
	}
	
	public String mostrarTodos() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nome: ");
		builder.append(nome);
		builder.append(" Status: ");
		builder.append(status);
		return builder.toString();
	}
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [nome=");
		builder.append(nome);
		builder.append(", email=");
		builder.append(email);
		builder.append(", senha=");
		builder.append(senha);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
    
}
