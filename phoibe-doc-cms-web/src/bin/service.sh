#!/bin/sh

BASEDIR=`dirname $0`/..
BASEDIR=`(cd "$BASEDIR"; pwd)`

# If a specific java binary isn't specified search for the standard 'java' binary
if [ -z "$JAVACMD" ] ; then
  if [ -n "$JAVA_HOME"  ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVACMD="$JAVA_HOME/jre/sh/java"
    else
      JAVACMD="$JAVA_HOME/bin/java"
    fi
  else
    JAVACMD=`which java`
  fi
fi

CLASSPATH="$JAVA_HOME"/lib/tools.jar

if [ ! -x "$JAVACMD" ] ; then
  echo "Error: JAVA_HOME is not defined correctly."
  echo "  We cannot execute $JAVACMD"
  exit 1
fi

#Java虚拟机参数
JAVA_OPTS="-server -Xms2G -Xmx2G -XX:MaxPermSize=256M -Xss256K -XX:+UseConcMarkSweepGC -XX:CMSFullGCsBeforeCompaction=5 -XX:+UseCMSCompactAtFullCollection -XX:CMSInitiatingOccupancyFraction=80 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs/ "
#Java远程调试参数
JAVA_DEBUG_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,suspend=n,server=y,address=5005"

#引用环境变量
source ./env.sh

exec "$JAVACMD" $JAVA_OPTS \
  -Dbasedir="$BASEDIR" \
  -Dfile.encoding="UTF-8" \
  -Dapp.name="$APP_NAME" \
  -jar ../app.jar
