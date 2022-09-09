<template>
  <div>
    <b-pagination
      v-model="currentPage"
      :total-rows="items.length"
      :per-page="perPage"
      aria-controls="news-table"
    ></b-pagination>
    <b-table
      id="news-table"
      fixed
      :items="items"
      :fields="fields"
      small
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
    <b-button type="submit" variant="primary" @click="formView">Dodaj vest</b-button>
    <br><br>

    <div v-if="kliknuo">
        <b-form @submit="onSubmit" id="formId">

        <b-form-group label="Naslov:" label-for="title" class="grp">
        <b-form-input id="title" v-model="form.title" required></b-form-input>
        </b-form-group>

        <b-form-group label="Tekst:" label-for="content" class="grp">
        <b-form-input id="content" v-model="form.content" required></b-form-input>
        </b-form-group>

        <b-form-group label="Tagovi:" label-for="tags" class="grp">
        <b-form-input id="tags" v-model="form.tags" required></b-form-input>
        </b-form-group>

        <b-form-group label="Kategorija:" label-for="category" class="grp">
        <b-form-select id="tags" v-model="selectedCategory" :options="categoriesArr" required></b-form-select>
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
    name: 'NewsListAll',

    props: {
      category_id: Number // ovo polje dobijamo od News.vue, ako je kliknuta neka kategorija ovde dobijamo njen id po kom cemo da filtriramo
    },

    data() {
      return {
        fields: ['title', 'content', 'date', 'visits', {key: 'actions', label: 'Actions'}],
        items: [],
        newsArr: [],
        categoriesArr: [],
        categories: [],
        currentPage: 1,
        perPage: 10,
        kliknuo: false,
        updateBool: false,
        selectedCategory: null,
        updateNewsId: 0,
        updateNewsVisits: 0,
        form: {
            title: '',
            content: '',
            tags: ''
        }
      }
    },

    computed: {
      ...mapState([
        'news'
      ])
    },

    watch: {
      news(nVal){
        this.newsArr = [];

        for(let i of nVal){
          this.newsArr.push(i);
        }
      }
    },

    mounted() {
      if(this.category_id){ // ako mi je prosledjen category_id znaci da dolazim iz kategorija i moram da filtriram
        this.fetchNewsByCategory(this.category_id).then( obj => this.items = obj);
      }else{ // ako nemam category_id prikazujem sve vesti, nema filtera
        this.fetchNews().then( obj => this.items = obj );
      }

      this.fetchCategories().then( obj =>{ // ovo mi treba kad se pravi nova vest, da mu prikazem sve kategorije i posle istu kategoriju da mu dodelim kad pravi vest
         for(let i of obj){
            this.categoriesArr.push(i.name); // ovo mi je lista za prikazivanje u dropdown meniju
            this.categories.push(i); // lista objekata
         }
         
        });

    },

    methods: {
      ...mapActions([
        'fetchNews',
        'fetchNewsByCategory',
        'addNews',
        'fetchCategories',
        'addTag',
        'addNewsTag',
        'deleteNews',
        'updateNews',
        'fetchTags',
        'deleteNewsIdInNewsTags'
      ]),

      obrisi(e){
        this.deleteNews(e.news_id);

        location.reload();
      },

      izmeni(e){
        e;
        this.updateNewsId = e.news_id; // pamtim id vesti koju sam kliknuo da bih posle mogao da je izmenim
        this.updateNewsVisits = e.visits;
        this.kliknuo = true;

        this.form.title = e.title;
        this.form.content = e.content;

        this.fetchTags(e.news_id).then(obj => { // uzimam tagove za ovu vest i stavljam u polje za tagove
          var x = "";
          for(let i of obj){
            x += i.tag + " ";
          }
          this.form.tags = x;
        })
        this.form.tags = e.tags;

        for(let i of this.categories){ // prolazim kroz listu svih kategorija i trazim iste id-jeve da bih prikazao selektovanu kategoriju u dropdown-u
          if(i.category_id == e.category_id){ // e mi je news, a i mi je category i trazim isti category_id
            this.selectedCategory = i.name; // prikazujem ime kategorije
          }
        }

        this.updateBool = true;
      },

      rowClicked(record) {
        
        let routeData = this.$router.resolve({name: 'SingleNews', params: {id: record.news_id }});
        window.open(routeData.href, '_blank');
      },

        formView(){ // za uklanjanje i prikazivanje forme za dodavanje vesti
          this.form.title = '';
          this.form.content = '';
          this.form.tags = '';
          this.selectedCategory = '';
          this.updateBool = false;

          if(this.kliknuo){
              this.kliknuo = false;
          }else{
              this.kliknuo = true;
          }
        },

        onSubmit(e){ // ovde ulazi ili kad dodaje novu vest ili kad update-uje staru
            e.preventDefault();

            let category_id;
            let news_id;

            for(let i of this.categories){ // na ovaj nacin pronalazim kategoriju koja je izabrana
                if(i.name == this.selectedCategory){
                    category_id = i.category_id;
                }
            }

            const obj = {
                title: this.form.title,
                content: this.form.content,
                date: "1/1/2012",
                visits: 0,
                user_id: localStorage.userId,
                category_id: category_id
            }

            const objUpdate = { // objekat koji saljem kad radim update, ima i news_id za razliku od onog gore
                news_id: this.updateNewsId,
                title: this.form.title,
                content: this.form.content,
                date: "1/1/2012",
                visits: this.updateNewsVisits,
                user_id: localStorage.userId,
                category_id: category_id
            }

            if(this.updateBool){ // za update neke vesti
              this.updateNews(objUpdate);

                const tags = this.form.tags.split(" ");

                this.deleteNewsIdInNewsTags(this.updateNewsId);

                if(tags.length == 1){ // ukoliko imam samo jedan tag(nema spejs)
                  const tmp = {
                        tag: tags[0] // jer imam samo jedan element(nemam spejs nigde)
                    }
                
                    this.addTag(tmp).then(obj => { // dodajem tag u tabelu tag
                      const newsTag = {
                          news_id: this.updateNewsId,
                          tag_id: obj.tag_id
                      }

                      this.addNewsTag(newsTag); // dodajem news_id i tag_id u newstag tabelu
                  });
                }else{ // ukoliko ima vise tagova
                    for(let i = 0; i < tags.length; i++){ // prolazim kroz listu tagova kako bih ih sve dodao u tabele tag i newstag
                    const tmp = {
                        tag: tags[i]
                    }
                
                    this.addTag(tmp).then(obj => { // dodajem tag u tabelu tag
                      const newsTag = {
                          news_id: this.updateNewsId,
                          tag_id: obj.tag_id
                      }

                      this.addNewsTag(newsTag); // dodajem news_id i tag_id u newstag tabelu
                  });
                    
                }
                }
            }else{ // za dodavanje nove vesti
              this.addNews(obj).then(obj => { // dodajem novu vest
                news_id = obj.news_id;

                const tags = this.form.tags.split(" ");

                if(tags.length == 1){ // ukoliko imam samo jedan tag(nema spejs)
                  const tmp = {
                        tag: tags[0] // jer imam samo jedan element(nemam spejs nigde)
                    }
                
                    this.addTag(tmp).then(obj => { // dodajem tag u tabelu tag
                      const newsTag = {
                          news_id: news_id,
                          tag_id: obj.tag_id
                      }

                      this.addNewsTag(newsTag); // dodajem news_id i tag_id u newstag tabelu
                  });
                }else{ // ukoliko ima vise tagova
                  for(let i = 0; i < tags.length; i++){ // prolazim kroz listu tagova kako bih ih sve dodao u tabele tag i newstag
                    const tmp = {
                        tag: tags[i]
                    }
                
                    this.addTag(tmp).then(obj => { // dodajem tag u tabelu tag
                      const newsTag = {
                          news_id: news_id,
                          tag_id: obj.tag_id
                      }

                      this.addNewsTag(newsTag); // dodajem news_id i tag_id u newstag tabelu
                  });
                    
                }
                }
              });
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

  #news-table{
    color: white;
  }

  #formId{
    margin-left: 30%;
    margin-right: 30%;
  }

  .grp{
    color:white;
  }

</style>