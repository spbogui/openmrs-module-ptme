<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage PTME Reports" otherwise="/login.htm" redirect="/module/ptme/reportDataSet.form" />
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<%@ include file="template/reportHeader.jsp"%>

<script type="application/javascript">
    if (jQuery) {
        $(document).ready(function () {
            $("#list-dataset").dataTable({
                "pageLength": 20,
                "order": [[1, "desc"]],
                "language": {
                    "zeroRecords": "Aucun dataset &agrave; afficher",
                    paginate: {
                        previous: 'Pr&eacute;c&eacute;dent',
                        next:     'Suivant'
                    },
                    "info":"Affichage de _START_ &agrave _END_ sur _TOTAL_ ",
                    "search": "Filtrer les dataset"
                },
                "lengthChange": false,
                "stripeClasses": [ 'odd', 'even' ]
            });
        });
        
        function moveForward() {
            var forwardVariable = '';
            $("#availableIndicatorList :selected").each(function () {
                forwardVariable += '<option value="'+$(this).val()+'">'+$(this).html()+'</option>';
                $(this).remove();
            });

            $("#selectedIndicatorList").append(forwardVariable);

            sortElement("selectedIndicatorList");
        }

        function moveBackward()
        {
            var backwardVariable='';
            //taking the selected items of list 2 and concatenating to a variable named backward_variable.
            $("#selectedIndicatorList :selected").each(function(){
                backwardVariable+="<option value='"+$(this).val()+"'>"+$(this).html()+"</option>";
                $(this).remove();
            });
            //Now appending the selected firs list's element to the list 1.
            $("#availableIndicatorList").append(backwardVariable);

            //Sorting the list 2 so that it shows the list alphabetically
            sortElement("availableIndicatorList");

        }

        function sortElement(id)
        {
            var selectElement = $('#'+id);
            var optionsElement = selectElement.children('option').get();
            optionsElement.sort(function(a, b) {
                var compA = $(a).text().toUpperCase();
                var compB = $(b).text().toUpperCase();
                return (compA < compB) ? -1 : (compA > compB) ? 1 : 0;
            });
            $.each(optionsElement, function(index, items) { selectElement.append(items); });

        }
    }
</script>


<div class="box">
    <h3><b>Ensembles de donn&eacute;es</b></h3>

    <div class="line"></div>
    <%--<form:form action="" commandName="getIndicatorFromFrom" id="form" method="get" >--%>
        <table cellspacing="0" cellpadding="5">
            <tr>
                <td><input type="submit" value="Nouveau" name="add"></td>
            </tr>
        </table>
    <%--</form:form>--%>
</div>
<div class="boxHeader"></div>
<div class="box">
    <h3>Liste des Ensembles de donn&eacute;es</h3>
    <div class="line"></div>
    <table width="100%" style="border: solid #1aac9b 1px" cellpadding="0" cellspacing="0" id="list-dataset">
        <thead>
        <tr style="background-color: #1aac9b; color: #ffffff;">
            <th>code</th>
            <th>Nom</th>
            <th>Description</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>


<%@ include file="template/localFooter.jsp"%>