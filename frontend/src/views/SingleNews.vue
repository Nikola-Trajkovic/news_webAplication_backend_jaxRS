<template>
    <div id="app2">
        <!-- <Header v-title="form.title"/> -->
        <h1 style="font-weight: bold;" v-text="form.title"></h1>

        <h6 style="font-weight: bold;" v-text="form.date"></h6>

        <h4 style="font-weight: bold; color:black;" v-text="form.content"></h4>

        <h5 style="font-weight: bold;" v-text="userName"></h5>

        <h4>Tags:</h4>

        <p v-for="tag in tags" :key="tag.tag_id" @click="tagClickedFunc(tag.tag_id)" style="color:blue;">
            {{tag.tag}}
        </p>

        <b-form @submit="onSubmit">
            <b-form-group label="Ime:" label-for="name" class="grp">
            <b-form-input id="name" v-model="commentForm.name" required></b-form-input>
            </b-form-group>

            <b-form-group label="Komentar:" label-for="comment" class="grp">
            <b-form-input id="comment" v-model="commentForm.comment" required></b-form-input>
            </b-form-group>
          
            <br>
            <b-button type="submit" variant="primary">Unesi</b-button>
            <br>
        </b-form>

        <h2>Komentari:</h2>

        <b-table
        id="comments"
        hover
        fixed
        :items="comm"
        :fields="commentFields"
        small
      >
      </b-table>

    </div>
</template>

<script>

    // import Header from '../components/Header.vue'
    import {mapActions, mapState} from 'vuex';

    export default {
        name:'SingleNews',

        data(){
            return{
                form: {
                    news_id: '',
                    user_id: '',
                    category_id: '',
                    title: '',
                    content: '',
                    date: '',
                    visits: ''
                },
                commentForm: {
                    name: '',
                    comment: ''
                },
                commentFields: ['author', 'content', 'date'],
                commentsList: [],
                comm: [],
                tags: [],
                userName:''
            }
        },

        components:{
            // Header
        },

        computed:{
            ...mapState([
                'comments'
            ])
        },

        watch: {
            commentsList(nVal){

                this.comm = [];

                for(let i of nVal){
                    this.comm.push(i);
                }
            }
        },

        methods: {
            ...mapActions([
                'fetchSingleNews',
                'addComment',
                'fetchSingleUser',
                'fetchComments',
                'incrementNewsVisits',
                'fetchTags'
            ]),

            onSubmit(e) {
                e.preventDefault();

                const tmp = {
                    author: this.commentForm.name,
                    content: this.commentForm.comment,
                    date: "22/21/22",
                    news_id: this.form.news_id
                }

                this.commentForm.name = "";

                this.commentForm.comment = "";

                this.addComment(tmp);

                location.reload();
            },

            tagClickedFunc(tag_id){
                this.$router.push({ name: 'TagNews', params: { id: tag_id } }); // kad neko klikne neki tag prebacujem ga na TagNews stranicu
            }
        },

        mounted(){
            
            this.fetchSingleNews(this.$route.params.id).then(res => { // dohvatam vest
                this.form = res;

                this.fetchSingleUser(res.user_id).then(res2 => { // dohvatam user-a koji je napisao ovu vest 
                    this.userName = "Author: " + res2.name;
                });

                this.fetchTags(res.news_id).then(res => { // dohvatam sve tagove za ovu vest
                    this.tags = res;
                })

                this.fetchComments(res.news_id).then(res => this.commentsList = res); // dohvatam komentare za ovu vest

                this.incrementNewsVisits(res); // povecavam broj poseta za ovu vest

            });

        }
        
    }


</script>

<style scoped>
    #app2{
        text-align: center; 
        margin-left: 20%;
        margin-right: 20%;
    }

  .grp{
      padding: 10px;
      margin-top: 10px;
  }
</style>