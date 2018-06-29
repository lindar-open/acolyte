package lindar.acolyte.util

import mu.KotlinLogging
import java.lang.reflect.InvocationTargetException

class ObjectsAcolyte {
    companion object {

        private val PRIMITIVES_TO_WRAPPERS = mapOf(
                Boolean::class.javaPrimitiveType to Boolean::class.javaObjectType,
                Byte::class.javaPrimitiveType to Byte::class.javaObjectType,
                Char::class.javaPrimitiveType to Char::class.javaObjectType,
                Double::class.javaPrimitiveType to Double::class.javaObjectType,
                Float::class.javaPrimitiveType to Float::class.javaObjectType,
                Int::class.javaPrimitiveType to Int::class.javaObjectType,
                Long::class.javaPrimitiveType to Long::class.javaObjectType,
                Short::class.javaPrimitiveType to Short::class.javaObjectType)

        private val SET_METHOD_PREFIX = "set"
        private val GET_METHOD_PREFIX = "get"
        private val IS_METHOD_PREFIX = "is"

        private val logger = KotlinLogging.logger {}

        private fun <T> wrap(c: Class<T>): Class<T> {
            return if (c.isPrimitive) PRIMITIVES_TO_WRAPPERS[c] as Class<T> else c
        }


        @JvmStatic
        fun <T : Any> copy(fromObject: Any, toObject: T): T {
            return copy(fromObject, toObject, true, ArrayList<String>(0))
        }

        @JvmStatic
        fun <T : Any> copy(fromObject: Any, toObject: T, override: Boolean): T {
            return copy(fromObject, toObject, override, ArrayList<String>(0))
        }

        /**
         * Go through all the setters of the second object and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
         * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
         * Ignores every other method that is NOT public, setter or getter.
         * On top of this there might be cases when you want to have override set to true but skip certain variables like the **id**.
         * You can do so by providing a list of string with the variables names.
         * Returns the second object with the new values
         * @return Returns the second object with the new values
         */
        @JvmStatic
        fun <T : Any> copy(fromObject: Any, toObject: T, override: Boolean = true, skipVariables: List<String> = ArrayList()): T {

            val firstObjMethods = fromObject.javaClass.methods
            val secondObjMethods = toObject.javaClass.methods

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
                            val firstObjMethodReturnValue = firstObjMethod.invoke(fromObject)

                            // if we don't want to override then we check secondObjMethodReturnValue and if not null (a value exists) then we move on without changing the value
                            if (!override || ListsAcolyte.containsIgnoreCase(skipVariables, firstObjStrippedMethodName)) {
                                try {
                                    val secondObjMethodReturnValue = if (booleanMethod) {
                                        toObject.javaClass.getMethod(IS_METHOD_PREFIX + secondObjStrippedMethodName).invoke(toObject)
                                    } else {
                                        toObject.javaClass.getMethod(GET_METHOD_PREFIX + secondObjStrippedMethodName).invoke(toObject)
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
                                secondObjMethod.invoke(toObject, firstObjMethodReturnValue.toString())
                            } else {
                                secondObjMethod.invoke(toObject, wrap(firstObjMethod.returnType).cast(firstObjMethodReturnValue))
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

            return toObject
        }

        fun sameNameAndType(firstObjMethodName: String, secondObjMethodName: String,
                            firstObjMethodReturnClass: String, secondObjParamClass: String): Boolean {
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
         * This method returns all public variables that have a getter including the
         * inherited ones.
         * This algorithm strips down the GET prefix or the IS
         * prefix for a boolean variable and then uncapitalizes the name to be
         * camel-case.
         * If you would like to skip/ignore any variable names then use the other method.
         * @return a list of public variable names that have a getter (including
         * * booleans)
        */
        @JvmStatic fun <T : Any> listAllVariablesWithGetters(`object`: T): List<String> {
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
        @JvmStatic fun <T : Any> listAllVariablesWithGettersIgnoreGetClass(`object`: T): List<String> {
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
        @JvmStatic fun <T : Any> listAllVariablesWithGetters(`object`: T, namesToIgnore: List<String>?): List<String> {
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

        @JvmStatic fun objectNullOrEmpty(`object`: Any?): Boolean {
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