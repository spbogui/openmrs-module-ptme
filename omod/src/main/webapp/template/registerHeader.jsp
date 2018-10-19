<%--<h3>--%>
    <%--<spring:message code="ptme.registerSubtitle" />--%>
<%--</h3>--%>
<%--<hr>--%>

<div class="box">
    <ul id="menu">

        <li <c:if test='<%= request.getRequestURI().contains("/registerList") %>'>class="active first"</c:if>
            <c:if test='<%= !request.getRequestURI().contains("/registerList") %>'>class="first"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/registerList.form">
                <%--<spring:message code="ptme.registerForm" />--%>
                Saisie des consultations
            </a>
        </li>

        <!-- Add further links here -->

        <li <c:if test='<%= request.getRequestURI().contains("/register.") %>'>class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/register.form">
                Historique des consultations
            </a>
        </li>

    </ul>
</div>
<div class="boxHeader"></div>