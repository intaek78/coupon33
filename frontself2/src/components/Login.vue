<template>
  <div>
    <h1>회원가입</h1>
    ID   : <input type="text" v-model="memid"><br/>
    pw   : <input type="text" v-model="pw"><br/>
    이름 : <input type="text" v-model="memNm"><br/>
    <button v-on:click="regist">가입</button>
  </div>

</template>

<script>
export default {
  name: 'Main',
  data () {
    return {
      memid: ''
    }
  },
  methods: {
    regist () {
      this.$axios.post('http://localhost:8088/members',
        { memId: this.memid,
          memNm: this.memNm }
      ).then(res => {
        alert('회원가입이 완료되었습니다.')
        this.memReqres = res.data
        this.$router.push({name: 'member', params: { memId: this.memid, memNo: res.data.memNo }})
      }).catch(err => {
        alert('errrr1234444')
        alert(err)
        console.log(err)
      })
    }
  }
}
</script>
