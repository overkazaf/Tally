#Tally
[See Wallet](http://maybeiwill.me/wallet/)

##Features
+  timeline
   *  year
   *  month
   *  week
   *  day
      * type
      * position
      * amount
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
	comment : String,
	create_date : String
};


// 收入模型
Income = {
	income_id : Number,
	wallet_id : Number,
	type : String, [工资、奖金、福利、补贴、彩票等]
	amount : Number,
	detail_id : Number
	
};

// 支出模型
Expense = {
	expense_id : Number,
	wallet_id : Number,
	type : String, [房租、水电费、话费、交通费、油费等]
	amount : Number,
	detail_id : Number
};

// 帐目的详情模型
Detail = {
	detail_id : Number,
	comment : String,
	photo_id  : Array
};

// 照片详情
Photo = {
	photo_id : Number,
	path : String,
	create_date : String
};
```