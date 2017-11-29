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

function search() {
    searchInputTable("input-management","table-management")
}

function searchInputTable(myInput,myTable) {
    var input, filter, table, tr, td, i;
    input = document.getElementById(myInput);
    filter = input.value.toUpperCase();
    table = document.getElementById(myTable);
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function readURL(input, imgTag, width, height) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $(imgTag)
                    .attr('src', e.target.result)
                    .width(width)
                    .height(height);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function sortAlpha(n, myTable) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById(myTable);
    switching = true;
    //Set the sorting direction to ascending:
    dir = "asc";
    /*Make a loop that will continue until
     no switching has been done:*/
    while (switching) {
        //start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /*Loop through all table rows (except the
         first, which contains table headers):*/
        for (i = 1; i < (rows.length - 1); i++) {
            //start by saying there should be no switching:
            shouldSwitch = false;
            /*Get the two elements you want to compare,
             one from current row and one from the next:*/
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /*check if the two rows should switch place,
             based on the direction, asc or desc:*/
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
             and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            //Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /*If no switching has been done AND the direction is "asc",
             set the direction to "desc" and run the while loop again.*/
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function sortNum(n, myTable) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById(myTable);
    switching = true;
    //Set the sorting direction to ascending:
    dir = "asc";
    /*Make a loop that will continue until
     no switching has been done:*/
    while (switching) {
        //start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /*Loop through all table rows (except the
         first, which contains table headers):*/
        for (i = 1; i < (rows.length - 1); i++) {
            //start by saying there should be no switching:
            shouldSwitch = false;
            /*Get the two elements you want to compare,
             one from current row and one from the next:*/
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /*check if the two rows should switch place,
             based on the direction, asc or desc:*/
            if (dir == "asc") {
                if (parseInt(x.innerHTML) > parseInt(y.innerHTML)) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (parseInt(x.innerHTML) < parseInt(y.innerHTML)) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
             and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            //Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /*If no switching has been done AND the direction is "asc",
             set the direction to "desc" and run the while loop again.*/
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function convertDate(originaldate) {
    var temp1 = originaldate.substring(originaldate.length - 5);
    var temp2 = originaldate.substring(0, 10);
    var temp3 = originaldate.substring(10, 19);
    return new Date(temp2 + temp1 + temp3);
}

function sortDate(n, myTable) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById(myTable);
    switching = true;
    //Set the sorting direction to ascending:
    dir = "asc";
    /*Make a loop that will continue until
     no switching has been done:*/
    while (switching) {
        //start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /*Loop through all table rows (except the
         first, which contains table headers):*/
        for (i = 1; i < (rows.length - 1); i++) {
            //start by saying there should be no switching:
            shouldSwitch = false;
            /*Get the two elements you want to compare,
             one from current row and one from the next:*/
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /*check if the two rows should switch place,
             based on the direction, asc or desc:*/
            if (dir == "asc") {
                if (convertDate(x.innerHTML) > convertDate(y.innerHTML)) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (convertDate(x.innerHTML) < convertDate(y.innerHTML)) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
             and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            //Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /*If no switching has been done AND the direction is "asc",
             set the direction to "desc" and run the while loop again.*/
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function deleteService(servicename) {
    swal({
        title: "Are you sure?",
        text: "Delete this service now!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        closeOnConfirm: false
    }, function () {
        window.location.href = '${pageContext.request.contextPath}/remove-service/' + servicename + '.htm';
    });
}

function deleteRoom(roomname) {
    swal({
        title: "Are you sure?",
        text: "Delete this room now!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        closeOnConfirm: false
    }, function () {
        window.location.href = '${pageContext.request.contextPath}/remove-room/' + roomname + '.htm';
    });
}

function checkeditresult(r) {
    if (r === undefined) {
    } else if (r === "success")
        swal('Congrats!', 'Edited successfully!', 'success');
    else if (r !== '')
        swal('Oops...!', r, 'error');
}

function checkSendEmail(r) {
    console.log(r);
    if (r === undefined || r === '') {
    } else if (r === "Sent successfully")
        swal('Congrats!', 'Email Sent successfully!', 'success');
    else 
        swal('Oops...!', r, 'error');
}

