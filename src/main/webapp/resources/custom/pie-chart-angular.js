/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : Nov 11, 2017, 9:38:05 AM
    Author     : HUNGCUONG
*/

var app = angular.module('chart', []);

app.controller('ChartController', function($scope, $http) {
  $http.get(PIE_CHART_API_URL).then(function (response) {
      $scope.data = response.data;
      $scope.sortCountry($scope.data);
      var chart = AmCharts.makeChart("chartdiv", {
    	  "type": "pie",
    	  "startDuration": 0,
    	   "theme": "light",
    	  "addClassNames": true,
    	  "legend":{
    	   	"position":"right",
    	    "marginRight":50,
    	    "autoMargins":false
    	  },
    	  "innerRadius": "30%",
    	  "defs": {
    	    "filter": [{
    	      "id": "shadow",
    	      "width": "100%",
    	      "height": "100%",
    	      "feOffset": {
    	        "result": "offOut",
    	        "in": "SourceAlpha",
    	        "dx": 0,
    	        "dy": 0
    	      },
    	      "feGaussianBlur": {
    	        "result": "blurOut",
    	        "in": "offOut",
    	        "stdDeviation": 5
    	      },
    	      "feBlend": {
    	        "in": "SourceGraphic",
    	        "in2": "blurOut",
    	        "mode": "normal"
    	      }
    	    }]
    	  },
    	  "dataProvider": $scope.data,
    	  "valueField": "visitTime",
    	  "titleField": "countryName",
    	  "export": {
    	    "enabled": true
    	  }
    	});

    	chart.addListener("init", handleInit);

    	chart.addListener("rollOverSlice", function(e) {
    	  handleRollOver(e);
    	});

    	function handleInit(){
    	  chart.legend.addListener("rollOverItem", handleRollOver);
    	}

    	function handleRollOver(e){
    	  var wedge = e.dataItem.wedge.node;
    	  wedge.parentNode.appendChild(wedge);
    	}
  });
  
  $scope.sortCountry = function(data) {
	  timsort.sort(data, (x, y) => y.visitTime - x.visitTime);
  };
});