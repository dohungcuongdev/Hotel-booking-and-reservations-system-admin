<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>

<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">
                IP Address Statistics

            </header>
            
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right" 
                           style="width: 150px;" id="ip-stat-input" onkeyup="searchInputTable('ip-stat-input', 'ip-stat-table')" 
                           placeholder="Search for ip address.." title="Type in a ip address"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive">
                <table id="ip-stat-table">
                    <tr>
                        <th class="tr-p" onclick="sortNum(0, 'ip-stat-table')">No.</th>
                        <th class="tr-p" onclick="sortAlpha(1, 'ip-stat-table')">IP Address</th>
                        <th class="tr-p" onclick="sortNum(2, 'ip-stat-table')">Visit Times</th>
                    </tr>

                    <c:forEach var="map" items="${mapFollowUsersIP}" varStatus="loop">
                        <tr class="tr-p" onclick="location.href = '${pageContext.request.contextPath}/follow-user-ip/${map.key}.html'">
                            <td>${loop.index + 1}</td>
                            <td>${map.key}</td>
                            <td>${map.value}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>

<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">
                Page Access Statistics
            </header>
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right" 
                           style="width: 150px;" id="page-access-input" onkeyup="searchInputTable('page-access-input', 'page-access-table')" 
                           placeholder="Search for page access.." title="Type in a page access"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive" id="page-access-box">
                <table id="page-access-table">
                    <tr>
                        <th class="tr-p" onclick="sortNum(0, 'page-access-table')">No.</th>
                        <th class="tr-p" onclick="sortAlpha(1, 'page-access-table')">Page Access</th>
                        <th class="tr-p" onclick="sortNum(2, 'page-access-table')">Visit Times</th>
                    </tr>

                    <c:forEach var="map" items="${mapFollowUsers}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${map.key}</td>
                            <td>${map.value}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>
<%@ include file="common/footer.jspf"%>