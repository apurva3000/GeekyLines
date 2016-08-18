(function () {
    'use strict';

    angular
        .module('GeekyLinesApp')
        .controller('TestController', TestController);

    TestController.$inject = ['$rootScope', '$location', '$http'];
    function TestController($rootScope, $location, $http) {
        var test = this;
      
        test.connect = function(){
        	var data = {'username': test.username, 'password': test.password}
        	$http({
  				method: 'POST',
  				url: '/check/form',
  				data: data
		}).then(
     		   function(response) {
     			   console.log(response);
     		   },
     		   function(error) {
     			   console.log(error);
     		   });	
        }
        
        test.worker = function(){
        	//var data = {'username': test.username, 'password': test.password}
        	$http({
  				method: 'POST',
  				url: '/check/form',
  				//data: data
		}).then(
     		   function(response) {
     			   console.log(response);
     		   },
     		   function(error) {
     			   console.log(error);
     		   });	
        } 
    }

})();