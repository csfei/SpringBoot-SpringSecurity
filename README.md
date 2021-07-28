# SpringBoot-SpringSecurity

前端界面中 的需要用到 一些新的命令：

  在pom文件中 整合    themeleaf 与 security 的jar
         <dependency>
            <groupId>org.thymeleaf.extras</groupId>
            <artifactId>thymeleaf-extras-springsecurity4</artifactId>
            <version>3.0.2.RELEASE</version>
        </dependency

  需要在前端界面导入命名空间  thymeleaf 的和security的两个命名空间
  
    xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4"
    
  
  例如：  sec:authentication
          sec:authorize
  实例：
  <div sec:authorize="isAuthenticated()"  class="row" >


    
