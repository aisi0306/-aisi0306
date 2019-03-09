(function(w,d,u){
	var loginForm = util.get('loginForm');
	if(!loginForm){
		return;
	}
	var account = loginForm['account'];
	var password = loginForm['password'];
	var isSubmiting = false;
	var loading = new Loading();
	var page = {
		init:function(){
			loginForm.addEventListener('submit',function(e){
				if(!isSubmiting && this.check()){
					var value1 = account.value;
					var value2 = md5(password.value);
					isSubmiting = true;
					loading.show();
					ajax({
						data:{account:value1,password:value2},
						url:'/login',
						success:function(result){
							loading.hide();
							location.href = '/index';
						},
						error:function(message){
							loading.result(message||'登录失败');
							isSubmiting = false;
						}
					});
				}
			}.bind(this),false);
			[account,password].forEach(function(item){
				item.addEventListener('input',function(e){
					item.classList.remove('z-err');
				}.bind(this),false);
			}.bind(this));
		},
		check:function(){
			var result = true;
			[
				[account,function(value){return value == ''}],
				[password,function(value){return value == ''}]
			].forEach(function(item){
				var value = item[0].value.trim();
				if(item[1](value)){
					item[0].classList.add('z-err');
					result = false;
				}
				item[0].value = value;
			});
			return result;
		}
	};
	page.init();
})(window,document);