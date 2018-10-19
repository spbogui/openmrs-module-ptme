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
</style>

<script type="application/javascript">
    if (jQuery) {

//        $j(document).ready(function () {
//            $j.fn.dataTable.moment( 'HH:mm MMM D, YY' );
//            $j.fn.dataTable.moment( 'dddd, MMMM Do, YYYY' );
//            $j.fn.dataTable.moment( 'dd/MM/YYYY' );
//
//            $j(".list-register-table,#list-register-table2").dataTable({
//                "bPaginate": true,
//                // "order": [[1, "desc"]],
//                "iDisplayLength": 5,
//                "bLengthChange": false,
//                "bFilter": true,
////                "bSort": true,
//                "bInfo": true,
//                "bJQueryUI": true,
//                "oLanguage": {
//                    "oPaginate": {
//                        "sPrevious": "Pr&eacute;c&eacute;dent",
//                        "sNext": "Suivant"
//                    },
//                    "sZeroRecords": "Aucune patiente à afficher",
//                    "sInfo": "Affichage de _START_ - _END_ sur _TOTAL_ ",
//                    "sSearch": "Chercher"
//                },
//                // "aaSorting": [[1, "desc"]]
//            });
//
//            $j(".list-register-table-child").dataTable({
//                "bPaginate": true,
//                // "order": [[1, "desc"]],
//                "iDisplayLength": 5,
//                "bLengthChange": false,
//                "bFilter": true,
////                "bSort": true,
//                "bInfo": true,
//                "bJQueryUI": true,
//                "oLanguage": {
//                    "oPaginate": {
//                        "sPrevious": "Pr&eacute;c&eacute;dent",
//                        "sNext": "Suivant"
//                    },
//                    "sZeroRecords": "Aucun enfant à afficher",
//                    "sInfo": "Affichage de _START_ - _END_ sur _TOTAL_ ",
//                    "sSearch": "Chercher"
//                },
//                // "aaSorting": [[1, "desc"]]
//            });
//        });
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
                <h3 class="colored-blue centered">Femmes enceintes</h3>
            </th>
            <th align="center">
                <h3 class="colored-blue centered">Enfants expos&eacute;s</h3>
            </th>
        </tr>
        </thead>
        <tbody>
        <%--<tr>--%>
            <%--<td colspan="2">--%>
                <%--<div class="line"></div>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td valign="top" style="border-right:5px solid #1aac9b;">
                <table cellspacing="5" cellpadding="5" width="100%">
                    <tr>
                        <th>Femmes enceintes VIH + ayant un rendez-vous ce mois</th>
                    </tr>
                    <tr>
                        <td>
                            <fieldset>
                                <table class="list-register-table" width="100%" cellpadding="5">
                                    <thead>
                                    <tr style="background-color: #1aac9b; color: #ffffff;">
                                        <th>Num&eacute;ro</th>
                                        <th>Nom et Pr&eacute;noms</th>
                                        <%--<th>Date de derni&egrave;re visite</th>--%>
                                        <th>Nb de visites</th>
                                        <th>Date de rendez-vous</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${ empty motherFollowedAppointments }"  >
                                        <tr>
                                            <td colspan="4" align="center">
                                                Aucune femme &agrave; afficher
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="mother" items="${ motherFollowedAppointments }">
                                        <tr style="color: ${mother.passed == 1 ? '#1aac9b': (mother.passed == 2 ? 'lightcoral': 'none' )}">
                                            <td>
                                                <c:url value="/module/ptme/motherFollowup.form" var="url">
                                                    <c:param name="hivCareNumber" value="${mother.hivCareNumber}"/>
                                                </c:url>
                                                <a href="${ url }">${mother.hivCareNumber}</a>
                                            </td>
                                            <td>${mother.familyName} ${mother.givenName}</td>
                                                <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                            <td align="center">${mother.numberOfVisit}</td>
                                            <td align="center"><fmt:formatDate type="date" value="${mother.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                    <tr>
                        <th>Femmes enceintes VIH + ayant rat&eacute; leur rendez-vous</th>
                    </tr>
                    <tr>
                        <td>
                            <fieldset>
                                <table class="list-register-table" width="100%" cellpadding="5">
                                    <thead>
                                    <tr style="background-color: firebrick; color: #ffffff;">
                                        <th>Num&eacute;ro</th>
                                        <th>Nom et Pr&eacute;noms</th>
                                        <th>Nb de visites</th>
                                        <th>Derni&egrave;re visite</th>
                                        <%--<th>Date de rendez-vous</th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${ empty motherFollowedAppointmentsMissed }"  >
                                        <tr>
                                            <td colspan="4" align="center">
                                                Aucune femme &agrave; afficher
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="mother" items="${ motherFollowedAppointmentsMissed }">
                                        <tr>
                                            <td>
                                                <c:url value="/module/ptme/motherFollowup.form" var="url">
                                                    <c:param name="hivCareNumber" value="${mother.hivCareNumber}"/>
                                                </c:url>
                                                <a href="${ url }">${mother.hivCareNumber}</a>
                                            </td>
                                            <td>${mother.familyName} ${mother.givenName}</td>
                                            <td align="center">${mother.numberOfVisit}</td>
                                            <td align="center"><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                                        <%--<td><fmt:formatDate type="date" value="${mother.appointmentDate}" pattern="dd/MM/yyyy" /></td>--%>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>

                <%--<table>
                    <tr>
                        <td>
                            <table class="patient-bloc">
                                <tr>
                                    <td rowspan="2">
                                        <span class="patient-name">KONAN</span> <br>
                                        <span class="patient-given-name">AYA CAROLINE</span>
                                    </td>
                                    <td colspan="2" align="center"><small>Visite</small></td>
                                    <td rowspan="2"><small>Attendue le</small></td>
                                    <td rowspan="3">Saisir la fiche</td>
                                </tr>
                                <tr>
                                    <td>Nombre</td>
                                    <td>Derni&egrave;re</td>
                                </tr>
                                <tr>
                                    <td class="patient-number">1234/01/18/00001</td>
                                    <td class="visit">5</td>
                                    <td class="last-vist">07/07/2018</td>
                                    <td class="appointment">07/08/2018</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>--%>

            </td>
            <td valign="top" style="">
                <table cellspacing="5" cellpadding="5" width="100%">
                    <tr>
                        <th>Enfants expos&eacute;s ayant un rendez-vous ce mois</th>
                    </tr>
                    <tr>
                        <td>
                            <fieldset>
                                <table id="list-ec-appointment-missed" class="list-register-table-child" width="100%" cellpadding="5">
                                    <thead>
                                    <tr style="background-color: #1aac9b; color: #ffffff;">
                                        <th>Num&eacute;ro</th>
                                        <th>Nom et Pr&eacute;noms</th>
                                        <%--<th>Date de derni&egrave;re visite</th>--%>
                                        <th>Nb de visites</th>
                                        <th>Date de rendez-vous</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${ empty childFollowedAppointments }"  >
                                        <tr>
                                            <td colspan="4" align="center">
                                                Aucun enfant &agrave; afficher
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="child" items="${ childFollowedAppointments }">
                                        <tr style="color: ${child.passed == 1 ? '#1aac9b': (child.passed == 2 ? 'lightcoral': 'none' )}">
                                            <td>
                                                <c:url value="/module/ptme/childFollowup.form" var="url">
                                                    <c:param name="childFollowupNumber" value="${child.childFollowupNumber}"/>
                                                </c:url>
                                                <a href="${ url }">${child.childFollowupNumber}</a>
                                            </td>
                                            <td>${child.familyName} ${child.givenName}</td>
                                                <%--<td><fmt:formatDate type="date" value="${mother.lastVisitDate}" pattern="dd/MM/yyyy" /></td>--%>
                                            <td align="center">${child.numberOfVisit}</td>
                                            <td align="center"><fmt:formatDate type="date" value="${child.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                    <tr>
                        <th>Enfants expos&eacute;s ayant rat&eacute; leur rendez-vous</th>
                    </tr>
                    <tr>
                        <td>
                            <fieldset>
                                <table class="list-register-table-child" width="100%" cellpadding="5">
                                    <thead>
                                    <tr style="background-color: firebrick; color: #ffffff;">
                                        <th>Num&eacute;ro</th>
                                        <th>Nom et Pr&eacute;noms</th>
                                        <th>Nb visites</th>
                                        <th>Derni&egrave;re visite</th>
                                        <%--<th>Date de rendez-vous</th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${ empty childFollowedAppointmentsMissed }"  >
                                        <tr>
                                            <td colspan="4" align="center">
                                                Aucun enfant &agrave; afficher
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="child" items="${ childFollowedAppointmentsMissed }">
                                        <tr>
                                            <td>
                                                <c:url value="/module/ptme/childFollowup.form" var="url">
                                                    <c:param name="childFollowupNumber" value="${child.childFollowupNumber}"/>
                                                </c:url>
                                                <a href="${ url }">${child.childFollowupNumber}</a>
                                            </td>
                                            <td>${child.familyName} ${child.givenName}</td>
                                            <td align="center">${child.numberOfVisit}</td>
                                            <td align="center"><fmt:formatDate type="date" value="${child.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                                                <%--<td><fmt:formatDate type="date" value="${mother.appointmentDate}" pattern="dd/MM/yyyy" /></td>--%>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>

                <%--<table>
                    <tr>
                        <td>
                            <table class="patient-bloc">
                                <tr>
                                    <td rowspan="2">
                                        <span class="patient-name">KONAN</span> <br>
                                        <span class="patient-given-name">AYA CAROLINE</span>
                                    </td>
                                    <td colspan="2" align="center"><small>Visite</small></td>
                                    <td rowspan="2"><small>Attendue le</small></td>
                                    <td rowspan="3">Saisir la fiche</td>
                                </tr>
                                <tr>
                                    <td>Nombre</td>
                                    <td>Derni&egrave;re</td>
                                </tr>
                                <tr>
                                    <td class="patient-number">1234/01/18/00001</td>
                                    <td class="visit">5</td>
                                    <td class="last-vist">07/07/2018</td>
                                    <td class="appointment">07/08/2018</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>--%>

            </td>
        </tr>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>