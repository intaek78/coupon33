<template>
  <div id="coupon">
    <h1>탈퇴장부</h1>
    탈퇴장부 조회(사용자 : {{user}})
    <button v-on:click="getCancelData">조회</button>
    <br/>
    <br/>
    <table border=10px align="center">
      <div>
        <tr>
          <th width=200>회원번호</th>
          <th width=80>멤버ID</th>
          <th width=200>이름</th>
          <th width=200>탈퇴일시</th>
          <th width=500>상태</th>
        </tr>
      </div>
      <tr v-for="(data, index) in cancellationList" :key="index">
        <div>
          <tr v-for="(data2, index2) in data.cancellations" :key="index2">
            <td  width=200>{{data2.memNo}}</td>
            <td  width=80>{{data2.memId}}</td>
            <td  width=200>{{data2.memNm}}</td>
            <td  width=200>{{data2.withdrawDate}}</td>
            <td  width=500>{{data2.status}}</td>
          </tr>
        </div>
      </tr>
    </table>
    <br/>
    <button v-on:click="goMember">멤버관리 화면으로 이동</button>
    <h5>=====================</h5>
    <p>{{cancellationList}}</p>
  </div>
</template>

<script>
export default {
  name: 'cancellation',
  data () {
    return {
      cancellationList: [],
      body: '',
      user: this.$route.params.memId
    }
  },
  methods: {
    getCancelData () {
      this.body = {
        memId: this.$route.params.memId
      }
      this.$axios.get('http://localhost:8088/cancellations', {
        params: this.body
      }
      ).then(res => {
        this.cancellationList = res.data
      }).catch(err => {
        alert('연결실패')
        alert(err)
        console.log(err)
      })
    },
    goMember () {
      this.$router.push({name: 'member',
        params:
          { memId: this.$route.params.memId,
            memNo: this.$route.params.memNo
          }})
    }
  }
}
</script>
