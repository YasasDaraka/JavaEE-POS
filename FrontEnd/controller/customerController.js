
getAllCustomers();


$("#cusSave").click(function () {

    if (checkAll()){
        saveCustomer();
    }else{
        alert("Error");
    }
});

$(document).ready(function(){
    $("#cusID").prop('disabled', true);
    $("#cusName").prop('disabled', true);
    $("#cusAddress").prop('disabled', true);

    $('#cusThead').css({
        'width': '600px',
        'display': 'flex'
    });
    $('#cusThead>th').css({
        'flex': '1',
        'max-width': 'calc(100%/3*1)'
    });
    $('#customerTable').css({
        'max-height': '370px',
        'overflow-y': 'auto',
        'display': 'table-caption'
    });
    $('#customerTable>tr').css({
        'width': '600px',
        'display': 'flex'
    });
    $('#customerTable>tr>td').css({
        'flex': '1',
        'max-width': 'calc(100%/3*1)'
    });
});
function generateCustomerId() {

    if (customerDB.length == 0) {
        $("#cusID").val("C00-0001");
    } else if (customerDB.length > 0) {
        var id = customerDB[customerDB.length - 1].id.split("-")[1];
        var tempId = parseInt(id);
        tempId = tempId + 1;
        if (tempId <= 9) {
            $("#cusID").val("C00-000" + tempId);
        } else if (tempId <= 99) {
            $("#cusID").val("C00-00" + tempId);
        } else if (tempId <= 999) {
            $("#cusID").val("C00-0" + tempId);
        } else if (tempId <= 9999) {
            $("#cusID").val("C00-" + tempId);
        }
    }
}


$('#cusAdd').click(function(){
    $("#cusID").prop('disabled', false);
    $("#cusName").prop('disabled', false);
    $("#cusAddress").prop('disabled', false);

    $(this).find("#cusID").focus();
    generateCustomerId();
    setClBtn();
});

$("#cusGetAll").click(function () {
    getAllCustomers();
});


function bindTrrEvents() {
    $('#customerTable>tr').click(function () {

        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let address = $(this).children().eq(2).text();


        $("#cusID").val(id);
        $("#cusName").val(name);
        $("#cusAddress").val(address);

        $("#cusID").prop('disabled', false);
        $("#cusName").prop('disabled', false);
        $("#cusAddress").prop('disabled', false);
        $("#cusUpdate").prop('disabled', false);

        setBtn();

    });
}

$("#cusDelete").click(function () {
    let id = $("#cusID").val();

    let consent = confirm("Do you want to delete.?");
    if (consent) {
        let response = deleteCustomer(id);
        if (response) {
            alert("Customer Deleted");
            clearCustomerInputFields();
            getAllCustomers();
        } else {
            alert("Customer Not Removed..!");
        }
    }
    $("#cusID").prop('disabled', true);
    $("#cusName").prop('disabled', true);
    $("#cusAddress").prop('disabled', true);

});

$("#cusUpdate").click(function () {
    let id = $("#cusID").val();
    updateCustomer(id);
    clearCustomerInputFields();
});

$("#cusClear").click(function () {
    clearCustomerInputFields();
});


function saveCustomer() {
    let customerID = $("#cusID").val();

    if (searchCustomer(customerID.trim()) == undefined) {


        let customerName = $("#cusName").val();
        let customerAddress = $("#cusAddress").val();


        let newCustomer = Object.assign({}, customer);
        newCustomer.id = customerID;
        newCustomer.name = customerName;
        newCustomer.address = customerAddress;


        customerDB.push(newCustomer);
        clearCustomerInputFields();
        getAllCustomers();
        $("#cusID").prop('disabled', true);
        $("#cusName").prop('disabled', true);
        $("#cusAddress").prop('disabled', true);


    } else {
        alert("Customer already exits.!");
        clearCustomerInputFields();
    }
}

function getAllCustomers() {

    $("#customerTable").empty();

    for (let i = 0; i < customerDB.length; i++) {
        let id = customerDB[i].id;
        let name = customerDB[i].name;
        let address = customerDB[i].address;

        let row = `<tr>
                     <td>${id}</td>
                     <td>${name}</td>
                     <td>${address}</td>
                   </tr>`;

        $("#customerTable").append(row);
        $('#customerTable').css({
            'max-height': '370px',
            'overflow-y': 'auto',
            'display': 'table-caption'
        });
        $('#customerTable>tr').css({
            'width': '600px',
            'display': 'flex'
        });
        $('#customerTable>tr>td').css({
            'flex': '1',
            'max-width': 'calc(100%/3*1)'
        });
        bindTrrEvents();
    }
}

function deleteCustomer(id) {
    for (let i = 0; i < customerDB.length; i++) {
        if (customerDB[i].id == id) {
            customerDB.splice(i, 1);
            return true;
        }
    }
    return false;
}

function searchCustomer(id) {
    return customerDB.find(function (customer) {

        return customer.id == id;
    });
}

function updateCustomer(id) {
    if (searchCustomer(id) == undefined) {
        alert("No such Customer..please check the ID");
    } else {
        let consent = confirm("Do you really want to update this customer.?");
        if (consent) {
            let customer = searchCustomer(id);


            let customerName = $("#cusName").val();
            let customerAddress = $("#cusAddress").val();

            customer.name = customerName;
            customer.address = customerAddress;

            getAllCustomers();

            $("#cusID").prop('disabled', true);
            $("#cusName").prop('disabled', true);
            $("#cusAddress").prop('disabled', true);
        }
    }

}