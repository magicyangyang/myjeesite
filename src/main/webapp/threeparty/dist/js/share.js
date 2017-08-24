$(function(){
    var inviteOpenid = Wechat_Util.getUrlParam('inviteopenid');
    Wechat_Util.getTaskInfo({
        succ: function(data){
            switch(data.errorCode){
                case 0: // 解析任务信息
                    var result = parseTaskToInvited(data);
                    break;
            }
            
        }
    });

    function parseTaskToInvited(data){
        var arrInfo = data.data;
        var moneyMap = {
            '0_1': 0,
            '1_1': 5,
            '2_1': 13,
            '3_1': 13,
            '3_0': 23
        };
        for(var i = 0; i < arrInfo.length; i++){
            var item = arrInfo[i], over_num = 0;
            if(item.status == 1 || item.score == "3"){
                var name = item.taskNickname.length > 4? item.taskNickname.substring(0, 4) + '...' : item.taskNickname;
                $("#share_list").append(
                    $("#temp_li").html()
                    .replace('{img}', item.taskHeadimgurl)
                    .replace('{name}', name)
                    .replace('{money}', (moneyMap[item.score + '_' + item.status] || 0))
                )
            }
        }
    }

})