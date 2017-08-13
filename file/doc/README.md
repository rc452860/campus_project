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


## 2017年6月3日18:37:18 数据库编码问题
[MySql修改数据库编码为UTF8](http://blog.csdn.net/qiyuexuelang/article/details/9049985)
```sql
--临时方法
alter database campus_project character set utf8;

SET character_set_client = utf8;  
SET character_set_connection = utf8;  
SET character_set_database = utf8;  
SET character_set_results = utf8;  
SET character_set_server = utf8;  
```

## 界面对照这个写一下
http://element.eleme.io/#/zh-CN/component/form

### 2017年6月18日13:44:26
javascript 反序列化字符串时时间类型为String的问题
```javascript
{
    "date": "2016-04-26T18:09:16Z"
}

const body = `{
    "date": "2016-04-26T18:09:16Z"
}`;

const obj = JSON.parse(body);

const { date } = obj;
console.log(typeof date);
// "string"

const dateFormat = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}Z$/;

function reviver(key, value) {
    if (typeof value === "string" && dateFormat.test(value)) {
        return new Date(value);
    }
    
    return value;
}

const text = '{ "date": "2016-04-26T18:09:16Z" }';
const obj = JSON.parse(text, reviver);

console.log(typeof obj.date);
// "object"
```

### 2017年6月22日14:56:20
[如何添加静态资源](http://www.baeldung.com/spring-mvc-static-resources)
嵌入的tomcat调节上传资源大小
修改配置文件
```yml
spring:
	http:
		multipart:
            max-file-size: 10MB
            max-request-size: 10MB
```



https://spring.io/blog/2015/01/20/the-resource-server-angular-js-and-spring-security-part-iii