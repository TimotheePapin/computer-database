<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" description="Current page"%>
<%@ attribute name="page" description="Current page"%>
<%@ attribute name="size" description="Number of element for each page"%>


<c:url value="${target}?page=${page}&size=${size}" />