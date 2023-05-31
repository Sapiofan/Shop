
// Get all sections that have an ID defined
// const sections = document.querySelectorAll("section[id]");

// // Add an event listener listening for scroll
// window.addEventListener("scroll", navHighlighter);

// function navHighlighter() {
  
//   // Get current scroll position
//   let scrollY = window.pageYOffset;
  
//   // Now we loop through sections to get height, top and ID values for each
//   sections.forEach(current => {
//     const sectionHeight = current.offsetHeight;
  
//     const sectionTop = (current.getBoundingClientRect().top + window.pageYOffset) - 50;
//     sectionId = current.getAttribute("id");
    
//     /*
//     - If our current scroll position enters the space where current section on screen is, add .active class to corresponding navigation link, else remove it
//     - To know which link needs an active class, we use sectionId variable we are getting while looping through sections as an selector
//     */
//     if (
//       scrollY > sectionTop &&
//       scrollY <= sectionTop + sectionHeight
//     ){
//       document.querySelector(".chars-nav a[href*=" + sectionId + "]").classList.add("active");
//     } else {
//       document.querySelector(".chars-nav a[href*=" + sectionId + "]").classList.remove("active");
//     }
//   });
// }

// window.onload = function(){
//   var head = document.getElementById('main');

//   head.addEventListener('scroll', function(e) {
      
//     if (this.scrollTop > 147) {
//       head.classList.add("fix");
//     } else {
//       head.classList.remove("fix");
//     }
    
//   });
// }


const sections = document.querySelectorAll("section");
const navLi = document.querySelectorAll(".chars-nav a");

window.onscroll = function() {fixHeader()};

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
    if (window.pageYOffset >= sectionTop ) {
      current = section.getAttribute("id"); }
  });

  navLi.forEach((a) => {
    a.classList.remove("active-navigation");
    if (a.classList.contains(current)) {
      a.classList.add("active-navigation");
    }
  });
}
