<template>
  <div id="app">
    <Header title="Vesti"/>
    <br><br>
    <NewsList/>
  </div>
</template>

<script>
import Header from '../components/Header.vue';
import NewsList from '../components/NewsList.vue';

export default {

  name: 'Home',

  components: {
    Header,
    NewsList
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
