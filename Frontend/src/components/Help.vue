<template>
  <v-container fluid>
    <v-row justify="space-around">
    </v-row>
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
      >
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
        </v-card>
      </v-dialog>
    </v-row>
    <v-row justify="space-around">
      <v-col cols="4" v-for="itemTribute in tributes" :key="itemTribute.id">
        <v-card>
          <v-card-text>
            <v-container>
              <youtube v-if="hasYoutube(itemTribute.attachmentUrl)" :video-id="getIdFromUrl(itemTribute.attachmentUrl)" player-width="100%" player-height="240"></youtube>
              <div class="title mb-2 text-center">{{ itemTribute.name }}</div>
              <div class="mb-2 text-center">{{ itemTribute.description }}</div>
              <a v-if="!hasYoutube(itemTribute.attachmentUrl)" :href="itemTribute.attachmentUrl" aspect-ratio="1.7">{{ itemTribute.attachmentUrl }}</a>
            </v-container>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import { not, isEmpty, match } from 'ramda'

  export default {
    name: 'Memorial',
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
    created() {
      this.fetchTribute()
    },
    methods: {
      hasYoutube(url = '') {
        return not(isEmpty(match('youtube', url)))
      },
      getIdFromUrl(url = '') {
        return this.$youtube.getIdFromURL(url)
      },
      async fetchTribute() {
        this.tributes = [
          {
            attachmentUrl: "https://www.youtube.com/watch?v=u8dbPGZbcig&ab_channel=sementecrista",
            name: "Morrer é voltar para casa",
            description: "Mensagem do CD Momento Espírita para o Dia de Finados"
          },
          {
            attachmentUrl: "https://www.youtube.com/watch?v=ZPaU9bbPTy8",
            name: "O que é a morte? Para onde iremos?",
            description: "Por Monja Coen"
          },
          {
            attachmentUrl: "https://www.youtube.com/watch?v=3Z9kXMxqZlY&ab_channel=CanaldoCortella",
            name: "O luto",
            description: "Por Mario Sergio Cortella"
          },
          {
            attachmentUrl: "https://www.youtube.com/watch?v=yjEVFwL35z4&ab_channel=SaberFilos%C3%B3fico",
            name: "A Morte que dá sentido às coisas",
            description: "Por Leandro Karnal"
          },
          {
            attachmentUrl: "https://www.youtube.com/watch?v=Ek2LmQ5d6Jo&ab_channel=CanaldoCortella",
            name: "O Tempo E A Vida",
            description: "Por Mario Sergio Cortella"
          },
          {
            attachmentUrl: "https://www.psicologosberrini.com.br/psicanalise-e-psicanalista/luto-como-lidar/",
            name: "Como lidar com o luto",
            description: "Por Thaiana F Brotto"
          },
          {
            attachmentUrl: "https://www.vittude.com/blog/luto-aprendendo-a-lidar-com-a-morte/",
            name: "Luto: aprendendo a lidar com a morte e ausência",
            description: "Por Tatiana Pimenta"
          },
          {
            attachmentUrl: "https://www.minhavida.com.br/bem-estar/materias/18111-como-lidar-com-o-luto-pela-morte-de-uma-pessoa-proxima",
            name: "Como lidar com o luto pela morte de uma pessoa próxima?",
            description: "Por Raquel Baldo"
          },
          {
            attachmentUrl: "https://www.helpguide.org/articles/grief/coping-with-grief-and-loss.htm",
            name: "Coping with Grief and Loss",
            description: "Por Melinda Smith, Lawrence Robinson e Jeanne Segal"
          },
          {
            attachmentUrl: "https://www.dignitymemorial.com/fr-ca/support-friends-and-family/grief-library/when-a-parent-dies-dealing-with-the-loss-of-your-mother-or-father",
            name: "Quando um de seus pais morre: gerenciando o luto de sua mãe ou pai",
            description: "Por David Kessler"
          },
          {
            attachmentUrl: "https://www.dignitymemorial.com/fr-ca/support-friends-and-family/grief-library/12-insights-into-grieving-after-the-death-of-your-loved-one",
            name: "Doze dicas para lidar com o luto após a morte de um ente querido",
            description: "Por Therese A. Rando"
          },
        ]
      },
    },
  }
</script>
