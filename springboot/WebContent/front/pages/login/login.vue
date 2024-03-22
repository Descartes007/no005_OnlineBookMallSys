<template>
	<view class="content">
		<view class="logo">
			<image :style='{"boxShadow":"0 0 16rpx #59f43e","borderColor":"#ccc","borderRadius":"40rpx","borderWidth":"2rpx","width":"160rpx","borderStyle":"solid","url":"http://codegen.caihongy.cn/20201024/ed5e326ca66f403aa3197b5fbb4ec733.jpg","isShow":false,"height":"160rpx"}' src='http://codegen.caihongy.cn/20201024/ed5e326ca66f403aa3197b5fbb4ec733.jpg' mode="aspectFill"></image>
		</view>
		<view class="uni-form-item uni-column">
			<input v-model="username" :style='{"borderColor":"rgba(102, 153, 153, 1)","backgroundColor":"#fff","borderRadius":"20rpx","color":"#333","textAlign":"left","borderWidth":"4rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}' type="text" class="uni-input" name="" placeholder="请输入账号" />
		</view>
		<view class="uni-form-item uni-column">
			<input v-model="password" :style='{"borderColor":"rgba(102, 153, 153, 1)","backgroundColor":"#fff","borderRadius":"20rpx","color":"#333","textAlign":"left","borderWidth":"4rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx"}' type="password" class="uni-input" name="" placeholder="请输入密码" />
		</view>
		<view class="uni-form-item uni-column">
			<picker @change="optionsChange" :value="index" :range="options">
				<view class="uni-picker-type" :style='{"lineHeight":"80rpx","fontSize":"28rpx","color":"rgba(0, 0, 0, 1)","textAlign":"center"}'>{{options[index]}}</view>
			</picker>
		</view>
		<view>
			<button @tap="onLoginTap" type="primary" :style='{"borderColor":"rgba(102, 153, 153, 1)","backgroundColor":"rgba(255, 255, 255, 1)","borderRadius":"8rpx","color":"rgba(0, 0, 0, 1)","borderWidth":"6rpx","fontSize":"32rpx","borderStyle":"double double double double","height":"88rpx"}'>登录</button>
		</view>
		<view class="links">
            			            <view class="link-highlight" @tap="onRegisterTap('yonghu')" :style='{"color":"rgba(0, 0, 0, 1)","fontSize":"26rpx"}'>注册用户</view>
            <view>|</view>
                        			            			            			            			            			            			            			            			            			            			            			<view @tap="onForgetTap" :style='{"color":"rgba(0, 129, 255, 1)","fontSize":"26rpx"}'>忘记密码？</view>
		</view>
		<!-- <view class="links">
			<view @tap="onForgetTap" :style='{"color":"rgba(0, 129, 255, 1)","fontSize":"26rpx"}'>忘记密码？</view>
		</view> -->
	</view>
</template>

<script>
	import menu from '@/utils/menu'

	export default {
		data() {
			return {
				username: '',
				password: '',
				options: [
					'请选择登录用户类型'
				],
                optionsValues: [
					'',
                    'yonghu',
				],
				index: 0
			}
		},
		onLoad() {
			let options = ['请选择登录用户类型'];
			let menus = menu.list();
			this.menuList = menus;
			for(let i=0;i<this.menuList.length;i++){
				if(this.menuList[i].hasFrontLogin=='是'){
					options.push(this.menuList[i].roleName);
				}
			}
			this.options = options;
			
			this.styleChange()
		},
		methods: {
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.uni-input .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.loginFrom.content.input.backgroundColor
					// })
				})
			},
			onRegisterTap(tableName) {
                uni.setStorageSync("loginTable", tableName);
				this.$utils.jump('../register/register')
			},
			onForgetTap() {
				this.$utils.jump('../forget/forget')
			},
			async onLoginTap() {
                if (!this.optionsValues[this.index]) {
					this.$utils.msg('请选择登陆用户类型')
					return
				}
				let res = await this.$api.login(`${this.optionsValues[this.index]}`, {
					username: this.username,
					password: this.password
				});
				uni.setStorageSync("token", res.token);
				uni.setStorageSync("nickname",this.username);
				uni.setStorageSync("nowTable", `${this.optionsValues[this.index]}`);
				res = await this.$api.session(`${this.optionsValues[this.index]}`);
				// 保存用户id
				uni.setStorageSync("userid", res.data.id);
				uni.setStorageSync("role", `${this.options[this.index]}`);
				this.$utils.tab('../index/index');
			},
			optionsChange(e) {
				this.index = e.target.value
			}
		}
	}
</script>

<style lang="scss" scoped>
	$color-primary: #b49950;

	.content {
		padding: 0 100upx;
		display: flex;
		justify-content: center;
		flex-direction: column;
		height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	
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

	.logo {
		text-align: center;

		image {
			height: 200upx;
			width: 200upx;
			margin: 0 0 60upx;
		}
	}

	.uni-form-item {
		margin-bottom: 40upx;
		padding: 0;

		.uni-input {
			font-size: 30upx;
			padding: 7px 0;
		}
	}

	button[type="primary"] {
		background-color: $color-primary;
		border-radius: 0;
		font-size: 34upx;
		margin-top: 60upx;
	}

	.links {
		text-align: center;
		margin-top: 40upx;
		font-size: 26upx;
		color: #999;

		view {
			display: inline-block;
			vertical-align: top;
			margin: 0 10upx;
		}

		.link-highlight {
			margin: 0;
			color: $color-primary
		}
	}
	
	
</style>
