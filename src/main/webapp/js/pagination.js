$(document).ready(function() {
    var first = document.getElementsByClassName("first")[0];
    first.getElementsByClassName("page-link")[0].innerHTML = "«";

    var prev = document.getElementsByClassName("prev")[0];
    prev.getElementsByClassName("page-link")[0].innerHTML = "‹";

    var next = document.getElementsByClassName("next")[0];
    next.getElementsByClassName("page-link")[0].innerHTML = "›";

    var last = document.getElementsByClassName("last")[0];
    last.getElementsByClassName("page-link")[0].innerHTML = "»";

    try {
        var first1 = document.getElementsByClassName("first")[1];
        first1.getElementsByClassName("page-link")[0].innerHTML = "«";

        var prev1 = document.getElementsByClassName("prev")[1];
        prev1.getElementsByClassName("page-link")[0].innerHTML = "‹";

        var next1 = document.getElementsByClassName("next")[1];
        next1.getElementsByClassName("page-link")[0].innerHTML = "›";

        var last1 = document.getElementsByClassName("last")[1];
        last1.getElementsByClassName("page-link")[0].innerHTML = "»";
    }
    catch(err) {
    }
});