<template>
  <div class="wedding">
    <header class="wedding-header">
        <a href="javascript:;"></a>
        <a href="javascript:;" class="minimum"></a>
        <a href="javascript:;" class="maximum"></a>
    </header>
    <Editor/>
  </div>
</template>

<script>
  import Editor from "./components/Editor.vue"

  export default {
    props: [],
    components: {
      Editor
    },
    name: 'Wedding'
  }

</script>

<style lang="less">
@import './assets/base.less';
html,
body{
  height: 100%;
}
.wedding{
  position: relative;
  max-width: 568px!important;
  height: 100%;
  min-height: 100%;
  margin: 0 auto;
  background: #2B2B48;
  perspective: 500px;
  .wedding-header{
    position: relative;
    z-index: 3;
    padding: 12px;
    overflow: hidden;
    background: #2B2B48;
    >a{
      float: left;
      display: block;
      width: 16px;
      height: 16px;
      margin-right: 5px;
      border-radius: 8px;
      background: #FC615D;
      &.minimum{
        background: #FDBC40;
      }
      &.maximum{
        background: #34C84A;
      }
    }
  }
}
</style>
