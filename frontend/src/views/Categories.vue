<template>
  <div id="app">
    <Header title="Kategorije"/>
    <br><br>
    <CategoriesList/>

  </div>
</template>

<script>
import Header from '../components/Header.vue';
import CategoriesList from '../components/CategoriesList.vue';
import {mapActions} from 'vuex';

export default {

  name: 'Home',

  components: {
    Header,
    CategoriesList
},

  data(){
    return{
        
    }
  },

  methods: {
     ...mapActions([
                
            ]),
  }
}
</script>

<style scoped>
  #app{
    background-color: rgb(39, 39, 39);
  }

  #formId{
    margin-left: 30%;
    margin-right: 30%;
  }


</style>
