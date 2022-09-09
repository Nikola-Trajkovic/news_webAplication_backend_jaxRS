<template>
  <div>
    <b-table
      id="news-table"
      fixed
      :items="newsArr"
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
  </div>
</template>

<script>

  import { mapActions, mapState } from 'vuex';

  export default {
    name: 'NewsList',

    data() {
      return {
        fields: ['title', 'content', 'date', 'visits'],
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
      news(nVal){
        this.newsArr = [];

        for(let i of nVal){
          this.newsArr.push(i);
        }
      }
    },

    mounted() {
      this.fetchNewsSorted().then( obj => this.items = obj );
    },

    methods: {
      ...mapActions([
        'fetchNewsSorted'
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