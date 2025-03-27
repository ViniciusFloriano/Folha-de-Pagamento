package Classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Classes.Conexao.Conexao;
import Classes.DTO.FuncionarioHorista;
import Classes.DTO.StatusUsuario;
import Classes.DTO.TipoUsuario;
public class FuncionarioHoristaDAO {
final String NOMEDATABELA = "funcionario_horista";
    
    public boolean inserir(FuncionarioHorista funcionarioHorista, int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql1 = "INSERT INTO  funcionario (id, cargo) VALUES (" + idUsuario + ", ?);";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, funcionarioHorista.getCargo());
            ps1.executeUpdate();
            ps1.close();
            String sql2 = "INSERT INTO " + NOMEDATABELA + " (id, salario_hora, horas_trabalhadas, horas_extras) VALUES (" + idUsuario + ", ?, ?, ?);";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setDouble(1, funcionarioHorista.getSalarioHora());
            ps2.setDouble(2, funcionarioHorista.getHorasTrabalhadas());
            ps2.setDouble(3, funcionarioHorista.getHorasExtras());
            ps2.executeUpdate();
            ps2.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean alterarSalarioHora(Double salario, int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET salario_hora = ? WHERE id = " + idFuncionario + ";";
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
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE id = " + idFuncionario + ";";
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
    
    public FuncionarioHorista procurarPorId(int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome, email, senha, cargo, salario_hora, horas_trabalhadas, horas_extras FROM usuario, funcionario, " + NOMEDATABELA + " WHERE funcionario_horista.id = " + idFuncionario + " AND usuario.id = funcionario_horista.id AND usuario.id = funcionario.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	FuncionarioHorista obj = new FuncionarioHorista(rs.getString(1), rs.getString(2), rs.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
            	obj.setNome(rs.getString(1));
                obj.setEmail(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setTipo(TipoUsuario.FUNCIONARIO);
                obj.setStatus(StatusUsuario.ATIVADO);
                obj.setCargo(rs.getString(4));
                obj.setSalarioHora(rs.getDouble(5));
                obj.setHorasTrabalhadas(rs.getDouble(6));
                obj.setHorasExtras(rs.getDouble(7));
                obj.getValorHorasExtras();
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
    
    public FuncionarioHorista procurarPorDescricao(int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome, email, senha, cargo, salario_hora, horas_trabalhadas, horas_extras FROM usuario, funcionario, " + NOMEDATABELA + " WHERE funcionario_horista.id = " + idFuncionario + " AND usuario.id = funcionario_horista.id AND usuario.id = funcionario.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	FuncionarioHorista obj = new FuncionarioHorista(rs.getString(1), rs.getString(2), rs.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
            	obj.setNome(rs.getString(1));
                obj.setEmail(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setTipo(TipoUsuario.FUNCIONARIO);
                obj.setStatus(StatusUsuario.ATIVADO);
                obj.setCargo(rs.getString(4));
                obj.setSalarioHora(rs.getDouble(5));
                obj.setHorasTrabalhadas(rs.getDouble(6));
                obj.setHorasExtras(rs.getDouble(7));
                obj.getValorHorasExtras();
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
    
    public boolean existe(int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM usuario, funcionario, " + NOMEDATABELA + " WHERE funcionario_horista.id = " + idUsuario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_horista.id;";
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
    
    public List<FuncionarioHorista> pesquisarTodos() {
        try {
        	Connection conn = Conexao.conectar();
            String sql = "SELECT nome, email, senha, cargo, salario_hora, horas_trabalhadas, horas_extras FROM usuario, funcionario, " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<FuncionarioHorista> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<FuncionarioHorista> montarLista(ResultSet rs) {
        List<FuncionarioHorista> listObj = new ArrayList<FuncionarioHorista>();
        try {
            while (rs.next()) {
            	FuncionarioHorista obj = new FuncionarioHorista(rs.getString(1), rs.getString(2), rs.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
                obj.setNome(rs.getString(1));
                obj.setEmail(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setTipo(TipoUsuario.FUNCIONARIO);
                obj.setStatus(StatusUsuario.ATIVADO);
                obj.setCargo(rs.getString(4));
                obj.setSalarioHora(rs.getDouble(5));
                obj.setHorasTrabalhadas(rs.getDouble(6));
                obj.setHorasExtras(rs.getDouble(7));
                obj.getValorHorasExtras();
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
    
    public double pegarSalarioHora(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT salario_hora FROM " + NOMEDATABELA + " WHERE id = " + id + ";";
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
    
    public double pegarHorasTrabalhadas(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT horas_trabalhadas FROM " + NOMEDATABELA + " WHERE id = " + id + ";";
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
    
    public double pegarHorasExtras(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT horas_extras FROM " + NOMEDATABELA + " WHERE id = " + id + ";";
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
