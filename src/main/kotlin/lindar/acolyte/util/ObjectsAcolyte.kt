package lindar.acolyte.util

import mu.KotlinLogging
import java.lang.reflect.InvocationTargetException
import java.util.*
import kotlin.collections.ArrayList

class ObjectsAcolyte {
    companion object {

        private val SET_METHOD_PREFIX = "set"
        private val GET_METHOD_PREFIX = "get"
        private val IS_METHOD_PREFIX = "is"

        private val logger = KotlinLogging.logger {}


        /**
         * Go through all the setters of the second object and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
         * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
         * Ignores every other method that is NOT public, setter or getter.

         * On top of this there might be cases when you want to have override set to true but skip certain variables like the **id**.
         * You can do so by providing a list of string with the variables names. For this use the other overloaded method

         * Returns the second object with the new values

         * Default: override is set to true

         * @param firstObject
         * *
         * @param secondObject
         * *
         * @return Returns the second object with the new values
         */
        @JvmStatic
        fun <T : Any> copy(firstObject: Any, secondObject: T): T {
            return copy(firstObject, secondObject, true, ArrayList<String>(0))
        }

        /**
         * Go through all the setters of the second object and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
         * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
         * Ignores every other method that is NOT public, setter or getter.

         * On top of this there might be cases when you want to have override set to true but skip certain variables like the **id**.
         * You can do so by providing a list of string with the variables names. For this use the other overloaded method

         * Returns the second object with the new values

         * @param firstObject
         * *
         * @param secondObject
         * *
         * @param override
         * *
         * @return Returns the second object with the new values
         */
        @JvmStatic
        fun <T : Any> copy(firstObject: Any, secondObject: T, override: Boolean): T {
            return copy(firstObject, secondObject, override, ArrayList<String>(0))
        }

        /**
         * Go through all the setters of the second object and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
         * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
         * Ignores every other method that is NOT public, setter or getter.

         * On top of this there might be cases when you want to have override set to true but skip certain variables like the **id**.
         * You can do so by providing a list of string with the variables names.

         * Returns the second object with the new values

         * @param firstObject
         * *
         * @param secondObject
         * *
         * @param override
         * *
         * @param skipVariables
         * *
         * @return Returns the second object with the new values
         */
        @JvmStatic
        fun <T : Any> copy(firstObject: Any, secondObject: T, override: Boolean, skipVariables: List<String>): T {

            val firstObjMethods = firstObject.javaClass.methods
            val secondObjMethods = secondObject.javaClass.methods

            for (secondObjMethod in secondObjMethods) {
                val secondObjMethodName = secondObjMethod.name
                val secondObjStrippedMethodName: String
                if (secondObjMethodName.startsWith(SET_METHOD_PREFIX)) {
                    secondObjStrippedMethodName = secondObjMethodName.substring(secondObjMethodName.indexOf(SET_METHOD_PREFIX) + SET_METHOD_PREFIX.length)
                } else {
                    continue
                }
                val secondObjMethodParamTypes = secondObjMethod.parameterTypes
                if (secondObjMethodParamTypes.size != 1) {
                    continue
                }
                for (firstObjMethod in firstObjMethods) {
                    val firstObjMethodName = firstObjMethod.name
                    val firstObjStrippedMethodName: String
                    var booleanMethod = false
                    if (firstObjMethodName.startsWith(GET_METHOD_PREFIX)) {
                        firstObjStrippedMethodName = firstObjMethodName.substring(firstObjMethodName.indexOf(GET_METHOD_PREFIX) + GET_METHOD_PREFIX.length)
                    } else if (firstObjMethodName.startsWith(IS_METHOD_PREFIX)) {
                        booleanMethod = true
                        firstObjStrippedMethodName = firstObjMethodName.substring(firstObjMethodName.indexOf(IS_METHOD_PREFIX) + IS_METHOD_PREFIX.length)
                    } else {
                        continue
                    }
                    if (sameNameAndType(firstObjStrippedMethodName, secondObjStrippedMethodName, firstObjMethod.returnType.canonicalName, secondObjMethodParamTypes[0].canonicalName)
                            || sameNameAndEnumAndStringTypes(firstObjStrippedMethodName, secondObjStrippedMethodName, firstObjMethod.returnType.isEnum, secondObjMethodParamTypes[0].canonicalName)) {
                        try {
                            val firstObjMethodReturnValue = firstObjMethod.invoke(firstObject)

                            // if we don't want to override then we check secondObjMethodReturnValue and if not null (a value exists) then we move on without changing the value
                            if (!override || ListsAcolyte.containsIgnoreCase(skipVariables, firstObjStrippedMethodName)) {
                                try {
                                    val secondObjMethodReturnValue: Any?
                                    if (booleanMethod) {
                                        secondObjMethodReturnValue = secondObject.javaClass.getMethod(IS_METHOD_PREFIX + secondObjStrippedMethodName).invoke(secondObject)
                                    } else {
                                        secondObjMethodReturnValue = secondObject.javaClass.getMethod(GET_METHOD_PREFIX + secondObjStrippedMethodName).invoke(secondObject)
                                    }
                                    if (!objectNullOrEmpty(secondObjMethodReturnValue)) {
                                        continue
                                    }
                                } catch (ex: NoSuchMethodException) {
                                    logger.error { ex }
                                } catch (ex: SecurityException) {
                                    logger.error { ex }
                                }
                            }
                            if (enumAndStringTypes(firstObjMethod.returnType.isEnum, secondObjMethodParamTypes[0].canonicalName)) {
                                secondObjMethod.invoke(secondObject, firstObjMethodReturnValue.toString())
                            } else {
                                secondObjMethod.invoke(secondObject, firstObjMethod.returnType.cast(firstObjMethodReturnValue))
                            }
                        } catch (ex: IllegalAccessException) {
                            logger.error { ex }
                        } catch (ex: IllegalArgumentException) {
                            logger.error { ex }
                        } catch (ex: InvocationTargetException) {
                            logger.error { ex }
                        }
                    }
                }
            }

            return secondObject
        }

        fun sameNameAndType(firstObjMethodName: String, secondObjMethodName: String,
                                     firstObjMethodReturnClass: String, secondObjParamClass: String) : Boolean {
            return secondObjMethodName == firstObjMethodName && secondObjParamClass == firstObjMethodReturnClass
        }

        fun sameNameAndEnumAndStringTypes(firstObjMethodName: String, secondObjMethodName: String,
                isFirstObjMethodReturnTypeEnum: Boolean, secondObjMethodParamType: String): Boolean {
            return secondObjMethodName == firstObjMethodName &&
                    enumAndStringTypes(isFirstObjMethodReturnTypeEnum, secondObjMethodParamType)
        }

        fun enumAndStringTypes(isFirstObjMethodReturnTypeEnum: Boolean, secondObjMethodParamType: String): Boolean {
            return isFirstObjMethodReturnTypeEnum && secondObjMethodParamType == String::class.java.canonicalName
        }

        /**
         * Create an object of the second object class and go through all the setters and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
         * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
         * Ignores every other method that is NOT public, setter or getter.

         * On top of this there might be cases when you want to have override set to true but skip certain variables like the **id**.
         * You can do so by providing a list of string with the variables names.

         * Returns the created second object with the new values.

         * @param firstObject
         * *
         * @param secondObjectClass
         * *
         * @param override
         * *
         * @param skipVariables
         * *
         * @return Returns the created second object with the new values
         */
        @JvmStatic
        fun <T : Any> copy(firstObject: Any, secondObjectClass: Class<T>, override: Boolean, skipVariables: List<String>): Optional<T> {
            try {
                val secondObject = secondObjectClass.newInstance()
                return Optional.of(copy(firstObject, secondObject, override, skipVariables))
            } catch (ex: InstantiationException) {
                logger.error { ex }
            } catch (ex: IllegalAccessException) {
                logger.error { ex }
            }

            return Optional.empty<T>()
        }

        /**
         * Create an object of the second object class and go through all the setters and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
         * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
         * Ignores every other method that is NOT public, setter or getter.

         * On top of this there might be cases when you want to have override set to true but skip certain variables like the **id**.
         * You can do so by providing a list of string with the variables names. For this use the other overloaded method

         * Returns the created second object with the new values.

         * @param firstObject
         * *
         * @param secondObjectClass
         * *
         * @param override
         * *
         * @return Returns the created second object with the new values
         */
        @JvmStatic
        fun <T : Any> copy(firstObject: Any, secondObjectClass: Class<T>, override: Boolean): Optional<T> {
            return copy(firstObject, secondObjectClass, override, ArrayList<String>(0))
        }


        /**
         * This method returns all public variables that have a getter including the
         * inherited ones.
         * This algorithm strips down the GET prefix or the IS
         * prefix for a boolean variable and then uncapitalizes the name to be
         * camel-case.
         * If you would like to skip/ignore any variable names then use the other method.

         * @param <T>
         * *
         * @param object
         * *
         * @return a list of public variable names that have a getter (including
         * * booleans)
        </T> */
        @JvmStatic
        fun <T : Any> listAllVariablesWithGetters(`object`: T): List<String> {
            return listAllVariablesWithGetters(`object`, null)
        }

        /**
         * This method returns all public variables that have a getter including the
         * inherited ones.
         * This algorithm strips down the GET prefix or the IS
         * prefix for a boolean variable and then uncapitalizes the name to be
         * camel-case.
         * This method also ignores the getClass method defined by Object.java so it won't return class as one of the variables.

         * @param <T>
         * *
         * @param object
         * *
         * @return a list of public variable names that have a getter (including booleans)
        </T> */
        @JvmStatic
        fun <T : Any> listAllVariablesWithGettersIgnoreGetClass(`object`: T): List<String> {
            return listAllVariablesWithGetters(`object`, listOf("class"))
        }

        /**
         * This method returns all public variables that have a getter including the
         * inherited ones.
         * This algorithm strips down the GET prefix or the IS
         * prefix for a boolean variable and then uncapitalizes the name to be
         * camel-case.
         * You can also provide a list of variable names that you would like the algorithm to ignore/skip.

         * @param <T>
         * *
         * @param object
         * *
         * @param namesToIgnore
         * *
         * @return a list of public variable names that have a getter (including
         * * booleans)
        </T> */
        @JvmStatic
        fun <T : Any> listAllVariablesWithGetters(`object`: T, namesToIgnore: List<String>?): List<String> {
            val objMethods = `object`.javaClass.methods
            val names = ArrayList<String>(objMethods.size)
            for (firstObjMethod in objMethods) {
                val objMethodName = firstObjMethod.name
                val objStrippedMethodName: String
                if (objMethodName.startsWith(GET_METHOD_PREFIX)) {
                    objStrippedMethodName = objMethodName.substring(objMethodName.indexOf(GET_METHOD_PREFIX) + GET_METHOD_PREFIX.length)
                } else if (objMethodName.startsWith(IS_METHOD_PREFIX)) {
                    objStrippedMethodName = objMethodName.substring(objMethodName.indexOf(IS_METHOD_PREFIX) + IS_METHOD_PREFIX.length)
                } else {
                    continue
                }
                val uncapitalizedName = objStrippedMethodName.decapitalize()
                if (namesToIgnore == null || !namesToIgnore.contains(uncapitalizedName)) {
                    names.add(uncapitalizedName)
                }
            }
            return names
        }

        @JvmStatic
        fun objectNullOrEmpty(`object`: Any?): Boolean {
            if (`object` == null) {
                return true
            }
            if (`object` is Collection<*>) {
                return `object`.isEmpty()
            }
            return false
        }
    }
}
