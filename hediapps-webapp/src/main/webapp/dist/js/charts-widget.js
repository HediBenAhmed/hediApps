angular.module('chartwdg', []).directive('chartwdg', function() {
	return {
		restrict : 'E',
		scope : {
			nameChart : '=name',
			typeChart : '=type',
			datasChart : '=datas'
		},
		templateUrl : 'partials/charts-widget.html',
		link : function link(scope, element, attrs) {

			var chartColors = [
			/* red */'rgba(255, 99, 132, 0.8)',
			/* orange */'rgba(255, 159, 64, 0.8)',
			/* yellow */'rgba(255, 205, 86, 0.8)',
			/* green */'rgba(75, 192, 192, 0.8)',
			/* blue */'rgba(54, 162, 235, 0.8)',
			/* purple */'rgba(153, 102, 255, 0.8)',
			/* grey */'rgba(231,233,237, 0.8)' ];

			scope.$watch('datasChart', function(newDatasChart) {

				if (newDatasChart.length > 0) {

					var labels = [];
					for ( var label in newDatasChart[0].values) {
						labels.push(label);
					}

					var newDatasets = [];
					for (var i = 0; i < newDatasChart.length; i++) {
						var color = chartColors[i % chartColors.length];
						var dataChart = newDatasChart[i];
						var dataset = {
							label : dataChart.name,
							fill : true,
							backgroundColor : color,
							borderColor : chartColors.blue,
							data : [],
						};

						for (var j = 0; j < labels.length; j++) {
							var label = labels[j];
							dataset.data.push(dataChart.values[label]);
						}

						newDatasets.push(dataset);
					}

					var data = {
						labels : labels,
						datasets : newDatasets
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
									display : false,
									labelString : 'X'
								}
							} ],
							yAxes : [ {
								display : true,
								scaleLabel : {
									display : false,
									labelString : 'Y'
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
				}
			});
		}
	};
});
