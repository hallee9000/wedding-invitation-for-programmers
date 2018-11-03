<template>
  <div class="container wedding">
    <header class="wedding-header">
        <a href="javascript:;"></a>
        <a href="javascript:;" class="minimum"></a>
        <a href="javascript:;" class="maximum"></a>
    </header>
    <Editor></Editor>
  </div>
</template>

<script>
  import Editor from "./Editor.vue"

  export default {
    props: [],
    components: {
      Editor
    },
    name: 'Wedding',
    data(){
      return {}
    }
  }

</script>