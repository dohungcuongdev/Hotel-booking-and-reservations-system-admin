<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Page Access Statistics of: ${username}</header>
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
                <table id="page-access-ip-table" ng-app="trackingTable" ng-controller="trackingCtrl">
                    <tr id="tableHeader">
                        <th class="tr-p" onclick="sortNum(0, 'page-access-ip-table')">No.</th>
                        <th class="tr-p" onclick="sortAlpha(1, 'page-access-ip-table')">Page Access</th>
                        <th class="tr-p" onclick="sortNum(2, 'page-access-ip-table')">Visit Times</th>
                    </tr>
                    <tr ng-repeat="d in trackingData">
                        <td>{{ $index + 1 }}</td>
                        <td>{{d._id }}</td>
                        <td>{{d.count }}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>
<script>
var app = angular.module('trackingTable', []);
var api_url = '';
var tracking = '${tracking}';
if(tracking == 'ip')
	api_url = PAGE_ACCESS_IP_API_URL + '${trackingParam}';
if(tracking == 'member')
	api_url = PAGE_ACCESS_MEMBER_API_URL + '${trackingParam}';
app.controller('trackingCtrl', function($scope, $http) {
  $http.get(api_url).then(function (response) {
      $scope.trackingData = response.data;
  });
});
</script>