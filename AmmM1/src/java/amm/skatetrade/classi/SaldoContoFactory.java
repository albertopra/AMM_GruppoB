/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.skatetrade.classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class SaldoContoFactory {
    private static SaldoContoFactory singleton;
    private String connectionString;
    
    //Lista utenti
    private ArrayList<Utente> listaUtenti = UtentiFactory.getInstance().getUserList();
    
    private SaldoContoFactory() {
        
    }
    
    /* Metodi */
    public static SaldoContoFactory getInstance() {
        if (singleton == null) {
            singleton = new SaldoContoFactory();
        }
        
        return singleton;
    }
    
    public ArrayList<SaldoConto> getSaldoContoList() {
        ArrayList<SaldoConto> listaSaldoConto = new ArrayList<SaldoConto>();
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, 
                    "alberto", "1234");
            
            String query = "select * from utente";
            Statement stmt = conn.createStatement();
            
            ResultSet res = stmt.executeQuery(query);
            
            while(res.next()) {
                SaldoConto saldoConto = new SaldoConto();
                
                if(res.getString("tipologia").equals("cliente")) {
                    UtenteCliente cliente = new UtenteCliente();
                    
                    cliente.setId(res.getInt("id"));
                    cliente.setNome(res.getString("nome"));
                    cliente.setCognome(res.getString("cognome"));
                    cliente.setUsername(res.getString("username"));
                    cliente.setPassword(res.getString("password"));
                    
                    saldoConto.setUtente(cliente);
                    saldoConto.setConto(res.getFloat("saldo_conto"));
                    
                    listaSaldoConto.add(saldoConto);
                }
                
                if(res.getString("tipologia").equals("venditore")) {
                    UtenteVenditore venditore = new UtenteVenditore();
                    
                    venditore.setId(res.getInt("id"));
                    venditore.setNome(res.getString("nome"));
                    venditore.setCognome(res.getString("cognome"));
                    venditore.setUsername(res.getString("username"));
                    venditore.setPassword(res.getString("password"));
                    
                    saldoConto.setUtente(venditore);
                    saldoConto.setConto(res.getFloat("saldo_conto"));
                    
                    listaSaldoConto.add(saldoConto);
                }
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
        
        return listaSaldoConto;
    }
    
    public void setConnectionString(String s){
    	this.connectionString = s;
    }

    public String getConnectionString(){
    	return this.connectionString;
    } 
}
