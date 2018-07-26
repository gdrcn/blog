$(function(){
  var a = {
    UserID : localStorage.getItem( "IDuser" ),
    leadAddress:"http://192.168.43.186:8080/rdc/",

    init:function(){
      $.ajax
    },

    fn1:function(){
      $( "#b_RDClogo" ).click(function(){
        window.location.replace('file:///F:/gittes/rdc/Michelangelo/html/home.html')
      })
    },   
  
  }

  a.fn1();


})