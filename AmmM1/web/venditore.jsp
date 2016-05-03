<%-- 
    Document   : venditore
    Created on : 2-mag-2016, 17.48.49
    Author     : alber
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<div id="content">
    <h2>Venditore</h2>

    <c:choose> 
        <c:when test="${accessoNegato == true}">
            <p>Accesso Negato!</p>
            <c:choose>
                <c:when test="${logged == true}">
                    <p>L'utente cliente non può avere accesso alla sezione del venditore.</p>
                </c:when>
                <c:otherwise>
                    <p>Si prega di effettuare l'accesso.</p>
                </c:otherwise>
            </c:choose>
        </c:when>

        <c:otherwise>
            <c:choose>
                <c:when test="${datiInviati == true}">
                    <h3 style="color: black">Inserimento oggetto avvenuto con successo!</h3>
                    <p>Ecco un riepilogo dei dati:</p>
                    <ul>
                        <li><strong>ID</strong>: ${object.getId()}</li>
                        <li><strong>Nome</strong>: ${object.getNome()}</li>
                        <li><strong>Url immagine</strong>: ${object.getUrlImmagine()}</li>
                        <li><strong>Descrizione</strong>: ${object.getDescrizione()}</li>
                        <li><strong>Prezzo</strong>: ${object.getPrezzo()}</li>
                        <li><strong>Quantita</strong>: ${object.getQuantita()}</li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <!-- Form inserimento dati oggetto -->
                    <form method="post" action="venditore.html">
                            <div class="input-field">
                                <!-- Nome -->
                                <label for="nome_oggetto">Nome oggetto:</label>
                                <input type="text" name="Nome_oggetto" id="nome_oggetto" />
                            </div>

                            <div class="input-field">
                                <!-- URL immagine -->
                                <label for="url_img">URL immagine:</label>
                                <input type="text" name="Url_immagine" id="url_img" />
                            </div>

                            <div class="input-field">
                                <!-- Descrizione -->
                                <label for="descrizione">Descrizione:</label>
                                <textarea name="Descrizione" id="descrizione"
                                 rows="10" cols="40">Descrizione oggetto</textarea>
                            </div>    

                            <div class="input-field">
                                <!-- Prezzo-->
                                <label for="prezzo">Prezzo:</label>
                                <input type="number" value="0" name="Prezzo" id="prezzo" />
                            </div>

                            <div class="input-field">
                                <!-- Quantità-->
                                <label for="quantità">Quantità:</label>
                                <input type="number" value="0" name="Quantita" id="quantità" />
                            </div>

                            <div class="input-field">
                                <!-- Submit e reset -->
                                <input type="submit" name="Submit" value="Invia"/>
                                <input type="reset" value="Reimposta"/>
                            </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="footer.jsp" />