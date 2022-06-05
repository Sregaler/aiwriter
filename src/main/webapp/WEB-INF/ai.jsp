<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AI智能写稿</title>
    <link rel="stylesheet" href="/aiwriter/css/bootstrap.css">
    <link rel="stylesheet" href="/aiwriter/css/ai.css">
    <script src="/aiwriter/js/JQuery3.6.js"></script>
    <script src="/aiwriter/js/bootstrap.js"></script>
    <script src="/aiwriter/js/vue.js"></script>
</head>
<body class="clearfix">
<div id="root">
    <div id="head-top" class="container-fluid">
        <div class="row">
            <div id="head-body" class="col-md-10 col-md-offset-1 col-xs-10 col-xs-offset-1">
                <div id="head-body-top">
                    <img id="head-logo" src="/aiwriter/img/顶部菜单/logo.png" alt="">
                    <span id="login">
                            <img id="head-figure" src="/aiwriter/img/顶部菜单/默认头像.svg" alt="">
                            {{username}}
                            <i>▼</i>
                        </span>
                </div>
            </div>
        </div>
    </div>

    <div id="head-buttom" class="container-fluid">
        <div class="row">
            <div id="head-buttom-img" class="col-md-1 col-md-offset-1 col-xs-1 col-xs-offset-1">
                <img id="head-logo1" src="/aiwriter/img/顶部菜单/插图.svg" alt="">
            </div>
            <div id="head-buttom-nav" class="col-md-10 col-xs-10">
                <ul id="head-nav" class="">
                    <li v-for="(item,index) in navItems" :key="item.id" class="head-li" :class="{'head-li-active':navActive==index}" @click="navChange(index)"><b>{{item}}</b></li>
                </ul>
            </div>
        </div>
    </div>

    <div id="context" class="container-fluid">
        <!-- 快速写稿部分 -->
        <div class="row" v-cloak :class="{'notShow':navChangeInfo[0]}">
            <div class="col-md-2  col-xs-2"></div>
            <div class="context-center col-md-8 col-xs-8">
                <div class ="context-center-top clearfix">
                    <div class ="b1">
                        <b>快速成稿</b>
                    </div>
                    <button><b>保存</b></button>
                </div>
                <div class ="QWFixWindow">
                    <div class ="QWFixWindowInner" :class="{'QWFixWindowInnerF':quickWriteFixTop}">
                        <div class="formwork clearfix" v-cloak :class="{'notShow':quickWriteFixer.flag}">
                            <!-- 模板选择 -->
                            <div v-cloak :class="{'notShow':!formworkSelectButton[0]}">
                                <div class="formwork-nav clearfix">
                                    <ul class="clearfix">
                                        <li :class="{'li-active':quickWriteNavInfo.name=='实时热点'}" @click="quickWriteNav('实时热点')">实时热点</li>
                                        <li :class="{'li-active':quickWriteNavInfo.name=='时政'}" @click="quickWriteNav('时政')">时政</li>
                                        <li :class="{'li-active':quickWriteNavInfo.name=='财经'}" @click="quickWriteNav('财经')">财经</li>
                                        <li :class="{'li-active':quickWriteNavInfo.name=='科技'}" @click="quickWriteNav('科技')">科技</li>
                                        <li :class="{'li-active':quickWriteNavInfo.name=='体育'}" @click="quickWriteNav('体育')">体育</li>
                                        <li :class="{'li-active':quickWriteNavInfo.name=='军事'}" @click="quickWriteNav('军事')">军事</li>
                                        <li :class="{'li-active':quickWriteNavInfo.name=='娱乐'}" @click="quickWriteNav('娱乐')">娱乐</li>
                                    </ul>
                                </div>
                                <div class="formwork-common">
                                    <ul class="clearfix">
                                        <li :class="{'li-select':quickWriteNavInfo.CK=='本地'}" @click="quickWriteCKNav('本地')">本地</li>
                                        <li :class="{'li-select':quickWriteNavInfo.CK=='公共'}" @click="quickWriteCKNav('公共')">公共</li>
                                    </ul>
                                </div>
                                <div class="formwork-panel">
                                    <ul class="clearfix">
                                        <li>
                                            <div>
                                                <img src="/aiwriter/img/其他/模板图片.png" alt="">
                                            </div>
                                            <span>会议召开</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- 素材展示 -->
                            <div class="suCaiShowing" v-cloak :class="{'notShow':!formworkSelectButton[1]}">

                                <div>
                                    <b>当前素材</b>
                                    <button class="clearAll" @click="startPut">提交素材</button>
                                    <button class="clearAll">
                                        <img class="clearImg" src="/aiwriter/img/其他/清空所有.svg" alt="">清空所有
                                    </button>
                                </div>
                                <div class="currentSuCaiList">
                                    <ul class="currentSuCaiListUL clearfix">
                                        <li v-for="(item,index) in suCaiInfo.type" :key="item.id">
                                            <img :src="showSuCaiImg(item)" alt="">
                                            <div style="font-size: 10px;">
                                                {{item}}
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- 敏感词检查 -->
                            <div v-cloak :class="{'notShow':!formworkSelectButton[2]}">
                                <div class="repeat-top">
                                    <span>以下段落原创性较低</span>
                                    <span class="recheck">重新检测</span>
                                </div>
                                <div class="checkMGC">
                                    <span>XXXXX</span>
                                    <span>XXXXX</span>
                                    <span>XXXXX</span>
                                    <span>XXXXX</span>
                                    <span>XXXXX</span>
                                    <span>XXXXX</span>
                                    <span>XXXXX</span>
                                </div>
                            </div>

                            <div class="formwork-select">
                                <ul class="clearfix">
                                    <li :class="{'li-select':formworkSelectButton[0]}" @click="toggleType(0)">模板选择</li>
                                    <li :class="{'li-select':formworkSelectButton[1]}" @click="toggleType(1)">上传素材</li>
                                    <li :class="{'li-select':formworkSelectButton[2]}" @click="toggleType(2)">敏感词查找</li>
                                </ul>
                            </div>
                        </div>
                        <div class="hotFix" @click="quickWriteFix">
                            <img :src="quickWriteFixer.link" alt="">
                        </div>
                    </div>
                </div>

                <div class="formwork-button">
                    <!-- 标题 -->
                    <div class="formwork-button-inner">
                        <div class="context-center-input" style="width: 100%;">
                            <input type="text" placeholder="请输入文章标题">
                        </div>
                    </div>
                    <!-- 工具条 -->
                    <div class="formwork-toolbar">
                        <ul>
                            <li @click="editChange('undo',null)"><img src="/aiwriter/img/工具栏/撤销.svg" alt=""></li>
                            <li @click="editChange('redo',null)"><img src="/aiwriter/img/工具栏/重做.svg" alt=""></li>
                            <li @click="editChange('removeFormat',null)"><img src="/aiwriter/img/工具栏/清除格式.svg" alt=""></li>
                            <li @click="editChange('bold',null)"><img src="/aiwriter/img/工具栏/加粗.svg" alt=""></li>
                            <li @click="editChange('italic',null)"><img src="/aiwriter/img/工具栏/斜体.svg" alt=""></li>
                            <li @click="editChange('underline',null)"><img src="/aiwriter/img/工具栏/下划线.svg" alt=""></li>
                            <li @click="editChange('foreColor','#ff0000')"><img src="/aiwriter/img/工具栏/字体颜色.svg" alt=""></li>
                            <li @click="editChange('justifyRight',null)"><img src="/aiwriter/img/工具栏/居右对齐.svg" alt=""></li>
                            <li @click="editChange('justifyCenter',null)"><img src="/aiwriter/img/工具栏/居中对齐.svg" alt=""></li>
                            <li @click="editChange('justifyLeft',null)"><img src="/aiwriter/img/工具栏/居左对齐.svg" alt=""></li>
                            <li @click="editChange('justifyFull',null)"><img src="/aiwriter/img/工具栏/两端对齐.svg" alt=""></li>
                            <li><img src="/aiwriter/img/工具栏/图片.svg" alt=""></li>
                            <li><img src="/aiwriter/img/工具栏/视频.svg" alt=""></li>
                            <li><img src="/aiwriter/img/工具栏/音频.svg" alt=""></li>
                        </ul>
                    </div>

                    <div id="formwork-text-ai" class="formwork-text" contenteditable="true" spellcheck="true" v-html="quickContent()">

                    </div>

                </div>
            </div>
        </div>

        <!-- 智能写稿部分 -->
        <div class="row" v-cloak :class="{'notShow':navChangeInfo[1]}">
            <!-- 智能写稿左侧 -->
            <div class="context-left col-md-2  col-xs-2">
                <div class="context-left-nav">
                    <ul class="context-left-ul">
                        <li class="left-li1" :class="{'context-left-li':stepFont[0]}"><img :src="stepImg[0]" alt="">选择类型</li>
                        <li class="left-li2" :class="{'context-left-li':stepFont[1]}"><img :src="stepImg[1]" alt="">提交素材</li>
                        <li class="left-li3" :class="{'context-left-li':stepFont[2]}"><img :src="stepImg[2]" alt="">审核发布</li>
                    </ul>
                </div>
            </div>

            <div class="context-center col-md-8 col-xs-8">
                <!-- 智能成稿选择类型 -->
                <div class="context-center-body" v-cloak :class="{'notShow':selectShow}">
                    <div class ="context-center-top clearfix">
                        <div class ="b1">
                            <b>选择类型</b>
                        </div>
                        <button id="next-page" @click="nextPage"><b id="b2">下一步</b></button>
                    </div>

                    <div id="context-center-buttom">
                        <div class="context-center-input">
                            <input type="text" placeholder="请输入文章标题">
                        </div>
                        <div id="context-center-select">
                            <span>请选择稿件输出格式</span>
                        </div>
                        <div id="context-center-button">
                            <label class="radio-inline">
                                <input type="radio" @change='showVideo' name="type" id="inlineRadio1" v-model="typeInfo.type" value="text"> 纯文字
                            </label>
                            <label class="radio-inline">
                                <input type="radio" @change='showVideo' name="type" id="inlineRadio2" v-model="typeInfo.type" value="img"> 图文
                            </label>
                            <label class="radio-inline">
                                <input type="radio" @change='showVideo' name="type" id="inlineRadio3" v-model="typeInfo.type" value="video"> 视频
                            </label>
                        </div>
                        <div id="video-select" v-cloak :class="{'notShow':videoNotShow}">
                            <div id="context-video-select" class="viedo-select-c">
                                <span>视频时长</span>
                            </div>
                            <div id="context-video-time" class="context-video">
                                <label class="radio-inline">
                                    <input type="radio" name="videoTimeSelect" id="inlineRadio4" v-model="typeInfo.videoTimeType" value="defaut"> 系统默认
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="videoTimeSelect" id="inlineRadio5" v-model="typeInfo.videoTimeType" value="self"> 自定义
                                </label>
                                <input type="text" name="videoTime" v-model="typeInfo.videoTime"   placeholder="30">
                            </div>
                            <div id="context-video-type" class="viedo-select-c">
                                <span>视频比例</span>
                            </div>
                            <div id="context-video-type-select" class="context-video">
                                <label class="radio-inline">
                                    <input type="radio" name="videoRate" v-model="typeInfo.videoRate" id="inlineRadio6" value="1">横屏:720P
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="videoRate" v-model="typeInfo.videoRate" id="inlineRadio7" value="2">横屏:1080P
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="videoRate" v-model="typeInfo.videoRate" id="inlineRadio8" value="3">竖屏:720P
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="videoRate" v-model="typeInfo.videoRate" id="inlineRadio9" value="4">竖屏:1080P
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 智能成稿提交稿件 -->
                <div class="context-center-body" v-cloak :class="{'notShow':suCaiShow}">
                    <div class ="context-center-top clearfix">
                        <div class ="b1">
                            <ul id="putSelect">
                                <li :class="{'putSelectLi':putSelectLi[0]}" @click="selfPut">素材上传</li>
                                <li :class="{'putSelectLi':putSelectLi[1]}" @click="hotSearch">热点搜索</li>
                            </ul>
                        </div>
                        <button @click="checkG"><b>下一步</b></button>
                        <button class="prePage" @click="preTypePage"><b>返回</b></button>
                    </div>
                    <div v-cloak :class="{'notShow':hideSuCai}">
                        <!-- 素材提交框 -->
                        <div id="selfPutBox">
                            <div id="sucaiImgList">
                                <ul class="clearfix">
                                    <li><img src="/aiwriter/img/素材上传页/文稿.svg" alt=""></li>
                                    <li><img src="/aiwriter/img/素材上传页/图片.svg" alt=""></li>
                                    <li><img src="/aiwriter/img/素材上传页/视频.svg" alt=""></li>
                                    <li><img src="/aiwriter/img/素材上传页/音频.svg" alt=""></li>
                                </ul>
                            </div>
                            <div class="putSuCai">
                                <button @click = "startPut">点此上传素材</button>
                            </div>
                            <div class="textList">
                                <ul class="clearfix">
                                    <li><span>文本(txt,doc)</span></li>
                                    <li><span>图片(jpg,png)</span></li>
                                </ul>
                                <ul class="clearfix">
                                    <li><span>视频(avi,mp4,wmv)</span></li>
                                    <li><span>音频(wav,mp3)</span></li>
                                </ul>
                            </div>
                            <div class="Liner">
                                <div class=""></div>
                            </div>
                            <div class="suCaiLinkImg">
                                <img src="/aiwriter/img/素材上传页/链接.svg" alt="">
                            </div>
                            <div class="sucaiDescripet">
                                输入网页链接上传素材
                            </div>
                            <div class="linkSearch">
                                <div class="linkSearchCenter clearfix">
                                    <input type="text" placeholder="当前仅支持“百家号、人民号”网页链接">
                                    <button>上传</button>
                                </div>
                            </div>
                        </div>

                        <!-- 显示当前素材 -->
                        <div class="sucaiList-put">
                            <div>
                                <b>当前素材</b>
                                <span style="font-size: 18px;">{{suCaiInfo.count}}个文件</span>
                                <button class="clearAll">
                                    <img class="clearImg" src="/aiwriter/img/其他/清空所有.svg" alt="">清空所有
                                </button>
                            </div>
                            <div class="currentSuCaiList">
                                <ul class="currentSuCaiListUL clearfix">
                                    <li v-for="(item,index) in suCaiInfo.type" :key="item.id">
                                        <img :src="showSuCaiImg(item)" alt="">
                                        <div style="font-size: 10px;">
                                            {{item}}
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div v-cloak :class="{'notShow':hideHotSearch}">
                        <div class="QWFixWindow">
                            <div class ="QWFixWindowInner" :class="{'SHFixWindowInnerF':quickWriteFixTop}">
                                <div class="hotSearch-top" v-cloak :class="{'notShow':hotSearchFixer.flag}">
                                    <div class="hotSearch-top-inner">
                                        <ul class="clearfix">
                                            <li><img src="/aiwriter/img/其他/全网热搜.jpg" alt=""></li>
                                            <li><img src="/aiwriter/img/其他/突发事件.jpg" alt=""></li>
                                            <li><img src="/aiwriter/img/其他/全网热搜.jpg" alt=""></li>
                                            <li><img src="/aiwriter/img/其他/突发事件.jpg" alt=""></li>
                                        </ul>
                                    </div>
                                    <div class="hotSearch">
                                        <div class="linkSearch hotLink">
                                            <div class="linkSearchCenter clearfix">
                                                <input type="text" placeholder="输入关键词搜索相关时事热点">
                                                <button>热点搜索</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="hotFix" @click="hotFix">
                                    <img :src="hotSearchFixer.link" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="hotContext" v-for="(item,index) in hotContent" :key="item.id">
                            <!-- <input type="checkbox" name="useFile"> -->
                            <div class="hotContext-inner" contenteditable="true" spellcheck="true" v-html="item.text"></div>
                            <span class="hotContext-close" style="font-size: 30px;"  @click="hotContextClose(index)">×</span>
                        </div>
                        <div class="hotAppend" @click="createNewHot">
                            ＋
                        </div>
                    </div>
                </div>
                <!-- 智能成稿审核发布 -->
                <div class="context-center-body" v-cloak :class="{'notShow':checkPage}">
                    <!-- 头部 -->
                    <div class ="context-center-top clearfix">
                        <div class ="b1">
                            <b>审核发布</b>
                        </div>
                        <button @click="PaperSave"><b>保存</b></button>
                        <button class="prePage" @click="preTypePage1"><b>返回</b></button>
                    </div>
                    <!-- 审核选项卡 -->
                    <div class ="QWFixWindow">
                        <div class ="QWFixWindowInner" :class="{'SHFixWindowInnerF':quickWriteFixTop}">
                            <div class="formwork clearfix" v-cloak :class="{'notShow':checkToolFixer.flag}">
                                <!-- 智能纠错 -->
                                <div v-cloak :class="{'notShow':!formworkSelectButton[0]}">
                                    <ul class="checku1 clearfix">
                                        <li>全部纠错</li>
                                        <li>全部忽略</li>
                                        <li>重新检测</li>
                                    </ul>
                                    <img src="" alt="">
                                    <ul class="checku2 clearfix">
                                        <li>疑似错误</li>
                                        <li>建议改动</li>
                                        <li>操作</li>
                                    </ul>
                                    <div class="errorText">
                                        <ul class="checku3 clearfix">
                                            <li>文义</li>
                                            <li>文艺</li>
                                            <li>
                                                <span>忽略</span><span>纠正</span>
                                            </li>
                                        </ul>
                                        <ul class="checku3 clearfix">
                                            <li>兹养</li>
                                            <li>滋养</li>
                                            <li>
                                                <span>忽略</span><span>纠正</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- 原创性分析 -->
                                <div v-cloak :class="{'notShow':!formworkSelectButton[1]}">
                                    <div class="repeat-top">
                                        <span>以下段落原创性较低</span>
                                        <span class="recheck">重新检测</span>
                                    </div>
                                    <div class="repeat-text">
                                        <div class="repeat-item">
                                            <div class="repeat-msg">
                                                贾大山擅长写小说，对人民群众反映强烈的社会问题，有着鞭辟入里的见解。他通过一篇篇小说，歌颂真善美，鞭挞假恶丑，鼓舞着人们对生活的信心。被举荐为当地文化局长后，贾大…
                                            </div>
                                            <span class="xiangsidu">87%</span>
                                            <span class="xiangsi">相似度</span>
                                        </div>
                                        <div class="repeat-item">
                                            <div class="repeat-msg">
                                                演员李雪健曾向总书记汇报自己扮演焦裕禄、杨善洲等典型人物的心得体会。听罢，总书记充分肯定了《焦裕禄》《杨善洲》两部作品。在他看来，这两部作品真实刻…
                                            </div>
                                            <span class="xiangsidu">69%</span>
                                            <span class="xiangsi">相似度</span>
                                        </div>
                                        <div class="repeat-item">
                                            <div class="repeat-msg">
                                                一直以来，习近平总书记十分关心我国文艺事业发展。2014年10月，出席文艺工作座谈会；2016年11月，在中国文联十大、中国作协九大开幕式上发表重要讲话；2019…
                                            </div>
                                            <span class="xiangsidu">83%</span>
                                            <span class="xiangsi">相似度</span>
                                        </div>
                                    </div>
                                </div>

                                <!-- 敏感词查找 -->
                                <div v-cloak :class="{'notShow':!formworkSelectButton[2]}">
                                    <div class="repeat-top">
                                        <span>以下段落原创性较低</span>
                                        <span class="recheck">重新检测</span>
                                    </div>
                                    <div class="checkMGC">
                                        <span>XXXXX</span>
                                        <span>XXXXX</span>
                                        <span>XXXXX</span>
                                        <span>XXXXX</span>
                                        <span>XXXXX</span>
                                        <span>XXXXX</span>
                                        <span>XXXXX</span>
                                    </div>
                                </div>

                                <div class="formwork-select">
                                    <ul class="clearfix">
                                        <li :class="{'li-select':formworkSelectButton[0]}" @click="toggleType(0)">智能纠错</li>
                                        <li :class="{'li-select':formworkSelectButton[1]}" @click="toggleType(1)">原创性分析</li>
                                        <li :class="{'li-select':formworkSelectButton[2]}" @click="toggleType(2)">敏感词查找</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="checkFix" @click="checkToolFix">
                                <img :src="checkToolFixer.link" alt="">
                            </div>
                        </div>
                    </div>


                    <!-- 稿件展示 -->
                    <div class="formwork-button">
                        <!-- 标题 -->
                        <div class="formwork-button-inner">
                            <div class="context-center-input" style="width: 100%;">
                                <input type="text" placeholder="请输入文章标题">
                            </div>
                        </div>
                        <!-- 评分 -->
                        <div class="textScore" :class="{'textScoreFix':!fixSocre,'textScoreFixer':fixSocre}">
                            <span>实时评分:85</span>
                        </div>
                        <!-- 工具条 -->
                        <div class="formwork-toolbar">
                            <ul>
                                <li @click="editChange('undo',null)"><img src="/aiwriter/img/工具栏/撤销.svg" alt=""></li>
                                <li @click="editChange('redo',null)"><img src="/aiwriter/img/工具栏/重做.svg" alt=""></li>
                                <li @click="editChange('removeFormat',null)"><img src="/aiwriter/img/工具栏/清除格式.svg" alt=""></li>
                                <li @click="editChange('bold',null)"><img src="/aiwriter/img/工具栏/加粗.svg" alt=""></li>
                                <li @click="editChange('italic',null)"><img src="/aiwriter/img/工具栏/斜体.svg" alt=""></li>
                                <li @click="editChange('underline',null)"><img src="/aiwriter/img/工具栏/下划线.svg" alt=""></li>
                                <li @click="editChange('foreColor','#ff0000')"><img src="/aiwriter/img/工具栏/字体颜色.svg" alt=""></li>
                                <li @click="editChange('justifyRight',null)"><img src="/aiwriter/img/工具栏/居右对齐.svg" alt=""></li>
                                <li @click="editChange('justifyCenter',null)"><img src="/aiwriter/img/工具栏/居中对齐.svg" alt=""></li>
                                <li @click="editChange('justifyLeft',null)"><img src="/aiwriter/img/工具栏/居左对齐.svg" alt=""></li>
                                <li @click="editChange('justifyFull',null)"><img src="/aiwriter/img/工具栏/两端对齐.svg" alt=""></li>
                                <li><img src="/aiwriter/img/工具栏/图片.svg" alt=""></li>
                                <li><img src="/aiwriter/img/工具栏/视频.svg" alt=""></li>
                                <li><img src="/aiwriter/img/工具栏/音频.svg" alt=""></li>
                            </ul>
                        </div>

                        <div id="formwork-text-quick" class="formwork-text" contenteditable="true" spellcheck="true" v-html="quickContent()">

                        </div>

                    </div>
                </div>
            </div>
        </div>

        <!-- 手动写稿部分 -->
        <div class="row" v-cloak :class="{'notShow':navChangeInfo[2]}">
            <div class="col-md-2  col-xs-2"></div>
            <div class="context-center col-md-8 col-xs-8">
                <div class ="context-center-top clearfix">
                    <div class ="b1">
                        <b>手动写稿</b>
                    </div>
                    <button><b>保存</b></button>
                </div>
                <div class ="QWFixWindow">
                    <div class ="QWFixWindowInner" :class="{'QWFixWindowInnerF':quickWriteFixTop}">
                        <div class="hotSearch-top" v-cloak :class="{'notShow':sdTopFixer.flag}">
                            <div class="hotSearch-top-inner">
                                <ul class="clearfix">
                                    <li><img src="/aiwriter/img/其他/全网热搜.jpg" alt=""></li>
                                    <li><img src="/aiwriter/img/其他/突发事件.jpg" alt=""></li>
                                    <li><img src="/aiwriter/img/其他/全网热搜.jpg" alt=""></li>
                                    <li><img src="/aiwriter/img/其他/突发事件.jpg" alt=""></li>
                                </ul>
                            </div>
                            <div class="sdTop-button clearfix">
                                <ul class="sdToolSelect">
                                    <li>智能纠错</li>
                                    <li>原创性分析</li>
                                    <li>敏感词查找</li>
                                </ul>
                                <!-- <div class="linkSearch hotLink">
                                    <div class="linkSearchCenter clearfix">
                                        <input type="text" placeholder="输入关键词搜索相关时事热点">
                                        <button>热点搜索</button>
                                    </div>
                                </div> -->
                                <div class="sdlinkSearchCenter clearfix">
                                    <input type="text" placeholder="输入关键词搜索相关时事热点">
                                    <button>热点搜索</button>
                                </div>
                            </div>
                        </div>
                        <div class="hotFix" @click="sdTopFix">
                            <img :src="sdTopFixer.link" alt="">
                        </div>
                    </div>
                </div>

                <div class="formwork-button">
                    <!-- 标题 -->
                    <div class="formwork-button-inner">
                        <div class="context-center-input" style="width: 100%;">
                            <input type="text" placeholder="请输入文章标题">
                        </div>
                    </div>

                    <!-- 工具条 -->
                    <div class="formwork-toolbar">
                        <ul>
                            <li @click="editChange('undo',null)"><img src="/aiwriter/img/工具栏/撤销.svg" alt=""></li>
                            <li @click="editChange('redo',null)"><img src="/aiwriter/img/工具栏/重做.svg" alt=""></li>
                            <li @click="editChange('removeFormat',null)"><img src="/aiwriter/img/工具栏/清除格式.svg" alt=""></li>
                            <li @click="editChange('bold',null)"><img src="/aiwriter/img/工具栏/加粗.svg" alt=""></li>
                            <li @click="editChange('italic',null)"><img src="/aiwriter/img/工具栏/斜体.svg" alt=""></li>
                            <li @click="editChange('underline',null)"><img src="/aiwriter/img/工具栏/下划线.svg" alt=""></li>
                            <li @click="editChange('foreColor','#ff0000')"><img src="/aiwriter/img/工具栏/字体颜色.svg" alt=""></li>
                            <li @click="editChange('justifyRight',null)"><img src="/aiwriter/img/工具栏/居右对齐.svg" alt=""></li>
                            <li @click="editChange('justifyCenter',null)"><img src="/aiwriter/img/工具栏/居中对齐.svg" alt=""></li>
                            <li @click="editChange('justifyLeft',null)"><img src="/aiwriter/img/工具栏/居左对齐.svg" alt=""></li>
                            <li @click="editChange('justifyFull',null)"><img src="/aiwriter/img/工具栏/两端对齐.svg" alt=""></li>
                            <li><img src="/aiwriter/img/工具栏/图片.svg" alt=""></li>
                            <li><img src="/aiwriter/img/工具栏/视频.svg" alt=""></li>
                            <li><img src="/aiwriter/img/工具栏/音频.svg" alt=""></li>
                        </ul>
                    </div>

                    <div id="formwork-text-shoudong" class="formwork-text" contenteditable="true" spellcheck="true">

                    </div>

                </div>
            </div>
        </div>

        <!-- 素材库 -->
        <div class="row" v-cloak :class="{'notShow':navChangeInfo[3]}">
            <!-- 素材库左侧 -->
            <div class="context-left col-md-2  col-xs-2">
                <div class="context-left-nav">
                    <ul class="context-left-ul">
                        <li class="left-li2" @click="togCS('公共素材')" :class="{'context-left-li':SCKFont[0]}"><img :src="SCKImg[0]" alt="">公共素材</li>
                        <li class="left-li1" @click="togCS('个人素材')" :class="{'context-left-li':SCKFont[1]}"><img :src="SCKImg[1]" alt="">个人素材</li>
                    </ul>
                </div>
            </div>
            <div class="context-center col-md-8 col-xs-8">
                <div class="SCK-top">
                    <span style="font-size:30px"><b>{{SCKTag}}</b></span>
                    <span style="font-size:18px">2个素材</span>
                    <button class="GJ-top-bt">搜索</button>
                    <input class="GJ-top-in" type="text" placeholder="输入搜索素材的名称">
                </div>
                <div class="SCK-inner">
                    <ul class="SCK-nav clearfix">
                        <li class="SCK-nav-li">文稿</li>
                        <li>图片</li>
                        <li>音频</li>
                        <li>视频</li>
                        <li>模板</li>
                    </ul>
                </div>
                <div class="SCK-common">
                    <ul class="clearfix">
                        <li v-for="(item,index) in commonList" @click="commonShow(index)">
                            <img :src="showSuCaiImg(item.name)" alt="">
                            <span>...</span>
                            <div :title="item.name">{{item.name}}</div>
                            <div v-if="SCKTag=='公共素材'">
                                <div class="common-select" :class="{'notShow':item.notShow}">
                                    <div>下载</div>
                                    <div>添加至个人素材</div>
                                </div>
                            </div>
                            <div v-if="SCKTag=='个人素材'">
                                <div class="common-select" :class="{'notShow':item.notShow}">
                                    <div>下载</div>
                                    <div>删除</div>
                                    <div>重命名</div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- 稿件管理 -->
        <div class="row" v-cloak :class="{'notShow':navChangeInfo[4]}">
            <!-- 稿件管理左侧 -->
            <div class="context-left col-md-2  col-xs-2">
                <div class="context-left-nav">
                    <ul class="context-left-ul">
                        <li class="left-li2" @click="togGJ('图片稿件')" :class="{'context-left-li':GJFont[0]}"><img :src="GJImg[0]" alt="">图片稿件</li>
                        <li class="left-li1" @click="togGJ('视频稿件')" :class="{'context-left-li':GJFont[1]}"><img :src="GJImg[1]" alt="">视频稿件</li>
                    </ul>
                </div>
            </div>

            <div class="context-center col-md-8 col-xs-8">
                <div class="context-GJ">
                    <div class="GJ-top clearfix">
                        <span class="GJ-top-s1" style="font-size:30px"><b>{{GJInfo.GJTag}}</b></span>
                        <span class="GJ-top-s2" style="font-size:18px">{{GJList.length}}篇稿件</span>
                        <button class="GJ-top-bt">搜索</button>
                        <input class="GJ-top-in" type="text" placeholder="输入搜索稿件的名称">
                    </div>
                    <div class="GJ-sort clearfix">
                        <span class="GJ-sort-s1"  @click="GJSortSelect=!GJSortSelect">∨</span>
                        <span>{{GJInfo.GJSorted}}</span>
                        <div class="GJ-sort-select" :class="{'notShow':GJSortSelect}">
                            <div>按创建时间排序</div>
                            <div>按评分排序</div>
                        </div>
                    </div>
                    <div class="GJ-show">
                        <div class="GJ-sample" v-for="(item,index) in GJList" :key="item.id">
                            <img :src="item.img" alt="">
                            <span class="GJ-sample-s1">{{item.name}}</span>
                            <span class="GJ-sample-s2">{{item.time}}</span>
                            <span class="GJ-sample-s3">系统评分&nbsp;{{item.score}}</span>
                            <div class="GJ-sample-d1" @click="GJSampleSelect(index)">...</div>
                            <div class="GJ-sample-select" :class="{'notShow':item.hide}">
                                <div>删除</div>
                                <div>导出</div>
                                <div>分享</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 素材提交模态框 -->
        <input id="fileUp" type="file" name="files" style="display:none" @change="fileUpOver">
        <div class="Cover" v-cloak :class="{'hide':putControl.Cover}"></div>
        <div class="Modal" v-cloak :class="{'hide':putControl.Modal}">
            <div class="modal-top">
                <ul class="clearfix">
                    <li class="modal-select">上传素材</li>
                    <li>本地素材库</li>
                    <li>公共素材库</li>
                    <span @click="cancelFilePut">×</span>
                </ul>
            </div>
            <div class="modal-center">
                <ul class="clearfix">
                    <li @click="fileUpbtn">
                        <div class="fileItem">+</div>
                        <span class="filetitle">本地上传</span>
                    </li>
                    <li>
                        <div class="fileItem">
                            <input class="filechecked" type="checkbox">
                            <img class="fileImg" src="/aiwriter/img/素材上传页/上传/文稿.svg" alt="">
                        </div>
                        <span class="filetitle" title="本地上传">本地上传</span>
                    </li>
                </ul>
            </div>
            <div class="modal-button">
                <input class="modal-button-i1" type="checkbox">
                <span class="modal-button-s1">保存至本地素材库</span>
                <input class="modal-button-i2" type="checkbox">
                <span class="modal-button-s2">保存至公共素材库</span>
                <button class="filequit" @click="cancelFilePut">取消</button>
                <button class="fileput">确定</button>
            </div>
        </div>
        <!-- 文稿生成确认 -->
        <div class="Generate" v-cloak :class="{'hide':putControl.Generate}">
            <span class="Generate-title">根据目前素材生成稿件</span>
            <button class="Generate-b1" @click="cancelGenerate">取消</button>
            <button class="Generate-b2" @click="nextStep">确认</button>
        </div>
        <!-- 文稿内容show -->
        <div id="tmp" style="display: none"></div>
    </div>
</div>
<script src="/aiwriter/js/ai.js"></script>
</body>
</html>