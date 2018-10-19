<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage PTME" otherwise="/login.htm" redirect="/module/ptme/motherFollowupFutures.form" />
<%@ include file="/WEB-INF/template/header.jsp"%>


<%@ include file="template/localHeader.jsp"%>

<%@ include file="template/motherFollowupHeader.jsp"%>

<div class="box">
    <h3>Information sur le suivi de la grossesse </h3>
    <div class="line"></div>
    <c:set var = "nbVisit" value = "${motherFollowupVisitsCount}"/>
    <c:set var = "constRowspan" value = "${nbVisit + 2}"/>
    <c:set var = "visitFormHeight" value = "${60}"/>
    <c:set var = "visitHeight" value = "${25}"/>
    <c:set var = "firstColHeight" value = "${410}"/>
    <c:set var="rowCount" value="${0}"/>

    <table class="table-mother-followup" align="center">
        <thead>
        <tr>
            <th rowspan="3" width="160px" height="170px"></th>
            <th rowspan="3">Date de <br>la visite</th>
            <th rowspan="3">Age <br>gestationnel <br>(en semaine <br>d'amenorrhee)</th>
            <th rowspan="3">Statut <br>ARV a <br>l'enregistrement</th>
            <th rowspan="3">Date <br>d'initiation <br>du traitement <br>ARV</th>
            <th rowspan="3">Mere <br>toujours <br>sous ARV ? <br>(Oui / Non)</th>
            <th rowspan="3">Cotrimoxazole</th>
            <th>Depistage VIH<br>du conjoint</th>
            <th rowspan="3">Issue <br>de la <br>grossesse</th>
            <th rowspan="3">Accouchement</th>
        </tr>
        <tr>
            <th>R&eacute;sultat</th>
        </tr>
        <tr>
            <th>Date <br>du <br>d&eacute;pistage</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="visit" items="${ motherFollowupVisits }">
            <tr>
                <c:if test="${rowCount == 0}">
                    <td class="bordered-green" rowspan="${constRowspan}"
                        height="${(visitFormHeight + visitHeight * nbVisit ) < firstColHeight ? firstColHeight : firstColHeight + nbVisit * visitHeight }px" >
                        Numero d'identification <br>
                        unique :<br>
                        <b class="colored-blue">${patientInfo.identifier}</b>
                        <br>
                        <br>
                        Nom: <br>
                        <b class="colored-blue">${patientInfo.familyName} ${patientInfo.givenName}</b>
                        <br>
                        <br>
                        Date de naissance :<br>
                        <b class="colored-blue"><fmt:formatDate type="date" value="${patientInfo.birthDate}" pattern="dd/MM/yyyy" /></b>
                        <br>
                        <br>
                        Age (ans):<br>
                        <b class="colored-blue">${patientInfo.age}</b>
                        <br>
                        <br>

                        Date Probable <br>
                        d'Accouchement : <br>
                        <b class="colored-blue">
                            <fmt:formatDate type="date" value="${patientInfo.outcomeProbableDate}" pattern="dd/MM/yyyy" />
                        </b>
                        <br>
                        <br>
                        Type de VIH :
                        <br>
                        <br>
                        <b class="colored-blue">${patientInfo.hivType}</b>

                        <br>
                        <br>
                        Contacts Telephonique :
                        <br>
                        Tel: <b class="colored-blue">${patientInfo.tel}</b>
                        <br>
                        Cel: <b class="colored-blue">${patientInfo.cel}</b><br>

                    </td>

                </c:if>
                <td class="bordered-green colored-blue boldText" align="center" height="${visitHeight}px">
                    <fmt:formatDate type="date" value="${ visit.visitDate }" pattern="dd/MM/yyyy" />
                </td>
                <td class="bordered-green colored-blue boldText" align="center">
                        ${ visit.gestationalAge }
                </td>
                <c:if test="${rowCount == 0}">
                    <td class="bordered-green" rowspan="${constRowspan}" align="center">
                        <span class="colored-blue">[${(motherFollowup.arvStatusAtRegistering == 0) ? 'X': '&nbsp;'}]</span>
                        <br>
                        Positif <br>sans ARV <br><br><br>
                        <span class="colored-blue">[${(motherFollowup.arvStatusAtRegistering == 1) ? 'X': '&nbsp;'}]</span>
                        <br>
                        Positif deja <br>sous ARV <br><br><br>
                        <span class="colored-blue">[${(motherFollowup.arvStatusAtRegistering == 2) ? 'X': '&nbsp;'}]</span><br>
                        Nouvellement <br>
                        diagnostiquee <br>
                        VIH Positif <br>

                    </td>
                    <td class="bordered-green" rowspan="${constRowspan}" align="center">
                        <b class="colored-blue">
                            <fmt:formatDate type="date" value="${patientInfo.hivCareBeginning}" pattern="dd/MM/yyyy" />
                        </b>
                    </td>
                </c:if>
                <td class="bordered-green" align="center">
                    <table align="center" cellspacing="0" cellpadding="5">
                        <tr>
                            <td class="center colored-blue boldText">[${visit.continuingArv == 1 ? 'X' : '&nbsp;'}]</td>
                            <td class="center colored-blue boldText">[${visit.continuingArv == 0 ? 'X' : '&nbsp;'}]</td>
                        </tr>
                        <tr>
                            <td align="center">Oui</td>
                            <td align="center">Non</td>
                        </tr>
                    </table>
                </td>
                <td class="bordered-green" align="center">
                    <table align="center" cellspacing="0" cellpadding="5">
                        <tr>
                            <td class="center colored-blue boldText">[${visit.continuingCtx == 1 ? 'X' : '&nbsp;'}]</td>
                            <td class="center colored-blue boldText">[${visit.continuingCtx == 0 ? 'X' : '&nbsp;'}]</td>
                        </tr>
                        <tr>
                            <td align="center">Oui</td>
                            <td align="center">Non</td>
                        </tr>
                    </table>
                </td>
                <c:if test="${rowCount == 0}">
                    <td class="bordered-green" rowspan="${constRowspan}" align="center">
                        <table align="center" cellspacing="0" cellpadding="5">
                            <tr>
                                <td class="centered colored-blue boldText">[${motherFollowup.spousalScreeningResult == 1 ? 'X' : '&nbsp;'}]</td>
                                <td class="centered colored-blue boldText">[${motherFollowup.spousalScreeningResult == 0 ? 'X' : '&nbsp;'}]</td>
                            </tr>
                            <tr>
                                <td align="center">Positif</td>
                                <td align="center">Negatif</td>
                            </tr>
                        </table>
                        <br><br>
                        <br><br>
                        <hr>
                        <br><br>
                        <br><br>
                        <b class="colored-blue">
                            <fmt:formatDate type="date" value="${motherFollowup.spousalScreeningDate}" pattern="dd/MM/yyyy" />
                        </b>
                    </td>
                    <td class="bordered-green" rowspan="${constRowspan}" align="center">
                        <span class="colored-blue">[${(motherFollowup.pregnancyOutcome == 1) ? 'X': '&nbsp;'}]</span>
                        <br>
                        A terme <br><br> <br>
                        <span class="colored-blue">[${(motherFollowup.pregnancyOutcome == 2) ? 'X': '&nbsp;'}]</span> <br>
                        Prematurit&eacute; <br><br><br>
                        <span class="colored-blue">[${(motherFollowup.pregnancyOutcome == 3) ? 'X': '&nbsp;'}]</span><br>
                        Avortement <br>/ Fausse <br>couche <br><br><br>
                        Mort-n&eacute;: <br>
                        Frais <span class="colored-blue">[${(motherFollowup.pregnancyOutcome == 4) ? 'X': '&nbsp;'}]</span><br>

                        Mac&eacute;r&eacute; <span class="colored-blue">[${(motherFollowup.pregnancyOutcome == 5) ? 'X': '&nbsp;'}]</span>

                    </td>
                    <td class="bordered-green" rowspan="${constRowspan}" align="center">
                        <span class="colored-blue">[${(motherFollowup.deliveryType == 1) ? 'X': '&nbsp;'}]</span>
                        <br>
                        Unique <br><br> <br>
                        <span class="colored-blue">[${(motherFollowup.deliveryType == 2) ? 'X': '&nbsp;'}]</span><br>
                        G&eacute;mellaire <br><br><br>
                    </td>
                </c:if>
            </tr>
            <c:set var="rowCount" value="${rowCount+1}"/>
        </c:forEach>

        <c:if test="${(visitFormHeight + visitHeight * nbVisit ) < firstColHeight}">
            <tr>
                <td class="bordered-green"></td>
                <td class="bordered-green"></td>
                <td class="bordered-green"></td>
                <td class="bordered-green"></td>
            </tr>
        </c:if>

        </tbody>
        <c:if test="${editable == true}">
            <tfoot>
            <tr class="bordered-green">
                <td colspan="3"  class="bordered-green"> <b>  vous pouvez continuer le suivi  de ce patient ici  :</b></td>

                <td>
                    <c:url value="/module/ptme/motherFollowup.form" var="url">
                        <c:param name="motherFollowupId" value="${motherFollowupId}"/>
                    </c:url>
                    <a href="${ url }"><img src="/openmrs/images/edit.gif" alt="Editer"></a>
                </td>
            </tr>
            </tfoot>
        </c:if>
    </table>
</div>

<%@ include file="template/localFooter.jsp"%>
