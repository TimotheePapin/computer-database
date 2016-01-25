<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" description="Current page"%>
<%@ attribute name="page" description="Current page"%>
<%@ attribute name="size" description="Number of element for each page"%>
<%@ attribute name="search" description="Searched value"%>
<%@ attribute name="order" description="ASC or DESC" %>
<%@ attribute name="by" description="Column name of the ordering" %>

<c:url value="${target}?page=${page}&size=${size}&search=${search}&by=${by}&order=${order}"/>