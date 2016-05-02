<%-- 
    Document   : venditore
    Created on : 2-mag-2016, 17.48.49
    Author     : alber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>

<div id="content-conteiner">
    <div id="sidebar-left">
        <h3>Navigazione:</h3>
        <div class="nav">
            <a href="descrizione.html">Descrizione</a>
            <a href="login.html">Login</a>
        </div>
    </div>

    <div id="content">
        <h2>Venditore</h2>

        <!-- Form inserimento dati oggetto -->
        <form method="post">
                <div class="input-field">
                    <!-- Nome -->
                    <label for="nome_oggetto">Nome oggetto:</label>
                    <input type="text" name="nome_oggetto" id="nome_oggetto" />
                </div>

                <div class="input-field">
                    <!-- URL immagine -->
                    <label for="url_img">URL immagine:</label>
                    <input type="text" name="url_img" id="url_img" />
                </div>

                <div class="input-field">
                    <!-- Descrizione -->
                    <label for="descrizione">Descrizione:</label>
                    <textarea name="descrizione" id="descrizione"
                     rows="10" cols="40">Descrizione oggetto</textarea>
                </div>    

                <div class="input-field">
                    <!-- Prezzo-->
                    <label for="prezzo">Prezzo:</label>
                    <input type="number" name="prezzo" id="prezzo" />
                </div>

                <div class="input-field">
                    <!-- Quantità-->
                    <label for="quantità">Quantità:</label>
                    <input type="number" name="quantità" id="quantità" />
                </div>

                <div class="input-field">
                    <!-- Submit e reset -->
                    <input type="submit" value="Invia"/>
                    <input type="reset" value="Reimposta"/>
                </div>
        </form>
    </div>
</div>

<%@ include file="footer.jsp" %>