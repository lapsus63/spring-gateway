# spring-gateway
A simple GET gateway accessible from outside to control LAN things or VirtualSmartHome switches.

# Architecture

![rest-gw-flows.drawio.png](doc%2Frest-gw-flows.drawio.png)

# Connection with Alexa

Add skill `URL Switch` to your Alexa account, then configure your 'switches' from the [plugin website](https://www.virtualsmarthome.xyz/url_switch).
The switches will be recognized automatically by Alexa, you just have to tell `switch on`, `switch off` or `trigger routine` your switch-name.

# How to build

### Prerequisites

- Java 17 installed
- Maven installed

### Build steps

```bash
export JAVA_HOME=$(locate java | grep -e 'jdk.*17.*bin/java$'  | sed 's#/bin/java##')
mvn clean install
# mvn spring-boot:build-image
```