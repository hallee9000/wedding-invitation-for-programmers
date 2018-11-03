<template>
  <div>
    <div v-html="codeInStyleTag"></div>
    <div class="wedding-editor" ref="editor">
      <p class="code">Last login: <span>{{ startDate }}</span></p>
      <!--代码-->
      <pre>
        <code v-html="highlightedCode"></code>
      </pre>
      <!--执行命令-->
      <p class="code" v-for="(execution, key) in executions" v-show="execution.visible">
        <span class="addon">~</span>
        [<span class="time">{{ execution.time }}</span>]<span class="task">{{ execution.name }}</span> <span class="duration">{{ execution.duration }} ms</span>
      </p>
      <!--进度条-->
      <p class="code" v-show="isProcessed">
        <span class="addon">~</span>
        {{ progressBarText }}
      </p>
    </div>
  </div>
</template>

<script>
  import "babel-polyfill"
  import { Promise } from 'es6-promise'
  import Prism from 'prismjs'
  import 'prismjs/themes/prism-okaidia.css'

  import '../../../utils/raf'

  export default {
    props: [],
    name: 'Editor',
    created() {
      this.startDate = (new Date()).toDateString();
      this.progressivelyExecute()
    },
    updated(){
      this.$refs.editor.scrollTop = 100000;
    },
    computed: {
      highlightedCode: function () {
        return Prism.highlight(this.currentCode, Prism.languages.javascript)+'<span style="opacity:'+this.isCursorVisible+'"> ▍</span>';
      },
      codeVisible0: function () {
        return this.codeVisible['initiate']
      },
      codeVisible1: function () {
        return this.codeVisible['decompress']
      },
      codeInStyleTag: function () {
        return `<style>${this.animationStyle}</style>`
      }
    },
    methods: {
    // 执行命令
      progressivelyExecute: async function () {
        await this.progressivelyTyping()
        await this.progressivelyRun('initiate')
        await this.progressivelyRun('decompress')
        await this.progressivelyRun('assemble')
        await this.progressivelyRun('package')
        await this.progressivelyRun('smile')
        await this.progressivelyRun('laugh')
        await this.progressivelyRun('success')
        await this.successProcessing(1000)
      },
      // 代码输入
      progressivelyTyping() {
        return new Promise((resolve, reject) => {
          let count = 0, typingCount = 0, typing;
          let step = (timestamp) => {
            let randomNumber = Math.round(Math.random()*6);
            if(count%2===0&&randomNumber%4===0){
              this.currentCode = this.code.substring(0,typingCount);
              typingCount++;
            }
            if(count%24===0){
              this.isCursorVisible = this.isCursorVisible===0?1:0;
            }
            count++;
            if (typingCount <= this.code.length) {
              typing = requestAnimationFrame(step);
            }else{
              resolve();
              cancelAnimationFrame(typing);
            }
          }
          typing = requestAnimationFrame(step);
        });
      },
      // 执行命令
      progressivelyRun(key,customDuration) {
        return new Promise((resolve, reject) => {
          let now = new Date(),
              duration = customDuration?customDuration:Math.random()*50+250,
          showCode = () => {
            this.executions[key].time = now.toLocaleTimeString();
            this.executions[key].duration = duration.toFixed(2);
            this.executions[key].visible = true;
            resolve();
          }
          setTimeout(showCode,duration);
        })
      },
      // 显示进度条
      successProcessing(duration){
        return new Promise((resolve, reject) => {
          let start = null, progressing, progressingCount = 0;
          this.isProcessed = true;
          let step = (timestamp) => {
              let timeGap;
              if (start === null) start = timestamp;
              timeGap = timestamp - start;
              if(progressingCount%3===0){
                this.progressBarText+='██████░░░░░░░░';
              }
              progressingCount++;
              if (timeGap < duration) {
                progressing = requestAnimationFrame(step);
              }else{
                resolve();
                this.isFinished = true;
                cancelAnimationFrame(progressing);
              }
            }
          progressing = requestAnimationFrame(step);
        });
      },
      // 打开邀请函
      openInvitation(){
        this.isOpening = true;
      },
      // 获取弹幕
      getBarrage(){
        this.barrageData = this.barrages;
        this.$nextTick(()=>{
          this.isOpening = false;
          setTimeout(()=>{
            this.isFinished = false;
          },600)
          this.$refs.wishInput.blur()
          this.barrageAnimationStart();
        })
      },
      // 弹幕动画开始
      barrageAnimationStart(){
        let barrageWidth = this.getWidth(this.$refs.barrage);
        let barrageWidthGroup = [
              this.getWidth(this.$refs.barrageFirst),
              this.getWidth(this.$refs.barrageSecond),
              this.getWidth(this.$refs.barrageThird),
              this.getWidth(this.$refs.barrageFourth)
            ]
        this.initialOffset = barrageWidth+15;
        barrageWidthGroup.map((item,index)=>{
          this.animationStyle+=`
            .barrage-${index}{
              animation: barrage-${index} ${item/40}s linear infinite;
              -webkit-animation: barrage-${index} ${item/40}s linear infinite;
            }
            @keyframes barrage-${index} {
              from {
                transform:translate3d(${barrageWidth+15}px,0,0);
                -webkit-transform:translate3d(${barrageWidth+15}px,0,0);
              }
              to {
                transform:translate3d(-${item+15}px,0,0);
                -webkit-transform:translate3d(-${item+15}px,0,0);
              }
            }`
        })
      },
      getWidth(ref){
        return window.getComputedStyle(ref,null).width.replace('px','')-0;
      },
      filterBarrage: function (barrageData,remainder) {
        if(barrageData){
          return barrageData.filter(function (barrage,index,barrageData) {
            if(index%4===remainder){
              return barrage;
            }
          })
        }
      }
    },
    data(){
      return {
        startDate: '',
        code:
`
  // 今天心情好
  // 我给你表演写代码
  // 我要开始写啦
  const { 😆, 🤗 } = 🌎;
  😆.say('嫁给我吧!❤️');
  🤗.response('好的，只要你请我吃🍉！');
  Utils.marry(😆,🤗);
  Utils.generateInvitation(😆,🤗);
  // 好了我写完了
  // 我要开始运行啦`,
        barrages: [
          '66666666',
          '愿你美梦成真',
          '你的邀请函真是美',
          '人美心更美',
          '你是最棒的',
          '哇哇哇',
          '还差女朋友吗',
          '今晚翻你牌',
          '看朕给你打下的江山',
          '你写代码老快了',
          '你的代码真是棒',
          '😗 😙 😚 😋 ',
          '只要代码写得好，单身永远跑不了'
        ],
        animationStyle:'',
        barrageData:false,
        currentCode: '',
        isCursorVisible: 0,
        isFinished: false,
        isOpening: false,
        progressBarText: '',
        isProcessed: false,
        wish: '',
        initialOffset: 0,
        executions: {
          initiate: {
            name:'初始化',
            time: '',
            duration: 0,
            visible: false
          },
          decompress: {
            name:'解压中',
            time: '',
            duration: 0,
            visible: false
          },
          assemble: {
            name:'组装中',
            time: '',
            duration: 0,
            visible: false
          },
          package: {
            name:'打包中',
            time: '',
            duration: 0,
            visible: false
          },
          smile: {
            name:'微笑🙂',
            time: '',
            duration: 0,
            visible: false
          },
          laugh: {
            name:'大笑😄',
            time: '',
            duration: 0,
            visible: false
          },
          success: {
            name:'成功',
            time: '',
            duration: 0,
            visible: false
          }
        }
      }
    }
  }

</script>