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
    <title>zone d'administration </title>
  </head>

  <body>
   <p>
  <% 

            String attribut = (String) request.getAttribute("nomUser");

            out.println( attribut );

            %>
</p>
  <% UserService userService = UserServiceFactory.getUserService(); %>
  <h1><%= userService.getCurrentUser().getNickname() %>, soit le bienvenue dans la page d'administration du jeu MoviesGame!</h1>
  
  
	<p><a href="<%= userService.createLogoutURL("/") %>">Se déconnecter</a></p>
	
    <table>
      <tr>
        <td colspan="2" style="font-weight:bold;">Tu peux ici choisir d'effectuer plusieurs actions :</td>        
      </tr>
      <tr>
        <td><a href="/admin/update_datastore">je veux mettre a jour le datastore</a></td>
      <tr>
        <td><a href="/_ah/api/explorer">je veux accéder à l'API</a></td>
      </tr>
      <tr>
        <td><a href="/admin/removeAllMovies">je veux supprimer tous les films</a></td>
      </tr>
      </tr>
    </table>
  </body>
</html>
