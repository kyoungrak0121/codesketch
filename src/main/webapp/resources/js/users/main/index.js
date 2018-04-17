$(document).ready(function() {
  'use strict'; // Start of use strict

  // Smooth scrolling using jQuery easing
  $("a.js-scroll-trigger[href*='#']:not([href='#'])").click(function() {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
      if (target.length) {
        $('html, body').animate({
          scrollTop: (target.offset().top - 54)
        }, 1000, 'easeInOutExpo');
        return false;
      }
    }
  });
  
  // Closes responsive menu when a scroll trigger link is clicked
  $('.js-scroll-trigger').click(function() {
    $('.navbar-collapse').collapse('hide');
  });

  // fullPage Scroll
  $('#fullpage').fullpage({
	  
	  //Scrolling
	  'css3':false,
	  'verticalCentered': false,
      'scrollOverflow': true,
      'normalScrollElements': '.modal' , 
     
      //Design
      'fixedElements': '#header',
      
      //Method
	  onLeave: function(index, nextIndex, direction){
		  // header nav effect
		  if (nextIndex === 1 ) {
			  $('#mainNav').removeClass('navbar-shrink');
		  } else {
			  $('#mainNav').addClass('navbar-shrink');
		  }
		  $('.navbar-nav .nav-item a').removeClass('active').eq(nextIndex-1).addClass('active');
	  } 
  });
  
  
  //
  $('.carousel').carousel({
	  interval: 3000,
  });
  
  
  $('.navbar-nav').on('click','.nav-item a',function(e){
	  var moveItem = $(this).parent().index();
	  $.fn.fullpage.moveTo(moveItem + 1);
  });
  
  // Hide navbar when modals trigger
  $('.portfolio-modal').on('show.bs.modal', function(e) {
    $('.navbar').addClass('d-none');
  });
  $('.portfolio-modal').on('hidden.bs.modal', function(e) {
    $('.navbar').removeClass('d-none');
  });

}); // End of use strict
