compile:
	@javac server/RemoteStore.java
	@javac client/Main.java
clean:
	@find . -name '*.class' -delete

createJar:
	@jar cf store.jar $(shell find . -name '*.class' -printf '%P ')

runServer:
	@java -cp store.jar server.RemoteStore

runClient:
	@java -cp store.jar client.Main