Starting of hub


//Start Hub
java -jar selenium-server-standalone-3.141.59.jar -role hub

//Start node
java -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5557


//Start node with firefox
java -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5556 -browser browserName="firefox"

//Start node with instance and session on firefox
java -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5559 -browser browserName=firefox,maxInstances=5,maxSession=2

//Start node on Chrome
java -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5558 -browser browserName=chrome


//Start node with instance and session on chrome
java-jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5560 -browser browserName=chrome,maxInstances=5,maxSession=2

