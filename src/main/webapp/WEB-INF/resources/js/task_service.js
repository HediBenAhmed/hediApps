'use strict';
 
App.factory('TaskService', ['$http', '$q', function($http, $q){
    return {
         
            fetchAllTasks: function() {
                    return $http.get(host + '/rest/task/all')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching tasks');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            createTask: function(task){
                    return $http.post(host + '/rest/task/create', task)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating task');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            updateTask: function(task){
                    return $http.put(host + '/rest/task/update', task)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            deleteTask: function(id){
                    return $http.delete(host + '/rest/task/delete/' + id)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting');
                                        return $q.reject(errResponse);
                                    }
                            );
            }
         
    };
 
}]);