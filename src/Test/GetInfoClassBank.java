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
        System.out.println(Modifier.isPublic(modifirers) + " - ����� "+ nameClass+" �������� public ");

        Class<?> [] interfaces = bankClass.getInterfaces();
        Arrays.stream(interfaces).toList().forEach(System.out::println);

        Class<?> superClass = bankClass.getSuperclass();
        System.out.println(superClass.getName());

        Field [] fieldsBankFantom = bankClass.getFields();// �������� ���� ��������

        Field [] fieldsDeclaredBankFantom = bankClass.getDeclaredFields();// �������� ���� ���
        Arrays.stream(fieldsDeclaredBankFantom).toList().forEach(System.out::println);

        //�������� ���������� ��������
        Field fieldBankFantom = null; // ���������� ��� ������� ����
        try {
            fieldBankFantom = BankFantom.class.getDeclaredField("name"); //���������� Field
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        fieldBankFantom.setAccessible(true); // ���������� ���� ����������� ��������� �����
        try {
            fieldBankFantom.set(bankFantom, "FANTOMAS BANK"); // ���������� ����� �������� ���� bankObject
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //������� ���������� �������
        for (Field field : fieldsDeclaredBankFantom) {
            System.out.println(field.getName());
            System.out.println(field.getModifiers());
            System.out.println(field.getType().getName());//���������� Class<?> ������������
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(bankFantom); // �������� ���������� ����� �������
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
            System.out.println(method.getReturnType() + " ����������"); //���������� Class<?>
            System.out.println(Arrays.toString(method.getParameterTypes()) + " ���� ���������� �����������");
            System.out.println(method.getParameterCount() + " ��� �� ����������� ����������");
            System.out.println("----------------");

        }
        //������ �������
        BankFantom bankObject = new BankFantom(); // ������� ��������� ������ Bank
        Class<?> bankObjectClass = bankObject.getClass();
        Method method = null; // ���������� ��� ������ � ��������������� ���������
        try {
            method = bankObjectClass.getDeclaredMethod("addPerson", Person.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        method.setAccessible(true); // ������������� ���� ����������� ��������� �������, ���� ����� �������� ���������

        Object result = null; // �������� ����� �� ���������� bankObject � ��������� �����������
        try {
            result = method.invoke(bankObject, new Person("Lera",19));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("���������: " + result); // ������� ���������� ���������

        //����� ��������� ��������� ���������
        bankObject.printPersonList();



    }
}
