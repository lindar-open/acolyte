package lindar.acolyte.util;

import com.google.common.collect.Lists;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public class ObjectsUtil {

    private static final String SET_METHOD_PREFIX = "set";
    private static final String GET_METHOD_PREFIX = "get";
    private static final String IS_METHOD_PREFIX = "is";

    private ObjectsUtil() {
    }
    
    
    /**
     * Go through all the setters of the second object and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
     * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
     * Ignores every other method that is NOT public, setter or getter.
     * 
     * On top of this there might be cases when you want to have override set to true but skip certain variables like the <b>id</b>. 
     * You can do so by providing a list of string with the variables names. For this use the other overloaded method
     * 
     * Returns the second object with the new values
     * 
     * @param firstObject
     * @param secondObject
     * @param override
     * @return Returns the second object with the new values
     */
    public static <T> T copyObjectsIfMatch(Object firstObject, T secondObject, boolean override) {
        return copyObjectsIfMatch(firstObject, secondObject, override, new ArrayList<>(0));
    }

    /**
     * Go through all the setters of the second object and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
     * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
     * Ignores every other method that is NOT public, setter or getter.
     * 
     * On top of this there might be cases when you want to have override set to true but skip certain variables like the <b>id</b>. 
     * You can do so by providing a list of string with the variables names.
     * 
     * Returns the second object with the new values
     * 
     * @param firstObject
     * @param secondObject
     * @param override
     * @param skipVariables
     * @return Returns the second object with the new values
     */
    public static <T> T copyObjectsIfMatch(Object firstObject, T secondObject, boolean override, List<String> skipVariables) {

        Method[] firstObjMethods = firstObject.getClass().getMethods();
        Method[] secondObjMethods = secondObject.getClass().getMethods();

        for (Method secondObjMethod : secondObjMethods) {
            String secondObjMethodName = secondObjMethod.getName();
            String secondObjStrippedMethodName;
            if (secondObjMethodName.startsWith(SET_METHOD_PREFIX)) {
                secondObjStrippedMethodName = secondObjMethodName.substring(secondObjMethodName.indexOf(SET_METHOD_PREFIX) + SET_METHOD_PREFIX.length());
            } else {
                continue;
            }
            Class<?>[] secondObjMethodParamTypes = secondObjMethod.getParameterTypes();
            if (secondObjMethodParamTypes.length != 1) {
                continue;
            }
            for (Method firstObjMethod : firstObjMethods) {
                String firstObjMethodName = firstObjMethod.getName();
                String firstObjStrippedMethodName;
                boolean booleanMethod = false;
                if (firstObjMethodName.startsWith(GET_METHOD_PREFIX)) {
                    firstObjStrippedMethodName = firstObjMethodName.substring(firstObjMethodName.indexOf(GET_METHOD_PREFIX) + GET_METHOD_PREFIX.length());
                } else if (firstObjMethodName.startsWith(IS_METHOD_PREFIX)) {
                    booleanMethod = true;
                    firstObjStrippedMethodName = firstObjMethodName.substring(firstObjMethodName.indexOf(IS_METHOD_PREFIX) + IS_METHOD_PREFIX.length());
                } else {
                    continue;
                }
                if (secondObjStrippedMethodName.equals(firstObjStrippedMethodName) && secondObjMethodParamTypes[0].getCanonicalName().equals(firstObjMethod.getReturnType().getCanonicalName())) {
                    try {
                        Object firstObjMethodReturnValue = firstObjMethod.invoke(firstObject);
                        if (!override || skipVariables.contains(firstObjStrippedMethodName)) {
                            try {
                                Object secondObjMethodReturnValue;
                                if (booleanMethod) {
                                    secondObjMethodReturnValue = secondObject.getClass().getMethod(IS_METHOD_PREFIX + secondObjStrippedMethodName).invoke(secondObject);
                                } else {
                                    secondObjMethodReturnValue = secondObject.getClass().getMethod(GET_METHOD_PREFIX + secondObjStrippedMethodName).invoke(secondObject);
                                }
                                if (!objectNullOrEmpty(secondObjMethodReturnValue)) {
                                    continue;
                                }
                            } catch (NoSuchMethodException | SecurityException ex) {
                                Logger.getLogger(ObjectsUtil.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        secondObjMethod.invoke(secondObject, ClassUtils.primitiveToWrapper(firstObjMethod.getReturnType()).cast(firstObjMethodReturnValue));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(ObjectsUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        return secondObject;
    }
    
    /**
     *Create an object of the second object class and go through all the setters and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
     * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
     * Ignores every other method that is NOT public, setter or getter.
     *
     * On top of this there might be cases when you want to have override set to true but skip certain variables like the <b>id</b>. 
     * You can do so by providing a list of string with the variables names.
     * 
     * Returns the created second object with the new values.
     * 
     * @param firstObject
     * @param secondObjectClass
     * @param override
     * @param skipVariables
     * @return Returns the created second object with the new values
     */
    public static <T> Optional<T> copyObjectsIfMatch(Object firstObject, Class<T> secondObjectClass, boolean override, List<String> skipVariables) {
        try {
            T secondObject = secondObjectClass.newInstance();
            return Optional.of(copyObjectsIfMatch(firstObject, secondObject, override, skipVariables));
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ObjectsUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }
    
    /**
     *Create an object of the second object class and go through all the setters and try to find a getter in the first object that matches the name and has the same return value as the setter's parameter type.
     * If override is set to false then for each setter of the second method, its own getter is checked and if the value returned is not null or empty (for collections) then the setter invoking is skipped.
     * Ignores every other method that is NOT public, setter or getter.
     *
     * On top of this there might be cases when you want to have override set to true but skip certain variables like the <b>id</b>. 
     * You can do so by providing a list of string with the variables names. For this use the other overloaded method
     * 
     * Returns the created second object with the new values.
     * 
     * @param firstObject
     * @param secondObjectClass
     * @param override
     * @return Returns the created second object with the new values
     */
    public static <T> Optional<T> copyObjectsIfMatch(Object firstObject, Class<T> secondObjectClass, boolean override) {
        return copyObjectsIfMatch(firstObject, secondObjectClass, override, new ArrayList<>(0));
    }
    
    
        /**
     * This method returns all public variables that have a getter including the
     * inherited ones. 
     * This algorithm strips down the GET prefix or the IS
     * prefix for a boolean variable and then uncapitalizes the name to be
     * camel-case.
     * If you would like to skip/ignore any variable names then use the other method.
     *
     * @param <T>
     * @param object
     * @return a list of public variable names that have a getter (including
     * booleans)
     */
    public static <T> List<String> listAllVariablesWithGetters(T object) {
        return listAllVariablesWithGetters(object, null);
    }
    
    /**
     * This method returns all public variables that have a getter including the
     * inherited ones. 
     * This algorithm strips down the GET prefix or the IS
     * prefix for a boolean variable and then uncapitalizes the name to be
     * camel-case.
     * This method also ignores the getClass method defined by Object.java so it won't return class as one of the variables.
     *
     * @param <T>
     * @param object
     * @return a list of public variable names that have a getter (including booleans)
     */
    public static <T> List<String> listAllVariablesWithGettersIgnoreGetClass(T object) {
        return listAllVariablesWithGetters(object, Lists.newArrayList("class"));
    }
    
    /**
     * This method returns all public variables that have a getter including the
     * inherited ones. 
     * This algorithm strips down the GET prefix or the IS
     * prefix for a boolean variable and then uncapitalizes the name to be
     * camel-case.
     * You can also provide a list of variable names that you would like the algorithm to ignore/skip.
     *
     * @param <T>
     * @param object
     * @param namesToIgnore
     * @return a list of public variable names that have a getter (including
     * booleans)
     */
    public static <T> List<String> listAllVariablesWithGetters(T object, List<String> namesToIgnore) {
        Method[] objMethods = object.getClass().getMethods();
        List<String> names = new ArrayList<>(objMethods.length);
        for (Method firstObjMethod : objMethods) {
            String objMethodName = firstObjMethod.getName();
            String objStrippedMethodName;
            if (objMethodName.startsWith(GET_METHOD_PREFIX)) {
                objStrippedMethodName = objMethodName.substring(objMethodName.indexOf(GET_METHOD_PREFIX) + GET_METHOD_PREFIX.length());
            } else if (objMethodName.startsWith(IS_METHOD_PREFIX)) {
                objStrippedMethodName = objMethodName.substring(objMethodName.indexOf(IS_METHOD_PREFIX) + IS_METHOD_PREFIX.length());
            } else {
                continue;
            }
            String uncapitalizedName = StringUtils.uncapitalize(objStrippedMethodName);
            if (namesToIgnore == null || !namesToIgnore.contains(uncapitalizedName)) {
                names.add(uncapitalizedName);
            }
        }
        return names;
    }

    
    
    private static boolean objectNullOrEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        }
        return false;
    }
    
    public static <T> boolean listIsEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
    
    public static <T> boolean listIsNotEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }
}
