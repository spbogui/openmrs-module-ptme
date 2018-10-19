<div class="box">
    <ul id="menu">

        <li class="<c:if test='<%= request.getRequestURI().contains("/childManage") %>'>active</c:if> first">
            <a href="${pageContext.request.contextPath}/module/ptme/childManage.form">
                Enfants Expos&eacute;s
            </a>
        </li>

        <li <c:if test='<%= request.getRequestURI().contains("/childFollowup.") %>'>class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/childFollowup.form">
                Saisie des visites
            </a>
        </li>

        <!-- Add further links here -->

        <li <c:if test='<%= request.getRequestURI().contains("/childFollowupManage.") %>'>class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/childFollowupManage.form">
                Historique des fiches
            </a>
        </li>

    </ul>
</div>
<div class="boxHeader"></div>