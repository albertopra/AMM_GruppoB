<%-- 
    Document   : cliente
    Created on : 2-mag-2016, 17.54.30
    Author     : alber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>

<div id="content-conteiner">
    <div id="sidebar-left">
        <h3>Navigazione:</h3>

        <!-- Navigazione -->
        <div class="nav">
            <a href="descrizione.html">Descrizione</a>
            <a href="login.html">Login</a>
        </div>
    </div>

    <div id="content">
        <h2>Cliente</h2>

        <!-- Tabella oggetti -->
        <table>
            <tr>
                <th>Nome</th>
                <th>Foto</th>
                <th>Quantità</th>
                <th>Prezzo</th>
                <th>Link acquisto</th>
            </tr>

            <tr>
                <td>Hudora Skateboard, ABEC 5</td>
                <td>
                    <img src="img/hudora_skate.jpg" width="100" 
                         height="100" alt="Hudora skate"/>
                </td>
                <td>5 pezzi</td>
                <td>25,46 €</td>
                <td><a href="cliente.html">Acquista ora!</a></td>
            </tr>

            <tr>
                <td>Truck Independent: 149 Salazar Doomsayers Matte Nero Std Stage 11</td>
                <td>
                    <img src="img/truck_independent.jpg" width="100" 
                         height="100" alt="Truck independent"/>
                </td>
                <td>12 pezzi</td>
                <td>40,71 €</td>
                <td><a href="cliente.html">Acquista ora!</a></td>
            </tr>

            <tr>
                <td>Tavola Element: Westgate Split 8</td>
                <td>
                    <img src="img/tavola_element.jpg" width="100" 
                         height="100" alt="Tavola element"/>
                </td>
                <td>7 pezzi</td>
                <td>58,70 €</td>
                <td><a href="cliente.html">Acquista ora!</a></td>
            </tr>

            <tr>
                <td>Tavola Zero: Dead Presidents Jamie Thomas 8.375</td>
                <td>
                    <img src="img/tavola_zero.jpg" width="100" 
                         height="100" alt="Tavola zero"/>
                </td>
                <td>9 pezzi</td>
                <td>63,82 €</td>
                <td><a href="cliente.html">Acquista ora!</a></td>
            </tr>

            <tr>
                <td>Spitfire - Kit di 4 ruote per skateboard Bighead</td>
                <td>
                    <img src="img/ruote_spitfire.jpg" width="100" 
                         height="100" alt="Ruote Spitfire"/>
                </td>
                <td>13 pezzi</td>
                <td>46,57 €</td>
                <td><a href="cliente.html">Acquista ora!</a></td>
            </tr>

            <tr>
                <td>Cuscinetti Skateboard Enuff Black</td>
                <td>
                    <img src="img/cuscinetti_enuff_black.jpg" width="100" 
                         height="100" alt="Cuscinetti Enuff Black"/>
                </td>
                <td>11 pezzi</td>
                <td>14,99 €</td>
                <td><a href="cliente.html">Acquista ora!</a></td>
            </tr>
        </table>
    </div>
</div>

<%@ include file="footer.jsp" %>
