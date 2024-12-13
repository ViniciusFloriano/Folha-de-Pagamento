package Classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Classes.Conexao.Conexao;
import Classes.DTO.BeneficioDesconto;
import Classes.DTO.TipoBenDes;
public class BeneficioDescontoDAO {
	final String NOMEDATABELA = "beneficio_desconto";

	public boolean inserir(BeneficioDesconto beneficioDesconto, int idFuncionario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "INSERT INTO " + NOMEDATABELA + " (funcionario_id, descricao, valor, tipo) VALUES (" + idFuncionario + ", ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, beneficioDesconto.getDescricao());
			ps.setDouble(2, beneficioDesconto.getValor());
			ps.setString(3, beneficioDesconto.getTipo());
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/*
	public boolean alterar(Usuario marca) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "UPDATE " + NOMEDATABELA + " SET descricao = ? WHERE codigo = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, marca.getDescricao());
			ps.setInt(2, marca.getCodigo());
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(Usuario marca) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "DELETE FROM " + NOMEDATABELA + " WHERE codigo = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, marca.getCodigo());
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Usuario procurarPorCodigo(Usuario marca) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE codigo = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, marca.getCodigo());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Marca obj = new Marca();
				obj.setCodigo(rs.getInt(1));
				obj.setDescricao(rs.getString(2));
				ps.close();
				rs.close();
				conn.close();
				return obj;
			} else {
				ps.close();
				rs.close();
				conn.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario procurarPorDescricao(Usuario marca) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE descricao = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, marca.getDescricao());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Usuario obj = new Usuario();
				obj.setCodigo(rs.getInt(1));
				obj.setDescricao(rs.getString(2));
				ps.close();
				rs.close();
				conn.close();
				return obj;
			} else {
				ps.close();
				rs.close();
				conn.close();
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public boolean existe(BeneficioDesconto beneficioDesconto, int idUsuario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM usuario, funcionario, " + NOMEDATABELA + " WHERE usuario.id = " + idUsuario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_horista.id;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ps.close();
				rs.close();
				conn.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public List<Usuario> pesquisarTodos() {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM " + NOMEDATABELA + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Usuario> listObj = montarLista(rs);
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Usuario> montarLista(ResultSet rs) {
		List<Usuario> listObj = new ArrayList<Usuario>();
		try {
			while (rs.next()) {
				Marca obj = new Marca();
				obj.setCodigo(rs.getInt(1));
				obj.setDescricao(rs.getString(2));
				listObj.add(obj);
			}
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	public List<BeneficioDesconto> buscarDescontosPorFuncionario(int idFuncionario) {
        List<BeneficioDesconto> descontos = new ArrayList<>();
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE funcionario_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idFuncionario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BeneficioDesconto desconto = new BeneficioDesconto();
                desconto.setDescricao(rs.getString("descricao"));
                desconto.setValor(rs.getDouble("valor"));
                desconto.setTipo(TipoBenDes.DESCONTO);
                descontos.add(desconto);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return descontos;
    }
}
