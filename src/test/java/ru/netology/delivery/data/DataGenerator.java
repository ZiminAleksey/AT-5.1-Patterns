package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker((new Locale(locale)));
        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        return faker.address().cityName();
    }

    public static String generateName(String locale) {
        Faker faker = new Faker((new Locale(locale)));
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        return faker.phoneNumber().phoneNumber();
    }

    public void clear() {

    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new UserInfo(faker.address().cityName(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
//            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }

    public static class CustomRegistration {
        private CustomRegistration() {
        }

        public static CustomUserInfo generateCustomUser() {
            String city = "Орёл";
            String name = "Артём";
            String phone = "+79991234564";
            return new CustomUserInfo(city, name, phone);
        }
    }

    @Value
    public static class CustomUserInfo {
        String city;
        String name;
        String phone;
    }

    public static class CustomInvalidRegistration {
        private CustomInvalidRegistration() {
        }

        public static CustomInvalidUserInfo generateCustomInvalidUser() {
            String city = "New York";
            String name = "Андрей";
            String phone = "+7999123";
            return new CustomInvalidUserInfo(city, name, phone);
        }
    }

    @Value
    public static class CustomInvalidUserInfo {
        String city;
        String name;
        String phone;
    }
}
