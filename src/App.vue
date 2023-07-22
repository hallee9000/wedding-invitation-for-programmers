<template>
  <div class="invitation">
    <Editor/>
  </div>
</template>

<script setup lang="ts">
  import Editor from "./components/Editor.vue"
</script>

<style lang="scss">
  .invitation {
    position: relative;
    max-width: 568px!important;
    height: 100%;
    min-height: 100%;
    margin: 0 auto;
    background: #2B2B48;
    perspective: 500px;
  }
</style>
