<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<%@ attribute name="requestComp" type="com.excilys.formation.java.computerDatabase.model.RequestComputer" description="Contains all the information of the page"%>


<div class="container text-center">
	<ul class="pagination">
		<c:if test="${requestComp.page != 1}">
			<li><a
				href="<mylib:link target="dashboard" page="${requestComp.page-1}" size="${requestComp.listSize}" search="${requestComp.search}" by="${requestComp.by}" order="${requestComp.order}"/>"aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:if test="${requestComp.page-2 > 0}">
			<li><a id="switchPageMalus2"
				href="<mylib:link target="dashboard" page="${requestComp.page-2}" size="${requestComp.listSize}" search="${requestComp.search}" by="${requestComp.by}" order="${requestComp.order}"/>">${requestComp.page-2}</a></li>
		</c:if>
		<c:if test="${requestComp.page-1 > 0}">
			<li><a id="switchPageMalus1"
				href="<mylib:link target="dashboard" page="${requestComp.page-1}" size="${requestComp.listSize}" search="${requestComp.search}" by="${requestComp.by}" order="${requestComp.order}"/>">${requestComp.page-1}</a></li>
		</c:if>
		<li><a id="switchCurrentPage" style="background: #D8D8D8"
			href="#">${requestComp.page}</a></li>
		<c:if test="${requestComp.page+1 <= requestComp.pageMax}">
			<li><a id="switchPageAdd1"
				href="<mylib:link target="dashboard" page="${requestComp.page+1}" size="${requestComp.listSize}" search="${requestComp.search}" by="${requestComp.by}" order="${requestComp.order}"/>">${requestComp.page+1}</a>
			</li>
		</c:if>
		<c:if test="${requestComp.page+2 <= requestComp.pageMax}">
			<li><a id="switchPageAdd2"
				href="<mylib:link target="dashboard" page="${requestComp.page+2}" size="${requestComp.listSize}" search="${requestComp.search}" by="${requestComp.by}" order="${requestComp.order}"/>">${requestComp.page+2}</a></li>
		</c:if>

		<c:if test="${requestComp.page != requestComp.pageMax}">
			<li><a href="<mylib:link target="dashboard" page="${requestComp.page+1}" size="${requestComp.listSize}" search="${requestComp.search}" by="${requestComp.by}" order="${requestComp.order}"/>" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>

	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default" 
			<c:if test="${requestComp.listSize == 10}">	style="background:#D8D8D8" 
		</c:if>
		onclick="document.location.href='dashboard?size=10&page=1&search=${requestComp.search}&by=${requestComp.by}&order=${requestComp.order}'">10
		</button>
		<button type="button" class="btn btn-default"
			<c:if test="${requestComp.listSize == 50}">	style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?size=50&page=1&search=${requestComp.search}&by=${requestComp.by}&order=${requestComp.order}'">50</button>
		<button type="button" class="btn btn-default"
			<c:if test="${requestComp.listSize == 100}">style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?size=100&page=1&search=${requestComp.search}&by=${requestComp.by}&order=${requestComp.order}'">100</button>
	</div>
</div>

