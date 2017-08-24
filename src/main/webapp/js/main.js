var wxConfig = Wechat_Util.shareContent.invite;

Wechat_Util.wxShare({
    wxConfig: wxConfig
});

$(function(){

    // 判断当前活动状态 false－已结束
    if(!Wechat_Util.partyStatus()){
        alert('活动已结束！');
        return false;
    }

    var $share = $(".share");
    var $answer = $(".answer");
    var $finish = $("#redeem");
    var $finishTitle = $("#redeem_title");
    var $finishZero = $("#redeemZero");
    var $home = $(".home_page");
    var $tasklist = $("#tasklist");
    var $totalMoney = $("#totalMoney");
    var $findFriend = $(".answerFind");
    var $getMoney = $(".answerGetMoney");
    var $shareMoney = $(".answerShareFriend");

    // 马上／继续 找人
    $findFriend.on('click', function(){
        showSharePopup('找到对的人<br>是成功<br>拿到红包<br>最好的开始~');
    });
    $share.on('click', function(){
        hideSharePopup();
    })

    function showSharePopup(title){
        $share.show().find('p').html(title);
    }
    function hideSharePopup(){
        $share.hide();
    }
    // 我要分钱
    $shareMoney.on('click', function(){
        wxConfig = Wechat_Util.shareContent.shareMoney;
        showSharePopup('<br>大家都不容易<br>发个红包给朋友')
    })
    $("#btnShareResult").on('click', function() {
        showSharePopup('让大家都看看<br>你的朋友<br>都是什么样的人')
    })

    // 绑定任务者和邀请者关系（绑定并不意味着“做任务”）
    function bindInvite(){
        $.post('/threeparty/bind', {
            taskOpenid: Wechat_Util.openid,
            inviteOpenid: Wechat_Util.getUrlParam('inviteopenid')
        }, function(data){
            //
        })
    }

    inviteIsShare();
    
    // 邀请人是否分享过活动
    function inviteIsShare(){
        var res = Wechat_Util.getClickInfo(1);
        if(res === false){
            // 显示主页
            $home.show();
            return;
        }
        // 显示当前做任务人信息
        inviteTaskInfo();
    }
    
    // 邀请人的任务信息
    function inviteTaskInfo(){
        Wechat_Util.getTaskInfo({
            data: {
                inviteOpenId: Wechat_Util.openid
            },
            succ: function(data){
                switch(data.errorCode){
                    case 1: // 显示二维码关注
                        break;
                    case 2: // 还没有人帮忙做任务
                        break;
                    case 0: $answer.show();
                            parseTaskToInvite(data.data); // 解析任务信息
                        break;
                }
            }
        })
    }
    function getRandom(m, n){
        return Math.round(Math.random() * (n - m) + m);
    }
    // 解析任务(邀请人)
    function parseTaskToInvite(arrInfo){
        var _scoreMap = {
            '-1': $("#temp_nostart").html(),
            '0': $("#temp_zero").html()
        }, _moneyMap = {
            '0': 0,
            '1': 5,
            '2': 13,
            '3': 23
        };
        var randomText = [$("#temp_score1").html(), $("#temp_score2").html(), $("#temp_score3").html()];
        randomText.sort(function(a, b){
            return Math.random()>.5 ? -1 : 1;
        })
        for(var i = 1; i <= randomText.length; i++){
            _scoreMap[i] = randomText[i - 1];
        }
        var result = [], totalMoney = 0, numZero = 0;
        for(var i = 0; i < arrInfo.length; i++){
            var item = arrInfo[i];
            result.push(_scoreMap[item.score].replace('{img}', item.taskHeadimgurl).replace('{x}', _moneyMap[item.score]));
            totalMoney += _moneyMap[item.score];
            numZero += item.score == 0? 1 : 0;
        }
        for(var k = 3; k > arrInfo.length; k--){
            var item = arrInfo[i];
            result.push(_scoreMap['-1']);
        }
        switch(arrInfo.length){
            case 3: showTaskResult(totalMoney, numZero);
                break;
            default: $findFriend.show();
                break;
        }
        $totalMoney.html(totalMoney);
        $tasklist.html(result.join(''));
    }

    function showTaskResult(totalMoney, numZero){
        var _titleMap = {
            '2': '最能帮你赚钱的朋友<br>已选出，<br>不请Ta吃顿饭吗？<br>恭喜获得{x}元红包！',
            '1': '不错哦，<br>你的朋友圈里还是<br>有一些真朋友的！<br>恭喜获得{x}元红包！',
            '0': '你的朋友们好棒！<br>个个是赚钱好帮手~<br>建议大家一起做点小生意！<br><span>{x}</span>元红包快拿去'
        };
        $finishTitle.html(_titleMap[numZero].replace('{x}', totalMoney));
        var res = Wechat_Util.getClickInfo(3); // 是否点击领取过兑奖码
        if(res === true){
            // 点击过，直接进入分钱页面
            if(numZero === 3){
                $finishZero.show();
            }else{
                getHBCode();
            }
        }else{
            // 没点击过，显示 我要领钱
            $getMoney.show();
        }
    }
    // 我要领钱 按钮事件
    $getMoney.on('click', function(){
        getHBCode();
    });

    function getHBCode(succ){
        $.post('/threeparty/code', { /*openId: Wechat_Util.openid*/ }, function(data){
            if(data.errorCode === 0){
                $("#hbcode").html(data.data.code);
                $answer.hide();
                $finish.show();
            }
        })
    }

    // 被邀请者的任务信息
    function invitedTaskInfo(){
        var userData = {
            taskOpenId: Wechat_Util.openid || '1'
        };
        var _invite_openid = Wechat_Util.getUrlParam('inviteopenid');
        if(_invite_openid){
            userData.inviteOpenId = _invite_openid;
        }
        Wechat_Util.getTaskInfo({
            data: userData,
            succ: function(data){
                //
            }
        })
    }

    
})