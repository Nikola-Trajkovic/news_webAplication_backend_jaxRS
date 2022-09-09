<template>
  <div id="app">

    <div>
      <b-navbar type="dark" variant="dark">
        <b-navbar-nav>
          <b-nav-item to="/" class="homeClass"><p class="par">Home</p></b-nav-item>
          <b-nav-item to="/najcitanije">Najcitanije</b-nav-item>
          <b-nav-item to="/categories">Kategorije</b-nav-item>
          <b-nav-item to="/news">Vesti</b-nav-item>
          <b-nav-item to="/users">Korisnici</b-nav-item>
          <b-nav-item to="/login">Log In</b-nav-item>
          <b-nav-item @click="logout()" class="customNav">Log Out</b-nav-item>
          <b-nav-item to="/register" class="customNav2">Register</b-nav-item>
        </b-navbar-nav>
      </b-navbar>
    </div>

    <router-view class="stranica"/>
  </div>
</template>

<script>

  import { mapActions, mapState, mapMutations } from 'vuex';

  export default {
    name: 'App',

    data() {
      return {
        items: []
      }
    },

    computed: {
      ...mapState([
        'token',
        'role'
      ])
    },

    mounted() {
      // if (localStorage.token) {
      //   this.setToken(localStorage.token, localStorage.role);
      // }
    },

    methods: {
      ...mapActions([
        
      ]),

      ...mapMutations([
        'removeToken',
        'removeUserId',
        'removeRole',
        'setToken'
      ]),

      logout(){
        this.removeToken();
        this.removeUserId();
        this.removeRole();
        window.location.href = '/';
      },

      funk(){
        console.log(localStorage.role);
      }
    },

    sockets: {
      error(err) {
        err;
        alert("AAAAAAAAAAAAAAA");
      }
    }
  }
</script>


<style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    padding-bottom: 100%;
  }

  .homeClass{
    background-color: rgb(245, 197, 24);
    border-radius: 5px;
    height: 40px;
    margin-left: 3px;
  }

  .par{
    color:black;
    
  }
</style>
