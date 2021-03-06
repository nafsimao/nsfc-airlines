var app = angular.module('NSFCAirlinesApp', ['ui.bootstrap']);

app
   .controller('NSFCAirlinesController', function($uibModal, $scope, $http) {
	   
	   
	   $scope.open = function (selectedFlight) {
		    var modalInstance = $uibModal.open({
		      animation: true,
		      ariaLabelledBy: 'modal-title',
		      ariaDescribedBy: 'modal-body',
		      template: '<div class="modal-header"><h3 class="modal-title" id="modal-title">Detalhes do vôo</h3></div><form class="form-horizontal"><div class="modal-body" id="modal-body"><div class="form-group"><label class="col-sm-6 control-label">Nome do piloto:</label><div class="row"><div class="col-sm-6">{{selectedFlight.pilotName}}</div></div></div><div class="form-group"><label class="col-sm-6 control-label">Inscrição do avião:</label><div class="row"><div class="col-sm-6">{{selectedFlight.airplane.inscription}}</div></div></div><div class="form-group"><label class="col-sm-6 control-label">Modelo do avião:</label><div class="row"><div class="col-sm-6">{{selectedFlight.airplane.model}}</div></div></div></div></form><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="ok()">OK</button></div>',
		      controller: 'FlightsDetailsModalController',
		      size: 10,
		      resolve: {
		        selectedFlight: function () {
		          return selectedFlight;
		        }
		      }
		    })}; 
		    
       $scope.flights = [];

       $scope.searchFlights = function(){
    	   if(!!$scope.filter && $scope.filter.status == ""){
    		   delete $scope.filter.status;
    	   }
  	$http({
    	   method: 'GET',
    	   url: 'flights',
    	   params: {filter: $scope.filter}
    	 }).then(function successCallback(response) {
    		 $scope.flights = response.data;
    	   }, function errorCallback(response) {
    		   console.log("Erro na request de flights!");
    	   });
       }

       $scope.searchFlights();

       $http({
    	   method: 'GET',
    	   url: 'airports'
    	 }).then(function successCallback(response) {
    		 $scope.airports = response.data;
    	   }, function errorCallback(response) {
    		   console.log("Erro na request de aeroportos!");
    	   });
       $http({
    	   method: 'GET',
    	   url: 'status'
    	 }).then(function successCallback(response) {
    		 $scope.status = response.data;
    	   }, function errorCallback(response) {
    		   console.log("Erro na request de status!");
    	   });

})

app
.controller('FlightsDetailsModalController', function($scope, $uibModalInstance, selectedFlight) {
		$scope.selectedFlight = selectedFlight;
		$scope.ok = function () {
		    $uibModalInstance.close($scope.selectedFlight);
		};
	});

/*

app
.controller('FlightsDetailsModalController', function($scope, $uibModalInstance, selectedFlight) {
	 $scope.selectedFlight = selectedFlight;
	 $scope.ok = function () {
		    $uibModalInstance.close($scope.selectedFlight);
     };
	});*/
