#!/bin/sh
source ./env.sh
touch PID
PID=`cat PID`
if [ -n "$PID" ];then
    PIDPROC=`ps -ef | grep \$PID | grep -v 'grep'| awk '{print $2}'`
    if [ -z "$PIDPROC" ];then
        echo "${APP_NAME} is not running"
        exit 0
    fi
else
    PIDPROC=`ps -ef | grep \$APP_NAME | grep -v 'grep'| awk '{print $2}'`
    if [ -z "$PIDPROC" ];then
        echo "${APP_NAME} is not running"
        exit 0
    fi     
fi
if kill $PIDPROC
    then echo "process $APP_NAME(Pid:$PIDPROC) was stopped at" `date`
fi
echo stop service finished
echo > PID
