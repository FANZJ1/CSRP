/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.36 : Database - csrp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`csrp` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `csrp`;

/*Table structure for table `cookie` */

DROP TABLE IF EXISTS `cookie`;

CREATE TABLE `cookie` (
  `cookie` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tools` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cookie` */

insert  into `cookie`(`cookie`,`tools`) values ('PHPSESSID=422md6ifcl7ua58lhmj8ma1jg5; think_var=zh-cn','cms');
insert  into `cookie`(`cookie`,`tools`) values ('Hm_lvt_7d51be3b7524d35798ad1304e725bd2c=1653190253,1653827802,1653871299,1653956560; Hm_lpvt_7d51be3b7524d35798ad1304e725bd2c=1653956560','domain');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `courseid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `coursename` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名',
  `teacherid` int(11) DEFAULT NULL COMMENT '教师ID',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`courseid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `course` */

insert  into `course`(`courseid`,`coursename`,`teacherid`,`createtime`) values (1,'2022春季网络攻击与防范',1,'2022-04-18 17:59:13');
insert  into `course`(`courseid`,`coursename`,`teacherid`,`createtime`) values (2,'2022春季密码学',2,'2022-06-08 15:58:52');
insert  into `course`(`courseid`,`coursename`,`teacherid`,`createtime`) values (3,'2022春季系统安全',1,'2022-06-08 15:58:52');

/*Table structure for table `flag` */

DROP TABLE IF EXISTS `flag`;

CREATE TABLE `flag` (
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `problemid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `flag` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`,`problemid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `flag` */

insert  into `flag`(`username`,`problemid`,`flag`) values ('001','pro002','flag{ZVgvB5YI5D3RKrexVTOVGImsQP8o6G}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('001','pro003','flag{k9tFIos3TvAPM6f026tK8U50xBRbj4}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('001','pro004','flag{ihBIILDxoZb4ew2KYUZQ2LmDBkBEIl}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('001','pro009','flag{AQYKjQlSXHmz2xUCsGNN1mh13csYZZ}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('002','pro009','flag{ZhSNv5bRvG8KGAvyUX2WN4rNWu7I31}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('003','pro001','flag{eGGwq7yxSyDXc7zUxQK10adhmQbRG3}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('003','pro002','flag{GLO5IKcxYisC9BdKVd6ScKVaXL6vAt}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('003','pro003','flag{hAEBGU9HLVBWRG8N70dpnlGZNFi4RV}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('003','pro004','flag{fLnHqmsWwMxgPxAG1d7GvuRWg51Hyw}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('003','pro014','flag{IBaWQb1nmMQoq3uWOiElZGMT6uHmjk}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('111','pro014','flag{2nQfmGOCMAlsx1kR670nySr8UGO1Kl}');
insert  into `flag`(`username`,`problemid`,`flag`) values ('hhh','pro004','flag{5liP6hTshBk2WyvAFuR13eGe7OUnTg}');

/*Table structure for table `forum` */

DROP TABLE IF EXISTS `forum`;

CREATE TABLE `forum` (
  `forumid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `username` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `state` int(255) DEFAULT '1' COMMENT '0：未通过审核；1：通过审核',
  PRIMARY KEY (`forumid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `forum` */

insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (1,'如来十三掌','题目：夜哆悉諳多苦奢陀奢諦冥神哆盧穆皤三侄三即諸諳即冥迦冥隸數顛耶迦奢若吉怯陀諳怖奢智侄諸若奢數菩奢集遠俱老竟寫明奢若梵等盧皤豆蒙密離怯婆皤礙他哆提哆多缽以南哆心曰姪罰蒙呐神。舍切真怯勝呐得俱沙罰娑是怯遠得呐數罰輸哆遠薩得槃漫夢盧皤亦醯呐娑皤瑟輸諳尼摩罰薩冥大倒參夢侄阿心罰等奢大度地冥殿皤沙蘇輸奢恐豆侄得罰提哆伽諳沙楞缽三死怯摩大蘇者數一遮……解法：首先需要找到“与佛论禅”对文字进行解码，很多时候就直接卡在这一步了，然后要根据题目名称联想到rot13，还要观察出是经过base64编码，让后还能将这两个组合起来，才能得到flag。','202000301001','2022-04-17 19:05:21',1);
insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (2,'buckup','打开题目连接，发现提示信息“index.php的备份文件名”；\r\n百度后可得知，一些编辑器再编辑后会生产一个 .bak后缀的文件，用于紧急恢复；\r\n例如：index.php→ index.php.bak；不同的编译器可能会有不同的后缀；同样也有可能是编写者主动备份；\r\n逐一尝试后，发现存在index.php.bak文件，notepad(或者其他编辑器)打开后即可得到flag。','202000301002','2022-04-17 16:11:38',1);
insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (3,'give_you_flag','下载附件后查看，发现是gif格式图片，并且有一张二维码一闪而过，猜测是图片的某一帧中插入了二维码；使用PS或者GIF制作器打开，发现缺少三个小方块的二维码，而这些小方块被称为定位图案，用于标记二维码矩形的大小，用三个定位图案可以标识并确定一个二维码矩形的位置和方向。二维码随然具有很强的纠错功能，能够在30%面积被遮盖的情况下成功识别，但前提是定位图案不被遮盖，因此接下来只需把三个定位图案P上就行了。','202000301003','2022-04-17 16:12:06',1);
insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (4,'disabled_button','打开题目链接，发现有一按钮，但是无法按下；\r\n查看源码发现，是“disabled”在捣鬼，我们不能通过点击按钮提交表单；\r\n不过我们能通过linux中的curl命令来post数据；\r\n在终端里输入命令：curl -d \"auth=flag\" \"http://111.198.29.45:49554/\"；\r\n\"auth=flag\"及是我们需要提交的数据；','202000301004','2022-04-17 16:12:58',1);
insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (5,'xff_referer','根据题目的提示，了解到本题需要我们伪造xff和referer；\r\n解题过程比较简单，主要是抓包并进行修改。\r\n使用fiddler抓包，然后添加上根据要求伪造的xff和referer即可。','202000301005','2022-04-17 16:13:34',1);
insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (6,'gif','下载附件并解压，发现是一堆黑色和白色的图片，按某种规律排列，猜想flag就应该隐藏在黑白图片的排列顺序里。\r\n联想到黑白两种状态可能对应二进制的‘0’和‘1’，而这些黑白图片的顺序可以改写成一串二进制序列，然后发现图片总数为104，是8的倍数，自然联想到一个字节8位二进制码，可以尝试对得到的序列按8位分组。推测出，白色对应0，黑色对应1，得到的8位二进制数应该对应ascii的字符编码。再把二进制数转为字符，即为flag。','202000301006','2022-04-17 18:59:22',1);
insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (7,'想蹭网先解开密码','下载附件，为.cap文件，这种文件是数据包探测程序包含收集的数据包文件格式，结合题目名称，此文件应该是wifi密码爆破过程中抓包得到的数据，因此可以使用工具进行爆破密码，可以使用工具有airepaly-ng， WIFIPR等，此处采用WIFIPR进行破解。','202000301007','2022-04-17 19:12:55',1);
insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (8,'旋转跳跃','此题需要用到隐写工具MP3stego，下载附件，使用MP3stego工具提取，输入指令：\r\nDecode.exe -X -P syclovergeek sycgeek-mp3.mp3；\r\n其中syclovergeek为题目提示的key值，等待提取结束后，获得flag:SYC{Mp3_B15b1uBiu_W0W}','202000301008','2022-04-17 19:14:34',1);
insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (9,'convert','打开题目连接，发现是一堆01数字；分析其结构比较符合8位二进制ask码，同时结合题目名称convert（转换）猜测是需要进行进制转换。打开转换后的文件，发现文件头部存在“RAR”的标识，因此将后缀名改为.rar，解压，得到图片key.jpg，在其属性中发现base64码，解码得到flag。','202000301009','2022-04-17 19:15:01',1);
insert  into `forum`(`forumid`,`title`,`content`,`username`,`time`,`state`) values (10,'求getshell','打开连接，是一个文件上传页面，结合题目名称，应该是需要上传shell；写一个简单shell，上传并抓包。\r\n将后缀改为.php5，这样不会被过滤；将Content-Type: application/octet-stream改为Content-Type: image/jpeg，躲避文件识别；\r\n将Content-Type: multipart/form-data;其中的一个字母换成大写，越过服务器端检查。','202000301002','2022-04-17 19:23:28',1);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `messageid` int(11) NOT NULL AUTO_INCREMENT,
  `messagecontent` text,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `messagetitle` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`messageid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`messageid`,`messagecontent`,`date`,`messagetitle`) values (11,'当前系统已经更新到3.0版本，请各位老师同学及时检查数据和功能完备，联系电话：18855606450','2022-06-08 15:53:39','新版本更新了！');

/*Table structure for table `opuser` */

DROP TABLE IF EXISTS `opuser`;

CREATE TABLE `opuser` (
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `add` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username1` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `opuser` */

insert  into `opuser`(`username`,`password`,`phone`,`add`,`username1`) values ('curry','123','13189031245','山东济南',NULL);
insert  into `opuser`(`username`,`password`,`phone`,`add`,`username1`) values ('lucy','123=+kshf:?dkjsidfo','12178902321','山东济南',NULL);

/*Table structure for table `problem` */

DROP TABLE IF EXISTS `problem`;

CREATE TABLE `problem` (
  `problemid` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `kind` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `problemratio` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `problemcontent` text COLLATE utf8mb4_unicode_ci,
  `problemname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`problemid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `problem` */

insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro001','0','rce','4','最近刚学会ping指令，你知道它也是TCP/IP协议的一部分。正好你的舍友刚搭建了一个路由器管理平台，不妨去搞个破坏实践一下。','RCE-ping?');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro002','0','sqli','3','这里有一个学生信息查询系统，尝试去找到藏在数据库中的flag，线索就在数据库中的表名里','SQLi-brackets');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro003','0','sqli','4','这里有一个学生信息查询系统，尝试去找到藏在数据库中的flag，线索就在数据库中的表名里','SQLi-Char');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro004','0','sqli','3','这里有一个学生信息查询系统，尝试去找到藏在数据库中的flag，线索就在数据库中的表名里','SQLi-Num');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro005','1','force','3','含有验证码的登录页面，看上去似乎更难破解，判断一下校验的位置在哪，也许是个突破口。','force_cllient');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro006','1','force','2','一位同学在登陆时忘记了他的密码，听说你精通暴力破解，请求你来帮助他登录','force_form');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro007','0','filedownload','3','任意文件下载漏洞，看看能不能下载flag文件？','文件下载');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro009','0','javadeser','4','根据提供的flag类的序列化后的数据，阅读序列化源码，写出反序列化代码，尝试获取flag','Java Deseialization');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro010','0','urlredirect','3','url重定向','URL-Redirect');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro011','0','xss','3','存储型XSS漏洞','XSS-Store');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro012','0','xss','4','反射型XSS漏洞','XSS-Reflect');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro013','0','xxe','4','XXE漏洞，flag存储在D://XXE.txt,can you get it?','XXE');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro014','1','force','5','小明在登陆时忘记了他的密码，尽管他也会一点暴力破解，但还是被token验证拦住了，有什么办法绕过token吗？','force_token1');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro015','3','csrf','2','截获URL 干点坏事吧','csrf_get');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro016','3','csrf','3','看不到URL了 想想该怎么办','csrf_post');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro017','3','csrf','4','加了token防御 试着写个攻击脚本','csrf_token1');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro018','1','fileupload','3','去了解一下MIME','upload_mime');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro019','1','xss','3','你能写出正确的攻击脚本嘛','xss_dom');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro020','1','force','4','含有验证码的登录页面，看上去似乎更难破解，判断一下校验的位置在哪，也许是个突破口。','force_vcode');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro021','1','xss','3','你能写出正确的攻击脚本嘛','xss_dom_x');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro022','0','sqli','4','试试用报错函数','SQL 删除型');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro023','0','sqli','3','注意正确的闭合','SQL 搜索型');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro024','0','BAC','4','尝试获取lucy用户的个人信息','op_login');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro025','0','filedownload','3','憨批写的漏洞','file_read');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro026','0','pathtravel','3','目录遍历','path_travel');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro027','0','sqli','4','assas','unsafe_filter1');
insert  into `problem`(`problemid`,`url`,`kind`,`problemratio`,`problemcontent`,`problemname`) values ('pro028','0','sqli','4','asdfdf','unsafe_filter2');

/*Table structure for table `sqlifl4g` */

DROP TABLE IF EXISTS `sqlifl4g`;

CREATE TABLE `sqlifl4g` (
  `username` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `problemid` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fl4g` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sqlifl4g` */

insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('001','pro004','flag{ihBIILDxoZb4ew2KYUZQ2LmDBkBEIl}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('003','pro002','flag{1be3aAihYpNSf0CA7IfeC8S4YpRYLG}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('003','pro003','flag{yFUHyQjPWoQzFPvDUq7lqZihqGYSun}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('003','pro004','flag{ZKSppX0TcDY1HYxQigeas0RP0VXngd}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('003','pro003','flag{2XDjWVzujzDwNUp7kFfRUlx90wddSC}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('001','pro003','flag{k9tFIos3TvAPM6f026tK8U50xBRbj4}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('001','pro002','flag{ZVgvB5YI5D3RKrexVTOVGImsQP8o6G}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('003','pro002','flag{lKNK2U3GdxrVihoab6pU6EdWPRRNtX}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('hhh','pro004','flag{5liP6hTshBk2WyvAFuR13eGe7OUnTg}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('003','pro004','flag{p7na53nkGDDBqOgxysAsLbjjYi81PZ}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('003','pro004','flag{fLnHqmsWwMxgPxAG1d7GvuRWg51Hyw}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('003','pro003','flag{hAEBGU9HLVBWRG8N70dpnlGZNFi4RV}');
insert  into `sqlifl4g`(`username`,`problemid`,`fl4g`) values ('003','pro002','flag{GLO5IKcxYisC9BdKVd6ScKVaXL6vAt}');

/*Table structure for table `sqliuser` */

DROP TABLE IF EXISTS `sqliuser`;

CREATE TABLE `sqliuser` (
  `id` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `level` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `sqliuser` */

insert  into `sqliuser`(`id`,`name`,`password`,`level`) values ('1','James','123','1');
insert  into `sqliuser`(`id`,`name`,`password`,`level`) values ('2','Curry','123','3');
insert  into `sqliuser`(`id`,`name`,`password`,`level`) values ('3','Harden','1','1');
insert  into `sqliuser`(`id`,`name`,`password`,`level`) values ('pro004','aaa','1','1');

/*Table structure for table `stucontainer` */

DROP TABLE IF EXISTS `stucontainer`;

CREATE TABLE `stucontainer` (
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `problemName` varchar(255) DEFAULT NULL COMMENT '容器名题目名',
  `containerId` varchar(255) DEFAULT NULL COMMENT '容器ID'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `stucontainer` */

/*Table structure for table `stucourse` */

DROP TABLE IF EXISTS `stucourse`;

CREATE TABLE `stucourse` (
  `courseid` int(10) NOT NULL,
  `userid` int(10) NOT NULL,
  PRIMARY KEY (`courseid`,`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `stucourse` */

insert  into `stucourse`(`courseid`,`userid`) values (1,3);
insert  into `stucourse`(`courseid`,`userid`) values (1,4);
insert  into `stucourse`(`courseid`,`userid`) values (1,5);
insert  into `stucourse`(`courseid`,`userid`) values (1,6);
insert  into `stucourse`(`courseid`,`userid`) values (1,7);
insert  into `stucourse`(`courseid`,`userid`) values (1,8);
insert  into `stucourse`(`courseid`,`userid`) values (1,9);
insert  into `stucourse`(`courseid`,`userid`) values (1,11);
insert  into `stucourse`(`courseid`,`userid`) values (1,13);
insert  into `stucourse`(`courseid`,`userid`) values (1,15);
insert  into `stucourse`(`courseid`,`userid`) values (1,24);
insert  into `stucourse`(`courseid`,`userid`) values (1,25);
insert  into `stucourse`(`courseid`,`userid`) values (1,26);
insert  into `stucourse`(`courseid`,`userid`) values (2,3);
insert  into `stucourse`(`courseid`,`userid`) values (2,5);
insert  into `stucourse`(`courseid`,`userid`) values (2,8);
insert  into `stucourse`(`courseid`,`userid`) values (2,10);
insert  into `stucourse`(`courseid`,`userid`) values (2,13);
insert  into `stucourse`(`courseid`,`userid`) values (2,19);
insert  into `stucourse`(`courseid`,`userid`) values (2,27);
insert  into `stucourse`(`courseid`,`userid`) values (3,33);
insert  into `stucourse`(`courseid`,`userid`) values (3,34);
insert  into `stucourse`(`courseid`,`userid`) values (3,35);
insert  into `stucourse`(`courseid`,`userid`) values (3,36);
insert  into `stucourse`(`courseid`,`userid`) values (3,37);
insert  into `stucourse`(`courseid`,`userid`) values (3,38);
insert  into `stucourse`(`courseid`,`userid`) values (3,39);
insert  into `stucourse`(`courseid`,`userid`) values (3,40);
insert  into `stucourse`(`courseid`,`userid`) values (3,46);
insert  into `stucourse`(`courseid`,`userid`) values (3,47);
insert  into `stucourse`(`courseid`,`userid`) values (3,48);

/*Table structure for table `stuproblem` */

DROP TABLE IF EXISTS `stuproblem`;

CREATE TABLE `stuproblem` (
  `userid` int(10) NOT NULL,
  `problemid` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `finishtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`,`problemid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `stuproblem` */

insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (1,'pro003','2022-05-24 14:55:49');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (3,'pro001','2022-05-25 11:09:48');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (3,'pro003','2022-05-25 11:10:20');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (3,'pro007','2022-05-24 12:09:46');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (3,'pro008','2022-05-25 11:10:21');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (3,'pro010','2022-05-25 11:10:21');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (3,'pro011','2022-05-25 11:10:21');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (5,'pro023','2022-06-08 15:54:43');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (5,'pro027','2022-06-08 15:54:43');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (6,'pro002','2022-05-25 11:10:21');
insert  into `stuproblem`(`userid`,`problemid`,`finishtime`) values (7,'pro005','2022-05-25 11:10:41');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学工号',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `realname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `role` int(1) DEFAULT '1' COMMENT '学生=1；管理员=2',
  `finished` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '0',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (1,'001','123','林丰波','13864521370',2,'1');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (2,'002','123','孔凡玉','15084673526',2,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (3,'202000301001','123','王冉','17735648311',1,'6');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (4,'202000301002','123','刘部成','15274563782',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (5,'202000301003','123','宋新宇','13392874672',1,'2');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (7,'202000301005','123','张小明','13637467289',1,'1');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (8,'202000301006','123','王小红','13746274682',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (9,'202000301007','123','王提提','14368782374',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (10,'202000301008','123','万叶','12645847568',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (11,'202000301009','123','刘珊珊','13287468798',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (12,'202000301010','123','张涛','12837469592',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (13,'202000301011','123','李娜','14356924352',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (14,'202000301012','123','王丽','19275837628',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (15,'202000301013','123','刘芳','12364587524',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (16,'202000301014','123','李杰','14236482375',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (17,'202000301015','123','张敏','13875627834',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (18,'202000301016','123','王瑞','13573862834',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (19,'202000301017','123','李霞','13278647553',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (20,'202000301018','123','吴军','13465372463',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (21,'202000301019','123','李娟','16437452834',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (22,'202000301020','123','王刚','13657243234',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (23,'202000301021','123','张平','15547834853',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (24,'202000301022','123','陈茹','13456624634',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (25,'202000301023','123','张强','15438677436',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (26,'202000301024','123','孙华','15843573535',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (27,'202000301025','123','刘念','15367235683',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (28,'202000301026','123','谢彬','16587467523',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (29,'202000301027','123','于婷婷','17643857732',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (30,'202000301028','123','周舟','16543295329',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (31,'003','123','麦兜','12345678901',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (32,'201865654004','123','雷永军','2143233444',2,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (33,'1234','1234','徐海华','13840180725',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (34,'201900122018','123WAsd456','阿亦','13961256652',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (35,'201900202093','12345678','杨鹏宇','13290319212',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (36,'201900000001','qjb15689788630','乔靖博','15689788630',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (37,'201900302031','123456','李晨阳','13246678987',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (38,'201900302008','yanhui1234','颜珲','18936556330',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (39,'201900301223','123456','黄怡淇','15078256123',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (40,'201900110029','123','刘竞猷','11111111111',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (41,'000','123','admin','18855606450',3,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (42,'20195645666','123','王健','124325353',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (43,'20180000001','123','陈诚','13224254',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (45,'2018324134','123','吃水果','214312',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (46,'201900150065','123','凌琛','18855606450',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (47,'201900202038','123','李宏伟','12345678900',1,'0');
insert  into `user`(`userid`,`username`,`password`,`realname`,`phone`,`role`,`finished`) values (48,'201922150278','123','范志洁','21342543213',1,'0');

/*Table structure for table `usermessage` */

DROP TABLE IF EXISTS `usermessage`;

CREATE TABLE `usermessage` (
  `userid` varchar(100) NOT NULL,
  `messageid` int(11) NOT NULL,
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`userid`,`messageid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usermessage` */

insert  into `usermessage`(`userid`,`messageid`,`state`) values ('1',11,1);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('10',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('11',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('12',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('13',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('14',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('15',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('16',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('17',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('18',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('19',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('2',11,1);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('20',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('21',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('22',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('23',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('24',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('25',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('26',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('27',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('28',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('29',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('3',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('30',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('31',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('32',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('33',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('34',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('35',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('36',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('37',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('38',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('39',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('4',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('40',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('41',11,1);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('42',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('43',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('45',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('46',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('47',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('48',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('5',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('7',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('8',11,0);
insert  into `usermessage`(`userid`,`messageid`,`state`) values ('9',11,0);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(5) NOT NULL,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`level`) values (1,'admin','123',500);
insert  into `users`(`id`,`username`,`password`,`level`) values (2,'pikachu','456',2);
insert  into `users`(`id`,`username`,`password`,`level`) values (3,'test','789',3);

/*Table structure for table `usersqli` */

DROP TABLE IF EXISTS `usersqli`;

CREATE TABLE `usersqli` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `level` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `usersqli` */

insert  into `usersqli`(`id`,`name`,`password`,`level`) values (1,'lc','123','1');
insert  into `usersqli`(`id`,`name`,`password`,`level`) values (2,'123','123','3');

/*Table structure for table `xssstore` */

DROP TABLE IF EXISTS `xssstore`;

CREATE TABLE `xssstore` (
  `username` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `xsscontent` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `xssstore` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
