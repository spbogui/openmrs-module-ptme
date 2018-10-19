

<div class="box">
    <ul id="menu">

        <li <c:if test='<%= request.getRequestURI().contains("/motherFollowupPatient") %>'>class="active first"</c:if>
            <c:if test='<%= !request.getRequestURI().contains("/motherFollowupPatient") %>'>class="first"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/motherFollowupPatient.form">
                Femmes enceintes VIH +
            </a>
        </li>

        <!-- Add further links here -->
        <li <c:if test='<%= request.getRequestURI().contains("/motherFollowup.") %>'>class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/motherFollowup.form">
                <c:if test="${mode != 'form'}">Saisie des visites de suivi</c:if>
                <c:if test="${mode == 'form'}">Choisir autre femme enceinte</c:if>
            </a>
        </li>

        <li <c:if test='<%= request.getRequestURI().contains("/motherFollowupManage") %>'>class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/module/ptme/motherFollowupManage.form">
                Historique des fiches
            </a>
        </li>

    </ul>
</div>
<div class="boxHeader"></div>