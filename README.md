# acolyte
Acolyte is a utility library, used by entire Lindar ecosystem, that helps developers reach their goals and dreams a wee bit faster. Try it yourself today!

Util classes: 

- ObjectsUtil: 
    - **copy**: copies two objects by copying the variables with same name and type. This is useful when you want to create a clone of the database model but one that matches your view's needs.
    - **listAllVariablesWithGetters**: returns all public variables that have a getter including the inherited ones
    - **objectNullOrEmpty**: check if an object is null and if collection and not null it will also check if it's empty
    - **listContainsIgnoreCase**: check if list contains a string param and ignores the case
    - **listIsEmpty** and **listIsNotEmpty**: check if a list is null and empty or not null and not empty
    - **numberNullOrZero** and **numberGreaterThanZero**: check if number is null or equal to zero or number not null and greater than zero. Supports any number (extends Number) including BigDecimal, BigInteger, etc
    
- NumberFormatterUtil: helpful with formatting numbers and money
- PasswordValidator
- UsernameValidator

Usage: 

```xml
<dependency>
    <groupId>com.lindar</groupId>
    <artifactId>acolyte</artifactId>
    <version>1.0.1</version>
</dependency>
```
