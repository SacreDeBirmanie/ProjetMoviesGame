<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>MoviesGame</title>
  </head>

  <body>
   <p>
  <% 

            String nomUser = (String) request.getAttribute("nomUser");

         if (request.getAttribute("nomUser") == null){
         String urlCo = (String) request.getAttribute("urlCo");%>
   			<h1>jeune inconnu(e), soit le bienvenue dans la page d'acceuil du jeu MoviesGame!</h1>
			<p><a href="<% out.println( urlCo ); %>">Se connecter</a></p>
	<% 	}
		else { %>
		<%String urlDeco = (String) request.getAttribute("urlDeco");%>
			<p>Bonjour <% out.println( nomUser ); %>, content de te revoir :)</p>
			<p><a href="<% out.println(urlDeco); %>">Se déconnecter</a></p>
		<% } %>
	</p>
        
    <table>
      <tr>
        <td colspan="2" style="font-weight:bold;">Tu as deux actions possibles :</td>        
      </tr>
      <tr>
        <td><a href="/leJeu/">je veux jouer au jeu</a></td>
      </tr>
      <tr>
        <td><a href="/admin/">je veux administrer le site</a></td>
      </tr>
    </table>
  </body>
</html>
