create database robot;
use robot;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receive` varchar(100) DEFAULT NULL,
  `response` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
insert into dictionary (receive, response) values ('你好','好你妹'), ('操', '请说中国话'), ('大神', '请叫我大佬');