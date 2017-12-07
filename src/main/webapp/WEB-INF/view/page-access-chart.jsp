<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<%@ include file="common/colunm-chart.jspf"%>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Page Access Chart for ${ipaddress}</header>
            <div id="chartdiv"></div>
        </div>
    </div>            
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Page Access Statistics for ${ipaddress}</header>
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right" style="width: 150px;" id="page-access-input" onkeyup="searchInputTable('page-access-input', 'page-access-table')" placeholder="Search for page access.." title="Type in a page access"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive" id="page-access-box">
                <table id="page-access-table">
                    <tr id="tableHeader">
                        <th class="tr-p" onclick="sortNum(0, 'page-access-table')">No.</th>
                        <th class="tr-p" onclick="sortAlpha(1, 'page-access-table')">Page Access</th>
                        <th class="tr-p" onclick="sortNum(2, 'page-access-table')">Visit Times</th>
                    </tr>
                    <c:forEach var="map" items="${mapPageAccess}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${map.key}</td>
                            <td>${map.value}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <c:if test="${ipaddress.equals('All IP address')}"> 
            	<br><center><button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/follow-users.html'">
            </c:if>
            <c:if test="${!ipaddress.equals('All IP address')}"> 
            	<br><center><button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/follow-user-ip/${ipaddress}.html'">
            </c:if> 
            <i class="fa fa-globe"></i><b> Check more details </b> </button></center><br>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>