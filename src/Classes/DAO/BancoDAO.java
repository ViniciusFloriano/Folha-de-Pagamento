package Classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import Classes.Conexao.Conexao;
import Classes.DTO.Banco;
import Classes.DTO.TipoConta;
public class BancoDAO {
	final String NOMEDATABELA = "banco";
    
    public boolean inserir(Banco banco, int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (funcionario_id, nome_banco, agencia, numero_conta, tipo_conta) VALUES (" + idFuncionario + ", ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, banco.getNomeBanco());
            ps.setString(2, banco.getAgencia());
            ps.setString(3, banco.getNumeroConta());
            ps.setString(4, banco.getTipoConta());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean alterarNomeBanco(Banco banco, int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET nome_banco = ? WHERE funcionario_id = " + idFuncionario + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, banco.getNomeBanco());
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
    
    public Banco procurarPorId(int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome_banco, agencia, numero_conta, tipo_conta FROM " + NOMEDATABELA + " WHERE funcionario_id = " + idFuncionario + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Banco obj = new Banco();
                obj.setNomeBanco(rs.getString(1));
                obj.setAgencia(rs.getString(2));
                obj.setNumeroConta(rs.getString(3));
                if (rs.getString(4).equals("CORRENTE")) {
                	obj.setTipoConta(TipoConta.CORRENTE);                	
                } else if (rs.getString(4).equals("POUPANCA")) {
                	obj.setTipoConta(TipoConta.POUPANCA);
                }
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
    
    public Banco procurarPorNomeBanco(String pesquisa) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome_banco, agencia, numero_conta, tipo_conta FROM " + NOMEDATABELA + " WHERE nome_banco LIKE '%" + pesquisa + "%';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	Banco obj = new Banco();
                obj.setNomeBanco(rs.getString(1));
                obj.setAgencia(rs.getString(2));
                obj.setNumeroConta(rs.getString(3));
                if (rs.getString(4).equals("CORRENTE")) {
                	obj.setTipoConta(TipoConta.CORRENTE);                	
                } else if (rs.getString(4).equals("POUPANCA")) {
                	obj.setTipoConta(TipoConta.POUPANCA);
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
            String sql = "SELECT * FROM funcionario, " + NOMEDATABELA + " WHERE banco.funcionario_id = " + idFuncionario + " AND funcionario.id = banco.funcionario_id;";
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
    
    public List<Banco> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT nome_banco, agencia, numero_conta, tipo_conta FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Banco> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Banco> montarLista(ResultSet rs) {
        List<Banco> listObj = new ArrayList<Banco>();
        try {
            while (rs.next()) {
            	Banco obj = new Banco();
                obj.setNomeBanco(rs.getString(1));
                obj.setAgencia(rs.getString(2));
                obj.setNumeroConta(rs.getString(3));
                if (rs.getString(4).equals("CORRENTE")) {
                	obj.setTipoConta(TipoConta.CORRENTE);                	
                } else if (rs.getString(4).equals("POUPANCA")) {
                	obj.setTipoConta(TipoConta.POUPANCA);
                }
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
