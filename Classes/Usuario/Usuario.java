package Usuario;
public abstract class Usuario {
	private int id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;
    private StatusUsuario status;

    // Construtor
    public Usuario(int id, String nome, String email, String senha, TipoUsuario tipo, StatusUsuario status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.status = status;
    }

    // Getters e Setters
    public int getId() { 
    	return id; 
    }
    
    public void setId(int id) { 
    	this.id = id; 
    }

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

    public TipoUsuario getTipo() { 
    	return tipo;
    }
    
    public void setTipo(TipoUsuario tipo) { 
    	this.tipo = tipo; 
    }

    public StatusUsuario getStatus() { 
    	return status; 
    }
    
    public void setStatus(StatusUsuario status) { 
    	this.status = status; 
    }

}