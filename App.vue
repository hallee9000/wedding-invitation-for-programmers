<template>
  <div class="container wedding">
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
    name: 'WeddingInvitation',
    data(){
      return {}
    }
  }

</script>