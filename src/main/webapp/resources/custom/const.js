
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : May 11, 2017, 4:11:03 AM
    Author     : HUNGCUONG
*/

/* change server config here */
// const SERVER_CONFIGURATION = 'custom';
// const SERVER_CONFIGURATION = 'localhost:3000';
const SERVER_CONFIGURATION = 'localhost:3000 & localhost:8080';
// const SERVER_CONFIGURATION = 'https://hotel-booking-and-reservations.herokuapp.com';
// const SERVER_CONFIGURATION = 'https://hotel-booking-system-v1.herokuapp.com';
// const SERVER_CONFIGURATION = 'https://hotel-booking-system-v2.herokuapp.com';

var SPRING_SERVER_URL;
var MEAN_SERVER_URL;

if(SERVER_CONFIGURATION == 'http://localhost:3000') {
    MEAN_SERVER_URL = "http://localhost:3000/";
    SPRING_SERVER_URL = "http://localhost:3000/";
}

if(SERVER_CONFIGURATION == 'localhost:3000 & localhost:8080') {
    MEAN_SERVER_URL = "http://localhost:3000/";
    SPRING_SERVER_URL = "http://localhost:8080/Hotel-booking-and-reservations-system-admin/";
}

if(SERVER_CONFIGURATION == 'https://hotel-booking-and-reservations.herokuapp.com') {
    MEAN_SERVER_URL = "https://hotel-booking-and-reservations.herokuapp.com/";
    SPRING_SERVER_URL = "https://admin-hotel-booking.herokuapp.com/";
}

if(SERVER_CONFIGURATION == 'https://hotel-booking-system-v1.herokuapp.com') {
    MEAN_SERVER_URL = "https://hotel-booking-system-v1.herokuapp.com/";
    SPRING_SERVER_URL = "https://admin-hotel-booking-v1.herokuapp.com/";
}

if(SERVER_CONFIGURATION == 'https://hotel-booking-system-v2.herokuapp.com') {
    MEAN_SERVER_URL = "https://hotel-booking-system-v2.herokuapp.com/";
    SPRING_SERVER_URL = "https://admin-hotel-booking-v1.herokuapp.com/";
}


var SPRING_API_URL = SPRING_SERVER_URL + 'api/';
var MEAN_URL = MEAN_SERVER_URL;

const MEAN_API_URL = MEAN_URL + "api/";
const TRACKING_API_URL = MEAN_API_URL + "follow-users/";
const TRACKING_API_URL_PAGE = MEAN_API_URL + "follow-users/page/";
const TRACKING_TOTAL_PAGE_API = TRACKING_API_URL + "count/page/";
const SORT_TRACKING_API = TRACKING_API_URL + "sort/";
const STATISTICS_API_URL = TRACKING_API_URL + "statistics/";
const PIE_CHART_API_URL = SPRING_API_URL + "/country-chart";
const PAGE_ACCESS_API_URL = STATISTICS_API_URL + "PageAccess/";
const PAGE_ACCESS_IP_API_URL = PAGE_ACCESS_API_URL + "userIP/";
const PAGE_ACCESS_MEMBER_API_URL = PAGE_ACCESS_API_URL + "username/";
const COLUNM_CHART_API_URL = SPRING_API_URL + 'page-access-chart/';
const IP_COLUNM_CHART_API_URL = COLUNM_CHART_API_URL + 'userIP/';
const MEMBER_COLUNM_CHART_API_URL = COLUNM_CHART_API_URL + 'username/';
const SEARCH_API = TRACKING_API_URL + 'search/';
const TOTAL_PAGE_SEARCH_API = SEARCH_API + 'total-page/';
const TRACKING_SEARCH_API = SEARCH_API + 'all/';
const TOTAL_PAGE_DISPLAY = 3;
