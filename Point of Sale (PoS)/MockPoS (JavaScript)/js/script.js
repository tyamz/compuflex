var paySetting;
var userDisplay;
var itemList = [];
var priceList = [];
var name = ["Tommy","Thor","Iron Man","Spider-Man","Captain America","Daredevil"];

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

    updateSettings();

    // Possible Product Lists

    // Setting Products
    $.ajaxSetup({beforeSend: function(xhr){
      if (xhr.overrideMimeType)
      {
        xhr.overrideMimeType("application/json");
      }
    }
    });
    $.getJSON("products/Nuts.json", function(data) {
        $.each(data.items, function(index, entry){
          itemList.push(this.name);
          priceList.push(this.price);
        });
    });

    var drop = document.getElementById('userDropMenu');
    for(var i = 0; i < name.length; i++) {
      var user = name[i];
      var entry = document.createElement('li');
      entry.innerHTML = '<a href="#" onclick="setUser(\'' + user + '\')">' + user + '</a>';
      drop.appendChild(entry);
    }

    $('#payWindowSettings input[name="popUp"]').click(function() {
      paySetting = this.value;
    })
    $('#userDisplaySettings input[name = "userDisplay"]').click(function() {
          userDisplay = this.value;
          changeUserDisplay();
    })

    drawTextAndResize("Tommy");
});

// Update page and window settings
function updateSettings() {
    paySetting = document.querySelector('#payWindowSettings input[name = "popUp"]:checked').value;
    userDisplay = document.querySelector('#userDisplaySettings input[name = "userDisplay"]:checked').value;
    changeUserDisplay();
}

/* USER FUNCTIONS */
// Function to Change the User
function changeUser() {
    var choice = Math.floor(Math.random() * name.length);
    document.getElementById("user").innerHTML = name[choice];
    document.getElementById("userBtn").innerHTML = name[choice];
    document.getElementById("userDrop").innerHTML = name[choice];
    drawTextAndResize(name[choice]);
}

function setUser(name) {
    document.getElementById("user").innerHTML = name;
    document.getElementById("userBtn").innerHTML = name;
    document.getElementById("userDrop").innerHTML = name;
    drawTextAndResize(name);
}

/* CART FUNCTIONS */
// Function to add item(s) to the cart.
function add() {
    "use strict";
    var choice = Math.floor(Math.random() * itemList.length);
    var table = document.getElementById("cart");

    if(table.rows.length > 0) {
      $('#pay').prop('disabled', false);
    }

    if(cartContains(itemList[choice])) {
        var row = document.getElementById(itemList[choice]);
        // Get Old Info
        var i = row.rowIndex;
        var q = row.cells[3].innerHTML;
        table.deleteRow(i);
        q++;
    } else {
        var i = table.rows.length;
        q = 1;
        cart.push(itemList[choice]);
    }
    var row = table.insertRow(i);
    row.className = "active";
    row.id = itemList[choice];

    var checkbox = row.insertCell(0);
    var description = row.insertCell(1);
    var price = row.insertCell(2);
    var quantity = row.insertCell(3);
    var total = row.insertCell(4);

    checkbox.innerHTML = '<input type="checkbox" name="chk"/>';
    description.innerHTML = itemList[choice];
    price.innerHTML = priceList[choice];
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

        if(table.rows.length == 1) {
          $('#addBtn').prop('disabled', false);
          $('#pay').prop('disabled', true);
          $('#process').hide();
          $('#cancel').hide();
          $('#pay').fadeIn(1000);
        }
}

// Function to clear ALL items from the cart
function clearAll() {
    cart = [];
    var table = document.getElementById("cart");

    for(var i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }

    document.getElementById("required").innerHTML = 0.00;
    document.getElementById("total").innerHTML = 0.00;
    document.getElementById("requiredWindow").innerHTML = 0.00;
    document.getElementById("totalWindow").innerHTML = 0.00;
    if(paySetting == 0) {
      document.getElementById("paidInput").value = "0.00";
    } else {
      document.getElementById("paidInputWindow").value = "0.00";
    }
    $("#paid").hide();
    $("#change").hide();
    $("#process").hide();
    $("#cancel").hide();
    $('#addBtn').prop('disabled', false);
    $('#pay').prop('disabled', true);
    $('#pay').fadeIn(1000);
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
    document.getElementById("totalWindow").innerHTML = parseFloat(t).toFixed(2);

    var paid = $('#paidInput').val() == '' ? '___' : $('#paidInput').val();
    var total = document.getElementById("total").innerHTML;
    var required = total - paid;
    $('#required').html(parseFloat(required).toFixed(2));
    $('#requiredWindow').html(parseFloat(required).toFixed(2));
}

/* PAYMENT FUNCTIONS */
// Function to (ACTUALLY) process the payment
function process() {
    var time = getTime();
    var userName = document.getElementById("user").innerHTML;
    var totalOwed = parseFloat(document.getElementById("total").innerHTML);
    var tenderedAmt = (paySetting == 0) ? document.getElementById("paidInput").value : document.getElementById("paidInputWindow").value;
    var required = parseFloat(document.getElementById("required").innerHTML);

    if(totalOwed == 0 || cart[0] == null) {
        $('#empty-cart').fadeIn(1000);
        $('#empty-cart').fadeOut(3000);
    } else {
        var table = document.getElementById("trans");
        var count = table.rows.length;
        var row = table.insertRow(count);
        row.className = "success";

        var stamp = row.insertCell(0);
        var user = row.insertCell(1);
        var total = row.insertCell(2);
        var tendered = row.insertCell(3);
        var change = row.insertCell(4);

        stamp.innerHTML = time;
        user.innerHTML = userName;
        total.innerHTML = "$" + parseFloat(totalOwed).toFixed(2);
        tendered.innerHTML = "$" + parseFloat(tenderedAmt).toFixed(2);
        change.innerHTML = "$" + parseFloat(required).toFixed(2);

        clearAll();
        $('#trans-complete').fadeIn(1000);
        $('#trans-complete').fadeOut(3000);
    }
}

/* DISPLAY FUNCTIONS */
// Function to display the payment process
function pay() {
  if(paySetting == 0) {
      $("#paid").fadeIn(1000);
      $("#change").fadeIn(1000);
      $("#pay").hide();
      $("#cancel").fadeIn(1000);
      $("#process").fadeIn(1000);
      $('#addBtn').prop('disabled', true);
  } else {
      $("#payWindow").modal();
  }
}

// Function to cancel the transaction process
function cancel() {
    var table = document.getElementById("cart");
    $("#paid").hide();
    $("#change").hide();
    $('#pay').fadeIn(1000);
    $("#cancel").hide();
    $('#addBtn').prop('disabled', false);
    $('#process').hide();
    if(table.rows.length > 0) {
      $('#pay').prop('disabled', false);
    }
}

// Function to change input values
function changeInput() {
    var paid;
    if(paySetting == 0) {
      paid = $('#paidInput').val() == '' ? '___' : $('#paidInput').val();
    } else {
      paid = $('#paidInputWindow').val() == '' ? '___' : $('#paidInputWindow').val();
    }
    var total = document.getElementById("total").innerHTML;
    var required = total - paid;
    $('#required').html(parseFloat(required).toFixed(2));
    $('#requiredWindow').html(parseFloat(required).toFixed(2));
    if(total != 0 && required <= 0) {
      if(paySetting == 0) {
        $('#process').prop('disabled', false);
      } else {
        $('#prcBtn').prop('disabled', false);
      }
    } else {
      $('#process').prop('disabled', true);
      $('#prcBtn').prop('disabled', true);
    }
}

// Function to change the way the username is displayed
function changeUserDisplay() {
  switch(userDisplay) {
    case "0":
      $('#userLbl').show();
      $('#userBtn').hide();
      $('#userDropDown').hide();
      $('#userImgHolder').hide();
      break;
    case "1":
      $('#userBtn').show();
      $('#userLbl').hide();
      $('#userDropDown').hide();
      $('#userImgHolder').hide();
      break;
    case "2":
      $('#userDropDown').css('display', 'inline-block');
      $('#userBtn').hide();
      $('#userLbl').hide();
      $('#userImgHolder').hide();
      break;
    case "3":
        $('#userImgHolder').show();
        $('#userDropDown').hide();
        $('#userBtn').hide();
        $('#userLbl').hide();
        break;
    default:
      $('#userLbl').show();
      $('#userBtn').hide();
      $('#userDropDown').hide();
      break;
  }
}

// Draw text as an image;
function drawTextAndResize(text) {
  var font = "Verdana";
  var fontSize = 14;
  var ctx = document.getElementById('textCanvas').getContext('2d'), imageElem = document.getElementById('userImg')
  ctx.font = fontSize + 'px '+ font;
  ctx.canvas.width = ctx.measureText(text).width;
  ctx.canvas.height = fontSize*1.3;
  ctx.font = fontSize + 'px '+ font;
  ctx.fillStyle = "#fff";
  ctx.fillText(text, 0,ctx.canvas.height/1.3);
  imageElem.src = ctx.canvas.toDataURL();
}

// Function to open settings window
function openSettings() {
    $('#settings').modal();
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
