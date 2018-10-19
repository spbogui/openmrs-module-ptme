<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fct" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<%@ include file="includeScript.jsp"%>

<h2 class="" style="padding-bottom: 15px">
    <spring:message code="ptme.manage" />
</h2>

<openmrs:hasPrivilege privilege="Manage PTME Reports">

    <ul id="menu">

        <li <c:if test='<%= request.getRequestURI().contains("/report") %>'>class="active first"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/manageReport.form">
                <spring:message code="ptme.report.submenu.title.manage.report" />
            </a>
        </li>
        <li <c:if test='<%= request.getRequestURI().contains("/manageReport") %>'>class="active first"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/manageReport.form">
                <spring:message code="ptme.report.submenu.title.manage.report" />
            </a>
        </li>

        <li <c:if test='<%= request.getRequestURI().contains("/reportIndicator") %>'>class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/reportIndicator.form">
                <spring:message code="ptme.report.submenu.title.indicator" />
            </a>
        </li>

        <!-- Add further links here -->

        <li <c:if test='<%= request.getRequestURI().contains("/reportDataSet.") %>'>class="active first"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/reportDataSet.form">
                <spring:message code="ptme.report.submenu.title.dataset" />
            </a>
        </li>

        <li <c:if test='<%= request.getRequestURI().contains("/reportTemplate") %>'>class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/reportTemplate.form">
                <spring:message code="ptme.report.submenu.title.template" />
            </a>
        </li>
    </ul>
</openmrs:hasPrivilege>