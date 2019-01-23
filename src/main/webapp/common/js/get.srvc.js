(function()
		{
	var module = angular.module("htracker");
	
	// service use to get data from the database
	module.service("getterService", getterServiceCtor);
	
	function getterServiceCtor($http,loginService) {
		var self = this;
		
//		variable holding the data sent in response from server
		this.dataArray=[];
		this.headerArray=[];

//		function to put in dataArray the data received
		this.setData = function(somePromise){
			self.dataArray.length=0;
			for(var i=0; i<somePromise.data.length;i++)
				{
				self.dataArray[i]=somePromise.data[i];
				}
			
		}
		
//		used to search in dataArray the record of have the provided id
		this.position = function(id){
			for (var i = 0; i < this.dataArray.length; i++) {
				if(this.dataArray[i].id==id) return i;
			}
			return -1;
		}
		
//		used to remove a record from dataArray provided is id
		this.removeItem = function(itemID){
						this.x = this.position(itemID);
						if(this.x>-1){
							this.dataArray.splice(this.x,1);}					
					}
		
//		used to send ajax GET request
		this.getDataArray = function(){
		$http.get("http://localhost:8080/webTracker/"+this.type.clientType+"/"+this.type.targetType).then(
				function(promise){
					if(promise.status=='205'){loginService.reaplyForLogin()}
					else if(promise.status=='202'){
						self.setData(promise);}
		},
				function(promise,error, status){loginService.reaplyForLogin();
		})
		}
		
	}
		})();
