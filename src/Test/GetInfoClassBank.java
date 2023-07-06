package Test;

import Test.bank.Bank;
import Test.bank.BankFantom;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class GetInfoClassBank {

    public void getinfo(){

        BankFantom bankFantom = new BankFantom();
        Class<?> bankClass = BankFantom.class;
        String nameClass = bankClass.getName();

        int modifirers = bankClass.getModifiers();
        System.out.println(modifirers);
        System.out.println(Modifier.PUBLIC);
        System.out.println(Modifier.isPublic(modifirers) + " - класс "+ nameClass+" является public ");

        Class<?> [] interfaces = bankClass.getInterfaces();
        Arrays.stream(interfaces).toList().forEach(System.out::println);

        Class<?> superClass = bankClass.getSuperclass();
        System.out.println(superClass.getName());

        Field [] fieldsBankFantom = bankClass.getFields();// получить поля открытые

        Field [] fieldsDeclaredBankFantom = bankClass.getDeclaredFields();// получить поля все
        Arrays.stream(fieldsDeclaredBankFantom).toList().forEach(System.out::println);

        //поменять содержимое свойства
        Field fieldBankFantom = null; // Подставьте имя нужного поля
        try {
            fieldBankFantom = BankFantom.class.getDeclaredField("name"); //возвращает Field
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        fieldBankFantom.setAccessible(true); // Установите флаг доступности приватных полей
        try {
            fieldBankFantom.set(bankFantom, "FANTOMAS BANK"); // Установите новое значение поля bankObject
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //вывести содержимое свойств
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


        System.out.println("---Method-------");
        Method []  methods = bankClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            System.out.println(method.getModifiers());
            System.out.println(method.getReturnType() + " возвращает"); //возвращает Class<?>
            System.out.println(Arrays.toString(method.getParameterTypes()) + " типы параметром принимающих");
            System.out.println(method.getParameterCount() + " кол во принимающих параметров");
            System.out.println("----------------");

        }
        //запуск методов
        BankFantom bankObject = new BankFantom(); // Создаем экземпляр класса Bank
        Class<?> bankObjectClass = bankObject.getClass();
        Method method = null; // Подставьте имя метода и соответствующие аргументы
        try {
            method = bankObjectClass.getDeclaredMethod("addPerson", Person.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        method.setAccessible(true); // Устанавливаем флаг доступности приватных методов, если метод является приватным

        Object result = null; // Вызываем метод на экземпляре bankObject с заданными аргументами
        try {
            result = method.invoke(bankObject, new Person("Lera",19));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("Результат: " + result); // Выводим полученный результат

        //после проверить результат изменений
        bankObject.printPersonList();



    }
}
