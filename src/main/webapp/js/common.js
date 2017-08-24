var Wechat_Util = {
    openid: '',
    shareContent: {
        invite: {
            title: '有件事儿，想问你很久了…',
            description: '感情深不深，就看这次了！',
            url: 'https://events.huli.com/static/redpacket/',
            imageUrl:'https://events.huli.com/static/web/common/weShare.jpeg'
        },
        shareMoney: {
            title: '有钱当然要大家一起分！',
            description: '点击抢红包',
            url: 'https://events.huli.com/static/redpacket/',
            imageUrl:'https://events.huli.com/static/web/common/weShare.jpeg'
        }
    },
    getUrlParam: function(param){
        var reg_param = new RegExp("(^|&)" + param + "=([^&]*)(&|$)");
        var arr = window.location.search.substr(1).match(reg_param);
        if (arr && arr.length >= 2) {
            return arr[2];
        }else{
            return '';
        }
    },
    partyStatus: function(){
        var res = false;
        $.ajax({
            url: '/threeparty/status',
            type: 'POST',
            dataType: 'json',
            async: false,
            success: function(data){
                res = data.data && data.data.currentStatus == 1;
            }
        })
        return res;
    },
    getClickInfo: function(type){
        var res = false;
        $.ajax({
            url: '/threeparty/income', 
            type: 'POST',
            data: {
                // openid: Wechat_Util.openid,
                // type: type
            }, 
            dataType: 'json',
            async: false,
            success: function(data){
                res = data.success;
            }
        })
        return res;
    },
    // 获取任务信息
    getTaskInfo: function(options){
        $.ajax({
            url: '/threeparty/task_info', 
            type: 'POST',
            // data: options.data, 
            dataType: 'json',
            async: false,
            success: function(data){
                options.succ && options.succ(data);
            }
        })
    }
};
Wechat_Util.openid = Wechat_Util.getUrlParam('openid') || 1;
Wechat_Util.openid = 'wD3EXbEdGf5EW7vo1';

Wechat_Util.wxShare = function (options) {
    
    var wxConfig = options.wxConfig;
    var wx_timeline = {
        title: wxConfig.title,
        link: wxConfig.url,
        imgUrl: wxConfig.imageUrl,
        success: function() {
            options.timeline_succ && options.timeline_succ(1);
        },
        fail:function(){
            options.timeline_fail && options.timeline_fail(0);
        }
    };
    var wx_friend = {
        title: wxConfig.title,
        link: wxConfig.url,
        imgUrl: wxConfig.imageUrl,
        desc: wxConfig.description,
        type: 'link',
        success: function() {
            options.friend_succ && options.friend_succ(1);
        },
        fail:function(){
            options.friend_fail && options.friend_fail(0);
        }
    };
    $.ajax({
        url: 'https://weixin.souyidai.com/serve/jssign?url='+ encodeURIComponent(window.location.href.split('#')[0]),
        type:'get',
        dataType: 'json',
        success:function(data){
            if(data.errorCode===0){
                wx.config(data.result);
                //微信分享注入
                wx.ready(function(){
                    // 朋友圈
                    wx.onMenuShareTimeline(wx_timeline);
                    // 分享给好友
                    wx.onMenuShareAppMessage(wx_friend);
                    // hide
                    wx.hideMenuItems({
                        menuList:['menuItem:share:qq', 'menuItem:share:weiboApp']
                    })
                });
            }
        },
        error:function(data){
        }
    });

}

//分享的统计方法
Wechat_Util.shareInfo = function(options){
    var oResult = {
        '1': '［index］［朋友圈转发］成功',
        '2': '［index］［微信好友转发］成功',
        '3': '［index］测试面相',
        '4': '［main］调起注册弹层',
        '5': '［main］调起再拍一张弹层',
        '6': '［main］调起服务器返回错误弹层',
        '7': '［main］调起二维码识别弹层',
        '8': '［main］调起loading弹层',
        '9': '［main］点击领取红包，引导分享弹层',
        '10': '［main］输入手机号，领取红包按钮事件',
        '11': '［index］［微信好友转发］取消',
        '12': '［index］［朋友圈转发］取消',
        '13': '［main］［朋友圈转发］成功',
        '14': '［main］［微信好友转发］成功',
        '15': '［main］［微信好友转发］取消',
        '16': '［main］［朋友圈转发］取消',
        '17': '［main］老用户领取红包成功弹层'
    };
    options.module = '找最具价值的朋友';
    options.result = options.result || oResult[options.type];
    options.openid = options.openid;
    options.remark = options.remark || '［找最具价值的朋友活动］';
    options.url = location.href;
    options.comeFrom = 1; // wechat

    $.post('/threeparty/shareinfo', options, function(data){
        // alert(data.errorCode);
    })
};

Wechat_Util.clickInfo = function(options){
    options.openid = options.openid;
    options.mobile = '';
    options.type = 1;  //此次活动用到两种：1－邀请者分享成功过该任务；2-用户通过公众号进入过（node写入）
    options.result = options.result || false;
    options.remark = '';
    $.post('/threeparty/clickinfo', options, function(data){});
};
