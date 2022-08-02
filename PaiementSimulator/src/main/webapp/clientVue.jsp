<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="lang"%>
<c:import url="navbar.jsp"></c:import>

<lang:setLocale
	value="${ cookie.lang != null? cookie.lang.value :'ang' }" />

<div class="container" style="width: 500px; margin-top: 20px">
	<div class="card">
		<div class="card-header" style="text-align: center">
			<lang:bundle basename="langue">
				<lang:message key="var6"></lang:message>
			</lang:bundle>
		</div>
		<div class="card-body">
			<form method="post" action="paiement">
				<p class="fw-light">Mr/Mme : ${ sessionScope.user.firstName }
					${ sessionScope.user.lastName }</p>
				<p class="fw-light">N° Compte : ${ sessionScope.user.accountNumber }</p>
				<p class="fw-light">Montant : ${ requestScope.model.abonnement.montant }</p>
				<div class="mb-3">
					<label for="numeroCarte" class="form-label"> <lang:bundle
							basename="langue">
							<lang:message key="var8"></lang:message>
						</lang:bundle>
					</label> <input type="text" class="form-control" id="numeroCarte"
						name="numeroCarte" required>
				</div>
				<div class="mb-3">
					<label for="expDate" class="form-label"> <lang:bundle
							basename="langue">
							<lang:message key="var9"></lang:message>
						</lang:bundle>
					</label> <input type="text" class="form-control" id="expDate" placeholder="MM/YY"
						name="dateExpiration"required>
				</div>
				<div class="mb-3">
					<label for="lastDigits" class="form-label"> <lang:bundle
							basename="langue">
							<lang:message key="var10"></lang:message>
						</lang:bundle>
					</label> <input type="text" class="form-control" id="lastDigits"
						name="lastDigits" required>
				</div>
				<input type="hidden" name="abonnementId" value="${ requestScope.model.abonnement.id}">
					<div class="d-grid gap-2">
						<button class="btn btn-outline-success" type="submit">
							<lang:bundle basename="langue">
								<lang:message key="var11"></lang:message>
							</lang:bundle>
						</button>
					</div>
			</form>
		</div>

		<c:if test="${ requestScope.model.badCredentials }">
			<div class="card-footer">
				<div class="alert alert-danger" role="alert">
					<lang:bundle basename="langue">
						<lang:message key="var7"></lang:message>
					</lang:bundle>
				</div>
			</div>
		</c:if> 

	</div>
</div>

<c:import url="footer.jsp"></c:import>
