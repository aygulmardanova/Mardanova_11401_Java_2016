$(document).ready(function()
{
    $(".slider").each(function ()
    {
        var obj = $(this);
        $(obj).append("<div class='nav'></div>");

        $(obj).find("li").each(function ()
        {
            $(obj).find(".nav").append("<span rel='"+$(this).index()+"'></span>");
            $(this).addClass("slider"+$(this).index());
        });

        $(obj).find("span").first().addClass("on");
    });
});

function sliderJS (obj, sl) // slider function
{
    var ul = $(sl).find("ul");
    var bl = $(sl).find("li.slider"+obj);
    var step = $(bl).width();
    $(ul).animate({marginLeft: "-"+step*obj}, 500);
}

$(document).on("click", ".slider .nav span", function() // slider click navigate
{
    var sl = $(this).closest(".slider");
    $(sl).find("span").removeClass("on");
    $(this).addClass("on");
    var obj = $(this).attr("rel");
    sliderJS(obj, sl);
    return false;
});



var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-23131825-1']);
_gaq.push(['_trackPageview']);
(function () {
    var ga = document.createElement('script');
    ga.type = 'text/javascript';
    ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(ga, s);
})();



new Image().src = "http://counter.yadro.ru/hit?r" +
    escape(document.referrer) + ((typeof(screen) == "undefined") ? "" :
    ";s" + screen.width + "*" + screen.height + "*" + (screen.colorDepth ?
        screen.colorDepth : screen.pixelDepth)) + ";u" + escape(document.URL) + ";" + Math.random();


(function (d, w, c) {
    (w[c] = w[c] || []).push(function () {
        try {
            w.yaCounter20963398 = new Ya.Metrika({
                id: 20963398,
                webvisor: true,
                clickmap: true,
                trackLinks: true,
                accurateTrackBounce: true,
                trackHash: true
            });
        } catch (e) {
        }
    });
    var n = d.getElementsByTagName("script")[0], s = d.createElement("script"), f = function () {
        n.parentNode.insertBefore(s, n);
    };
    s.type = "text/javascript";
    s.async = true;
    s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";
    if (w.opera == "[object Opera]") {
        d.addEventListener("DOMContentLoaded", f, false);
    } else {
        f();
    }
})(document, window, "yandex_metrika_callbacks");



