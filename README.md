# Open Test Class
[![Build Status](https://github.com/alaahong/open_test_class/workflows/CI/badge.svg)](https://github.com/alaahong/open_test_class)
[![Code Quality Score](https://www.code-inspector.com/project/4050/score/svg)](https://www.code-inspector.com/project/4050/score/svg)

------

## 任务要求

> **特别注意**：请于完成后，将项目源码更新至候选者您本人的Github，在您项目的**Settings**页面，例如本项目为此[链接](https://github.com/alaahong/open_test_class/settings/access) ，将您的项目设置为**Private Repository**以免借阅, 并于**Manage access**邀请**alaahong**作为协作者(collaborator)后，发起[issue](https://github.com/alaahong/open_test_class/issues)声明您的项目链接，并最终反馈给HR/Vendor/猎头等渠道 

> 这么简单直白的需求，没必要网上随便找个开源的项目扒代码，面试者自行决定实现的程度。硬要扒的话，面试的时候会加深项目设计和实现方面的细节询问，无法自圆其说的话，按捉刀代笔处理。


* 请**Fork**本项目到您自己的Github账号下
* 以下内容均基于**Java**进行考察，并同时涉及到了Git, Maven, Spring全家桶等技术  
* 请基于项目提供的基础结构进行开发扩展，符合标准的项目结构，可以通过Maven添加必要的依赖,务必自行解决依赖冲突  

希望，我们可以遇到这样的您：
* 认真理解题目的要求，若有不明确的地方，可以直接提[issue](https://github.com/alaahong/open_standard_class/issues)沟通 或 通过HR/Vendor/猎头等反馈
* 尽量提交可以编译运行的项目，至少也应该是可以通过**Maven**构建的 [![Build Status](https://github.com/alaahong/open_test_class/workflows/CI/badge.svg)](https://github.com/alaahong/open_test_class)
* 项目不依赖于特定的IDE，可以通过命令行完成基本的CICD引用
* 思路清晰，代码规范，尽量更好地完成任务，针对**项目结构**和**代码质量**进行了完善 



## 项目需求

## 测试课堂 (Test Class)

受疫情影响，“停课不停学”的在线教育成为这段时间各大高校师生的常态。据教育部数据，截至4月初，参加在线课程学习的学生达 11.8 亿人次，参与在线教学教师 1600 万人。某单位响应号召，计划推出“测试课堂”来实现在线教育管理的功能.

1 基本功能要求
* 用户登录：根据用户名密码登录软件。根据账号类型(教师/学生)不同自动切换功能，必须包含一个账户名为Admin，密码为Admin的管理员帐号，此账号仅能用于管理教师和学生账户的增删改.
* 课程管理：教师账号登录系统后，可以操作课程管理模块，本模块提供课程的增删改查功能，并可以展示具体某门课程的订阅学生数量.
* 订阅管理：学生账号登录系统后，可以操作订阅管理模块，本模块可以查询当前有效的课程信息，并提供订阅以及取消订阅的功能.
 
2 系统设计要求
* 数据库请使用H2
* 持久层请使用JPA以及相关技术
* 对于UI的实现，至少需要使用[Thymeleaf](https://www.thymeleaf.org/)，对于掌握现代UI开发的同学可以使用[Vuetify](https://vuetifyjs.com/)

> 对于评估实现需要几周甚至几个月的同学，请认真思考面试题的场景和需求，制定出编码者实际的交付计划. 项目要求里可能有不熟悉的技术要求，但是业务的基本需求非常简单，基于Spring全家桶的要求大大降低了学习成本和最小实现难度。

 
