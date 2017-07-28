var app = angular.module('admin', ['ngRoute']);
app.run(function () {});
app.config(function ($routeProvider) {
    $routeProvider
            .when('/menu1', {controller: 'menu1', templateUrl: '/JavaCodes/c/admin/menu1'})
            .when('/menu2', {controller: 'menu2', templateUrl: '/JavaCodes/views/admin/menu2.html'})
            .when('/upload', {controller: 'upload', templateUrl: '/JavaCodes/views/admin/upload.html'})
            .when('/parameter', {controller: 'parameter', templateUrl: '/JavaCodes/views/admin/parameter.html'})
            .when('/image', {controller: 'image', templateUrl: '/JavaCodes/views/admin/image.html'})
            .when('/download', {controller: 'download', templateUrl: '/JavaCodes/views/admin/download.html'})
            .otherwise({redirectTo: '/menu1'});
});
app.controller('menu1', function ($scope) {
    console.log('in menu1');
});
app.controller('menu2', function ($scope) {
    console.log('in menu2');
});
app.controller('upload', function ($scope) {
    $scope.singleFileUpload1 = function () {
        var formData = new FormData();
        formData.append('file', $("#input-file")[0].files[0]);
        $.ajax({
            type: "post",
            url: "/JavaCodes/c/admin/singlefileupload1",
            contentType: false, //这个一定要写
            processData: false, //这个也一定要写
            data: formData,
            dataType: 'json', //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
            success: function (d) {
                d == true ? alert('上传成功') : alert(d);
            },
            error: function () {
                alert('http错误');
            }
        });
    };
    $scope.singleFileUpload2 = function () {
        var formData = new FormData();
        formData.append('file', $("#input-file")[0].files[0]);
        $.ajax({
            type: "post",
            url: "/JavaCodes/c/admin/singlefileupload2",
            contentType: false, //这个一定要写
            processData: false, //这个也一定要写
            data: formData,
            dataType: 'json', //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
            success: function (d) {
                d == true ? alert('上传成功') : alert(d);
            },
            error: function () {
                alert('http错误');
            }
        });
    };
    $scope.singleTwoFileUpload1 = function () {
        var formData = new FormData();
        formData.append('file1', $("#input-file1")[0].files[0]);
        formData.append('file2', $("#input-file2")[0].files[0]);
        $.ajax({
            type: "post",
            url: "/JavaCodes/c/admin/singletwofileupload1",
            contentType: false, //这个一定要写
            processData: false, //这个也一定要写
            data: formData,
            dataType: 'json', //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
            success: function (d) {
                d == true ? alert('上传成功') : alert(d);
            },
            error: function () {
                alert('http错误');
            }
        });
    };
    $scope.singleTwoFileUpload2 = function () {
        var formData = new FormData();
        formData.append('file1', $("#input-file1")[0].files[0]);
        formData.append('file2', $("#input-file2")[0].files[0]);
        $.ajax({
            type: "post",
            url: "/JavaCodes/c/admin/singletwofileupload2",
            contentType: false, //这个一定要写
            processData: false, //这个也一定要写
            data: formData,
            dataType: 'json', //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
            success: function (d) {
                d == true ? alert('上传成功') : alert(d);
            },
            error: function () {
                alert('http错误');
            }
        });
    };
    $scope.multipleFileUpload = function () {
        var formData = new FormData();
        for (var i = 0; i < $("#input-files")[0].files.length; i++) {
            formData.append('files', $("#input-files")[0].files[i]);
        }
        $.ajax({
            type: "post",
            url: "/JavaCodes/c/admin/multiplefileupload",
            contentType: false, //这个一定要写
            processData: false, //这个也一定要写
            data: formData,
            dataType: 'json', //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
            success: function (d) {
                d == true ? alert('上传成功') : alert(d);
            },
            error: function () {
                alert('http错误');
            }
        });
    };
});
app.controller('parameter', function ($scope, $http) {
    $scope.simple = function () {
        $http.post('/JavaCodes/c/admin/parametersimplemodel', {
            simple_id: 0,
            simple_name: 'tom'
        }).success(function (d) {
            d == true ? alert('ok') : alert(d);
        }).error(function () {
            alert('http错误');
        });
    };
    $scope.complex1 = function () {
        var model = {simple_id: 0, simple_name: 'tom'};
        var list = [];
        for (var i = 1; i <= 3; i++) {
            list.push({simple_id: i, simple_name: 'tom' + i});
        }
        $http.post('/JavaCodes/c/admin/parametercomplexmodel1', {
            complex_id: 0,
            complex_name: 'jack',
            model: model,
            list: list
        }).success(function (d) {
            d == true ? alert('ok') : alert(d);
        }).error(function () {
            alert('http错误');
        });
    };
    $scope.complex2 = function () {
        var model = [];
        for (var i = 1; i <= 3; i++) {
            model.push({simple_id: i, simple_name: 'tom' + i});
        }
        $http.post('/JavaCodes/c/admin/parametercomplexmodel2', {
            model: model
        }).success(function (d) {
            d == true ? alert('ok') : alert(d);
        }).error(function () {
            alert('http错误');
        });
    };
    $scope.complex3 = function () {
        var model = [];
        for (var i = 1; i <= 3; i++) {
            model.push({
                complex_id: i,
                complex_name: 'jack' + i,
                model: {simple_id: 0, simple_name: 'tom'},
                list: [{simple_id: 1, simple_name: 'tom1'}, {simple_id: 2, simple_name: 'tom2'}]
            });
        }
        $http.post('/JavaCodes/c/admin/parametercomplexmodel3', {
            model: model
        }).success(function (d) {
            d == true ? alert('ok') : alert(d);
        }).error(function () {
            alert('http错误');
        });
    };
});
app.controller('image', function ($scope, $http) {
    function previewImage(fileTag, previewTag) {
        var sizeConfig = {
            REQUIRE_MAX_WIDTH: 360,
            REQUIRE_MAX_HEIGHT: 480,
            ZOOM_MAX_WIDTH: 120,
            ZOOM_MAX_HEIGHT: 120,
        };
        var file = document.getElementById(fileTag);
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                var data = e.target.result;
                var image = document.getElementById(previewTag);
                image.onload = function () {
//                    //检查图片尺寸
//                    if (image.width > sizeConfig.REQUIRE_MAX_WIDTH || image.height > sizeConfig.REQUIRE_MAX_HEIGHT) {
//                        alert('图片尺寸超出范围');
//                    }
                    //图片缩放预览
                    var resize = imageZoom(sizeConfig.ZOOM_MAX_WIDTH, sizeConfig.ZOOM_MAX_HEIGHT, image.offsetWidth, image.offsetHeight);
                    image.width = resize.width;
                    image.height = resize.height;
                };
                image.src = data;
            };
            reader.readAsDataURL(file.files[0]);
        } else {
            file.selected();
        }
    }
    function imageZoom(maxWidth, maxHeight, width, height) {
        var param = {
            width: width,
            height: height
        };
        if (width > maxWidth || height > maxHeight) {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;
            if (rateWidth > rateHeight) {
                param.width = maxWidth;
                param.height = Math.round(height / rateWidth);
            } else {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }
        return param;
    }
    function checkImageType(fileTag, extensionNameList) {
        var file = document.getElementById(fileTag);
        var extensionName = file.value.substring(file.value.lastIndexOf('.') + 1, file.value.length);
        if (extensionNameList.indexOf(extensionName.toUpperCase()) == -1) {
            return true;
        } else {
            return false;
        }
    }
    function checkImageSize(fileTag, size) {
        var file = document.getElementById(fileTag);
        if (file.files && file.files[0]) {
            var realSize = file.files[0].size; //这里读到的是字节数
            if (realSize > size) {
                return true;
            } else {
                return false;
            }
        }
    }
    $scope.onFileChange = function () {
        if (checkImageType('input-img', 'GIF,PNG,JPG,JPEG')) {
            alert('请上传' + 'GIF,PNG,JPG,JPEG' + '格式的文件');
            return;
        }
        if (checkImageSize('input-img', 1000000)) {//1M左右
            alert('图片大小超出限制');
            return;
        }
        previewImage('input-img', 'preview-img');
    };
    $scope.upload = function () {
        var formData = new FormData();
        formData.append('file', $("#input-img")[0].files[0]);
        $.ajax({
            type: "post",
            url: "/JavaCodes/c/admin/imageconverter",
            contentType: false, //这个一定要写
            processData: false, //这个也一定要写
            data: formData,
            dataType: 'text', //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
            success: function (d) {
                $('#callback-img').attr('src', d);
            },
            error: function () {
                alert('http错误');
            }
        });
    };
});
app.controller('download', function ($scope) {
    console.log('in download');
});
