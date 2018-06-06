var input = document.getElementsByTagName("input");
var events = ["keydown", "keyup", "keypressed"];
for (i = 0; i < input.length; i++) {
    for (j = 0; j < events.length; j++) {
        input[i].addEventListener(events[j], function (event) {
            var inputs = document.getElementsByTagName("input");
            for (i = 0; i < inputs.length; i++) {
                if (!inputs[i].value) {
                    document.getElementById("submit-btn").disabled = true;
                    return;
                }
            }
            document.getElementById("submit-btn").disabled = false;

            // If ENTER pressed
            if (event.keyCode === 13) {
                document.getElementById("submit-btn").click();
            }
        });
    }
}
