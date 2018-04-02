function remove(url, text, confirm) {
    swal({
        title: "Are you sure?",
        text: text,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: confirm,
        closeOnConfirm: false
    }, function () {
        window.location.href = url;
    });
}

function defaultRemove(url, text) {
	remove(url, text, "Yes, delete it!");
}

function deleteService(url) {
	defaultRemove(url, 'Delete this item from restaurant now!');
}

function deleteRoom(url) {
	defaultRemove(url, 'Delete this room now!');
}

function blockUser(url) {
	remove(url, 'Block this user now!', "Yes, block plz!");
}

function unblockUser(url) {
	remove(url, 'Unblock this user now!', "Yes, unblock plz!");
}

function checkSendEmail(r) {
    console.log(r);
    if (r === undefined || r === '') {
    } else if (r === "Sent successfully")
        swal('Congrats!', 'Email Sent successfully!', 'success');
    else 
        swal('Oops...!', r, 'error');
}

function checkUpdateResult(r, success_mes) {
    if (r === undefined) {
    } else if (r === "success")
        swal('Congrats!', success_mes, 'success');
    else if (r !== '')
        swal('Oops...!', r, 'error');
}

function checkeditresult(r) {
	checkUpdateResult(r, 'Edited successfully!');
}

function checkAddResult(r) {
	checkUpdateResult(r, 'Hotel Item Added with default image. You can change the image of this item!');
}