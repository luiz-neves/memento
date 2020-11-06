<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
      right
    >
      <v-list dense>
        <v-list-item link @click="goToPath('/')">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>

          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item link @click="goToPath('contact')">
          <v-list-item-action>
            <v-icon>mdi-email</v-icon>
          </v-list-item-action>
          <v-list-item-title>
            <v-list-item-content>
              Contatos
            </v-list-item-content>
          </v-list-item-title>
        </v-list-item>
        <v-list-item link @click="goToPath('agenda')">
          <v-list-item-action>
            <v-icon>mdi-calendar</v-icon>
          </v-list-item-action>
          <v-list-item-title>
            <v-list-item-content>
              Agenda
            </v-list-item-content>
          </v-list-item-title>
        </v-list-item>
        <v-list-item link @click="goToPath('call')">
          <v-list-item-action>
            <v-icon>mdi-phone</v-icon>
          </v-list-item-action>
          <v-list-item-title>
            <v-list-item-content>
              Video chamada
            </v-list-item-content>
          </v-list-item-title>
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
    <v-main>
      <router-view/>
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
export default {
  name: 'App',
  data: () => ({
    drawer: null,
  }),
  methods: {
    goToPath(path) {
      this.$router.push({ path })
    },
  }
}
</script>
