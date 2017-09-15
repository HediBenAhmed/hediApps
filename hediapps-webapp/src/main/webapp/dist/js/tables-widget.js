angular.module('tablewdg', []).directive('tablewdg', function() {
	return {
		restrict : 'E',
		scope : {
			nameTable : '=name',
			typeTable : '=type',
			datasTable : '=datas'

		},
		templateUrl : 'partials/tables-widget.html',
		link : function link(scope, element, attrs) {

			scope.$watch('datasTable', function(newDatasTable) {

				if (newDatasTable.length > 0) {

					var labels = [];
					for ( var label in newDatasTable[0].values) {
						labels.push(label);
					}

					var newDatasets = [];
					for (var i = 0; i < newDatasTable.length; i++) {

						var dataTable = newDatasTable[i];
						var dataset = {
							data : []
						};

						for (var j = 0; j < labels.length; j++) {
							var label = labels[j];
							dataset.data.push(dataTable.values[label]);
						}

						newDatasets.push(dataset);
					}

					var data = {
						labels : labels,
						datasets : newDatasets
					};

					scope.headers = data.labels;
					scope.lignes = data.datasets;
				}
			});
		}
	};
});
