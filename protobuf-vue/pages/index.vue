<template>
  <div class="container">
    <div>
      <logo />
      <h1 class="title">
        protobuf-vue
      </h1>
      <h2 class="subtitle">
        My gnarly Nuxt.js project
      </h2>
      <div class="links">
        <a href="#" @click="getPb" class="button--grey">
          Get Protobuf
        </a>
        <a href="#" @click="getJson" class="button--grey">
          Get Json
        </a>
        <a href="#" @click="getJsonGzip" class="button--grey">
          Get Json Gzip
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import Logo from '~/components/Logo.vue'
import axios from 'axios'
let protos = require('../assets/proto/wind_pb.js');

export default {
  components: {
    Logo
  },
  methods: {
    // async asyncData ({ params }) {
    //   const { data } = await axios.get(`https://my-api/posts/${params.id}`)
    //   return { title: data.title }
    // }
    getPb: function () {
      axios.get('http://localhost:8080/pb',{
        responseType: 'arraybuffer'
      }).then(res => {
        let wd = protos.Wind.deserializeBinary(new Buffer(res.data, 'binary').toString('base64'))
        console.log('---wd.length----', wd.getGpsList().length)
        console.log('---wd----', wd.getGpsList()[0].getDimension())
      }).catch((error) => {
        console.log('---error----', error)
      })
    },
    getJson: function () {
      axios({
        method:'get',
        url: 'http://localhost:8080/json',
      }).then(res => {
        console.log('---wd----', res)
      }).catch((error) => {
        console.log(error)
      })
    },
    getJsonGzip: function () {
      axios({
        method:'get',
        url: 'http://localhost:8080/json-gzip',
        headers: {
          'Content-Type': 'application/octet-stream'
        }
      }).then(res => {
        console.log('---wd----', res)
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>

<style>
.container {
  margin: 0 auto;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.title {
  font-family: 'Quicksand', 'Source Sans Pro', -apple-system, BlinkMacSystemFont,
    'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}

.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}

.links {
  padding-top: 15px;
}
</style>
