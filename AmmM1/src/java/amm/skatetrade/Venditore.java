/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.skatetrade;

import amm.skatetrade.classi.ObjectSale;
import amm.skatetrade.classi.ObjectSaleFactory;
import amm.skatetrade.classi.UtenteCliente;
import amm.skatetrade.classi.Utente;
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
@WebServlet(name = "Venditore", urlPatterns = {"/venditore.html"})
public class Venditore extends HttpServlet {

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
        ArrayList<Utente> listaUtenti = UtentiFactory.getInstance()
                .getUserList();
        
        /* L'attributo "datiInviati" sara' utilizzato nella jsp per
           decidere se devono essere visualizzati i dati dell'oggetto
           o meno */ 
        request.setAttribute("datiInviati", false);
        
        //E' stato effettuato l'accesso in una richiesta precedente
        if(session.getAttribute("loggedIn") != null) {
            
            /* L'attributo "logged" sara' utilizzato nella jsp per
               visualizzare il relativo messaggio di accesso negato
               da utente loggato o non */
            request.setAttribute("logged", true);
            
            //Controlla che tipologia di utente ha effettuato l'accesso
            for(Utente u : listaUtenti) {
                if (session.getAttribute("id").equals(u.getId())) {
                    if(u instanceof UtenteVenditore) {
                        
                        /* L'attributo "accessoNegato" sara' utilizzato nella
                           jsp per capire se visualizzare o meno il messaggio 
                           di accesso negatov*/
                        request.setAttribute("accessoNegato", false);
                        
                        //Sono stati inviati dei dati dal form
                        if(request.getParameter("Submit") != null) {
                            ObjectSale object = new ObjectSale();
                            
                            request.setAttribute("datiInviati", true);
                            
                            object.setNome(request.getParameter("Nome_oggetto"));
                            object.setUrlImmagine(request.getParameter("Url_immagine"));
                            object.setDescrizione(request.getParameter("Descrizione"));
                            object.setPrezzo(Float.parseFloat(request.getParameter("Prezzo")));
                            object.setQuantita(Integer.parseInt(request.getParameter("Quantita")));
                            
                            request.setAttribute("object", object);
                            
                            //Inserisco l'oggetto
                            ObjectSaleFactory.getInstance().addObject(object, 
                                    (int) session.getAttribute("id"));
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
        
        request.getRequestDispatcher("venditore.jsp")
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
