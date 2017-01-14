"use strict";
angular.module('myApp', []).controller('namesCtrl', function ($scope) {
    $scope.names = [
        {
            name: 'Raj',
            country: 'India'
        },
        {
            name: 'Hege',
            country: 'Sweden'
        },
        {
            name: 'Kai',
            country: 'Denmark'
        }
    ];
});