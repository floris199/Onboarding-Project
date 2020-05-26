<script>
function showCustomer(str) {
    var xhttp;
    if (str == "") {
        return;
    }
    debugger;
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("myText").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "/OnBoardingProject/DemoServlet?&action="+str, true);
    xhttp.send();
}
</script>