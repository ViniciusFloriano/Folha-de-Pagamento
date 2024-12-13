package Classes.BO;
import Classes.DAO.UsuarioDAO;
import Classes.DTO.Usuario;
import java.util.List;
public class UsuarioBO {

    public boolean inserir(Usuario usuario){
        if (existe(usuario) != true) {
        	UsuarioDAO usuarioDAO = new UsuarioDAO();
            return usuarioDAO.inserir(usuario);
        }
        return false;
    }
    
    public boolean alterar(Usuario usuario, int idUsuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.alterar(usuario, idUsuario);
    }
    
    public boolean excluir(Usuario usuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.excluir(usuario);
    }
    
    public Usuario procurarPorNome(Usuario usuario, String pesquisa){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.procurarPorNome(usuario, pesquisa);
    }
    
    public Usuario procurarPorEmail(Usuario usuario, String pesquisa){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.procurarPorEmail(usuario, pesquisa);
    }
    /*
    public Usuario procurarPorDescricao(Usuario usuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.procurarPorDescricao(usuario);
    }
    */
    public boolean existe(Usuario usuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.existe(usuario);
    }
    
    public List<Usuario> pesquisarTodosFuncionarios(){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.pesquisarTodosFuncionarios();
    }
    
    public List<Usuario> pesquisarTodos(){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.pesquisarTodos();
    }
    
    public int pegarId(Usuario usuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.pegarId(usuario);
    }
    
    public boolean ativar(int idUsuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.ativar(idUsuario);
    }
    
    public boolean desativar(int idUsuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.desativar(idUsuario);
    }
    
    public static Usuario logarSemSenha(String email){
        return UsuarioDAO.logarSemSenha(email);
    }
    
    public static Usuario logar(String email, String senha){
        return UsuarioDAO.logar(email, senha);
    }
    
    public boolean atualizaSenha(String senha, int idUsuario){
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.atualizarSenha(senha, idUsuario);
    }
    
    public static boolean verificaSenha(String email){
        return UsuarioDAO.verificaSenha(email);
    }
}
