export CATALINA_OPTS="$CATALINA_OPTS -server -Xms256m -Xmx1024m -XX:PermSize=512m -XX:MaxPermSize=512m"
export JAVA_OPTS="$JAVA_OPTS -javaagent:/killbill/jolokia-jvm-1.3.4-agent.jar=port=7777,host=localhost"
