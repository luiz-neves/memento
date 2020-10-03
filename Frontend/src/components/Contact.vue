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
        <v-card>
          <v-list subheader>
            <v-subheader>Recent chat</v-subheader>

            <v-list-item
              v-for="chat in recent"
              :key="chat.title"
            >
              <v-list-item-avatar>
                <v-img
                  :alt="`${chat.title} avatar`"
                  :src="chat.avatar"
                ></v-img>
              </v-list-item-avatar>

              <v-list-item-content>
                <v-list-item-title v-text="chat.title"></v-list-item-title>
              </v-list-item-content>

              <v-list-item-icon>
                <v-icon :color="chat.active ? 'deep-purple accent-4' : 'grey'">
                  mdi-message-outline
                </v-icon>
              </v-list-item-icon>
            </v-list-item>
          </v-list>
        </v-card>
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
    name: 'Contact',
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
      async fetchPhones() {
        // const { data } = await axios.get('/insertPhone')
        const { data } = await axios.post('/findAllPhones')
        console.log(data)
        this.phones = data
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
