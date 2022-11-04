#!/bin/bash

# 1st argument is the command to execute
# 2nd argument is the number of iterations. If not specified defaults to 1

# Example usage
# 1) Run the java app 10 times
# $ ./1strequest.sh "java -jar target/quarkus-app/quarkus-run.jar" 10
#
# 2) Run the native app 10 times
# $ ./1strequest.sh target/chapter-4-spring-data-jpa-1.0.0-SNAPSHOT-runner 10

COMMAND=$1
NUM_ITERATIONS=1
TOTAL_RSS=0
TOTAL_TTFR=0

if [ "$#" -eq 2 ]; then
  NUM_ITERATIONS=$2
fi

for (( i=0; i<$NUM_ITERATIONS; i++))
do
  ts=$(gdate +%s%N)
  $1 &
  pid=$!

  while ! (curl -sf http://localhost:8080/fruits > /dev/null)
  do
    sleep .2
    printf "."
  done

  TTFR=$((($(gdate +%s%N) - $ts)/1000000))
  RSS=`ps -o rss= -p $pid | sed 's/^ *//g'`
  TOTAL_RSS=$((TOTAL_RSS + RSS))
  TOTAL_TTFR=$((TOTAL_TTFR + TTFR))
  kill $pid
  wait $pid 2> /dev/null
done

echo
echo
echo
echo "-------------------------------------------------"
printf "AVG RSS (after 1st request): %.1f MB\n" $(echo "$TOTAL_RSS / $NUM_ITERATIONS / 1024" | bc -l)
printf "AVG time to first request: %.3f sec\n" $(echo "$TOTAL_TTFR / $NUM_ITERATIONS / 1000" | bc -l)
echo "-------------------------------------------------"