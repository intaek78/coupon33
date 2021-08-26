<template>
  <div id="coupon">
    <h1>멤버관리</h1>
    멤버 리스트(사용자 : {{user}})
    <button v-on:click="getMember">조회</button>
    <input type="hidden" id="inputmemNo">
    <br/>
    <br/>
    <table border=10px align="center">
      <div>
        <tr>
          <th width=80>회원번호</th>
          <th width=80>멤버ID</th>
          <th width=80>이름</th>
          <th width=100>쿠폰발행여부</th>
          <th width=500>상태</th>
        </tr>
      </div>
      <tr v-for="(data, index) in memberList" :key="index">
        <div>
          <tr v-for="(data2, index2) in data.members" :key="index2">
            <td  width=80>{{data2.memNo}}</td>
            <td  width=80>{{data2.memId}}</td>
            <td  width=80>{{data2.memNm}}</td>
            <td  width=100>{{data2.couponYN}}</td>
            <td  width=500>{{data2.status}}</td>
          </tr>
        </div>
      </tr>
    </table>
    <br/>
    <button v-on:click="goCoupon" align="left">쿠폰관리 화면으로 이동</button>
    <button v-on:click="goCancellation">탈퇴장부 화면으로 이동</button>
    <button v-on:click="goMypage">마이페이지로 이동</button>
    <button v-on:click="cancelMember">회원탈퇴</button>

    <h5>=====================</h5>
    <p>{{memberList}}</p>
  </div>
</template>

<script>
export default {
  name: 'member',
  data () {
    return {
      memberList: [],
      memId: '',
      memNo: this.$route.params.memNo,
      user: this.$route.params.memId
    }
  },
  methods: {
    getMember () {
      this.body = {
        memId: this.$route.params.memId
      }
      this.$axios.get('http://localhost:8088/members', {
        params: this.body
      }
      ).then(res => {
        this.memberList = res.data
      }).catch(err => {
        alert('연결실패')
        alert(err)
        console.log(err)
      })
    },
    goCoupon () {
      this.$router.push({name: 'coupon', params: { memId: this.$route.params.memId, memNo: this.$route.params.memNo }})
    },
    goCancellation () {
      this.$router.push({name: 'cancellation', params: { memId: this.$route.params.memId, memNo: this.$route.params.memNo }})
    },
    goMypage () {
      this.$router.push({name: 'mypage', params: { memId: this.$route.params.memId, memNo: this.$route.params.memNo }})
    },
    cancelMember () {
      alert('withdraw!!!!')
      this.$axios.delete('http://localhost:8088/members/' + this.memNo
      ).then(res => {
        alert('회원 탈퇴 완료!!')
      }).catch(err => {
        alert(err)
      })
    }
  }
}
</script>
