app.controller("my", function ($scope,$controller, brandService) {
    $controller("baseController",{$scope:$scope})//继承

    //保存
    $scope.save = function () {
        var name = "add";//如果id有值那么就用updata,如果没有就用add
        if ($scope.entity.id != null) {

            name = "update";

        }
       brandService.save(name,$scope.entity).success(function (response) {

            if (response.success) {

                $scope.reloadList();//刷新
                alert(response.message);
            } else {
                alert(response.message);
            }

        });
    }
    //根据id查询
    $scope.findOne = function (id) {
        brandService.findOne(id).success(function (bra) {

            $scope.entity = bra;

        })
    }
    //删除
    $scope.dele = function () {
        brandService.dele($scope.selectIds).success(function (response) {
            if (response.success) {

                $scope.reloadList();//刷新
                alert(response.message);
            } else {
                alert(response.message);
            }

        })

    }

    $scope.tbBrand={};
    //条件查询
    $scope.search=function (page,rows) {
        brandService.search(page,rows,$scope.tbBrand).success(function (obj) {

            $scope.paginationConf.totalItems=obj.total;
            $scope.list=obj.rows;
        })
    }

});
