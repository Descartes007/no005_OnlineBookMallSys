<template>
	<view class="content">
		<view class="forget-bg">
			<view class="forget-card" :style='{"boxShadow":"0 0 0px #59f43e","backgroundColor":"rgba(51, 153, 153, 0.37)","borderColor":"rgba(51, 153, 153, 1)","borderRadius":"8rpx","borderStyle":"solid","borderWidth":"2rpx"}'>
				<view class="forget-input forget-margin-b">
					<input v-model="username" :style='{"borderColor":"rgba(51, 153, 153, 1)","backgroundColor":"#fff","borderRadius":"16rpx","color":"#333","textAlign":"left","borderWidth":"4rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}' type="text" placeholder="请输入您的账号" />
				</view>
				<view class="login-input login-margin-b">
					<picker style="color: #888888;padding: 0 40upx;box-sizing:border-box;margin-top: 20upx;" @change="optionsChange" :value="index"
					 :range="options">
						<view class="uni-input" :style='{"lineHeight":"80rpx","fontSize":"28rpx","color":"#333","textAlign":"left"}'>{{options[index]}}</view>
					</picker>
				</view>
			</view>
		</view>

		<view class="forget-btn">
			<button class="landing" :style='{"borderColor":"#ccc","backgroundColor":"rgba(51, 153, 153, 1)","borderRadius":"8rpx","color":"rgba(255, 255, 255, 1)","borderWidth":"2rpx","fontSize":"32rpx","borderStyle":"solid","height":"88rpx"}' @tap="onResetPass" type="primary">重置密码</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				options: [
                    '请选择登陆用户类型',
					                                        '用户',
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ],
                optionsValues: [
					'',
					                                        'yonghu',
                                                                                                                                                                                                                                                                                                                                                                                                                                                        				],
				index: 0
			}
		},
		onLoad() {
			this.styleChange()
		},
		methods: {
			async onResetPass() {
				if(this.username==undefined) {
					this.$utils.msg('请输入账号')
					return;
                                }
				if(this.optionsValues[this.index]=="") {
					this.$utils.msg('请选择角色')
                                        return;
				}
				let res = await this.$api.resetPass(`${this.optionsValues[this.index]}`, this.username)
				this.$utils.msgBack("重置密码成功,原始密码为:123456")
			},
			optionsChange(e) {
				this.index = e.target.value
			},
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.forget-input .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.restPwFrom.content.input.backgroundColor
					// })
				})
			},
		}
	}
</script>

<style>

	.content:after {
		position: fixed;
		top: 0;
		right: 0;
		left: 0;
		bottom: 0;
		content: '';
				background-attachment: fixed;
		background-size: cover;
		background-position: center;
	}
	
	.verify-left {
		width: calc(100% - 260upx);
	}

	.verify-right {
		padding-left: 20upx;
	}

	.verify-btn {
		height: 80upx;
		line-height: 80upx;
		font-size: 28upx;
		width: 240upx;
		border-radius: 8upx;
		background: linear-gradient(left, #FF978D, #FFBB69);
	}

	.verify-left,
	.verify-right {
		float: left;
	}

	.landing {
		height: 84upx;
		line-height: 84upx;
		border-radius: 44upx;
		font-size: 32upx;
		// background: linear-gradient(left, #FF978D, #FFBB69);
	}

	.forget-btn {
		padding: 10upx 20upx;
		margin-top: 380upx;
	}

	.forget-input input {
		background: #FFFFFF;
		font-size: 28upx;
		padding: 10upx 25upx;
		height: 62upx;
		line-height: 62upx;
		border-radius: 8upx;
	}

	.forget-margin-b {
		margin-bottom: 25upx;
	}

	.forget-input {
		padding: 10upx 20upx;
		overflow: auto;
	}

	.forget-card {
		background: #fff;
		border-radius: 12upx;
		padding: 60upx 25upx;
		box-shadow: 0 6upx 18upx rgba(0, 0, 0, 0.12);
		position: relative;
		margin-top: 120upx;
	}

	.forget-bg {
		height: 260upx;
		padding: 25upx;
		// background: linear-gradient(#FF978D, #FFBB69);
	}
</style>
