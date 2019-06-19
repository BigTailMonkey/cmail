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
minu:  
  url:  
  username:  
  passowrd:  
  
  
接口说明
----------------------------------------------------------------
1./content/all 获得全部已配置的邮件内容信息
2./sendMail/simple 发送简单邮件，仅包含：收件人、邮件标题、内容。多个收件人以群发方式送达。
请求方式为post，请求参数json格式如下：{"content":"内容","recipients":["收件人1","收件人2"],"subject":"邮件标题"}。
3./sendMail/attachement/minio/group 发送从带附件的邮件，附件文件从minio客户端读取。多个收件人以群发的方式送达。
请求方式为post，请求参数json格式如下：{"attachements":{"文件1-id":"文件1-bucket","文件2-id":"文件2-bucket"},
"content":"内容","recipients":["收件人1","收件人2"],"subject":"邮件标题"}。