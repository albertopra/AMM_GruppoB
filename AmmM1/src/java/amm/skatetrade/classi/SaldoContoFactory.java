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
public class SaldoContoFactory {
    private static SaldoContoFactory singleton;
    private ArrayList<SaldoConto> listaSaldoConto = new ArrayList<SaldoConto>();
    
    //Lista utenti
    private ArrayList<Utente> listaUtenti = UtentiFactory.getInstance().getUserList();
    
    private SaldoContoFactory() {
        for(Utente u : listaUtenti) {
            SaldoConto saldoConto = new SaldoConto();
            
            saldoConto.setUtente(u);
            saldoConto.setConto(30);
            
            listaSaldoConto.add(saldoConto);
        }
    }
    
    /* Metodi */
    public static SaldoContoFactory getInstance() {
        if (singleton == null) {
            singleton = new SaldoContoFactory();
        }
        
        return singleton;
    }
    
    public ArrayList<SaldoConto> getSaldoContoList() {
        return listaSaldoConto;
    }
}
