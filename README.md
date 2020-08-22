# pomodoro
Personal pomodoro program

## Virtual Environment
* Run `python3 -m venv env`
* Then `. env/bin/activate`

## Future Improvements
* Program it, first of all

## To run JUnit tests (which as of Aug 22 2020 do nothing):
* Compile (in `src` folder):
```
javac -cp <path>/junit-4.13.jar:<path>/hamcrest-core-1.3.jar com/lmccrone/pomodoro_test/PomodoroTest.java
```
* Run:
```
java -cp .:<path>/junit-4.13.jar:<path>/hamcrest-core-1.3.jar org.junit.runner.JUnitCore com.lmccrone.pomodoro_test.PomodoroTest
```
