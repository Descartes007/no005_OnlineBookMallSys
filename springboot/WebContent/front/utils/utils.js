// 跳转页面
const jump = (url) => {
	uni.navigateTo({
		url: url
	})
}
const tab = (url) => {
	uni.switchTab({
		url: url
	})
}
// 输出信息
const msg = (title, duration = 1500, mask = false, icon = 'none') => {
	//统一提示方便全局修改
	if (Boolean(title) === false) {
		return;
	}
	uni.showToast({
		title,
		duration,
		mask,
		icon
	});
}
const msgBack = (title, duration = 1500, delta = 1) => {
	if (!title) {
		return;
	}
	msg(title);
	setTimeout(function() {
		uni.navigateBack({
			delta: delta
		})
	}, duration);
}
const genTradeNo = () => {
	var date = new Date();
	var tradeNo = date.getFullYear().toString() + (date.getMonth() + 1).toString() +
		date.getDate().toString() + date.getHours().toString() + date.getMinutes().toString() +
		date.getSeconds().toString() + date.getMilliseconds().toString();
	for (var i = 0; i < 5; i++) //5位随机数，用以加在时间戳后面。
	{
		tradeNo += Math.floor(Math.random() * 10);
	}
	return tradeNo;
}
export default {
	jump,
	msg,
	msgBack,
	tab,
	genTradeNo
}
