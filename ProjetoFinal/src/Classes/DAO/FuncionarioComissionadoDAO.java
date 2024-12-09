package Classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Classes.Conexao.Conexao;
import Classes.DTO.FuncionarioComissionado;
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
            String sql2 = "INSERT INTO " + NOMEDATABELA + " (id, salario_base, comissao_percentual) VALUES (" + idUsuario + ", ?, ?);";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setDouble(1, funcionarioComissionado.getSalarioBase());
            ps2.setDouble(2, funcionarioComissionado.getComissao());
            ps2.executeUpdate();
            ps2.close();
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
    */
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
    /*
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
}
