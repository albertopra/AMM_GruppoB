/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.skatetrade.classi;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class UtentiFactory {
    // Attributi
    private static UtentiFactory singleton;
    private String connectionString;
    
    /* Costruttore */
    private UtentiFactory() {
    }
    
    /* Metodi */
    public static UtentiFactory getInstance() {
        if (singleton == null) {
            singleton = new UtentiFactory();
        }
        return singleton;
    }
    
    public Utente getUtente(String username, String password)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, 
                    "alberto", "1234"); 
            
            String query = "select * from utente "
                    + "where username = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet res = stmt.executeQuery();
            
            if(res.next()) {
                if(res.getString("tipologia").equals("cliente")) {
                    UtenteCliente cliente = new UtenteCliente();
                    
                    cliente.setId(res.getInt("id"));
                    cliente.setNome(res.getString("nome"));
                    cliente.setCognome(res.getString("cognome"));
                    cliente.setUsername(res.getString("username"));
                    cliente.setPassword(res.getString("password"));
                    
                    return cliente;
                }
                
                if(res.getString("tipologia").equals("venditore")) {
                    UtenteVenditore venditore = new UtenteVenditore();
                    
                    venditore.setId(res.getInt("id"));
                    venditore.setNome(res.getString("nome"));
                    venditore.setCognome(res.getString("cognome"));
                    venditore.setUsername(res.getString("username"));
                    venditore.setPassword(res.getString("password"));
                    
                    return venditore;
                }
            }
            
            stmt.close();
            conn.close();
            
            return null;
        }
        catch(SQLException e) {
            
        }
        
        return null;
    }
    
    public ArrayList<Utente> getClienteList()
    {
        ArrayList<Utente> listaClienti = new ArrayList<Utente>();
        
        try {
            Connection conn = DriverManager.getConnection(connectionString,
                    "alberto", "1234");
            
            String query = "select * from utente where tipologia = 'cliente'";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            
            while(res.next()) {
                UtenteCliente cliente = new UtenteCliente();
                    
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setCognome(res.getString("cognome"));
                cliente.setUsername(res.getString("username"));
                cliente.setPassword(res.getString("password"));
                    
                listaClienti.add(cliente);   
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
        
        return listaClienti;
    }
    
    public Utente getCliente(int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString,
                    "alberto", "1234");
            
            String query = "select * from utente where id = ? "
                    + "and tipologia = 'cliente'";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, id);
            
            ResultSet res = stmt.executeQuery();
            
            if(res.next()) {
                UtenteCliente cliente = new UtenteCliente();
                    
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setCognome(res.getString("cognome"));
                cliente.setUsername(res.getString("username"));
                cliente.setPassword(res.getString("password"));
                    
                return cliente;   
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
        
        return null;
    }
    
    public ArrayList<Utente> getVenditoreList()
    {
        ArrayList<Utente> listaVenditori = new ArrayList<Utente>();
        
        try {
            Connection conn = DriverManager.getConnection(connectionString,
                    "alberto", "1234");
            
            String query = "select * from utente where tipologia = 'venditore'";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            
            while(res.next()) {
                UtenteVenditore venditore = new UtenteVenditore();
                    
                venditore.setId(res.getInt("id"));
                venditore.setNome(res.getString("nome"));
                venditore.setCognome(res.getString("cognome"));
                venditore.setUsername(res.getString("username"));
                venditore.setPassword(res.getString("password"));
                    
                listaVenditori.add(venditore);   
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
        
        return listaVenditori;
    }
    
    public Utente getVenditore(int id)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString,
                    "alberto", "1234");
            
            String query = "select * from utente where id = ? "
                    + "and tipologia = 'venditore'";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, id);
            
            ResultSet res = stmt.executeQuery();
            
            if(res.next()) {
                UtenteVenditore venditore = new UtenteVenditore();
                    
                venditore.setId(res.getInt("id"));
                venditore.setNome(res.getString("nome"));
                venditore.setCognome(res.getString("cognome"));
                venditore.setUsername(res.getString("username"));
                venditore.setPassword(res.getString("password"));
                    
                return venditore;   
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
        
        return null;
    }
    
    public ArrayList<Utente> getUserList() 
    {
        ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
        ArrayList<Utente> listaClienti;
        ArrayList<Utente> listaVenditori ;
        
        listaClienti = this.getClienteList();
        listaVenditori = this.getVenditoreList();      
        
        listaUtenti.addAll(listaClienti);
        listaUtenti.addAll(listaVenditori);
        
        return listaUtenti;
    }
    
    public void setConnectionString(String s){
    	this.connectionString = s;
    }

    public String getConnectionString(){
    	return this.connectionString;
    } 
}

