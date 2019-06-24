Cmail
================================================================
使用配置文件  
----------------------------------------------------------------
spring:  
  datasource:  
    druid:  
      url:  
      username:  
      password:  
      driver-class-name: com.mysql.cj.jdbc.Driver  
  mail:  
    host:  
    username:  
    password:  
    default-encoding: UTF-8  
    port:   
    

使用minio提供对象存储服务，请添加一下配置 
cmail:
  minio:  
    url:  
    username:  
    passowrd:  
  
  
项目集成说明
===========
1.支持minio客户端，但需要引入minio依赖，并提供cmail:minio相关配置信息。
2.支持swagger2接口文档框架，直接访问swagger-ui.html即可

接口说明
----------------------------------------------------------------
1./content/all 获得全部已配置的邮件内容信息，请求方式：get。
2./sendMail/simple/group 发送简单邮件，请求方式：post，仅包含：收件人、邮件标题、内容。多个收件人以群发方式送达。
3./sendMail/simple/singleton 发送简单邮件，请求方式：post，仅包含：收件人、邮件标题、内容。多个收件人以单发方式送达。
请求方式为post，请求参数json格式如下：{"content":"内容","recipients":["收件人1","收件人2"],"subject":"邮件标题"}。
4./sendMail/attachment/minio/group 发送从带附件的邮件，附件文件从minio客户端读取。多个收件人以群发的方式送达。
请求方式：post，请求参数json格式如下：{"attachments":{"文件1-id":"文件1-bucket","文件2-id":"文件2-bucket"},
"content":"内容","recipients":["收件人1","收件人2"],"subject":"邮件标题"}。
5./sendMail/attachment/minio/singleton 发送从带附件的邮件，附件文件从minio客户端读取。多个收件人以单发的方式送达。
  请求方式：post，请求参数json格式如下：{"attachments":{"文件1-id":"文件1-bucket","文件2-id":"文件2-bucket"},
  "content":"内容","recipients":["收件人1","收件人2"],"subject":"邮件标题"}。
6./cmi/content/all 获得全部收件人信息，请求方式：get。