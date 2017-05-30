# <center>相关文档</center>

Jpa是一个类似于MyBatis的框架

[Jpa文档](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)

[Jpa如何操作数据库](https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/)

##这个问题调试了两天
使用Spring security oauth2千万不要自己重载WebSecurityConfigurerAdapter虽然看到别人写过没问题。
但是这个项目中如果重载会报错匿名用户登陆没有权限访问


- [Spring Security OAuth2, which decides security?](https://stackoverflow.com/questions/28537181/spring-security-oauth2-which-decides-security)
- [Spring boot oauth2 server 实战(maven)](http://www.jianshu.com/p/dd1b0983594c)

这个里边会涉及到 Filter顺序问题WebSecurityConfigurerAdapter的默认顺序为0，
OAuth2的默认顺序为3

OAuth里边包含用户、客户端、令牌。都有对应的DetailsService。可以自己重载。DetialsService
和普通Service没什么区别就是要实现一个接口提取对应的数据。按照接口需要的数据
相应的给出就可以了。里边包含JDBC的简写。也就是内部帮你封装好了。其实最后也是
调用的DetailService。有空给出调试路径。


- [JPA动态查询](http://blog.csdn.net/anxpp/article/details/51996472)

- [Spring security 单点登陆(sso)](https://spring.io/blog/2015/02/03/sso-with-oauth2-angular-js-and-spring-security-part-v)


目前可以登陆了。
登陆方式参见SpringBoot-seed


## 2017年5月31日00:07:49
添加跨域请求Filter
SimpleCorsFilter，如果修改端口和连接后请对应修改SimpleCorsFilter中的连接
``` java
response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
```
SecurityContextHolder这个东西可以拿到当前令牌的信息。就是当前登录的主体。
添加通用specification这个东西挺好用的用法明天写。
改版自
- [REST Query Language with Spring Data JPA Specifications](http://www.baeldung.com/rest-api-search-language-spring-data-specifications)
- [REST Example Project with Spring Security](https://github.com/eugenp/tutorials/tree/master/spring-security-rest-full)
可以开展相关业务逻辑了。做成两个端。学生一个端教师一个端。分开做
应当添加班级院系表方便下发权限,具体设计待沟通