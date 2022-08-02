<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="lang"%>
<c:import url="navbar.jsp"></c:import>


<div class="container" style="margin-top: 100px">
	<div class="card border-success">
		<div class="card-header" style="text-align: center">
			<p class="fw-light">Mr/Mme : ${ sessionScope.user.firstName } ${ sessionScope.user.lastName }</p>
		</div>
		<div class="card-body">
			<div class="card-title" style="text-align: center">
				<p class="fw-light">
					<lang:bundle basename="langue">
						<lang:message key="var15"></lang:message>
					</lang:bundle>${requestScope.model.client.firstName } ${requestScope.model.client.lastName }
				</p>
			</div>
			<table class="table table-success table-striped">
				<tr>
					<td>ID Paiement</td>
					<td>Date Paiement</td>
					<td>Montant Payer</td>
					<td>Abonnement</td>
				</tr>
				<c:forEach items="${requestScope.model.paiements}" var="p">
					<tr>
						<td>${p.id}</td>
						<td>${p.date}</td>
						<td>${p.montant}</td>
						<td>${p.abonnementName}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="card-footer">
			<div class="d-grid gap-2">
				<a href="Login" class="btn btn-success" style="text-decoration:none;color:white">Revenir au liste principale</a>
			</div>
		</div>
	</div>
	
</div>


<c:import url="footer.jsp"></c:import>
