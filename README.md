# wesay

#### 框架启动需要初始化数据库脚本

1. 新建一个数据库或者使用原有的数据库，例如：wesay

2. 修改wesay-server模块下的application.properties配置文件，修改数据源配置

   ```
   spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
   spring.datasource.url=jdbc:mysql://192.168.249.132:3306/wesay?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong
   spring.datasource.username=admin
   spring.datasource.password=ENC(s86x6miH2NvKxWepuuoGjvx/XLu9gRj6)
   
   spring.datasource.password加密使用，wesay-server模块下test/java文件夹里面EncryptMain.java类进行加密
   ```

3. 修改日志的存储路径，修改wesay-server模块下resources/log4j2.xml

   ```
   <!--日志文件存储路径配置-->
   <property name="LOG_DIR" value="F:/logs"/>
   <!--日志文件名称配置-->
   <property name="LOG_NAME" value="wesay-server"/>
   <property name="SPY_LOG_NAME" value="spy-sql"/>
   ```

4. 运行wesay-server模块下，src/main/java/com/github/ApplicationStartup.java
5. 访问http://localhost:8080/hello