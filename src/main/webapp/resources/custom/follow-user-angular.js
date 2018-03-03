
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : Nov 11, 2017, 9:38:05 AM
    Author     : HUNGCUONG
*/

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
			timsort.sort(followUserData, (x, y) => new Date(x.created_at).getTime() - new Date(y.created_at).getTime());
			$scope.isSortAsc6 = false;
		}
		else {
			timsort.sort(followUserData, (x, y) => new Date(y.created_at).getTime() - new Date(x.created_at).getTime());
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

app.controller('folowAllUserCtrl', function($scope, $http) {
  $http.get(TRACKING_API_URL).then(function (response) {
      $scope.followUserData = response.data;
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
			timsort.sort(followUserData, (x, y) => new Date(x.created_at).getTime() - new Date(y.created_at).getTime());
			$scope.isSortAsc6 = false;
		}
		else {
			timsort.sort(followUserData, (x, y) => new Date(y.created_at).getTime() - new Date(x.created_at).getTime());
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

function isNDigit(s, n) {
	if(s.length != n)
		return false;
	else if(isNaN(s))
		return false;
	else
		return true;
}

function is2Digit(s) {
	return isNDigit(s,2);
}

function is3Digit(s) {
	return isNDigit(s,3);
}

function getDurationFormat(input) {
	let temp = input.split(":");
	let hour = temp[0];
	let min = temp[1];
	let second = temp[2];
	let millis = temp[3];
	if(!is2Digit(hour) || !is2Digit(min) || !is2Digit(second) || !is3Digit(millis) ||  min > 59 || second > 59 || millis > 999 || min < 0 || second < 0 || millis < 0) {
		return -1;
	}
	else	
		return 1000*(3600*parseInt(hour)+ 60*parseInt(min) + parseInt(second)) + parseInt(millis);
}

function getDurationKeywordFormat(keyword) {
	let temp = keyword.split(",");
	if(temp.length != 2)
		return '00:00:00:000 -> 00:00:00:000';
	else {
		let i = getTimeBySecond(temp[0]);
		let k = getTimeBySecond(temp[1]);
		return i + ' -> ' + k;
	}
}

function changeRangeVal() {
	let keyword = $("#keyword").val();
	let temp = keyword.split(" -> ");
	if(temp.length == 2) {
		let i = getDurationFormat(temp[0]);
		let k = getDurationFormat(temp[1]);
		console.log(i);
		if(i != -1 && k != -1) {
			$("#range1").val(i);
			$("#range2").val(k);
		}
	}
}

function showRangeVal() {
	let input1 = $("#range1").val();
	let input2 = $("#range2").val();
	let duration1 = getTimeBySecond(input1);
	let duration2 = getTimeBySecond(input2);
	if(duration1 != -1 && duration2 != -1)
		$("#keyword").val(duration1 + " -> " + duration2);
}

function initFieldName() {
	let selected = $("#fieldname").val();
	if(selected == 'duration') {
		document.getElementById('keyword').type = 'text';
		document.getElementById('range1').style.display = 'block';
		document.getElementById('range2').style.display = 'block';
	} else {
		document.getElementById('range1').style.display = 'none';
		document.getElementById('range2').style.display = 'none';
		if(selected == 'created_at')
			document.getElementById('keyword').type = 'date';
		else
			document.getElementById('keyword').type = 'text';
	}
}

function selectFieldName() {
	initFieldName();
	$("#keyword").val('');
	$("#range1").val(0);
	$("#range2").val(0);
	$("#search-keyword").html('');
	$("#search-fu-btn").css("margin-top", "0px");
}

function showSearchKeyword() {
	let searchKW = '';
	if(keyword != '') {
		if(fieldname == 'duration')
			searchKW = 'Keyword: ' + '<b style=\"color:#CC0033\">' + getDurationKeywordFormat(keyword) + '<b>';
		else
			searchKW = 'Keyword: ' + '<b style=\"color:#CC0033\">' + keyword + '<b>';
	}
	$("#search-keyword").html(searchKW);
}