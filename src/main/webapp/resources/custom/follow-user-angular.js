
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : Nov 11, 2017, 9:38:05 AM
    Author     : HUNGCUONG
*/
//const API_URL = "http://localhost:8080/Hotel-booking-and-reservations-system-admin/api/follow-users.html";
const API_URL = "http://localhost:3000/api/follow-users";
var app = angular.module('follow-users', []);

app.filter('secondsToTime',function(){
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

    return function (s) {
		if (typeof s !== "number" || s < 0)
            return "00:00:00:000";
        var ms = s % 1000;
        s = (s - ms) / 1000;
        var secs = s % 60;
        s = (s - secs) / 60;
        var mins = s % 60;
        var hrs = (s - mins) / 60;

        return padTime(hrs) + ':' + padTime(mins) + ':' + padTime(secs) + ':' + padMilli(ms);        
    };
});

app.controller('folowUserCtrl', function($scope, $http) {
  $http.get(API_URL).then(function (response) {
      $scope.followUserData = response.data;
	  for(var i = 0; i < $scope.followUserData.length;i++) {
		if($scope.followUserData[i].username == null || $scope.followUserData[i].username == "")
			$scope.followUserData[i].username = "guest";
		$scope.followUserData[i].created_at = new Date($scope.followUserData[i].created_at);
	  }
	  $scope.sortDateAccess($scope.followUserData);
	});
	
	$scope.isSortAsc1, $scope.isSortAsc2, $scope.isSortAsc3, $scope.isSortAsc4, $scope.isSortAsc5, $scope.isSortAsc6, $scope.isSortAsc7 = false;
	
	$scope.sortIP = function(followUserData) {
		if($scope.isSortAsc1) {
			timsort.sort(followUserData, (x, y) => x.user_ip_address.localeCompare(y.user_ip_address));
			$scope.isSortAsc1 = false;
		}
		else {
			timsort.sort(followUserData, (x, y) => y.user_ip_address.localeCompare(x.user_ip_address));
			$scope.isSortAsc1 = true;;
		}
	};
	$scope.sortExIP = function(followUserData) {
		if($scope.isSortAsc2) {
			timsort.sort(followUserData, (x, y) => x.external_ip_address.localeCompare(y.external_ip_address));
			$scope.isSortAsc2 = false;
		}
		else {
			timsort.sort(followUserData, (x, y) => y.external_ip_address.localeCompare(x.external_ip_address));
			$scope.isSortAsc2 = true;;
		}
	};
	$scope.sortCountry = function(followUserData) {
		if($scope.isSortAsc3) {
			timsort.sort(followUserData, (x, y) => x.country.localeCompare(y.country));
			$scope.isSortAsc3 = false;
		}
		else {
			timsort.sort(followUserData, (x, y) => y.country.localeCompare(x.country));
			$scope.isSortAsc3 = true;;
		}
	};
	$scope.sortUser = function(followUserData) {
		if($scope.isSortAsc4) {
			timsort.sort(followUserData, (x, y) => x.username.localeCompare(y.username));
			$scope.isSortAsc4 = false;
		}
		else {
			timsort.sort(followUserData, (x, y) => y.username.localeCompare(x.username));
			$scope.isSortAsc4 = true;;
		}
	};
	$scope.sortDateAccess = function(followUserData) {
		if($scope.isSortAsc6) {
			timsort.sort(followUserData, (x, y) => x.created_at.getTime() - y.created_at.getTime());
			$scope.isSortAsc6 = false;
		}
		else {
			timsort.sort(followUserData, (x, y) => y.created_at.getTime() - x.created_at.getTime());
			$scope.isSortAsc6 = true;;
		}
	};
	$scope.sortPageAccess = function(followUserData) {
		if($scope.isSortAsc5) {
			timsort.sort(followUserData, (x, y) => x.page_access.localeCompare(y.page_access));
			$scope.isSortAsc5 = false;
		}
		else {
			timsort.sort(followUserData, (x, y) => y.page_access.localeCompare(x.page_access));
			$scope.isSortAsc5 = true;;
		}
	};
	$scope.sortDuration = function(followUserData) {
		if($scope.isSortAsc7) {
			timsort.sort(followUserData, (x, y) => x.duration - y.duration);
			$scope.isSortAsc7 = false;
		}
		else {
			timsort.sort(followUserData, (x, y) => y.duration - x.duration);
			$scope.isSortAsc7 = true;;
		}
	};
});