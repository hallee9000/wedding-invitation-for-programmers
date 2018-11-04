<template>
  <div class="wedding-barrage" ref="barrage" :style="{opacity:barrageData?1:0}">
    <p class="code barrage-0" ref="barrageFirst" :style="{transform:'translate('+initialOffset+'px)',top:'10px'}">
      <span class="mine">{{ wish }}</span>
      <span v-for="(item, index) in filterBarrage(barrageData,0)" :key="index">{{ item }}</span>
    </p>
    <p class="code barrage-1" ref="barrageSecond" :style="{transform:'translate('+initialOffset+'px)',top:'40px'}">
      <span v-for="(item, index) in filterBarrage(barrageData,1)" :key="index">{{ item }}</span>
    </p>
    <p class="code barrage-2" ref="barrageThird" :style="{transform:'translate('+initialOffset+'px)',top:'70px'}">
      <span v-for="(item, index) in filterBarrage(barrageData,2)" :key="index">{{ item }}</span>
    </p>
    <p class="code barrage-3" ref="barrageFourth" :style="{transform:'translate('+initialOffset+'px)',top:'100px'}">
      <span v-for="(item, index) in filterBarrage(barrageData,3)" :key="index">{{ item }}</span>
    </p>
    <div class="barrage-space"></div>
  </div>
</template>

<script>
  import data from '../mock/data'
  import '../../utils/raf'

  export default {
    methods: {
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
    }
  }
</script>

<style lang="less">
  .wedding-barrage{
    position: relative;
    p{
      position: absolute;
      padding: 5px 0;
      white-space:nowrap;
      transition: all 0.6s linear;
      -webkit-transition: all 0.6s linear;
      span{
        padding: 0 15px;
        &.mine{
          color: #e6db74;
          padding: 4px 6px;
          border: 1px solid #e6db74;
        }
      }
    }
    .barrage-space{
      height: 180px;
    }
  }
</style>