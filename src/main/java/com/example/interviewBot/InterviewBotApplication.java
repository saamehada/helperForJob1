package com.example.interviewBot;

import com.example.interviewBot.model.Course;
import com.example.interviewBot.model.Teacher;
import com.example.interviewBot.model.User;
import com.example.interviewBot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class InterviewBotApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(InterviewBotApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        List<Teacher> allTeachers = initTeachers();
        List<Course> allCourses = initCourses();

        User currentUser = null;

        while (currentUser == null) {
            System.out.println();
            System.out.println("Добро пожаловать в Interview Bot!");
            System.out.println();
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Выход");
            System.out.print("Ваш выбор: ");

            String authChoice = scanner.nextLine();

            if (authChoice.equals("1")) {
                System.out.print("Придумайте логин: ");
                String login = scanner.nextLine();
                System.out.print("Придумайте пароль: ");
                String password = scanner.nextLine();

                if (userService.register(login, password)) {
                    currentUser = userService.getUser(login);
                    System.out.println("Регистрация успешна! Добро пожаловать, " + login + "!");
                    System.out.println();
                } else {
                    System.out.println("Пользователь с таким логином уже существует!");
                    System.out.println();
                }
            }
            else if (authChoice.equals("2")) {
                System.out.print("Логин: ");
                String login = scanner.nextLine();
                System.out.print("Пароль: ");
                String password = scanner.nextLine();

                if (userService.login(login, password)) {
                    currentUser = userService.getUser(login);
                    System.out.println("Вход выполнен! Добро пожаловать, " + login + "!");
                    System.out.println();
                } else {
                    System.out.println("Неверный логин или пароль!");
                    System.out.println();
                }
            }
            else if (authChoice.equals("3")) {
                System.out.println("До свидания!");
                scanner.close();
                return;
            }
            else {
                System.out.println("Неверный ввод!");
                System.out.println();
            }
        }

        while (true) {
            System.out.println();
            System.out.println("Главное меню (пользователь: " + currentUser.getUsername() + ")");
            System.out.println();
            System.out.println("1. Выбрать преподавателя");
            System.out.println("2. Выбрать курс для подготовки к собеседованию");
            System.out.println("3. Воспользоваться помощью бота");
            System.out.println("4. Покинуть страницу");
            System.out.println("5. Посмотреть мой выбор");
            System.out.print("Ваш выбор: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println();
                System.out.println("Список преподавателей:");
                for (int i = 0; i < allTeachers.size(); i++) {
                    Teacher teacher = allTeachers.get(i);
                    String status = "";
                    if (currentUser.getSelectedTeachers().contains(teacher)) {
                        status = " [ВЫБРАН]";
                    }
                    System.out.println(teacher.toString() + status);
                }
                System.out.print("\nВыберите номер преподавателя (или 0 для выхода): ");

                String teacherChoice = scanner.nextLine();
                if (teacherChoice.matches("\\d+")) {
                    int index = Integer.parseInt(teacherChoice);
                    if (index >= 1 && index <= allTeachers.size()) {
                        Teacher selected = allTeachers.get(index-1);
                        List<Teacher> userTeachers = currentUser.getSelectedTeachers();

                        if (userTeachers.contains(selected)) {
                            userTeachers.remove(selected);
                            System.out.println("Преподаватель \"" + selected.getName() + "\" удалён из выбранных!");
                        } else {
                            userTeachers.add(selected);
                            System.out.println("Преподаватель \"" + selected.getName() + "\" добавлен в выбранные!");
                        }
                        userService.updateUser(currentUser);
                    } else if (index != 0) {
                        System.out.println("Неверный номер!");
                    }
                } else if (!teacherChoice.equals("0")) {
                    System.out.println("Неверный ввод!");
                }
            }

            else if (choice.equals("2")) {
                System.out.println();
                System.out.println("Список курсов:");
                for (int i = 0; i < allCourses.size(); i++) {
                    Course course = allCourses.get(i);
                    String status = "";
                    if (currentUser.getSelectedCourses().contains(course)) {
                        status = " [ВЫБРАН]";
                    }
                    System.out.println(course.toString() + status);
                }
                System.out.print("\nВыберите номер курса (или 0 для выхода): ");

                String courseChoice = scanner.nextLine();
                if (courseChoice.matches("\\d+")) {
                    int index = Integer.parseInt(courseChoice);
                    if (index >= 1 && index <= allCourses.size()) {
                        Course selected = allCourses.get(index-1);
                        List<Course> userCourses = currentUser.getSelectedCourses();

                        if (userCourses.contains(selected)) {
                            userCourses.remove(selected);
                            System.out.println("Курс \"" + selected.getName() + "\" удалён из выбранных!");
                        } else {
                            userCourses.add(selected);
                            System.out.println("Курс \"" + selected.getName() + "\" добавлен в выбранные!");
                        }
                        userService.updateUser(currentUser);
                    } else if (index != 0) {
                        System.out.println("Неверный номер!");
                    }
                } else if (!courseChoice.equals("0")) {
                    System.out.println("Неверный ввод!");
                }
            }

            else if (choice.equals("3")) {
                System.out.println();
                System.out.println("Помощь бота:");
                System.out.println("Бот: Я помогу вам подготовиться к собеседованию!");
                System.out.println("Советы:");
                System.out.println("- Повторяйте теорию каждый день");
                System.out.println("- Решайте задачи на LeetCode");
                System.out.println("- Изучите топ-50 вопросов по Java");
                System.out.println("Удачи на собеседовании!");
                System.out.println("\nНажмите Enter, чтобы продолжить...");
                scanner.nextLine();
            }

            else if (choice.equals("4")) {
                System.out.println();
                System.out.println("Выход со страницы. До свидания, " + currentUser.getUsername() + "!");
                break;
            }

            else if (choice.equals("5")) {
                System.out.println();
                System.out.println("Мой выбор:");
                System.out.println("Выбранные преподаватели:");
                List<Teacher> userTeachers = currentUser.getSelectedTeachers();
                if (userTeachers.isEmpty()) {
                    System.out.println("   - Не выбран ни один преподаватель");
                } else {
                    for (int i = 0; i < userTeachers.size(); i++) {
                        System.out.println("   " + (i+1) + ". " + userTeachers.get(i).getName() +
                                " (" + userTeachers.get(i).getSpecialization() + ")");
                    }
                }

                System.out.println();
                System.out.println("Выбранные курсы:");
                List<Course> userCourses = currentUser.getSelectedCourses();
                if (userCourses.isEmpty()) {
                    System.out.println("   - Не выбран ни один курс");
                } else {
                    for (int i = 0; i < userCourses.size(); i++) {
                        System.out.println("   " + (i+1) + ". " + userCourses.get(i).getName() +
                                " (" + userCourses.get(i).getLevel() + ")");
                    }
                }

                if (!userTeachers.isEmpty() || !userCourses.isEmpty()) {
                    System.out.println();
                    System.out.println("Итого: " + userTeachers.size() + " преподавателей, " + userCourses.size() + " курсов");
                }
                System.out.println("\nНажмите Enter, чтобы продолжить...");
                scanner.nextLine();
            }

            else {
                System.out.println("Неверный ввод. Выберите 1, 2, 3, 4 или 5.");
            }
        }

        scanner.close();
    }

    private List<Teacher> initTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1, "Анна Петрова", "Java Core", 8, 4.9));
        teachers.add(new Teacher(2, "Иван Смирнов", "Spring Boot", 6, 4.8));
        teachers.add(new Teacher(3, "Мария Козлова", "АиСД", 10, 5.0));
        teachers.add(new Teacher(4, "Дмитрий Иванов", "SQL", 7, 4.7));
        teachers.add(new Teacher(5, "Елена Соколова", "Микросервисы", 5, 4.6));
        return teachers;
    }

    private List<Course> initCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1, "Java Core для собеседования", "Junior", 40, "Java"));
        courses.add(new Course(2, "Spring Boot Masterclass", "Middle", 60, "Spring"));
        courses.add(new Course(3, "Алгоритмы и структуры данных", "Junior/Middle", 50, "Алгоритмы"));
        courses.add(new Course(4, "SQL от простого к сложному", "Junior", 30, "SQL"));
        courses.add(new Course(5, "Системный дизайн", "Senior", 45, "Архитектура"));
        courses.add(new Course(6, "Подготовка к техническому интервью", "All levels", 20, "Soft skills"));
        return courses;
    }
}