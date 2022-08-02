<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="lang"%>
<c:import url="navbar.jsp"></c:import>

<lang:setLocale value="${ cookie.lang != null? cookie.lang.value :'ang' }" />

<div
	style="display: flex; justify-content: space-around; align-items: center; margin-bottom: 40px; margin-top: 20px">
	<h1 class="fs-1 fw-light">		<lang:bundle basename="langue">
			<lang:message key="var16"></lang:message>
		</lang:bundle> ${ sessionScope.user.firstName }
		${ sessionScope.user.lastName }</h1>
</div>

<div class="container">
	<c:forEach items="${requestScope.model.abonnements }" var="abonnement">
		<div class="card ${  !abonnement.is_paid ? 'border-danger' : 'border-success' } text-center" style="margin-bottom: 20px;">
			<div class="card-header ${  !abonnement.is_paid ? 'bg-danger text-white' : 'bg-success text-white' }">${abonnement.name }</div>
			<div class="card-body">
				<h5 class="card-title">		<lang:bundle basename="langue">
			<lang:message key="var17"></lang:message>
		</lang:bundle> ${abonnement.paid_at }</h5>
				<c:if test="${  !abonnement.is_paid }">
					<p class="card-text">		<lang:bundle basename="langue">
			<lang:message key="var18"></lang:message>
		</lang:bundle>.</p>
					
					<a href="paiement?id=${ abonnement.id }" class="btn btn-primary ">		<lang:bundle basename="langue">
			<lang:message key="var19"></lang:message>
		</lang:bundle></a>
				</c:if>
				<c:if test="${  abonnement.is_paid }">
					<p class="card-text">
						<a href="paiements">		<lang:bundle basename="langue">
			<lang:message key="var20"></lang:message>
		</lang:bundle></a><div></div> <a
							href="paiement?id=${ abonnement.id }"
							class="btn btn-primary disabled">		<lang:bundle basename="langue">
			<lang:message key="var19"></lang:message>
		</lang:bundle></a>
					</p>
				</c:if>
			</div>
			<c:if test="${  abonnement.is_paid }">
				<div class="card-footer ${ !abonnement.is_paid ? 'border-danger' : 'border-success' } text-muted">		<lang:bundle basename="langue">
			<lang:message key="var21"></lang:message>
		</lang:bundle> ${ abonnement.daysDiff } 		<lang:bundle basename="langue">
			<lang:message key="var23"></lang:message>
		</lang:bundle></div>
			</c:if>
			<c:if test="${  !abonnement.is_paid }">
				<div class="card-footer ${  !abonnement.is_paid ? 'border-danger' : 'border-success' } text-muted">		<lang:bundle basename="langue">
			<lang:message key="var22"></lang:message>
		</lang:bundle> ${ abonnement.daysDiff - 30 } 		<lang:bundle basename="langue">
			<lang:message key="var23"></lang:message>
		</lang:bundle></div>
			</c:if>
		</div>
	</c:forEach>
</div>



<c:import url="footer.jsp"></c:import>
