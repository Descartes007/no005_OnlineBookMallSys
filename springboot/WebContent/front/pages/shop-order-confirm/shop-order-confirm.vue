<template>
	<view class="content">
		<form>
			<view v-if="seat!=1" @tap="onAddressTap" class="cu-form-group">
				<view class="title">地址</view>
				<view>
					<view>
						{{address.name}} {{address.phone}}
					</view>
					<view>
						{{address.address}}
					</view>
				</view>
			</view>
			<view class="cu-form-group">
				<view class="title">购买清单</view>
			</view>
			<view v-for="(item,index) in orderGoods " v-bind:key="index" class="cu-form-group">
				<image class="avator" :src="item.picture" mode=""></image>
				<view class="title">
					<view>{{item.goodname}}</view>
					<view v-if="seat!=1">
						x{{item.buynumber}}
						<text v-if="type==1" style="margin-left: 30upx;color: red;">￥{{item.price}}</text>
						<text v-if="type==2" style="margin-left: 30upx;color: red;">{{item.price}}积分</text>
					</view>
					<view v-else>
						{{item.address}}号
						<text v-if="type==1" style="margin-left: 30upx;color: red;">￥{{item.price}}</text>
						<text v-if="type==2" style="margin-left: 30upx;color: red;">{{item.price}}积分</text>
					</view>
				</view>
			</view>
			<view @tap="onAddressTap" class="cu-form-group">
				<view v-if="type==1" class="title">总价</view>
				<view v-if="type==2" class="title">总积分</view>
				<view>
					<text v-if="type==1">￥{{total}}</text>
					<text v-if="type==2">{{total}}积分</text>
				</view>
			</view>
		</form>
		<view class="padding" style="text-align: center;">
			<button  v-if="type==1" @tap="onSubmitTap()" class="bg-red lg">确认支付</button>
			<button  v-if="type==2" @tap="onSubmitTap()" class="bg-red lg">确认兑换</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: {},
				orderGoods: {},
				address: {},
				total: 0,
				type: 1,
				seat: 0
			}
		},
		async onLoad(options) {
			this.type = options.type
			this.seat = options.seat
			// 获取订单信息，座位信息
			this.orderGoods = uni.getStorageSync('orderGoods');
			if (this.seat != 1) {
				for (let i = 0; i < this.orderGoods.length; i++) {
					if(this.total==0) {
						this.total = parseFloat(this.orderGoods[i].price) * this.orderGoods[i].buynumber
					} else {
						this.total = parseFloat(this.total) + parseFloat(this.orderGoods[i].price) * this.orderGoods[i].buynumber	
					}
				}
			}else{
				this.total = parseFloat(this.orderGoods[0].total)
				this.address = this.orderGoods[0].address
			}
			this.total= this.total.toFixed(2)
		},
		async onShow() {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table)
			this.user = res.data
			if(this.seat!=1){
				let address = uni.getStorageSync('address')
				if(address&&this.user.id==this.address.userid){
					this.address = uni.getStorageSync('address');
				}else{
					res = await this.$api.defaultAddress(this.user.id);
					this.address = res.data
				}
			}
			
		},
		methods: {
			async onSubmitTap() {
				let _this = this;
				let table = uni.getStorageSync("nowTable");
				uni.showModal({
					title: '提示',
					content: '是否确认支付',
					success: async function(res) {
						if (res.confirm) {
							// 订单类型
							let type = _this.type;
							let isSuccess = true ;
                            if(type==1){
								if (_this.user.money - _this.total < 0) {
									_this.$utils.msg('余额不足，请先充值');
									isSuccess = false
									return
								}
							}else if(type==2){
								if (_this.user.jifen - _this.total < 0) {
									_this.$utils.msg('积分不足，兑换商品失败');
									isSuccess = false
									return
								}
							}
							for (let i = 0; i < _this.orderGoods.length; i++) {
								// 如果有限次模块 ，则减少商品的库存信息，不够则提示库存不足
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                								let order =  {
									orderid: _this.$utils.genTradeNo(),
									tablename: _this.orderGoods[i].tablename,
									userid: _this.user.id,
									goodid: _this.orderGoods[i].goodid,
									goodname: _this.orderGoods[i].goodname,
									picture: _this.orderGoods[i].picture,
									buynumber: _this.orderGoods[i].buynumber,
									discountprice: _this.orderGoods[i].price,
									price: _this.orderGoods[i].price,
									total: _this.orderGoods[i].price * _this.orderGoods[i].buynumber,
									discounttotal: _this.orderGoods[i].price * _this.orderGoods[i].buynumber,
									type: type,
									address: _this.address.address,
									status: '已支付'
								}
								if(_this.seat == 1){
									order['address'] = _this.address;
									let buynumber = _this.address.split(",").length;
									order['buynumber'] = buynumber;
									order['total'] = _this.orderGoods[i].price * buynumber;
									order['discounttotal'] = _this.orderGoods[i].price * buynumber;
								}
                                // 添加订单
                                await _this.$api.add('orders', order);
								if (_this.orderGoods[i].id) {
									// 删除购物车对应的模块
									await _this.$api.del('cart', JSON.stringify([_this.orderGoods[i].id]))
									uni.removeStorageSync(`cart${_this.orderGoods[i].goodid}${_this.user.id}`)
								}
							}
							if(isSuccess){
								if(type==1){
									_this.$utils.msgBack('支付成功');
									// 减少余额
									_this.user.money = _this.user.money - _this.total;
									if(_this.user.jifen||_this.user.jifen==0){
										_this.user.jifen = parseFloat(_this.user.jifen) + parseFloat(_this.total);
									}
									await _this.$api.update(table, _this.user);
								}else if(type==2){
									_this.$utils.msgBack('兑换成功');
									// 减少积分
									_this.user.jifen = _this.user.jifen - _this.total;
									await _this.$api.update(table, _this.user);
								}
							}
						}
					}
				});
			},
			onAddressTap() {
				this.$utils.jump('../shop-address/shop-address');
			}
		}
	}
</script>

<style lang="scss">
	.avator {
		width: 150upx;
		height: 150upx;
		margin: 20upx 0;
	}
</style>
