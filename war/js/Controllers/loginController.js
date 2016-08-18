(function () {
    'use strict';

    angular
        .module('GeekyLinesApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$rootScope', '$location', 'AuthenticationService'];
    function LoginController($rootScope, $location, AuthenticationService) {
        var vm = this;
      
        vm.login = function(){
        	
        	vm.dataLoading = true;
        	AuthenticationService.Login(vm.username, vm.password, function(response){
        		 
        		 if (response.status == 200) {
                     AuthenticationService.SetCredentials(vm.username, response.data.access_token);
                     $location.path('/dashboard');
                     console.log(response);
                     console.log(response.data.access_token);
                 } else {
                	 console.log("Authentication Error");
                	 alert("Authentication error");
                     vm.dataLoading = false;
                 }
             });	
        	
        } 
    }

})();