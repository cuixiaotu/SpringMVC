

[TOC]

# 一.SpringMVC简介

## 1.什么是MVC

MVC是一种软件架构的思想，将软件按照模型、视图、控制器划分

M：Model 模型层，指工程中的JavaBean，作用是处理数据，分为两类：

- 一类为实体类Bean 专门存储业务数据 如Student、User等

- 一类为业务处理Bean：指Service或Dao对象，专门用于处理业务逻辑

V：View 视图层，工程中的html或jsp等页面，作用是与用户进行交互

C：Controller 控制层，工程中的servlet。作用接收请求和响应浏览器

MVC的工作流程：

用户通过视图层发送请求到服务器，在服务器中请求被Controller接收，Controller调相对应的Model层处理请求，处理完毕将结果返回到Controller，Controller再根据请求的结果找到相应的View视图，渲染数据后最终相应到浏览器。



## 2.什么是SpringMVC

SpringMVC是Spring的一个后续产品，是Spring的一个子项目。

SpringMVC是Spring为表述层开发提供的一整套完备的解决方案。在表述层框架历经Strust、WebWork、Struct2等诸多产品历史更迭之后，目前业界普遍选择了SpringMVC作为Java EE项目表述层开发的首选方案。

> 注：三层架构分为表述层（表示层）、业务逻辑层、数据访问层，表述层标识前台页面和后台servlet

## 3.SpringMVC的特点

+ Spring家族原生产品，与IOC容器等基础设施无缝对接
+ 基于原生的servelet，通过了功能强大的前端控制器DispatcherServlet,对请求和响应进行统一处理
+ 表述层各细分领域需要解决的问题全方面覆盖，提供全面解决方案
+ 代码清新简洁，大幅度提升开发效率
+ 内部组件化程度高，可插拔式组件即插即用，想要什么功能配置相应组件即可
+ 性能卓越 尤其适合现代大型、超大型互联网要求



#  二. HelloWorld

## 1.开发环境

IDE: idea 2020.2

构建工具：maven3.8.6

服务器：tomcat8

Spring版本：5.3.2

## 2.创建maven工程

### a>添加web模块

### b>打包方式: war

### c>引入依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.20</version>
    </dependency>

    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.11</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope><!--已经由Tomcat提供 打包时候回忽略该依赖-->
    </dependency>

    <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf-spring5</artifactId>
        <version>3.0.15.RELEASE</version>
    </dependency>
</dependencies>
```

注：由于 Maven 的传递性，我们不必将所有需要的包全部配置依赖，而是配置最顶端的依赖，其他靠传递性导入。

![image-20220623181848434](images/readme/image-20220623181848434.png)

## 3.配置web.xml

注册SpringMVC的前端控制器DispatchServlet

### a>默认配置方式

此配置作用下，SpringMVC的配置文件位于WEB-INF下，默认名称为<servlet-name>-serlet.xml,例如以下配置所对应的SpringMVC的配置文件位于WEB-INF下，文件名为springMVC-servlet.xml

```xml
 <!--配置springMVC的前端控制器 对请求做统一处理-->
<!-- 此配置作用下 SpringMVC的配置文件默认位于WEB-INF下，默认名称为<servlet-name>-servlet.html
以下配置所对应springMVC的配置文件位于WEB-INF下，文件名为springMVC-servlet.xml -->
<servlet>
    <servlet-name>SpringMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!--配置springMVC的配置文件名称和位置 -->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springMVC.xml</param-value><!--classpath => java/resources -->
    </init-param>
    <!--将前端控制器DispatcherServlet初始化提前至服务器启动时-->
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>SpringMVC</servlet-name>
    <!-- 设置springMVC的核心控制器所处理的请求的请求方式
          所匹配的请求可以是/login或.html或.js或.css的请求方式
          但是不能匹配.jsp请求路径的请求
          /  不包括
          /* 包括jsp
     -->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

> \<url-pattern>标签中使用/和/*的区别：
>
> /所匹配的请求可以是/login或.html或.js或.css方式的请求路径，但是/不能匹配.jsp请求路径的请求
>
> 因此就可以避免在访问jsp页面时，该请求被DispatcherServlet处理，从而找不到相应的页面
>
> /*则能够匹配所有请求，例如在使用过滤器时，若需要对所有请求进行过滤，就需要使用/\*的写法


## 4.创建请求控制器

由于前端控制器对浏览器发送的请求进行了统一的处理，但是具体的请求有不同的处理过程，因此需要创建处理具体请求的类，即请求控制器

请求控制器中的每一个处理请求的方法成为控制器方法

因为SpringMVC的控制器由一个POJO（普通的java类）担任，因此需要通过@Controller注解将其标识为一个控制层组件，交给Spring的IoC容器管理，此时SpringMVC才能识别控制器的存在

```java
@Controller
public class HelloController{

}
```

## 5.创建springMVC的配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
 ">
    <!--扫描组件 别忘了增加xsi:schemaLocation。。。 -->
    <context:component-scan base-package="com.xiaotu.mvc.Controller" />

    <!--配置Thymeleaf视图解析器-->
    <bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <property name="order" value="1"/>
        <property name="characterEncoding" value="UTF-8"/>
        <property name="templateEngine">
            <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                <property name="templateResolver">
                    <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                        <!--视图前缀-->
                        <property name="prefix" value="/WEB-INF/templates/"/>
                        <!--视图后缀-->
                        <property name="suffix" value=".html"/>
                        <property name="templateMode" value="HTML5"/>
                        <property name="characterEncoding" value="UTF-8"/>
                    </bean>
                </property>
            </bean>
        </property>
    </bean>

</beans>
```

## 6、测试HelloWorld

### a>实现对首页的访问

在请求控制器中创建处理请求的方法

```java
// @RequestMapping注解：处理请求和控制器方法之间的映射关系
// @RequestMapping注解的value属性可以通过请求地址匹配请求，/表示的当前工程的上下文路径
// localhost:8080/springMVC/
@RequestMapping("/")
public String index() {
    //设置视图名称
    return "index";
}
```

### b>通过超链接跳转到指定页面

在主页index.html中设置超链接

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
    <h1>首页</h1>
    <a th:href="@{/hello}">HelloWorld</a><br/>
</body>
</html>
```

在请求控制器中创建处理请求的方法

```java
@RequestMapping("/hello")
public String HelloWorld() {
    return "target";
}
```

## 7、总结

浏览器发送请求，若请求地址符合前端控制器的url-pattern，该请求就会被前端控制器DispatcherServlet处理。前端控制器会读取SpringMVC的核心配置文件，通过扫描组件找到控制器，将请求地址和控制器中@RequestMapping注解的value属性值进行匹配，若匹配成功，该注解所标识的控制器方法就是处理请求的方法。处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行渲染，最终转发到视图所对应页面



## 8、异常记录

- pom里配置了两个spring-webmvc依赖包 （找到多个名为spring_web的片段）
- idea的Tomcat上下文配置和url路径不一致 （404）
- templates目录放错了 不在resource 在webapp下（An error happened during template parsing）



# 三、@RequestMapping注解

## 1、@RequestMapping注解的功能

从注解名称上我们可知，@RequestMapping注解的作用是将请求和处理请求的控制器方法关联起来，建立映射关系。

SpringMVC接收到指定的请求，就会来找到在映射关系中对应的控制器方法来处理这个请求。



## 2、@RequestMapping注解的位置

@RequestMapping标识一个类：设置映射请求的请求路径的初始信息

@RequestMapping标识一个方法：设置映射请求路径的具体信息

```java
@Controller
@RequestMapping("/test")
public class RequestMappingController{
	//此时请求映射路径为 /test/testRequestMapping
	public String testRequestMapping(){
		return "success";
	}
}
```



## 3、@RequestMapping注解的value值

@RequestMapping注解的value属性通过请求的请求地址匹配请求映射

@RequestMapping注解的value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求

@RequestMapping注解的value属性必须设置，至少通过请求地址匹配请求映射

```html
<a th:href="@{/testRequestMapping}">测试@RequestMapping的value属性-->/testRequestMapping </a><br/>
<a th:href="@{/test}">测试@ReuqestMapping的value属性-->/test</a>
```

```java
@RequestMapping(
	value = { "/testRequestMapping", "/test" }
)
public String testRequestMapping(){
	return "success";
}
```



# 4. @RequestMapping注解的method属性

@RequestMapping注解的method属性通过请求的请求方式（get或post）匹配请求映射

@RequestMapping注解的method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配多种请求方式的请求

若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，则浏览器报错405：Request method 'POST' not supported



```html
<a th:href="@{/test}">测试@RequestMapping的value属性-->/test</a><br>
<form th:action="@{/test}" method="post">
    <input type="submit">
</form>
```

```java
@RequestMapping(
        value = {"/testRequestMapping", "/test"},
        method = {RequestMethod.GET, RequestMethod.POST}
)
public String testRequestMapping(){
    return "success";
}
```

> 注：
>
> 1、对于处理指定请求方式的控制器方法，SpringMVC中提供了@RequestMapping的派生注解
>
> 处理get请求的映射-->@GetMapping
>
> 处理post请求的映射-->@PostMapping
>
> 处理put请求的映射-->@PutMapping
>
> 处理delete请求的映射-->@DeleteMapping
>
> 2、常用的请求方式有get，post，put，delete
>
> 但是目前浏览器只支持get和post，若在form表单提交时，为method设置了其他请求方式的字符串（put或delete），则按照默认的请求方式get处理
>
> 若要发送put和delete请求，则需要通过spring提供的过滤器HiddenHttpMethodFilter，用RESTful



# 5.RequestMapping注解的params属性

@RequestMapping注解的params属性通过请求的请求参数匹配请求映射

@RequestMapping注解的params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系

"param"：要求请求映射所匹配的请求必须携带param请求参数

"!param"：要求请求映射所匹配的请求必须不能携带param请求参数

"param=value"：要求请求映射所匹配的请求必须携带param请求参数且param=value

"param!=value"：要求请求映射所匹配的请求必须携带param请求参数但是param!=value



```html
<a th:href="@{/test(username='admin',password=123456)">测试@RequestMapping的params属性-->/test</a><br>
```

```java
@RequestMapping(
        value = {"/testRequestMapping", "/test"}
        ,method = {RequestMethod.GET, RequestMethod.POST}
        ,params = {"username","password!=123456"}
)
public String testRequestMapping(){
    return "success";
}
```

> 注：
>
> 若当前请求满足@RequestMapping注解的value和method属性，但是不满足params属性，此时页面回报错400：Parameter conditions "username, password!=123456" not met for actual request parameters: username={admin}, password={123456}



## 6、@RequestMapping注解的headers属

@RequestMapping注解的headers属性通过请求的请求头信息匹配请求映射

@RequestMapping注解的headers属性是一个字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系

"header"：要求请求映射所匹配的请求必须携带header请求头信息

"!header"：要求请求映射所匹配的请求必须不能携带header请求头信息

"header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value

"header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value

若当前请求满足@RequestMapping注解的value和method属性，但是不满足headers属性，此时页面显示404错误，即资源未找到



## 7、SpringMVC支持ant风格的路径

？：表示任意的单个字符

*：表示任意的0个或多个字符

\**：表示任意的一层或多层目录

注意：在使用\**时，只能使用/**/xxx的方式



##  8、SpringMVC支持路径中的占位符（重点）

原始方式：/deleteUser?id=1

rest方式：/deleteUser/1

SpringMVC路径中的占位符常用于RESTful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，在通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参

```html
<a th:href="@{/testRest/1/admin}">测试路径中的占位符-->/testRest</a><br>
```

```java
@RequestMapping("/testRest/{id}/{username}")
public String testRest(@PathVariable("id") String id, @PathVariable("username") String username){
    System.out.println("id:"+id+",username:"+username);
    return "success";
}
//最终输出的内容为-->id:1,username:admin
```



