<%-- 
    Document   : cliente
    Created on : 2-mag-2016, 17.54.30
    Author     : alber
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<div id="content">
    <h2>Cliente</h2>
    
    <c:choose> 
        <c:when test="${accessoNegato == true}">
            <p>Accesso Negato!</p>
            <c:choose>
                <c:when test="${logged == true}">
                    <p>L'utente venditore non può avere accesso alla sezione del cliente.</p>
                </c:when>
                <c:otherwise>
                    <p>Si prega di effettuare l'accesso.</p>
                </c:otherwise>
            </c:choose>
        </c:when>

        <c:otherwise>
            <c:choose>
                <c:when test="${fase_acquisto == true}">
                    <c:choose>
                        <c:when test="${oggetto_acquistato == true}">
                            <c:choose>
                                <c:when test="${errore_acquisto == true}">
                                    <p>Attenzione! Non è stato possibile effettuare la transazione.</p>
                                    <a href="cliente.html">Torna indietro.</a>
                                </c:when>
                                <c:otherwise>
                                    <p>L'oggetto e' stato aquistato correttamente!</p>
                                    <a href="cliente.html">Torna indietro.</a>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <p>Ecco un riepilogo dei dati:</p>
                            <ul>
                                <li><strong>ID</strong>: ${object.getId()}</li>
                                <li><strong>Nome</strong>: ${object.getNome()}</li>
                                <li><strong>Url immagine</strong>: ${object.getUrlImmagine()}</li>
                                <li><strong>Descrizione</strong>: ${object.getDescrizione()}</li>
                                <li><strong>Prezzo</strong>: ${object.getPrezzo()}</li>
                                <li><strong>Quantita</strong>: ${object.getQuantita()}</li>
                            </ul>
                            <form method="post" action="cliente.html?id_oggetto=${object.getId()}" >
                                <input type="submit" name="Submit" value="Acquista!"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <!-- Tabella oggetti -->
                    <table>
                        <tr>
                            <th>Nome</th>
                            <th>Foto</th>
                            <th>Quantità</th>
                            <th>Prezzo</th>
                            <th>Link acquisto</th>
                        </tr>
                        
                        <c:forEach var="obj" items="${objectList}">
                            <tr>
                                <td>${obj.getNome()}</td>
                                <td><img src=${obj.getUrlImmagine()} width="100" 
                                     height="100"/>
                                </td>
                                <td>${obj.getQuantita()}</td>
                                <td>${obj.getPrezzo()}€</td>
                                <td><a href="cliente.html?id_oggetto=${obj.getId()}">Acquista ora!</a></td>
                            </tr>  
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
   
</div>

<jsp:include page="footer.jsp" />
