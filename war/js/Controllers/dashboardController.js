(function () {
    'use strict';

    angular
        .module('GeekyLinesApp')
        .controller('DashBoardController', DashBoardController);

    DashBoardController.$inject = ['$rootScope', '$http', '$cookieStore', '$location', 'AuthenticationService', 'Restangular', '$uibModal'];
    function DashBoardController($rootScope, $http, $cookieStore, $location, AuthenticationService, Restangular, $uibModal) {
        var dbd = this;
        
        dbd.submitLine = function(){
        	
        	 $rootScope.submitLineModal = $uibModal.open({
        	      animation: true,
        	      templateUrl: 'submitLineModal.html',
        	      controller: 'SubmitLineController',
        	      size: 'md',
        	      resolve: {
        	        //items: function () {
        	        //  return $scope.items;
        	        //}
        	      }
        	    });

        	
        }
        
        dbd.logout = function(){
        	AuthenticationService.Logout();
        	console.log('Logged out');
        	$location.path('/login');
        }
    }
    
    angular
    .module('GeekyLinesApp')
    .controller('SubmitLineController', SubmitLineController);

    SubmitLineController.$inject = ['$rootScope', '$http', '$cookieStore', '$location', 'AuthenticationService', 'Restangular'];
    
    function SubmitLineController($rootScope, $http, $cookieStore, $location, AuthenticationService, Restangular) {
    	
    	var submitLine = Restangular.one('submitLine');
    	var token = $cookieStore.get('globals')['currentUser']['token'];
    	var username = $cookieStore.get('globals')['currentUser']['username'];
    	
    	$rootScope.regex = "[a-z]+,[a-z]+,[a-z]+,[a-z]+";
    	/*
    	var data = {
    			'content': 'Great men said always move forward in life, so let the I in your life become V(we). And now tell me Coffee or Tea?',
    			'username': username,
    			'genre': 'Coffee',
    			'keywords': 'move,coffee,tea,we,cheesy'};
    	var headers = {'authorization': 'bearer ' + token}
    	*/
    	
    	$rootScope.submitForm = function(isValid, line) {

    	    // check to make sure the form is completely valid
    	    if (isValid) {
    	    	
    	    	line.username = username;
    	     
    	    	var submitPromise = submitLine.post("", line);
    	    	
    	    	submitPromise.then(
    	     		   function(response) {
    	         	 		console.log(response)
    	         		},
    	         		function(error) {
    	         		    console.log('error in submitting new line');
    	         		    console.log(error);
    	         		});
    	         		
    	    }
    	}
    	
    	$rootScope.cancel = function() {
    		$rootScope.submitLineModal.close();
    	         		
    	    }	
    }

})();