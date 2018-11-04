<template>
  <div>
    <div v-html="codeInStyleTag"></div>
    <div class="wedding-editor" ref="editor">
      <!-- 日期 -->
      <p class="code">Last login: <span>{{ startDate }}</span> on ttys001</p>
      <!--代码编辑区-->
      <pre>
        <code v-html="highlightedCode"></code>
      </pre>
      <Executions/>
      <Barrage/>
      <Invitation/>
    </div>
  </div>
</template>

<script>
  import "babel-polyfill"
  import { Promise } from 'es6-promise'
  import Prism from 'prismjs'
  import 'prismjs/themes/prism-okaidia.css'

  import Executions from './Executions'
  import Barrage from './Barrage'
  import Invitation from './Invitation'

  import data from '../mock/data'
  import '../../utils/raf'

  export default {
    props: [],
    name: 'Editor',
    components: { Executions, Barrage, Invitation },
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
      }
    },
    data(){
      return {
        startDate: '',
        code: data.code,
        barrages: data.barrages,
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
        executions: data.executions
      }
    }
  }

</script>

<style lang="less">
html,
body{
  height: 100%;
}
.container{
  width: 100%!important;
}
.wedding-editor{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 15px;
  padding-top: 50px;
  overflow-x: hidden;
  overflow-y: auto;
  z-index: 1;
  transform-origin: 0 0;
  -webkit-transform-origin: 0 0;
  transition: all 1.6s cubic-bezier(0.4, 0, 1, 1);
  -webkit-transition: all 1.6s cubic-bezier(0.4, 0, 1, 1);
  pre{
    margin: 0;
    margin-top: -25px;
    white-space: pre-wrap;
    code{
      white-space: pre-wrap;
      word-break: break-all;
      font-size: 16px!important;
      margin: 0;
      color: #BBB;
      line-height: 1.2;
      font-family: 'Roboto Mono', 'Menlo', 'Monaco', Courier, monospace !important;
      font-weight: 500 !important;
      background: transparent;
    }
  }
  p.code{
    margin: 0;
    color: #BBB;
    line-height: 1.2;
    font-family: 'Roboto Mono', 'Menlo', 'Monaco', Courier, monospace !important;
    font-weight: 500 !important;
    font-size: 16px!important;
    .addon{
      color: #68FCFB;
    }
    .time{ color: #666; }
    .task{ color: #009AB2; }
    .duration{ color: #BF36B7; }
  }
}
</style>