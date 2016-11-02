<!DOCTYPE html>
<html ng-app="plunker">

  <head>
    <meta charset="utf-8" />
    <title>Movies Game :)</title>
    <script>document.write('<base href="' + document.location + '" />');</script>
    <link href="style.css" rel="stylesheet" />
    <script type="text/javascript" src="https://code.angularjs.org/1.5.8/angular.js" data-require="angular.js@1.5.x" data-semver="1.5.8"></script>
    <script type="text/javascript" src="app.js"></script>
  </head>

  <body>
  <%

            String nomUser = (String) request.getAttribute("nomUser");
  			String idUser = (String) request.getAttribute("idUser");

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
	
    <div ng-controller="madonnee_dynamique as mydonnee_dynamique">
    <div ng-show="mydonnee_dynamique.affiche_connection">
      <form name="reviewForm_dynamique" ng-submit="mydonnee_dynamique.donne_nom()">
        <label><input type="hidden" ng-init="mydonnee_dynamique.monnom='<% out.print(nomUser); %>'"></label><br>
        <label><input type="hidden" ng-init="mydonnee_dynamique.monid='<%out.print(idUser);%>'"></label><br>
    
  <% out.println(idUser);%>
        <input type="submit" value="Commencer le jeu"/>
      </form>
    </div>
      <div class="question" ng-show="mydonnee_dynamique.affiche_form">
        <p>A vous de jouer {{mydonnee_dynamique.monnom}}</p>
        <p>HightScore : {{mydonnee_dynamique.hightscore}}</p>
        <p>Votre Score : {{mydonnee_dynamique.score}}</p>
        <h1>Question {{mydonnee_dynamique.i+1}}/{{mydonnee_dynamique.nb_question}}</h1>
        <p>{{mydonnee_dynamique.madonnee.question}}</p>
        <form name="reviewForm_dynamique" ng-submit="mydonnee_dynamique.donne_reponse()">
          <label><input type="radio" name="cbox2" ng-model="mydonnee_dynamique.myreponse" value="1">{{mydonnee_dynamique.madonnee.reponse1}}</label><br>
          <label><input type="radio" name="cbox2" ng-model="mydonnee_dynamique.myreponse" value="2">{{mydonnee_dynamique.madonnee.reponse2}}</label><br>
          <label><input type="radio" name="cbox2" ng-model="mydonnee_dynamique.myreponse" value="3">{{mydonnee_dynamique.madonnee.reponse3}}</label><br>
          <input type="submit" value="Valider"/>
        </form>
      </div>
      <div class="reponse" ng-show="mydonnee_dynamique.affiche_res">
        <h1 >la rï¿½ponse ï¿½tait : {{mydonnee_dynamique.rep}}</h1>
        <form name="reviewForm_dynamique" ng-submit="mydonnee_dynamique.suivant_inter()">
          <input type="submit" value="Suivant" />
        </form>
      </div>
    </div>
  

<script>
  var init = function() {
    console.log("init called");
    window.init();
  };
</script>

<script src="https://apis.google.com/js/client.js?onload=init"></script>
</body>

</html>