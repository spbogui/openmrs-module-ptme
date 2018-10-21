<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage PTME" otherwise="/login.htm" redirect="/module/ptme/register.form" />
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<script type="application/javascript">
    if (jQuery) {
        $(document).ready(function () {
            $("#list-register").dataTable({
                dom: 'B<"clear">lfrtip',
                buttons: {
                    name: 'primary',
                    buttons: [ 'copy', 'csv', 'excel' ]
                },
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
                    "search": "Filtrer les consultations"
                },
                "lengthChange": false,
                "stripeClasses": [ 'odd', 'even' ]
            });
        });
    }
</script>

<%@ include file="template/registerHeader.jsp"%>

<div class="box" style="padding-bottom: 20px">
    <h2><b>Historique des consultations</b></h2>
    <div class="line"></div>

    <form:form  modelAttribute="manageConsultationForm" method="get" action="" id="form">
        <form:hidden path="page" />
        <table cellpadding="5" cellspacing="0">
            <tr>
                <td class="boldText">Choix du registre : </td>
                <td>
                    <form:radiobutton path="register" value="Prenatal" label="Prenatal" />
                    <form:radiobutton path="register" value="Birth" label="Accouchement" />
                    <form:radiobutton path="register" value="Postnatal" label="Postnatal" />
                </td>
                <td>|</td>
                <td class="boldText">Date de debut  : </td>
                <td>
                    <form:input path="sDate" cssClass="datepickerPtme" size="10px"/>
                </td>
                <td class="boldText">Date de fin : </td>
                <td>
                     <form:input path="eDate" cssClass="datepickerPtme" size="10px"/>
                </td>
                <td>|<%--</td>
                <td class="boldText">Nombre de ligne : </td>
                <td>
                    <form:select path="size">
                        <form:option value="10">10</form:option>
                        <form:option value="20">20</form:option>
                        <form:option value="30">30</form:option>
                    </form:select>
                </td>
                <td>|--%></td>
                <td><input type="submit" value="Afficher"></td>
            </tr>
            </tr>
        </table>
    </form:form>

    <div class="line"></div>
<%--</div>--%>
<%--<div class="boxHeader"></div>--%>
<%--<div class="box">--%>
    <c:if test="${ not empty consultationList }" >
        <h2>
            <b>
                Liste des
                <c:if test="${ manageConsultationForm.register == 'Prenatal' }" >
                    Consultations pr&eacute;natales
                </c:if>
                <c:if test="${ manageConsultationForm.register == 'Birth' }" >
                    Accouchents
                </c:if>
                <c:if test="${ manageConsultationForm.register == 'Postanal' }" >
                    Consultations postnatales
                </c:if>
                du <fmt:formatDate type="date" value="${ manageConsultationForm.sDate }" pattern="dd/MM/yyyy" />
                au <fmt:formatDate type="date" value="${ manageConsultationForm.eDate }" pattern="dd/MM/yyyy" />
            </b>
        </h2>
        <div class="line"></div>
    </c:if>
    <table style="border-collapse: collapse; border: 1px solid #1aac9b;" id="list-register" width="100%" align="center" cellspacing="0" cellpadding="0">
        <c:if test="${ not empty consultationList }" >
            <thead>
            <tr style="background-color: #1aac9b; color: #ffffff;">
                <th align="center">Date de <br>consultation</th>
                <th align="center">Num&eacute;ro de gestante</th>
                <th align="center">Num&eacute;ro de PEC</th>
                <th align="center">Num&eacute;ro de D&eacute;pistage</th>
                <c:if test="${ manageConsultationForm.register == 'Prenatal' }" >
                    <th align="center">Rang de CPN</th>
                    <th align="center">Semaines <br>d'Amenorrhee</th>
                </c:if>
                <th align="center">Statut VIH <br>&agrave; l'accueil</th>
                <th align="center">Propostion <br>de test</th>
                <th align="center">R&eacute;sultat <br>du test</th>
                <th align="center">Annonce du <br>r&eacute;sultat</th>
                <th align="center">ARV Remise ?</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="consult" items="${ consultationList }">
                <tr>
                    <td><fmt:formatDate type="date" value="${ consult.consultationDate }" pattern="dd/MM/yyyy" /></td>
                    <td>${consult.pregnantPatient.pregnantNumber}</td>
                    <td>${consult.pregnantPatient.hivCareNumber}</td>
                    <td>${consult.pregnantPatient.screeningNumber}</td>
                    <c:if test="${ manageConsultationForm.register == 'Prenatal' }" >
                        <td>${consult.rank}</td>
                        <td align="center">${consult.weekOfAmenorrhea}</td>
                    </c:if>
                    <td align="center">${(consult.hivService.hivStatusAtReception == 0) ? 'Negatif': (consult.hivService.hivStatusAtReception == 1 ? 'Positif': 'Inconnu')}</td>
                    <td align="center">${ (consult.hivService.testProposal == 0) ? 'Non': (consult.hivService.testProposal == 1 ? 'Oui' : 'N/A') }</td>
                    <td align="center">${ (consult.hivService.testResult == 0) ? 'Negatif': (consult.hivService.testResult == 1 ? 'Positif' : 'N/A') }</td>
                    <td align="center">${ (consult.hivService.resultAnnouncement == 0) ? 'Non' : (consult.hivService.resultAnnouncement == 1 ? 'Oui' : 'N/A')}</td>
                    <td align="center">${ (consult.hivService.arvDiscount == 0) ? 'Non' : (consult.hivService.arvDiscount == 1 ? 'Oui' : 'N/A') }</td>
                    <td align="center" width="30">
                        <table cellspacing="0" cellpadding="0" class="button-table">
                            <tr>
                                <td>
                                    <c:url value="/module/ptme/registerList.form" var="url">
                                        <c:param name="consultationId" value="${consult.consultationId}"/>
                                        <c:param name="register" value="${manageConsultationForm.register}"/>
                                    </c:url>
                                    <a href="${ url }"><img src="/openmrs/images/edit.gif" alt="Editer"></a>
                                </td>
                                <td>|</td>
                                <td>
                                    <c:url value="/module/ptme/registerList.form" var="urlsup">
                                        <c:param name="delId" value="${consult.consultationId}"/>
                                        <c:param name="register" value="${manageConsultationForm.register}"/>
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
        </c:if>

        <c:if test="${ empty consultationList }" >
            <tr>
                <td align="center">Veuillez entrer les param&egrave;tres de votre s&eacute;lection pour voir la liste</td>
            </tr>
        </c:if>

    </table>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>