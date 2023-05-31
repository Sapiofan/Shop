// Get the modal
var reg = document.getElementById('registration');
var log = document.getElementById('login');
var likes = document.getElementById('likes');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == reg) {
    reg.style.display = "none";
  }
  if (event.target == log) {
    log.style.display = "none";
  }
  if (event.target == likes) {
    likes.style.display = "none";
  }
//   if (event.target == nav) {
// 	navigation.className = 'navigation';
// 	nav.className = 'nav';
//   }
}

'use strict';

var search = document.getElementById('search'), 
    searchWrapper = document.getElementById('search-wrapper'), 
    closeIcon = document.getElementById('close-icon');
    searchSign = document.getElementById('search-sign');

// Input focus
search.onfocus = function() {
  searchWrapper.classList.add('search-expanded');
  this.addEventListener('transitionend', function() {
    closeIcon.style.display = 'block';
    search.style.paddingRight = '35px'
  });
}

// Input blur
search.onblur = function() {
  
  searchWrapper.classList.remove('search-expanded');
  closeIcon.classList.add('closing');
  
  this.addEventListener('transitionend', function() {
    search.style.paddingRight = '14px'
    closeIcon.classList.remove('closing');
    closeIcon.style.display = 'none';
  });
  
}

// Close
closeIcon.onclick = function() {
  
  this.classList.add('closing');
  
  document.activeElement.blur();
  
  setTimeout(function() {
    closeIcon.classList.remove('closing');
  }, 1000);
  
}


$(document).ready(function($) {
	// Declare the body variable
	var $body = $("body");

	// Function that shows and hides the sidebar cart
	$(".cart-button, .close-button, #sidebar-cart-curtain").click(function(e) {
		e.preventDefault();
		
		// Add the show-sidebar-cart class to the body tag
		$body.toggleClass("show-sidebar-cart");

		// Check if the sidebar curtain is visible
		if ($("#sidebar-cart-curtain").is(":visible")) {
			// Hide the curtain
			$("#sidebar-cart-curtain").fadeOut(500);
		} else {
			// Show the curtain
			$("#sidebar-cart-curtain").fadeIn(500);
		}
	});
	
	// Function that adds or subtracts quantity when a 
	// plus or minus button is clicked
	$body.on('click', '.plus-button, .minus-button', function () {
		// Get quanitity input values
		var qty = $(this).closest('.qty').find('.qty-input');
		var val = parseFloat(qty.val());
		var max = parseFloat(qty.attr('max'));
		var min = parseFloat(qty.attr('min'));
		var step = parseFloat(qty.attr('step'));

		// Check which button is clicked
		if ($(this).is('.plus-button')) {
			// Increase the value
			qty.val(val + step);
		} else {
			// Check if minimum button is clicked and that value is 
			// >= to the minimum required
			if (min && min >= val) {
				// Do nothing because value is the minimum required
				qty.val(min);
			} else if (val > 0) {
				// Subtract the value
				qty.val(val - step);
			}
		}
	});
});

$(document).ready(function($) {
	// Declare the body variable
	var $body = $("body");

	// Function that shows and hides the sidebar cart
	$(".mobile-icon, #menu-curtain").click(function(e) {
		e.preventDefault();
		
		// Add the show-sidebar-cart class to the body tag
		$body.toggleClass("show-menu");

		// Check if the sidebar curtain is visible
		if ($("#menu-curtain").is(":visible")) {
			// Hide the curtain
			$("#menu-curtain").fadeOut(500);
		} else {
			// Show the curtain
			$("#menu-curtain").fadeIn(500);
		}
	});
});

var coll = document.getElementsByClassName("collapsible");
var i;

function scale(){
	var w = document.documentElement.clientWidth;
	if(w <= 600){
		for (i = 0; i < coll.length; i++) {
			coll[i].addEventListener("click", function() {
			  this.classList.toggle("active-faq");
			  var content = this.nextElementSibling;
			  if (content.style.display === "block") {
				content.style.display = "none";
			  } else {
				content.style.display = "block";
			  }
			});
		}
	}
}
window.addEventListener("resize", scale);
    
scale();


// function focusCat(){
// 	var cat = document.getElementById('menu-category');
// 	cat.style.maxHeight = "250px";
// 	cat.style.transition = "transition: max-height 0.5s ease-in;";
// }

// function blurCat(){
// 	cat.style.maxHeight = "0px";
// 	cat.style.transition = "transition: max-height 0.5s ease-in;";
// }