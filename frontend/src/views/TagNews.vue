<template>
  <div id="app">
    <TagNewsList/>
  </div>
</template>

<script>
import TagNewsList from '../components/TagNewsList.vue';

export default {

  name: 'Home',

  components: {
    TagNewsList
},

  data(){
    return{
     
    }
  },

  methods: {
    
  }
}
</script>

<style scoped>
  #app{
    background-color: rgb(39, 39, 39);
  }
</style>
