package Classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Classes.Conexao.Conexao;
import Classes.DTO.FuncionarioComissionado;
import Classes.DTO.StatusUsuario;
import Classes.DTO.TipoUsuario;
public class FuncionarioComissionadoDAO {
final String NOMEDATABELA = "funcionario_comissionado";
    
    public boolean inserir(FuncionarioComissionado funcionarioComissionado, int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql1 = "INSERT INTO  funcionario (id, cargo) VALUES (" + idUsuario + ", ?);";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, funcionarioComissionado.getCargo());
            ps1.executeUpdate();
            ps1.close();
            String sql2 = "INSERT INTO " + NOMEDATABELA + " (id, salario_base, comissao_percentual, vendas_realizadas, bonus) VALUES (" + idUsuario + ", ?, ?, ?, ?);";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setDouble(1, funcionarioComissionado.getSalarioBase());
            ps2.setDouble(2, funcionarioComissionado.getComissao());
            ps2.setDouble(3, funcionarioComissionado.getVendasRealizadas());
            ps2.setDouble(4, funcionarioComissionado.getBonus());
            ps2.executeUpdate();
            ps2.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean alterarSalarioBase(double salario, int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET salario_base = ? WHERE codigo = " + idFuncionario + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, salario);
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
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE codigo = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idFuncionario);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    
    public FuncionarioComissionado procurarPorId(int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome, email, senha, cargo, salario_base, comissao_percentual, vendas_realizadas, bonus FROM usuario, funcionario, " + NOMEDATABELA + " WHERE funcionario_comissionado.id = " + idFuncionario + " AND usuario.id = funcionario_comissionado.id AND usuario.id = funcionario.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	FuncionarioComissionado obj = new FuncionarioComissionado(rs.getString(1), rs.getString(2), rs.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
                obj.setNome(rs.getString(1));
                obj.setEmail(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setTipo(TipoUsuario.FUNCIONARIO);
                obj.setStatus(StatusUsuario.ATIVADO);
                obj.setCargo(rs.getString(4));
                obj.setSalarioBase(rs.getDouble(5));
                obj.setComissao(rs.getDouble(5));
                obj.setVendasRealizadas(rs.getDouble(5));
                obj.setBonus(rs.getDouble(5));
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
    
    public FuncionarioComissionado procurarPorDescricao(int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome, email, senha, cargo, salario_base, comissao_percentual, vendas_realizadas, bonus FROM usuario, funcionario, " + NOMEDATABELA + " WHERE funcionario_comissionado.id = " + idFuncionario + " AND usuario.id = funcionario_comissionado.id AND usuario.id = funcionario.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	FuncionarioComissionado obj = new FuncionarioComissionado(rs.getString(1), rs.getString(2), rs.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
                obj.setNome(rs.getString(1));
                obj.setEmail(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setTipo(TipoUsuario.FUNCIONARIO);
                obj.setStatus(StatusUsuario.ATIVADO);
                obj.setCargo(rs.getString(4));
                obj.setSalarioBase(rs.getDouble(5));
                obj.setComissao(rs.getDouble(5));
                obj.setVendasRealizadas(rs.getDouble(5));
                obj.setBonus(rs.getDouble(5));
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
    
    public boolean existe(FuncionarioComissionado funcionarioComissionado, int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM usuario, funcionario, " + NOMEDATABELA + " WHERE usuario.id = " + idUsuario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_comissionado.id;";
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
    
    public List<FuncionarioComissionado> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome, email, senha, cargo, salario_base, comissao_percentual, vendas_realizadas, bonus FROM usuario, funcionario, " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<FuncionarioComissionado> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<FuncionarioComissionado> montarLista(ResultSet rs) {
        List<FuncionarioComissionado> listObj = new ArrayList<FuncionarioComissionado>();
        try {
            while (rs.next()) {
                FuncionarioComissionado obj = new FuncionarioComissionado(rs.getString(1), rs.getString(2), rs.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
                obj.setNome(rs.getString(1));
                obj.setEmail(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setTipo(TipoUsuario.FUNCIONARIO);
                obj.setStatus(StatusUsuario.ATIVADO);
                obj.setCargo(rs.getString(4));
                obj.setSalarioBase(rs.getDouble(5));
                obj.setComissao(rs.getDouble(6));
                obj.setVendasRealizadas(rs.getDouble(7));
                obj.setBonus(rs.getDouble(8));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int pegarId(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT id FROM " + NOMEDATABELA + " WHERE id = " + id + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
           e.printStackTrace();
            return 0;
        }
        return 0;
    }
    
    public double pegarSalarioBase(int id) {
	    try {
	        Connection conn = Conexao.conectar();
	        String sql = "SELECT salario_base FROM " + NOMEDATABELA + " WHERE id = " + id + ";";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble(1);
	        }
	        ps.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	       e.printStackTrace();
	        return 0;
	    }
	    return 0;
	}
    
    public double pegarComissao(int id) {
	    try {
	        Connection conn = Conexao.conectar();
	        String sql = "SELECT comissao_percentual FROM " + NOMEDATABELA + " WHERE id = " + id + ";";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble(1);
	        }
	        ps.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	       e.printStackTrace();
	        return 0;
	    }
	    return 0;
	}
    
    public double pegarVendas(int id) {
	    try {
	        Connection conn = Conexao.conectar();
	        String sql = "SELECT vendas_realizadas FROM " + NOMEDATABELA + " WHERE id = " + id + ";";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble(1);
	        }
	        ps.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	       e.printStackTrace();
	        return 0;
	    }
	    return 0;
	}
    
    public double pegarBonus(int id) {
	    try {
	        Connection conn = Conexao.conectar();
	        String sql = "SELECT bonus FROM " + NOMEDATABELA + " WHERE id = " + id + ";";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble(1);
	        }
	        ps.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	       e.printStackTrace();
	        return 0;
	    }
	    return 0;
	}
}
