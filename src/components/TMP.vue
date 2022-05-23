<template>
  <!-- 审核选项卡 -->
        <div class ="QWFixWindow">
            <div class ="QWFixWindowInner">
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
</template>

<script>
export default {

}
</script>

<style>
/* 审核卡 */
/* 审核发布 */
.li-select{
    background-color: #015478;
    color:white !important;
}
.formwork{
    height: 410px;
    background-color: white;
    padding:0px 60px;
    padding-top: 20px;
    position: relative;
    width: 100%;
}
.formwork-select{
    width: 300px;
    position: absolute;
    left: 50%;
    margin-left: -150px;
    top:368px;
}
.formwork-select ul{
    display:block;
    height: 32px;
    background-color: rgb(221,221,221);
    border-radius: 32px;
}
.formwork-select ul li{
    float: left;
    width: 100px;
    height: 32px;
    line-height: 32px;
    text-align:center;
    border-radius: 32px;
    font-size: 12px;
    color:rgb(48,48,48);
}
.checkFix{
    width: 120px;
    height: 20px;
    background-color: rgb(1,84,120);
    border-bottom-left-radius: 120px;
    border-bottom-right-radius: 120px;
    left: 50%;
    margin-left: -60px;
    position: relative;
}
.checkFix:hover{
    cursor: pointer;
}
.checkFix img{
    display:block;
    height: 20px;
    margin: auto;
}

.checku1 li{
    float: left;
    width: 10%;
    height: 40px;
    text-align: center;
    line-height: 40px;
    color: rgb(1,84,120);
}
.checku2{
    margin-top: -20px;
    background-color: rgb(240,240,240);
}
.checku2 li{
    float: left;
    width: 33.33%;
    height: 40px;
    text-align: center;
    line-height: 40px;
}
.checku2 li:hover{
    cursor: default;
}
.errorText{
    height: 240px;
    overflow-y: auto;
    margin-top: -10px;
}
.checku3{
    margin-bottom: 0px;
    border-bottom: 1px solid rgb(241, 231, 231);
}
.checku3 li{
    float: left;
    width: 33.33%;
    height: 40px;
    text-align: center;
    line-height: 40px;
}
.checku3 li:hover{
    cursor: default;
}
.checku3 li span{
    margin: 8px;
    color: rgb(1,84,120);
}
.checku3 li span:hover{
    cursor: pointer;
}
.repeat-top{
    margin-top: 10px;
}
.repeat-top span{
    margin-right: 10px;
}
.recheck{
    color: rgb(1,84,120);
}
.recheck:hover{
    cursor: pointer;
}
.repeat-text{
    height: 290px;
    margin-top: 20px;
    overflow-y: auto;
}
.repeat-item{
    width: 100%;
    height: 60px;
    border-bottom: 1px solid #dbdbdb;
    position: relative;
}
.repeat-msg{
    width: 90%;
    position: absolute;
    top: 0px;
    left: 0px;
    height: 60px;
    overflow-y: auto;
}
.xiangsidu{
    width: 10%;
    position: absolute;
    top: 0px;
    right: 0px;
    color: red;
    text-align: center;
}
.xiangsi{
    width: 10%;
    position: absolute;
    bottom: 20px;
    right: 0px;
    text-align: center;
}
.checkMGC{
    height: 290px;
    margin-top: 20px;
    overflow-y: auto;
}
.checkMGC span{
    float: left;
    margin-right: 20px;
    padding:5px 10px;
    color: red;
    background-color: rgb(253,241,241);
}
/* 审核卡固定窗口 */
.QWFixWindow{
    margin-top: 50px;
}
.QWFixWindowInner{
    width: 1030px;
    margin:auto;
}
.notShow{
    display: none;
}
</style>