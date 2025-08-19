// import { createRouter,createWebHashHistory } from 'vue-router'
import { createRouter,createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import login from '../views/Login.vue'
import register from '../views/Register.vue'
import honeypot from '../views/Honeypot.vue'
import abilitypromotion from '../views/AbilityPromotion.vue'
import Forum from '../views/Forum.vue'
import courseManagement from '../views/CourseManagement.vue'
import userManagement from '../views/UserManagement.vue'
import Test from "../views/Test.vue";
import BugPage from "../views/BugPage.vue";
import message from "../views/Message.vue";
import personalcenter from "../views/PersonalCenter.vue";
import navigation from "../views/navigation.vue";
import invitation from "../views/invitation.vue";

// 漏洞页面
import Rceping from '../views/problem_views/RCE/RCE_ping'
import sqlinum from '../views/problem_views/SQLi/sqli_num'
import sqlibrackets from '../views/problem_views/SQLi/sqli_brackets'
import sqlichar from '../views/problem_views/SQLi/sqli_char'
import sqlidelete from '../views/problem_views/SQLi/sqli_delete'
import sqlidelete1 from '../views/problem_views/SQLi/sqli_delete1'
import sqlisearch from '../views/problem_views/SQLi/sqli_search'
import sqli_unsafe from '../views/problem_views/SQLi/sqli_unsafe'
import sqli_unsafe2 from '../views/problem_views/SQLi/sqli_unsafe2'

import forceclient from '../views/problem_views/FORCE/force_client'
import forceform from '../views/problem_views/FORCE/force_form'
import filedownload from '../views/problem_views/FileDownLoad/FileDownload'
import fileread from '../views/problem_views/FileDownLoad/FileRead'
import fileupload from '../views/problem_views/FileUpLoad/fileupload'
import java_deser from "../views/problem_views/JavaDeser/java_deser";

import urlredirect from "../views/problem_views/UrlRedirect/UrlRedirect"
import buy from "../views/problem_views/UrlRedirect/buy"
import urllogin from "../views/problem_views/UrlRedirect/urlLogin"
import urllogin1 from "../views/problem_views/UrlRedirect/urlLogin1"

import xss_reflect from "../views/problem_views/XSS/xss_reflect";
import xss_store from "../views/problem_views/XSS/xss_store";
import xxe from "../views/problem_views/XXE/xxe";
import oplogin from "../views/problem_views/OverPermission/op1_login";
import oppc from "../views/problem_views/OverPermission/op1_person_center";
import pathtravel from "../views/problem_views/PathTravel/PathTravel";
import imageshow from "../views/problem_views/PathTravel/imageshow";


//工具页面
import base64tool from '../views/tools_views/base64';
import cmstool from '../views/tools_views/CMS';
import domainfindtool from '../views/tools_views/domain_find';
import ipinfotool from '../views/tools_views/IP_info';
import scanporttool from "../views/tools_views/scan_port";
import urlencode from "../views/tools_views/URLencode";
import caesar from "../views/tools_views/CaesarCrypt";
import rc4 from "../views/tools_views/RC4";
import des from "../views/tools_views/DES";
import hash from "../views/tools_views/HASH";
import rot13 from "../views/tools_views/Rot13";
import unicode from "../views/tools_views/unicode";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: login
  },
  {
    path: '/register',
    name: 'Register',
    component: register
  },
  {
    path: '/honeypot',
    name: 'Honeypot',
    component: honeypot,
  },
  {
    path: '/bugpage',
    name: 'BugPage',
    component: BugPage,
  },
  {
    path: '/abilitypromotion',
    name: 'abilitypromotion',
    component: abilitypromotion
  },
  {
    path: '/forum',
    name: 'Forum',
    component: Forum,
  },
  {
    path: '/usermanagement',
    name: 'userManagement',
    component: userManagement,
  },
  {
    path: '/coursemanagement',
    name: 'courseManagement',
    component: courseManagement,
  },
  {
    path: '/test',
    name: 'Test',
    component: Test,
  },
  {
    path: '/message',
    name: 'Message',
    component: message,
  },
  {
    path: '/personalcenter',
    name: 'personalcenter',
    component: personalcenter,
  },
  {
    path: '/navigation',
    name: 'navigation',
    component: navigation,
  },
  {
    path: '/invitation',
    name: 'invitation',
    component: invitation,
  },
  {
    path: '/oppc',
    name: 'oppc',
    component: oppc,
  },

// 漏洞页面
  {
    path: '/pro001',
    name: 'Rceping',
    component: Rceping
  },
  {
    path: '/pro002',
    name: 'sqlibrackets',
    component: sqlibrackets
  },
  {
    path: '/pro003',
    name: 'sqlichar',
    component: sqlichar
  },
  {
    path: '/pro004',
    name: 'sqlinum',
    component: sqlinum
  },
  {
    path: '/pro005',
    name: 'forceclient',
    component: forceclient
  },
  {
    path: '/pro006',
    name: 'forceform',
    component: forceform
  },
  {
    path: '/pro007',
    name: 'filedownload',
    component: filedownload
  },
  {
    path: '/pro008',
    name: 'fileupload',
    component: fileupload
  },
  {
    path: '/pro009',
    name: 'java_deser',
    component: java_deser
  },
  {
    path: '/pro010',
    name: 'buy',
    component: buy
  },
  {
    path: '/urllogin',
    name: 'urllogin',
    component: urllogin
  },
  {
    path: '/urllogin1',
    name: 'urllogin1',
    component: urllogin1
  },
  {
    path: '/urlredirect',
    name: 'urlredirect',
    component: urlredirect
  },
  {
    path: '/pro011',
    name: 'xss_store',
    component: xss_store
  },
  {
    path: '/pro012',
    name: 'xss_reflect',
    component: xss_reflect
  },
  {
    path: '/pro013',
    name: 'xxe',
    component: xxe
  },
  {
    path: '/pro022',
    name: 'sqlidelete',
    component: sqlidelete
  },
  {
    path: '/pro022success',
    name: 'sqlidelete1',
    component: sqlidelete1
  },
  {
    path: '/pro023',
    name: 'sqlisearch',
    component: sqlisearch
  },
  {
    path: '/pro024',
    name: 'oplogin',
    component: oplogin
  },
  {
    path: '/pro025',
    name: 'fileread',
    component: fileread
  },
  {
    path: '/pro026',
    name: 'pathtravel',
    component: pathtravel
  },
  {
    path: '/imageshow',
    name: 'imageshow',
    component: imageshow
  },
  {
    path: '/pro027',
    name: 'sqli_unsafe',
    component: sqli_unsafe
  },
  {
    path: '/pro028',
    name: 'sqli_unsafe2',
    component: sqli_unsafe2
  },

//工具页面
  {
    path: '/base64tool',
    name: 'base64tool',
    component: base64tool
  },
  {
    path: '/cmstool',
    name: 'cmstool',
    component: cmstool
  },
  {
    path: '/domainfindtool',
    name: 'domainfindtool',
    component: domainfindtool
  },
  {
    path: '/ipinfotool',
    name: 'ipinfotool',
    component: ipinfotool
  },
  {
    path: '/scanporttool',
    name: 'scanporttool',
    component: scanporttool
  },
  {
    path: '/urlencode',
    name: 'urlencode',
    component: urlencode
  },
  {
    path: '/rc4',
    name: 'rc4',
    component: rc4
  },
  {
    path: '/rot13',
    name: 'rot13',
    component: rot13
  },
  {
    path: '/des',
    name: 'des',
    component: des
  },
  {
    path: '/hash',
    name: 'hash',
    component: hash
  },
  {
    path: '/CaesarCrypt',
    name: 'caesar',
    component: caesar
  },
  {
    path: '/unicode',
    name: 'unicode',
    component: unicode
  },

]

const router = createRouter({
  // history: createWebHashHistory(),
  history: createWebHistory(),
  routes

})

export default router
