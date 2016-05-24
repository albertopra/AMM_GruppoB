/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.skatetrade.classi;

import amm.skatetrade.classi.ObjectSale;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class ObjectSaleFactory {
    private static ObjectSaleFactory singleton;
    private String connectionString;
    
    //Costruttore
    private ObjectSaleFactory() {
        
    }
    
    //Metodi
    public static ObjectSaleFactory getInstance() {
        if(singleton == null)
            singleton = new ObjectSaleFactory();
        
        return singleton;
    }
    
    public ArrayList<ObjectSale> getSellingObjectList() {
        ArrayList<ObjectSale> objectList = new ArrayList<ObjectSale>();
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, "alberto", "1234");
            Statement stmt = conn.createStatement();
            String query = "select * from oggetto";
            ResultSet res = stmt.executeQuery(query);
            
            while(res.next()) {
                ObjectSale object = new ObjectSale();
                
                object.setId(res.getInt("id"));
                object.setCategoria(res.getString("categoria"));
                object.setNome(res.getString("nome"));
                object.setUrlImmagine(res.getString("url_immagine"));
                object.setDescrizione(res.getString("descrizione"));
                object.setPrezzo(res.getFloat("prezzo"));
                object.setQuantita(res.getInt("quantita"));
                
                objectList.add(object);
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
        
        return objectList;
    }
    
    public ArrayList<ObjectSale> getSellingObjectByCategory(String categoria) {
        ArrayList<ObjectSale> objectListbyCategory = new ArrayList<ObjectSale>();
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, "alberto", "1234");
            String query = "select * from oggetto where categoria = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, categoria);
            
            ResultSet res = stmt.executeQuery();
            
            while(res.next()) {
                ObjectSale object = new ObjectSale();
                
                object.setId(res.getInt("id"));
                object.setCategoria(res.getString("categoria"));
                object.setNome(res.getString("nome"));
                object.setUrlImmagine(res.getString("url_immagine"));
                object.setDescrizione(res.getString("descrizione"));
                object.setPrezzo(res.getFloat("prezzo"));
                object.setQuantita(res.getInt("quantita"));
                
                objectListbyCategory.add(object);
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
        
        return objectListbyCategory;
    }
    
    public ObjectSale getObjectSaleById(int id) {
        try {
            Connection conn = DriverManager.getConnection(connectionString, "alberto", "1234");
            String query = "select * from oggetto where id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, id);
            
            ResultSet res = stmt.executeQuery();
            
            if(res.next()) {
                ObjectSale object = new ObjectSale();
                
                object.setId(res.getInt("id"));
                object.setCategoria(res.getString("categoria"));
                object.setNome(res.getString("nome"));
                object.setUrlImmagine(res.getString("url_immagine"));
                object.setDescrizione(res.getString("descrizione"));
                object.setPrezzo(res.getFloat("prezzo"));
                object.setQuantita(res.getInt("quantita"));
                
                return object;
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
        
        return null;
    }
    
    public void addObject(ObjectSale object, int idVenditore)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, 
                    "alberto", "1234");
            
            String query = "insert into oggetto(id, categoria, nome, url_immagine, "
                    + "descrizione, quantita, prezzo, id_venditore) "
                    + "values (default, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, object.getCategoria());
            stmt.setString(2, object.getNome());
            stmt.setString(3, object.getUrlImmagine());
            stmt.setString(4, object.getDescrizione());
            stmt.setInt(5, object.getQuantita());
            stmt.setFloat(6, object.getPrezzo());
            stmt.setInt(7, idVenditore);
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
    }
    
    public void removeObject(int idObject)
    {
        try {
            Connection conn = DriverManager.getConnection(connectionString, 
                    "alberto", "1234");
            
            String query = "delete from oggetto where id = " + idObject;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e) {
            
        }
    }
    
    public boolean acquistoObject(int idCliente, int idOggetto) {
        try {
            Connection conn = DriverManager.getConnection(connectionString, 
                    "alberto", "1234");
            
            int idVenditore;
            int prezzoOggetto;
            
            String queryGetIdVenditore = "select venditore from oggetto where id = ?";
            String queryGetPrezzoOggetto = "select prezzo from oggetto where id = ?";
            String queryRimozioneOggetto = "delete from oggetto where id = ?";
            String queryDiminuireSaldoCliente = "update utente set prezzo = ? where id = ?";
            String queryAumentoSaldoVenditore = "update utente set prezzo = ? where id = ?";
            
            conn.setAutoCommit(false);
            
            PreparedStatement getIdVenditore = conn.
                    prepareStatement(queryGetIdVenditore);
            PreparedStatement getPrezzoOggetto = conn.
                    prepareStatement(queryGetPrezzoOggetto);
            PreparedStatement diminuireSaldoCliente = conn.
                    prepareStatement(queryDiminuireSaldoCliente);
            PreparedStatement aumentoSaldoVenditore = conn.
                    prepareStatement(queryAumentoSaldoVenditore);
            
            getIdVenditore.setInt(1, idOggetto);
            getPrezzoOggetto.setInt(1, idOggetto);
            
            //recupero l'id del venditore
            ResultSet res = getIdVenditore.executeQuery();
            if(res.next()) {
                idVenditore = res.getInt("venditore");
            }
            
            //recupero il prezzo dell'oggetto
            res = getPrezzoOggetto.executeQuery();
            if(res.next()) {
                prezzoOggetto = res.getInt("prezzo");
            }
            
            //rimuovo l'oggetto acquistato
            
            
            
        }
        catch(SQLException e) {
            return false;
        }
        
        return true;
    }
    
    public void setConnectionString(String s){
    	this.connectionString = s;
    }

    public String getConnectionString(){
    	return this.connectionString;
    } 
}

