package Test.work_with_reflections;

import Test.entities.Person;
import Test.entities.BankFantom;

import java.lang.reflect.*;
import java.util.Arrays;

public class GetInfoClassBank {

    public void getinfo() throws ClassNotFoundException {

        Class bankFantomClass = Class.forName("Test.entities.Person"); //можно создать класс класса вот так

        BankFantom bankFantom = new BankFantom();

        Class<?> bankClass = BankFantom.class;
        String nameClass = bankClass.getName();

        int modifirers = bankClass.getModifiers();
        System.out.println(modifirers);
        System.out.println(Modifier.PUBLIC);
        System.out.println(Modifier.isPublic(modifirers) + " - класс " + nameClass + " является public ");

        Class<?>[] interfaces = bankClass.getInterfaces();
        Arrays.stream(interfaces).toList().forEach(System.out::println);

        Class<?> superClass = bankClass.getSuperclass();
        System.out.println(superClass.getName());

        Field[] fieldsBankFantom = bankClass.getFields();// получить поля открытые

        Field[] fieldsDeclaredBankFantom = bankClass.getDeclaredFields();// получить поля все
        Arrays.stream(fieldsDeclaredBankFantom).toList().forEach(e -> System.out.println("Field -> " + e));//Вывести все поля

        //get content one field (получить содержимое свойства )
        try {
            BankFantom bankFantom2 = new BankFantom();
            Field field = BankFantom.class.getDeclaredField("name");//возвращает Field
            field.setAccessible(true);// Установите флаг доступности приватных полей
            Object fieldValue = field.get(bankFantom2);
            System.out.println("Field value: " + fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


        //change content field (изменить содержимое свойства)
        try {
            Field fieldBankFantom = BankFantom.class.getDeclaredField("name");//возвращает Field
            fieldBankFantom.setAccessible(true);// Установите флаг доступности приватных полей
            fieldBankFantom.set(bankFantom, "FANTOMAS BANK");// Установите новое значение поля bankObject
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


        //display class fields ( вывести содержимое свойств всех)
        for (Field field : fieldsDeclaredBankFantom) {
            System.out.println(field.getName());
            System.out.println(field.getModifiers());
            System.out.println(field.getType().getName());//возвращает Class<?> модификатора
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(bankFantom); // получить содержимое полей объекта
                System.out.println("Field value: " + fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("--------------");
        }

        //get information about all class methods (получить информация про все методы класса)
        System.out.println("---Method-------");
        Method[] methods = bankClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println(method.getModifiers());
            System.out.println(method.getReturnType() + " возвращает"); //возвращает Class<?>
            System.out.println(Arrays.toString(method.getParameterTypes()) + " типы параметром принимающих");
            System.out.println(method.getParameterCount() + " кол во принимающих параметров");
            System.out.println("----------------");

        }
        //run methods (запуск методов)

        BankFantom bankObject = new BankFantom();
        Class<?> bankObjectClass = bankObject.getClass();

        try {
            Method method = bankObjectClass.getDeclaredMethod("addPerson", Person.class);
            method.setAccessible(true);

            Object result = method.invoke(bankObject, new Person("Lera", 19));
            System.out.println("Результат: " + result);

            bankObject.printPersonList();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //check result
        bankObject.printPersonList();


        //create object
        try {
            Constructor<?> constructor = BankFantom.class.getConstructor();
            Object obj = constructor.newInstance();
            System.out.println(((BankFantom) obj).getName() + " -> new fantom Bank");

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        //create object with constructor
        try {
            Constructor<?> constructor = BankFantom.class.getConstructor();
            Object obj = constructor.newInstance();
            System.out.println(((BankFantom) obj).getName() + " -> new fantom Bank");

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
