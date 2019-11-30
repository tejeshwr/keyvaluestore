# keyvaluestore

Usage:keyValueStore \<filepath\> \[operation\]  <br/>
Note:operation can be -c or -d or -g: -c is Create,-d is Delete, -g is GetValue

# Download or clone the module and build it

mvn clean install

# How to invoke the Main class
java -cp keyvaluestore-0.0.1-SNAPSHOT.jar com.tejeshwr.keyvalue.store.main.StoreMain $1 $2
