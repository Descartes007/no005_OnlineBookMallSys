<template>
	<view class="content">
		<view class="row b-b">
			<text class="tit">联系人</text>
			<input class="input" type="text" v-model="addressData.name" placeholder="收货人姓名" placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">手机号</text>
			<input class="input" type="number" v-model="addressData.phone" placeholder="收货人手机号码" placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">地址</text>
			<text @click="chooseLocation" class="input">
				{{addressData.addressName}}
			</text>
			<text class="yticon icon-shouhuodizhi"></text>
		</view>
		<!-- <view class="row b-b">
			<text class="tit">门牌号</text>
			<input class="input" type="text" v-model="addressData.area" placeholder="楼号、门牌" placeholder-class="placeholder" />
		</view> -->
		<view class="row default-row">
			<text class="tit">设为默认</text>
			<switch :checked="addressData.default" color="#fa436a" @change="switchChange" />
		</view>
		<button class="add-btn" @click="confirm">提交</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				addressData: {
					name: '',
					phone: '',
					addressName: '在地图选择',
					address: '',
					// area: '',
					default: false
				},
				id: ''
			}
		},
		async onLoad(option) {
			let title = '新增收货地址';
			if (option.id) {
				title = '编辑收货地址'
				this.id = option.id;
				// this.addressData.addressName = JSON.parse(option.data)
				let res = await this.$api.info('address', this.id);
				this.addressData = res.data;
				this.addressData.addressName = res.data.address;
				if (this.addressData.isdefault == '是') {
					this.addressData.default = true
				}
			}
			uni.setNavigationBarTitle({
				title
			})
		},
		methods: {
			switchChange(e) {
				this.addressData.default = e.detail;
			},
			//地图选择地址
			chooseLocation() {
				uni.chooseLocation({
					success: (data) => {
						this.addressData.addressName = data.name;
						this.addressData.address = data.name;
					}
				})
			},
			//提交
			async confirm() {
				let data = this.addressData;
				// console.log(this.$validate.isMobile(data.phone),data.phone)
				if (!data.name) {
					this.$utils.msg('请填写收货人姓名');
					return;
				}
				// if (data.mobile) {
				// 	this.$utils.msg('请输入正确的手机号码');
				// 	return;
				// }
				if (!this.$validate.isMobile(data.phone)) {
					this.$utils.msg('请输入正确的手机号码');
					return;
				}
				if (!data.address) {
					this.$utils.msg('请在地图选择所在位置');
					return;
				}
				// if (!data.area) {
				// 	this.$utils.msg('请填写门牌号信息');
				// 	return;
				// }
				if (data.default) {
					data.isdefault = '是'
				} else {
					data.isdefault = '否'
				}
				if (this.id) {
					await this.$api.update('address', data)
				} else {
					await this.$api.add('address', data)
				}

				this.$utils.msgBack('操作成功');

			},
		}
	}
</script>

<style lang="scss">
	page {
		background: #FFFFFF;
		padding-top: 16upx;
	}

	.row {
		display: flex;
		align-items: center;
		position: relative;
		padding: 0 30upx;
		height: 110upx;
		background: #fff;

		.tit {
			flex-shrink: 0;
			width: 120upx;
			font-size: 30upx;
			color: #888888;
		}

		.input {
			flex: 1;
			font-size: 30upx;
			color: #888888;
		}

		.icon-shouhuodizhi {
			font-size: 36upx;
			// color: $font-color-light;
		}
	}

	.default-row {
		margin-top: 16upx;

		.tit {
			flex: 1;
		}

		switch {
			transform: translateX(16upx) scale(.9);
		}
	}

	.add-btn {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		margin: 60upx auto;
		// font-size: $font-lg;
		color: #fff;
		background-color: red;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
</style>
