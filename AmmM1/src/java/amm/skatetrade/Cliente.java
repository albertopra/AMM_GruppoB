/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.skatetrade;

import amm.skatetrade.classi.ObjectSale;
import amm.skatetrade.classi.ObjectSaleFactory;
import amm.skatetrade.classi.SaldoConto;
import amm.skatetrade.classi.SaldoContoFactory;
import amm.skatetrade.classi.Utente;
import amm.skatetrade.classi.UtenteCliente;
import amm.skatetrade.classi.UtenteVenditore;
import amm.skatetrade.classi.UtentiFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alber
 */
@WebServlet(name = "Cliente", urlPatterns = {"/cliente.html"})
public class Cliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        ArrayList<ObjectSale> objectList = ObjectSaleFactory.getInstance()
                .getSellingObjectList();
        ArrayList<Utente> listaUtenti = UtentiFactory.getInstance()
                .getUserList();
        
        //Utilizzato nella jsp per capire se e' stato premuto il link dell'acquisto 
        request.setAttribute("fase_acquisto", false);
        
        //Utilizato nella jsp per capire se e' stato confermato l'acquisto
        request.setAttribute("oggetto_acquistato", false);
        
        //E' stato effettuato l'accesso in una richiesta precedente
        if(session.getAttribute("loggedIn") != null) {
            
            /* Utilizzato nella jsp per visualizzare il relativo messaggio 
               di accesso negato da utente loggato o non */
            request.setAttribute("logged", true);
            
            //Controlla che tipologia di utente ha effettuato l'accesso
            for(Utente u : listaUtenti) {
                if (session.getAttribute("id").equals(u.getId())) {
                    if(u instanceof UtenteCliente) {
                        
                        /* Utilizzato nella jsp per capire se visualizzare o 
                            meno il messaggio di accesso negato */
                        request.setAttribute("accessoNegato", false);
                        
                        request.setAttribute("objectList", objectList);
                        
                        //E' stato premuto il link per l'acquisto
                        if(request.getParameter("id_oggetto") != null) {
                            ObjectSale object = ObjectSaleFactory.getInstance().getObjectSaleById(
                                            Integer.parseInt(request.getParameter("id_oggetto")));
                            
                            request.setAttribute("fase_acquisto", true);
                            request.setAttribute("object", object);
                            
                            //E' stato premuto il pulsante per la conferma dell'acquisto
                            if(request.getParameter("Submit") != null) {
                                ArrayList<SaldoConto> listaSaldoConto = SaldoContoFactory.
                                        getInstance().getSaldoContoList();
                                double saldoContoUtente = 0;
                                
                                request.setAttribute("oggetto_acquistato", true);
                                
                                //Ricerca e salva il saldo conto dell'utente loggato
                                for(SaldoConto s : listaSaldoConto) {
                                    if(s.getUtente().equals(u))
                                        saldoContoUtente = s.getConto();
                                }
                                
                                if(object.getPrezzo() > saldoContoUtente)
                                    request.setAttribute("errore_acquisto", true);
                                else
                                    request.setAttribute("errore_acquisto", false);
                            }
                        }
                    }
                    else {
                        request.setAttribute("accessoNegato", true);
                    }
                }
            }      
        }       
        else {
            request.setAttribute("accessoNegato", true);
            request.setAttribute("logged", false);
        }
        
        request.getRequestDispatcher("cliente.jsp")
                .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
