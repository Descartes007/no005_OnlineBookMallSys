<template>
	<view class="content">
		<view v-if="!changeSuccess">
			<view class="uni-form-item uni-column">
				<input v-model="money" type="number" class="uni-input" name="" placeholder="请输入要充值的数量" />
			</view>
			<button type="primary" @tap="onResetPass">提交</button>
		</view>
		<view v-if="changeSuccess">
			<view class="text-reset">充值成功！</view>
			<button type="primary" @tap="back">返回</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				changeSuccess: false,
				money: 0
			}
		},
		async onShow() {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table);
			this.user = res.data
		},
		methods: {
			back() {
				uni.navigateBack({
					delta: 1
				})
			},
			async onResetPass() {
				let table = uni.getStorageSync("nowTable");
				if (this.money <= 0) {
					this.$utils.msg('请输入正确的充值数目');
					return;
				}
				this.user.money = parseFloat(this.user.money) + parseFloat(this.money);
				let res = await this.$api.update(table, this.user)
				this.changeSuccess = !this.changeSuccess
			}
		}
	}
</script>

<style lang="scss" scoped>
	$color-primary: #b49950;

	.content {
		padding: 100upx;
	}

	.uni-form-item {
		margin-bottom: 40upx;
		padding: 0;
		border-bottom: 1px solid #e3e3e3;

		.uni-input {
			font-size: 30upx;
			padding: 7px 0;
		}
	}

	.column-with-btn {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;

		button {
			font-size: 24upx;
			margin: 0;
			width: 180upx;
			text-align: center;

			&:after {
				border: none
			}

			&.active {
				background-color: $color-primary;
				color: $uni-text-color-inverse;
			}
		}
	}

	.img-captcha {
		width: 150upx;
		height: 60upx;
	}

	button[type="primary"] {
		background-color: $color-primary;
		border-radius: 0;
		font-size: 34upx;
		margin-top: 60upx;
	}

	.text-reset {
		text-align: center;
		margin-bottom: 100upx;
		font-size: 36upx;
	}
</style>
