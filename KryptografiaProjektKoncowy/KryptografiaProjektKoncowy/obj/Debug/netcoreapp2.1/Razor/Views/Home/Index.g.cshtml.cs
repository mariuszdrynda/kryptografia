#pragma checksum "E:\Repozytoria\C#\kryptografiaprojekt\KryptografiaProjektKoncowy\KryptografiaProjektKoncowy\Views\Home\Index.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "6a257cb45ffee161f9a609a717967bed97185b5f"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Home_Index), @"mvc.1.0.view", @"/Views/Home/Index.cshtml")]
[assembly:global::Microsoft.AspNetCore.Mvc.Razor.Compilation.RazorViewAttribute(@"/Views/Home/Index.cshtml", typeof(AspNetCore.Views_Home_Index))]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#line 1 "E:\Repozytoria\C#\kryptografiaprojekt\KryptografiaProjektKoncowy\KryptografiaProjektKoncowy\Views\_ViewImports.cshtml"
using KryptografiaProjektKoncowy;

#line default
#line hidden
#line 2 "E:\Repozytoria\C#\kryptografiaprojekt\KryptografiaProjektKoncowy\KryptografiaProjektKoncowy\Views\_ViewImports.cshtml"
using KryptografiaProjektKoncowy.Models;

#line default
#line hidden
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"6a257cb45ffee161f9a609a717967bed97185b5f", @"/Views/Home/Index.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"12a9fe47962317a3994026e404701f0ce39ed54b", @"/Views/_ViewImports.cshtml")]
    public class Views_Home_Index : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<dynamic>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
#line 1 "E:\Repozytoria\C#\kryptografiaprojekt\KryptografiaProjektKoncowy\KryptografiaProjektKoncowy\Views\Home\Index.cshtml"
  
    ViewData["Title"] = "Strona główna";

#line default
#line hidden
            BeginContext(49, 7689, true);
            WriteLiteral(@"
<center>
    <div id=""logging_page"">
        <button type=""submit"" onclick=""on_login()"">Login</button>
    </div>
    <div id=""ask_question_page"" hidden>
        <h1>Zadaj pytanie</h1>
        <h2>Albo poczekaj aż ktoś inny zada</h2>
        <textarea id=""question_here"" rows=""4"" cols=""50""></textarea><br />
        <button type=""submit"" onclick=""on_asking()"">Ask question</button>
    </div>
    <div id=""answer_question_page"" hidden>
        <h1>Question:</h1><br />
        <p id=""question_here""></p>
        <input type=""radio"" id=""answer_yes"" name=""ans"" value=""1""> YES<br>
        <input type=""radio"" id=""answer_no"" name=""ans"" value=""0""> NO<br>
        <input type=""submit"" onclick=""send_answer()"" value=""Submit"">
    </div>
    <div id=""answer"" hidden>
        <p id=""answer_here""></p>
    </div>
    <button id=""again"" onclick=""again()"">AGAIN</button>
</center>

<script>
    var q = 11;
    var g = 6;
    var id = 0;
    var g_y = 0;
    var x = 1;
    var question;
    var trigger1");
            WriteLiteral(@"_interval;
    var trigger2_interval;
    var trigger3_interval;
    var token;
    function on_login() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                id = this.responseText;
                console.log(id);
                var el = document.getElementById('logging_page');
                el.remove();
                document.getElementById(""ask_question_page"").hidden = false;
                trigger1_interval = setInterval(trigger, 1000);
            }
        }
        xhttp.open(""GET"", ""https://localhost:44316/api/logging"", true);
        xhttp.send();
    }
    function potega(g, x) {
        var h = g;
        for (i = 1; i < x; i++) {
            h = h * g % q;
        }
        return h;
    }
    function policz_gy(wszystkie_gx) {
        //console.log(wszystkie_gx[0].gx + "" "" + wszystkie_gx[1].gx);
        var mn1 = 1;
        var mn2 = 1;
        f");
            WriteLiteral(@"or (i = 0; i < wszystkie_gx.length; i++) {
            if (wszystkie_gx[i].id < id) {
                mn1 *= wszystkie_gx[i].gx;
            }
            if (wszystkie_gx[i].id > id) {
                mn2 *= wszystkie_gx[i].gx;
            }
        }
        return parseInt(mn1 / mn2);
    }
    function wylicz_final(wszystkie_gcy) {
        var mn = 1;
        for (i = 0; i < wszystkie_gcy.length; i++) {
            mn *= wszystkie_gcy[i].gcy;
        }
        mn = mn % q;
        return mn;
    }
    function trigger() {
        console.log(""TRIGGER"");
        var xhttp = new XMLHttpRequest();
        console.log(""CZEKAM NA PYTANIE"");
        xhttp.onreadystatechange = function () {
            console.log(""ODPOWIEDZ"");
            question = this.responseText;
            if (this.readyState == 4 && this.status == 200 && this.responseText != """") { //KTOŚ ZADAŁ PYTANIE
                clearInterval(trigger1_interval);
                var el = document.getElementById('ask_questi");
            WriteLiteral(@"on_page');
                el.remove();
                x = parseInt(Math.random() * (q - 1)) + 1;
                console.log(""x "" + x);
                var g_x = potega(g, x);
                console.log(""g_x "" + g_x);
                var xhttp2 = new XMLHttpRequest();
                xhttp2.onreadystatechange = function () {
                    console.log(""WYSŁANO G_X"");
                    trigger2_interval = setInterval(trigger2, 1000);//TI2
                }
                xhttp2.open(""GET"", ""https://localhost:44316/api/gx/"" + g_x.toString() + ""/"" + id.toString(), true);
                xhttp2.send(); //wyślij g^x
            }
        };
        xhttp.open(""GET"", ""https://localhost:44316/api/Question/"", true);
        xhttp.send();
    }
    function trigger2() {
        console.log(""TRIGGER2"");
        var xhttp = new XMLHttpRequest();
        //console.log(""CZEKAM NA WSZYSTKIE G^X"");
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this");
            WriteLiteral(@".status == 200 && this.responseText != """") {
                clearInterval(trigger2_interval);//TI2
                console.log(""CLEAR INTERVAL 2"");
                var wszystkie_gx = JSON.parse(this.responseText);
                console.log(wszystkie_gx);
                document.getElementById(""answer_question_page"").hidden = false;
                var q = document.getElementById(""question_here"");
                q.innerHTML = question;
                g_y = policz_gy(wszystkie_gx);
                //console.log(g_y);
            }
        }
        xhttp.open(""GET"", ""https://localhost:44316/api/gx"", true);
        xhttp.send();
    }
    function on_asking() {
        var text = document.getElementById('question_here').value;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            console.log(""WYSŁANO PYTANIE"");
        }
        xhttp.open(""GET"", ""https://localhost:44316/api/Question/"" + text, true);
        xhttp.send();

        con");
            WriteLiteral(@"sole.log(text)
    }
    function send_answer() {
        var g_cy = 0;
        if (document.getElementById('answer_yes').checked) {
            console.log(""ZAZNACZONO TAK"");
            var r = parseInt(Math.random() * (q - 1)) + 1;
            g_cy = potega(g_y, r);
            send_gcy(g_cy);
        }
        else if (document.getElementById('answer_no').checked) {
            console.log(""ZAZNACZONO NIE"");
            g_cy = potega(g_y, x);
            send_gcy(g_cy);
        }
    }
    function send_gcy(g_cy) {
        var el = document.getElementById('answer_question_page');
        el.remove();
        document.getElementById(""answer"").hidden = false;
        document.getElementById(""answer_here"").innerHTML = ""WAITING FOR ANSWER"";
        console.log(""g_cy "" + g_cy);
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            console.log(""WYSŁANO G^(C*Y)"");
            trigger3_interval = setInterval(trigger3, 1000);//TI3
        }");
            WriteLiteral(@"
        var text = ""https://localhost:44316/api/Gcy/"" + g_cy.toString() + ""/"" + id.toString() + ""/"";
        console.log(text);
        xhttp.open(""GET"", text, true);
        xhttp.send();
    }
    function trigger3() {
        console.log(""TRIGGER3"");
        //debugger;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200 && this.responseText != """") {
                console.log(""WSZYSCY ODPOWIEDZIELI"");
                clearInterval(trigger3_interval);//TI3
                var wszystkie_gcy = JSON.parse(this.responseText);
                console.log(wszystkie_gcy);
                var final_answer = wylicz_final(wszystkie_gcy);//WYLICZ ODPOWIEDZ
                var a = document.getElementById(""answer_here"");
                if (final_answer == 1) {
                    a.innerHTML = ""NO"";
                }
                else {
                    a.innerHTML = ""YES"";
                }");
            WriteLiteral(@"
            }
        }
        xhttp.open(""GET"", ""https://localhost:44316/api/Gcy/"", true);
        xhttp.send();
    }
    function again() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                console.log(""AGAIN"");
                location.reload();
            }
        }
        xhttp.open(""GET"", ""https://localhost:44316/api/clear"", true);
        xhttp.send();
    }
</script>
");
            EndContext();
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<dynamic> Html { get; private set; }
    }
}
#pragma warning restore 1591