<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="lang"%>
<c:import url="navbar.jsp"></c:import>


<div class="container" style="margin-top:100px">
	<div class="card border-info">
		<div class="card-header" style="text-align:center">
			<p class="fw-light">Mr/Mme : ${ sessionScope.user.firstName }
				${ sessionScope.user.lastName}</p>
		</div>
		<div class="card-body">
			<div class="card-title" style="text-align:center">
				<p class="fw-light">
					<lang:bundle basename="langue">
						<lang:message key="var14"></lang:message>
					</lang:bundle>
				</p>
			</div>
			<table class="table table-striped">
				<tr>
					<td>ID</td>
					<td>Nom</td>
					<td>Prenom</td>
					<td>Username</td>
					<td>Email</td>
					<td>Phone number</td>
					<td>Numero de compte</td>
					<td>Paiement info</td>
				</tr>
				<c:forEach items="${requestScope.model.clients}" var="client">
					<tr>
						<td>${client.id}</td>
						<td>${client.firstName}</td>
						<td>${client.lastName}</td>
						<td>${client.username}</td>
						<td>${client.email}</td>
						<td>${client.phoneNumber}</td>
						<td>${client.accountNumber}</td>
						<td><a href="clientinfo?id=${client.id }" class="link-info">All Paiements</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

<c:import url="footer.jsp"></c:import>
