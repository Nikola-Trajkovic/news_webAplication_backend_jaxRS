import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    items: [],
    role: '',
    token: '',
    userId: '',
    news: [],
    comments: [],
    tags: [],
    categories: [],
    users: []
  },

  mutations: {
    addNews(state, news){
      state.news.push(news);
    },

    addComment(state, comment){
      state.comments.push(comment)
    },

    addTag(state, tag){
      state.tags.push(tag);
    },

    addCategory(state, category){
      state.categories.push(category);
    },

    addUser(state, user){
      state.users.push(user);
    },

    setUserId(state, id){
      state.userId = id;
      localStorage.userId = id;
    },

    addItem(state, item) {
      state.items.push(item);
    },

    setToken(state, token) {
      state.token = token;
      localStorage.token = token;
    },

    setRole(state, role){
      state.role = role;
      localStorage.role = role;
    },

    removeToken(state) {
      state.token = '';
      localStorage.token = '';
    },

    removeUserId(state) {
      state.userId = '';
      localStorage.userId = '';
    },

    removeRole(state){
      state.role = '';
      localStorage.role = '';
    }
  },

  actions: {
    fetchNews({commit}){
      return new Promise( (resolve) => {
        fetch('http://localhost:10000/api/news',{
          headers: {}
        })
          .then( obj => obj.json() )
          .then( data => {
            this.state.news = [];
            data.forEach( el => {
                commit('addNews', el);
            });
            resolve(data);
        });
      });
    },

    fetchNewsSorted({commit}){
      return new Promise( (resolve) => {
        fetch('http://localhost:10000/api/news/sorted',{
          headers: {}
        })
          .then( obj => obj.json() )
          .then( data => {
            this.state.news = [];
            data.forEach( el => {
                commit('addNews', el);
            });
            resolve(data);
        });
      });
    },

    fetchUsers({commit}){
      return new Promise( (resolve) => {
        fetch('http://localhost:10000/api/users',{
          headers: {}
        })
          .then( obj => obj.json() )
          .then( data => {
            this.state.users = [];
            data.forEach( el => {
                commit('addUser', el);
            });
            resolve(data);
        });
      });
    },

    fetchNewsByCategory({commit}, id){ // za filtriranje po kategorijama
      return new Promise( (resolve) => {
        fetch(`http://localhost:10000/api/news/category/${id}`,{
          headers: {}
        })
          .then( obj => obj.json() )
          .then( data => {
            commit;
            resolve(data);
        });
      });
    },

    fetchSingleNews({commit}, id){ // dohvatanje samo jedne vesti
      return new Promise( (resolve) => {
          fetch(`http://localhost:10000/api/news/${id}`, {})
          .then(obj => obj.json())
          .then(res => {
            resolve(res);
            commit;
          });
      });
    },

    addNews({ commit }, obj){
      return new Promise( (resolve) => {
        fetch('http://localhost:10000/api/news', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json',
                   'Authorization': `Bearer ${localStorage.token}` },
        body: JSON.stringify(obj)
      })
      .then( res => res.json())
      .then((data) => {
        resolve(data);
        commit;
      });
      })
    },

    addUser({ commit }, obj){
      return new Promise( (resolve) => {
        fetch('http://localhost:10000/api/users', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json',
                   'Authorization': `Bearer ${localStorage.token}` },
        body: JSON.stringify(obj)
      })
      .then( res => res.json())
      .then((data) => {
        resolve(data);
        commit;
      });
      })
    },

    deleteUser({ commit }, id){
      fetch(`http://localhost:10000/api/users/${id}`, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' }
      }).then( res =>{ 
        res; 
        commit;
      });
    },    

    fetchComments({commit}, id){
      return new Promise( (resolve) => {
        fetch(`http://localhost:10000/api/comments/${id}`,{
          headers: {}
        })
          .then( obj => obj.json() )
          .then( data => {
            this.state.comments = [];
            data.forEach( el => {
                commit('addComment', el);
            });
            resolve(data);
        });
      });
    },

    addComment({ commit }, obj){
      fetch('http://localhost:10000/api/comments', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
      }).then( res =>{ 
        res.json() 
        commit;
      });
    },

    fetchSingleUser({commit}, id){ // dohvatanje samo jednog user-a
      return new Promise( (resolve) => {
        fetch(`http://localhost:10000/api/users/${id}`, {
          headers: {'Authorization': `Bearer ${localStorage.token}`}
        })
        .then(obj => obj.json())
        .then(res => {
          commit('addNews', res);
          resolve(res);
        });
      });
    },

    incrementNewsVisits({commit}, obj){
      fetch(`http://localhost:10000/api/news/incr`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(obj)
          }).then( res =>{ 
            res.json() 
            commit;
          });
    },

    fetchTags({commit}, id){
      return new Promise( (resolve) => {
        fetch(`http://localhost:10000/api/newstag/${id}`,{
          headers: {}
        })
          .then( obj => obj.json() )
          .then( data => {
            this.state.tags = [];
            data.forEach( el => {
                commit('addTag', el);
            });
            resolve(data);
        });
      });
    },

    fetchNewsByTag({commit}, id){
      return new Promise( (resolve) => {
        fetch(`http://localhost:10000/api/newstag/tag/${id}`,{
          headers: {}
        })
          .then( obj => obj.json() )
          .then( data => {
            commit;
            resolve(data);
        });
      });
    },

    fetchCategories({commit}){
      return new Promise( (resolve) => {
        fetch('http://localhost:10000/api/categories',{
          headers: {}
        })
          .then( obj => obj.json() )
          .then( data => {
            this.state.categories = [];
            data.forEach( el => {
                commit('addCategory', el);
            });
            resolve(data);
        });
      });
    },

    addCategory({ commit }, obj){
      fetch('http://localhost:10000/api/categories', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
      }).then( res =>{ 
        res.json() 
        commit;
      });
    },

    addTag({ commit }, obj){
      return new Promise( (resolve) => {
        fetch('http://localhost:10000/api/tags', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json',
                   'Authorization': `Bearer ${localStorage.token}` },
        body: JSON.stringify(obj)
      })
      .then( res => res.json())
      .then((data) => {
        resolve(data);
        commit;
      });
      })
    },

    addNewsTag({ commit }, obj){
      fetch('http://localhost:10000/api/newstag', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
      }).then( res =>{ 
        res.json() 
        commit;
      });
    },

    deleteCategory({ commit }, id){
      fetch(`http://localhost:10000/api/categories/${id}`, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' }
      }).then( res =>{ 
        res.json() 
        commit;
      });
    },

    updateCategory({commit}, obj){
      fetch(`http://localhost:10000/api/categories`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(obj)
          }).then( res =>{ 
            res.json() 
            commit;
          });
    },

    updateUser({commit}, obj){
      fetch(`http://localhost:10000/api/users`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(obj)
          }).then( res =>{ 
            res; 
            commit;
          });
    },

    deleteNews({ commit }, id){
      fetch(`http://localhost:10000/api/news/${id}`, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' }
      }).then( res =>{ 
        res.json() 
        commit;
      });
    },

    updateNews({commit}, obj){
      fetch(`http://localhost:10000/api/news`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(obj)
          }).then( res =>{ 
            res;
            commit;
          });
    },

    deleteNewsIdInNewsTags({ commit }, id){
      fetch(`http://localhost:10000/api/newstag/${id}`, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' }
      }).then( res =>{ 
        res;
        commit;
      });
    },

    login({ commit }, obj) {
      fetch('http://localhost:10000/api/users/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
    }).then( res => res.json() )
      .then( tkn => {
        console.log(tkn);
        if (tkn.msg) {
          alert(tkn.msg);
        } else {
          commit('setToken', tkn.token)
          commit('setUserId', tkn.id)
          commit('setRole', tkn.role)
        }
      });
    }

  }
})

