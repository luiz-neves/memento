<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
      right
    >
      <v-list dense>
        <v-list-item link>
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>

          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item link>
          <v-list-item-action>
            <v-icon>mdi-email</v-icon>
          </v-list-item-action>

          <v-list-item-content>
            <v-list-item-title>Contatos</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item link>
          <v-list-item-action>
            <v-icon>mdi-plus-circle</v-icon>
          </v-list-item-action>

          <v-list-item-content>
            <v-list-item-title>Adicionar tributo</v-list-item-title>
          </v-list-item-content>

        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      color="cyan"
      dark
    >
      <v-spacer></v-spacer>
      <v-toolbar-title>Memento</v-toolbar-title>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    </v-app-bar>
    <v-main class="pa-0">
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
          <v-row justify="space-around">
            <v-col cols="4">
              <div v-if="tribute.attachmentUrl">
                <v-img :src="tribute.attachmentUrl" aspect-ratio="1.7"></v-img>
                <div class="title mb-2 text-center">{{ tribute.description }}</div>
              </div>
            </v-col>
            <v-col cols="4">
              <div>
                <v-img src="https://portalgiro.com/wp-content/uploads/2020/08/chadwick-boseman.jpg" aspect-ratio="1.7"></v-img>
                <div class="title mb-2 text-center">Chadwick fazendo o que mais gostava</div>
              </div>
            </v-col>
            <v-col cols="4">
              <div>
                <v-img src="https://jpimg.com.br/uploads/2020/08/chadwick-boseman.jpg" aspect-ratio="1.7"></v-img>
                <div class="title mb-2 text-center">Seu talento era reconhecido por todos, recebendo vários prêmios</div>
              </div>
            </v-col>
          </v-row>
        </v-container>
    </v-main>
    <v-footer
      color="cyan"
      app
    >
      <v-spacer></v-spacer>
      <span class="white--text">Memento &copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
  </v-app>
</template>

<script>
  import axios from '../axios'

  export default {
    name: 'LayoutsDemosBaselineFlipped',
    props: {
      source: String,
    },
    data: () => ({
      drawer: null,
      id: null,
      name: null,
      birthDate: null,
      deathDate: null,
      description: null,
      tributes: [
        { attachmentUrl: 'https://picsum.photos/510/301?random', name: 'Nome de um parente' },
        { attachmentUrl: 'https://picsum.photos/510/302?random', name: 'Nome de um parente' },
        { attachmentUrl: 'https://picsum.photos/510/303?random', name: 'Nome de um parente' },
        { attachmentUrl: 'https://picsum.photos/510/304?random', name: 'Nome de um parente' },
        { attachmentUrl: 'https://picsum.photos/510/305?random', name: 'Nome de um parente' },
        { attachmentUrl: 'https://picsum.photos/510/306?random', name: 'Nome de um parente' },
      ],
      tribute: [],
      dialog: false,
      dialogValues: {},
    }),
    created() {
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
        this.tribute = data
      },
      async saveTribute() {
        console.log(this.dialogValues)
        await axios.post('/tribute', {
          ...this.dialogValues,
          id: 1,
          personId: 1,
        })

        this.dialog = false
        this.fetchTribute()
      }
    },
  }
</script>
