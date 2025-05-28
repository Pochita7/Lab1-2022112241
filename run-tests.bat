@echo off
echo 正在编译Java文件...
javac -d out src\main\java\Lab1Experiment1.java
javac -cp "lib\junit-jupiter-api-5.9.2.jar;lib\opentest4j-1.2.0.jar;lib\apiguardian-api-1.1.2.jar;out" -d out src\test\java\Lab1Experiment1Test.java

echo.
echo 正在运行JUnit测试...
java -jar lib\junit-platform-console-standalone-1.9.2.jar --classpath out --select-class Lab1Experiment1Test

echo.
echo 测试完成！
pause
