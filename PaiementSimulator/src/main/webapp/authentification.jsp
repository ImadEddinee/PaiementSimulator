<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="lang"%>
<c:import url="header.jsp"></c:import>

<div class="container" style="width: 300px; margin-top: 20px">
	<form method="GET" action="setLangue">
		<select name="langue" id="language" class="form-select"
			aria-label="Default select example">
			<option value="ang">Anglais</option>
			<option value="fr">Francais</option>
		</select>
		<div class="d-grid gap-2" style="margin-top: 10px">
			<button class="btn btn-outline-info" type="submit">Change</button>
		</div>

	</form>
</div>
<lang:setLocale value="${ cookie.lang != null? cookie.lang.value :'ang' }" />

<div class="card"
	style="width: 25rem; margin-top: 60px; margin-left: 440px">
	<div class="card-header" style="text-align: center">
		<lang:bundle basename="langue">
			<lang:message key="var4"></lang:message>
		</lang:bundle>
	</div>
	<div class="card-body">
		<form action="authentification" method="post">
			<div class="mb-3">
				<label for="username" class="form-label"> <lang:bundle
						basename="langue">
						<lang:message key="var1"></lang:message>
					</lang:bundle>
				</label>
			<input type="text" class="form-control" id="logging" name="username" >
			</div>
			<div class="mb-3">
				<label for="password" class="form-label"> <lang:bundle
						basename="langue">
						<lang:message key="var2"></lang:message>
					</lang:bundle>
				</label> <input type="password" class="form-control" name="password"
					id="password">
			</div>
			<div class="d-grid gap-2">
				<button class="btn btn-outline-success" type="submit">
					<lang:bundle basename="langue">
						<lang:message key="var3"></lang:message>
					</lang:bundle>
				</button>
			</div>
		</form>
	</div>
	<c:if
		test="${  requestScope.model.badCredentials }">
		<div class="card-footer">
		<div class="alert alert-danger" role="alert">
			<lang:bundle basename="langue">
				<lang:message key="var5"></lang:message>
			</lang:bundle>
		</div>
	</div>
	</c:if>
</div>


<c:import url="footer.jsp"></c:import>
