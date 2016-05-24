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
    
    public boolean acquistoObject(int idCliente, float saldoContoCliente, int idOggetto, float prezzoOggetto) throws SQLException {
        int idVenditore = 0;
        float saldoContoVenditore = 0;
        
        Connection conn = DriverManager.getConnection(connectionString, 
                "alberto", "1234");
        
        PreparedStatement getIdVenditore = null;
        PreparedStatement rimozioneOggetto = null;
        PreparedStatement getSaldoContoVenditore = null;
        PreparedStatement modificaSaldo = null;
        ResultSet res;
        
        String queryGetIdVenditore = "select venditore from oggetto where id = ?";
        String queryGetSaldoContoVenditore = "select saldo_conto from utente where id = ?";
        String queryRimozioneOggetto = "delete from oggetto where id = ?";  
        String queryModificaSaldo = "update utente set prezzo = ? where id = ?";
        
        try {    
            conn.setAutoCommit(false);
            
            getIdVenditore = conn.prepareStatement(queryGetIdVenditore);
            getSaldoContoVenditore = conn.prepareStatement(queryGetSaldoContoVenditore);
            rimozioneOggetto = conn.prepareStatement(queryRimozioneOggetto);
            modificaSaldo = conn.prepareStatement(queryModificaSaldo);

            //recupero id e saldo conto venditore
            getIdVenditore.setInt(1, idOggetto);
            res = getIdVenditore.executeQuery();
            
            if(res.next())
                idVenditore = res.getInt("venditore");
            else
                return false;
            
            getSaldoContoVenditore.setInt(1, idVenditore);
            res = getIdVenditore.executeQuery();
            
            if(res.next()) {
                saldoContoVenditore = res.getFloat("saldo_conto");
            }
            else
                return false;
            
            
            //rimuovo l'oggetto acquistato
            rimozioneOggetto.setInt(1, idOggetto);
            
            int updateOggetto = rimozioneOggetto.executeUpdate();
            
            if(updateOggetto != 1) {
                conn.rollback();
                return false;
            }
            
            
            //diminuisco il saldo conto del cliente
            modificaSaldo.setFloat(1, saldoContoCliente - prezzoOggetto);
            modificaSaldo.setInt(2, idCliente);
            
            int updateCliente = modificaSaldo.executeUpdate();
            
            if(updateCliente != 1) {
                conn.rollback();
                return false;
            }
            
            //aumento il saldo conto del venditore
            modificaSaldo.setFloat(1, saldoContoVenditore + prezzoOggetto);
            modificaSaldo.setInt(2, idVenditore);
            int updateVenditore = modificaSaldo.executeUpdate();
            
            if(updateVenditore != 1) {
                conn.rollback();
                return false;
            }
            
            conn.commit();
            conn.setAutoCommit(true);
            
            getIdVenditore.close();
            getSaldoContoVenditore.close();
            rimozioneOggetto.close();
            modificaSaldo.close();
            
            conn.close();
            
            return true;
        }
        catch(SQLException e) {
            try {
                conn.rollback();
            }
            catch(SQLException e2) {
                
            }
            
            return false;
        }
    }
    
    public void setConnectionString(String s){
    	this.connectionString = s;
    }

    public String getConnectionString(){
    	return this.connectionString;
    } 
}

