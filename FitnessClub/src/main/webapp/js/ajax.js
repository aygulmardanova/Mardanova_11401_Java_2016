/**
 * Created by aygulmardanova on 15.05.16.
 */

f = function () {
    jQuery.ajax({
        type: 'POST',
        url: "/ajax/sort",
        dataType: "json",
        data: {"sort": $("#categorySort").val()},
        success: function (response_data) {
            if (response_data.length != 0) {
                jQuery("#res").html("Sort results: ");
                console.log('Some text');
                for (var i = 0; i < response_data.length; i++) {
                    if (response_data[i].photo != null && response_data[i].photo != '') {
                        jQuery("#res").append("<div class=\"trainers_div\">" +
                            "<div class=\"trainer_one_line\">" +
                            "<img src=\"/images/users/" + response_data[i].photo + "\" width=\"200\" height=\"200\">" +
                            "<p class=\"trainer_href\">" +
                            "<a href=\"/trainers\">" + response_data[i].name + " " + response_data[i].surname + "" +
                            "</a>  " +
                            "</p> " +
                            "</div> " +
                            "</div>");
                    } else {
                        jQuery("#res").append("<div class=\"trainers_div\">" +
                            "<div class=\"trainer_one_line\">" +
                            "<img src=\"/images/no_photo.jpg\" width=\"200\" height=\"200\">" +
                            "<p class=\"trainer_href\">" +
                            "<a href=\"/trainers\">" + response_data[i].name + " " + response_data[i].surname + "" +
                            "</a>  " +
                            "</p> " +
                            "</div> " +
                            "</div>");
                    }
                    jQuery("#res").append("<hr size=1px color=\"#ccc\">");
                }

            } else {
                jQuery("#res").html("No results");
            }
        }
    })
}
