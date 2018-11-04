<template>
  <div>
    <!--执行命令-->
    <p class="code" v-for="(execution, key) in executions" :key="key" v-show="execution.visible">
      <span class="addon">~</span>
      [<span class="time">{{ execution.time }}</span>]<span class="task">{{ execution.name }}</span> <span class="duration">{{ execution.duration }} ms</span>
    </p>
    <!--进度条-->
    <p class="code" v-show="isProcessed">
      <span class="addon">~</span>
      {{ progressBarText }}
    </p>
  </div>
</template>

<script>
  import data from '../mock/data'
  export default {
    props: [],
    name: 'Executions',
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
      // 逐条执行命令
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
      }
    }
  }
</script>