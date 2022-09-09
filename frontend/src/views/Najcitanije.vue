<template>
    <div id="app">
      <div>
        <Header title="Najcitanije vesti"/>
        <br><br>
        <NewsList/>
      </div>
        
    </div>
</template>

<script>
  import Header from '@/components/Header.vue';
  import NewsList from '@/components/NewsList.vue';
  import { mapActions } from 'vuex';

  export default {
    name: 'Najcitanije',
    
    components: {
    Header,
    NewsList
},

    data() {
        return {
            depID: null
        }
    },

    watch: {
      $route() {
        
      }
    },

    mounted() {
        

    },

    methods: {
      ...mapActions([
        
      ])
    }
  }
</script>

<style scoped>
  #app{
    background-color: rgb(39, 39, 39);
  }

  .mList{
    margin:20px;
  }

</style>