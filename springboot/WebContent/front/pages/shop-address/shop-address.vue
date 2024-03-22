<template>
	<view class="content b-t">
		<view class="list b-b" v-for="(item, index) in list" :key="index" @click="onSelectTap(item)">
			<view class="wrapper">
				<view class="address-box">
					<text v-if="item.isdefault=='是'" class="tag">默认</text>
					<text class="address">{{item.address}}</text>
				</view>
				<view class="u-box">
					<text class="name">{{item.name}}</text>
					<text class="mobile">{{item.phone}}</text>
				</view>
			</view>
			<text class="cuIcon-edit" @click.stop="onAddressDtailTap(item.id)"></text>
			<text style="margin-left: 50upx;" class="cuIcon-delete" @click.stop="onDeleteTap(item.id)"></text>
		</view>
		<button class="add-btn" @click="onAddressDtailTap()">新增地址</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				source: 0,
				list: [],
				user: {}
			}
		},
		async onLoad(option) {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table);
			this.user = res.data;
			res = await this.$api.list('address', {
				userid: this.user.id
			});
			this.list = res.data.list
		},
		async onShow() {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table);
			this.user = res.data;
			res = await this.$api.list('address', {
				userid: this.user.id
			});
			this.list = res.data.list
		},
		methods: {
			//选择地址
			onSelectTap(item) {
				uni.setStorageSync('address', item);
				uni.navigateBack({
					delta: 1
				})
			},
			onAddressDtailTap(id) {
				let url = ''
				if (id) {
					url = '../shop-address-detail/shop-address-detail?id=' + id
				} else {
					url = '../shop-address-detail/shop-address-detail?id='
				}
				this.$utils.jump(url);
			},
			async onDeleteTap(id) {
				var _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							let res = await _this.$api.del('address', JSON.stringify([id]));
							res = await _this.$api.list('address', {
								userid: this.user.id
							});
							_this.list = res.data.list
							_this.$utils.msg('删除成功')
						}
					}
				});
			}
		}
	}
</script>

<style lang='scss'>
	page {
		padding-bottom: 120upx;
	}

	.content {
		position: relative;
	}

	.list {
		display: flex;
		align-items: center;
		padding: 20upx 30upx;
		;
		background: #fff;
		position: relative;
	}

	.wrapper {
		display: flex;
		flex-direction: column;
		flex: 1;
	}

	.address-box {
		display: flex;
		align-items: center;

		.tag {
			font-size: 24upx;
			color: red;
			margin-right: 10upx;
			background: #fffafb;
			border: 1px solid #ffb4c7;
			border-radius: 4upx;
			padding: 4upx 10upx;
			line-height: 1;
		}

		.address {
			font-size: 30upx;
			color: #888888;
		}
	}

	.u-box {
		font-size: 28upx;
		/* color: $font-color-light; */
		margin-top: 16upx;

		.name {
			margin-right: 30upx;
		}
	}

	.icon-bianji {
		display: flex;
		align-items: center;
		height: 80upx;
		font-size: 40upx;
		/* color: $font-color-light; */
		padding-left: 30upx;
	}

	.add-btn {
		position: fixed;
		left: 30upx;
		right: 30upx;
		bottom: 16upx;
		z-index: 95;
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		font-size: 32upx;
		color: #fff;
		background-color: red;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
</style>
