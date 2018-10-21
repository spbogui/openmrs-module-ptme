<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage PTME" otherwise="/login.htm" redirect="/module/ptme/motherFollowupPatient.form" />

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>


<script type="application/javascript">
    if (jQuery) {
        $(document).ready(function () {
            $("#list-patient-to-follow").dataTable({
                /*dom: 'B<"clear">lfrtip',
                buttons: {
                    name: 'primary',
                    buttons: [ 'copy', 'csv', 'excel' ]
                },*/
                "pageLength": 20,
                "order": [[1, "desc"]],
                "language": {
                    "zeroRecords": "Aucune donn&eacute;e &agrave; afficher",
                    "emptyTable": "Aucune donn&eacute;e",
                    paginate: {
                        previous: 'Pr&eacute;c&eacute;dent',
                        next:     'Suivant'
                    },
                    "info":"Affichage de _START_ a _END_ sur _TOTAL_ ",
                    "search": "Filtrer les femmes enceintes"
                },
                "lengthChange": false,
                "stripeClasses": [ 'odd', 'even' ]
            });
        });
    }
</script>

<%@ include file="template/motherFollowupHeader.jsp"%>
<%--<div class="box">--%>
<%--&lt;%&ndash;<div class=""><h2><spring:message code="ptme.mother.followup.subtitle" /></h2></div>&ndash;%&gt;--%>
<%--</div>--%>
<c:if test="${mode == 'list' || empty(mode)}">

    <div class="box">
        <h3>Choix de la patiente</h3>
        <div class="line"></div>
        <form:form action="" modelAttribute="findPregnantPatientForm" id="form" method="get" >
            <form:hidden path="mode"/>
            <table cellpadding="10" cellspacing="0">
                <tr>
                    <td>Numero de gestante : </td>
                    <td><form:input path="pregnantNumber" cssClass=""/> </td>
                    <td><input type="submit" value="Attribuer Num&eacute;ro PEC"></td>
                </tr>
            </table>
        </form:form>
    </div>
    <div class="boxHeader"></div>
    <div class="box">

        <h3>Liste des pateintes &agrave; suivre ou dont le suivi est en cours sur le site</h3>

        <div class="line"></div>

        <table id="list-patient-to-follow" align="" width="100%" style="border-collapse: collapse; border: 1px solid #1aac9b" cellpadding="5" cellspacing="0">
            <thead>
            <tr style="background-color: #1aac9b; color: #ffffff;">
                <th>Num&eacute;ro de gestante</th>
                <th>Num&eacute;ro de prise en charge</th>
                <th>Num&eacute;ro de d&eacute;pistage</th>
                <th>Nom</th>
                <th>Pr&eacute;nom</th>
            <%--<th>Date de naissance</th>--%>
                <th>Age</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pregnant" items="${ pregnantPatientFollowupList }">
                <tr>
                    <td align="center">${ pregnant.pregnantNumber }</td>
                    <td align="center">
                        <c:url value="/module/ptme/motherFollowup.form" var="url">
                            <c:param name="hivCareNumber" value="${pregnant.hivCareNumber}"/>
                        </c:url>
                        <a href="${url}">${pregnant.hivCareNumber}</a>
                    </td>
                    <td>${pregnant.screeningNumber}</td>
                    <td>${pregnant.familyName}</td>
                    <td>${pregnant.givenName}</td>
                    <%--<td align="center">--%>
                        <%--<fmt:formatDate type="date" value="${ pregnant.patient.birthdate }" pattern="dd/MM/yyyy" />--%>
                    <%--</td>--%>
                    <td align="center">${ pregnant.age } ans</td>

                    <td align="center">
                        <table cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <c:url value="/module/ptme/motherFollowupPatient.form" var="url">
                                        <c:param name="pregnantPatientId" value="${pregnant.pregnantPatientId}"/>
                                    </c:url>
                                    <a href="${ url }"><img src="/openmrs/images/edit.gif" alt="Editer"></a>
                                </td>
                                <td>|</td>
                                <td>
                                    <c:url value="/module/ptme/motherFollowupPatient.form" var="urlsup">
                                        <c:param name="delId" value="${pregnant.pregnantPatientId}"/>
                                    </c:url>
                                    <a href="${ urlsup }" onclick="return confirm('Voulez-vous vraiment supprimer la visite ?');">
                                        <img src="/openmrs/images/trash.gif" alt="Supprimer">
                                    </a>
                                </td>
                            </tr>
                        </table>


                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</c:if>

<c:if test="${ mode == 'form'}">
    <div class="box">
    <div class=""><h3>Correspondance femme enceinte - patiente VIH</h3></div>
    <div class="line"></div>

        <form:form action="${pageContext.request.contextPath}/module/ptme/motherFollowupPatient.form"
                   modelAttribute="motherFollowupPatientForm" id="form" method="post">

            <form:hidden path="pregnantPatientId"/>
            <form:hidden path="patientId"/>
            <form:hidden path="screeningNumber"/>
            <form:hidden path="familyName"/>
            <form:hidden path="givenName"/>
            <form:hidden path="age"/>

            <%--<form:errors cssClass="error" path="visitDate"/>--%>
            <table cellpadding="5" cellspacing="0">

                <c:set var = "disabled" value = "${ motherFollowupPatientForm.patientId != null ? 'readonly' : '' }"/>
                <tr>
                    <td class="boldText"><spring:message code="ptme.pregnantNumber" /> <b class="required">*</b> :</td>
                    <td><form:input path="pregnantNumber" cssClass="" /></td>
                    <td><form:errors cssClass="error" path="pregnantNumber"/> </td>
                </tr>
                <tr>
                    <td class="boldText"><spring:message code="ptme.hivCareNumber" /> <b class="required">*</b> : </td>
                    <td><form:input path="hivCareNumber" readonly="${disabled}" /></td>
                    <td><form:errors cssClass="error" path="hivCareNumber"/></td>
                </tr>
                <%--<tr>--%>
                    <%--<td class="boldText"><spring:message code="ptme.screeningNumber" /> : </td>--%>
                    <%--<td><form:input path="screeningNumber" cssClass="" /></td>--%>
                    <%--<td><form:errors cssClass="error" path="screeningNumber"/></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="boldText"><spring:message code="ptme.familyName" /> : </td>--%>
                    <%--<td><form:input path="familyName" disabled="${disabled}" /></td>--%>
                    <%--<td><form:errors cssClass="error" path="familyName"/> </td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="boldText"><spring:message code="ptme.givenName" /> : </td>--%>
                    <%--<td><form:input path="givenName" disabled="${disabled}" /></td>--%>
                    <%--<td><form:errors cssClass="error" path="givenName"/></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="boldText">Age <b class="required">*</b> : </td>--%>
                    <%--<td><form:input path="age" cssClass="" size="5" disabled="${disabled}" /> ans </td>--%>
                    <%--<td><form:errors cssClass="error" path="age"/> </td>--%>
                <%--</tr>--%>
            </table>
            <div class="line"></div>
            <c:if test="${ empty motherFollowupPatientForm.pregnantPatientId }">
                <input type="submit" value="Enregistrer" name="action"/>
            </c:if>
            <c:if test="${ not empty motherFollowupPatientForm.pregnantPatientId }">
                <input type="submit" value="Modifier" name="action"/>
            </c:if>
        </form:form>
    </div>
</c:if>

<%@ include file="template/localFooter.jsp"%>