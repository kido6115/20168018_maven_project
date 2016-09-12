$("#manage").click(function(){
        $("#toggle").slideToggle();
        $("#toggle2").slideToggle("toggled");

    });
var alps=new Array();
var alpe=new Array();

var cou = $("#count").val();
console.log(cou);
	for(var k=1;k<cou;k++)
	{
	holi(k);
	count(k);
	}  



function setMin(l){
	 var d1= document.getElementById('date_s'+l).value;
	  $("#date_e"+l).attr({ "min":d1 ,}); 
	  countt(l);
			 
}
function setMax(y){
	 var e1= document.getElementById('date_e'+y).value;
	  $("#date_s"+y).attr({ "max":e1 ,});
	  countt(y);
}
function countt(x){
	 var ds =document.getElementById('date_s'+x).value;
	 var de =document.getElementById('date_e'+x).value;
	 var ts =document.getElementById('time_s'+x).value;
	 var te =document.getElementById('time_e'+x).value;
	 
	if(ds!=null&&ds!=null&&ts!='請選擇'&&te!='請選擇')
		count(x);
	
};
function count(j){
					
	//開始日期
		var ds=$("#date_s"+j).val();
		
		//結束日期
		var de=$("#date_e"+j).val();
		
		//開始時間
		var str="#time_s";
		str=str+j+" :selected";
		var ts=$(str).val();
		
		
		//結束時間
		str="#time_e";
		str=str+j+" :selected";
		var te=$(str).val();
		
		ds=ds.split('-');
		ts=ts.split(':');
		de=de.split('-');
		te=te.split(':');
		
		var s = new Date(ds[0],ds[1],ds[2],ts[0],ts[1]);
		var e = new Date(de[0],de[1],de[2],te[0],te[1]); 
		alps[j]=s;
		alpe[j]=e;
		
		var pd = (de[0]-ds[0])*365+(de[1]-ds[1])*30+(de[2]-ds[2]);
		var bh = pd *16;
		var rh = 0;
		if(ts[0]<=12&&te[0]>=13)
			rh=1;
		if(pd>0&&ts[0]>= 13 &&te[0]<= 12)
			rh=-1;
						
		var ONE_HOUR = 1000 * 60 * 60;  // 1小時的毫秒數
		var ONE_MIN = 1000 * 60; // 1分鐘的毫秒數


		var diff = e-s;
		var leftHours = Math.floor(diff/ONE_HOUR)-bh -rh;
		if(leftHours >=0) diff = diff - ((leftHours+bh+rh) * ONE_HOUR);

		var leftMins = Math.floor(diff/ONE_MIN);
		if(leftMins > 0) diff = diff - (leftMins * ONE_MIN);
							
		if(leftMins==30)
			leftMins=0.5; 
		var tot =leftHours+leftMins;
		
		
	if(tot>0) 
		$("#tot"+j).val(tot); 
		else if(tot==0)
	    $("#tot"+j).val('請假時數為零');	
		else if(tot<0)
		$("#tot"+j).val('請假時數為負');	 
		else
		$("#tot"+j).val('時間尚未輸入完整');
	    
}

function holi(h){
	var d = document.getElementById('date_s'+h);

	function holiday(e){
		var mon;
		var datee;
	  // Days in JS range from 0-6 where 0 is Sunday and 6 is Saturday
	  if((new Date(e.target.value).getMonth()+1)<10){
		   mon='0'+(new Date(e.target.value).getMonth()+1);
	  }else {
		  mon=(new Date(e.target.value).getMonth()+1);
	  }
	  if((new Date(e.target.value).getDate())<10){
		  datee='0'+(new Date(e.target.value).getDate());
	  }else {
		  datee=(new Date(e.target.value).getDate());
	  }
	  var day = (new Date(e.target.value).getFullYear())+'-'+mon+'-'+datee;

	  
	}

	d.addEventListener('input',holiday);
	
	
	
	var d2 = document.getElementById('date_e'+h);

	function holiday2(e){
		var mon;
		var datee;
	  // Days in JS range from 0-6 where 0 is Sunday and 6 is Saturday
	  if((new Date(e.target.value).getMonth()+1)<10){
		   mon='0'+(new Date(e.target.value).getMonth()+1);
	  }else {
		  mon=(new Date(e.target.value).getMonth()+1);
	  }
	  if((new Date(e.target.value).getDate())<10){
		  datee='0'+(new Date(e.target.value).getDate());
	  }else {
		  datee=(new Date(e.target.value).getDate());
	  }
	  var day = (new Date(e.target.value).getFullYear())+'-'+mon+'-'+datee;
	  

	  
	}

	d2.addEventListener('input',holiday2);
	
	var date = document.getElementById('date_s'+h);
	function noHoliday(e){
	  // Days in JS range from 0-6 where 0 is Sunday and 6 is Saturday
	  var day = new Date(e.target.value).getUTCDay();
	  if ( day == 0 || day==6){
		  sweetAlert('不可以選擇假日');
		  document.getElementById('date_s'+h).value="";
	    e.target.setCustomValidity('不可選擇假日！');
	  } else {
	    e.target.setCustomValidity('');
	  };
	}

	date.addEventListener('input',noHoliday);

	
	var date2 = document.getElementById('date_e'+h);
	function noHoliday2(e){
	  // Days in JS range from 0-6 where 0 is Sunday and 6 is Saturday
	  var day = new Date(e.target.value).getUTCDay();
	  if ( day == 0 || day==6){
		  sweetAlert('不可以選擇假日');
		  document.getElementById('date_e'+h).value="";
	    e.target.setCustomValidity('不可選擇假日！');
	  } else {
	    e.target.setCustomValidity('');
	  };
	}

	date2.addEventListener('input',noHoliday2);
	}


var kl=[0,0,0,0,0];
kl[1]=parseFloat($("#kl0").val());
kl[2]=parseFloat($("#kl1").val());
kl[3]=parseFloat($("#kl2").val());
kl[4]=parseFloat($("#kl3").val()); 
console.log(kl);



$("#submit").click(function(){
	var r=check();
	if(r==1)
	return false;
	if(r==0)
		return true;
})
function check(){
	
	var bug={'pk' : '', 'ds' : '', 'de' : '','ts' : '', 'te' : '','total' : '' ,'re' : '' ,};
	var kp=[0,0,0,0,0];
	for(var i=1;i<cou;i++)
	{
	
	
		//開始日期
		var ds=$("#date_s"+i).val();
		
		//結束日期
		var de=$("#date_e"+i).val();
		
		//開始時間
		 var str="#time_s";
		str=str+i+" :selected";
		var ts=$(str).text();
				
		//結束時間
		str="#time_e";
		str=str+i+" :selected";
		var te=$(str).text();
				
		//時數
		var tot=$("#tot"+i).val();
												
		//錯誤提醒
		if(ts=='請選擇')
			bug.ts+="子假單"+i+"開始時間未選擇"+"\n";
		if(te=='請選擇')
			bug.te+="子假單"+i+"結束時間未選擇"+"\n";
		
		if(tot=='請假時數為負')
				bug.total+="子假單"+i+"剩餘時間為負"+"\n";
		if(tot=='請假時數為零')
			bug.total+="子假單"+i+"請假時間為零"+"\n";
		if(tot=='時間尚未輸入完整')
			bug.total+="子假單"+i+"時間尚未輸入完整"+"\n";
	 
		
		//假別
		str="input[name=pk";
		str=str+i+"]:checked";
		var k=$(str).val();
		console.log(k);
		tot=parseFloat(tot);
		
		if(k=='2')
			kp[1]+=tot;
		if(k=='3')
			kp[2]+=tot;
		if(k=='4')
			kp[3]+=tot;
		if(k=='5')
			kp[4]+=tot;			
		
		if(k=='2'&&kl[1]<kp[1])
			bug.pk+="子假單"+i+"病假剩餘額度不足"+"\n";
		if(k=='3'&&kl[2]<kp[2])
			bug.pk+="子假單"+i+"喪假剩餘額度不足"+"\n";
		if(k=='4'&&kl[3]<kp[3])
			bug.pk+="子假單"+i+"產假剩餘額度不足"+"\n";
		if(k=='5'&&kl[4]<kp[4])
			bug.pk+="子假單"+i+"特休剩餘額度不足"+"\n"; 
								
			}	;
	
	
 for(i=1;i<cou;i++)
	{
	 
	  for(var j=1;j<i;j++)
		{
		   	if(alps[i]-alpe[j]<=0&&alps[i]-alps[j]>=0)
			bug.re="時間重複";
			if(alpe[i]-alps[j]>=0&&alpe[i]-alpe[j]<=0)
			bug.re="時間重複";
			if(alps[i]-alps[j]<=0&&alpe[i]-alpe[j]>=0)
				bug.re="時間重複";
		}; 
	}			 

var bugmeg = '';
if (bug.pk!='')
	bugmeg=bugmeg+bug.pk;
if (bug.ds!='')
	bugmeg=bugmeg+bug.ds;
if (bug.de!='')
	bugmeg=bugmeg+bug.de;
if (bug.ts!='')
	bugmeg=bugmeg+bug.ts;
if (bug.te!='')
	bugmeg=bugmeg+bug.te;
if (bug.total!='')
	bugmeg=bugmeg+bug.total;
if (bug.re!='')
	bugmeg=bugmeg+bug.re;
	
if(bugmeg != ''){
	sweetAlert(bugmeg);
	return 1;
	};
	
	return 0;
	};


	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
	});
