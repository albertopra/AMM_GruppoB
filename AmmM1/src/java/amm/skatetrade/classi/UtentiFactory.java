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
    private ArrayList<Utente> listaClienti = new ArrayList<Utente>();
    private ArrayList<Utente> listaVenditori = new ArrayList<Utente>();
    
    /* Costruttore */
    private UtentiFactory() {
        UtenteCliente cliente1 = new UtenteCliente();
        cliente1.setId(1);
        cliente1.setNome("Alberto");
        cliente1.setCognome("Pranteddu");
        cliente1.setUsername("apranteddu");
        cliente1.setPassword("psw1");
        listaClienti.add(cliente1);
        
        UtenteVenditore venditore1 = new UtenteVenditore();
        venditore1.setId(2);
        venditore1.setNome("Mario");
        venditore1.setCognome("Rossi");
        venditore1.setUsername("mrossi");
        venditore1.setPassword("psw2");
        listaVenditori.add(venditore1);
    }
    
    /* Metodi */
    public static UtentiFactory getInstance() {
        if (singleton == null) {
            singleton = new UtentiFactory();
        }
        return singleton;
    }
    
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

