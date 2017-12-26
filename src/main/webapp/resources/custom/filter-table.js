
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
    Created on : May 10, 2017, 3:12:13 AM
    Author     : HUNGCUONG
*/


function search() {
    searchInputTable("input-management","table-management")
}

function searchInputTable(myInput,myTable) {
    var filter = document.getElementById(myInput).value.toUpperCase();
    searchInputTableWithFilter(myInput, myTable, filter);
}

function searchInputTableWithFilter(myInput, myTable, filter) {
	var filter, found, table, tr, td, i, j;
	table = document.getElementById(myTable);
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td");
		for (j = 0; j < td.length; j++) {
			if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
				found = true;
			}
		}
		if (found) {
			tr[i].style.display = "";
			found = false;
		} else {
			if (tr[i].id != 'tableHeader')
				tr[i].style.display = "none";
		}
	}
}

function searchMessageWithFilter(myInput, myMesBox, filter) {
	var mesBox = document.getElementById(myMesBox);
	var messages = mesBox.getElementsByTagName("div");
	var found;
	for (i = 0; i < messages.length; i++) {
		if (messages[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
			found = true;
		}
		if (found) {
			messages[i].style.display = "";
			found = false;
		} else {
			messages[i].style.display = "none";
		}
	}
}


function searchMessage(myInput,myMesBox) {
    var filter = document.getElementById(myInput).value.toUpperCase();
    searchMessageWithFilter(myInput, myMesBox, filter);
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

function convertDate(originaldate) {
    var temp1 = originaldate.substring(originaldate.length - 5);
    var temp2 = originaldate.substring(0, 10);
    var temp3 = originaldate.substring(10, 19);
    return new Date(temp2 + temp1 + temp3);
}

function compareInnerHTML(dataType, x, y) {
	if(dataType == 'alpha')
		return x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase();
	if(dataType == 'number')
		return parseFloat(x.innerHTML) > parseFloat(y.innerHTML);
	if(dataType == 'date')
		return convertDate(x.innerHTML) > convertDate(y.innerHTML);
}

function sortAlpha(n, myTable) {
	sortTable(n, myTable, 'alpha');
}

function sortNum(n, myTable) {
	sortTable(n, myTable, 'number');
}

function sortDate(n, myTable) {
	sortTable(n, myTable, 'date');
}

function sortTable(n, myTable, dataType) {
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
                if (compareInnerHTML(dataType, x, y)) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (compareInnerHTML(dataType, y, x)) {
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