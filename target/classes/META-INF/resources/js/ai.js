new Vue({
    el:"#root",
    data:{
        username:"admin",
        // 导航切换
        navChangeInfo:[false,true,true,true,true],
        navItems:["快速成稿","智能成稿","手动写稿","素材库","稿件管理"],
        navActive:0,
        // 快速写稿收起展开
        quickWriteFixer:{
            flag:false,
            link:"/img/其他/浮窗-收回.svg"
        },
        // 快速写稿切换信息
        quickWriteNavInfo:{
            name:"实时热点",
            CK:"本地"
        },
        // 存储后台发来的模板信息
        quickWriteModelList:[],
        //审核发布收起展开
        checkToolFixer:{
            flag:false,
            link:"/img/其他/浮窗-收回.svg"
        },
        // 快速写稿模板选择、上传素材、敏感词查找
        formworkSelectButton:[true,false,false],
        // 类型选择左侧字体切换
        stepFont:[true,false,false],
        // 素材左侧
        SCKFont:[true,false],
        // 稿件左侧
        GJFont:[true,false],
        GJImg:["/img/其他/日志选中.svg","/img/其他/视频未选中.svg"],
        GJInfo:{
            GJTag:"图片稿件",
            GJSorted:"按创建时间排序"
        },
        // 素材左侧图片
        SCKImg:["/img/其他/素材选中.svg","/img/其他/本地未选中.svg"],
        // 类型选择左侧图片切换
        stepImg:["/img/其他/选择类型-选中.svg","/img/其他/提交素材-未选中.svg","/img/其他/审核发布-未选中.svg"],
        // 视频类型选择
        videoNotShow:true,
        // 表单数据
        typeInfo:{
            type:"text",
            videoTimeType:"defaut",
            videoTime:30,
            videoRate:"1"
        },
        // 隐藏类型选择
        selectShow:false,
        // 隐藏素材提交
        suCaiShow:true,
        // 隐藏稿件审核
        checkPage:true,
        // 隐藏提交自己得素材
        hideSuCai:false,
        // 隐藏热点搜索
        hideHotSearch:true,
        // 提交方式选择
        putSelectLi:[true,false],
        suCaiInfo:{
            count:2,
            type:["1.txt","2.avi"]
        },
        //控制素材提交模态框
        putControl:{
            Modal:true,
            Cover:true,
            Generate:true
        },
        // 隐藏热点搜索框
        hotSearchFixer:{
            flag:false,
            link:"/img/其他/浮窗-收回.svg"
        },
        // 隐藏手动写稿顶部
        sdTopFixer:{
            flag:false,
            link:"/img/其他/浮窗-收回.svg"
        },
        // 是否固定评分
        fixSocre:false,
        // 是否固定快速写稿顶部
        quickWriteFixTop:false,
        //热点内容
        hotContentID:1,
        hotContent:[{"id":0,"text":"澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预计新增运营里程56.1公里。这些线路中，不仅有3条贯穿城市南北的“大动脉”，也有打通既有轨道路网断点的剩余段，更有为北京冬奥会、京津冀协同发展服务的交通线。昌平线南延（清河站）连通京张高铁，将实现高铁与地铁融合。昌平线将从西二旗延伸至清河站，成为接驳京张高铁的又一条地铁线路，直接为北京冬奥会和京冀两地服务。清河站同时与既有13号线、规划中19号线支线实现换乘。16号线中段（玉渊潭东门站）全长约1公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预计新增运营里程56.1公里。这些线路中，不仅有3条贯穿城市南北的“大动脉”，也有打通既有轨道路网断点的剩余段，更有为北京冬奥会、京津冀协同发展服务的交通线。昌平线南延（清河站）连通京张高铁，将实现高铁与地铁融合。昌平线将从西二旗延伸至清河站，成为接驳京张高铁的又一条地铁线路，直接为北京冬奥会和京冀两地服务。清河站同时与既有13号线、规划中19号线支线实现换乘。16号线中段（玉渊潭东门站）全长约1公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预计新增运营里程56.1公里。这些线路中，不仅有3条贯穿城市南北的“大动脉”，也有打通既有轨道路网断点的剩余段，更有为北京冬奥会、京津冀协同发展服务的交通线。昌平线南延（清河站）连通京张高铁，将实现高铁与地铁融合。昌平线将从西二旗延伸至清河站，成为接驳京张高铁的又一条地铁线路，直接为北京冬奥会和京冀两地服务。清河站同时与既有13号线、规划中19号线支线实现换乘。16号线中段（玉渊潭东门站）公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预全长约1公里，从甘家口站至玉渊潭东门站。开通后。澎湃新闻、16号线中段（玉渊潭东门站）9条线（段），预计新增运营里程56.1公里。这些线路中，不仅有3条贯穿城市南北的“大动脉”，也有打通既有轨道路网断点的剩余段，更有为北京冬奥会、京津冀协同发展服务的交通线。昌平线南延（清河站）连通京张高铁，将实现高铁与地铁融合。昌平线将从西二旗延伸至清河站，成为接驳京张高铁的又一条地铁线路，直接为北京冬奥会和京冀两地服务。清河站同时与既有13号线、规划中19号线支线实现换乘。16号线中段（玉渊潭东门站）全长约1公里，从甘家口站至玉渊潭东门站。开通后。"}],
        // 公共素材-文稿
        commonList:[
            {name:"1.txt",notShow:"true"},
            {name:"2.txt",notShow:"true"}
        ],
        // 素材库类型
        SCKTag:"公共素材",
        // 稿件信息
        GJList:[{
            img:"/img/其他/GJimg.png",
            name:"西安市处理疫情防控不力相关负责人26名",
            time:"2021-12-24 11:07:49",
            score:83,
            hide:true
        },{
            img:"/img/其他/GJimg.png",
            name:"习近平念兹在兹的人民文艺",
            time:"2021-12-13 12:27:19",
            score:88,
            hide:true
        }],
        // 稿件选择排序列表隐藏与显示
        GJSortSelect:true,
    },
    methods:{
        navChange(index){
            this.navActive = index;
            this.navChangeInfo.splice(0,5,true,true,true,true,true)
            this.navChangeInfo.splice(index,1,false)
        },
        // 下一页
        nextPage(event){
            event.preventDefault();
            this.stepFont.splice(0,1,false);
            this.stepFont.splice(1,1,true);
            this.stepImg.splice(0,1,"/img/其他/选择类型-未选中.svg");
            this.stepImg.splice(1,1,"/img/其他/提交素材-选中.svg");
            this.selectShow = true
            this.suCaiShow = false
        },
        // 切换视频类型选择
        showVideo(){
            if(this.typeInfo.type == "video"){
                this.videoNotShow = false;
            }else{
                this.videoNotShow = true;
            }
        },
        // 智能成稿
        nextStep(event){
            event.preventDefault();
            this.putControl.Generate = true
            this.putControl.Cover = true
            this.stepFont.splice(1,1,false);
            this.stepFont.splice(2,1,true);
            this.stepImg.splice(1,1,"/img/其他/提交素材-未选中.svg");
            this.stepImg.splice(2,1,"/img/其他/审核发布-选中.svg");
            this.suCaiShow = true;
            this.checkPage = false;
        },
        // 返回类型选择
        preTypePage(event){
            event.preventDefault();
            this.stepFont.splice(0,1,true);
            this.stepFont.splice(1,1,false);
            this.stepImg.splice(0,1,"/img/其他/选择类型-选中.svg");
            this.stepImg.splice(1,1,"/img/其他/提交素材-未选中.svg");
            this.suCaiShow = true
            this.selectShow = false
        },
        // 返回提交素材
        preTypePage1(event){
            event.preventDefault();
            this.stepFont.splice(1,1,true);
            this.stepFont.splice(2,1,false);
            this.stepImg.splice(1,1,"/img/其他/提交素材-选中.svg");
            this.stepImg.splice(2,1,"/img/其他/审核发布-未选中.svg");
            this.checkPage = true;
            this.suCaiShow = false;
        },
        //素材上传
        selfPut(){
            event.preventDefault();
            this.putSelectLi.splice(1,1,false);
            this.putSelectLi.splice(0,1,true);
            this.hideSuCai = false;
            this.hideHotSearch = true;
        },
        //热点搜索
        hotSearch(){
            event.preventDefault();
            this.putSelectLi.splice(1,1,true);
            this.putSelectLi.splice(0,1,false);
            this.hideSuCai = true;
            this.hideHotSearch = false;
        },
        // 展示素材库的内容
        showSuCaiImg(item){
            var end = item.lastIndexOf('.')
            var t = item.slice(end+1)
            if(t == "txt" || t == "doc"){
                return "/img/素材上传页/上传/文稿.svg"
            }else if(t == "avi" || t == "mp4"){
                return "/img/素材上传页/上传/视频.svg"
            }else if(t == "jpg" || t == "png"){
                return "/img/素材上传页/上传/图片.svg"
            }else{
                return "/img/素材上传页/上传/音频.svg"
            }
        },
        // 上传素材模态框
        startPut(event){
            event.preventDefault();
            this.putControl.Modal = false
            this.putControl.Cover = false
        },
        //取消文件上传
        cancelFilePut(event){
            event.preventDefault();
            this.putControl.Cover = true
            this.putControl.Modal = true
        },
        // 热点搜索折叠
        hotFix(){
            if(this.hotSearchFixer.flag == false){
                this.hotSearchFixer.link = "/img/其他/浮窗-展开.svg"
            }else{
                this.hotSearchFixer.link = "/img/其他/浮窗-收回.svg"
            }
            this.hotSearchFixer.flag = !this.hotSearchFixer.flag
        },
        // 删除引用的热点
        hotContextClose(index){
            this.hotContent.splice(index,1);
        },
        //创建新热点内容
        createNewHot(event){
            this.hotContent.push({"id":this.hotContentID,"text":""});
            this.hotContentID += 1;
        },
        //快速写稿收起展开
        quickWriteFix(){
            if(this.quickWriteFixer.flag == false){
                this.quickWriteFixer.link = "/img/其他/浮窗-展开.svg"
            }else{
                this.quickWriteFixer.link = "/img/其他/浮窗-收回.svg"
            }
            this.quickWriteFixer.flag = !this.quickWriteFixer.flag
        },
        //审核发布快速写稿收起展开
        checkToolFix(){
            if(this.checkToolFixer.flag == false){
                this.checkToolFixer.link = "/img/其他/浮窗-展开.svg"
            }else{
                this.checkToolFixer.link = "/img/其他/浮窗-收回.svg"
            }
            this.checkToolFixer.flag = !this.checkToolFixer.flag
        },
        //快速写稿模板选择和上传素材
        toggleType(index){
            if(index==0){
                this.formworkSelectButton.splice(0,3,false,false,false);
                this.formworkSelectButton.splice(0,1,true);
            }
            else if(index==1){
                this.formworkSelectButton.splice(0,3,false,false,false);
                this.formworkSelectButton.splice(1,1,true);
            }else{
                this.formworkSelectButton.splice(0,3,false,false,false);
                this.formworkSelectButton.splice(2,1,true);
            }
        },
        //快速写稿内容展示
        quickContent(){
            let data = `
            <html>
                <div id="title">
                    <var id="1"></var>会议于<var id="2"></var>召开
                </div>

                <div id="abstract">
                </div>

                <div id="time">
                </div>

                <div id="content">
                    <p>据<var id="3"></var>报道：<var id="4"></var>日<var id="5"></var>召开了<var id="6"></var>会议</p>

                    <p>会议认为，我国<var id="7"></var>工作取得显著成绩和进步，<var id="8"></var>改革迈出重大步伐<var id="9"></var>持续快速发展，
                    <var id="10"></var>领域对外开放稳步推进，<var id="11"></var>监管得到加强，<var id="12"></var>秩序明显好转，<var id="13"></var>在我国经
                    济社会发展中发择了重要作用。我们要认真总结<var id="14"></var>改革发展积累的成功经验，同时也要清醒地看到当前<var id="15"></var>
                    领域存在的矛盾和问题，充分认识<var id="16"></var>改革发展任务的重要性和艰巨性，坚持不懈地做好各项<var id="17"></var>工作。</p>

                    <p>会议指出，当前我国正处于全面建设小康社会的关键时期，对外开放进入新阶段。我国<var id="18"></var>领域面临着新情况新问题，
                    国际<var id="19"></var>发展也出现了新趋势。切实做好新形势下的<var id="20"></var>工作，全面推进<var id="21"></var>改革，促进
                    <var id="22"></var>业持续健康安全发展，对于实现国民经济叉好叉快发展，构建社会主义和谐社会，具有十分重要的意义。</p>
                    <br>
                    <img src="/img/其他/template1_0.png">
                    <br>
                    <p>会议强调，今后时期<var id="23"></var>工作的总体要求是：以邓小平理论和 “三个代表”重要思想为指导，全面落实科学发展观，
                    坚持以<var id="24"></var>改革开放和科技进步为动力，着力推进现代<var id="25"></var>体系和制度建设，着力提升<var id="26"></var>创
                    新能力和服务水平，着力提高<var id="27"></var>运行效率和<var id="28"></var>企业经营效益，着力加强<var id="29"></var>调控和监管，
                    着力维护<var id="30"></var>稳定和安全，显著增强我国<var id="31"></var>的综合实力、竞争力和抗风险能力，促进经济社会全面协调
                    可持续发展，充分发挥<var id="32"></var>对全面建设小康社会、加快推进社会主义现代化的重要作用。</p>
                </div>
            </html>`
            $("#tmp").html(" ");
            $("#tmp").html(data);
            $("var").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            return $("#content").prop('outerHTML');
        },
        // 保存文稿
        PaperSave(event){
            event.preventDefault();
        },
        // 固定布局
        windowScroll(e){
            let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
            if(scrollTop>=800){
                this.fixSocre = true;
            }else{
                this.fixSocre = false;
            }
            if(scrollTop>=384){
                this.quickWriteFixTop = true;
            }else{
                this.quickWriteFixTop = false;
            }
        },
        // 生成文稿确认
        checkG(event){
            event.preventDefault();
            this.putControl.Generate = false
            this.putControl.Cover = false
        },
        // 取消生成文稿提交
        cancelGenerate(event){
            event.preventDefault();
            this.putControl.Generate = true
            this.putControl.Cover = true
        },
        // 手动成稿顶部隐藏与展开
        sdTopFix(){
            if(this.sdTopFixer.flag == false){
                this.sdTopFixer.link = "/img/其他/浮窗-展开.svg"
            }else{
                this.sdTopFixer.link = "/img/其他/浮窗-收回.svg"
            }
            this.sdTopFixer.flag = !this.sdTopFixer.flag
        },
        // 公共素材下载与保存本地选择
        commonShow(index){
            this.commonList[index].notShow = !this.commonList[index].notShow
        },
        // 公共素材库和个人素材库切换
        togCS(name){
            this.SCKTag = name
            if(name=='公共素材'){
                this.SCKFont = [true,false]
                this.SCKImg=["/img/其他/素材选中.svg","/img/其他/本地未选中.svg"]
            }else{
                this.SCKFont = [false,true]
                this.SCKImg=["/img/其他/素材未选中.svg","/img/其他/本地选中.svg"]
            }
        },
        // 文本和视频稿件切换
        togGJ(name){
            this.GJInfo.GJTag = name
            if(name=='图片稿件'){
                this.GJFont = [true,false]
                this.GJImg=["/img/其他/日志选中.svg","/img/其他/视频未选中.svg"]
            }else{
                this.GJFont = [false,true]
                this.GJImg=["/img/其他/日志未选中.svg","/img/其他/视频选中.svg"]
            }
        },
        // 稿件功能选择
        GJSampleSelect(index){
            this.GJList[index].hide = !this.GJList[index].hide;
        },
        // 快速写稿导航切换
        quickWriteNav(name){
            this.quickWriteNavInfo.CK = "本地";
            this.quickWriteNavInfo.name = name;
        },
        quickWriteCKNav(name){
            this.quickWriteNavInfo.CK = name;
        },
        // 富文本工具栏字体变化
        editChange(com,value){
            document.execCommand(com,false,value);
        },
        // 文件上传
        fileUpbtn(){
            $("#fileUp").click();
        },
        fileUpOver(event){
            var fileObj = event.target.files[0]; // js 获取文件对象
            if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
                return;
            }
            var formFile = new window.FormData();
            formFile.append("files", fileObj); //加入文件对象
            $.ajax({
                url: "http://127.0.0.1:8080/filesUpload2TempPublicLib",
                type: "post",
                data: formFile,
                processData: false,
                contentType: false,
                success: function (data) {
                    alert("success")
                },
                error: function (e) {
                    alert("error")
                }
            });
        }
    },
    mounted() {
        window.addEventListener('scroll', this.windowScroll)
    }
})