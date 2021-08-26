<template>
  <div id="coupon">
    <h1>쿠폰관리</h1>
    쿠폰을 조회해보세요(사용자 : {{user}})
    <button v-on:click="getData">조회</button>
    <br/>
    <br/>
    <table border=10px align="center">
      <div>
        <tr>
          <th width=80>멤버ID</th>
          <th width=200>쿠폰ID</th>
          <th width=200>유효기간 시작일</th>
          <th width=200>유효기간 종료일</th>
          <th width=500>상태</th>
        </tr>
      </div>
      <tr v-for="(data, index) in couponList" :key="index">
        <div>
          <tr v-for="(data2, index2) in data.coupons" :key="index2">
            <td  width=80>{{data2.memId}}</td>
            <td  width=200>{{data2.couponId}}</td>
            <td  width=200>{{data2.startDate}}</td>
            <td  width=200>{{data2.endDate}}</td>
            <td  width=500>{{data2.status}}</td>
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
  name: 'coupon',
  data () {
    return {
      couponList: [],
      body: '',
      user: this.$route.params.memId
    }
  },
  methods: {
    getData () {
      this.body = {
        memId: this.$route.params.memId
      }
      this.$axios.get('http://localhost:8088/coupons', {
        params: this.body
      }
      ).then(res => {
        this.couponList = res.data
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
