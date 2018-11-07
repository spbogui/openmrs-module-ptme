<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage PTME" otherwise="/login.htm" redirect="/module/ptme/reportGenerate.form" />
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<script type="application/javascript">
    if (jQuery) {
        $(document).ready(function () {
            $("#list-generate").dataTable({
//                dom: 'B<"clear">lfrtip',
//                buttons: {
//                    name: 'primary',
//                    buttons: [ 'copy', 'csv', 'excel' ]
//                },
                "pageLength": 20,
                "order": [[1, "desc"]],
                "language": {
                    "zeroRecords": "Aucun rapport &agrave; afficher",
                    paginate: {
                        previous: 'Pr&eacute;c&eacute;dent',
                        next:     'Suivant'
                    },
                    "info":"Affichage de _START_ &agrave _END_ sur _TOTAL_ ",
                    "search": "Filtrer les rapports g&eacute;n&eacute;r&eacute;s"
                },
                "lengthChange": false,
                "stripeClasses": [ 'odd', 'even' ]
            });
        });
    }
</script>

<%@ include file="template/reportHeader.jsp"%>

<c:if test="${mode == 'list' || empty(mode)}">
    <div class="box">
        <h3><b>Rapports</b></h3>

        <div class="line"></div>
        <%--<form:form action="" commandName="getReportGenerationFromFrom" id="form" method="get" >--%>
            <table cellspacing="0" cellpadding="5">
                <tr>
                    <td><input type="submit" value="Nouveau" name="add"></td>
                </tr>
            </table>
        <%--</form:form>--%>
    </div>

    <div class="boxHeader"></div>
    <div class="box">
        <h3>Liste des Rapports g&eacute;n&eacute;r&eacute;s</h3>
        <div class="line"></div>
        <table width="100%" style="border: solid #1aac9b 1px" cellpadding="0" cellspacing="0" id="list-generate">
            <thead>
            <tr style="background-color: #1aac9b; color: #ffffff;">
                <th>Nom</th>
                <th>Rapport</th>
                <th>Date d&eacute;but </th>
                <th>Date de fin </th>
                <th>G&eacute;n&eacute;r&eacute; par</th>
                <th>G&eacute;n&eacute;r&eacute; le</th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>

</c:if>

<c:if test="${mode == 'form'}">

</c:if>

<%@ include file="template/localFooter.jsp"%>

