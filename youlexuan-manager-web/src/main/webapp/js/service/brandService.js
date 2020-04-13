app.service("brandService", function ($http) {
    this.findAll = function () {
        return $http.get("../brand/findAll.do");
    }
    this.findPage = function (page, rows) {

        return $http.post("../brand/findPage.do?page=" + page + "&rows=" + rows);
    }
    this.save = function (name, entity) {
        return $http.post("../brand/" + name + ".do", entity);
    }
    this.findOne = function (id) {
        return $http.get("../brand/findOne.do?id=" + id);
    }
    this.dele = function (selectId) {
        return $http.get("../brand/delete.do?ids=" + selectId);
    }
    this.search = function (page, rows, tbBrand) {
        return $http.post("../brand/search.do?page=" + page + "&rows=" + rows, tbBrand);
    }
    this.selectOptionList=function () {

        return $http.get("../brand/selectOptionList.do")
    }
})