(function () {
    'use strict';
 
    angular
        .module('GeekyLinesApp')
        .factory('AuthenticationService', AuthenticationService);
 
    AuthenticationService.$inject = ['$http', '$cookieStore', '$rootScope', 'Restangular'];
    function AuthenticationService($http, $cookieStore, $rootScope, Restangular) {
        
       	var service = {};
 
        service.Login = Login;
        service.Logout = Logout;
        service.SetCredentials = SetCredentials;
        service.ClearCredentials = ClearCredentials;
 
        return service;
 
        function Login(username, password, callback) {
         
        	var data = {'grant_type':'password',
        				'client_id':'restapp',
        				'client_secret':'restapp',
        				'username':username,
        				'password':password
        				};
        	var config = {
        			 params: data,
        			 headers : {'Accept' : 'application/json'}
        			};
        	var token = $http({
        		  				method: 'GET',
        		  				url: '/oauth/token',
        		  				params: data
        				});
           token.then(
        		   function(response) {
            	 		callback(response);
            		},
            		function(error) {
            		    callback(error);
        	});
 
        }
 
        function Logout() {
        	this.ClearCredentials();
        }
        function SetCredentials(username, token) {
 
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    token: token
                }
            };
 
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
            $cookieStore.put('globals', $rootScope.globals);
        }
 
        function ClearCredentials() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic';
        }
    }
 
})();