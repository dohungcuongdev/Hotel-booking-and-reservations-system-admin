
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : May 11, 2017, 4:11:03 AM
    Author     : HUNGCUONG
*/

const SPRING_API_URL = "http://localhost:8080/Hotel-booking-and-reservations-system-admin/api/";
const MEAN_API_URL = "http://localhost:3000/api/";
const TRACKING_API_URL = MEAN_API_URL + "follow-users/";
const STATISTICS_API_URL = TRACKING_API_URL + "statistics/";
const PIE_CHART_API_URL = TRACKING_API_URL + "country/chart-data";
const PAGE_ACCESS_API_URL = STATISTICS_API_URL + "PageAccess/";
const PAGE_ACCESS_IP_API_URL = PAGE_ACCESS_API_URL + "userIP/";
const PAGE_ACCESS_MEMBER_API_URL = PAGE_ACCESS_API_URL + "username/";
const COLUNM_CHART_API_URL = SPRING_API_URL + 'page-access-chart/';
const IP_COLUNM_CHART_API_URL = COLUNM_CHART_API_URL + 'userIP/';
const MEMBER_COLUNM_CHART_API_URL = COLUNM_CHART_API_URL + 'username/';