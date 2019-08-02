# ReboundLayout
越界回弹效果的Layout

![image](https://github.com/yoyoyaobin/ReboundLayout/blob/master/app/src/main/assets/1.gif)

## 配置
1.在project的build.gradle里配置
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2.在app的build.gradle里配置
```
implementation 'com.github.yoyoyaobin:ReboundLayout:1.0.0'
```

## 使用方式1(普通布局)
```
<com.hyb.rebound.ReboundLayout>
  <你的layout布局>
  </你的layout布局>
  </com.hyb.rebound.ReboundLayout>
```

## 使用方式2（需要scrollview效果的布局）
```
<com.hyb.rebound.ReboundScrollView>
  <com.hyb.rebound.ReboundLayout>
    <你的layout布局>
    </你的layout布局>
  </com.hyb.rebound.ReboundLayout>
</com.hyb.rebound.ReboundScrollView>
```

## 注意
需要包住的是一个layout布局，如果是散装控件无效哦

 ## LICENSE
详见[LICENSE](https://github.com/yoyoyaobin/ReboundLayout/blob/master/LICENSE)
