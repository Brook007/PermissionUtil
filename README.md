# PermissionUtil

## 概述
[![](https://jitpack.io/v/Brook007/PermissionUtil.svg)](https://github.com/Brook007/PermissionUtil)
[![](https://img.shields.io/badge/Platform-Android-brightgreen.svg)](https://github.com/Brook007/PermissionUtil)
[![](https://img.shields.io/badge/API_Live-14+-brightgreen.svg)](https://github.com/Brook007/PermissionUtil)
[![](https://img.shields.io/badge/License-Apache_2-brightgreen.svg)](https://github.com/Brook007/PermissionUtil/blob/master/LICENSE)
[![](https://img.shields.io/badge/Author-Brook007-orange.svg)](https://github.com/Brook007)

Android 权限请求库，一句代码实现权限请求

## 引入依赖
### Gradle方式--适合Android Studio用户
在根项目的build.gradle中添加下面的代码
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

然后在需要使用的模块的build.gradle中添加以下代码
```groovy
dependencies {
    implementation 'com.github.Brook007:PermissionUtil:1.0.1'
}
```

## 使用

仅需下面的代码就能实现权限申请
```java
PermissionUtil.with(this).permission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new PermissionUtil.PermissionCallback() {
                    @Override
                    public void onGranted() {
                        // 允许权限
                    }

                    @Override
                    public void onDenied(List<String> permissionList) {
                        // 拒绝权限
                    }
                });
```

## LICENSE

    Copyright (c) 2016-present, Brook007 Contributors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
