<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage PTME" otherwise="/login.htm" redirect="/module/ptme/childFollowupManage.form" />
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<%@ include file="template/chlidFollowupHeader.jsp"%>

<script type="application/javascript">
    if (jQuery) {
        $(document).ready(function () {
            $("#list-child-followup").dataTable({
                dom: 'B<"clear">lfrtip',
                buttons: {
                    name: 'primary',
                    buttons: [ 'copy', 'csv', 'excel' ]
                },
                "pageLength": 20,
                "order": [[1, "asc"]],
                "language": {
                    "zeroRecords": "Aucune donn&eacutee trouv&eacute;",
                    //"emptyTable": "Aucune donn&eacute;e",
                    paginate: {
                        previous: 'Pr&eacute;c&eacute;dent',
                        next:     'Suivant'
                    },
                    "info":"Affichage de _START_ a _END_ sur _TOTAL_ ",
                    "search": "Filtrer les visites"
                },
                "lengthChange": false,
                "stripeClasses": [ 'odd', 'even' ]
            });
        });
    }
</script>
<c:if test="${empty mode}">
<div class="box">
    <h3><b>Historique des fiches de suivi</b></h3>

    <div class="line"></div>
    <h4><b>Formulaire de choix</b></h4>
    <div class="line"></div>
    <form:form  modelAttribute="manageChildFollowupForm" method="get" action="" id="form">
        <table cellpadding="5" cellspacing="0">
            <tr>
                <td>Statut du suivi : </td>
                <td>
                    <form:radiobutton path="status" value="On" label="En cours" />
                    <form:radiobutton path="status" value="Off" label="Termine" />
                </td>
                <td>|</td>
                <td>P&eacute;riode du r&eacute;sultat final :</td>
                <td>
                    D&eacute;but : <form:input path="followupResultStartDate" cssClass="datepickerPtme" size="10px" />
                    Fin : <form:input path="followupResultEndDate" cssClass="datepickerPtme" size="10px" />
                </td>
                <td><input type="submit" value="Valider"></td>
                <td><input type="button" value="Vider" onclick="document.location.href='${pageContext.request.contextPath}/module/ptme/childFollowupManage.form'"></td>
            </tr>
        </table>
    </form:form>
    <div class="line"></div>
    <table style="border-collapse: collapse; border: 1px solid #1aac9b;"
           id="list-child-followup" width="100%" align="center" cellspacing="0" cellpadding="5">
        <thead>
        <tr style="background-color: #1aac9b; color: white">
            <th align="center">Numero d'identification</th>
            <th align="center">Nom</th>
            <th align="center">Prenom</th>
            <th align="center">Sexe</th>
            <th align="center">Date de naissance</th>
            <th align="center">Derni&egrave;re PCR</th>
            <th align="center">Date Derni&egrave;re PCR</th>
            <th align="center">R&eacute;sultat Derni&egrave;re PCR</th>
            <th align="center">Date initiation CTX</th>
            <th align="center">Date initiation INH</th>
            <th align="center">Nombre de visite</th>
            <th align="center">Date de derni&egrave;re visite</th>
            <c:if test="${status == 'Off'}">
                <th align="center">R&eacute;sultat final</th>
                <th align="center">Date du r&eacute;sultat final</th>
            </c:if>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="followup" items="${ childFollowupList }">
            <tr>
                <td>
                    <c:url value="/module/ptme/childFollowup.form" var="url">
                        <c:param name="childFollowupNumber" value="${followup.childFollowupNumber}"/>
                    </c:url>
                    <a href="${ url }">${followup.childFollowupNumber}</a>
                </td>
                <td>${followup.familyName}</td>
                <td>${followup.givenName}</td>
                <td>${followup.gender}</td>
                <td><fmt:formatDate type="date" value="${followup.birthDate}" pattern="dd/MM/yyyy" /></td>
                <td>${followup.lastPcr}</td>
                <td><fmt:formatDate type="date" value="${followup.lastPcrDate}" pattern="dd/MM/yyyy" /></td>
                <td>${followup.lastPcrResult == 0 ? 'Negatif' : (followup.lastPcrResult == 1 ? 'Positif': '') }</td>
                <td><fmt:formatDate type="date" value="${followup.ctxInitiationDate}" pattern="dd/MM/yyyy" /></td>
                <td><fmt:formatDate type="date" value="${followup.inhInitiationDate}" pattern="dd/MM/yyyy" /></td>
                <td>${followup.visitCount}</td>
                <td><fmt:formatDate type="date" value="${followup.lastVisitDate}" pattern="dd/MM/yyyy" /></td>
                <c:if test="${status == 'Off'}">
                    <td>
                            ${followup.result == 0 ? 'Negatif' :
                                    (followup.result == 1 ? 'Perdue de vue' :
                                            (followup.result == 2 ? 'Deces' :
                                                    (followup.result == 3 ? 'Positif' :
                                                            (followup.result == 4 ? 'Trensfert' :
                                                                    (followup.result == 5 ? 'Reference' : '')))))}
                    </td>
                    <td><fmt:formatDate type="date" value="${followup.resultDate}" pattern="dd/MM/yyyy" /></td>
                </c:if>
                <td>
                    <table class="button-table">
                        <tr>
                            <%--<td>
                                <c:url value="/module/ptme/childFollowup.form" var="url">
                                    <c:param name="childFollowupNumber" value="${followup.childFollowupNumber}"/>
                                </c:url>
                                <a href="${ url }"><img src="/openmrs/images/edit.gif" alt="Editer" title="Editer"></a>
                            </td>
                            <td>|</td>--%>
                            <td>
                                <c:url value="/module/ptme/childFollowupManage.form" var="v_url">
                                    <c:param name="childFollowupNumber" value="${followup.childFollowupNumber}"/>
                                </c:url>
                                <a href="${ v_url }"><img src="/openmrs/images/file.gif" alt="Voir" title="Voir"></a>
                            </td>
                            <td>|</td>
                            <td>
                                <c:url value="/module/ptme/childFollowupManage.form" var="s_url">
                                    <c:param name="delFollowupId" value="${followup.childId}"/>
                                </c:url>
                                <a href="${ s_url }" onclick="return confirm('Voulez-vous vraiment supprimer la ligne ?');">
                                    <img src="/openmrs/images/trash.gif" alt="Supprimer" title="Supprimer">
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
    <h3><b>Saisie de la fiche de suivi de l'enfant expos&eacute;</b></h3>
    <c:if test="${ not empty patientInfo}">
    <div class="line"></div>
    <table cellspacing="0" cellpadding="5">
        <tr>
            <td class="boldText">Information sur la  m&egrave;re <span style="font-size: 15px">&blacktriangleright;</span> </td>
            <td>Num&eacute;ro identifiant : </td>
            <td class="colored-blue">${patientInfo.identifier}</td>
            <td>|</td>
            <td>Nom : </td>
            <td class="colored-blue">${patientInfo.familyName} ${patientInfo.givenName}</td>
            <td>|</td>
            <td>Age : </td>
            <td class="colored-blue">${patientInfo.age} ans</td>
            <td>|</td>
            <td>Type de VIH : </td>
            <td class="colored-blue">${patientInfo.hivType}</td>
        </tr>
    </table>
    </c:if>
    <c:if test="${ empty patientInfo }">
        <table cellspacing="0" cellpadding="5">
            <tr>
                <td class="boldText">Information sur la m&egrave;re <span style="font-size: 15px">&blacktriangleright;</span> </td>
                <td class="boldText">La m&egrave;re de cet enfant n'est pas prise en charge sur ce site</td>
                <td class="boldText">Pensez &agrave; le mettre &agrave; jour en modifiant les informations sur l'enfant !</td>

            </tr>
        </table>
    </c:if>
    <div class="line"></div>
    <table cellspacing="0" cellpadding="5">
        <tr>
            <td class="boldText">Information sur l'enfant <span style="font-size: 15px">&blacktriangleright;</span> </td>
            <td>Num&eacute;ro identifiant : </td>
            <td class="colored-blue">${currentChild.childFollowupNumber}</td>
            <td>|</td>
            <td>Nom : </td>
            <td class="colored-blue">${currentChild.familyName} ${currentChild.givenName}</td>
            <td>|</td>
            <td>Date de naissance : </td>
            <td class="colored-blue"><fmt:formatDate type="date" value="${currentChild.birthDate}" pattern="dd/MM/yyyy" /></td>
            <td>|</td>
            <td>Sexe : </td>
            <td class="colored-blue">${currentChild.gender}</td>
        </tr>
    </table>
    <div class="line"></div>
    <%--<div class="box">--%>
    <c:set var = "nbVisit" value = "${childFollowupVisitCount}"/>
    <c:set var = "constRowspan" value = "${nbVisit + 2}"/>
    <c:set var = "visitFormHeight" value = "${50}"/>
    <c:set var = "visitHeight" value = "${35}"/>
    <c:set var = "firstColHeight" value = "${310}"/>

        <%--<form:errors cssClass="error" path="visitDate"/>--%>

        <table class="table-child-followup" align="center" cellspacing="0" cellpadding="5" style="background-color: #efefef">
            <thead>
            <tr>
                <th rowspan="5" height="170px">Methode de <br>contraception <br>moderne <br>(Oui / Non)</th>
                <th rowspan="2">Prophylaxie ARV <br>Remise a l'enfant? <br>(Oui / Non)</th>
                <th rowspan="5">Date de <br>la visite</th>
                <th rowspan="5">Age <br>au moment <br>de la viste <br>(en jour ou <br>en semaine <br>ou en mois)</th>
                <th rowspan="2">Type <br>d'alimentation</th>
                    <%--<th rowspan="1" colspan="3">DBS-PCR</th>--%>
                <th rowspan="1" colspan="1">DBS-PCR</th>
                <th rowspan="2" colspan="2">Cotrimoxazole (CTX)</th>
                <th rowspan="2" colspan="2">Prophylaxie <br> a l'isoniazide (INH)</th>
                <th rowspan="2" colspan="1">Serologie ARV</th>
                <th rowspan="5">Resultat final <br>du suivi</th>
            </tr>
            <tr>
                <th> Date <br> Age <br>R&eacute;sultat</th>
                    <%--<th>2e PCR</th>--%>
                    <%--<th>3e PCR</th>--%>
            </tr>
            <tr>
                <th rowspan="3">Date<br>de<br>remise</th>
                <th rowspan="3">
                    1=Allaitement <br>exclusif<br>
                    2=Alimentation <br>de remplacement <br>exclusif<br>
                    3=Alimentation de<br> complement<br>
                    4=Autres
                </th>
                <th>1er PCR</th>
                    <%--<th>Date du<br>prelevement</th>--%>
                    <%--<th>Date du<br>prelevement</th>--%>
                    <%--<th rowspan="2" colspan="2">Initiation CTX</th>--%>
                <th rowspan="1" colspan="1">Initiation CTX</th>
                <th rowspan="3" colspan="1">Enfant <br>sous CTX <br>(Oui / Non)</th>
                <th rowspan="1" colspan="1">Initiation INH</th>
                    <%--<th rowspan="2" colspan="2">Initiation INH</th>--%>
                <th rowspan="3" colspan="1">Enfant <br>sous INH <br>(Oui / Non)</th>
                <th>Date</th>
                    <%--<th>Date</th>--%>
            </tr>
            <tr>
                <th>2e PCR</th>
                    <%--<th>Age au <br>moment du <br>prelevement</th>--%>
                    <%--<th>Age au <br>moment du <br>prelevement</th>--%>
                <th>Date</th>
                <th>Date</th>
                    <%--<th>Age</th>--%>
                <th>Age</th>
            </tr>
            <tr>
                <th>3e PCR</th>
                    <%--<th>Resultat</th>--%>
                    <%--<th>Resultat</th>--%>
                    <%--<th>Age</th>--%>
                <th>Age</th>
                <th>Age</th>
                <th>Resultat</th>
            </tr>
            </thead>
            <tbody>

            <c:set var = "countLine" value = "${0}"/>
            <c:forEach var="visit" items="${ childFollowupVisits }">
                <tr>
                    <td class="bordered-green" align="center" height="${visitHeight}px">
                        <table align="center" cellspacing="0" cellpadding="5">
                            <tr>
                                <td align="center" class="centered colored-blue boldText">${ visit.modernContraceptiveMethod ? '&ofcir;' : '&cir;' }</td>
                                <td align="center" class="centered colored-blue boldText">${ !visit.modernContraceptiveMethod ? '&ofcir;' : '&cir;' }</td>
                            </tr>
                            <tr>
                                <td align="center">Oui</td>
                                <td align="center">Non</td>
                            </tr>
                        </table>
                    </td>
                    <c:if test="${countLine == 0}">
                        <td class="bordered-green" rowspan="${constRowspan}" height="${firstColHeight}px" align="center">
                            <table align="center" cellspacing="0" cellpadding="5">
                                <tr>
                                    <td class="centered colored-blue boldText">${childFollowup.arvProphylaxisGiven == 1 ? '&ofcir;' : '&cir;'}</td>
                                    <td class="centered colored-blue boldText">${childFollowup.arvProphylaxisGiven == 0 ? '&ofcir;' : '&cir;'}</td>
                                </tr>
                                <tr>
                                    <td align="center">Oui</td>
                                    <td align="center">Non</td>
                                </tr>
                            </table>
                            <br><br><br>
                            <span class="center colored-blue boldText"><fmt:formatDate type="date" value="${ childFollowup.arvProphylaxisGivenDate }" pattern="dd/MM/yyyy" /></span>
                        </td>
                    </c:if>
                    <td class="bordered-green colored-blue boldText" align="center">
                        <fmt:formatDate type="date" value="${ visit.visitDate }" pattern="dd/MM/yyyy" />
                    </td>
                    <td class="bordered-green" align="center">
                        <table cellpadding="2" cellspacing="3">
                            <tr>
                                <td class="centered colored-blue boldText bordered-green">${ visit.ageInDay }</td>
                                <td class="centered colored-blue boldText bordered-green">${ visit.ageInWeek }</td>
                                <td class="centered colored-blue boldText bordered-green">${ visit.ageInMonth }</td>
                            </tr>
                            <tr>
                                <td width="20" align="center">J</td>
                                <td width="20" align="center">S</td>
                                <td width="20" align="center">M</td>
                            </tr>
                        </table>
                    </td>
                    <td class="bordered-green" align="center">
                        <table align="center" cellspacing="0" cellpadding="5">
                            <tr>
                                <td class="centered colored-blue boldText">${visit.eatingType == 1 ? '&ofcir;' : '&cir;'}</td>
                                <td class="centered colored-blue boldText">${visit.eatingType == 2 ? '&ofcir;' : '&cir;'}</td>
                                <td class="centered colored-blue boldText">${visit.eatingType == 3 ? '&ofcir;' : '&cir;'}</td>
                                <td class="centered colored-blue boldText">${visit.eatingType == 4 ? '&ofcir;' : '&cir;'}</td>
                            </tr>
                            <tr>
                                <td align="center">1</td>
                                <td align="center">2</td>
                                <td align="center">3</td>
                                <td align="center">4</td>
                            </tr>
                        </table>
                    </td>
                    <c:if test="${countLine == 0}">
                        <td class="bordered-green" rowspan="${constRowspan}" align="center"
                            style="padding: 0;">

                            <table cellpadding="5" cellspacing="0" align="center" style="padding: 0; height: 100%" width="100%">
                                <tr>
                                    <td height="100%" style="border-bottom: 1px #1aac9b solid" align="center">
                                        <span class="center colored-blue boldText">
                                            <fmt:formatDate type="date" value="${ childFollowup.pcr1SamplingDate }" pattern="dd/MM/yyyy" />
                                        </span><br><br>
                                        <table align="center" cellspacing="0" cellpadding="5">
                                            <tr>
                                                <td class="colored-blue centered">${childFollowup.ageInWeekOnPcr1Sampling}</td>
                                                <td align="center">S</td>
                                                <td class="colored-blue centered">${childFollowup.ageInMonthOnPcr1Sampling}</td>
                                                <td align="center">M</td>
                                            </tr>
                                        </table>
                                        <table align="center" cellspacing="0" cellpadding="5">
                                            <tr>
                                                <td class="centered colored-blue boldText">${childFollowup.pcr1Result == 1 ? '&ofcir;' : '&cir;'}</td>
                                                <td class="centered colored-blue boldText">${childFollowup.pcr1Result == 0 ? '&ofcir;' : '&cir;'}</td>
                                            </tr>
                                            <tr>
                                                <td align="center">Positif</td>
                                                <td align="center">Negatif</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="100%" style="border-bottom: 1px #1aac9b solid" align="center">
                                        <span class="center colored-blue boldText">
                                            <fmt:formatDate type="date" value="${ childFollowup.pcr2SamplingDate }" pattern="dd/MM/yyyy" />
                                        </span><br><br>
                                        <table align="center" cellspacing="0" cellpadding="5">
                                            <tr>
                                                <td class="centered colored-blue boldText">${childFollowup.ageInWeekOnPcr2Sampling}</td>
                                                <td align="center">S</td>
                                                <td class="centered colored-blue boldText">${childFollowup.ageInMonthOnPcr2Sampling}</td>
                                                <td align="center">M</td>
                                            </tr>
                                        </table>
                                        <table align="center" cellspacing="0" cellpadding="5">
                                            <tr>
                                                <td class="centered colored-blue boldText">${childFollowup.pcr2Result == 1 ? '&ofcir;' : '&cir;'}</td>
                                                <td class="centered colored-blue boldText">${childFollowup.pcr2Result == 0 ? '&ofcir;' : '&cir;'}</td>
                                            </tr>
                                            <tr>
                                                <td align="center">Positif</td>
                                                <td align="center">Negatif</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="100%" style="border-bottom: 1px #1aac9b solid" align="center">
                                        <span class="center colored-blue boldText"><fmt:formatDate type="date" value="${ childFollowup.pcr3SamplingDate }" pattern="dd/MM/yyyy" /></span><br><br>
                                        <table align="center" cellspacing="0" cellpadding="5">
                                            <tr>
                                                <td class="centered colored-blue boldText">${childFollowup.ageInWeekOnPcr3Sampling}</td>
                                                <td align="center">S</td>
                                                <td class="centered colored-blue boldText">${childFollowup.ageInMonthOnPcr3Sampling}</td>
                                                <td align="center">M</td>
                                            </tr>
                                        </table>
                                        <table align="center" cellspacing="0" cellpadding="5">
                                            <tr>
                                                <td class="centered colored-blue boldText">${childFollowup.pcr3Result == 1 ? '&ofcir;' : '&cir;'}</td>
                                                <td class="centered colored-blue boldText">${childFollowup.pcr3Result == 0 ? '&ofcir;' : '&cir;'}</td>
                                            </tr>
                                            <tr>
                                                <td align="center">Positif</td>
                                                <td align="center">Negatif</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>

                                <%--<hr style="border-color: #1aac9b">--%>

                                <%--<hr style="width: 100%">--%>

                        </td>
                    </c:if>
                    <c:if test="${countLine == 0}">
                        <td class="bordered-green" rowspan="${constRowspan}" align="center">
                            <span class="center colored-blue boldText">
                                <fmt:formatDate type="date" value="${ childFollowup.ctxInitiationDate }" pattern="dd/MM/yyyy" />
                            </span><br><br><br>
                            <table align="center" cellspacing="0" cellpadding="5">
                                <tr>
                                    <td class="centered colored-blue">${childFollowup.ageInWeekOnCtxInitiation}</td>
                                    <td align="center">S</td>
                                    <td class="centered colored-blue">${childFollowup.ageInMonthOnCtxInitiation}</td>
                                    <td align="center">M</td>
                                </tr>
                            </table>
                        </td>
                    </c:if>
                    <td class="bordered-green" align="center">
                        <table align="center" cellspacing="0" cellpadding="5">
                            <tr>
                                <td class="centered colored-blue boldText">${ visit.continuingCtx == 1 ? '&ofcir;' : '&cir;' }</td>
                                <td class="centered colored-blue boldText">${ visit.continuingCtx == 0 ? '&ofcir;' : '&cir;' }</td>
                            </tr>
                            <tr>
                                <td align="center">Oui</td>
                                <td align="center">Non</td>
                            </tr>
                        </table>
                    </td>
                    <c:if test="${countLine == 0}">
                        <td class="bordered-green" rowspan="${constRowspan}" align="center">
                            <span class="center colored-blue boldText"><fmt:formatDate type="date" value="${ childFollowup.inhInitiationDate }" pattern="dd/MM/yyyy" /></span><br> <br> <br>
                            <table align="center" cellspacing="0" cellpadding="5">
                                <tr>
                                    <td class="colored-blue centered">${childFollowup.ageInWeekOnInhInitiation}</td>
                                    <td align="center">S</td>
                                    <td class="colored-blue centered">${childFollowup.ageInMonthOnInhInitiation}</td>
                                    <td align="center">M</td>
                                </tr>
                            </table>
                        </td>
                    </c:if>
                    <td class="bordered-green" align="center">
                        <table align="center" cellspacing="0" cellpadding="5">
                            <tr>
                                <td class="centered colored-blue boldText">${ visit.continuingInh == 1 ? '&ofcir;' : '&cir;' }</td>
                                <td class="centered colored-blue boldText">${ visit.continuingInh == 0 ? '&ofcir;' : '&cir;' }</td>
                            </tr>
                            <tr>
                                <td align="center">Oui</td>
                                <td align="center">Non</td>
                            </tr>
                        </table>
                                    <%--${ (visit.continuingInh == 0) ? 'Non' : (visit.continuingInh == 1 ? 'Oui' : '')}--%>
                    </td>
                    <c:if test="${countLine == 0}">
                        <td class="bordered-green" rowspan="${constRowspan}" align="center" style="padding: 0">
                            <span class="center colored-blue boldText">
                                <fmt:formatDate type="date" value="${ childFollowup.hivSerology1Date }" pattern="dd/MM/yyyy" />
                            </span><br><br>
                            <table cellspacing="0" cellpadding="5">
                                <tr>
                                    <td class="centered colored-blue">${childFollowup.ageInWeekOnHivSerology1}</td>
                                    <td align="center">S</td>
                                    <td class="centered colored-blue">${childFollowup.ageInMonthOnHivSerology1}</td>
                                    <td align="center">M</td>
                                </tr>
                            </table>
                            <br>
                            <table align="center" cellspacing="0" cellpadding="5">
                                <tr>
                                    <td class="centered colored-blue">${childFollowup.hivSerology1Result == 1 ? '&ofcir;' : '&cir;'}</td>
                                    <td class="centered colored-blue">${childFollowup.hivSerology1Result == 0 ? '&ofcir;' : '&cir;'}</td>
                                </tr>
                                <tr>
                                    <td align="center">Positif</td>
                                    <td align="center">Negatif</td>
                                </tr>
                            </table>
                            <br>
                            <hr>
                            <br>
                            <span class="center colored-blue boldText"><fmt:formatDate type="date" value="${ childFollowup.hivSerology2Date }" pattern="dd/MM/yyyy" /></span><br><br>
                            <table align="center" cellspacing="0" cellpadding="5">
                                <tr>
                                    <td class="centered colored-blue">${childFollowup.ageInWeekOnHivSerology2}</td>
                                    <td align="center">S</td>
                                    <td class="centered colored-blue">${childFollowup.ageInMonthOnHivSerology2}</td>
                                    <td align="center">M</td>
                                </tr>
                            </table>
                            <br>
                            <table align="center" cellspacing="0" cellpadding="5">
                                <tr>
                                    <td class="centered colored-blue">${childFollowup.hivSerology2Result == 1 ? '&ofcir;' : '&cir;'}</td>
                                    <td class="centered colored-blue">${childFollowup.hivSerology2Result == 0 ? '&ofcir;' : '&cir;'}</td>
                                </tr>
                                <tr>
                                    <td align="center">Positif</td>
                                    <td align="center">Negatif</td>
                                </tr>
                            </table>
                        </td>
                        <td class="bordered-green" rowspan="${constRowspan}" align="left">
                            <table>
                                <tr><td width="10px" class="colored-blue centered boldText">${childFollowup.followupResult == 0 ? '&ofcir;' : '&cir;' }</td><td>Negatif sorti du programme</td></tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr><td width="10px" class="colored-blue centered boldText">${childFollowup.followupResult == 1 ? '&ofcir;' : '&cir;' }</td><td>Perdue de vue</td></tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr><td width="10px" class="colored-blue centered boldText">${childFollowup.followupResult == 2 ? '&ofcir;' : '&cir;' }</td><td>Decede</td></tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr><td width="10px" class="colored-blue centered boldText">${childFollowup.followupResult == 3 ? '&ofcir;' : '&cir;' }</td><td>Positif adresse pour la PEC</td></tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr><td width="10px" class="colored-blue centered boldText">${childFollowup.followupResult == 5 ? '&ofcir;' : '&cir;' }</td><td>Transfere</td></tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr><td width="10px" class="colored-blue centered boldText">${childFollowup.followupResult == 6 ? '&ofcir;' : '&cir;' }</td><td>Refere</td></tr>

                            </table>
                            <br><br>
                            Site de transfert <br>ou de reference : <br>
                                <span class="center colored-blue boldText">${childFollowup.referenceLocation}</span>
                                <%--<form:input path="referenceLocation" cssClass=""/>--%>
                            <br><br>

                            Numero d'identification <br>unique : <br>
                                <span class="colored-blue">${currentChild.patient.getPatientIdentifier.identifier}</span><br><br>
                            Date du statut final : <br>
                            <span class="center colored-blue boldText">
                                <fmt:formatDate type="date" value="${ childFollowup.followupResultDate }" pattern="dd/MM/yyyy" />
                            </span>
                        </td>
                    </c:if>
                </tr>
                <c:set var = "countLine" value = "${countLine + 1}"/>
            </c:forEach>

            <c:if test="${(visitFormHeight + visitHeight * nbVisit ) < firstColHeight}">
                <tr>
                    <td class="bordered-green"></td>
                    <td class="bordered-green"></td>
                    <td class="bordered-green"></td>
                    <td class="bordered-green"></td>
                    <td class="bordered-green"></td>
                    <td class="bordered-green"></td>
                </tr>
            </c:if>

            </tbody>
        </table>
</c:if>

<%@ include file="template/localFooter.jsp"%>