# IoT-Piano
This is a small code snippet put together during the Tinkerforge IOT workshop held By Sven Ruppert at [JavaZone 2015](http://2015.javazone.no/).
The code turns the multi touch bricklet into a mini-piano. The octaves change relative to the lightning perceived by the ambient light bricklet.

Testing requires the following Tinkerforge brick(lets)
* [ambient light bricklet](http://www.tinkerforge.com/en/doc/Hardware/Bricklets/Ambient_Light.html)
* [multi touch bricklet]( http://www.tinkerforge.com/en/doc/Hardware/Bricklets/Multi_Touch.html)
* [master brick](http://www.tinkerforge.com/en/doc/Primer.html#primer-bricks)


### Running the program

Make sure you have the *Java Developer Kit (JDK)* installed. The code has been tested on JDK 8, update 60, but JDK 7 should work just fine.  Download from the [Oracle web site](http://www.oracle.com/technetwork/java/javase/downloads/index.html) if you don't have JDK 8 installed.

Now, follow the steps below:

* Connect the [ambient light bricklet](http://www.tinkerforge.com/en/doc/Hardware/Bricklets/Ambient_Light.html) and the [multi touch bricklet]( http://www.tinkerforge.com/en/doc/Hardware/Bricklets/Multi_Touch.html) to your computer via the [master brick](http://www.tinkerforge.com/en/doc/Primer.html#primer-bricks). If you're unsure on how to do this, head over to [Tinkerforge](http://www.tinkerforge.com/en/doc/Tutorials/Tutorial_Extending/Tutorial.html#tutorial-first-steps) for a tutorial

* Download this repository to your computer, open IotPiano.java and change **ABIEND_UID** and **KEYPAD_UID** to macth the corresponding uid shown in the Brick viewer program

* Download and extract [Tinkerforge.jar, version 2.1.5]( http://download.tinkerforge.com/bindings/java/tinkerforge_java_bindings_2_1_5.zip)

* Download [jfugue-5.0.1.jar](http://www.jfugue.org/jfugue-5.0.1.jar)

* Put the two jar files in the same directory as IotPiano.java and run the following commands:
##### Windows
```cmd
  javac -classpath .;jfugue-5.0.1.jar;Tinkerforge.jar IotPiano.java

  java -cp .;jfugue-5.0.1.jar;Tinkerforge.jar IotPiano
```
##### Linux/Mac OS
```bash
  javac -classpath .:jfugue-5.0.1.jar:Tinkerforge.jar IotPiano.java

  java -cp .:jfugue-5.0.1.jar:Tinkerforge.jar IotPiano
```
 * Play some tunes on the multi touch bricklet!

*PS:*
This repository includes a Makefile for linux users. Just type **make run** in your terminal to download the two libraries, compile IotPiano.java and run the program.

