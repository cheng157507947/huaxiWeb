var hid_type;
function echoSceneTree(id,name,type,group){
	if(type == -1)return; //选择的是场景组
	if(hid_type!=null){
		if(hid_type.value != type){
			if(type == 1){
				this.location = ctx+"/admin/irrigateContorl/realTimeField?id="+id; //田块
			}else if(type == 2){
				this.location = ctx+"/admin/irrigateContorl/realTimePump?id="+id;//泵房
			}else if(type == 3){
				//this.location = "${param.ctx }/op_scene_other_two.action";//水质
			}else if(type == 4){
				//this.location = ctx+"/admin/irrigateContorl/realTimeWeather?id="+id;//气象
			}else{
				execute(id,name,type,group);
			}
		}else{
			execute(id,name,type,group);
		}
	}else{
		if(type == 1){
			this.location = ctx+"/admin/irrigateContorl/realTimeField?id="+id; //田块
		}else if(type == 2){
			this.location = ctx+"/admin/irrigateContorl/realTimePump?id="+id;  //泵房
		}else if(type == 3){
			//this.location = ctx+"/op_scene_other_two.action";//水质
		}else{
			//this.location = ctx+"/admin/irrigateContorl/realTimeWeather?id="+id;//气象
		}
	}	
}

window.onload = function(){ 
	hid_type = document.getElementById("hid_type");
	if(hid_type != null){
		return;
	}
	
	//默认加载	
	//alert(this.parent.rightFrame.scene_tree.selectedNode);
	var sceneTree = window.parent.frames['leftFrame'].zTree;
	var curr_node_id = "";
	if(sceneTree.getSelectedNodes().length == 0){
		var pnode = sceneTree.getNodeByTId("2")
		var pnode1 = sceneTree.getNodeByParam("pId", "2", pnode);
		var node = sceneTree.getNodeByParam("pId", pnode1.id, pnode1);
		sceneTree.selectNode(node);
		curr_node_id = node.id;
	}else{
		var node = sceneTree.getSelectedNodes();
		curr_node_id = node[0].id;
	}
	var scene_id = curr_node_id;
	$.getJSON(ctx+"/admin/findSceneById?id="+scene_id,{
		random:Math.random()
	},function(scene){
		echoSceneTree(scene.id,scene.name,scene.type,scene.group);
	});		
}