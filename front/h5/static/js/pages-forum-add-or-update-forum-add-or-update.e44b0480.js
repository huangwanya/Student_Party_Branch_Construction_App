(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-forum-add-or-update-forum-add-or-update"],{"073b":function(r,t,e){"use strict";e.r(t);var o=e("25a0"),a=e("96a8");for(var i in a)"default"!==i&&function(r){e.d(t,r,(function(){return a[r]}))}(i);e("e201");var n,d=e("f0c5"),u=Object(d["a"])(a["default"],o["b"],o["c"],!1,null,"4ad12420",null,!1,o["a"],n);t["default"]=u.exports},"25a0":function(r,t,e){"use strict";var o,a=function(){var r=this,t=r.$createElement,e=r._self._c||t;return e("v-uni-view",{staticClass:"container my-publish-pv",style:{padding:"0 24rpx",boxShadow:"0 0 0px rgba(0,0,0,0)",margin:"24rpx 0",borderColor:"rgba(0,0,0,0)",backgroundColor:"#fff",borderRadius:"0",borderWidth:"0",width:"100%",borderStyle:"solid",height:"auto"}},[e("v-uni-form",[e("v-uni-view",{staticClass:"cu-form-group margin-top",style:{padding:"0",boxShadow:"0 0 0px rgba(0,0,0,0)",margin:"0 0 20rpx 0",borderColor:"rgba(0,0,0,0)",backgroundColor:"#fff",borderRadius:"0",borderWidth:"0",width:"100%",borderStyle:"solid",height:"auto"}},[e("v-uni-input",{style:{padding:"0 20rpx",boxShadow:"0 0 0px rgba(0,0,0,.6) inset",margin:"0",borderColor:"rgba(0,0,0,.6)",backgroundColor:"rgba(255, 255, 255, 0)",color:"#333",textAlign:"left",borderRadius:"16rpx",borderWidth:"2rpx",width:"100%",fontSize:"28rpx",borderStyle:"solid",height:"88rpx"},attrs:{placeholder:"标题"},model:{value:r.forum.title,callback:function(t){r.$set(r.forum,"title",t)},expression:"forum.title"}})],1),e("v-uni-view",{staticClass:"cu-form-group margin-top",style:{padding:"0",boxShadow:"0 0 0px rgba(0,0,0,0)",margin:"0 0 20rpx 0",borderColor:"rgba(0,0,0,0)",backgroundColor:"#fff",borderRadius:"0",borderWidth:"0",width:"100%",borderStyle:"solid",height:"auto"}},[e("v-uni-picker",{attrs:{value:r.index,range:r.options},on:{change:function(t){arguments[0]=t=r.$handleEvent(t),r.setIsDoneTap.apply(void 0,arguments)}}},[e("v-uni-view",{staticClass:"uni-picker-type",style:{padding:"0 20rpx",boxShadow:"0 0 16rpx rgba(0,0,0,0)",margin:"0",borderColor:"rgba(0,0,0,.6)",backgroundColor:"rgba(255, 255, 255, 0)",color:"#333",textAlign:"left",borderRadius:"16rpx",borderWidth:"2rpx",width:"100%",lineHeight:"80rpx",fontSize:"28rpx",borderStyle:"solid"}},[r._v(r._s(r.options[r.index]))])],1)],1),e("v-uni-view",{staticClass:"cu-form-group margin-top",style:{padding:"0",boxShadow:"0 0 0px rgba(0,0,0,0)",margin:"0 0 20rpx 0",borderColor:"rgba(0,0,0,0)",backgroundColor:"#fff",borderRadius:"0",borderWidth:"0",width:"100%",borderStyle:"solid",height:"auto"}},[e("v-uni-textarea",{staticStyle:{height:"500upx"},style:{padding:"20rpx",boxShadow:"0 0 0px #A3A9E4 inset",margin:"0",borderColor:"rgba(0,0,0,.6)",backgroundColor:"rgba(255, 255, 255, 0)",color:"#333",borderRadius:"16rpx",borderWidth:"2rpx",width:"100%",fontSize:"28rpx",borderStyle:"solid",height:"300rpx"},attrs:{placeholder:"内容"},model:{value:r.forum.content,callback:function(t){r.$set(r.forum,"content",t)},expression:"forum.content"}})],1),e("v-uni-view",{staticClass:"cu-form-group margin-top",staticStyle:{"text-align":"center"}},[e("v-uni-button",{staticClass:"bg-red margin-tb-sm",style:{padding:"0 20rpx",boxShadow:"0 0 16rpx rgba(0,0,0,0) inset",margin:"20rpx 0",borderColor:"rgba(163, 169, 228, 1)",backgroundColor:"rgba(163, 169, 228, 1)",color:"rgba(0, 0, 0, 1)",borderRadius:"80rpx",borderWidth:"1",width:"60%",fontSize:"28rpx",borderStyle:"solid",height:"80rpx"},on:{click:function(t){arguments[0]=t=r.$handleEvent(t),r.onSubmitTap.apply(void 0,arguments)}}},[r._v("确认提交")])],1)],1)],1)},i=[];e.d(t,"b",(function(){return a})),e.d(t,"c",(function(){return i})),e.d(t,"a",(function(){return o}))},"28b6":function(r,t,e){var o=e("b8d7");"string"===typeof o&&(o=[[r.i,o,""]]),o.locals&&(r.exports=o.locals);var a=e("4f06").default;a("b7fbeaa4",o,!0,{sourceMap:!1,shadowMode:!1})},4280:function(r,t,e){"use strict";var o=e("4ea4");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,e("96cf");var a=o(e("3b8d")),i={data:function(){return{forum:{content:"",id:"",title:"",isdone:"开放",parentid:0},index:0,options:["开放","关闭"],username:"",user:{}}},onLoad:function(){var r=(0,a.default)(regeneratorRuntime.mark((function r(t){var e,o,a;return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:return e=uni.getStorageSync("nowTable"),r.next=3,this.$api.session(e);case 3:if(o=r.sent,this.user=o.data,"dangyuan"==e&&(this.username=this.user.dangyuanzhanghao),!t.id){r.next=12;break}return this.id=t.id,r.next=10,this.$api.info("forum",this.id);case 10:a=r.sent,this.forum=a.data;case 12:this.styleChange();case 13:case"end":return r.stop()}}),r,this)})));function t(t){return r.apply(this,arguments)}return t}(),methods:{styleChange:function(){this.$nextTick((function(){}))},onSubmitTap:function(){var r=(0,a.default)(regeneratorRuntime.mark((function r(){return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:if(this.forum.username=this.username,!this.id){r.next=6;break}return r.next=4,this.$api.update("forum",this.forum);case 4:r.next=8;break;case 6:return r.next=8,this.$api.save("forum",this.forum);case 8:this.$utils.msgBack("操作成功");case 9:case"end":return r.stop()}}),r,this)})));function t(){return r.apply(this,arguments)}return t}(),setIsDoneTap:function(r){this.index=r.target.value,this.forum.isdone=this.options[this.index]}}};t.default=i},"96a8":function(r,t,e){"use strict";e.r(t);var o=e("4280"),a=e.n(o);for(var i in o)"default"!==i&&function(r){e.d(t,r,(function(){return o[r]}))}(i);t["default"]=a.a},b8d7:function(r,t,e){var o=e("24fb");t=o(!1),t.push([r.i,'uni-page-body[data-v-4ad12420]{background-color:#fff}.container[data-v-4ad12420]{padding:%?20?% %?24?%}.container[data-v-4ad12420]:after{position:fixed;top:0;right:0;left:0;bottom:0;content:"";background-attachment:fixed;background-size:cover;background-position:50%}.cu-form-group[data-v-4ad12420]{padding:0;background-color:initial;min-height:inherit}.cu-form-group+.cu-form-group[data-v-4ad12420]{border:0}.cu-form-group uni-input[data-v-4ad12420]{padding:0 %?30?%}.cu-form-group uni-textarea[data-v-4ad12420]{padding:%?30?%;margin:0}.cu-form-group uni-picker[data-v-4ad12420]::after{line-height:%?80?%}.bg-red[data-v-4ad12420]{line-height:%?80?%}body.?%PAGE?%[data-v-4ad12420]{background-color:#fff}',""]),r.exports=t},e201:function(r,t,e){"use strict";var o=e("28b6"),a=e.n(o);a.a}}]);