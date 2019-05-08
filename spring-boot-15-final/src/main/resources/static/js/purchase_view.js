var seckill = {
    URL: {
        now: function () {
            return '/time/now';
        },
        exposer: function (seckillId) {
            return '/' + seckillId + '/exposer';
            // return '/test';
        },
        execute: function (seckillId, md5) {
            return '/' + seckillId + '/' + md5 + '/execution';
        }
    },
    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    //时间一致性计算
    countdown: function (seckillId, nowTime, startTime, endTime, money) {
        var seckillBox = $('#seckill-box');
        var seckillTimeSpan = $('#seckill-time-span');
        //判断时间
        if (nowTime > endTime) {
            //抢购活动结束
            seckillTimeSpan.html('抢购活动结束');
            seckillBox.hide();
        } else if (nowTime < startTime) {
            //抢购活动未开始，时间事件绑定
            var killTime = new Date(startTime + 1000);
            seckillTimeSpan.countdown(killTime, function(event) {
                //format of time
                var format = event.strftime('活动开始倒计时： %D天 %H时 %M分 %S秒');
                seckillTimeSpan.html(format);
                //时间结束后执行回调函数
            }).on('finish.countdown', function () {
                //曝光抢购地址，控制实现逻辑，执行抢购函数
                seckill.handleSeckill(seckillId, seckillBox, money);
            });
        } else {
            //秒杀开始
            // window.location.reload();
            seckill.handleSeckill(seckillId, seckillBox, money);

            //时间开始
            var killEndTime = new Date(endTime + 1000);
            seckillTimeSpan.countdown(killEndTime, function (event) {
                //format of time
                var format = event.strftime('距离秒杀结束： %D天 %H时 %M分 %S秒');
                seckillTimeSpan.html(format);
            });
        }
    },
    //处理抢购业务逻辑
    handleSeckill: function (seckillId, node, money) {
        //获取秒杀地址，控制显示逻辑，执行抢购函数体
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始抢购</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //在返回的函数中执行交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开启抢购
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execute(seckillId, md5);
                    console.log('killUrl:' + killUrl);
                    //绑定  once:
                    $('#killBtn').one('click', function () {
                        //执行抢购的操作
                        //1. 现禁用抢购button
                        $(this).addClass('disable');
                        //2. 发送抢购url请求，执行后台函数
                        $.post(killUrl, {money: money}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var stateInfo = killResult['stateInfo'];
                                //3. 返回数据后在前端显示抢购的结果
                                node.html('<span class="label label-success" style="background-color: #c7254e">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {
                    //抢购未开启，避免正在等待的用户得到的时间存在偏差
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countdown(seckillId, now, start, end);
                }
            } else {
                console.log('result' + result);
            }
        });
    },

    //view页面抢购函数
    detail: {
        //view页init
        init: function (params) {
            //1. 手机号验证
            //cookie中查询用户
            var killPhone = $.cookie('killPhone');
            //验证
            if (!seckill.validatePhone(killPhone)) {
                //绑定
                var killPhoneModal = $('#killPhoneModal');
                // killPhoneModal.modal({
                //     show: true,
                //     backdrop: 'static', //close
                //     keyboard: false     //关闭键盘
                // });
                $("#killPhoneModal").modal({
                    show: true,
                    backdrop: 'static',
                    keyboard: false
                })
                $("#killPhoneBtn").click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        //input cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/'});
                        window.location.reload();
                    } else {
                        $("#killPhoneMessage").hide().html('<lable class="label label-danger">error message: Useless cell！</lable>').show(300);
                    }
                });
            }
            //已登录的
            //时间对齐
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            var money = params['money'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    seckill.countdown(seckillId, nowTime, startTime, endTime, money);
                } else {
                    console.log('result' + result);
                }
            });

        }
    }
}