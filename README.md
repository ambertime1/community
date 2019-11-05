##majiang community

##resources
[spring docs](https://spring.io/guides)
[spring web docs]https://spring.io/guides/gs/serving-web-content/
[spring elastic community]https://elasticsearch.cn/explore
[sgithub deploy key]https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-key
https://developer.github.com/apps/building-github-apps/creating-a-github-app/
https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-sql
##tools
https://git-scm.com/download
https://v3.bootcss.com
https://www.visual-paradigm.com

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
