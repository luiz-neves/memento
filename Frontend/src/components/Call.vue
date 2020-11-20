<template>
  <v-row>
    <v-col cols="6">
      <v-container>
        <div class="title">Tributos</div>
        <v-carousel
          height="400"
          hide-delimiter-background
          show-arrows-on-hover
          v-model="indexTribute"
          cycle
        >
          <v-carousel-item
            v-for="(itemTribute, i) in tributes"
            :key="i"
          >
            <v-sheet
              color="indigo"
              height="100%"
            >
              <youtube v-if="hasYoutube(itemTribute.attachmentUrl)" :video-id="getIdFromUrl(itemTribute.attachmentUrl)" player-width="100%" player-height="300px"></youtube>
              <v-img v-else :src="itemTribute.attachmentUrl" aspect-ratio="1.777"></v-img>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>
        <div class="title mb-2 text-center">{{ tributeName }}</div>
        <div class="mb-2 text-center">{{ tributeDescription }}</div>
      </v-container>
    </v-col>
    <v-col cols="6">
      <v-container>
        <div class="title">Participantes</div>
        <session></session>
      </v-container>
    </v-col>
  </v-row>
</template>

<script>
  import axios from '../axios'
  import Session from './Session'  
  import { not, isEmpty, match } from 'ramda'

  export default {
    name: 'Call',
    components: { Session },
    data: () => ({
      tributes: [],
      indexTribute: '',
    }),
    created() {
      this.fetchTribute()
    },
    computed: {
      tributeName() {
        return this.tributes[this.indexTribute] ? this.tributes[this.indexTribute].name : ''
      },
      tributeDescription() {
        return this.tributes[this.indexTribute] ? this.tributes[this.indexTribute].description : ''
      },
    },
    methods: {
      hasYoutube(url = '') {
        return not(isEmpty(match('youtube', url)))
      },
      getIdFromUrl(url = '') {
        return this.$youtube.getIdFromURL(url)
      },
      async fetchTribute() {
        const { data } = await axios.get('/tribute?personId=1')
        this.tributes = data
      },
    },
  }
</script>
