<template>
  <v-card
    class="mx-auto"
  >
    <div class="title mb-1 text-center pt-5">Agenda</div>
    <v-card
      dark
      flat
    >
      <v-row justify="center">
        <v-dialog
          v-model="dialog"
          persistent
          max-width="600px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              id="add-event-button"
              absolute
              bottom
              color="pink"
              right
              fab
              v-bind="attrs"
              v-on="on"
            >
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">Evento</span>
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
                      label="Nome do evento*"
                      required
                      v-model="dialogValues.nameEvent"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="6"
                  >
                    <v-text-field
                      label="Descrição*"
                      required
                      v-model="dialogValues.description"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="6"
                  >
                    <v-text-field
                      label="Orador"
                      required
                      v-model="dialogValues.speaker"
                    ></v-text-field>
                  </v-col>
                  <v-col>
                    <v-menu
                      ref="menu"
                      v-model="menu"
                      :close-on-content-click="false"
                      transition="scale-transition"
                      offset-y
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="dialogValues.date"
                          label="Data*"
                          prepend-icon="mdi-calendar"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        ref="picker"
                        v-model="dialogValues.date"
                        :min="new Date().toJSON()"
                        @change="save"
                      ></v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col
                    cols="11"
                    sm="5"
                  >
                    <v-menu
                      ref="menu"
                      v-model="menu2"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      :return-value.sync="dialogValues.time"
                      transition="scale-transition"
                      offset-y
                      max-width="290px"
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="dialogValues.time"
                          label="Horário*"
                          prepend-icon="mdi-clock-time-four-outline"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-time-picker
                        v-if="menu2"
                        v-model="dialogValues.time"
                        full-width
                        @click:minute="$refs.menu.save(dialogValues.time)"
                      ></v-time-picker>
                    </v-menu>
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
                @click="saveAgenda"
              >
                Salvar
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </v-card>
    <v-card-text class="py-5">
      <v-timeline
        align-top
        dense
      >
        <v-timeline-item
          color="teal lighten-3"
          small
          v-for="schedule in schedules" 
          :key="schedule.id"
        >
          <v-row class="pt-1">
            <v-col cols="3">
              <strong>{{ schedule.date.dayOfMonth }}/{{ schedule.date.monthValue }}/{{ schedule.date.year }}</strong>
            </v-col>
            <v-col>
              <strong>{{ schedule.nameEvent }}</strong>
              <div class="caption">
                {{ schedule.speaker }} | {{ schedule.description }}
              </div>
            </v-col>
          </v-row>
        </v-timeline-item>
      </v-timeline>
    </v-card-text>
  </v-card>
</template>

<script>
  import axios from '../axios'

  export default {
    name: 'Agenda',
    data: () => ({
      schedules: [],
      dialog: false,
      dialogValues: {},
      menu: false,
      menu2: false,
      date: null,
    }),
    watch: {
      menu (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
    },
    created() {
      this.fetchAgenda()
    },
    methods: {
      save (date) {
        this.$refs.menu.save(date)
      },
      async fetchAgenda() {
        const { data } = await axios.get('/schedule?personId=1')
        this.schedules = data
      },
      async saveAgenda() {
        const { nameEvent, date, description, speaker, time } = this.dialogValues
        
        await axios.post('/schedule', {
          personId: 1, 
          nameEvent,
          description,
          speaker,
          date: `${date} ${time}:00`,
        })

        this.dialog = false
        this.fetchAgenda()
      },
    },
  }
</script>
<style scoped>
#add-event-button {
  bottom: -80px
}
</style>
