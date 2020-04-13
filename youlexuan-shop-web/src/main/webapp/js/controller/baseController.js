app.controller("baseController", function ($scope) {
    $scope.reloadList = function () {

        //分页控件配置     因为html是由上至下加载的,所以调用在上
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);


        /*
                        $scope.findPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
        */
    }


    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    };
    //分页
    $scope.findPage = function (page, rows) {
        brandService.findPage(page,rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数

            }
        )
    }

//删除
    $scope.selectIds = [];//定义一个数组
    //$event是angularjs内置对象有方法可以确认是勾选或取消
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            //如果别选中,则增加到数组
            $scope.selectIds.push(id);
        } else {
            //获取他出现的位置
            var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx, 1);//删除

        }

    }

    /*全选*/
    $scope.all=function ($event) {
        if($event.target.checked){
            //如果被选中,增加全部到数组
            $scope.selectIds=[];
            $scope.isAllSelect=true;
            $scope.isSelect=true;
            angular.forEach($scope.list,function (value,key) {
                $scope.selectIds.push(value.id);
            });
        }else{
            $scope.isAllSelect=false;
            $scope.isSelect=false;
            $scope.selectIds=[];

        }
    }
    //key-value格式转换
    $scope.jsonToString=function (jsonStriing,key) {
        var json =JSON.parse(jsonStriing);//将json字符串转为json对象
        var value= "";
        for(var i=0;i<json.length;i++){
            if(i>0){
                value+=",";
            }
            /*json获取value的方法就是json[key];*/
            value+=json[i][key];
        }
      return value;

    }
    $scope.sa=function (jsons) {
    var ss = JSON.parse(jsons);
    return ss;
    }
});
