function changeUser() {
    var name = ["Tommy","Thor","Iron Man","Spider-Man","Captain America","Daredevil"];
    var choice = Math.floor(Math.random() * name.length);
    document.getElementById("user").innerHTML = name[choice];
}
function add() {
    "use strict";
    var n = ["Macadamia", "Hazelnut", "Almond", "Peanut", "Walnut"];
    var p = [2.00, 1.90, 1.31, 0.85, 1.12];
    var choice = Math.floor(Math.random() * 5);

    var table = document.getElementById("cart");
    if(cartContains(n[choice])) {
        var row = document.getElementById(n[choice]);
        // Get Old Info
        var i = row.rowIndex;
        var q = row.cells[3].innerHTML;
        table.deleteRow(i);

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
        q++;
        quantity.innerHTML = q;
        var t = price.innerHTML * quantity.innerHTML;
        total.innerHTML = parseFloat(t).toFixed(2);
        incTotal(price.innerHTML);
    } else {
        var count = table.rows.length;
        var row = table.insertRow(count);
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
        quantity.innerHTML = 1;
        var t = price.innerHTML * quantity.innerHTML;
        total.innerHTML = parseFloat(t).toFixed(2);

        cart.push(n[choice]);
        incTotal(price.innerHTML);
    }
}

function cartContains(name) {
    for(var i = 0; i < cart.length; i++) {
      if(cart[i] === name) {
        return true;
      }
    }
    return false;
}

function incTotal(total) {
    var t = document.getElementById("total").innerHTML;
    t = parseFloat(t) + parseFloat(total);
    document.getElementById("total").innerHTML = parseFloat(t).toFixed(2);

    var paid = $('#paidInput').val() == '' ? '___' : $('#paidInput').val();
    var total = document.getElementById("total").innerHTML;
    var result = (total - paid) * -1;
    $('#result').html(parseFloat(result).toFixed(2));
}

function pay() {
    $("#paid").fadeIn(1000);
    $("#change").fadeIn(1000);
    $("#pay").hide();
    $("#process").fadeIn(1000);
}

$( document ).ready(function() {
    startTime();
    clearAll();
    $('.input').each(function() {
        $(this).on('keyup', function() {
            var paid = $('#paidInput').val() == '' ? '___' : $('#paidInput').val();
            var total = document.getElementById("total").innerHTML;
            var result = (total - paid) * -1;
            $('#result').html(parseFloat(result).toFixed(2));
        });
    });

    $('.input').each(function() {
            var paid = $('#paidInput').val() == '' ? '___' : $('#paidInput').val();
            var total = document.getElementById("total").innerHTML;
            var result = (total - paid) * -1;
            $('#result').html(parseFloat(result).toFixed(2));
    })
});

function remove()  {
        var table = document.getElementById("cart");
        var rowCount = table.rows.length;

        for(var i = rowCount; i > 0; i--) {
            var row = table.rows[i];
            if(row != null) {
              var chkbox = row.cells[0].getElementsByTagName('input')[0];
              var name = row.cells[1].innerHTML;
              if('checkbox' == chkbox.type && true == chkbox.checked) {
                  table.deleteRow(i);
                  var index = cart.indexOf(name);
                  cart.splice(index, 1);
              }
            }
        }
}

function clearAll() {
    cart = [];
    var table = document.getElementById("cart");

    for(var i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }

    document.getElementById("result").innerHTML = 0.00;
    document.getElementById("total").innerHTML = 0.00;
    document.getElementById("paidInput").value = 0.00;
}

function openCart() {
    var cartButton = document.getElementById("cart-button");
    var transButton = document.getElementById("trans-button");
    cartButton.className = "active";
    transButton.className = "";

    $("#trans-content").hide();
    $("#bottom").fadeIn();
    $("#cart-content").fadeIn();
}

function openTrans() {
    var cartButton = document.getElementById("cart-button");
    var transButton = document.getElementById("trans-button");
    cartButton.className = "";
    transButton.className = "active";

    $("#cart-content").hide();
    $("#bottom").hide();
    $("#trans-content").fadeIn();
}

function process() {
    var time = getTime();
    var userName = document.getElementById("user").innerHTML;
    var totalOwed = document.getElementById("total").innerHTML;
    var totalPaid = document.getElementById("paidInput").value;
    var tendered = parseFloat(document.getElementById("result").innerHTML);

    if(tendered >= 0) {
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
        $("#moneyOwed").modal('toggle');
    }
}

function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function startTime() {
    var x = getTime();
    document.getElementById("time").innerHTML = x;
    t = setTimeout(function () {
        startTime()
    }, 500);
}

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
