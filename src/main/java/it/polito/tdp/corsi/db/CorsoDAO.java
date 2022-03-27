package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;


public class CorsoDAO {
	
	public List<Corso>getCorsiByPeriodo(int periodo) {
		String sql="SELECT * "
				+ "FROM corso "
				+ "WHERE pd= ? "	;
		List<Corso> result=new ArrayList<Corso>();
		//da qui inizia il codice che può dare eccezioni
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,periodo);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Corso c=new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				result.add(c);
				
			}
			st.close();
			rs.close();
			conn.close();
			return result;
			
		}catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
		
	}

}
