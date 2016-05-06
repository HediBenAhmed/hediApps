'use strict';
 
App.controller('TaskController', ['$scope', 'TaskService', function($scope, TaskService) {
          var self = this;
          self.task={id:null,name:'',description:'',status:''};
          self.tasks=[];
               
          self.fetchAllTasks = function(){
              TaskService.fetchAllTasks()
                  .then(
                               function(d) {
                                    self.tasks = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Currencies');
                                }
                       );
          };
            
          self.createTask = function(task){
              TaskService.createTask(task)
                      .then(
                      self.fetchAllTasks, 
                              function(errResponse){
                                   console.error('Error while creating Task.');
                              } 
                  );
          };
 
         self.updateTask = function(task){
              TaskService.updateTask(task)
                      .then(
                              self.fetchAllTasks, 
                              function(errResponse){
                                   console.error('Error while updating Task.');
                              } 
                  );
          };
 
         self.deleteTask = function(id){
              TaskService.deleteTask(id)
                      .then(
                              self.fetchAllTasks, 
                              function(errResponse){
                                   console.error('Error while deleting Task.');
                              } 
                  );
          };
 
          self.fetchAllTasks();
 
          self.submit = function() {
              if(self.task.id==null){
                  console.log('Saving New Task', self.task);    
                  self.createTask(self.task);
              }else{
                  self.updateTask(self.task);
                  console.log('Task updated with id ', self.task.id);
              }
              self.reset();
          };
               
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.tasks.length; i++){
                  if(self.tasks[i].id == id) {
                     self.task = angular.copy(self.tasks[i]);
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              for(var i = 0; i < self.tasks.length; i++){//clean form if the task to be deleted is shown there.
                  if(self.tasks[i].id == id) {
                     self.reset();
                     break;
                  }
              }
              self.deleteTask(id);
          };
 
           
          self.reset = function(){
              self.task={id:null,taskname:'',address:'',email:''};
              $scope.myForm.$setPristine(); //reset Form
          };
 
      }]);