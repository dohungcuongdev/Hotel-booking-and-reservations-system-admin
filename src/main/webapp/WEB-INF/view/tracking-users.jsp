<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/sub-content.jspf"%>
<div class="row">
    <div class="col-xs-12">
        <div class="panel">
            <header class="panel-heading">Tracking By ${tracking}</header>
            <div class="box-tools m-b-15">
                <div class="input-group">
                    <input type="text" name="table_search" class="form-control input-sm pull-right" 
                           style="width: 150px;" id="input-tracking" onkeyup="searchInputTable('input-tracking', 'table-tracking')" 
                           placeholder="Search for tracking.." title="Type in tracking data"/>
                    <div class="input-group-btn">
                        <button class="btn btn-sm btn-default"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>
            <div class="panel-body table-responsive">
				<table id="table-tracking" ng-app="trackingTable" ng-controller="trackingCtrl">
				    <tr id="tableHeader">
				        <th class="tr-p" onclick="sortNum(0, 'table-tracking')">No.</th>
				        <th class="tr-p" onclick="sortAlpha(1, 'table-tracking')">${tracking}</th>
				        <th class="tr-p" onclick="sortNum(2, 'table-tracking')">Visit Times</th>
				    </tr>
				    <tr ng-repeat="d in trackingData">
				        <td>{{ $index + 1 }}</td>
				        <td><a href = "${pageContext.request.contextPath}/${tracking}/{{ d._id }}.html">{{ d._id }}</a></td>
				        <td>{{ d.count }}</td>
				    </tr>
				</table>
            </div>
        </div>
    </div>
</div>
<script>
var app = angular.module('trackingTable', []);
app.controller('trackingCtrl', function($scope, $http) {
  $http.get(STATISTICS_API_URL + '${tracking}').then(function (response) {
      $scope.trackingData = response.data;
  });
});
</script>
<%@ include file="common/footer.jspf"%>