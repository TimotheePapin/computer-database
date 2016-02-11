<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<%@ attribute name="webPage"
	type="com.excilys.formation.java.computerdatabase.web.dto.Page"
	description="Contains all the information of the page"%>


<div class="container text-center">
	<ul class="pagination">
		<c:if test="${webPage.page == 1}">
			<li><a id="previousPage" style="background: #D8D8D8" href="#" aria-label="Previous"><span
					aria-hidden="true">&laquo;</span></a></li>
		</c:if>
		<c:if test="${webPage.page != 1}">
			<li><a id="previousPage"
				href="<mylib:link target="dashboard" page="${webPage.page-1}" listSize="${webPage.listSize}" search="${webPage.search}" by="${webPage.by}" order="${webPage.order}"/>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:if test="${webPage.page-2 > 0}">
			<li><a id="switchPageMalus2"
				href="<mylib:link target="dashboard" page="${webPage.page-2}" listSize="${webPage.listSize}" search="${webPage.search}" by="${webPage.by}" order="${webPage.order}"/>">${webPage.page-2}</a></li>
		</c:if>
		<c:if test="${webPage.page-1 > 0}">
			<li><a id="switchPageMalus1"
				href="<mylib:link target="dashboard" page="${webPage.page-1}" listSize="${webPage.listSize}" search="${webPage.search}" by="${webPage.by}" order="${webPage.order}"/>">${webPage.page-1}</a></li>
		</c:if>
		<li><a id="switchCurrentPage" style="background: #D8D8D8"
			href="#">${webPage.page}</a></li>
		<c:if test="${webPage.page+1 <= webPage.pageMax}">
			<li><a id="switchPageAdd1"
				href="<mylib:link target="dashboard" page="${webPage.page+1}" listSize="${webPage.listSize}" search="${webPage.search}" by="${webPage.by}" order="${webPage.order}"/>">${webPage.page+1}</a>
			</li>
		</c:if>
		<c:if test="${webPage.page+2 <= webPage.pageMax}">
			<li><a id="switchPageAdd2"
				href="<mylib:link target="dashboard" page="${webPage.page+2}" listSize="${webPage.listSize}" search="${webPage.search}" by="${webPage.by}" order="${webPage.order}"/>">${webPage.page+2}</a></li>
		</c:if>
		<c:if test="${webPage.page == webPage.pageMax}">
			<li><a id="nextPage" style="background: #D8D8D8" href="#" aria-label="Next"><span
					aria-hidden="true">&raquo;</span></a></li>
		</c:if>
		<c:if test="${webPage.page != webPage.pageMax}">
			<li><a id="nextPage"
				href="<mylib:link target="dashboard" page="${webPage.page+1}" listSize="${webPage.listSize}" search="${webPage.search}" by="${webPage.by}" order="${webPage.order}"/>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>

	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			<c:if test="${webPage.listSize == 10}">	style="background:#D8D8D8" 
		</c:if>
			onclick="document.location.href='dashboard?listSize=10&page=1&search=${webPage.search}&by=${webPage.by}&order=${webPage.order}'">10
		</button>
		<button type="button" class="btn btn-default"
			<c:if test="${webPage.listSize == 50}">	style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?listSize=50&page=1&search=${webPage.search}&by=${webPage.by}&order=${webPage.order}'">50</button>
		<button type="button" class="btn btn-default"
			<c:if test="${webPage.listSize == 100}">style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?listSize=100&page=1&search=${webPage.search}&by=${webPage.by}&order=${webPage.order}'">100</button>
	</div>
</div>

