package com.awesomedev.bankonet.utils;

import java.lang.reflect.Field;

/**
 * This Awesome class allow you to parse any java object to a string. Usefull to display and probably nothing else.
 * Only stringifyObject is public, see further fort more details.
 * Send MONEY to http://www.dashCigareInDaAss.com:8090/not-a-scam
 */
public class ObjectTools {

    /**
     * @param objectToParse, any class accepted
     * @return String : all attributes (key, values) concat in a string.
     */
    public static <T> String stringifyObject(T objectToParse){
        return ObjectTools.stringifyFields(objectToParse , ObjectTools.getAttributes(objectToParse));
    }

    /**
     * @param objectToParse, any class accepted
     * @param getParentsAttributes bool : allow to retrieved all super classes attributes as well.
     * @return String : all attributes (key, values) concat in a string.
     */

    public static <T> String stringifyObject(T objectToParse, boolean getParentsAttributes) {
        if (!getParentsAttributes) {
            return ObjectTools.stringifyObject(objectToParse);
        } else {
            Field[] attributes = ObjectTools.getAttributes(objectToParse);
            Field[] parentsAttributes = ObjectTools.getParentsAttributes(objectToParse);
            Field[] allAttributes = new Field[attributes.length + parentsAttributes.length];
            System.arraycopy(attributes, 0, allAttributes, 0, attributes.length);
            System.arraycopy(parentsAttributes, 0, allAttributes, attributes.length, parentsAttributes.length);
            return ObjectTools.stringifyFields(objectToParse, allAttributes);
        }
    }


    private static <T> String stringifyFields( T objectToParse, Field[] fields){
        String properties = "";

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                properties += field.getName() + " : " + field.get(objectToParse) + ", ";

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return properties.substring(0, properties.length() - 2);
    }

    private static <T> Field[] getAttributes(T objectToParse){
        Field[] fields = objectToParse.getClass().getDeclaredFields();
        return fields;
    }

    private static <T> Field[] getParentsAttributes(T objectToParse){

        boolean keepDiging = true;
        Class superClass = objectToParse.getClass().getSuperclass();
        Field[] allField = new Field[0];
        do {

            Field[] previousField = new Field[allField.length];
            // save old fields
            System.arraycopy(allField, 0, previousField, 0, allField.length);

            Field[] superClassFields = superClass.getDeclaredFields();
            allField =  new Field[previousField.length + superClassFields.length];

            System.arraycopy(previousField, 0, allField, 0, previousField.length);
            System.arraycopy(superClassFields, 0, allField, previousField.length, superClassFields.length);
            // is there some Mutation? Don't know, too lazy to search.

            superClass = superClass.getSuperclass();
            if (superClass == null){
                keepDiging = false;
            }

        } while(keepDiging);

        return allField;
    }
}

