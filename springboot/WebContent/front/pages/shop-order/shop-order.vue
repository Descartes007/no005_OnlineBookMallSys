<template>
	<view>
		<scroll-view  scroll-x class="bg-white nav">
			<view class='flex text-center'>
				<view class="cu-item flex-sub text-df " @click="navselect(index,nav)" v-for="(nav,index) in navlist" :key="index"
				 :class="[index == currentIndex ? 'text-red cur' : '']">
					{{nav}}
				</view>
			</view>
		</scroll-view>
		<mescroll-uni :top="88" @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
			<view v-for="(item,index) in list" :key="index">
				<view class="bg-white margin-sm padding-sm solids-bottom radius text-content" @click="goDetail(item)">
					<view class="flex justify-between">
						<view class="text-bold" style="flex-shrink: 0;">
							<text class="text-green">{{ item.status == '已支付'?'已支付':'' }}</text>
							<text class="text-red">{{ item.status == '已发货'?'已发货':'' }}</text>
							<text class="text-blue">{{ item.status == '已完成'?'已完成':'' }}</text>
							<text class="text-grey">{{ item.status == '已取消'?'已取消':'' }}</text>
						</view>
						<view><text class="icon-goods padding-right-sm"></text><text class="text-bold solids-bottom">{{ item.orderid }}</text></view>
					</view>
					<view class="bg-white margin-xs padding-xs  radius text-content">
						<view class="flex padding-top-sm solids-top">
							<image :src="item.picture" style="flex-shrink: 0;" mode="aspectFill" class=" cover radius solids"></image>
							<view class="text-content padding-left" style="flex-grow: 1;">
								<view>{{ item.goodname }}</view>
								<view v-if="item.type==1">{{ item.buynumber }} x ￥{{ item.price }}</view>
								<view v-if="item.type==2">{{ item.buynumber }} x {{ item.price }}积分</view>
								<view class="flex justify-end">地址：{{item.address}}</view>
							</view>
						</view>
						<view v-if="item.type==1" class="flex justify-end">合计{{ item.total }}元</view>
						<view v-if="item.type==2" class="flex justify-end">合计{{ item.total }}积分</view>
					</view>
					<view class="flex justify-end"></view>
					<view class="flex justify-end" style="font-size: 24upx;">
						<view v-if="item.status == '已支付'">
							<view @tap="onCancelTap(item)" class="round cu-btn lines-grey mid  margin-right-sm">取消订单</view>
						</view>
						<view v-if="item.status == '已发货'">
							<view @tap="onConfirm(item)" class="round cu-btn lines-grey mid  margin-right-sm">确认收货</view>
						</view>
					</view>
				</view>
			</view>
		</mescroll-uni>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				navlist: [
					'全部', '已支付', '已发货' ,'已完成', '已取消'
				],
				currentIndex: 0,
				pages: 1,
				list: [],
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					page: {
						// num: 0, // 当前页码,默认0,回调之前会加1,即callback(page)会从1开始
						// size: 5 // 每页数据的数量
					},
					noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
				status: '',
				user: ''
			}
		},
		async onShow() {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table)
			this.user = res.data
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		methods: {
			navselect(index, nav) {
				this.currentIndex = index
				if (nav == '全部') {
					this.status = "";
				} else if (nav == '已支付') {
					this.status = "已支付";
				} else {
					this.status = nav
				}
				this.hasNext = true
				// 重新加载数据
				if (this.mescroll) this.mescroll.resetUpScroll()
			},
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},
			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				// 重置分页参数页数为1
				mescroll.resetUpScroll()
			},
			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				let params = {
					page: mescroll.num,
					limit: mescroll.size,
				}
				if (this.status) {
					params['status'] = this.status
				}
				let res = await this.$api.page('orders', params);
				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			async onCancelTap(order) {
				let _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认取消',
					success: async function(res) {
						let table = uni.getStorageSync("nowTable");
						if (res.confirm) {
							order.status = '已取消';
							// 设置订单状态为取消
							let res = await _this.$api.update('orders', order);
							_this.hasNext = true;
							// 重新加载数据
							if (_this.mescroll) _this.mescroll.resetUpScroll()
							if(order.type==1) {
								// 退回用户余额
								_this.user.money = _this.user.money + order.total;
							} else if(order.type==2) {
								// 退回用户积分
								_this.user.jifen = _this.user.jifen + order.total;
							}
							_this.$api.update(table, _this.user);
						}
					}
				});
			},
			/**
			 * 确认收货
			 */
			async onConfirm(order){
				let _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认收货',
					success: async function(res) {
						let table = uni.getStorageSync("nowTable");
						if (res.confirm) {
							order.status = '已完成';
							// 设置订单状态为取消
							let res = await _this.$api.update('orders', order);
							_this.hasNext = true;
							// 重新加载数据
							if (_this.mescroll) _this.mescroll.resetUpScroll()
						}
					}
				});
			}
		}
	}
</script>

<style>
	.cover {
		width: 150upx;
		height: 150upx;
	}
</style>


<style>

</style>
