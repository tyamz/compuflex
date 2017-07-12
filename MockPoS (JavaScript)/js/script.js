/* STARTUP SCRIPTS */
$( document ).ready(function() {
    startTime();
    clearAll();
    $('.input').each(function() {
        $(this).on('keyup', function() {
            changeInput();
        });
    });
    $('.input').each(function() {
        changeInput();
    });
});

/* USER FUNCTIONS */
// Function to Change the User
function changeUser() {
    var name = ["Tommy","Thor","Iron Man","Spider-Man","Captain America","Daredevil"];
    var choice = Math.floor(Math.random() * name.length);
    document.getElementById("user").innerHTML = name[choice];
}

/* CART FUNCTIONS */
// Function to add item(s) to the cart.
function add() {
    "use strict";
    var n = ["Macadamia", "Hazelnut", "Almond", "Peanut", "Walnut", "Pistachio", "Pecan", "Brazil"];
    var p = [2.00, 1.90, 1.31, 0.85, 1.12, 1.53, 1.25, 1.75];
    var choice = Math.floor(Math.random() * n.length);

    var table = document.getElementById("cart");
    if(cartContains(n[choice])) {
        var row = document.getElementById(n[choice]);
        // Get Old Info
        var i = row.rowIndex;
        var q = row.cells[3].innerHTML;
        table.deleteRow(i);
        q++;
    } else {
        var i = table.rows.length;
        q = 1;
        cart.push(n[choice]);
    }
    var row = table.insertRow(i);
    row.className = "active";
    row.id = n[choice];

    var checkbox = row.insertCell(0);
    var description = row.insertCell(1);
    var price = row.insertCell(2);
    var quantity = row.insertCell(3);
    var total = row.insertCell(4);

    checkbox.innerHTML = '<input type="checkbox" name="chk"/>';
    description.innerHTML = n[choice];
    price.innerHTML = p[choice];
    quantity.innerHTML = q;
    var t = price.innerHTML * quantity.innerHTML;
    total.innerHTML = parseFloat(t).toFixed(2);
    incTotal(price.innerHTML);
}

// Function to remove an item from the cart
function remove()  {
        var table = document.getElementById("cart");
        var rowCount = table.rows.length;

        for(var i = rowCount; i > 0; i--) {
            var row = table.rows[i];
            if(row != null) {
                var chkbox = row.cells[0].getElementsByTagName('input')[0];
                var name = row.cells[1].innerHTML;
                if('checkbox' == chkbox.type && true == chkbox.checked) {
                    var total = document.getElementById('total');
                    total.innerHTML = parseFloat(parseFloat(total.innerHTML) - (parseFloat(row.cells[2].innerHTML) * parseFloat(row.cells[3].innerHTML))).toFixed(2);
                    table.deleteRow(i);
                    var index = cart.indexOf(name);
                    cart.splice(index, 1);
                    changeInput();
                }
            }
        }
}

// Function to clear ALL items from the cart
function clearAll() {
    cart = [];
    var table = document.getElementById("cart");

    for(var i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }

    document.getElementById("result").innerHTML = 0.00;
    document.getElementById("total").innerHTML = 0.00;
    document.getElementById("paidInput").value = 0.00;
    $("#paid").hide();
    $("#change").hide();
    $("#pay").fadeIn(1000);
    $("#process").hide();
    $("#cancel").hide();
}

// Function to check if one of the item(s) has already been added to the cart, if so, increment instead of create new cart item.
function cartContains(name) {
    for(var i = 0; i < cart.length; i++) {
      if(cart[i] === name) {
        return true;
      }
    }
    return false;
}

// Function to increment the total(s)
function incTotal(total) {
    var t = document.getElementById("total").innerHTML;
    t = parseFloat(t) + parseFloat(total);
    document.getElementById("total").innerHTML = parseFloat(t).toFixed(2);

    var paid = $('#paidInput').val() == '' ? '___' : $('#paidInput').val();
    var total = document.getElementById("total").innerHTML;
    var result = (total - paid) * -1;
    $('#result').html(parseFloat(result).toFixed(2));
}

/* PAYMENT FUNCTIONS */
// Function to (ACTUALLY) process the payment
function process() {
    var time = getTime();
    var userName = document.getElementById("user").innerHTML;
    var totalOwed = parseFloat(document.getElementById("total").innerHTML);
    var totalPaid = document.getElementById("paidInput").value;
    var tendered = parseFloat(document.getElementById("result").innerHTML);

    if(totalOwed == 0 || cart[0] == null) {
        $('#empty-cart').fadeIn(1000);
        $('#empty-cart').fadeOut(3000);
    } else if(tendered >= 0) {
        var table = document.getElementById("trans");
        var count = table.rows.length;
        var row = table.insertRow(count);
        row.className = "success";

        var stamp = row.insertCell(0);
        var user = row.insertCell(1);
        var total = row.insertCell(2);
        var paid = row.insertCell(3);
        var change = row.insertCell(4);

        stamp.innerHTML = time;
        user.innerHTML = userName;
        total.innerHTML = "$" + parseFloat(totalOwed).toFixed(2);
        paid.innerHTML = "$" + parseFloat(totalPaid).toFixed(2);
        change.innerHTML = "$" + parseFloat(tendered).toFixed(2);

        clearAll();
        $('#trans-complete').fadeIn(1000);
        $('#trans-complete').fadeOut(3000);
    } else {
        var paidInput = document.getElementById("paidInput")
        while(paidInput.value < totalOwed) {
          paidInput.value = prompt("Payment amount must be greater than or equal to: " + totalOwed, totalOwed);
        }
        changeInput();
    }
}

/* DISPLAY FUNCTIONS */
// Function to display the payment process
function pay() {
    $("#paid").fadeIn(1000);
    $("#change").fadeIn(1000);
    $("#pay").hide();
    $("#process").fadeIn(1000);
    $("#cancel").fadeIn(1000);
}

function changeInput() {
    var paid = $('#paidInput').val() == '' ? '___' : $('#paidInput').val();
    var total = document.getElementById("total").innerHTML;
    var result = (total - paid) * -1;
    $('#result').html(parseFloat(result).toFixed(2));
}

// Function to change to cart page
function openCart() {
    var cartButton = document.getElementById("cart-button");
    var transButton = document.getElementById("trans-button");
    cartButton.className = "active";
    transButton.className = "";

    $("#trans-content").hide();
    $("#bottom").fadeIn();
    $("#cart-content").fadeIn();
}

// Function to change to transaction page
function openTrans() {
    var cartButton = document.getElementById("cart-button");
    var transButton = document.getElementById("trans-button");
    cartButton.className = "";
    transButton.className = "active";

    $("#cart-content").hide();
    $("#bottom").hide();
    $("#trans-content").fadeIn();
}

/* TIME FUNCTIONS */
// Function to add 0(s) to minutes and seconds, if necessary.
function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

// Function to (recursively) change the time displayed
function startTime() {
    var x = getTime();
    document.getElementById("time").innerHTML = x;
    t = setTimeout(function () {
        startTime()
    }, 500);
}

// Function to get current time.
function getTime() {
  var today = new Date();
  var mon = today.getMonth() + 1;
  var d = today.getDate();
  var y = today.getFullYear();
  var h = today.getHours();
  var p = "AM";
  if(h === 0) {
    h = 12;
  } else if(h >= 12) {
    p = "PM";
    h = (h != 12) ? (h - 12) : 12;
  }
  var m = today.getMinutes();
  var s = today.getSeconds();
  // add a zero in front of numbers<10
  m = checkTime(m);
  s = checkTime(s);
  var time = mon + "/" + d + "/" + y + " " + h + ":" + m + ":" + s + " " + p;
  return time;
}
