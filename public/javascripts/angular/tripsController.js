app.controller('tripsController', function($http, $window) {
    var tripController = this;

    tripController.userTrips = [];
    tripController.trip = {};

    tripController.getUserTrips = function (userId) {
        getAllUsersTrips(userId);
    };

    tripController.newTrip = function (userId) {
        createATrip(userId);
    };

    tripController.getTripBy = function (userId, tripId) {
        getUserTripByUserIdAndTripId(userId, tripId);
    };

   function getUserTripByUserIdAndTripId(userId, tripId) {
      $http({
           method: 'GET',
           url: '/user-trips/'+ userId
       }).then(function mySuccess (response) {
          console.log(response.data);
          for (var i = 0; i < response.data.trips.length; i++) {
              if(response.data.trips[i].id === tripId) {
                  tripController.trip  = response.data.trips[i];
              }
          }
       }, function myError (response) {
           console.log(response.statusText)
       });
   }

    function getAllUsersTrips (userId) {
        $http({
            method: 'GET',
            url: '/user-trips/'+ userId
        }).then(function mySuccess (response) {
            console.log(response.data);
            tripController.userTrips = response.data.trips;
           }, function myError (response) {
            console.log(response.statusText)
           });
    }

    function createATrip(userId) {
        tripController.formData.userId = userId;
        console.log(tripController.formData);
        $http({
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            url: '/trips',
            data: JSON.stringify(tripController.formData),
        }).then(function mySuccess() {
            console.log("successfully created");
            $window.location.href = "http://" + $window.location.host + "/profile-page"
        }, function myError() {
            console.log("ERROR creating a trip");
        })
    }
});
