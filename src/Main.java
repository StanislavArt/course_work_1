/*
Для решения задач из 1 и 2 блоков создан библиотечный класс EmployeeService для работы с массивом Employee[],
в котором присутствуют только статичные функции. Создавать экземпляры этого класса запрещено (сделан приватный
конструктор). Для задач из 3 блока сделан класс EmployeeBook (согласно условию), позволяющий создавать независимые
списки сотрудников.
 */

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        EmployeeService.fillArrayEmployees(employees); // заполнение тестовых данных

        // 1. Тестирование задач с базовой сложностью

        // печать всех данных по сотрудникам
        EmployeeService.printEmployees(employees);

        System.out.printf("\nCумма затрат на зарплаты в месяц: %.2f", EmployeeService.getAmountSalary(employees));
        System.out.printf("\nСотрудник с минимальной зарплатой: %s", EmployeeService.getEmployeeMinSalary(employees).getFullName());
        System.out.printf("\nСотрудник с максимальной зарплатой: %s", EmployeeService.getEmployeeMaxSalary(employees).getFullName());
        System.out.printf("\nСреднее значение зарплат: %.2f", EmployeeService.getAverageSalary(employees));
        System.out.println('\n');
        // ФИО всех сотрудников
        EmployeeService.printEmployeesNames(employees);

        // 2. Тестирование задач с повышенной сложностью
        System.out.println();
        EmployeeService.indexSalary(employees, 5);
        System.out.println("Список сотрудников после индексации зарплаты на 5%");
        EmployeeService.printEmployees(employees);

        // 3. Тестирование задач с высокой сложностью
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Тестирование класса EmployeeBook");
        System.out.println("-------------------------------------------------------------------------------------------------");
        EmployeeBook employeeBook = new EmployeeBook(10);
        employeeBook.fillBookWithRandomEmployees();
        employeeBook.printEmployees();
        employeeBook.deleteEmployee("", 3);  // удаление всех сотрудников из 3 отдела
        System.out.println("\nПечать сотрудников по отделам");
        employeeBook.printDepartmentsWithEmployees();

        // если при удалении сотрудников 3 отдела окажется, что в этом отделе не было сотрудников,
        // тогда все ячейки останутся заполненными и при создании нового пользователя вылетит
        // запланированное исключение об ошибке
        employeeBook.createEmployee("Кузнецов", "Потап", "Иванович", 3, 25000);
        employeeBook.modifyEmployee("Кузнецов Потап Иванович", 0, 73000);
        System.out.println();
        employeeBook.printEmployees();
    }
}