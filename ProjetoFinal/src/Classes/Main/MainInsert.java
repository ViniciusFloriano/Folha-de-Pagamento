package Classes.Main;
import Classes.BO.FuncionarioCLTBO;
import Classes.BO.UsuarioBO;
import Classes.DTO.FuncionarioCLT;
import Classes.DTO.StatusUsuario;
import Classes.DTO.TipoUsuario;
import Classes.DTO.Usuario;
public class MainInsert {
	public static void main(String[] args) {
		
		UsuarioBO usuarioBO = new UsuarioBO();
		Usuario usuario1 = new Usuario("Vinicius", "vini@gmail.com", "123eq123", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		
		if (usuarioBO.inserir(usuario1)) {
			System.out.println("Inserido com Sucesso");
			
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
		int idUsuario = usuarioBO.pegarId(usuario1);
		
		FuncionarioCLTBO funcionariocltBO = new FuncionarioCLTBO();
		FuncionarioCLT funcionarioclt = new FuncionarioCLT(usuario1.getNome(), "teste@gmail.com", usuario1.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "Analista de TI", 2000.00);
		
		if (funcionariocltBO.inserir(funcionarioclt, idUsuario)) {
			System.out.println("Inserido com Sucesso");
			
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
	}
}
