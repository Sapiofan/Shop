const sections = document.querySelectorAll("section");
const navLi = document.querySelectorAll(".chars-nav a");

window.onscroll = function () {
    fixHeader()
};

var navbar = document.getElementById("chars-header");
var sticky = navbar.offsetTop;
var but = document.getElementById("header-buy");
var stickyBut = but.offsetTop;

function fixHeader() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("fix");
    } else {
        navbar.classList.remove("fix");
    }
    if (window.pageYOffset >= 250) {
        navbar.classList.add("fix-but");
    } else {
        navbar.classList.remove("fix-but");
    }

    var current = "";

    sections.forEach((section) => {
        const sectionTop = section.offsetTop;
        if (window.pageYOffset >= sectionTop) {
            current = section.getAttribute("id");
        }
    });

    navLi.forEach((a) => {
        a.classList.remove("active-navigation");
        if (a.classList.contains(current)) {
            a.classList.add("active-navigation");
        }
    });
}
