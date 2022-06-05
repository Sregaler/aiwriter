package com.hdumil.aiwriter.test;

import org.assertj.core.internal.cglib.asm.$Label;

import java.util.*;

public class ACMtool {
    /**
     * 快速排序
     */
    public int[] sortArrayK(int[] nums) {
        randomizedQuicksort(nums, 0, nums.length - 1);
        return nums;
    }

    public void randomizedQuicksort(int[] nums, int low, int high) {
        if (low < high) {
            int left = low;
            int right = high;
            //随机选取基准值，这样能避免最坏情况
            int rnd = new Random().nextInt(high - low + 1) + low;
            int temp = nums[left];
            nums[left] = nums[rnd];
            nums[rnd] = temp;

            int pivot = nums[left];
            while (left < right) {
                //从后向前找到比基准小的元素
                while (left < right && nums[right] >= pivot)
                    right--;
                nums[left] = nums[right];
                //从前往后找到比基准大的元素
                while (left < right && nums[left] <= pivot)
                    left++;
                nums[right] = nums[left];
            }
            // 放置基准值，准备分治递归快排
            nums[left] = pivot;
            randomizedQuicksort(nums, low, left - 1);
            randomizedQuicksort(nums, left + 1, high);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 堆排序
     */
    public int[] sortArrayD(int[] nums) {
        heapSort(nums);
        return nums;
    }

    public void heapSort(int[] nums) { //创建堆
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            maxHeap(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i > 0; i--) {
            // 将最大元素放到堆底，后续不再进入堆排序
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            maxHeap(nums, 0, i);
        }
    }

    public void maxHeap(int[] nums, int parent, int length) {
        int temp = nums[parent];
        int lchild = 2 * parent + 1;
        while (lchild < length) {
            int rchild = lchild + 1;
            if (rchild < length && nums[lchild] < nums[rchild]) lchild++;
            if (temp > nums[lchild]) break;
            nums[parent] = nums[lchild];
            parent = lchild;
            lchild = 2 * lchild + 1;
        }
        nums[parent] = temp;
    }

    /**
     * 归并排序
     */
    public int[] sortArrayG(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void mergeSort(int[] nums, int l, int r) {
        int[] tmp = new int[r - l + 1];
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        int i = l, j = mid + 1;
        int cnt = 0;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                tmp[cnt++] = nums[i++];
            } else {
                tmp[cnt++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[cnt++] = nums[i++];
        }
        while (j <= r) {
            tmp[cnt++] = nums[j++];
        }
        for (int k = 0; k < r - l + 1; ++k) {
            nums[k + l] = tmp[k];
        }
    }

    /**
     * 有向图的深度优先搜索，拓扑排序，用递归dfs
     */
    List<List<Integer>> edgesDFS;// 存储有向图
    int[] visited;// 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成 
    int[] resultDFS;// 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    boolean valid = true; // 判断有向图中是否有环
    int indexDFS;// 栈下标

    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        edgesDFS = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edgesDFS.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        resultDFS = new int[numCourses];
        indexDFS = numCourses - 1;
        for (int[] info : prerequisites) {
            edgesDFS.get(info[1]).add(info[0]);  // 后面放高级的课，图→就是顺序
        }
        // 每次挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
            if (!valid) { // 如果有环就不是拓扑排序
                return new int[0];
            }
        }
        return resultDFS;
    }

    public void dfs(int u) {
        visited[u] = 1;// 将节点标记为「搜索中」
        // 搜索其相邻节点
        // 只要发现有环，立刻停止搜索
        for (int v : edgesDFS.get(u)) {
            if (visited[v] == 0) {// 如果「未搜索」那么搜索相邻节点
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {  // 如果「搜索中」说明找到了环
                valid = false;
                return;
            }
        }
        visited[u] = 2;        // 将节点标记为「已完成」
        resultDFS[indexDFS--] = u;        // 将节点入栈
    }

    /**
     * 有向图的广度优先搜索，拓扑排序，用队列
     */
    List<List<Integer>> edgesWFS;    // 存储有向图
    int[] indeg;    // 存储每个节点的入度
    int[] resultWFS;    // 存储答案
    int indexWFS;    // 答案下标

    public int[] findOrderWFS(int numCourses, int[][] prerequisites) {
        edgesWFS = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edgesWFS.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        resultWFS = new int[numCourses];
        indexWFS = 0;
        for (int[] info : prerequisites) {
            edgesWFS.get(info[1]).add(info[0]);
            ++indeg[info[0]];  //高级课的入度
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        // 将所有入度为 0 的节点放入队列中
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();// 从队首取出一个节点
            resultWFS[indexWFS++] = u;// 放入答案中
            for (int v : edgesWFS.get(u)) {
                --indeg[v];
                // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        if (indexWFS != numCourses) {
            return new int[0];
        }
        return resultWFS;
    }

    /**
     * 最长递增子序列
     */
    public static int longestSubArray(int[] nums) {
        //tails[k] 的值代表长度为k+1子序列 的尾部元素值，tails[k]是单调递增的
        int[] tails = new int[nums.length];
        // res 为 tails当前长度
        int res = 0;
        for (int num : nums) {
            int l = 0;
            //r为当前最长的长度，而不是length-1，这点要注意，二分查找到可以插入的位置
            int r = res;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (tails[m] < num)
                    l = m + 1;
                else
                    r = m;
            }
            tails[l] = num;
            if (r == res)
                res++;
        }
        return res;
    }

    /**
     * 素数筛
     */
    public static List<Integer> sushu(int n) {  //返回n以内的所有素数
        List<Integer> prime = new ArrayList<>();
        boolean[] isVisit = new boolean[n+1];
        for(int i = 2;i <= n; ++i){
            if( ! isVisit[i]) //如果这个数未被访问，则是素数
                prime.add(i);
            for(int j = 0;j < prime.size() && i * prime.get(j) <= n; ++j){
                isVisit[i * prime.get(j)] = true;  //当前数乘其他素数
                if (i % prime.get(j) == 0) break;  //减少循环
            }
        }
        return prime;
    }

    /**
     * 最长回文子串  Manacher 算法
     */
    public String longestPalindrome(String s) {
        if(s.length()==0) return s;
        int n = s.length();
        StringBuffer sff = new StringBuffer("$#");
        for(int i = 0;i<n;i++){
            sff.append(s.charAt(i));
            sff.append('#');
        }
        n = sff.length();
        sff.append('!');
        int[] fp = new int[n];
        int center =0, i_R = 0, res =0;
        for(int i=1;i<n;i++){  // # 位置的f[i]=1  f[i]是该位置回文串真实长度+1
            // 初始化 f[i]  //  2 * center - i  是i_mirror
            fp[i] = i < i_R?Math.min(i_R-i+1,fp[2*center - i]) : 1;
            while(sff.charAt(i+fp[i]) == sff.charAt(i-fp[i])){  // 中心拓展
                fp[i]++;
            }

            if(i+fp[i]-1 > i_R){  // 动态维护 center 和 i_R  ,拉车Center
                center =  i;
                i_R = i + fp[i]-1;
            }
            // 统计答案, 子串数量：ans += f[i] / 2; 当前贡献为 (f[i] - 1) / 2 上取整
            if(fp[i]>fp[res])
                res = i;
        }
        StringBuffer new_sff = new StringBuffer();
        int i;
        if(sff.charAt(res)!='#'){
            new_sff.append(sff.charAt(res));
            i = 2;
        }
        else{
            i = 1;
        }
        for(;i<fp[res];){
            new_sff.insert(0,sff.charAt(res-i));
            new_sff.append(sff.charAt(res+i));
            i = i + 2;
        }
        return new_sff.toString();
    }

    /**
     * 用 Rand7() 实现 Rand10()
     */
    public int rand10() {
        int row, col, idx;
        do {
            row = new Random().nextInt(8);  // rand7
            col = new Random().nextInt(8);
            idx = col + (row - 1) * 7;
        } while (idx > 40);  // 拒绝域
        return 1 + (idx - 1) % 10;
        //古典概型
//        int first, second;
//        while ((first = Random().nextInt(8)) > 6);
//        while ((second = Random().nextInt(8)) > 5);
//        return (first&1) == 1 ? second : 5+second;
    }

    /**
     * 零钱兑换
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {  // 面额后遍历，防止重复。求最小硬币数量时，面额先遍历
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 单调栈，接雨水
     */
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 树状数组，计算右侧小于当前元素的个数
     */
    private int[] table_tree;  // table_tree[0]是不存东西的
    private int[] idTable;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        discretization(nums);
        table_tree = new int[nums.length + 1];
        for (int i = nums.length - 1; i >= 0; --i) {
            int id = getId(nums[i]);
            resultList.add(query(id - 1));
            update(id, 1);
        }
        Collections.reverse(resultList);
        return resultList;
    }

    private int lowBit(int x) {  // 树状数组坐标转换
        return x & (-x);
    }

    private void update(int pos, int x) {   // 单点更新
        while (pos < table_tree.length) {
            table_tree[pos] += x;
            pos += lowBit(pos);
        }
    }

    private int query(int pos) {  // 查询[0,pos]的前缀和
        int ret = 0;
        while (pos > 0) {
            ret += table_tree[pos];
            pos -= lowBit(pos);
        }
        return ret;
    }

    private void discretization(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        idTable = new int[size];
        int index = 0;
        for (int num : set) {
            idTable[index++] = num;
        }
        Arrays.sort(idTable);
    }

    private int getId(int x) { // 元素对应桶的序号
        return Arrays.binarySearch(idTable, x) + 1;
    }


    /**
     * dijkstra算法，单点最短距离
     */
    public int[] dijkstra(int[][] edges,int start,int end){
        int[] dist = new int[edges.length]; //定义从start开始到其他各个点的距离
        System.arraycopy(edges[start], 0, dist, 0, edges.length); //初始化
        boolean[] flag = new boolean[edges.length]; //记录该点是否被访问过
        flag[start] = true;
        dist[start] = 0;
        int cur = start; //设置当前最优点
        while(true){
            int min = Integer.MAX_VALUE;
            int mininex = -1;
            for (int j= 0; j< edges.length; j++) { //依次遍历所有点
                if(!flag[j]){  //访问为找到最优路径的点，取当前较优路径与cur连成的路径相比(防止MAX_VAULE再加的话溢出)
                    int temp = dist[cur]+edges[cur][j];
                    dist[j] = Math.min(dist[j],(temp<0?Integer.MAX_VALUE:temp));
                    if(min > dist[j]){  //未访问的点中距离最小的那个
                        min = dist[j];
                        mininex = j;
                    }
                }
            }
            if(mininex!=-1){  //设置为当前最优点，设置该点已经访问过
                cur = mininex;
                flag[cur] = true;
                if(cur==end) return dist;  // 提前退出搜索
            }
            else break;
        }
        return dist;
    }

    /**
     * floyd算法，所有点之间的最短距离
     */
    public void floyd(int[][] edges){
        int n = edges.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int temp = edges[i][k]+edges[k][j];
                    if(edges[i][j]>temp) edges[i][j] = temp;
                }
            }
        }
    }

    /**
     * 链表存储图
     */
    public static class Node {
        //        Map<Integer,Integer> nextNode = new HashMap();
        Map<Integer,Integer> minPath = new HashMap();  //定义从该店开始到其他各个点的dijkstra距离
        Set<Integer> flag = new HashSet<>(); //记录该点是否已经是最短距离
//        boolean[] flag; //记录该点是否已经是最短距离
        int next_indx;  // 保存dijkstra的跳出点
        Node(int indx) {
            next_indx = indx;
            flag.add(indx);
//            flag = new boolean[n];  // n是节点数量
//            flag[indx] = true;
            minPath.put(indx,0);
        }
        public void putNext(Integer next,Integer val){
//            nextNode.put(next,val);
            minPath.put(next,val);
        }
    }

    /**
     * dijkstra算法，单点最短距离，Map实现
     */
    public static void dijkstraList(Node[] nodes, int start, int end){
        Node start_node = nodes[start];
        Set<Integer> flag = start_node.flag; //记录该点是否被访问过
//        boolean[] flag = start_node.flag; //记录该点是否被访问过
        Map<Integer,Integer> minPath = start_node.minPath;
        flag.add(start);
        minPath.put(start,0);
        int cur = start_node.next_indx; //前面中断点
        while(true) {
            Node now_node = nodes[cur]; //以当前节点为跳板，依次遍历所有点
            int min = Integer.MAX_VALUE;
            int mininex = cur;
            for (int key= 0; key < nodes.length; key++) {
                if(!flag.contains(key)){  //访问为找到最优路径的点
//                    if(now_node.minPath.containsKey(key)){
//                        int temp = minPath.get(cur) + now_node.minPath.get(key);
//                        if (!minPath.containsKey(key)||minPath.get(key)>temp){  //不存在或者更优
//                            minPath.put(key,temp);
//                        }
//                    }
//                    if(minPath.containsKey(key)&&min > minPath.get(key)){  //未访问的点中距离最小的那个
//                        min = minPath.get(key);
//                        mininex = key;
//                    }
                    try {
                        int temp = minPath.get(cur) + now_node.minPath.get(key);
                        try {
                            if (minPath.get(key)>temp)  //更优
                                minPath.put(key,temp);
                        }catch (NullPointerException ex) {
                            minPath.put(key,temp);  //不存在
                        }
                    } catch (NullPointerException ignored) {}
                    try {
                        if(min > minPath.get(key)){  //未访问的点中距离最小的那个
                            min = minPath.get(key);
                            mininex = key;
                        }
                    } catch (NullPointerException ignored) {}
                }
            }
            flag.add(mininex);
//            flag[mininex] = true;
            if (mininex == cur) break; // 已经没有可以优化的点了
            start_node.next_indx = mininex;
            cur = mininex;
            if(cur==end) return;  // 提前退出
        }
    }

    /**
     * 边节点
     */
    public static class Edge implements Comparable{
        public int start;
        public int end;
        public int weight;

        public Edge(int edgeNode1, int edgeNode2, int weight) {
            this.start = edgeNode1;
            this.end = edgeNode2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            return this.weight - ((Edge)o).weight;
        }
        //该方法用于判断两个结点连接后是否会形成环
        public static Integer find(Map<Integer ,Integer> map,int nodeName){
            while (map.get(nodeName)!=null){
                nodeName=map.get(nodeName);
            }
            return nodeName;
        }
    }

    /**
     * 最小生成树，Kruskal算法,Prim算法是对已有的点去找最小的扩散边
     */
    public static List<Edge> miniTree(List<Edge> list,int node_num){
        Integer node1, node2;
        Map<Integer,Integer> map =new HashMap<>();  //用于判断是否产生环
        List<Edge> res = new ArrayList<>();  //用于存放最小生成树的边
        Collections.sort(list);
        int edge_num = 0;
        for (int i = 0; i <list.size() ; i++) {
            node1=Edge.find(map,list.get(i).start);
            node2=Edge.find(map,list.get(i).end);
            if (!node1.equals(node2)){
                map.put(node1,node2);
//                System.out.println("开始结点："+list.get(i).start +";结束结点："+list.get(i).end+";权值："+list.get(i).weight);
                res.add(list.get(i));
                edge_num++;
                if(edge_num==node_num-1) break;
            }
        }
        return res;
    }

    /**
     * KMP算法，字符串匹配，复杂度的高低取决于pattern串
     * 在query查询串(长)中找到pattern模式串(短)
     */
    public static int KMP(String query, String pattern) {
        int n = query.length();
        int m = pattern.length();
        int[] fail = new int[m];
        Arrays.fill(fail, -1);
        for (int i = 1; i < m; ++i) {  // 对pattern进行操作，pattern无规律时，复杂度越高
            int j = fail[i - 1];
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int match = -1;
        for (int i = 0; i < n; ++i) {
            while (match != -1 && pattern.charAt(match + 1) != query.charAt(i)) {
                match = fail[match];
            }
            if (pattern.charAt(match + 1) == query.charAt(i)) {
                ++match;
                if (match == m - 1) {
                    return i-m+1;
                }
            }
        }
        return -1;
    }

    /**
     * 快速幂,大数取模
     */
    public static int qpow(int a, int n){
        int MOD = 1000000007;
        int ans = 1;
        while(n>0){
            if((n&1)==1)        //如果n的当前末位为1
                ans = ans*a%MOD;  //ans乘上当前的a
            a = a*a%MOD;        //a自乘
            n >>= 1;       //n往右移一位
        }
        return ans % MOD;
    }

    /**
     * 组合Cn m的实现
     */
    public static int cel_C(int n,int m){
        if(n-m<0) return -1;
        if(m>n/2) m = n - m;
        int res = cel_A(n,m);
        for (int i = 2; i <= m; i++) {
            res = res / i;
        }
        return res;
    }

    /**
     * 排列An m的实现
     */
    public static int cel_A(int n,int m){
        if(n-m<0) return -1;
        int res = 1;
        for (int i = n-m+1; i <= n; i++) {
            res = res * i;
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
