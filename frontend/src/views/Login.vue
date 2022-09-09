<template>
  <div id="app">
    <Header title="Log In"/>
    <br>

    <b-form @submit="onSubmit" class="forma">
      <b-form-group label="Email:" label-for="email" class="grp">
        <b-form-input id="email" v-model="form.email" placeholder="Enter email" required></b-form-input>
      </b-form-group>

      <b-form-group label="Password:" label-for="password" class="grp">
        <b-form-input id="password" v-model="form.password" type="password" required></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
    </b-form>
  </div>
</template>
 
<script>

  import Header from '@/components/Header.vue';
  import { mapActions, mapState } from 'vuex';

  export default {
    name: 'Login',
    
    components: {
      Header
    },

    data() {
      return {
        form: {
          email: '',
          password: ''
        }
      }
    },

    computed: {
      ...mapState([
        'role'
      ])
    },

    methods: {
      ...mapActions([
        'login'
      ]),

      onSubmit(e) {
        e.preventDefault();

        if(this.form.email != "admin" && this.form.password != "admin"){
            if(this.form.email.length < 3){
                this.$alert("Invalid email!");
                return;
            }else if(this.form.password.length < 6){
                this.$alert("Invalid password!");
                return;
            }
        }

        this.login(this.form);
        this.$router.push({ name: 'Home' });
      }
    }
  }
</script>

<style scoped>
    #app{
     
    }

    .forma{
        width: 300px;
        margin-left: 600px;
        color:black;
    }

    .grp{
        padding: 10px;
        margin-top: 20px;
    }
</style>
