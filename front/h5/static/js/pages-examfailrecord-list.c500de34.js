(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-examfailrecord-list"],{"508e":function(t,e,s){"use strict";var n=s("4ea4");Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0,s("96cf");var i=n(s("3b8d")),a={data:function(){return{list:[],mescroll:null,downOption:{auto:!1},upOption:{noMoreSize:5,textNoMore:"~ 没有更多了 ~"},hasNext:!0,searchForm:{},CustomBar:"0"}},onShow:function(){this.hasNext=!0,this.mescroll&&this.mescroll.resetUpScroll()},onLoad:function(t){this.hasNext=!0,this.userid=t.userid,this.mescroll&&this.mescroll.resetUpScroll()},methods:{mescrollInit:function(t){this.mescroll=t},downCallback:function(t){this.hasNext=!0,t.resetUpScroll()},upCallback:function(){var t=(0,i.default)(regeneratorRuntime.mark((function t(e){var s;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,this.$api.list("examrecord",{page:e.num,limit:e.size,userid:this.userid,sort:"id",myscore:0});case 2:s=t.sent,1==e.num&&(this.list=[]),this.list=this.list.concat(s.data.list),0==s.data.list.length&&(this.hasNext=!1),e.endSuccess(e.size,this.hasNext);case 7:case"end":return t.stop()}}),t,this)})));function e(e){return t.apply(this,arguments)}return e}()}};e.default=a},8111:function(t,e,s){"use strict";var n={"mescroll-uni":s("f05e").default},i=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("mescroll-uni",{attrs:{up:t.upOption,down:t.downOption},on:{init:function(e){arguments[0]=e=t.$handleEvent(e),t.mescrollInit.apply(void 0,arguments)},down:function(e){arguments[0]=e=t.$handleEvent(e),t.downCallback.apply(void 0,arguments)},up:function(e){arguments[0]=e=t.$handleEvent(e),t.upCallback.apply(void 0,arguments)}}},t._l(t.list,(function(e,n){return s("v-uni-view",{key:n,staticClass:"cu-list menu-avatar"},[s("v-uni-view",{staticClass:"cu-item",staticStyle:{height:"180rpx"}},[s("v-uni-view",{staticClass:"content",staticStyle:{left:"20rpx"}},[s("v-uni-view",{staticClass:"text-grey"},[t._v("在线考试："+t._s(e.papername))]),s("v-uni-view",{staticClass:"text-gray text-sm flex"},[t._v("试题内容："+t._s(e.questionname))]),s("v-uni-view",{staticClass:"text-gray text-sm flex"},[t._v("答案："+t._s(e.answer))]),s("v-uni-view",{staticClass:"text-gray text-sm flex"},[t._v("我的答案："+t._s(e.myanswer))])],1),s("v-uni-view",{staticClass:"action"},[s("v-uni-view",{staticClass:"text-grey text-xs"},[t._v("分数："+t._s(e.score))]),0==e.myscore?s("v-uni-view",{staticClass:"cu-tag round bg-grey sm"},[t._v("得分"+t._s(e.myscore))]):t._e(),e.myscore>0?s("v-uni-view",{staticClass:"cu-tag round bg-red sm"},[t._v("得分"+t._s(e.myscore))]):t._e()],1)],1)],1)})),1)},a=[];s.d(e,"b",(function(){return i})),s.d(e,"c",(function(){return a})),s.d(e,"a",(function(){return n}))},"8f9b":function(t,e,s){"use strict";s.r(e);var n=s("508e"),i=s.n(n);for(var a in n)"default"!==a&&function(t){s.d(e,t,(function(){return n[t]}))}(a);e["default"]=i.a},"9de0":function(t,e,s){"use strict";s.r(e);var n=s("8111"),i=s("8f9b");for(var a in i)"default"!==a&&function(t){s.d(e,t,(function(){return i[t]}))}(a);var r,u=s("f0c5"),c=Object(u["a"])(i["default"],n["b"],n["c"],!1,null,"1fb00bd5",null,!1,n["a"],r);e["default"]=c.exports}}]);