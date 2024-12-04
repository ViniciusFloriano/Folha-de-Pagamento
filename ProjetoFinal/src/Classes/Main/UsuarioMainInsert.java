package Classes.Main;
import Classes.BO.UsuarioBO;
import Classes.DTO.StatusUsuario;
import Classes.DTO.TipoUsuario;
import Classes.DTO.Usuario;
public class UsuarioMainInsert {
	public static void main(String[] args) {

		UsuarioBO usuarioBO = new UsuarioBO();
		Usuario usuario = new Usuario("Vinicius", "vini@gmail.com", "123eq123", TipoUsuario.ADMINISTRADOR, StatusUsuario.ATIVADO);
		
		if (usuarioBO.inserir(usuario))
			System.out.println("Inserido com Sucesso");
		else
			System.out.println("Erro ao Inserir");
		
	}
}
