(function () {
    'use strict';
 
    angular
        .module('GeekyLinesApp', ['ngRoute', 'ngCookies', 'restangular', 'ui.bootstrap'])
        .config(config)
        .run(run);
 
    config.$inject = ['$routeProvider', '$locationProvider', 'RestangularProvider'];
    function config($routeProvider, $locationProvider, RestangularProvider) {
    	
    	var newBaseUrl = "";
    	if (window.location.hostname == "localhost") {
    	newBaseUrl = "http://localhost:8888" + "/api/lines";
    	} 
    	RestangularProvider.setBaseUrl(newBaseUrl);
    	
    	
        $routeProvider
 
        	.when('/', {
        		redirectTo: '/test'
        	})
            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login.html',
                controllerAs: 'vm'
            })
            
            .when('/dashboard', {
                controller: 'DashBoardController',
                templateUrl: 'dashboard.html',
                controllerAs: 'dbd'
            })
            
             .when('/test', {
                controller: 'TestController',
                templateUrl: 'test.html',
                controllerAs: 'test'
            })
 
            .otherwise({ redirectTo: '/' });
    }
 
    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
       // after page refresh
    	$rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.currentUser.token; // jshint ignore:line
        }
        //move to login if logged out
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var loginPage = $.inArray($location.path(), ['/login', '/test']) === -1;
            var isLoggedIn = $rootScope.globals.currentUser;
            if (loginPage && !isLoggedIn) {
                $location.path('/login');
            }
        });
    }
 
})();