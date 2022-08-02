<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="lang"%>
<c:import url="navbar.jsp"></c:import>


<div class="container" style="margin-top: 100px">
	<h1 class="display-4">
		<lang:bundle basename="langue">
			<lang:message key="var13"></lang:message>
		</lang:bundle>
	</h1>
	<button type="button" class="btn btn-outline-success"
		style="margin-left: 420px; margin-top: 50px">
		<a href="generatepdf?client=${ sessionScope.user.id }" class="link-success"
			style="text-decoration: none">Télécharger votre facture</a>
	</button>
</div>
<c:import url="footer.jsp"></c:import>
