<template>
  <div id="coupon">
    <h1>마이페이지</h1>
    마이페이지 리스트(사용자 : {{user}})
    <button v-on:click="getMypageData">조회</button>
    <br/>
    <br/>
    <table border=10px align="center">
      <div>
        <tr>
          <th width=80>멤버ID</th>
          <th width=80>회원번호</th>
          <th width=80>이름</th>
          <th width=200>쿠폰ID</th>
          <th width=150>유효기간 시작일</th>
          <th width=150>유효기간 종료일</th>
          <th width=400>상태</th>
          <th width=100>가입일</th>
          <th width=100>탈퇴일</th>
        </tr>
      </div>
      <tr v-for="(data, index) in mypageList" :key="index">
        <div>
          <tr v-for="(data2, index2) in data.mypages" :key="index2">
            <td  width=80>{{data2.memId}}</td>
            <td  width=80>{{data2.memNo}}</td>
            <td  width=80>{{data2.memNm}}</td>
            <td  width=200>{{data2.couponId}}</td>
            <td  width=150>{{data2.startDate}}</td>
            <td  width=150>{{data2.endDate}}</td>
            <td  width=400>{{data2.status}}</td>
            <td  width=100>{{data2.regDate}}</td>
            <td  width=100>{{data2.withdrawDate}}</td>
          </tr>
        </div>
      </tr>
    </table>
    <br/>
    <button v-on:click="goMember">멤버관리 화면으로 이동</button>
    <h5>=====================</h5>
    <p>{{couponList}}</p>
  </div>
</template>

<script>
export default {
  name: 'mypage',
  data () {
    return {
      mypageList: [],
      body: '',
      user: this.$route.params.memId
    }
  },
  methods: {
    getMypageData () {
      this.body = {
        memId: this.$route.params.memId
      }
      this.$axios.get('http://localhost:8088/mypages', {
        params: this.body
      }
      ).then(res => {
        alert('연결성공')
        this.mypageList = res.data
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
