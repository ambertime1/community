##majiang community

##resources
[spring docs](https://spring.io/guides)
[spring web docs](https://spring.io/guides/gs/serving-web-content/)
[spring elastic community](https://elasticsearch.cn/explore)
[github deploy key]https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-key
[app auth](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)
[Mybatis](https://mybatis.org/mybatis-3/configuration.html)
[Spring boot docs](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-sql)
[Thyleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

##tools
https://git-scm.com/download
https://v3.bootcss.com
https://www.visual-paradigm.com
flyway https://flywaydb.org/getstarted/
#自动注入getter setter toString() Data
https://projectlombok.org/features/all
mvn command:
mvn -Dmaven.wagon.http.ssl.insecure=true clean package

##script
-- create database community;
DROP TABLE IF EXISTS community.user；
CREATE TABLE community.user
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_id VARCHAR(100),
  name VARCHAR(50),
  token CHAR(36),
  gmt_create BIGINT,
  gmt_modified BIGINT
  ) ENGINE = InnoDB ROW_FORMAT = DEFAULT;
  ..
  ..bash
  ALTER TABLE user ADD bio VARCHAR(256) NULL;
  
  DROP TABLE IF EXISTS community.question；
CREATE TABLE community.question
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50),
  description TEXT,
  gmt_create BIGINT,
  gmt_modified BIGINT,
  creator INT,
  comment_count INT DEFAULT 0,
  view_count INT DEFAULT 0,
  like_count INT DEFAULT 0,
  tag VARCHAR(256)
  ) ENGINE = InnoDB ROW_FORMAT = DEFAULT;
