<template>
  <v-container fluid>
    <v-row justify="space-around">
      <v-col cols="6">
        <v-img src="https://observatoriodocinema.uol.com.br/wp-content/uploads/2020/08/chadwick-boseman-1.jpg" aspect-ratio="1.7" max-height="300px"></v-img>
      </v-col>
      <v-col cols="6">
        <div class="title mb-1">{{ name }}</div>
        <div class="subheading">Companheiro, irmão e amigo</div>
        <div class="subheading">
          <v-icon>mdi-star</v-icon>
          {{ birthDate }}
        </div>
        <div class="subheading">
          <v-icon>mdi-shield-cross</v-icon>
          {{ deathDate }}
        </div>
        <div class="text" justify-center>
          {{ description }}
        </div>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="primary"
            dark
            v-bind="attrs"
            v-on="on"
          >
            Adicionar tributo
          </v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">Tributo</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    label="Nome*"
                    required
                    v-model="dialogValues.name"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    label="URL da imagem"
                    required
                    v-model="dialogValues.attachmentUrl"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    label="Descrição"
                    required
                    v-model="dialogValues.description"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
            <small>*indica que é obrigatório</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue darken-1"
              text
              @click="dialog = false"
            >
              Fechar
            </v-btn>
            <v-btn
              color="blue darken-1"
              text
              @click="saveTribute"
            >
              Salvar
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-container>
</template>

<script>
  import axios from '../axios'
  var apiKey = '45828062'
  var sessionId = '2_MX40NTgyODA2Mn5-MTYwNDY5MDQyMDE4N35LQW10a0lCdHJhYjVBQ3QrR00vU3hkVUp-UH4'
  var token = 'T1==cGFydG5lcl9pZD00NTgyODA2MiZzaWc9MGI5YzJjZjA4NDI3MjNjZWQ2OWEwM2ZhNjY5NjFjZWNmYjdmNGFiZDpzZXNzaW9uX2lkPTJfTVg0ME5UZ3lPREEyTW41LU1UWXdORFk1TURReU1ERTROMzVMUVcxMGEwbENkSEpoWWpWQlEzUXJSMDB2VTNoa1ZVcC1VSDQmY3JlYXRlX3RpbWU9MTYwNDY5MjU1MCZub25jZT0wLjIyNjY4NDcxOTY0NTYzNjk3JnJvbGU9cHVibGlzaGVyJmV4cGlyZV90aW1lPTE2MDQ3Nzg5NTA='
  
  export default {
    name: 'Call',
    data: () => ({
      id: null,
      name: null,
      birthDate: null,
      deathDate: null,
      description: null,
      tributes: [],
      tribute: [],
      dialog: false,
      dialogValues: {},
    }),
    mounted() {
      // let externalScript = document.createElement('script')
      // externalScript.setAttribute('src', 'https://static.opentok.com/v2/js/opentok.js')
      // externalScript.setAttribute('id', 'OT')
      // document.head.appendChild(externalScript)

      const { OT } = window
      console.log(OT)
      var session = OT.initSession(apiKey, sessionId)

      // create publisher
      let publisher = OT.initPublisher()
      session.connect(token, function() {
        session.publish(publisher)

        console.log(session)
        console.log('session connected')
      })
      
      session.on('streamCreated', function(event) {
        session.subscribe(event.stream);
      })
    },
    created() {
      console.log('asdasdasd')
      this.fetchPerson()
      this.fetchTribute()
    },
    methods: {
      async fetchPerson() {
        const { data } = await axios.get('/person/1')

        this.id = data.id
        this.name = data.name
        this.birthDate = data.birthDate
        this.deathDate = data.deathDate
        this.description = data.description
      },
      async fetchTribute() {
        const { data } = await axios.get('/tribute?personId=1')
        this.tributes = data
      },
      async saveTribute() {
        await axios.post('/tribute', {
          ...this.dialogValues,
          id: 1,
          personId: 1,
        })

        this.dialog = false
        this.fetchTribute()
      },
    },
  }
</script>
