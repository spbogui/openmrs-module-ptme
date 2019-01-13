<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage PTME" otherwise="/login.htm" redirect="/module/ptme/manage.form" />

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<style>
    .patient-bloc {
        background-color: #1aac9b;
        border: 1px darkgreen solid;
        padding: 5px;
    }
    .patient-bloc tr, .patient-bloc td {
        border: 1px lightyellow solid;
    }
    .patient-given-name {
        font-size: small;
    }
    .patient-name {
        font-size: large;
    }
    .patient-number {
        font-size:larger;
    }
    
    legend {
        font-weight:bold;
        font-size: 14px;
        color: #1aac9b;
    }

    fieldset {
        margin-bottom: 15px;
        padding: 10px;
    }
</style>

<script type="application/javascript">
    if (jQuery) {

        $(document).ready(function () {

            $(".list-register-table,#list-register-table2").dataTable({
                dom: 'B<"clear">lfrtip',
                buttons: {
                    name: 'primary',
                    buttons: [ 'copy', 'excel' ]
                },
               "pageLength": 10,
                "order": [[4, "asc"]],
                "language": {
                    "zeroRecords": "Aucune femme enceinte trouv&eacute;",
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

            $(".list-register-table-child").dataTable({
                dom: 'B<"clear">lfrtip',
                buttons: {
                    name: 'primary',
                    buttons: [ 'copy', 'excel' ]
                },
                "pageLength": 10,
                "order": [[4, "asc"]],
                "language": {
                    "zeroRecords": "Aucun enfant trouv&eacute;",
                    paginate: {
                        previous: 'Pr&eacute;c&eacute;dent',
                        next:     'Suivant'
                    },
                    "info":"Affichage de _START_ a _END_ sur _TOTAL_ ",
                    "search": "Filtrer les enfants expos&eacute;"
                },
                "lengthChange": false,
                "stripeClasses": [ 'odd', 'even' ]
            });

            $(".list-register-table-child-waiting").dataTable({
                dom: 'B<"clear">lfrtip',
                buttons: {
                    name: 'primary',
                    buttons: [ 'copy', 'excel' ]
                },
                "pageLength": 10,
                "order": [[4, "desc"]],
                "language": {
                    "zeroRecords": "Aucun enfant trouv&eacute;",
                    paginate: {
                        previous: 'Pr&eacute;c&eacute;dent',
                        next:     'Suivant'
                    },
                    "info":"Affichage de _START_ a _END_ sur _TOTAL_ ",
                    "search": "Filtrer les enfants expos&eacute;"
                },
                "lengthChange": false,
                "stripeClasses": [ 'odd', 'even' ]
            });
        });
    }
</script>

<div class="box">
    <h1 class="textBold centered" style="font-size: large; padding: 15px;">
        Module de gestion des registres en
        maternit&eacute; <br> et du suivi des femmes enceintes s&eacute;ropositives et enfants expos&eacute;s
    </h1>

</div>
<div class="boxHeader"></div>
<div class="box">
    <table width="100%" cellpadding="5" cellspacing="0">
        <thead>
        <tr style="border-bottom: 1px solid #1aac9b;">
            <th width="49%" style="border-right:5px solid #1aac9b;" align="center">
                <h3 class="colored-blue centered">Rendez-vous Femmes enceintes et Enfant expos&eacute;s</h3>
            </th>
            <th align="center">
                <h3 class="colored-blue centered">Enfants expos&eacute;s attendus pour les PCR et les tests</h3>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td valign="top" style="border-right:5px solid #1aac9b;">
                <fieldset>
                    <legend>Femmes enceintes VIH + ayant un rendez-vous ce mois</legend>
                    <table class="list-register-table" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: #1aac9b; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact</th>
                            <%--<th>Date de derni&egrave;re visite</th>--%>
                            <th>Nb de visites</th>
                            <th>Date de rendez-vous</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--<c:if test="${ empty motherFollowedAppointments }"  >--%>
                        <%--<tr>--%>
                        <%--<td colspan="4" align="center">--%>
                        <%--Aucune femme &agrave; afficher--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--</c:if>--%>
                        <c:forEach var="mother" items="${ motherFollowedAppointments }">
                            <tr style="color: ${mother.passed == 1 ? '#1aac9b': (mother.passed == 2 ? 'lightcoral': 'none' )}">
                                <td>
                                    <c:url value="/module/ptme/motherFollowup.form" var="url">
                                        <c:param name="hivCareNumber" value="${mother.hivCareNumber}"/>
                                    </c:url>
                                    <a href="${ url }">${mother.hivCareNumber}</a>
                                </td>
                                <td>${mother.familyName} ${mother.givenName}</td>
                                <td>${mother.contact}</td>
                                    <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                <td align="center">${mother.numberOfVisit}</td>
                                <td align="center"><fmt:formatDate type="date" value="${mother.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </fieldset>
                <br>
                <fieldset>
                    <legend>Enfants expos&eacute;s attendus ce mois</legend>
                    <table id="list-ec-appointment-missed" class="list-register-table-child" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: #1aac9b; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact M&egrave;re</th>
                            <th>Nb de visites</th>
                            <th>Date de rendez-vous</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="child" items="${ childFollowedAppointments }">
                            <tr style="color: ${child.passed == 1 ? '#1aac9b': (child.passed == 2 ? 'lightcoral': 'none' )}">
                                <td>
                                    <c:url value="/module/ptme/childFollowup.form" var="url">
                                        <c:param name="childFollowupNumber" value="${child.childFollowupNumber}"/>
                                    </c:url>
                                    <a href="${ url }">${child.childFollowupNumber}</a>
                                </td>
                                <td>${child.familyName} ${child.givenName}</td>
                                <td>${child.motherContact}</td>
                                    <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                <td align="center">${child.numberOfVisit}</td>
                                <td align="center"><fmt:formatDate type="date" value="${child.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </fieldset>
                <br>
                <fieldset>
                    <legend>Femmes enceintes VIH + ayant rat&eacute; leur rendez-vous les mois pr&eacute;c&eacute;dents</legend>
                    <table class="list-register-table" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: firebrick; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact</th>
                            <th>Nb de visites</th>
                            <th>Derni&egrave;re visite</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mother" items="${ motherFollowedAppointmentsMissed }">
                            <tr>
                                <td>
                                    <c:url value="/module/ptme/motherFollowup.form" var="url">
                                        <c:param name="hivCareNumber" value="${mother.hivCareNumber}"/>
                                    </c:url>
                                    <a href="${ url }">${mother.hivCareNumber}</a>
                                </td>
                                <td>${mother.familyName} ${mother.givenName}</td>
                                <td>${mother.contact}</td>
                                <td align="center">${mother.numberOfVisit}</td>
                                <td align="center"><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </fieldset>
                <hr>
                <fieldset>
                    <legend>Enfants expos&eacute;s ayant rat&eacute; leur rendez-vous les mois pr&eacute;c&eacute;dents</legend>
                    <table class="list-register-table-child" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: firebrick; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact M&egrave;re</th>
                            <th>Nb visites</th>
                            <th>Derni&egrave;re visite</th>
                            <%--<th>Date de rendez-vous</th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <%--<c:if test="${ empty childFollowedAppointmentsMissed }"  >--%>
                        <%--<tr>--%>
                        <%--<td colspan="4" align="center">--%>
                        <%--Aucun enfant &agrave; afficher--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--</c:if>--%>
                        <c:forEach var="child" items="${ childFollowedAppointmentsMissed }">
                            <tr>
                                <td>
                                    <c:url value="/module/ptme/childFollowup.form" var="url">
                                        <c:param name="childFollowupNumber" value="${child.childFollowupNumber}"/>
                                    </c:url>
                                    <a href="${ url }">${child.childFollowupNumber}</a>
                                </td>
                                <td>${child.familyName} ${child.givenName}</td>
                                <td>${child.motherContact}</td>
                                <td align="center">${child.numberOfVisit}</td>
                                <td align="center"><fmt:formatDate type="date" value="${child.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                                    <%--<td><fmt:formatDate type="date" value="${mother.appointmentDate}" pattern="dd/MM/yyyy" /></td>--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </fieldset>
            </td>
            <td valign="top" style="">

                <fieldset>
                    <legend><%--Enfants expos&eacute;s attendus pour la --%>PCR 1</legend>
                    <table class="list-register-table-child" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: #1aac9b; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact M&egrave;re</th>
                            <th>Date de naissance</th>
                            <th>Attendu le</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="childPcr1" items="${ childPcr1Appointment }">
                            <tr style="color: ${childPcr1.passed == 1 ? '#1aac9b': (childPcr1.passed == 2 ? 'lightcoral': 'none' )}">
                                <td>
                                    <c:url value="/module/ptme/childFollowup.form" var="urlPcr1">
                                        <c:param name="childFollowupNumber" value="${childPcr1.childFollowupNumber}"/>
                                    </c:url>
                                    <a href="${ urlPcr1 }">${childPcr1.childFollowupNumber}</a>
                                </td>
                                <td>${childPcr1.familyName} ${childPcr1.givenName}</td>
                                <td>${childPcr1.motherContact}</td>
                                    <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                <td align="center"><fmt:formatDate type="date" value="${childPcr1.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                                <td align="center"><fmt:formatDate type="date" value="${childPcr1.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </fieldset>
                <br>
                <fieldset>
                    <legend><%--Enfants expos&eacute;s attendus pour la --%>PCR 2</legend>
                    <table class="list-register-table-child" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: #1aac9b; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact M&egrave;re</th>
                            <th>Date de naissance</th>
                            <th>Attendu le </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="childPcr3" items="${ childPcr2Appointment }">
                            <tr style="color: ${childPcr3.passed == 1 ? '#1aac9b': (childPcr3.passed == 2 ? 'lightcoral': 'none' )}">
                                <td>
                                    <c:url value="/module/ptme/childFollowup.form" var="urlPcr2">
                                        <c:param name="childFollowupNumber" value="${childPcr3.childFollowupNumber}"/>
                                    </c:url>
                                    <a href="${ urlPcr2 }">${childPcr3.childFollowupNumber}</a>
                                </td>
                                <td>${childPcr3.familyName} ${childPcr3.givenName}</td>
                                <td>${childPcr3.motherContact}</td>
                                    <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                <td align="center"><fmt:formatDate type="date" value="${childPcr3.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                                <td align="center"><fmt:formatDate type="date" value="${childPcr3.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </fieldset>
                <br>
                <fieldset>
                    <legend><%--Enfants expos&eacute;s attendus pour la --%>PCR 3</legend>
                    <table class="list-register-table-child" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: #1aac9b; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact M&egrave;re</th>
                            <th>Date de naissance</th>
                            <th>Attendu depuis le </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="childPcr3" items="${ childPcr3Appointment }">
                            <tr style="color: ${childPcr3.passed == 1 ? '#1aac9b': (childPcr3.passed == 2 ? 'lightcoral': 'none' )}">
                                <td>
                                    <c:url value="/module/ptme/childFollowup.form" var="urlPcr3">
                                        <c:param name="childFollowupNumber" value="${childPcr3.childFollowupNumber}"/>
                                    </c:url>
                                    <a href="${ urlPcr3 }">${childPcr3.childFollowupNumber}</a>
                                </td>
                                <td>${childPcr3.familyName} ${childPcr3.givenName}</td>
                                <td>${childPcr3.motherContact}</td>
                                    <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                <td align="center"><fmt:formatDate type="date" value="${childPcr3.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                                <td align="center"><fmt:formatDate type="date" value="${childPcr3.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </fieldset>
                <br>
                <fieldset>
                    <legend><%--Enfants expos&eacute;s attendus pour le --%>TEST SEROLOGIQUE ENTRE M9 ET M18</legend>
                    <table class="list-register-table-child" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: #1aac9b; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact M&egrave;re</th>
                            <th>Date de naissance</th>
                            <th>Date &agrave; 9 mois</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="childTest" items="${ childTestAppointment }">
                            <tr style="color: ${childTest.passed == 1 ? '#1aac9b': (childTest.passed == 2 ? 'lightcoral': 'none' )}">
                                <td>
                                    <c:url value="/module/ptme/childFollowup.form" var="urlTest">
                                        <c:param name="childFollowupNumber" value="${childTest.childFollowupNumber}"/>
                                    </c:url>
                                    <a href="${ urlTest }">${childTest.childFollowupNumber}</a>
                                </td>
                                <td>${childTest.familyName} ${childTest.givenName}</td>
                                <td>${childTest.motherContact}</td>
                                    <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                <td align="center"><fmt:formatDate type="date" value="${childTest.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                                <td align="center"><fmt:formatDate type="date" value="${childTest.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </fieldset>
                <br>
                <fieldset>
                    <legend><%--Enfants expos&eacute;s attendus pour le --%>TEST SEROLOGIQUE A M18</legend>
                    <table class="list-register-table-child" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: #1aac9b; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact M&egrave;re</th>
                            <th>Date de naissance</th>
                            <th>Date &agrave; 18 mois</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="childM18" items="${ childTestAppointmentM18 }">
                            <tr style="color: ${childM18.passed == 1 ? '#1aac9b': (childM18.passed == 2 ? 'lightcoral': 'none' )}">
                                <td>
                                    <c:url value="/module/ptme/childFollowup.form" var="urlTestM18">
                                        <c:param name="childFollowupNumber" value="${childM18.childFollowupNumber}"/>
                                    </c:url>
                                    <a href="${ urlTestM18 }">${childM18.childFollowupNumber}</a>
                                </td>
                                <td>${childM18.familyName} ${childM18.givenName}</td>
                                <td>${childM18.motherContact}</td>
                                    <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                <td align="center"><fmt:formatDate type="date" value="${childM18.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                                <td align="center"><fmt:formatDate type="date" value="${childM18.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </fieldset>
                <br>
                <hr>
                <br>
                <fieldset>
                    <legend><%--Enfants expos&eacute;s attendus pour le --%>RESULTATS ATTENDUS PCR</legend>
                    <table class="list-register-table-child-waiting" width="100%" cellpadding="5">
                        <thead>
                        <tr style="background-color: #1aac9b; color: #ffffff;">
                            <th>Num&eacute;ro</th>
                            <th>Nom et Pr&eacute;noms</th>
                            <th>Contact M&egrave;re</th>
                            <th>Date de pr&eacute;l&egrave;vement</th>
                            <th>Nb jours attente</th>
                            <th>Ordre PCR</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="childRes" items="${ childFollowedPcrWaiting }">
                            <tr>
                                <td>
                                    <c:url value="/module/ptme/childFollowup.form" var="urlRes">
                                        <c:param name="childFollowupNumber" value="${childRes.childFollowupNumber}"/>
                                    </c:url>
                                    <a href="${ urlRes }">${childRes.childFollowupNumber}</a>
                                </td>
                                <td>${childRes.familyName} ${childRes.givenName}</td>
                                <td>${childRes.motherContact}</td>
                                    <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                <td align="center"><fmt:formatDate type="date" value="${childRes.samplingDate}" pattern="dd/MM/yyyy" /></td>
                                <td align="center">${childRes.numDay}</td>
                                <td align="center">${childRes.pcrRank}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </fieldset>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>