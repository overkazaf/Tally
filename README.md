#Tally [Memo your finaces]
*Referrence projects*

[Wallet](http://maybeiwill.me/wallet/)

##Feature Structure
+  timeline
   *  year
   *  month
   *  week
   *  day
      * type
      * position
      * amount
      * hasPicture      
      * ...
      
      
+  wallet
    +  expense
       *  date
   	   *  type
       *  amount
       *  detail
          *  position
   	      *  attachment
   
    +  income
       *  date
       *  type
       *  amount
       *  detail
          *  position
          *  attachment
   
+  configuration
   *  sign
   *  avatar
   *  password
   
+  to be continued...


##Data Models (JSON)
```
// 用户模型
User = {
	user_id : Number,
	user_info_id : Number,
	username : String,
	password : String,
	create_date : String
};

// 用户信息模型
UserInfo = {
	user_info_id : Number,
	user_id : Number,
	gender : Boolean,
	age : Number,
	occupation : String,
	email : String,
	phone : String,
	avatar : Number
};

// 头像模型
Avatar = {
	avatar_id : Number,
	path : String,
	create_date : String
};

// 钱包模型
Wallet = {
	wallet_id : Number,
	wallet_name : String,  [银行卡、现金、医保卡、红包、公积金等]
	amount : Number,
	comment : String,
	create_date : String
};


// 收入模型
Income = {
	income_id : Number,
	wallet_id : Number,
	type_id : Number, [工资、奖金、福利、补贴、彩票等]
	amount : Number,
	detail_id : Number [详情，见Detail的data model]
	
};

// 支出模型
Expense = {
	expense_id : Number,
	wallet_id : Number,
	type_id : Number, [房租、水电费、话费、交通费、油费等]
	amount : Number,
	detail_id : Number [详情，见Detail的data model]
};

// 帐单类型
Type = {
	type_id : Number,
	type_name : String,
	create_date : String
};

// 帐目的详情模型
Detail = {
	detail_id : Number,
	pos_id : Number,
	comment : String,
	attach_id  : Array
};


// 地理位置模型，作为帐目详情的附加信息
Pos = {
	pos_id : Number,
	longitude : Number,
	latitude : Number,
	pos_name : String
};

// 附件模型，目前只提供照片
Attachment = {
	attach_id : Number,
	path : String,
	create_date : String
};
```


## 下边是给开发者的废话
`v1.0版本功能概要`

*  提供增加（删除）钱包的功能
*  提供在某一钱包内的增加（删除）收入或支出功能
*  提供按日期、类型、[地点]等条件查询帐目的功能
*  提供按日期、金额、分类、[地点]的排序功能
*  个人信息提供签名和头像上传、更换，修改密码的功能

`IDE`

Android Studio


`框架`

Android/PhoneGap + SQLLite

`版本支持`

Android 4.1.2及以上版本
