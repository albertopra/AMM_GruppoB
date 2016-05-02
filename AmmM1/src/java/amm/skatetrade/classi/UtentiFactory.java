/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.skatetrade.classi;

import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class UtentiFactory {
    // Attributi
    private static UtentiFactory singleton;
    public static UtentiFactory getInstance() {
        if (singleton == null) {
            singleton = new UtentiFactory();
        }
        return singleton;
    }
    
    private ArrayList<Utente> listaClienti = new ArrayList<Utente>();
    private ArrayList<Utente> listaVenditori = new ArrayList<Utente>();
    
    /* Costruttore */
    private UtentiFactory() {
        Cliente cliente = new Cliente();
        Venditore venditore = new Venditore();
        
        cliente.setId(1);
        cliente.setNome("Alberto");
        cliente.setCognome("Pranteddu");
        cliente.setUsername("tarenpudd");
        cliente.setPassword("ciao1");
        
        listaClienti.add(cliente);
        
        venditore.setId(2);
        venditore.setNome("Mario");
        venditore.setCognome("Rossi");
        venditore.setUsername("mrossi");
        venditore.setPassword("ciao2");
        
        listaVenditori.add(venditore);
    }
    
    /* Metodi */
    public ArrayList<Utente> getClienteList()
    {
        return listaClienti;
    }
    public Utente getCliente(int id)
    {
        for(Utente u : listaClienti)
        {
            if(u.id == id)
                return u;
        }
        
        return null;
    }
    public ArrayList<Utente> getVenditoreList()
    {
        return listaVenditori;
    }
    public Utente getVenditore(int id)
    {
        for(Utente u : listaVenditori)
        {
            if(u.id == id)
                return u;
        }
        
        return null;
    }
    public ArrayList<Utente> getUserList() 
    {
        ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
        
        listaUtenti.addAll(listaClienti);
        listaUtenti.addAll(listaVenditori);
        
        return listaUtenti;
    }
}

