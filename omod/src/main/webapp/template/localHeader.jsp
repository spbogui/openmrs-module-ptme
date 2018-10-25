<%--<%@ spring:taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fct" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<%@ include file="includeScript.jsp"%>

<script type="text/javascript">

    if (jQuery) {

        $(document).ready(function () {
            jQuery.extend( jQuery.fn.dataTableExt.oSort, {
                "date-uk-pre": function ( a ) {
                    if (a == null || a == "") {
                        return 0;
                    }
                    var ukDatea = a.split('/');
                    return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
                },

                "date-uk-asc": function ( a, b ) {
                    return ((a < b) ? -1 : ((a > b) ? 1 : 0));
                },

                "date-uk-desc": function ( a, b ) {
                    return ((a < b) ? 1 : ((a > b) ? -1 : 0));
                }
            } );

            $.datepicker.setDefaults({
                showOn: "button",
                buttonImageOnly: true,
                buttonImage: "${pageContext.request.contextPath}/moduleResources/ptme/images/calendar.gif",
                buttonText: "Calendar"
            });
            $.datepicker.setDefaults( $.datepicker.regional[ "fr" ] );

            $(".datepickerPtme").datepicker({
				dateFormat: 'dd/mm/yy'
//                dayNames: [ "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" ],
//                dayNamesShort: [ "Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam" ],
//                monthNamesShort: [ "Jan", "Fev", "Mar", "Avr", "Mai", "Jui", "Juil", "Aou", "Sep", "Oct", "Nov", "Dec" ],
//                monthNames: [ "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre" ]
            });

            $('input[type=radio]').click(function(e){
                if (e.ctrlKey) {
                    $(this).prop('checked', false);
                }
            });

            $j('input:radio').live('dblclick', function () {
                var name = $j(this).attr('name');
                resetRadio(name);
            });

//            var prev = {};
//                if (prev && prev.value == this.value) {
//                    $j(this).prop('checked', !prev.status);
//                }
//                prev = {
//                    value: this.value,
//                    status: this.checked
//                };
//            });
			$('button').button();
			$('select').selectmenu();
			$('input[type=submit]').button();
			$('input[type=button]').button();

        });
    }
</script>
<style>
	.tableHeader {
		border: none;
		margin-bottom:-3px;
	}

	.line {
		width:100%;
		border-bottom: 1px solid #1aac9b;
		margin: 10px 0;
	}

	table {
		border-collapse: collapse;
	}

	a {
		text-decoration: none;
	}

	.tableHeader a {
		text-decoration: none;
		text-align: center;
	}

	table.table-mother-followup tr > td{
		padding: 5px;
	}

	table.table-mother-followup th {
		border: 1px solid #000000;
		background-color: #1aac9b;
		text-align: center;
		color: #ffffff;
		font-weight: normal;
	}

	.tableHeader td {
		padding: 5px 0;
		text-align: center;
	}

	.headerMaternity {
		padding: 15px;
	}

	.boxMenuItemChoice {
		background-color: #1aac9b;
		display: block;
		text-align: center;
		font-weight: bold;
	}

	.boxMenuItemChoice a {
		color: #ffffff;
	}

	.small-table-type-vih td{
		border: none;
	}

	.bordered-green {
		border: 1px solid #1aac9b;
	}

	.bordered-black {
		border: 1px solid #000000;
	}

	.required {
		color: red;
	}

	.centered {
		text-align: center;
	}

	.bordered-white {
		border: 1px solid #ffffff;
	}

	.colored-blue {
		color: darkblue;
		font-weight: bold;
	}

	.colored-green {
		color: darkgreen;
		font-weight: bold;
	}

	.table-child-followup {
		font-size: 12px;
		margin-top: 10px;
		border: 1px solid #1aac9b;
	}

	.table-child-followup th {
		background-color: #1aac9b;
		text-align: center;
		border: 1px solid #000000;
		color: #ffffff;
	}

	table.table-mother-followup > table.small-table-type-vih  td {
		text-align: center;
		border: 1px solid #ffffff;
	}

	table.table-mother-followup > table.small-table-type-vih  td > table > td {
		border-bottom: 1px solid #1aac9b;
		/*width: 2px;*/
		/*height: 2px;*/
	}

	.boxMenuLine {
		border-bottom: 1px solid #1aac9b;
		padding: 0;
	}

	.boldText {
		font-weight: bold;
	}

	.textarea-c {
		width:100%;
		font-size: 14px;
		/*border:none;*/
		text-align: justify;
		font-family: "Consolas", Helvetica, sans-serif;
	}

	.dataTableOddRow {
		background-color: #79ace9;
	}
	.dataTableEvenRow {
		background-color: #a2aec7;
	}
	table.dataTable tr.odd { background-color: white;/*  border:1px lightgrey;*/}
	table.dataTable tr.even{ background-color: #bbccf7; /*border:1px lightgrey;*/ }

	table.dataTable tr td {padding: 5px}

	table.dataTable tr td table.button-table tr {
		background-color: transparent;
		/*padding:0;*/
	}

	table.dataTable tr td table.button-table td {
		background-color: transparent;
		/*padding:0;*/
	}

	table.dataTable tr td a {
		text-transform: none;
		font-weight: bold;
		text-decoration: none;
	}

	table.dataTable tr td a:hover {
		color: #1aac9b;
	}

</style>

<div class="">

	<h2 class="" style="padding-bottom: 15px">
		<spring:message code="ptme.manage" />
	</h2>

</div>

<%--<div style="border-bottom: solid 1px #1aac9b; width: 100%;margin-top: 10px"></div>--%>

<div class="">
	<table class="tableHeader">
		<tr>
			<td>&nbsp;</td>
			<td width="100px" <c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="boxMenuItemChoice"</c:if>>
				<a href="${pageContext.request.contextPath}/module/ptme/manage.form"
				   <c:if test='<%= request.getRequestURI().contains("/manage") %>'>style="color: white"</c:if>>
					<spring:message code="ptme.homeTitle" />
				</a>
			</td>
			<td width="250px" <c:if test='<%= request.getRequestURI().contains("/register") %>'>class="boxMenuItemChoice"</c:if>>
				<a href="${pageContext.request.contextPath}/module/ptme/registerList.form"
				   <c:if test='<%= request.getRequestURI().contains("/register") %>'>style="color: white"</c:if>>
					<spring:message code="ptme.registerTitle" />
				</a>
			</td>
			<td width="250px" <c:if test='<%= request.getRequestURI().contains("/motherFollowup") %>'>class="boxMenuItemChoice"</c:if>>
				<a href="${pageContext.request.contextPath}/module/ptme/motherFollowupPatient.form"
				   <c:if test='<%= request.getRequestURI().contains("/motherFollowup") %>'>style="color: white"</c:if>>
					<spring:message code="ptme.followupMotherTitle" /></a>
			</td>
			<td width="250px" <c:if test='<%= request.getRequestURI().contains("/child") %>'>class="boxMenuItemChoice"</c:if>>
				<a href="${pageContext.request.contextPath}/module/ptme/childManage.form"
				   <c:if test='<%= request.getRequestURI().contains("/child") %>'>style="color: white"</c:if>>
					<spring:message code="ptme.followupChildTitle" /></a>
			</td>
			<openmrs:hasPrivilege privilege="Run PTME Reports">
				<td width="250px" <c:if test='<%= request.getRequestURI().contains("report") || request.getRequestURI().contains("Report") %>'>class="boxMenuItemChoice"</c:if>>
					<a href="${pageContext.request.contextPath}/module/ptme/reportGenerate.form"
					   <c:if test='<%= request.getRequestURI().contains("report") || request.getRequestURI().contains("Report") %>'>style="color: white"</c:if>>
						<spring:message code="ptme.report.menu.title" /></a>
				</td>
			</openmrs:hasPrivilege>
		</tr>
	</table>
</div>
<div class="boxHeader"></div>