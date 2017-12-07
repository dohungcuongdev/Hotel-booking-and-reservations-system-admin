/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : Nov 11, 2017, 9:38:05 AM
    Author     : HUNGCUONG
*/

const CHART_API_URL = "http://localhost:8080/Hotel-booking-and-reservations-system-admin/api/chart-data.html";
var app = angular.module('chart', []);

app.controller('ChartController', function($scope, $http) {
  $http.get(CHART_API_URL).then(function (response) {
      $scope.data = response.data;
      
      var chart = AmCharts.makeChart("chartdiv", {
    	  "type": "pie",
    	  "startDuration": 0,
    	   "theme": "light",
    	  "addClassNames": true,
    	  "legend":{
    	   	"position":"right",
    	    "marginRight":150,
    	    "autoMargins":false
    	  },
    	  "innerRadius": "30%",
    	  "defs": {
    	    "filter": [{
    	      "id": "shadow",
    	      "width": "200%",
    	      "height": "200%",
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
    	  "valueField": "quantity",
    	  "titleField": "data",
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
});