<template>
	<view>
		<view class="title">
			{{detail.title}}
		</view>
		<view class="content">
			<rich-text :nodes="detail.content"></rich-text>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				detail: {},
				id: '',
			}
		},
		async onLoad(options) {
			this.id = options.id;
			let res = await this.$api.info('news', options.id)
			this.detail = res.data;
			this.detail.content = this.detail.content.replace(/<img/g,'<img style="width: 100%;"');
		}
	}
</script>

<style scoped>
	page {
		background: #FFFFFF;
	}

	.title {
		text-align: center;
		font-size: 40upx;
		font-weight: bold;
		margin: 20upx;
	}

	.content {
		margin: 40upx;
		font-size: 30upx;
		line-height: 50upx;
		letter-spacing: 5upx;
	}
</style>
