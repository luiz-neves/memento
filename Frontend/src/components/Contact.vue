<template>
  <v-container>
    <v-card>
      <v-list header class="text-center">
        <div class="title mb-1">Lista de contatos</div>
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
</template>

<script>
  import axios from '../axios'

  export default {
    name: 'Contact',
    data: () => ({
      contacts: [],
      dialogContact: false,
      dialogContactValues: {},
    }),
    created() {
      this.fetchContact()
    },
    methods: {
      async fetchContact() {
        const { data } = await axios.get('/findAllPhones')
        this.contacts = data
      },
      async saveContact() {
        await axios.post('/insertPhone', this.dialogContactValues)

        this.dialogContact = false
        this.fetchContact()
      },
    },
  }
</script>
