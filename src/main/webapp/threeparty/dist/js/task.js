$(function(){
    var inviteOpenid = Wechat_Util.getUrlParam('inviteopenid');
    
    // 判断当前活动状态 false－已结束
    if(!Wechat_Util.partyStatus()){
        alert('活动已结束！');
        return;
    }

    // 是否从公众号进入过
    var res = Wechat_Util.getClickInfo(2);
    if(res === false){
        // 显示二维码
        $(".share_to").show();
        return;
    }

    $(".invitehref").attr('href', '/index.html?openid=' + Wechat_Util.openid);
    Wechat_Util.getTaskInfo({
        succ: function(data){
            switch(data.errorCode){
                case 2: // 无人找你做任务
                    $(".no_call").removeClass('hide');
                    break;
                case 0: // 解析任务信息
                    var result = parseTaskToInvited(data);
                    break;
            }
            
        }
    });

    // 解析任务信息
    var currentIdx = 1, inviteNickname = '';
    function parseTaskToInvited(data){
        var arrInfo = data.data;
        inviteNickname = data.inviteNickname.length > 6? data.inviteNickname.substring(0, 5) + '...' : data.inviteNickname;
        var result = {
            inviteImg: data.inviteHeadimgurl,
            nickname: name,
            isIn: true, // 是否在做题人之列
            isOver: false,
            currentIdx: 1,  // 默认当前做题编号1
            isFull: arrInfo.length === 3 // 任务人是否已满
        }
        var num = 0;
        $("#wx_img").attr('src', result.inviteImg);
        for(var i = 0; i < arrInfo.length; i++){
            var item = arrInfo[i];
            if(item.taskOpenId === Wechat_Util.openid){
                result.status = item.status;
                result.score = item.score;
                if(item.status == 1 || item.score == "3"){
                    result.isOver = true; // 答题结束
                    result.currentIdx = item.score;
                }else{
                    result.currentIdx = currentIdx = item.score + 1;
                }
            }else{
                num++;
            }
        }
        // 是否在做题人之列
        result.isIn = arrInfo.length < 3? true : (num === 3)? false : true;
        
        // 如果已经做满且不在做题人之列，提示弹窗
        if(result.isFull && result.isIn === false){
            $(".more_than").removeClass('hide');
            return;
        }
        
        showTask(result);

        return result;
    }

    function showTask(result){
        console.log(result, 2)
        // 是否答题结束
        if(result.isOver){
            // 显示答题信息
            $(".questions").hide()
            $(".task_result").show();
            $(".wx_nickname").html(inviteNickname);
            $("#task_result_desc").html($("#task_result_desc_" + result.score + "_" + result.status).html().replace('{x}', inviteNickname));
            $(".task_result_" + result.score + "_" + result.status).removeClass('hide');
            return;
        }
        // 其余显示正在进行中的状态
        $(".questions").show();
        $(".qs_num").addClass('qs_num' + result.currentIdx);
        $("#num_title").html($("#num_title_" + result.currentIdx).html().replace('{invitename}', inviteNickname));
        $("#num1").html($("#answer1" + result.currentIdx).html());
        $("#num2").html($("#answer2" + result.currentIdx).html());
        $("#ul_qa>li").removeClass('active');
    }

    $("#num1, #num2").on('click', function(){
        $("#ul_qa>li").removeClass('active');
        $(this).closest('li').addClass('active');
    })
    $("#btnSubmitQa").on('click', function(){
        answerTask(currentIdx, $("li.active").index());
    })

    // 做任务（只要做过一题，就会被占用一个任务名额，后端据此处理）
    function answerTask(question, answer){
        $.ajax({
            url: '/threeparty/qa', 
            type: 'POST',
            data: {
                // question: question,
                // answer: answer,
                // taskOpenid: Wechat_Util.openid,
                // inviteOpenid: inviteOpenid
            }, 
            dataType: 'json',
            async: false,
            success: function(data){
                if(data.errorCode == 0){
                    var result = {
                        score: data.data.score,
                        status: data.data.status
                    };
                    if(data.data.status == 1 || data.data.score == 3){
                        result.isOver = true;
                    }else{
                        currentIdx += 1;
                        result.currentIdx = currentIdx;
                    }
                    showTask(result);
                } 
            }
        })
    }


})