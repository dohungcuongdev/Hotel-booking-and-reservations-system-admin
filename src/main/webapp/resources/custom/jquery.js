/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : Nov 11, 2017, 9:38:05 AM
    Author     : HUNGCUONG
*/

$('input').on('ifChecked', function (event) {
    // var element = $(this).parent().find('input:checkbox:first');
    // element.parent().parent().parent().addClass('highlight');
    $(this).parents('li').addClass("task-done");
    console.log('ok');
});
$('input').on('ifUnchecked', function (event) {
    // var element = $(this).parent().find('input:checkbox:first');
    // element.parent().parent().parent().removeClass('highlight');
    $(this).parents('li').removeClass("task-done");
    console.log('not');
});
$('#noti-box').slimScroll({
    height: '400px',
    size: '5px',
    BorderRadius: '5px'
});
$('#activity-box').slimScroll({
    height: '707px',
    size: '5px',
    BorderRadius: '5px'
});
$('#room-booked-box').slimScroll({
    height: '150px',
    size: '5px',
    BorderRadius: '5px'
});
$('#room-canceled-box').slimScroll({
    height: '150px',
    size: '5px',
    BorderRadius: '5px'
});
$('#date-visited-box').slimScroll({
    height: '150px',
    size: '5px',
    BorderRadius: '5px'
});    
$('#feedback-box').slimScroll({
    height: '150px',
    size: '5px',
    BorderRadius: '5px'
});
$('#feedback-room-box').slimScroll({
    height: '150px',
    size: '5px',
    BorderRadius: '5px'
});
$('input[type="checkbox"].flat-grey, input[type="radio"].flat-grey').iCheck({
    checkboxClass: 'icheckbox_flat-grey',
    radioClass: 'iradio_flat-grey'
});
$('#manage-services-box').slimScroll({
    height: '400px',
    size: '5px',
    BorderRadius: '5px'
});
$('#manage-rooms-box').slimScroll({
    height: '500px',
    size: '5px',
    BorderRadius: '5px'
});
$('#all-message-box').slimScroll({
    height: '400px',
    size: '5px',
    BorderRadius: '5px'
});
$('#page-access-box').slimScroll({
    height: '600px',
    size: '5px',
    BorderRadius: '5px'
});
$('#follow-user-box').slimScroll({
    height: '400px',
    size: '5px',
    BorderRadius: '5px'
});

var windowsize = $(window).width();

$(window).resize(function () {
    windowsize = $(window).width();
    if (windowsize < 1000) {
        //if the window is greater than 440px wide then turn on jScrollPane..

    }
});