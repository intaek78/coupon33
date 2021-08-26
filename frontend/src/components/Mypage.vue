<template>

<v-data-table
    :headers="headers"
    :items="mypage"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'Mypage',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "id", value: "id" },
            { text: "memId", value: "memId" },
            { text: "regDate", value: "regDate" },
            { text: "withdrawDate", value: "withdrawDate" },
            { text: "status", value: "status" },
            { text: "startDate", value: "startDate" },
            { text: "endDate", value: "endDate" },
            { text: "phoneNum", value: "phoneNum" },
            { text: "email", value: "email" },
        ],
        mypage : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/mypages')

      this.mypage = temp.data._embedded.mypages;

    },
    methods: {
    }
  }
</script>

