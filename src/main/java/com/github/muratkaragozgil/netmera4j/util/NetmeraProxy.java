package com.github.muratkaragozgil.netmera4j.util;

import com.github.muratkaragozgil.netmera4j.Netmera;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Murat Karagözgil
 */
public class NetmeraProxy implements InvocationHandler {

    private final static Logger logger = Logger.getLogger(NetmeraProxy.class.getName());

    private Netmera netmeraApi;

    public static Netmera newInstance(Netmera obj) {
        return (Netmera) java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new NetmeraProxy(obj));
    }

    private NetmeraProxy(Netmera obj) {
        this.netmeraApi = obj;
    }

    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try {
            Object requestObject = args[0];
            Class firstArgument = requestObject.getClass();

            // TODO exception handling will activate again
            for (Field field : firstArgument.getDeclaredFields()) {
                // TODO maybe added inner fields
                if (validateField(requestObject, field)) return null;
            }

            result = m.invoke(netmeraApi, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
        return result;
    }

    private boolean validateField(Object requestObject, Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(NotNull.class)) {
            field.setAccessible(true);
            Object value = field.get(requestObject);
            if (value == null) {
                logger.warning(field + " : value is null!");
//                        throw new ValidationException(field + " : value is null!");
                return true;
            }
        } else if (field.isAnnotationPresent(NotEmpty.class)) {
            field.setAccessible(true);
            Object value = field.get(requestObject);
            if (value == null) {
                logger.warning(field + " : value is null!");
//                        throw new ValidationException(field + " : value is null!");
                return true;
            } else if (value instanceof String) {
                if (StringUtils.isEmpty((String) value)) {
                    logger.warning(field + " : value is empty!");
//                            throw new ValidationException(field + " : value is empty!");
                    return true;
                }
            } else if (value instanceof ArrayList) {
                if (((ArrayList) value).size() == 0) {
                    logger.warning(field + " : list is empty!");
//                            throw new ValidationException(field + " : list is empty!");
                    return true;
                }
            } else if (value instanceof List) {
                if (((List) value).size() == 0) {
                    logger.warning(field + " : list is empty!");
//                            throw new ValidationException(field + " : list is empty!");
                    return true;
                }
            } else if (value instanceof Map) {
                if (((Map) value).size() == 0) {
                    logger.warning(field + " : map is empty!");
                }
            }
        }
        return false;
    }
}
