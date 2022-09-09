<template>
  <div id="app">
    <Header title="Korisnici"/>
    <br><br>
    <UsersList/>

  </div>
</template>

<script>
import Header from '../components/Header.vue';
import UsersList from '../components/UsersList.vue';
import {mapActions} from 'vuex';

export default {

  name: 'Home',

  components: {
    Header,
    UsersList
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
