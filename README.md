# WeChatNews
采用 MVP 模式构建的微信精选 APP<br>

#### 效果图：<br>
![](https://github.com/linfaimom/WeChatNews/raw/master/screenshots/screenshots.png)

#### 背景：<br>
做这个小项目的初衷是为了总结与实践当时所学知识，诸如各种热门的新框架、新工具等，将其进行一个整合应用以提升自己的 Android 开发技能。而且因为我老爸经常喜欢看微信的文章，那为啥不趁机给他弄一个专门提供微信精选的新闻 APP 呢？

#### 所用工具/技术
* 采用负责网络请求的 Retrofit
* 采用负责处理图片的 Picasso
* 采用 “聚合数据” 所提供的免费 API，发起请求时将返还 JSON 格式的数据
* 采用处理 Object 与 JSON 之间相互转化的框架——GSON
* 采用实现代码低耦合的依赖注入框架 Dagger2
* 采用 MVP 架构模式构建整个 APP

#### 所实现功能
* 对请求成功后所返还的数据做了分页处理，启动时展现 20 条
* 下拉刷新，动画效果采用 Android 提供的原生控件 RefreshLayout

#### 总结
