/**
 * Created by OneReverse on 2015/7/22.
 */
"use strict";

function calculate() {
    // look up the input and output elements in the document
    var amount = document.getElementById("amount");
    var apr = document.getElementById("apr");
    var years = document.getElementById("years");
    var zipcode = document.getElementById("zipcode");
    var payment = document.getElementById("payment");
    var total = document.getElementById("total");
    var totalinterest = document.getElementById("totalinterest");

    var principal = parseFloat(amount.value);
    var interest = parseFloat(apr.value) / 100 / 12;
    var payments = parseFloat(years.value) * 12;

    var x = Math.pow(1 + interest, payments);
    var monthly = (principal*x*interest)/(x-1);

    if (isFinite(monthly)) {
        payment.innerHTML = monthly.toFixed(2);
        total.innerHTML = (monthly * payment).toFixed(2);
        totalinterest.innerHTML =((monthly*payments)-principal).toFixed(2);

        save(amount.value, apr.value, years.value, zipcode.value);

        // Advertise: find and display local lenders, but ignore network errors
        try {
            getLenders(amount.value, apr.value, years.value, zipcode.value);
        } catch(e) {}

        chart(principal, interest, monthly, payments);
    }
    else {
        payment.innerHTML = ""; // Erase the content of these elements
        total.innerHTML = "";
        totalinterest.innerHTML = "";
        chart();
    }
}

function save(amount, apr, years, zipcode) {
    if (window.localStorage) {  // Only do this if the browser supports it
        localStorage.loan_amount = amount;
        localStorage.loan_apr = apr;
        localStorage.loan_years = years;
        localStorage.loan_zipcode = zipcode;
    }
}

window.onload = function() {
    // If the browser supports localStorage and we have some stored data
    if (window.localStorage && localStorage.loan_amount) {
        document.getElementById("amount").value = localStorage.loan_amount;
        document.getElementById("apr").value = localStorage.loan_apr;
        document.getElementById("years").value = localStorage.loan_years;
        document.getElementById("zipcode").value = localStorage.loan_zipcode;
    }
}

// Pass the user's input to a server-side script which can (in theory) return
// a list of links to local lenders interested in making loans. This example
// does not actually include a working implementation of such a lender-finding
// service. But if the service existed, this function would work with it.
function getLenders(amount, apr, years, zipcode) {
    // If the browser does not support the XMLHttpRequest object, do nothing
    if (!window.XMLHttpRequest)
        return;

    // Find the element to display the list of lenders in
    var ad = document.getElementById("lenders");
    if (!ad)
        return; // Quit if no spot for output

    // Encode the user's input as query parameters in a URL
    var url = "getLenders.php" +                // Service url plus
        "?amt=" + encodeURIComponent(amount) +  // user data in query string
        "&apr=" + encodeURIComponent(apr) +
        "&yrs=" + encodeURIComponent(years) +
        "&zip=" + encodeURIComponent(zipcode);

    // Fench the contents of that URL using the XMLHttpRequest object
    var req = new XMLHttpRequest();
    req.open("GET", url);
    req.send(null);

    // Before returning , register an event handler function that will be called
    // at some later time when the HTTP server's response arrives. This kind of
    // asynchronous programming is very common in client-side JavaScript
    req.onreadystatechange = function () {
        if (req.readyState == 4 && req.status == 200) {
            // If we get here, we got a complete valid Http response
            var response = req.responseText;    // HTTP response as a string
            var lenders = JSON.parse(response); // Parse it to a JS array

            // Convert the array of lender objects to a string of HTML
            var list = "";
            for (var i = 0; i < lenders.length; i++) {
                list += "<li><a href='" + lenders[i].url + "'>" +
                        lenders[i].name + "</a>";
            }

            // Display the HTML in the element from above.
            ad.innerHTML = "<ul>" + list + "</ul>";
        }
    }
}

// Chart monthly loan balance, interest and equity in an HTML <canvas> element.
// If called with no arguments then just erase any previously drawn chart.
function chart(principal, interest, monthly, payments) {
    var graph = document.getElementById("graph");   // Get the canvas tag
    graph.width = graph.width;  // Magic to clear and reset the canvas elements

    // If we're called with no arguments, or if this browser does not support
    // graphics in a <canvas> element, then just return now.
    if (arguments.length == 0 || !graph.getContext)
        return;

    // Get the "context" object for the <canvas> that defines the drawing API
    var g = graph.getContext("2d");
    var width = graph.width, height = graph.height; // Get canvas size

    // These functions convert payment numbers and dollar amounts to pixels
    function paymentToX(n) {
        return n * width/payments;
    }

    function amountToY(a) {
        return height-(a * height/(monthly*payments*1.05));
    }

    // Payments are a straight line from (0, 0) to (payments, monthly*payments)
    g.moveTo(paymentToX(0), amountToY(0));    // Start at lower left
    g.lineTo(paymentToX(payments), amountToY(monthly*payments));    // Draw to upper right
    g.lineTo(paymentToX(payments), amountToY(0));    // Down to lower right
    g.closePath();
    g.fillStyle = "#f88";
    g.fill();
    g.font = "bold 12px sans-serif";
    g.fillText("Total Interest Payments", 20, 20);  // Draw text in legend

    // Cumulative equity is non-linear and trickier to chart
    var equity = 0;
    g.beginPath();
    g.moveTo(paymentToX(0), amountToY(0));
    for (var p = 1; p <= payments; p++) {
        // For each payment, figure out how much is interest
        var thisMonthsInterest = (principal-equity)*interest;
        equity += (monthly - thisMonthsInterest);
        g.lineTo(paymentToX(p), amountToY(equity));
    }
    g.lineTo(paymentToX(payments), amountToY(0));
    g.closePath();
    g.fillStyle = "green";
    g.fill();
    g.fillText("Total Equity", 20, 35);

    // Loop again, as above, but chart loan balance as a thick black line
    var bal = principal;
    g.beginPath();
    g.moveTo(paymentToX(0), amountToY(bal));
    for (var p = 1; p <= payments; p++) {
        var thisMonthInterest = bal*interest;
        bal -= (monthly - thisMonthInterest);   // The rest goes to equity
        g.lineTo(paymentToX(p), amountToY(bal));
    }
    g.lineWidth = 3;
    g.stroke();
    g.fillStyle = "black";
    g.fillText("Loan Balance", 20, 50);

    // Now make yearly tick marks and year numbers on X axis
    g.textAlign="center";
    var y = amountToY(0);
    for (var year = 1; year*12 <= payments; year++) {
        var x = paymentToX(year*12);
        g.fillRect(x-0.5, y-3, 1, 3);
        if (year == 1)
            g.fillText("Year", x, y-5);
        if (year % 5 == 0 && year*12 !== payments)
            g.fillText(String(year), x, y-5);
    }

    // Mark payment amounts along the right edge
    g.textAlign = "right";
    g.textBaseline = "middle";
    var ticks = [monthly*payments, principal];
    var rightEdge = paymentToX(payments);
    for (var i = 0; i < ticks.length; i++) {
        var y = amountToY(tick[i]);
        g.fillRect(rightEdge-3, y-0,5, 3, 1);
        g.fillText(String(ticks[i].toFixed(0)), rightEdge-5, y);
    }
}