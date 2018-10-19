<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage PTME Reports" otherwise="/login.htm" redirect="/module/ptme/reportTemplate.form" />
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<%@ include file="template/reportHeader.jsp"%>

<script type="application/javascript">
    if (jQuery) {
        $j(document).ready(function () {
            $j("#list-register").dataTable({
                "bPaginate": true,
                "order": [[1, "desc"]],
                "iDisplayLength": 20,
                "bLengthChange": false,
                "bFilter": true,
                "bSort": true,
                "bInfo": true,
                "bJQueryUI": true,
                "oLanguage": {
                    "oPaginate": {
                        "sPrevious": "Pr&eacute;c&eacute;dent",
                        "sNext": "Suivant"
                    },
                    "sZeroRecords": "Aucun enregistrement trouv&eacute;",
                    "sInfo": "Affichage de _START_ a _END_ sur _TOTAL_ ",
                    "sSearch": "Chercher"
                },
                "aaSorting": [[0, "desc"]]
            });
        });
    }
</script>


Ypu are on ${pageName} Page


<%@ include file="template/localFooter.jsp"%>