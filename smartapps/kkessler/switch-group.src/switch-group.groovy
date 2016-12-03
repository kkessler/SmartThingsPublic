/**
 *  Copyright 2016 Kenan Kessler
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Switch group
 *
 *  Author: kkessler
 *
 *  Date: 2016-12-02
 */
definition(
	name: "Switch group",
	namespace: "kkessler",
	author: "Kenan Kessler",
	description: "Control a collection of lights with any of those switches",
	category: "Convenience",
	iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/light_outlet.png",
	iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_outlet@2x.png"
)

preferences {
	section("Turn on or off all of these switches as well") {
		input "switches", "capability.switch", multiple: true, required: false
	}
}

def installed() {
	subscribe(switches, "switch", Handler)
}

def updated() {
	unsubscribe()
    installed()
}

def Handler(evt) {
	log.debug("trigger")
    if("${evt.value}" == "off"){
		switches?.off()
    }else{
		switches?.on()
    }
}
