<div class="box">
    <ul id="menu">

        <openmrs:hasPrivilege privilege="Run PTME Reports">
            <li <c:if test='<%= request.getRequestURI().contains("/reportGenerate") %>'>class="active first"</c:if>
                <c:if test='<%= !request.getRequestURI().contains("/reportGenerate") %>'>class="first"</c:if>>
                <a href="${pageContext.request.contextPath}/module/ptme/reportGenerate.form">
                    <spring:message code="ptme.report.submenu.title.generate" />
                </a>
            </li>

            <!-- Add further links here -->

            <li <c:if test='<%= request.getRequestURI().contains("/report.") %>'>class="active"</c:if>>
                <a href="${pageContext.request.contextPath}/module/ptme/report.form">
                    <spring:message code="ptme.report.submenu.title.list" />
                </a>
            </li>
        </openmrs:hasPrivilege>

        <openmrs:hasPrivilege privilege="Manage PTME Reports">
            <%--<li <c:if test='<%= request.getRequestURI().contains("/reportManagement") %>'>class="active"</c:if>>--%>
            <%--<a href="${pageContext.request.contextPath}/module/ptme/reportManagement.form">--%>
            <%--<spring:message code="ptme.report.submenu.title.management" />--%>
            <%--</a>--%>
            <%--</li>--%>

            <li <c:if test='<%= request.getRequestURI().contains("/manageReport") %>'>class="active"</c:if>>
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

            <li <c:if test='<%= request.getRequestURI().contains("/reportDataSet.") %>'>class="active"</c:if>>
                <a href="${pageContext.request.contextPath}/module/ptme/reportDataSet.form">
                    <spring:message code="ptme.report.submenu.title.dataset" />
                </a>
            </li>

            <li <c:if test='<%= request.getRequestURI().contains("/reportTemplate") %>'>class="active"</c:if>>
                <a href="${pageContext.request.contextPath}/module/ptme/reportTemplate.form">
                    <spring:message code="ptme.report.submenu.title.template" />
                </a>
            </li>
        </openmrs:hasPrivilege>
    </ul>
</div>

<div class="boxHeader"></div>