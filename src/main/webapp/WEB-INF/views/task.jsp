<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
    <style>
      .taskname.ng-valid {
          background-color: lightgreen;
      }
      .taskname.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .taskname.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
 
      .status.ng-valid {
          background-color: lightgreen;
      }
      .status.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .status.ng-dirty.ng-invalid-status {
          background-color: yellow;
      }
 
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="TaskController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Task Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.task.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.task.name" name="tname" class="taskname form-control input-sm" placeholder="Enter the name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.tname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.tname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.tname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                         
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Description</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.task.description" class="form-control input-sm" placeholder="Enter the description."/>
                              </div>
                          </div>
                      </div>
 
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Status</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.task.status" name="status" class="status form-control input-sm" placeholder="Enter your Email" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.status.$error.required">This is a required field</span>
                                      <span ng-show="myForm.status.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
 
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.task.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Tasks </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Name</th>
                              <th>Description</th>
                              <th>Status</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="t in ctrl.tasks">
                              <td><span ng-bind="t.id"></span></td>
                              <td><span ng-bind="t.name"></span></td>
                              <td><span ng-bind="t.description"></span></td>
                              <td><span ng-bind="t.status"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(t.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(t.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
       
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/resources/js/app.js' />"></script>
      <script src="<c:url value='/resources/js/task_service.js' />"></script>
      <script src="<c:url value='/resources/js/task_controller.js' />"></script>
  </body>
</html>