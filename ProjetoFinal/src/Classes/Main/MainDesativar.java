package Classes.Main;
import Classes.BO.*;
import Classes.DTO.*;
public class MainDesativar {
	public static void main(String[] args) {

		// Teste Excluir
		UsuarioBO usuarioBO = new UsuarioBO();
		Usuario usuario = new Usuario("CLT@gmail.com", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		
		int idUsuario1 = usuarioBO.pegarId(usuario);
		
		if (usuarioBO.desativar(idUsuario1)) {
			System.out.println("Desativado com Sucesso");
			
		} else {
			System.out.println("Erro ao Desativar");			
		}

	}
}
