<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<%@ include file="template/registerHeader.jsp"%>

<b class="boxHeader">Saisie des registres</b>

<c:if test="${ mode == 'form' }" >
    <b class="boxHeader">Titre registre</b>
    <form:form modelAttribute="consultationForm" method="post" action="" id="form">
        <table>
            <thead>
            <tr>
                <th colspan="2"></th>
            </tr>
            </thead>
            <tr>
                <td>
                    <table>
                        <tbody>
                        <tr>
                            <td>Family Name</td>
                            <td><form:input path="familyName" cssClass=""/></td>
                        </tr>
                        <tr>
                            <td>Given Name</td>
                            <td><form:input path="givenName" cssClass=""/></td>
                        </tr>
                        <tr>
                            <td>Pregnant number</td>
                            <td><form:input path="pregnantNumber" cssClass=""/></td>
                        </tr>
                        <tr>
                            <td>HIV Care Number</td>
                            <td><form:input path="hivCareNumber" cssClass=""/></td>
                        </tr>
                        <tr>
                            <td>Screening Number</td>
                            <td><form:input path="screeningNumber" cssClass=""/></td>
                        </tr>
                        <tr>
                            <td>age</td>
                            <td><form:input path="age" cssClass=""/></td>
                        </tr>
                        <tr>
                            <td>Marital Status</td>
                            <td>
                                <form:radiobutton path="maritalStatus" value="1" label="Celibataire" cssClass=""/>
                                <form:radiobutton path="maritalStatus" value="2" label="Marie(e)" cssClass="" />
                                <form:radiobutton path="maritalStatus" value="3" label="Divorce(e)" cssClass="" />
                                <form:radiobutton path="maritalStatus" value="4" label="Concubinage" cssClass="" />
                                <form:radiobutton path="maritalStatus" value="5" label="Veuf(ve)" cssClass="" />
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </td>
                <td>
                    <table>
                        <tr>
                            <td></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <table>
                <tr>
                    <td></td>
                </tr>
            </table>
            <c:if test="${ register == 'prenatal' }" >
                <span>Consultation prenatale</span>
            </c:if>
            <c:if test="${ register == 'birth' }" >
                <span>Accouchement</span>
            </c:if>
            <c:if test="${ register == 'postnatal' }" >
                <span>Consultation postnatale</span>
            </c:if>
        </table>

    </form:form>
</c:if>

<%@ include file="/WEB-INF/template/footer.jsp"%>