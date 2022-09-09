<template>
  <div class="divClass">
    <b-pagination
      v-model="currentPage"
      :total-rows="categoriesArr.length"
      :per-page="perPage"
      aria-controls="categories-table"
    ></b-pagination>
    <b-table
      id="categories-table"
      fixed
      :items="categoriesArr"
      :fields="fields"
      :per-page="perPage"
      :current-page="currentPage"
      @row-clicked="rowClicked"
    >
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
    <b-button type="submit" variant="primary" @click="formView">Dodaj kategoriju</b-button>
    <br><br>

    <div v-if="kliknuo">
        <b-form @submit="onSubmit" id="formId">
        <b-form-group label="Kategorija:" label-for="name" class="grp">
        <b-form-input id="name" v-model="form.name" required></b-form-input>
        </b-form-group>

        <b-form-group label="Opis:" label-for="description" class="grp">
        <b-form-input id="description" v-model="form.description" required></b-form-input>
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
    name: 'CategoriesList',

    data() {
      return {
        form: {
            name: '',
            description: ''
        },
        fields: ['name', 'description', {key: 'actions', label: 'Actions'}],
        items: [],
        categoriesArr: [],
        currentPage: 1,
        perPage: 10,
        kliknuo: false,
        updateBool: false,
        updateCategoryId: 0
      }
    },

    computed: {
      ...mapState([
        'categories'
      ])
    },

    watch: {
      categories(nVal){
        this.categoriesArr = [];

        for(let i of nVal){
          this.categoriesArr.push(i);
        }
      }
    },

    mounted() {
      this.fetchCategories().then( obj => this.categoriesArr = obj );
    },

    methods: {
      ...mapActions([
        'fetchCategories',
        'deleteCategory',
        'updateCategory',
        'addCategory'
      ]),

      rowClicked(record) {
        record;
        this.$router.push({ name: 'News', params: { id: record.category_id } });
      },

      obrisi(e){
        this.deleteCategory(e.category_id);

        location.reload();
      },

      izmeni(e){
        this.updateCategoryId = e.category_id;
        this.kliknuo = true;

        this.form.name = e.name;
        this.form.description = e.description;

        this.updateBool = true;
      },

      formView(){ // za uklanjanje i prikazivanje forme za dodavanje kategorija
        this.form.name = '';
        this.form.description = '';
        this.updateBool = false;
        if(this.kliknuo){
            this.kliknuo = false;
        }else{
            this.kliknuo = true;
        }
      },

      onSubmit(e) {
        e.preventDefault();

        const tmp = {
            name: this.form.name,
            description: this.form.description
        }

        const tmpUpdate = { // ovaj ima category_id za razliku od obicnog tmp koji koristim za dodavanje
            category_id: this.updateCategoryId,
            name: this.form.name,
            description: this.form.description
        }

        this.form.name = '';
        this.form.description = '';

        if(this.updateBool){ // za update kategorije
          this.updateCategory(tmpUpdate);
        }else{ // za dodavanje nove kategorije
          this.addCategory(tmp);
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

  #categories-table{
    color: white;
  }

  .divClass{
    margin-left: 20%;
    margin-right: 20%;
  }

  .grp{
    color:white;
  }

</style>