<!-- glance 店铺购物车单页模板 -->
<template name="glanceShopcart">
	<view class="glance-shop-cart" style="background-color: #F5F5F5;" @touchmove="scrollhoming" @touchend="scrollhomed">
		<!-- 顶部广告图 -->
		<!-- 我是分割 -->
		<view style="height: 10px;background-color: #F5F5F5;"></view>
		<!-- 空购物车背景 -->
		<view v-if="shownullcart" style="width: 100%;height: 250px;">
			<view class="glance-shop-cart-nullcart"></view>
			<view style="height: 30px;width: 100%;font-size: 15px;line-height: 30px;text-align: center;">您的购物车为空，快去添加心爱的商品吧！</view>
		</view>
		<!-- 购物车商品 -->
		<view v-for="(item,index) in cart" :key="index" style="background-color: #FFFFFF;">
			<scroll-view style="width: 100%;white-space: nowrap;" scroll-x="true" :scroll-left='scrollposition'
			 scroll-with-animation="true" v-if="item.id > -99">
				<view class="glance-shop-cart-scrollx-items" style="display: inline-block;width: 100%;">
					<view class="glance-shop-cart-scrollx-items-item">
						<!-- 勾选 -->
						<view style="width: 10%;height: 100%;background-color: #FFFFFF;">
							<!-- 勾选 -->
							<view @tap="clickitemselected(item.id)" class="glance-shop-cart-scrollx-items-item-sel" :class="[ item.id > 0 ? 'glance-shop-cart-itemselected-img':'glance-shop-cart-itemunselected-img']"></view>
						</view>
						<!-- 图片 -->
						<view style="width: 30%;height: 100%;text-align:center;">
							<image :src="item.picture" mode="scaleToFill" style="height: 75px;width: 75px;line-height: 80px;padding-top: 5px;"></image>
						</view>
						<!-- 描述 -->
						<view class="glance-shop-cart-items-item-des">
							<!-- 名称 -->
							<view class="sigle-line-text" style="font-size: 16px;height: 33.33%;text-align: left;">{{ item.goodname }}</view>
							<!-- 属性 -->
							<!-- 价格 & 数量-->
							<view class="glance-shop-cart-items-item-pq">
								<view class="sigle-line-text" style="font-size: 15px;text-align: left;width: 50%;">￥{{item.price}}</view>
								<!-- 数量操作 -->
								<view class="glance-shop-cart-items-item-opt">
									<!-- 减数量 -->
									<view class="glance-shop-cart-items-item-opt-quantity-minus" @click="minusitem(item.id)">-</view>
									<!-- 数量 -->
									<view class="glance-shop-cart-items-item-opt-quantity">{{ item.buynumber }}</view>
									<!-- 加数量 -->
									<view class="glance-shop-cart-items-item-opt-quantity-plus" @click="plusitem(item.id)">+</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
			<view style="height: 10px;background-color: #F5F5F5;"></view>
		</view>
		<!-- 金额合计 -->
		<view class="glance-shop-cart-order">
			<!-- 全选 -->
			<view style="width: 12%;">
				<!-- 勾选 -->
				<view class="glance-shop-cart-scrollx-items-item-sel" :class="[ isselectedall ? 'glance-shop-cart-itemselected-img':'glance-shop-cart-itemunselected-img']"
				 @click="clickitemselectedall"></view>
			</view>
			<!-- 总数量 -->
			<view class="glance-shop-cart-total-cnt">已选：( {{ cntitems }} )</view>
			<!-- 总金额 -->
			<view class="glance-shop-cart-total-amt">￥{{ totalamount }}</view>
			<!-- 立即下单 -->
			<view class="glance-shop-cart-create-order" @click="createorder">立即下单</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 全选 默认全选
				isselectedall: true,
				// scroll position
				scrollposition: 0,
				// 实际项目中 购物车数据可从本地缓存中获取或从服务端获取
				cart: [],
				// 合计金额
				totalamount: 0,
				// 购物车商品数量 
				cntitems: 0,
				// 显示空购物车背景
				shownullcart: false,
				user: {}
			};
		},
		onShow() {
			this.init();
		},
		methods: {
			async init() {
				this.totalamount = 0;
				this.cntitems = 0;
				let table = uni.getStorageSync("nowTable");
				let res = await this.$api.session(table);
				this.user = res.data;
				res = await this.$api.list('cart', {
					userid: this.user.id
				});
				this.cart = res.data.list;
				// 默认勾选购物车所有商品 合计金额 合计数量
				for (let i = 0; i < this.cart.length; i++) {
					// 总金额 
					this.totalamount = parseFloat(this.totalamount) + parseFloat(this.cart[i].price * this.cart[i].buynumber)
					// 总数量
					this.cntitems = parseInt(this.cntitems) + parseInt(this.cart[i].buynumber)
				}
				this.totalamount = this.fmamount(this.totalamount)
			},
			// scroll x 归位
			scrollhoming() {
				this.scrollposition = this.scrollposition - 1
			},
			// scroll x 归位后重置 scrollposition
			scrollhomed() {
				this.scrollposition = 0
			},
			// 点击全选
			clickitemselectedall() {
				this.isselectedall = !this.isselectedall
				// 全选
				if (this.isselectedall) {
					if (this.cart) {
						for (var i = 0; i < this.cart.length; i++) {
							// 未选的则选
							if (this.cart[i].id < 0) {
								this.cart[i].id = -this.cart[i].id
								// 更新总数量
								this.cntitems = parseInt(this.cntitems) + parseInt(this.cart[i].buynumber)
								// 更新总金额
								this.totalamount = parseFloat(this.totalamount) + parseFloat(this.cart[i].price * this.cart[i].buynumber)
								this.totalamount = this.fmamount(this.totalamount)
							}
						}
					}
				} else {
					// 全不选
					// this.cntitems = 0
					if (this.cart) {
						for (var i = 0; i < this.cart.length; i++) {
							// 累计总金额和总数量 勾选时加
							if (this.isselected(this.cart[i].id)) {
								this.cart[i].id = -this.cart[i].id
								// 更新总数量
								this.cntitems = this.cntitems - this.cart[i].buynumber
								// 更新总金额
								this.totalamount = this.totalamount - this.cart[i].price * this.cart[i].buynumber
								this.totalamount = this.fmamount(this.totalamount)
							}
						}
					}
				}
			},
			// 点击勾选
			clickitemselected(id) {
				if (this.cart) {
					for (var i = 0; i < this.cart.length; i++) {
						if (this.cart[i].id == id) {
							this.cart[i].id = -this.cart[i].id
							// 累计总金额和总数量 勾选时加
							if (this.isselected(this.cart[i].id)) {
								// 更新总数量
								this.cntitems = parseInt(this.cntitems) + parseInt(this.cart[i].buynumber)
								// 更新总金额
								this.totalamount = parseFloat(this.totalamount) + parseFloat(this.cart[i].price * this.cart[i].buynumber)
								this.totalamount = this.fmamount(this.totalamount)
								// 最后已勾选则 全选
								if (this._isselectedall()) {
									this.isselectedall = true
								}
							} else {
								// 取消勾选时减
								// 更新总数量
								this.cntitems = this.cntitems - this.cart[i].buynumber
								// 更新总金额
								this.totalamount = this.totalamount - this.cart[i].price * this.cart[i].buynumber
								this.totalamount = this.fmamount(this.totalamount)
								this.isselectedall = false
							}
							return
						}
					}
				}
			},
			// 减数量
			async minusitem(itemid) {
				for (let i = 0; i < this.cart.length; i++) {
					if ((this.cart[i].id == itemid) && (this.cart[i].buynumber > 0)) {
						// 更新item数量
						this.cart[i].buynumber = this.cart[i].buynumber - 1
						// 勾选状态下更新数量和金额
						if (this.isselected(this.cart[i].id)) {
							// 更新总数量
							this.updatecntitems(-1)
							// 更新总金额
							this.updatetotalamt(-this.cart[i].price)
							// 执行update接口
							await this.$api.update('cart', this.cart[i]);
						}
						// 数量减为0时 不勾选
						if (this.cart[i].buynumber == 0) {
							this.cart[i].id = this._unselected(this.cart[i].id)
							// 执行删除接口
							await this.$api.del('cart', JSON.stringify([itemid]));
							console.log(`cart${this.cart[i].goodid}`)
							uni.removeStorageSync(`cart${this.cart[i].goodid}`)
							this.init();
						}
						return
					}
				}
			},
			// 加数量
			async plusitem(itemid) {
				for (let i = 0; i < this.cart.length; i++) {
					// 这里需要进行超卖控制 商品可售卖的数量 这里面示例可售卖100
					if ((this.cart[i].id == itemid) && (this.cart[i].buynumber < 100)) {
						// 更新item数量
						this.cart[i].buynumber = parseInt(this.cart[i].buynumber) + 1
						// 勾选状态下更新数量和金额
						if (this.isselected(this.cart[i].id)) {
							// 更新总数量
							this.updatecntitems(1)
							// 更新总金额
							this.updatetotalamt(this.cart[i].price)
						} else {
							// 加数量时未勾选则 勾选
							this.cart[i].id = this._selected(this.cart[i].id)
							// 更新总数量、
							this.cntitems = parseInt(this.cntitems) + parseInt(this.cart[i].buynumber)
							// 更新总金额
							this.totalamount = parseFloat(this.totalamount) + parseFloat(this.cart[i].price * this.cart[i].buynumber)
							this.totalamount = this.fmamount(this.totalamount)
						}
						// 执行update接口
						await this.$api.update('cart',this.cart[i]);
						// 最后已勾选则 全选
						if (this._isselectedall()) {
							this.isselectedall = true
						}
						return
					}
				}
			},
			// 更新合计金额
			updatetotalamt(amt) {
				this.totalamount = parseFloat(this.totalamount) + parseFloat(amt)
				this.totalamount = this.fmamount(this.totalamount)

			},
			// 更新合计数量
			updatecntitems(cnt) {
				this.cntitems = parseInt(this.cntitems) + parseInt(cnt)
			},
			// 格式化金额
			fmamount(amt) {
				return Math.round(amt * 100) / 100
			},
			// 不勾选
			_unselected(id) {
				return -Math.abs(id)
			},
			// 勾选
			_selected(id) {
				return Math.abs(id)
			},
			// 是否勾选
			isselected(id) {
				return id > 0
			},
			// 是否全部已勾选
			_isselectedall() {
				for (let i = 0; i < this.cart.length; i++) {
					// 存在一个未勾选 则未全选
					if (this.cart[i].id < 0) {
						return false
					}
				}
				return true
			},
			// 是否全部删除
			_isdeledall() {
				for (let i = 0; i < this.cart.length; i++) {
					// 存在 未删除
					if (this.cart[i].id > -99) {
						return false
					}
				}
				return true
			},
			// 生成订单
			createorder() {
				// 合计金额大于0 创建订单
				if (this.totalamount == 0) {
					uni.showModal({
						content: '请选择下单的商品！'
					})
				} else {
					let orderGoods = [];
					// 1、处理购物车内已选择的订单生成商品
					for (let i = 0; i < this.cart.length; i++) {
						// item id 大于0 的是勾选的
						if (this.cart[i].id > 0) {
							//这里的item 是下单的
							orderGoods.push(this.cart[i])
						}
					}
					// 跳转到下单页面,需要购买的数据保存在缓存
					uni.setStorageSync('orderGoods', orderGoods)
					this.$utils.jump('../shop-order-confirm/shop-order-confirm?type=1');
				}
			}
		}
	}
</script>

<style lang="scss">
	page {
		background: rgb(245, 245, 245);
	}

	// 单行文本样式
	.sigle-line-text {
		width: 100%;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		text-align: left;
	}

	.glance-shop-cart {
		width: 100%;
		// font-family: Arial, Helvetica, sans-serif;
	}

	// 滑动item 样式
	.glance-shop-cart-scrollx-items {
		width: 100%;
		height: 90px;
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: flex-start;
		justify-content: space-between;
		// padding: 10px 0 10px 0;
		margin-top: 10px;
	}

	// 滑动区域内item 样式
	.glance-shop-cart-scrollx-items-item {
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: flex-start;
		justify-content: flex-start;
		width: 100%;
		height: 100%;
	}

	// item 勾选样式
	.glance-shop-cart-scrollx-items-item-sel {
		position: relative;
		top: 30%;
		left: 60%;
		;
	}

	// item 描述信息样式
	.glance-shop-cart-items-item-des {
		width: 60%;
		height: 100%;
		display: -webkit-flex;
		flex-flow: column nowrap;
		align-items: flex-start;
		justify-content: flex-start;
	}

	// 价格和数量样式
	.glance-shop-cart-items-item-pq {
		width: 100%;
		height: 33.33%;
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: flex-start;
		justify-content: space-between;
	}

	// 数量操作
	.glance-shop-cart-items-item-opt {
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: center;
		justify-content: space-between;
		width: 50%;
		height: 100%;
		font-size: 20px;
		margin-right: 10px;
		text-align: center;
		color: #6C6C6C;
		line-height: 100%;

		// 减数量
		&-quantity-minus {
			border-style: solid;
			border-width: 1px 0px 1px 1px;
			border-color: #E0E0E0;
			width: 33.33%;
			height: 80%;
			position: relative;
			top: 25%;
			transform: translateY(-50%)
		}

		// 数量
		&-quantity {
			border-style: solid;
			border-width: 1px 1px 1px 1px;
			border-color: #E0E0E0;
			width: 33.33%;
			height: 80%;
			font-size: 13px;
			position: relative;
			top: 25%;
			transform: translateY(-50%);
		}

		// 加数量
		&-quantity-plus {
			border-style: solid;
			border-width: 1px 1px 1px 0;
			border-color: #E0E0E0;
			width: 33.33%;
			height: 80%;
			position: relative;
			top: 25%;
			transform: translateY(-50%);
		}
	}

	// 底部汇总和订单样式
	.glance-shop-cart-order {
		width: 100%;
		height: 60px;
		background-color: #ffffff;
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: center;
		justify-content: space-between;
		position: fixed;
		bottom: 0;
	}

	// 合计金额样式
	.glance-shop-cart-total-cnt {
		width: 30%;
		text-align: left;
		line-height: 40px;
		font-size: 13px;
		margin-left: 15px;
	}

	// 合计金额样式
	.glance-shop-cart-total-amt {
		width: 35%;
		text-align: right;
		margin-right: 15px;
		line-height: 40px;
		font-size: 16px;
		margin-left: 10px;
		color: #f40;
	}

	// 生成订单样式
	.glance-shop-cart-create-order {
		width: 30%;
		background-color: #DC143C;
		text-align: center;
		line-height: 40px;
		font-size: 0.8rem;
		color: #ffffff;
		margin-right: 10px;
	}

	// item 选择样式
	// 不勾选
	.glance-shop-cart-itemunselected-img {
		width: 16px;
		height: 16px;
		border: solid 1px lightgray;
		border-radius: 50%;
	}

	// 勾选
	.glance-shop-cart-itemselected-img {
		width: 20px;
		height: 20px;
		background-size: 20px 20px;
		background-repeat: no-repeat;
		background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAC+ElEQVRYR8WXy2vUUBTGv5OZpCIqRexDp8miYpOBurEuFLQKirR26wsKgv4F/gW2de9G16IbQbFrW0TEtjtfiKiT6dQuklatuJCWgk0mcySxHSeTTPMYYbLL5J7v+91zz9x7LqHFD7XYH7EBSkAbK9lBh2kIhOME6gI4xwyHiFYA/sqMVwBPO2b5ZT9gxZlcLICiLF1m4jsAdcYRZeC7wLihmtbjqPHbAix2ostqk+6DMBwlFPqdMSVtWNd6f2ClUXxDgPlcZqQiCA9AtC+V+VYQ808Gj+bN8rMwnVCAgpw9R6BpEMVaokhAZgbzeW2pPF0/NmBQ7EGOBfEjQO2RwokG8C+q2P3qEpZrwwIAuizOguhkIu24g5nnNNMebAig5zIXkMk8iauXapzjXNSWncmtWF8GdEX6AqA3lXBNEDOXiagEIB+itagZ1sEAQEERBwj0pllzgFeJcVY17de6LM6B6ES9JoOP5g37rft7NQMFRRonYKw5AF4VHJzqW7bfl7rR4YjiJxB1BAEwkTescR+ArojPATqTHqDOXJJmAWjhevxCM2zP618GZKlIhL50AEnMAWbM501LrQMQ14loZwhAIbthnXYkcZSB28HNKZm5q8/M63nT3lW/BKsA7Q4DUA3rMAFOURGvMsPdnjczl9z8rz6vaYa9pw5A0gF4aQl5JlXDuuKHwJqv4LZd84BiUTMsrz6qNRCjCH0Q5OBDtdqTmbsZCBZhsSd7kwVhIqIIqxDuOO+vltgcoEplTF0q3/JloLRfPOKI5G0OcSAWurE3jbmrnbF54NA3+50PwH3RZWkBhOo22QiEgc/EaAfhQBRtyPfwrdgDaPVh5EIUZHGGiHxHZopZhodEHcduVMsbEm8perJDEISp/zhztycbCusLG/Z8m33hw5Y0pVszd9vyjR3SPQJGUmbjqfTbup6qLa811BXpEsB3419MeEVgci8mj6LAY7fdNVezYRCONbqaZYinLKM881+vZlGzaOZ77Aw0Y7Jd7B9C42gwboolDgAAAABJRU5ErkJggg==)
	}

	.glance-shop-cart-del {
		display: inline-block;
		width: 18%;
		height: 90px;
		background-color: red;
	}

	// 删除商品背景样式
	.glance-shop-cart-del-img {
		width: 30px;
		height: 30px;
		background-repeat: no-repeat;
		position: relative;
		top: 25%;
		transform: translateY(-50%);
		left: 50%;
		transform: translateX(-50%);
		background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAABoklEQVRYR+2XTSttURzGf08kLzHxBUgpE4qSMvAhcOtmxPhkYmxgSpmRqYnXGN3u7M7uxOhmcKUU8g2QZMCjddpOB/s4i72ltPdw77We9dvPf708S3zxo9jxbTcDs8AY0Faj3w3wB1iRdBej/R6ADeAHcAhc1xBvBwaAXUk/8wa4BJYlLbwlbDt8L0nqzA3AdnDqHpiQtF8HYBzYkdSQG0AQsj0C/JdUy/7yeLY7gD5JB+8CsD0MtMZ0yqHNhaTToFOZhLaPAnkO4jES/yQNPgOI6fUZbV4tQ9vdwJykUp4D2l4FFiWdV+umAYS1vi0peo+IAbXtsI9I2i0APuRAUsMlSWdPArangAdJm1XvUudQ5hKkCdgO9bSkMG+eNqLUOVQAFA4UDhQOfAsHQiacrM6EtreSnbCSgG2nZsI8HHiVCW33JADlePVWJswMEHPm10nL0XlgIrlYfEYgCbF+r95xPAr8TaL1cdY/T8rSn9yoeiWd1ANoTAC6gHXgNiNECzAN/JI081Ir1Wbb4X4wDwwBTRkBroA1Sb/TdHKt80dAHwFIXaswp43kfwAAAABJRU5ErkJggg==)
	}

	// 空购物车背景样式
	.glance-shop-cart-nullcart {
		width: 120px;
		height: 120px;
		position: relative;
		transform: translateY(-50%);
		left: 50%;
		transform: translateX(-50%);
		background-repeat: no-repeat;
		background-image: url(data:image/png;base64,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)
	}
</style>
