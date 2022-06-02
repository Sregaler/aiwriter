<template>
<div class="row pt">
    <!-- 稿件管理左侧 -->
    <div class="context-left col-md-2  col-xs-2">
        <div class="context-left-nav">
            <ul class="context-left-ul">
                <li class="left-li2" @click="togGJ('本地素材')" :class="{'context-left-li':GJFont[0]}"><img :src="GJImg[0]" alt="">本地素材</li>
                <li class="left-li1" @click="togGJ('云端素材')" :class="{'context-left-li':GJFont[1]}"><img :src="GJImg[1]" alt="">云端素材</li>
            </ul>
        </div>
    </div>

    <div class="context-center col-md-8 col-xs-8">
        <div class="context-GJ">
            <div class="GJ-top clearfix">
                <span class="GJ-top-s1" style="font-size:30px"><b>{{GJInfo.GJTag}}</b></span>
                <span class="GJ-top-s2" :class="{'GJ-top-s2-a':suCaiType==0}" @click="searchInfo(0)" v-if="GJFont[0]">全部</span>
                <span class="GJ-top-s2" :class="{'GJ-top-s2-a':suCaiType==1}" @click="searchInfo(1)">图片</span>
                <span class="GJ-top-s2" :class="{'GJ-top-s2-a':suCaiType==2}" @click="searchInfo(2)">视频</span>
                <span class="GJ-top-s2" :class="{'GJ-top-s2-a':suCaiType==3}" @click="searchInfo(3)" v-if="GJFont[0]">音频</span>
                <span class="GJ-top-s2" :class="{'GJ-top-s2-a':suCaiType==4}" @click="searchInfo(4)" v-if="!GJFont[0]">主题</span>
                <button class="GJ-top-bt" @click="searchByname">搜索</button>
                <input class="GJ-top-in" type="text" v-model="searchname" placeholder="输入搜索素材的名称">
            </div>
            <div class="GJ-sort clearfix">
                <!-- <span class="GJ-sort-s1"  @click="GJSortSelect=!GJSortSelect">∨</span>
                <span>{{GJInfo.GJSorted}}</span>
                <div class="GJ-sort-select" v-show="!GJSortSelect">
                    <div @click="sortGJ(按创建时间排序)">按创建时间排序</div>
                </div> -->
            </div>
            <div class="GJ-show">
                <!-- <div class="clearfix sucai-ul" :style="{'width':width}"> -->
                <div class="clearfix sucai-ul" style="width:810px;" v-show="suCaiType!=4">
                    <div class="sucai-show" v-for="item in suCaiList" :key="item.mid" @mouseover="changeActive($event)" @mouseleave="removeActive($event)">
                        <img :src="item.imgurl" alt="" @click="showMore(item.m_type,item.content,item.m_name)">
                        <span class="sucai-show-title" :title="item.m_name">{{item.m_name.length>10?item.m_name.substring(0,11):item.m_name}}</span>
                        <div class="el-icon-picture sucai-show-time" style="margin:0px 15px;" v-if="item.m_type==1">&nbsp;{{item.update_time}}</div>
                        <div class="el-icon-video-camera-solid sucai-show-time" style="margin:0px 15px;" v-if="item.m_type==2">&nbsp;{{item.update_time}}</div>
                        <div class="el-icon-mic sucai-show-time" style="margin:0px 15px;" v-if="item.m_type==3">&nbsp;{{item.update_time}}</div>
                        <div class="sucai-show-more" @click="item.hide=!item.hide">...</div>
                        <div class="GJ-sample-select" v-show="!item.hide && GJInfo.GJTag=='本地素材'">
                            <!-- <div>下载</div>
                            <div>重命名</div> -->
                            <div @click="deleteById(item.mid)">删除</div>
                        </div>
                    </div>
                </div>
                <!-- 知识图谱 -->
                <div id="container" style="height: 800px" v-if="suCaiType==4"></div>
                <el-pagination
                    background
                    :page-size="12"
                    layout="prev, pager, next"
                    @current-change="currentPage"
                    @prev-click="prevPage"
                    @next-click="nextPage"
                    :total="totalPage" v-show="GJInfo.GJTag=='本地素材'">
                </el-pagination>
            </div>
            <div>
            </div>
        </div>
    </div>

    <!-- 通用遮罩 -->
      <div class="Cover" v-cloak v-show="ShowBtn"></div>
      <!-- 视频展示 -->
      <div class="vide-show" v-if="ShowBtn">
        <div class="videoTitle">{{currentTitle}}</div>
        <span class="videoQuit" @click="ShowBtn = false;showT='1'">关闭</span>
        <div class="video-inner">
          <video :src="currentUrl" controls v-if="showT==2"></video>
          <audio :src="currentUrl" controls v-if="showT==3"></audio>
        </div>
      </div>
</div>   
  
</template>

<script>
import axios from "axios"
export default {
    name: 'SuCaiKu',
    data() {
        return {
            GJFont:[true,false],
            GJImg:[require("../assets/icon/本地素材.svg"),require("../assets/icon/素材1.svg")],
            suCaiType:0,
            suCaiList:[],
            GJInfo:{
                GJTag:"本地素材",
                GJSorted:"更多操作"
            },
            totalSize:0,
            GJList:[],
            // 稿件选择排序列表隐藏与显示
            GJSortSelect:true,
            totalPage:0,
            sortByTime:false,
            showT:"1",
            currentTitle:"",
            currentUrl:"",
            ShowBtn:false,
            searchname:"",

        }
    },
    methods:{
        //云端素材
        querySuCai(){
            if(this.searchname.trim()==""){
                    return
            }
            if(this.suCaiType==2){
                var formFile = new window.FormData();
                formFile.append("num",12)
                formFile.append("query",this.searchname)
                formFile.append("end_date","20220520")
                axios.post("/baidu/search/video_search",formFile).then(res=>{
                    this.suCaiList = []
                    var data = JSON.parse(res.data.t)
                    console.log(data)
                    if(data.content.length==0||data.error_message!="success"){
                        this.$message.info("未检索到相关视频");
                        return
                    }
                    for(let info of data.content){
                        info["hide"]=true
                        info["imgurl"] = info.poster
                        info["m_type"] = 2
                        info["m_name"] = info.title
                        info["content"] = info.play_url
                        info["update_time"] = info.pdate
                        this.suCaiList.push(info)
                    }
                }).catch(e=>{
                })
            }else{
                var formFile = new window.FormData();
                formFile.append("keyWord", this.searchname);
                if(this.searchname.trim()==""){
                    return
                }
                formFile.append("num", 12); //加入文件对象
                this.suCaiList = []
                axios
                    .post("/reptile/baidu_image", formFile)
                    .then((res) => {
                    var data = res.data;
                    if(data.ok){
                        for(let info of data.t){
                            info["hide"]=true
                            info["imgurl"] = info.url
                            info["m_type"] = 1
                            info["m_name"] = this.searchname
                            info["content"] = ""
                            info["update_time"] = ""
                            this.suCaiList.push(info)
                        }
                    }else{
                        this.$message.warning("未检索到相关图片");
                    }
                })
                    .catch((e) => {
                    this.$message.warning("未检索到相关图片");
                    console.log(e);
                });
            }
        },
        // 搜索素材
        searchByname(){
            if(this.searchname==""){
                return
            }
            if(this.GJInfo.GJTag=="本地素材"){
                this.selectAll(1)
            }else if(this.suCaiType==4){
                this.getTuPu()
            }else{
                this.querySuCai()
            }
        },
        //获取图谱
        getTuPu(){
            let dom = document.getElementById('container');
            // dom.innerHTML=""       
            let myChart = echarts.init(dom, null, {
                renderer: 'canvas',
                useDirtyRect: false
            });
            let app = {};
            let option;

            let height = dom.clientHeight
            let width = dom.clientWidth
            let point_x = width / 2
            let point_y = height / 2
            let bound = Math.min(height, width)

            myChart.showLoading();
            $.get("http://127.0.0.1:8080/baidu/search/correlation_list",{query:this.searchname}, (data)=>{
                var data = JSON.parse(data.t)
                data.data = {}
                data.data.content = data.content
                delete data.content
                data.data.query = this.searchname
                console.log(data)
                if(!data.data.content||data.data.content.length<=0){
                    this.$message.warning("未检索到相关信息");
                    return
                }
                myChart.hideLoading();
                data = data['data']
                let graph = {
                    'categories': [{'name': 'query', 'color': 'red'}, {'name': 'up'}, {'name': 'down'}],
                    'nodes': [],
                    'links': []
                }
                graph.nodes.push({
                    'id': 0,
                    'name': data['query'],
                    'category': 0,
                    'symbolSize': 30,
                    'label': {'show': true},
                    'x': point_x,
                    'y': point_y
                })
                let m = -1
                for (let i = 1; i < data['content'].length; i++) {
                    if (data['content'][i]['pv'] > m) {
                        m = data['content'][i]['pv']
                    }
                }
                for (let i = 1; i < data['content'].length; i++) {
                    let cate = data['content'][i]['is_pv_rising'] ? 1 : 2;
                    let pv = data['content'][i]['pv']
                    let angle = Math.random() * 360 * 0.017453293
                    let dx = Math.cos(angle)
                    let dy = Math.sin(angle)
                    let hot_rate = pv / m
                    graph.nodes.push({
                        'id': i,
                        'name': data['content'][i]['name'],
                        'category': cate,
                        'related_url': data['content'][i]['related_url'],
                        'pv': pv,
                        'symbolSize': 40 * hot_rate + 5,
                        'label': {'show': true},
                        'x': point_x + dx * (bound * 0.5 * (1 - hot_rate) + bound * 0.2),
                        'y': point_y + dy * (bound * 0.5 * (1 - hot_rate) + bound * 0.2)
                    })
                    graph.links.push({'source': 0, 'target': i})
                }
                option = {
                    tooltip: {
                        formatter: (param) => {
                            if (param['dataType'] !== 'node') return;
                            if (param.data.id === 0) return;

                            let res = `<div> <span> <strong> ${param.name} </strong> 热度 : ${param.data.pv}</span> `
                            for (let i = 0; i < param.data.related_url.length; i++) {
                                let t = param.data.related_url[i].title
                                res += `</br></br> <span> ${t}  </span> `
                            }
                            res += '</div>'
                            return res;
                        }

                    },
                    animationDuration: 1500,
                    animationEasingUpdate: 'quinticInOut',
                    series: [
                        {
                            name: '主题关联',
                            type: 'graph',
                            layout: 'none',
                            data: graph.nodes,
                            links: graph.links,
                            categories: graph.categories,
                            roam: true,
                            label: {
                                position: 'right',
                                formatter: '{b}'
                            },
                            lineStyle: {
                                color: 'source',
                                curveness: 0.3
                            },
                            emphasis: {
                                focus: 'adjacency',
                                lineStyle: {
                                    width: 10
                                }
                            }
                        }
                    ]
                };
                myChart.setOption(option);          
            });
            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }

            window.addEventListener('resize', myChart.resize);
        },
        // 文本和视频稿件切换
        togGJ(name){
            this.GJInfo.GJTag = name
            this.suCaiType = 1
            this.suCaiList = []
            this.searchname=""
            if(name=='本地素材'){
                this.GJFont = [true,false]
                this.GJImg=[require("../assets/icon/本地素材.svg"),require("../assets/icon/素材1.svg")]
                this.selectAll(1)
            }else{
                this.GJFont = [false,true]
                this.GJImg=[require("../assets/icon/本地素材1.svg"),require("../assets/icon/素材.svg")]
            }
        },
        //显示视频，音频详情
        showMore(type,url,title){
            this.showT = type
            if(this.showT==2 || this.showT==3){
                this.ShowBtn = true
                this.currentUrl = url
                this.currentTitle = title
            }
        },
        // 响应式布局
        // onw(){
        //     var w = window.outerWidth
        //     console.log(window.outerWidth)
        //     if(w>2330){
        //         this.width = "1350px"
        //     }else if(w>1933){
        //         this.width = "1080px"
        //     }else{
        //         this.width = "810px"
        //     }
        // },
        // 
        searchInfo(id){
            this.suCaiType = id
            this.$nextTick(()=>{
                if(this.GJInfo.GJTag=="本地素材"){
                    this.selectAll(1)
                }else{
                    if(this.searchname==""){
                        return
                    }
                    this.suCaiList=[]
                    if(this.suCaiType!=4){
                        this.querySuCai()
                    }else{
                        this.getTuPu()
                    }
                }
            });
        },
        changeActive(event){
            event.currentTarget.className = 'sucai-show boxShadow'
        },
        removeActive(event){
            event.currentTarget.className = 'sucai-show'
        },
        selectAll(page){
            var formFile = new window.FormData();
            formFile.append("page",page+"")
            formFile.append("pageSize","12")
            if(this.suCaiType!=0){
                formFile.append("m_type",this.suCaiType+"")
            }
            if(this.searchname!=""){
                formFile.append("m_name",this.searchname)
            }
            axios.post("/material/list",formFile).then(res=>{
                this.suCaiList = []
                for(let info of res.data.list){
                    info["hide"]=true
                    if(info.m_type==1){
                        info["imgurl"] = info.content
                    }else if(info.m_type==2){
                        info["imgurl"] = require("../assets/video.jpg")
                    }else{
                        info["imgurl"] = require("../assets/audio.jpg")
                    }
                    this.suCaiList.push(info)
                }
                this.totalPage = res.data.total
            }).catch(e=>{
            })
        },
        deleteById(id){
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {
                var formFile = new window.FormData();
                formFile.append("mid",id)
                axios.post("/material/deleteById",formFile).then(res=>{
                    if(res.data.ok){
                        this.selectAll("1")
                        this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    }
                }).catch(e=>{
                    console.log(e)
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
            });
        },
        currentPage(index){
            this.selectAll(index)
        },
        prevPage(index){
            this.selectAll(index)
        },
        nextPage(index){
            this.selectAll(index)
        },
        sortGJ(){
            this.sortByTime = true
            this.selectAll(1)
            this.GJSortSelect=true
        },
    },
    mounted() {
        // this.onw()
        this.selectAll("1")
        // window.onresize = this.onw
    }
}
</script>

<style scoped>
.context-left{
    padding-right: 0px;
    position: relative;
}
.context-left-nav{
    border-right: 2px solid rgb(236, 236, 236);
    position: absolute;
    height: 110px;
    right: 0px;
    top: 50px;
}
.context-left-ul li{
    margin-bottom: 15px;
    padding: 8px 20px;
    font-size: 20px;
    color: #dbdbdb;
}
.context-left-li{
    color: #2c2c2c !important;
}
.left-li1 img{
    width: 20px;
    height: 20px;
    margin-right: 8px;
    margin-left: 2px;
}
.left-li2 img,.left-li3 img{
    width: 25px;
    height: 25px;
    margin-right: 8px;
}
.context-GJ{
    padding-left: 100px;
}
.GJ-top span{
    float: left;
}
.GJ-top-s1{
    margin-right: 30px;
}
.GJ-top-s2{
    font-size: 16px;
    height: 26px;
    line-height: 26px;
    border-radius: 13px;
    margin-top: 10px;
    display: inline-block;
    width: 60px;
    margin-right: 5px;
    text-align: center;
    color: rgb(115,115,115);
}
.GJ-top-s2:hover{
    cursor: pointer;
}
.GJ-top-s2-a{
    color: white;
    background-color: rgb(2,129,184);
}
.GJ-top-bt{
    float: right;
    height: 40px;
    width: 100px;
    outline:none;
    color:white;
    background-color:#015478;
    border: none;
}
.GJ-top-in{
    float: right;
    height: 40px;
    width: 400px;
    outline:none;
    text-indent: 5px;
}
.GJ-sort{
    margin-top: 30px;
    position: relative;
    height: 30px;
}
.GJ-sort span{
    float: right;
    padding: 0px 5px;
}
.GJ-sort-s1:hover{
    cursor: pointer;
}

.GJ-sort-select{
    background-color: white;
    position:absolute;
    right: 0px;
    top: 20px;
    z-index: 300;
    border-radius: 5px;
}
.GJ-sort-select div{
    width: 100px;
    height: 30px;
    line-height: 30px;
    font-size: 12px;
    margin: 5px 5px;
}
.GJ-sort-select div:hover{
    background-color:#dbdbdb;
    cursor:pointer;
}

.GJ-show{
    /* height: 1000px; */
    margin-top: 10px;
    border-radius: 10px;
    background-color:white;
    overflow-y: auto;
    padding:30px 30px 0px;
    padding-bottom: 80px;
    min-height: 600px;
}
.el-pagination{
        text-align: center;
        margin-top: 30px;
}
.sucai-show{
    float: left;
    width: 250px;
    height: 200px;
    background-color: rgb(240,240,240);
    position: relative;
    margin: 10px 10px;
}
.sucai-show img{
    width: 100%;
    height: 140px;

}
.sucai-show-title{
    position:absolute;
    left: 15px;
    top: 145px;
    font-size: 16px;
}
.sucai-show-time{
    position:absolute;
    left: 0px;
    bottom: 10px;
    height: 12px;
    font-size: 10px;
}
.sucai-show-more{
    position:absolute;
    right: 10px;
    bottom: 10px;
    width: 30px;
    height: 20px;
    color:rgb(191,191,191);
    font-size:20px;
    line-height: 10px;
    text-align: center;
}
.sucai-ul{
    margin: 0 auto;
}
.sucai-show-more:hover{
    background-color:rgb(207,207,207);
    cursor: pointer;
}
.GJ-sample-select{
    background-color: white;
    position:absolute;
    right: 0px;
    top: 190px;
    z-index: 300;
    border-radius: 5px;
}
.GJ-sample-select div{
    width: 50px;
    height: 20px;
    line-height: 20px;
    font-size: 12px;
    margin: 5px 5px;
    text-align: center;
}
.GJ-sample-select div:hover{
    background-color:#dbdbdb;
    cursor:pointer;
}
.boxShadow{
    box-shadow:#999 0px 5px 8px;
    cursor: pointer;
}

/* 视频展示 */
.Cover {
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    background-color: darkgrey;
    z-index: 500;
    opacity: 0.5;
}
.vide-show{
  width: 1100px;
  height: 600px;
  z-index: 1000;
  background-color: white;
  position: fixed;
  left: 50%;
  top: 50%;
  margin-left: -550px;
  margin-top: -300px;
  border-radius: 10px;
  padding: 30px;
}
.videoTitle{
    font-size:30px;
    font-weight: bolder;
    padding-bottom: 20px;
    border-bottom: 1px solid rgb(235,235,235);
}
.videoQuit{
    display: inline-block;
    width: 60px;
    height: 30px;
    font-size: 12px;
    text-align: center;
    line-height: 30px;
    position:absolute;
    right:30px;
    top: 30px;
    background-color: #015478;
    color:white;
    border-radius: 3px;
}
.videoQuit:hover{
  cursor: pointer;
}
.video-inner{
  padding-top: 20px;
  width: 100%;
  height: 460px;
  text-align: center;
}
.video-inner video{
  height: 460px;
  display: inline-block;
}
.video-select-tw{
  padding-top: 80px;
  padding-left: 50px;
}
</style>