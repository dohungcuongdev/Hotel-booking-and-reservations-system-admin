/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : Nov 11, 2017, 9:38:05 AM
    Author     : HUNGCUONG
*/

var app = angular.module('pageAccessChart', []);
app.controller('pageAccessChartCtrl', function($scope, $http) {
	$http.get(api_with_IP).then(function(response) {
		$scope.pageAccessData = response.data;
		$scope.sortVisitTime($scope.pageAccessData);
		var chart = AmCharts.makeChart("chartdiv", {
		    "theme": "light",
		    "type": "serial",
			"startDuration": 2,
		    "dataProvider":  $scope.pageAccessData,
		    "valueAxes": [{
		        "position": "left",
		        "title": "Visit time of page access"
		    }],
		    "graphs": [{
		        "balloonText": "[[category]]: <b>[[value]]</b>",
		        "fillColorsField": "color",
		        "fillAlphas": 1,
		        "lineAlpha": 0.1,
		        "type": "column",
		        "valueField": "visit_time"
		    }],
		    "depth3D": 20,
			"angle": 30,
		    "chartCursor": {
		        "categoryBalloonEnabled": false,
		        "cursorAlpha": 0,
		        "zoomable": false
		    },
		    "categoryField": "page_access",
		    "categoryAxis": {
		        "gridPosition": "start",
		        "labelRotation": 45
		    },
		    "export": {
		    	"enabled": true
		     }
		});
	});
	$scope.sortVisitTime = function(pageAccessData) {
		timsort.sort(pageAccessData, (x, y) => y.visit_time - x.visit_time);
	};
});