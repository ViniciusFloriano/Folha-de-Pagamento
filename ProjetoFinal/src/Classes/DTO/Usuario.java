package Classes.DTO;
public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;
    private StatusUsuario status;

    // Construtor
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

    public String getTipo() { 
    	return tipo.name();
    }
    
    public void setTipo(TipoUsuario tipo) { 
    	this.tipo = tipo; 
    }

    public String getStatus() { 
    	return status.name(); 
    }
    
    public void setStatus(StatusUsuario status) { 
    	this.status = status; 
    }

}
