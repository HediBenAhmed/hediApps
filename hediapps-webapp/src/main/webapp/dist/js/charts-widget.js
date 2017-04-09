angular.module('chartwdg', []).directive(
		'chartwdg',
		function() {
			return {
				restrict : 'E',
				scope : {
					nameChart : '=name',
					typeChart : '=type',
					datasChart : '=datas'

				},
				templateUrl : 'partials/charts-widget.html',
				link : function link(scope, element, attrs) {

					var chartColors = {
						red : 'rgba(255, 99, 132, 0.8)',
						orange : 'rgba(255, 159, 64, 0.8)',
						yellow : 'rgba(255, 205, 86, 0.8)',
						green : 'rgba(75, 192, 192, 0.8)',
						blue : 'rgba(54, 162, 235, 0.8)',
						purple : 'rgba(153, 102, 255, 0.8)',
						grey : 'rgba(231,233,237, 0.8)'
					};

					var randomScalingFactor = function() {
						return (Math.random() > 0.5 ? 1.0 : -1.0)
								* Math.round(Math.random() * 100);
					}

					scope.$watch('datasChart', function(newDatasChart) {
						var data = {
							labels : [ "January", "February", "March", "April",
									"May", "June", "July" ],
							datasets : [
									{
										label : "Unfilled",
										fill : true,
										backgroundColor : chartColors.blue,
										borderColor : chartColors.blue,
										data : [ randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor() ],
									},
									{
										label : "Dashed",
										fill : false,
										backgroundColor : chartColors.green,
										borderColor : chartColors.green,
										borderDash : [ 5, 5 ],
										data : [ randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor() ],
									},
									{
										label : "Filled",
										backgroundColor : chartColors.red,
										borderColor : chartColors.red,
										data : [ randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor(),
												randomScalingFactor() ],
										fill : true,
									} ]
						};

						var options = {
							responsive : true,
							title : {
								display : false,
								text : 'Chart.js Chart'
							},
							tooltips : {
								mode : 'index',
								intersect : false,
							},
							hover : {
								mode : 'nearest',
								intersect : true
							},
							scales : {
								xAxes : [ {
									display : true,
									scaleLabel : {
										display : true,
										labelString : 'Month'
									}
								} ],
								yAxes : [ {
									display : true,
									scaleLabel : {
										display : true,
										labelString : 'Value'
									}
								} ]
							}
						};

						var ctx = element.find("canvas")[0].getContext("2d");

						new Chart(ctx, {
							type : scope.typeChart,
							data : data,
							options : options
						});
					});
				}
			};
		});
