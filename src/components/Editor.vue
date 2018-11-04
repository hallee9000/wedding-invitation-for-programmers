<template>
  <div>
    <div class="wedding-editor" ref="editor">
      <!-- 日期 -->
      <p class="code">Last login: <span>{{ startDate }}</span> on ttys001</p>
      <!--代码编辑区-->
      <pre>
        <code v-html="highlightedCode"></code>
      </pre>
      <Executions/>
    </div>
  </div>
</template>

<script>
  import Prism from 'prismjs'
  import 'prismjs/themes/prism-okaidia.css'
  import '../utils/raf'
  import data from '../mock/data'

  import Executions from './Executions'
  // import Barrage from './Barrage'
  // import Invitation from './Invitation'

  export default {
    props: [],
    name: 'Editor',
    components: { Executions },
    created() {
      this.startDate = (new Date()).toDateString()
      this.progressivelyTyping()
    },
    updated() {
      // 保持页面一直滚到最下面
      this.$refs.editor.scrollTop = 100000
    },
    computed: {
      highlightedCode () {
        const code = Prism.highlight(
            this.currentCode,
            Prism.languages.javascript
          )
        const codeWithCursor = `${code}<span style="opacity:${this.isCursorVisible}"> ▍</span>`
        return codeWithCursor
      }
    },
    methods: {
      // 代码输入
      progressivelyTyping() {
        return new Promise((resolve) => {
          let count = 0, typingCount = 0, typing
          // 写代码每一帧的函数
          let step = () => {
            let randomNumber = Math.round(Math.random() * 6)
            // 摸你打字的随机速度
            if(count % 2 === 0 && randomNumber % 4 === 0){
              this.currentCode = this.code.substring(0, typingCount)
              typingCount++
            }
            // 大约每 24 帧光标闪动一次
            if(count % 24 === 0){
              this.isCursorVisible = this.isCursorVisible === 0 ? 1 : 0
            }
            count++
            if (typingCount <= this.code.length) {
              typing = requestAnimationFrame(step)
            } else {
              resolve()
              cancelAnimationFrame(typing)
            }
          }
          typing = requestAnimationFrame(step)
        })
      }
    },
    data() {
      return {
        startDate: '',
        code: data.code,
        currentCode: '',
        isCursorVisible: 1
      }
    }
  }

</script>

<style lang="less">
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