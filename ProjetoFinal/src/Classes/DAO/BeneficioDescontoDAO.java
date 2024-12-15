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
	
	public boolean alterarDescricao(BeneficioDesconto beneficioDesconto, int idFuncionario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "UPDATE " + NOMEDATABELA + " SET descricao = ? WHERE funcionario_id = " + idFuncionario + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, beneficioDesconto.getDescricao());
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(int idFuncionario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "DELETE FROM " + NOMEDATABELA + " WHERE funcionario_id = " + idFuncionario + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public BeneficioDesconto procurarPorFuncionario(int idFuncionario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT descricao, valor, tipo FROM " + NOMEDATABELA + " WHERE funcionario_id = " + idFuncionario + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				BeneficioDesconto obj = new BeneficioDesconto();
				obj.setDescricao(rs.getString(1));
				obj.setValor(rs.getDouble(2));
                if (rs.getString(3).equals("BENEFICIO")) {
                	obj.setTipo(TipoBenDes.BENEFICIO);
                } else if (rs.getString(3).equals("DESCONTO")) {
                	obj.setTipo(TipoBenDes.DESCONTO);
                }
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

	public BeneficioDesconto procurarPorDescricao(String pesquisa) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT descricao, valor, tipo FROM " + NOMEDATABELA + " WHERE descricao = " + pesquisa + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				BeneficioDesconto obj = new BeneficioDesconto();
				obj.setDescricao(rs.getString(1));
				obj.setValor(rs.getDouble(2));
                if (rs.getString(3).equals("BENEFICIO")) {
                	obj.setTipo(TipoBenDes.BENEFICIO);
                } else if (rs.getString(3).equals("DESCONTO")) {
                	obj.setTipo(TipoBenDes.DESCONTO);
                }
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
	
	public boolean existe(int idFuncionario) {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT * FROM funcionario, " + NOMEDATABELA + " WHERE beneficio_desconto.funcionario_id = " + idFuncionario + " AND funcionario.id = beneficio_desconto.funcionario_id;";
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

	public List<BeneficioDesconto> pesquisarTodos() {
		try {
			Connection conn = Conexao.conectar();
			String sql = "SELECT descricao, valor, tipo FROM " + NOMEDATABELA + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<BeneficioDesconto> listObj = montarLista(rs);
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<BeneficioDesconto> montarLista(ResultSet rs) {
		List<BeneficioDesconto> listObj = new ArrayList<BeneficioDesconto>();
		try {
			while (rs.next()) {
				BeneficioDesconto obj = new BeneficioDesconto();
				obj.setDescricao(rs.getString(1));
				obj.setValor(rs.getDouble(2));
                if (rs.getString(3).equals("BENEFICIO")) {
                	obj.setTipo(TipoBenDes.BENEFICIO);
                } else if (rs.getString(3).equals("DESCONTO")) {
                	obj.setTipo(TipoBenDes.DESCONTO);
                }
				listObj.add(obj);
			}
			return listObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<BeneficioDesconto> buscarDescontosPorFuncionario(int idFuncionario) {
        List<BeneficioDesconto> descontos = new ArrayList<>();
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT descricao, valor, tipo FROM " + NOMEDATABELA + " WHERE funcionario_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idFuncionario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BeneficioDesconto desconto = new BeneficioDesconto();
                desconto.setDescricao(rs.getString(1));
                desconto.setValor(rs.getDouble(2));
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
