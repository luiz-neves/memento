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
          <v-col cols="4" v-for="itemTribute in tributes" :key="itemTribute.id">
            <div>
              <v-img :src="itemTribute.attachmentUrl" aspect-ratio="1.7"></v-img>
              <div class="title mb-2 text-center">{{ itemTribute.name }}</div>
            </div>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-card>
          <v-list header class="text-center">
            <v-header class="title mb-1">
              Lista de contatos
              <v-row justify="center">
                  <v-dialog
                    v-model="dialogContact"
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
                        Adicionar telefone
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
                              md="6"
                            >
                              <v-text-field
                                label="Nome*"
                                required
                                v-model="dialogContactValues.name"
                              ></v-text-field>
                            </v-col>
                            <v-col
                              cols="12"
                              sm="6"
                              md="6"
                            >
                              <v-text-field
                                label="Número do celular"
                                required
                                v-model="dialogContactValues.phone"
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
                          @click="dialogContact = false"
                        >
                          Fechar
                        </v-btn>
                        <v-btn
                          color="blue darken-1"
                          text
                          @click="saveContact"
                        >
                          Salvar
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-row>
              </v-header>
            <v-list-item
              v-for="contact in contacts"
              :key="contact.id"
            >
              <v-list-item-content>
                <v-list-item-title v-text="contact.name"></v-list-item-title>
              </v-list-item-content>
              <v-list-item-content>
                <v-list-item-title v-text="contact.phone"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
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
      // tributes: [
      //   { id: 1, attachmentUrl: 'https://picsum.photos/510/301?random', name: 'Nome de um parente' },
      //   { id: 2, attachmentUrl: 'https://picsum.photos/510/302?random', name: 'Nome de um parente' },
      //   { id: 3, attachmentUrl: 'https://picsum.photos/510/303?random', name: 'Nome de um parente' },
      //   { id: 4, attachmentUrl: 'https://picsum.photos/510/304?random', name: 'Nome de um parente' },
      //   { id: 5, attachmentUrl: 'https://picsum.photos/510/305?random', name: 'Nome de um parente' },
      //   { id: 6, attachmentUrl: 'https://picsum.photos/510/306?random', name: 'Nome de um parente' },
      // ],
      tributes: [],
      tribute: [],
      contacts: [],
      dialog: false,
      dialogContact: false,
      dialogContactValues: {},
      dialogValues: {},
      recent: [
        {
          active: true,
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Jason Oner',
        },
        {
          active: true,
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Mike Carlson',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          title: 'Cindy Baker',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
          title: 'Ali Connors',
        },
      ],
      contact: [
        {
          name: 'Travis Howard',
          phone: '11972175764',
        },
        {
          name: 'Hamilton',
          phone: '11972175764',
        },
        {
          name: 'Steve Howard',
          phone: '11972175764',
        },
      ],
    }),
    created() {
      this.fetchPerson()
      this.fetchTribute()
      this.fetchContact()
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
        console.log(data)
        this.tributes = data
      },
      async fetchContact() {
        const { data } = await axios.get('/findAllPhones')
        this.contacts = data
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
      },
      async saveContact() {
        await axios.post('/insertPhone', this.dialogContactValues)

        this.dialogContact = false
        this.fetchContact()
      },
    },
  }
</script>
