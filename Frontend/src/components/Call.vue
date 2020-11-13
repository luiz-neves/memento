<template>
  <v-row>
    <v-col cols="4">
      Memorial
    </v-col>
    <v-col cols="8">
      <session></session>
    </v-col>
  </v-row>
</template>

<script>
  import Session from './Session'  

  export default {
    name: 'Call',
    components: { Session },
    data: () => ({}),
    methods: {},
  }
</script>
