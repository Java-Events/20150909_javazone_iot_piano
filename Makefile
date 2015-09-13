
TARGET=trgt
MAIN_JAVA_FILE=IotPiano.java
MAIN_CLASS_FILE=$(MAIN_JAVA_FILE:%.java=%.class)

install: ${TARGET} ${TARGET}/Tinkerforge.jar ${TARGET}/Jfugue.jar ${MAIN_CLASS_FILE}


${TARGET}/Tinkerforge.jar: 
	wget -O ${TARGET}/tinkerforge.zip http://download.tinkerforge.com/bindings/java/tinkerforge_java_bindings_2_1_5.zip
	unzip ${TARGET}/tinkerforge.zip -d ${TARGET}
	rm ${TARGET}/tinkerforge.zip

${TARGET}/Jfugue.jar: 
	wget -O $@ http://www.jfugue.org/jfugue-5.0.1.jar

${TARGET}:
	mkdir -p $@

IotPiano.class:
	javac -classpath .:${TARGET}/Jfugue.jar:${TARGET}/Tinkerforge.jar ${MAIN_JAVA_FILE}


clean: 
	rm -rf ${TARGET}
	rm ${MAIN_CLASS_FILE}


run: install
	java -cp .:trgt/Jfugue.jar:trgt/Tinkerforge.jar $(MAIN_JAVA_FILE:%.java=%)

.PHONY: clean run
