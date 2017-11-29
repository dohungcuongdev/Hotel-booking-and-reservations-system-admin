<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>

<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">
                Page Access Statistics with User IP: ${ip}
            </header>
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right" 
                           style="width: 150px;" id="page-access-ip-input" onkeyup="searchInputTable('page-access-ip-input', 'page-access-ip-table')" 
                           placeholder="Search for page access.." title="Type in a page access"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive" id="page-access-box">
                <table id="page-access-ip-table">
                    <tr>
                        <th class="tr-p" onclick="sortNum(0, 'page-access-ip-table')">No.</th>
                        <th class="tr-p" onclick="sortAlpha(2, 'page-access-ip-table')">Page Access</th>
                        <th class="tr-p" onclick="sortAlpha(1, 'page-access-ip-table')">IP Address</th>
                        <th class="tr-p" onclick="sortNum(3, 'page-access-ip-table')">Visit Times</th>
                    </tr>

                    <c:forEach var="map" items="${mapFollowUserIP}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${map.key}</td>
                            <td>${ip}</td>
                            <td>${map.value}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <br><center><button class="btn btn-danger" onclick="location.href = '${pageContext.request.contextPath}/page-access-chart/${ip}.html'">
                    <i class="fa fa-bar-chart"></i><b> View Chart of this IP Address </b> </button></center><br>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>
<%@ include file="common/footer.jspf"%>