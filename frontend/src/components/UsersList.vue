<template>
  <div>
    <b-pagination
      v-model="currentPage"
      :total-rows="users.length"
      :per-page="perPage"
      aria-controls="users-table"
    ></b-pagination>
    <b-table
      id="users-table"
      fixed
      :items="users"
      :fields="fields"
      small
      :per-page="perPage"
      :current-page="currentPage"
      @row-clicked="rowClicked"
    >
    <template #cell(status)="{item}">
      <b-form-checkbox v-model="item.status" @change="activate(item)">
      </b-form-checkbox>
    </template>
    <template #cell(actions)="row">
      <b-button size="sm" style="background-color:blue; margin-right: 10px;" @click="izmeni(row.item)" class="mr-1">
          Izmeni
      </b-button>
      <b-button size="sm" style="background-color:red;" @click="obrisi(row.item)">
          Obrisi
      </b-button>
    </template>
    </b-table>

    <br>
    <b-button type="submit" variant="primary" @click="formView">Dodaj korisnika</b-button>
    <br><br>

    <div v-if="kliknuo">
        <b-form @submit="onSubmit" id="formId">

        <b-form-group label="Ime:" label-for="name" class="grp">
        <b-form-input id="name" v-model="form.name" required></b-form-input>
        </b-form-group>

        <b-form-group label="Prezime:" label-for="lastname" class="grp">
        <b-form-input id="lastname" v-model="form.lastname" required></b-form-input>
        </b-form-group>

        <b-form-group label="Email:" label-for="email" class="grp">
        <b-form-input id="email" v-model="form.email" required></b-form-input>
        </b-form-group>

        <b-form-group label="Tip:" label-for="role" class="grp">
        <b-form-input id="role" v-model="form.role" required></b-form-input>
        </b-form-group>

        <b-form-group label="Password:" label-for="password" class="grp">
        <b-form-input v-if="!updateBool" type="password" id="password" v-model="form.password" required></b-form-input>
        <b-form-input v-if="updateBool" id="password" v-model="form.password" required></b-form-input>
        </b-form-group>

        <b-form-group label="Confirm Password:" label-for="confirmPassword" class="grp">
        <b-form-input v-if="!updateBool" type="password" id="confirmPassword" v-model="form.confirmPassword" required></b-form-input>
        <b-form-input v-if="updateBool" id="confirmPassword" v-model="form.confirmPassword" required></b-form-input>
        </b-form-group>
        
        <br>
        <b-button v-if="!updateBool" type="submit" variant="primary">Unesi</b-button>
        <b-button v-if="updateBool" type="submit" variant="primary">Izmeni</b-button>
        <br>
    </b-form>
    </div>

  </div>
</template>

<script>

  import { mapActions, mapState } from 'vuex';

  export default {
    name: 'UsersList',

    data() {
      return {
        fields: ['email', 'name', 'lastname', 'role',  {key: 'status', label: 'Aktivan'}, {key: 'actions', label: 'Actions'}],
        users: [],
        currentPage: 1,
        perPage: 20,
        kliknuo: false,
        updateUserId: 0,
        updateBool: false,
        form: {
          name: '',
          lastname: '',
          email: '',
          role: '',
          password: '',
          confirmPassword: ''
        }
      }
    },

    computed: {
      ...mapState([
        'items'
      ])
    },

    watch: {
      items(nVal){
        this.users = [];

        for(let i of nVal){
          this.users.push(i);
        }
      }
    },

    mounted() {
      this.fetchUsers().then( obj =>{ this.users = obj });       
    },

    methods: {
      ...mapActions([
        'fetchUsers',
        'addUser',
        'deleteUser',
        'updateUser'
      ]),

      obrisi(e){
        this.deleteUser(e.user_id);

        location.reload();
      },

       izmeni(e){
        e;
        this.updateUserId = e.user_id; // pamtim id vesti koju sam kliknuo da bih posle mogao da je izmenim
        this.kliknuo = true;

        // this.form.title = e.title;
        // this.form.content = e.content;

        this.form.email = e.email;
        this.form.name = e.name;
        this.form.lastname = e.lastname;
        this.form.role = e.role;
        this.form.password = e.password;
        this.form.confirmPassword = e.password;

        this.updateBool = true;
      },

      rowClicked(record) {
        record;
        // this.$router.push({ name: 'Single', params: { id: record.objectID } });
      },

      activate(e){
        if(e.status == true){
          const objUpdate = { // objekat koji saljem kad radim update, ima i news_id za razliku od onog gore
                user_id: e.user_id,
                email: e.email,
                name: e.name,
                lastname: e.lastname,
                role: e.role,
                status: "active",
                password: e.password
            }
            this.updateUser(objUpdate);
        }else{
          const objUpdate = { // objekat koji saljem kad radim update, ima i news_id za razliku od onog gore
              user_id: e.user_id,
              email: e.email,
              name: e.name,
              lastname: e.lastname,
              role: e.role,
              status: "inactive",
              password: e.password
          }
          this.updateUser(objUpdate);
        }
            
            // location.reload();
      },

      formView(){ // za uklanjanje i prikazivanje forme za dodavanje korisnika
          // this.form.title = '';
          // this.form.content = '';
          // this.form.tags = '';
          // this.selectedCategory = '';
          this.updateBool = false;

          if(this.kliknuo){
              this.kliknuo = false;
          }else{
              this.kliknuo = true;
          }
        },

        onSubmit(e){
          e.preventDefault();

          if(this.form.password != this.form.confirmPassword){
            alert("Passwords must be same!");
            return;
          }

          const obj = {
                email: this.form.email,
                name: this.form.name,
                lastname: this.form.lastname,
                role: this.form.role,
                status: "active",
                password: this.form.password
            }

            const objUpdate = { // objekat koji saljem kad radim update, ima i news_id za razliku od onog gore
                user_id: this.updateUserId,
                email: this.form.email,
                name: this.form.name,
                lastname: this.form.lastname,
                role: this.form.role,
                status: "active",
                password: this.form.password
            }

            if(this.updateBool){
              this.updateUser(objUpdate);
            }else{ // ako dodajem nekog korisnika
              this.addUser(obj);
            }

            location.reload();
        }
    }
  }

</script>

<style scoped>
  .pagination {
    justify-content: center;
  }

  .grp{
    color:white;
  }

  #users-table{
    color: white;
  }

  #formId{
    margin-left: 30%;
    margin-right: 30%;
  }
</style>