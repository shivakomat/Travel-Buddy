'use strict';

var app =
    angular.module('travelBuddyApp', ['auth0', 'angular-storage', 'angular-jwt', 'ngMaterial', 'ui.router']);

app.config(function ($provide, authProvider, $urlRouterProvider, $httpProvider, jwtInterceptorProvider) {
        $urlRouterProvider.otherwise('/');
});

// app.config(function(angularAuth0Provider) {
//     angularAuth0Provider.init({
//         clientID: 'vK4iZYJ5SodMQvzIjRRjLUTfNNZgR7QP',
//         clientSecret:'6fzNArjFKe6GkvoGYyv9kvC8rOHjlLZ4nDHOcv66YVOqg3IjRfEDGeXXlYM-nPzv',
//         domain: 'shiva-komatreddy.auth0.com'
//     });
// });
