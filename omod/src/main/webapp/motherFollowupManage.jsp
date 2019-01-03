<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage PTME" otherwise="/login.htm" redirect="/module/ptme/motherFollowupManage.form" />

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<script type="application/javascript">
    if (jQuery) {
        $(document).ready(function () {
            $("#list-mother-followup-table").dataTable({
                dom: 'B<"clear">lfrtip',
                buttons: {
                    name: 'primary',
                    buttons: [ 'copy', 'excel' ]
                },
                "pageLength": 20,
                "order": [[1, "desc"]],
                "language": {
                    "zeroRecords": "Aucune donn&eacute;e &agrave; afficher",
                    //"emptyTable": "Aucune donn&eacute;e",
                    paginate: {
                        previous: 'Pr&eacute;c&eacute;dent',
                        next:     'Suivant'
                    },
                    "info":"Affichage de _START_ a _END_ sur _TOTAL_ ",
                    "search": "Filtrer les suivis"
                },
                "lengthChange": false,
                "stripeClasses": [ 'odd', 'even' ]
            });
        });
    }
</script>


<%@ include file="template/motherFollowupHeader.jsp"%>


<c:if test="${mode == 'list' || empty mode}">
    <div class="box">
        <h3>Option de recherche de la patiente</h3>
        <div class="line"></div>
        <form:form action="" modelAttribute="manageMotherFollowupForm" id="form" method="get" >
            <table cellpadding="10" cellspacing="0">
                <tr>
                    <td class="boldText">Issue grossesse: </td>
                    <td>
                        <form:radiobutton path="status" value="On" label="En cours" cssClass=""/>
                        <form:radiobutton path="status" value="Off" label="A terme" cssClass=""/>
                    </td>
                    <td>|</td>
                    <td>
                        <form:radiobutton path="startOrEnd" value="startDate" label="Premiere Visite" cssClass=""/>
                        <form:radiobutton path="startOrEnd" value="endDate" label="Derniere Visite" cssClass=""/>
                    </td>
                    <td>Comprises entre</td>
                    <td class="boldText">Date de d&eacute;but : </td>
                    <td><form:input path="startDate" cssClass="datepickerPtme" size="10px"/></td>
                    <td class="boldText">Date de fin : </td>
                    <td><form:input path="endDate" cssClass="datepickerPtme" size="10px"/></td>
                    <td>|</td>
                    <td><input type="submit" value="Afficher"></td>
                </tr>
            </table>
        </form:form>
    </div>
    <div class="boxHeader"></div>
    <div class="box">
        <h3>Liste des pateintes suivis sur le site</h3>

        <div class="line"></div>

        <table id="list-mother-followup-table" style="border-collapse: collapse; border: 1px solid #1aac9b" cellpadding="5" cellspacing="0" width="100%">
            <thead>
            <tr style="background-color: #1aac9b; color: #ffffff;">
                <th class="centered">Numero de PEC</th>
                <th class="centered">Nom</th>
                <th class="centered">Prenom</th>
                <th class="centered">Date de d&eacute;but</th>
                <th class="centered">Statut ARV &agrave; l'enregistrement</th>
                <th class="centered">Statut VIH Conjoint</th>
                <th class="centered">Date d&eacute;pistage Conjoint</th>
                <th class="centered">Nombre de visite</th>
                <th class="centered">Date de derni&egrave;re visite</th>
                <c:if test="${manageMotherFollowupForm.status == 'Off'}">
                    <th class="centered">Issue de grossesse</th>
                    <th class="centered">Accouchement</th>
                </c:if>
                <th width="20px"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="followupOn" items="${ listMotherFollowupOn }">
                <tr>
                    <td>
                        <c:if test="${followupOn.pregnancyOutcome == null}">
                            <c:url value="/module/ptme/motherFollowup.form" var="url_followup">
                                <c:param name="hivCareNumber" value="${followupOn.hivCareNumber}"/>
                            </c:url>
                            <a href="${ url_followup }">${followupOn.hivCareNumber}</a>
                        </c:if>
                        <c:if test="${followupOn.pregnancyOutcome != null}">
                            ${followupOn.hivCareNumber}
                        </c:if>
                    </td>
                    <td>${followupOn.familyName}</td>
                    <td>${followupOn.givenName}</td>
                    <td><fmt:formatDate type="date" value="${followupOn.startDate}" pattern="dd/MM/yyyy" /></td>
                    <td>${followupOn.arvStatusAtRegistering == 0 ? 'Positif sans ARV' : (followupOn.arvStatusAtRegistering == 1 ? 'Positif deja sous ARV': 'Nouvellement diagnostiquee VIH Positif') }</td>
                    <td>${followupOn.spousalScreeningResult == 0 ? 'Negatif' : (followupOn.spousalScreeningResult == 1 ? 'Positif' : '')}</td>
                    <td><fmt:formatDate type="date" value="${followupOn.spousalScreeningDate}" pattern="dd/MM/yyyy" /></td>
                    <td>${followupOn.visitCount}</td>
                    <td><fmt:formatDate type="date" value="${followupOn.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                    <c:if test="${manageMotherFollowupForm.status == 'Off'}">
                        <td class="centered">
                                ${followupOn.pregnancyOutcome == 1 ? "A terme" :
                                (followupOn.pregnancyOutcome == 2 ? "Pr&#233;matur&#233;" :
                                (followupOn.pregnancyOutcome == 3 ? "Avortement / Fausse couche" :
                                (followupOn.pregnancyOutcome == 4 ? "Mort n&#233; frais" :
                                (followupOn.pregnancyOutcome == 5 ? "Mort n&#233; mac&#233;r&#233;" :  ""))))}</td>
                        <td class="centered">${followupOn.deliveryType == 1 ? "Unique" : (followupOn.deliveryType == 2 ? "G&#233;mellaire" : "")}</td>
                    </c:if>
                    <td class="centered ">
                        <table cellspacing="0" cellpadding="0" class="button-table">
                            <tr>
                                <td>
                                    <c:url value="/module/ptme/motherFollowupManage.form" var="url">
                                        <c:param name="motherFollowupId" value="${followupOn.motherFollowupId}"/>
                                    </c:url>
                                    <a href="${ url }"><img src="/openmrs/images/file.gif" alt="Voir" title="Voir la fiche"></a>
                                </td>
                                <%--<c:if test="${followupOn.pregnancyOutcome == null}">
                                    <td>|</td>
                                    <td>
                                        <c:url value="/module/ptme/motherFollowup.form" var="url">
                                            <c:param name="hivCareNumber" value="${followupOn.hivCareNumber}"/>
                                        </c:url>
                                        <a href="${ url }"><img src="/openmrs/images/edit.gif" alt="Editer"></a>
                                    </td>
                                </c:if>--%>
                                <td>|</td>
                                <td>
                                    <c:url value="/module/ptme/motherFollowup.form" var="urlsup">
                                        <c:param name="delFollowupId" value="${followupOn.motherFollowupId}"/>
                                    </c:url>
                                    <a href="${ urlsup }" onclick="return confirm('Voulez-vous vraiment supprimer la ligne ?');">
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

<c:if test="${mode == 'followup'}">
    <div class="box">
        <h3>Information sur le suivi de la grossesse </h3>
        <div class="line"></div>
        <c:if test="${editable == true}">
            <table>
                <tr>
                    <td> <b>  Cliquez sur ce bouton pour </b></td>
                    <td>
                        <button type="button"
                                onclick="document.location.href='${pageContext.request.contextPath}/module/ptme/motherFollowup.form?motherFollowupId=${motherFollowupId}'">
                            <img src="/openmrs/images/edit.gif" alt="Editer"> Modifier
                        </button>
                    </td>
                </tr>
             </table>
            <div class="line"></div>
        </c:if>
        <c:set var = "nbVisit" value = "${motherFollowupVisitsCount}"/>
        <c:set var = "constRowspan" value = "${nbVisit + 2}"/>
        <c:set var = "visitFormHeight" value = "${60}"/>
        <c:set var = "visitHeight" value = "${25}"/>
        <c:set var = "firstColHeight" value = "${410}"/>
        <c:set var="rowCount" value="${0}"/>

        <table class="table-mother-followup" align="center" style="background-color: #efefef">
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
                <th>Resultat</th>
            </tr>
            <tr>
                <th>Date <br>du <br>depistage</th>
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
                            <span class="colored-blue">${(motherFollowup.arvStatusAtRegistering == 0) ? '&ofcir;': '&cir;'}</span>
                            <br>
                            Positif <br>sans ARV <br><br><br>
                            <span class="colored-blue">${(motherFollowup.arvStatusAtRegistering == 1) ? '&ofcir;': '&cir;'}</span>
                            <br>
                            Positif deja <br>sous ARV <br><br><br>
                            <span class="colored-blue">${(motherFollowup.arvStatusAtRegistering == 2) ? '&ofcir;': '&cir;'}</span><br>
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
                                <td class="centered colored-blue boldText">${visit.continuingArv == 1 ? '&ofcir;' : '&cir;'}</td>
                                <td class="centered colored-blue boldText">${visit.continuingArv == 0 ? '&ofcir;' : '&cir;'}</td>
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
                                <td class="centered colored-blue boldText">${visit.continuingCtx == 1 ? '&ofcir;' : '&cir;'}</td>
                                <td class="centered colored-blue boldText">${visit.continuingCtx == 0 ? '&ofcir;' : '&cir;'}</td>
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
                                    <td class="centered colored-blue boldText">${motherFollowup.spousalScreeningResult == 1 ? '&ofcir;' : '&cir;'}</td>
                                    <td class="centered colored-blue boldText">${motherFollowup.spousalScreeningResult == 0 ? '&ofcir;' : '&cir;'}</td>
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
                            <span class="colored-blue">${(motherFollowup.pregnancyOutcome == 1) ? '&ofcir;': '&cir;'}</span>
                            <br>
                            A terme <br><br> <br>
                            <span class="colored-blue">${(motherFollowup.pregnancyOutcome == 2) ? '&ofcir;': '&cir;'}</span> <br>
                            Prematurit&eacute; <br><br><br>
                            <span class="colored-blue">${(motherFollowup.pregnancyOutcome == 3) ? '&ofcir;': '&cir;'}</span><br>
                            Avortement <br>/ Fausse <br>couche <br><br><br>
                            Mort-n&eacute;: <br>
                            Frais <span class="colored-blue">${(motherFollowup.pregnancyOutcome == 4) ? '&ofcir;': '&cir;'}</span><br>

                            Mac&eacute;r&eacute; <span class="colored-blue">${(motherFollowup.pregnancyOutcome == 5) ? '&ofcir;': '&cir;'}</span>
                        </td>
                        <td class="bordered-green" rowspan="${constRowspan}" align="center">
                            <span class="colored-blue">${(motherFollowup.deliveryType == 1) ? '&ofcir;': '&cir;'}</span>
                            <br>
                            Unique <br><br> <br>
                            <span class="colored-blue">${(motherFollowup.deliveryType == 2) ? '&ofcir;': '&cir;'}</span><br>
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

        </table>
    </div>
</c:if>

<%@ include file="template/localFooter.jsp"%>

