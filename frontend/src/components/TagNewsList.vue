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
      <template #cell(isHighlight)="data">
        <b-icon v-if="data.value" icon="check-square" variant="success" scale="2"></b-icon>
        <b-icon v-else icon="x-circle" variant="danger" scale="2"></b-icon>
      </template>
    </b-table>
    <b-pagination
      v-model="currentPage"
      :total-rows="items.length"
      :per-page="perPage"
      aria-controls="news-table"
    ></b-pagination>
  </div>
</template>

<script>

  import { mapActions, mapState } from 'vuex';

  export default {
    name: 'TagNewsList',

    data() {
      return {
        fields: ['title', 'content', 'date', 'visits', 'user_id', 'category_id'],
        items: [],
        newsArr: [],
        currentPage: 1,
        perPage: 10
      }
    },

    computed: {
      ...mapState([
        'news'
      ])
    },

    watch: {
    //   news(nVal){
    //     this.newsArr = [];

    //     for(let i of nVal){
    //       this.newsArr.push(i);
    //     }
    //   }
    },

    mounted() {
       this.fetchNewsByTag(this.$route.params.id).then( obj => this.items = obj );
    },

    methods: {
      ...mapActions([
        'fetchNewsByTag'
      ]),

      rowClicked(record) {
        this.$router.push({ name: 'SingleNews', params: { id: record.news_id } });
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

</style>