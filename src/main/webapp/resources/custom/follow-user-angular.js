
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : Nov 11, 2017, 9:38:05 AM
    Author     : HUNGCUONG
*/


function padTime(t) {
    return t < 10 ? "0"+t : t;
}

function padMilli(s) {
	if(s < 10)
		return "00"+s;
	else if(s < 100)
		return "0"+s;
	return s;
}

function getTimeBySecond(s) {
    var ms = s % 1000;
    s = (s - ms) / 1000;
    var secs = s % 60;
    s = (s - secs) / 60;
    var mins = s % 60;
    var hrs = (s - mins) / 60;

    return padTime(hrs) + ':' + padTime(mins) + ':' + padTime(secs) + ':' + padMilli(ms);  
}

var app = angular.module('follow-users', []);

app.filter('secondsToTime',function(){


    return function (s) {
		if (s < 0)
            return "00:00:00:000";
		if(typeof s !== "number") {
			if(s == "NaN") {
				return "00:00:00:000";
			}
		}
		return getTimeBySecond(s);
    };
});

app.controller('folowUserCtrl', function($scope, $http) {
  $scope.currentPage = page;
  $http.get(totalPageAPI).then(function (response) {
      var totalpage = response.data.total_page;
      var arrPageDisplay = new Array(TOTAL_PAGE_DISPLAY);
      console.log(response.data.total_page);
      if(totalpage <= 1) 
    	  arrPageDisplay = [1];
      else if(page >= totalpage) 
    	  arrPageDisplay = [page-1, page];
      else {
          for(var i = 0; i < TOTAL_PAGE_DISPLAY; i++) {
        	  if(page == 1)
        		  arrPageDisplay[i] = page + i;
        	  else
        		  arrPageDisplay[i] = page - 1 + i;
          }
      }
      $scope.arrPageDisplay = arrPageDisplay;
      console.log(arrPageDisplay);
      var backPage = page - 1;
      var nextPage = page + 1;
      if(page == 1)
    	  backPage = 1;
      if(page == totalpage)
		  nextPage = totalpage;
      var footerURL = '.html#follow-user-table';
	  $scope.clickPageURL = clickPageURL;
	  $scope.clickBackPageURL = clickPageURL + backPage + footerURL;
	  $scope.clickNextPageURL = clickPageURL + nextPage + footerURL;
	  $scope.footerURL = footerURL;
  });
  $http.get(tracking_api_url).then(function (response) {
      $scope.followUserData = response.data;
  });

  $scope.sortDB = function(field_name) {
	  $http.get(SORT_TRACKING_API + field_name).then(function (response) {
	      $scope.followUserData = response.data;
	  });
  }
});