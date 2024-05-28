// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("openModalBtn");

// Get close for the modal
var span = document.getElementById("closeModalBtn");

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on x, close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}



// Function to make an AJAX request to make a reservation
function makeReservation(data) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:9999/reservations/make',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            // Handle the response from the server
            console.log(response);
        },
        error: function (error) {
            // Handle errors
            console.error(error);
        }
    });
}

// Function to make an AJAX request to cancel a reservation
function cancelReservation(reservationId) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:9999/reservations/make',
        data: { reservationID: reservationId },
        success: function (response) {
            // Handle the response from the server
            console.log(response);
        },
        error: function (error) {
            // Handle errors
            console.error(error);
        }
    });
}

// Function to make an AJAX request to reject a reservation
function rejectReservation(reservationId) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:9999/reservations/make',
        data: { reservationID: reservationId },
        success: function (response) {
            // Handle the response from the server
            console.log(response);
        },
        error: function (error) {
            // Handle errors
            console.error(error);
        }
    });
}

// Function to make an AJAX request to accept a reservation
function acceptReservation(reservationId) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:9999/reservations/make',
        data: { reservationID: reservationId },
        success: function (response) {
            // Handle the response from the server
            console.log(response);
        },
        error: function (error) {
            // Handle errors
            console.error(error);
        }
    });
}

// Function to make an AJAX request to get all reservations
function getAllReservations() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:9999/reservations/make',
        success: function (response) {
            // Handle the response from the server
            console.log(response);
            // Update the UI with the received data (this depends on your UI structure)
        },
        error: function (error) {
            // Handle errors
            console.error(error);
        }
    });
}

// Function to make an AJAX request to suggest a room number for relocation
function suggestRoomNumber(reservationId) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:9999/reservations/make',
        data: { reservationID: reservationId },
        success: function (response) {
            // Handle the response from the server
            console.log(response);
        },
        error: function (error) {
            // Handle errors
            console.error(error);
        }
    });
}

// Form submission
document.getElementById("myForm").addEventListener("submit", function (event) {
    event.preventDefault();

    // Get form values
    var classroomCode = document.getElementById("classroomCode").value;
    var courseCode = document.getElementById("courseCode").value;
    var studentName = document.getElementById("studentName").value;
    var studentContact = document.getElementById("studentContact").value;
    var startTime = document.getElementById("startTime").value;
    var endTime = document.getElementById("endTime").value;

    // Create data object
    var data = {
        classroomCode: classroomCode,
        courseCode: courseCode,
        studentName: studentName,
        studentContact: studentContact,
        timeslot: {
            startTime: startTime,
            endTime: endTime
        }
    };

    // Call the makeReservation function
    makeReservation(data);

    // Close the modal
    modal.style.display = "none";
});