angular
		.module('graph', [])
		.directive(
				'graph',
				function() {
					return {
						restrict : 'E',
						scope : {
							datasGraph : '=datas'
						},
						templateUrl : 'graph.html',
						link : function link(scope, element, attrs) {

							scope
									.$watch(
											'datasGraph',
											function(newDatasGraph) {
												if (newDatasGraph
														&& newDatasGraph.length > 0) {

													self.colors = [
															"rgba(60, 118, 61, 1)",
															"rgba(169, 68, 66, 1)",
															"rgba(49, 112, 143, 1)",
															"rgba(120,120,120,1)" ];												
													
													var graph = {
														labels : [],
														datasets : []
													};
													
													var labels = [];
													
													for (var i = 0; i < scope.datasGraph.length; i++) {

														if(Object.keys(scope.datasGraph[i].values).length > labels.length){
															labels = Object.keys(scope.datasGraph[i].values);
														}
														
														var idColor = i
																% self.colors.length;

														var dataset = {
															label : scope.datasGraph[i].name,
															fillColor : "rgba(0,0,0,0)",
															strokeColor : self.colors[idColor],
															pointColor : self.colors[idColor],
															pointStrokeColor : "#fff",
															pointHighlightFill : "#fff",
															pointHighlightStroke : self.colors[idColor],
															data : scope.datasGraph[i].values
														};

														graph.datasets[i] = dataset;
													}

													graph.labels = labels;
													
													var canvas = angular
															.element(element
																	.find("canvas"))[0];
													new Chart(canvas
															.getContext("2d"))
															.Line(
																	graph,
																	{
																		responsive : true,
																		maintainAspectRatio : false,
																	});

												}
											});
						}
					};
				});
