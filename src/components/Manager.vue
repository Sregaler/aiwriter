<template>
<div class="row pt">
    <!-- 稿件管理左侧 -->
    <div class="context-left col-md-2  col-xs-2">
        <div class="context-left-nav">
            <ul class="context-left-ul">
                <li class="left-li2" @click="togGJ('图文稿件')" :class="{'context-left-li':GJFont[0]}"><img :src="GJImg[0]" alt="">图文稿件</li>
                <!-- <li class="left-li1" @click="togGJ('视频稿件')" :class="{'context-left-li':GJFont[1]}"><img :src="GJImg[1]" alt="">视频稿件</li> -->
            </ul>
        </div>
    </div>

    <div class="context-center col-md-8 col-xs-8">
        <div class="context-GJ">
            <div class="GJ-top clearfix">
                <span class="GJ-top-s1" style="font-size:30px"><b>{{GJInfo.GJTag}}</b></span>
                <span class="GJ-top-s2" style="font-size:18px" >{{totalSize}}篇稿件</span>
                <button class="GJ-top-bt"  @click="searchByname">搜索</button>
                <input class="GJ-top-in" type="text" v-model="searchname" placeholder="输入搜索稿件的名称">
            </div>
            <div class="GJ-sort clearfix">
                <span class="GJ-sort-s1"  @click="GJSortSelect=!GJSortSelect">∨</span>
                <span>{{GJInfo.GJSorted}}</span>
                <!-- <el-dropdown>
                    <span class="el-dropdown-link">
                        更多操作<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click="sortGJ">按创建时间排序</el-dropdown-item>
                        <el-dropdown-item>按评分排序</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown> -->
                <div class="GJ-sort-select" v-show="!GJSortSelect">
                    <div @click="sortGJ()">按创建时间排序</div>
                    <!-- <div>按评分排序</div> -->
                </div>
            </div>
            <div class="GJ-show">
                <div class="GJ-sample" v-for="(item) in GJList" :key="item.id">
                    <img :src="item.logo" alt="">
                    <!-- <img src="../assets/其他/GJimg.png" alt=""> -->
                    <span class="GJ-sample-s1" @click="watchGJ(item)">{{item.title}}</span>
                    <span class="GJ-sample-s2">{{item.update_time}}</span>
                    <!-- <span class="GJ-sample-s3">系统评分&nbsp;{{item.score}}</span> -->
                    <div class="GJ-sample-d1" @click="item.hide=!item.hide">...</div>
                    <div class="GJ-sample-select" v-show="!item.hide">
                        <div @click="deleteById(item.aid)">删除</div>
                        <!-- <div>导出</div> -->
                    </div>
                </div>
                <el-pagination
                    background
                    :page-size="5"
                    layout="prev, pager, next"
                    @current-change="currentPage"
                    @prev-click="prevPage"
                    @next-click="nextPage"
                    :total="totalPage">
                </el-pagination>
            </div>
            <div>
                
            </div>
        </div>
    </div>
</div>   
  
</template>

<script>
import axios from "axios"
export default {
    name: 'Manager',
    data() {
        return {
            GJFont:[true,false],
            GJImg:[require("../assets/其他/日志选中.svg"),require("../assets/其他/视频未选中.svg")],
            GJInfo:{
                GJTag:"图文稿件",
                GJSorted:"更多操作"
            },
            totalSize:0,
            // 稿件信息
            // GJList:[{
            //     logo:require("../assets/其他/GJimg.png"),
            //     title:"北京冬残奥会开幕式剧透 中国队旗手公布",
            //     create_time:"2021-12-24 11:07:49",
            //     score:83,
            //     hide:true
            // },{
            //     logo:require("../assets/其他/GJimg1.png"),
            //     title:"做咖啡、送外卖、防疫清洁，多种智能机器人服务北京冬残奥",
            //     create_time:"2021-12-13 12:27:19",
            //     score:88,
            //     hide:true
            // }],
            GJList:[],
            // 稿件选择排序列表隐藏与显示
            GJSortSelect:true,
            totalPage:0,
            sortByTime:false,
            searchname:""
        }
    },
    methods:{
        searchByname(){
            if(this.searchname==""){
                return
            }
            this.selectAll(1)
        },
        // 文本和视频稿件切换
        togGJ(name){
            this.GJInfo.GJTag = name
            if(name=='图文稿件'){
                this.GJFont = [true,false]
                this.GJImg=[require("../assets/其他/日志选中.svg"),require("../assets/其他/视频未选中.svg")]
            }else{
                this.GJFont = [false,true]
                this.GJImg=[require("../assets/其他/日志未选中.svg"),require("../assets/其他/视频选中.svg")]
            }
        },
        selectAll(page){
            if(this.sortByTime){
                var formFile = new window.FormData();
                formFile.append("page",page+"")
                formFile.append("pageSize","5")
                formFile.append("orderByColumn","create_time")
                if(this.searchname!=""){
                    formFile.append("title",this.searchname)
                }
                axios.post("/article/list",formFile).then(res=>{
                    this.GJList = []
                    this.totalSize = res.data.total
                    for(let info of res.data.list){
                        info["hide"]=true
                        this.GJList.push(info)
                    }
                    this.totalPage = res.data.total
                }).catch(e=>{
                    console.log(e)
                })
            }else{
                var formFile = new window.FormData();
                formFile.append("page",page+"")
                formFile.append("pageSize","5")
                if(this.searchname!=""){
                    formFile.append("title",this.searchname)
                }
                axios.post("/article/list",formFile).then(res=>{
                    this.GJList = []
                    this.totalSize = res.data.total
                    for(let info of res.data.list){
                        info["hide"]=true
                        this.GJList.push(info)
                    }
                    this.totalPage = res.data.total
                }).catch(e=>{
                    console.log(e)
                })
            }
        },
        deleteById(id){
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {
                var formFile = new window.FormData();
                formFile.append("aid",id)
                axios.post("/article/deleteById",formFile).then(res=>{
                    this.selectAll("1")
                    this.$message({
                    type: 'success',
                    message: '删除成功!'
                    });
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
        // 查看稿件
        watchGJ(item){
            console.log(item)
            this.$bus.$emit("navTag","智能图文成稿")
            this.$router.push({
                name:"aiwrite",
                params:{
                    textobj:item,
            }})
        }
    },
    mounted() {
        this.selectAll("1")
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
    width: 150px;
}
.GJ-top-s2{
    margin-top: 17px;
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
    height: 1000px;
    margin-top: 10px;
    border-radius: 10px;
    background-color:white;
    overflow-y: auto;
    padding-top:60px
}
.GJ-sample{
    width: 90%;
    height: 144px;
    margin: 10px auto;
    border: 2px solid #dbdbdb;
    position: relative;
}
.el-pagination{
        text-align: center;
        margin-top: 30px;
    }
.GJ-sample img{
    width: 220px;
    height: 120px;
    position: absolute;
    top: 10px;
    left: 10px;
}
.GJ-sample-s1{
    position: absolute;
    font-size: 20px;
    top:15px;
    left: 240px;
}
.GJ-sample-s1:hover{
    cursor: pointer;
}
.GJ-sample-s2{
    position: absolute;
    font-size: 13px;
    top:60px;
    left: 240px;
    color:rgb(138,138,138);
}
.GJ-sample-s3{
    position: absolute;
    font-size: 13px;
    top:110px;
    left: 240px;
    color:rgb(93,93,93);
}
.GJ-sample-d1{
    width: 30px;
    height: 30px;
    border-radius:50%;
    background-color: rgb(235,242,244);
    color:rgb(191,191,191);
    font-size:20px;
    line-height: 18px;
    text-align: center;
    position: absolute;
    right: 20px;
    top: 50%;
    margin-top: -15px;
}
.GJ-sample-d1:hover{
    cursor: pointer;
}

.GJ-sample-select{
    background-color: white;
    position:absolute;
    right: 5px;
    top: 85px;
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
.context-left-ul li:hover{
    cursor: pointer;
}
</style>