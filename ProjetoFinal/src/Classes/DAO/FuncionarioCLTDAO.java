package Classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Classes.DTO.FuncionarioCLT;
import Classes.DTO.StatusUsuario;
import Classes.DTO.TipoUsuario;
import Classes.Conexao.Conexao;
public class FuncionarioCLTDAO{
    final String NOMEDATABELA = "funcionario_clt";
    
    public boolean inserir(FuncionarioCLT funcionarioclt, int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql1 = "INSERT INTO  funcionario (id, cargo) VALUES (" + idUsuario + ", ?);";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, funcionarioclt.getCargo());
            ps1.executeUpdate();
            ps1.close();
            String sql2 = "INSERT INTO " + NOMEDATABELA + " (id, salario_mensal) VALUES (" + idUsuario + ", ?);";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setDouble(1, funcionarioclt.getSalarioMensal());
            ps2.executeUpdate();
            ps2.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean alterarSalario(Double salario, int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET salario_mensal = ? WHERE id = " + idFuncionario + ";";
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
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE id = ?;";
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
    
    public FuncionarioCLT procurarPorId(int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome, email, senha, cargo, salario_mensal FROM usuario, funcionario, " + NOMEDATABELA + " WHERE funcionario_clt.id = " + idFuncionario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_clt.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	FuncionarioCLT obj = new FuncionarioCLT(rs.getString(1), rs.getString(2), rs.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
                obj.setNome(rs.getString(1));
                obj.setEmail(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setTipo(TipoUsuario.FUNCIONARIO);
                obj.setStatus(StatusUsuario.ATIVADO);
                obj.setCargo(rs.getString(4));
                obj.setSalarioMensal(rs.getDouble(5));
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
    
    public FuncionarioCLT procurarPorDescricao(int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome, email, senha, cargo, salario_mensal FROM usuario, funcionario, " + NOMEDATABELA + " WHERE funcionario_clt.id = " + idFuncionario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_clt.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	FuncionarioCLT obj = new FuncionarioCLT(rs.getString(1), rs.getString(2), rs.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
                obj.setNome(rs.getString(1));
                obj.setEmail(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setTipo(TipoUsuario.FUNCIONARIO);
                obj.setStatus(StatusUsuario.ATIVADO);
                obj.setCargo(rs.getString(4));
                obj.setSalarioMensal(rs.getDouble(5));
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
    
    public boolean existe(FuncionarioCLT funcionarioclt, int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM usuario, funcionario, " + NOMEDATABELA + " WHERE usuario.id = " + idUsuario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_clt.id;";
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
    
    public List<FuncionarioCLT> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome, email, senha, cargo, salario_mensal FROM usuario, funcionario, " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<FuncionarioCLT> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<FuncionarioCLT> montarLista(ResultSet rs) {
        List<FuncionarioCLT> listObj = new ArrayList<FuncionarioCLT>();
        try {
            while (rs.next()) {
            	FuncionarioCLT obj = new FuncionarioCLT(rs.getString(1), rs.getString(2), rs.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
                obj.setNome(rs.getString(1));
                obj.setEmail(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setTipo(TipoUsuario.FUNCIONARIO);
                obj.setStatus(StatusUsuario.ATIVADO);
                obj.setCargo(rs.getString(4));
                obj.setSalarioMensal(rs.getDouble(5));
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
	
	public double pegarSalario(int id) {
	    try {
	        Connection conn = Conexao.conectar();
	        String sql = "SELECT salario_mensal FROM " + NOMEDATABELA + " WHERE id = " + id + ";";
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