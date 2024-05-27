# SpringBoot-Vue-微头条
保姆级教程！尚硅谷微头条项目复刻，技术栈为SpringBoot+mybatisPlus+Vue+maven+Node.js，前端使用VSCode作为编辑器,后端使用IDEA专业版IDE。[项目地址](https://www.wolai.com/ie9813gmgSnVnEbFCdWfM2)如有问题可查看原项目。可以用来练手或者当大作业，对您有帮助的话请点个star吧！
## 运行步骤如下，建议在后端项目启动后再运行前端项目：
  ### 前端部分：
  首先安装node.js，[Node.js安装步骤](https://blog.csdn.net/WHF__/article/details/129362462),
  前端为weitoutiao文件夹，使用VSCode打开weitoutiao文件夹，确保路径在weitoutiao文件夹下并在终端中输入
  ```
  npm install
  ```
  安装完成后weitoutiao目录下会多出一个node_modules文件，接着在终端中输入
  ```
  npm run dev
  ```
  即可运行前端程序。
  ****
  ### 数据库部分:
  数据库为mysql，版本需采用8.0以上版本。首先在开始徽标旁的搜索框中搜索“服务”，打开MySQL80服务，接着打开IDEA或Navicat或其他数据库工具管理软件，复制weitoutiao.sql中的代码并运行。
  ****
  ### 后端部分：
  JDK版本需要在17及以上，maven版本为3.8.1[maven安装配置步骤](https://blog.csdn.net/MSDCP/article/details/127680844)，需要安装Lombok插件，建议安装MyBatisX插件。
  使用IDEA打开springboot-weitoutiao。打开src/main/resources/application.yaml文件，将连接池配置中的username和password修改为自己的。点开侧边栏Maven，点击刷新图标，等待构建完毕。
  构建完毕后打开根目录下的src/main/java/com/whj,运行main文件即可。
