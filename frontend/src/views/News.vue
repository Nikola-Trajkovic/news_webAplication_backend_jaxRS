<template>
  <div id="app">
    <Header title="Vesti"/>
    <br><br>
    <NewsListAll :category_id="this.$route.params.id"/>
  </div>
</template>

<script>
import Header from '../components/Header.vue';
import NewsListAll from '../components/NewsListAll.vue';

export default {

  name: 'Home',

  components: {
    Header,
    NewsListAll
},

  data(){
    return{
        
    }
  },

  methods: {
    
  },

  mounted(){
    
  }
}
</script>

<style scoped>
  #app{
    background-color: rgb(39, 39, 39);
  }
</style>
