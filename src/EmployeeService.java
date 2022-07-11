import java.util.Random;

public class EmployeeService {
    private static String[] manLastNames = {"Ефимов", "Игнатов", "Шульц", "Воробьев", "Мичурин", "Горбатенко", "Петренко"};
    private static String[] manFirstNames = {"Петр", "Порфирий", "Игнат", "Иван", "Савелий", "Василий", "Тимофей", "Ибрагим"};
    private static String[] manSecondNames = {"Иванович", "Сергеевич", "Ильич", "Платонович", "Петрович", "Васильевич"};

    private static String[] womanLastNames = {"Веселова", "Носова", "Черненко", "Калинина", "Морева", "Мочнева"};
    private static String[] womanFirstNames = {"Ирина", "Татьяна", "Василиса", "Октябрина", "Маруся", "Шоира", "Маргарита"};
    private static String[] womanSecondNames = {"Валерьевна", "Ивановна", "Николаевна", "Аркадьевна", "Ильинична", "Прокофьевна"};

    private EmployeeService() {}

    /* служебная функция, которая создает сотрудника при помощи случайной перестановки
    элементов из заранее созданных массивов имен. Также случайно задаются отделы и зарплата.
    Алгоритм может использовать массивы имен с любым количеством элементов (т.е. можно расширить предустановленные значения).
    При более заполненных предустановленных массивах имен будет лучший разброс сочетаний ФИО.
    Создание повторных полных имен запрещено, т.е. могут повторяться отдельные элементы:
    имя, отчество или фамилия - но кортежи этих элементов уникальны.
    Функция имеет упрощенный характер, не претендуя на хорошую оптимизацию.*/
    private static Employee createEmployee(String verifyString, Random randomGenerator) {
        String[] lastNames;
        String[] firstNames;
        String[] secondNames;

        String lastName;
        String firstName;
        String secondName;
        String fullName;

        int department;
        double salary;

        // выбор пола сотрудника: четное 'causalNumber' - мужчина, нечетное - женщина
        int gender = 0;
        int causalNumber = randomGenerator.nextInt(1000);
        if (causalNumber % 2 == 1) gender = 1;

        if (gender == 0) {
            lastNames = manLastNames;
            firstNames = manFirstNames;
            secondNames = manSecondNames;
        } else {
            lastNames = womanLastNames;
            firstNames = womanFirstNames;
            secondNames = womanSecondNames;
        }

        // цикл выполняется до тех пор, пока не будет построена уникальная строка,
        // но предусмотрен экстренный выход после 100 неудачных попыток
        int counter = 0;
        do {
            lastName = lastNames[randomGenerator.nextInt(lastNames.length-1)];
            firstName = firstNames[randomGenerator.nextInt(firstNames.length-1)];
            secondName = secondNames[randomGenerator.nextInt(secondNames.length-1)];
            fullName = lastName + " " + firstName + " " + secondName;
            counter++;
            if (counter > 100) {
                throw new RuntimeException("Ошибка в функции 'EmployeeService::createEmployee()': невозможно сформировать уникальную строку.");
            }
        } while (verifyString.contains(fullName.subSequence(0, fullName.length()-1)));

        // обновляем строку имен для следующих проверок
        verifyString += fullName;

        department = randomGenerator.nextInt(5) + 1; // отделы от 1 до 5
        salary = randomGenerator.nextDouble() * 100_000;

        // создание сотрудника
        Employee employee = new Employee(lastName, firstName, secondName, department, salary);
        return  employee;
    }
    // функция заполняет массив сотрудников случайным образом (заполняются только пустые ячейки массива)
    public static void fillArrayEmployees (Employee[] arrayEmployees) {
        Random randomGenerator = new Random();
        String verifyString = "";

        for (int i=0; i < arrayEmployees.length; i++) {
            if (arrayEmployees[i] != null) continue;
            arrayEmployees[i] = createEmployee(verifyString, randomGenerator);
        }
    }
    public static void printEmployees(Employee[] arrayEmployees) {
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            System.out.println(employee);
        }
    }
    public static double getAmountSalary(Employee[] arrayEmployees) {
        double sum = 0;
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            sum += employee.getSalary();
        }
        return sum;
    }
    public static Employee getEmployeeMinSalary(Employee[] arrayEmployees) {
        double currentSalary;
        int indexArray = -1;
        double minSalary = -1;
        for (int i=0; i < arrayEmployees.length; i++) {
            if (arrayEmployees[i] == null) continue;
            currentSalary = arrayEmployees[i].getSalary();
            if (currentSalary < minSalary || minSalary < 0) {
                minSalary = currentSalary;
                indexArray = i;
            }
        }
        if (indexArray < 0) {
            return null;
        } else {
            return arrayEmployees[indexArray];
        }
    }
    public static Employee getEmployeeMaxSalary(Employee[] arrayEmployees) {
        double currentSalary;
        int indexArray = -1;
        double maxSalary = -1;
        for (int i=0; i < arrayEmployees.length; i++) {
            if (arrayEmployees[i] == null) continue;
            currentSalary = arrayEmployees[i].getSalary();
            if (currentSalary > maxSalary || maxSalary < 0) {
                maxSalary = currentSalary;
                indexArray = i;
            }
        }
        if (indexArray < 0) {
            return null;
        } else {
            return arrayEmployees[indexArray];
        }
    }
    public static double getAverageSalary(Employee[] arrayEmployees) {
        return getAmountSalary(arrayEmployees) / arrayEmployees.length;
    }
    public static void printEmployeesNames(Employee[] arrayEmployees) {
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            System.out.println(employee.getFullName());
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    // Функции из категории задач повышенной сложности
    //////////////////////////////////////////////////////////////////////////////
    public static void indexSalary(Employee[] arrayEmployees, int percent) {
        double coefficient = percent / 100.0 + 1;
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            employee.setSalary(employee.getSalary() * coefficient);
        }
    }
    public static Employee getEmployeeMinSalaryByDepartment(Employee[] arrayEmployees, int department) {
        double currentSalary;
        int indexArray = -1;
        double minSalary = -1;
        for (int i = 0; i < arrayEmployees.length; i++) {
            if (arrayEmployees[i] == null) continue;
            if (arrayEmployees[i].getDepartment() != department) continue;
            currentSalary = arrayEmployees[i].getSalary();
            if (currentSalary < minSalary || minSalary < 0) {
                minSalary = currentSalary;
                indexArray = i;
            }
        }
        if (indexArray < 0) {
            return null;
        } else {
            return arrayEmployees[indexArray];
        }
    }
    public static Employee getEmployeeMaxSalaryByDepartment(Employee[] arrayEmployees, int department) {
        double currentSalary;
        int indexArray = -1;
        double maxSalary = -1;
        for (int i = 0; i < arrayEmployees.length; i++) {
            if (arrayEmployees[i] == null) continue;
            if (arrayEmployees[i].getDepartment() != department) continue;
            currentSalary = arrayEmployees[i].getSalary();
            if (currentSalary > maxSalary || maxSalary < 0) {
                maxSalary = currentSalary;
                indexArray = i;
            }
        }
        if (indexArray < 0) {
            return null;
        } else {
            return arrayEmployees[indexArray];
        }
    }
    public static double getAmountSalaryByDepartment(Employee[] arrayEmployees, int department) {
        double sum = 0;
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            if (employee.getDepartment() != department) continue;
            sum += employee.getSalary();
        }
        return sum;
    }
    public static double getAverageSalaryByDepartment(Employee[] arrayEmployees, int department) {
        int count = 0;
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            if (employee.getDepartment() == department) count++;
        }
        if (count == 0) return 0;
        return getAmountSalaryByDepartment(arrayEmployees, department) / count;
    }
    public static void indexSalaryByDepartment(Employee[] arrayEmployees, int department, int percent) {
        double coefficient = percent / 100.0 + 1;
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            if (employee.getDepartment() == department) {
                employee.setSalary(employee.getSalary() * coefficient);
            }
        }
    }
    public static void printEmployeesOfDepartment(Employee[] arrayEmployees, int department) {
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            if (employee.getDepartment() == department) {
                System.out.println("Сотрудник: " + employee.getFullName() + " (id = " + employee.getId() + "); зарплата: " + String.format("%.2f", employee.getSalary()));
            }
        }
    }
    public static void printEmployeesHavingSalaryAboveOrEqual(Employee[] arrayEmployees, double boundarySalary) {
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            if (employee.getSalary() >= boundarySalary) {
                System.out.println("Сотрудник: " + employee.getFullName() + " (id = " + employee.getId() + "); зарплата: " + String.format("%.2f", employee.getSalary()));
            }
        }
    }
    public static void printEmployeesHavingSalaryBelow(Employee[] arrayEmployees, double boundarySalary) {
        for (Employee employee : arrayEmployees) {
            if (employee == null) continue;
            if (employee.getSalary() < boundarySalary) {
                System.out.println("Сотрудник: " + employee.getFullName() + " (id = " + employee.getId() + "); зарплата: " + String.format("%.2f", employee.getSalary()));
            }
        }
    }

}
