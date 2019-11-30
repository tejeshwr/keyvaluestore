#!/bin/bash
##########################################################################
# This key value store  bash shell script takes the input from the user  #
# , validates it and then starts the opted process.                      #
# Usage : sh <full_path_of_this_script>                                  #
##########################################################################

if [ -z "$1" ]
then
echo "Usage:keyValueStore <filepath> [<operation>]"
echo "Note:operation can be -c or -d or -g: -c is Create,-d is Delete, -g is GetValue"
else
java -Dlogback.configurationFile=logback.xml -cp com.tejeshwr.keyvalue.store.main.StoreMain $1 $2
fi
