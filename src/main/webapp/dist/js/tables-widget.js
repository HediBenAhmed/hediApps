angular.module('tablewdg', []).directive(
		'tablewdg',
		function() {
			return {
				restrict : 'E',
				scope : {
					nameTable : '=name',
					typeTable : '=type',
					datasTable : '=datas'

				},
				templateUrl : 'partials/tables-widget.html',
				link : function link(scope, element, attrs) {

					scope.$watch('datasTable', function(newDatasChart) {

						var randomScalingFactor = function() {
							return (Math.random() > 0.5 ? 1.0 : -1.0)
									* Math.round(Math.random() * 100);
						}

						var data = {
							labels : [ "January", "February", "March", "April",
									"May", "June", "July" ],
							datasets : [
									{
										data : [ randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor() ],
									},
									{

										data : [ randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor() ],
									},
									{

										data : [ randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor() ]
									} ]
						};
						
						scope.headers = data.labels;
						scope.lignes = data.datasets;
					});
				}
			};
		});
