public class EmployeeBook {
    private Employee[] employees;

    public EmployeeBook(int numberOfEmployees) {
        employees = new Employee[numberOfEmployees];
    }

    // реализацию данной функции не стал переносить в этот класс,
    // так как она используется только для тестовых случаев и для финального набора функциональности не нужна
    public void fillBookWithRandomEmployees() {
        EmployeeService.fillArrayEmployees(employees);
    }

    //////////////////////////////////////////////////////////////////////////////
    // Функции из категории задач базовой сложности
    //////////////////////////////////////////////////////////////////////////////
    public void printEmployees() {
        for (Employee employee : employees) {
            if (employee == null) continue;
            System.out.println(employee);
        }
    }
    public double getAmountSalary() {
        double sum = 0;
        for (Employee employee : employees) {
            if (employee == null) continue;
            sum += employee.getSalary();
        }
        return sum;
    }
    public Employee getEmployeeMinSalary() {
        double currentSalary;
        int indexArray = -1;
        double minSalary = -1;
        for (int i=0; i < employees.length; i++) {
            if (employees[i] == null) continue;
            currentSalary = employees[i].getSalary();
            if (currentSalary < minSalary || minSalary < 0) {
                minSalary = currentSalary;
                indexArray = i;
            }
        }
        if (indexArray < 0) {
            return null;
        } else {
            return employees[indexArray];
        }
    }
    public Employee getEmployeeMaxSalary() {
        double currentSalary;
        int indexArray = -1;
        double maxSalary = -1;
        for (int i=0; i < employees.length; i++) {
            if (employees[i] == null) continue;
            currentSalary = employees[i].getSalary();
            if (currentSalary > maxSalary || maxSalary < 0) {
                maxSalary = currentSalary;
                indexArray = i;
            }
        }
        if (indexArray < 0) {
            return null;
        } else {
            return employees[indexArray];
        }
    }
    public double getAverageSalary() {
        return getAmountSalary() / employees.length;
    }
    public void printEmployeesNames() {
        for (Employee employee : employees) {
            if (employee == null) continue;
            System.out.println(employee.getFullName());
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    // Функции из категории задач повышенной сложности
    //////////////////////////////////////////////////////////////////////////////
    public void indexSalary(int percent) {
        double coefficient = percent / 100.0 + 1;
        for (Employee employee : employees) {
            if (employee == null) continue;
            employee.setSalary(employee.getSalary() * coefficient);
        }
    }
    public Employee getEmployeeMinSalaryByDepartment(int department) {
        double currentSalary;
        int indexArray = -1;
        double minSalary = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) continue;
            if (employees[i].getDepartment() != department) continue;
            currentSalary = employees[i].getSalary();
            if (currentSalary < minSalary || minSalary < 0) {
                minSalary = currentSalary;
                indexArray = i;
            }
        }
        if (indexArray < 0) {
            return null;
        } else {
            return employees[indexArray];
        }
    }
    public Employee getEmployeeMaxSalaryByDepartment(int department) {
        double currentSalary;
        int indexArray = -1;
        double maxSalary = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) continue;
            if (employees[i].getDepartment() != department) continue;
            currentSalary = employees[i].getSalary();
            if (currentSalary > maxSalary || maxSalary < 0) {
                maxSalary = currentSalary;
                indexArray = i;
            }
        }
        if (indexArray < 0) {
            return null;
        } else {
            return employees[indexArray];
        }
    }
    public double getAmountSalaryByDepartment(int department) {
        double sum = 0;
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (employee.getDepartment() != department) continue;
            sum += employee.getSalary();
        }
        return sum;
    }
    public double getAverageSalaryByDepartment(int department) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (employee.getDepartment() == department) count++;
        }
        if (count == 0) return 0;
        return getAmountSalaryByDepartment(department) / count;
    }
    public void indexSalaryByDepartment(int department, int percent) {
        double coefficient = percent / 100.0 + 1;
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (employee.getDepartment() == department) {
                employee.setSalary(employee.getSalary() * coefficient);
            }
        }
    }
    public void printEmployeesOfDepartment(int department) {
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (employee.getDepartment() == department) {
                System.out.println("Сотрудник: " + employee.getFullName() + " (id = " + employee.getId() + "); зарплата: " + String.format("%.2f", employee.getSalary()));
            }
        }
    }
    public void printEmployeesHavingSalaryAboveOrEqual(double boundarySalary) {
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (employee.getSalary() >= boundarySalary) {
                System.out.println("Сотрудник: " + employee.getFullName() + " (id = " + employee.getId() + "); зарплата: " + String.format("%.2f", employee.getSalary()));
            }
        }
    }
    public void printEmployeesHavingSalaryBelow(double boundarySalary) {
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (employee.getSalary() < boundarySalary) {
                System.out.println("Сотрудник: " + employee.getFullName() + " (id = " + employee.getId() + "); зарплата: " + String.format("%.2f", employee.getSalary()));
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    // Функции из категории задач высокой сложности
    //////////////////////////////////////////////////////////////////////////////
    public void createEmployee(String lastName, String firstName, String secondName, int department, double salary) {
        // находим пустую ячейку
        int index = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            throw new RuntimeException("Ошибка в функции 'EmployeeBook::createEmployee()': в массиве нет пустых ячеек для создания нового пользователя");
        }
        employees[index] = new Employee(lastName, firstName, secondName, department, salary);
    }

    /* аргумент функции будет проигнорирован, если для строки задано пустое значение, а
       для отдела указан нуль. Если будут проигнорированы оба параметра, то функция
       ничего не выполняет.
       Если в поиске участвует параметр 'ФИО', то после первого найденного значения
       поиск по циклу прекратится, т.е. вводится предположение, что сотрудников
       в массиве с одинаковым ФИО не имеется. Если же поиск для удаления проводится только по
       отделу, то будут удалены все сотрудники указанного отдела.
    */
    public void deleteEmployee(String fullName, int department) {
        boolean fullNameUsed = true;
        boolean departmentUsed = true;
        String fullNameSpecified = null;

        if (fullName == "" || fullName == null) fullNameUsed = false;
        if (department == 0) departmentUsed = false;
        if (!(fullNameUsed || departmentUsed)) return;

        if (fullNameUsed) fullNameSpecified = fullName.trim();

        String currentFullName;
        for (int i = 0; i < employees.length; i++) {
            if (departmentUsed) {
                if (employees[i].getDepartment() != department) continue;
            }
            if (fullNameUsed) {
                if(employees[i] == null) continue; // пустую ячейку пропускаем
                currentFullName = employees[i].getFullName().trim();
                if (!fullNameSpecified.equalsIgnoreCase(currentFullName)) continue;
            }
            employees[i] = null;
            if (fullNameUsed) break;
        }
    }

    /* если аргумент (department или salary) принимает нулевое значение, то изменение по этому
    параметру игнорируются.
    Изменения коснутся только первого найденного сотрудника с заданным ФИО.
     */
    public void modifyEmployee(String fullName, int department, double salary) {
        boolean salaryRequiresModified = true;
        String fullNameSpecified = null;

        // округляем salary до копеек и, если полученный результат меньше 1 коп., то параметр игнорируется
        double roundSalary = Math.round(salary * 100.0) / 100.0;
        if (roundSalary < 0.01) salaryRequiresModified = false;

        // если оба параметра игнорируются или ФИО не указано, то ничего не делаем
        if (department == 0 && !salaryRequiresModified || fullName == "" || fullName == null) return;

        fullNameSpecified = fullName.trim(); // дополнительная подстраховка
        String currentFullName;
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] == null) continue; // пустую ячейку пропускаем
            currentFullName = employees[i].getFullName().trim();
            if (!fullNameSpecified.equalsIgnoreCase(currentFullName)) continue;

            // на этом участке оказываемся только в случае, когда найдена ячейка с требуемым ФИО
            if (department > 0) {
                employees[i].setDepartment(department);
            }
            if (salaryRequiresModified) {
                employees[i].setSalary(roundSalary);
            }
            break;
        }
    }

    /* Функция написана в соответствии с условием, что отделы могут принимать значение от 1 до 5,
    но при более общем решении потребовалось бы получить массив возможных отделов, а затем уже
    печать сотрудников по каждому из этого отдела (предложенное решение предполагает использование
    только поданного материала)
     */
    public void printDepartmentsWithEmployees() {
        boolean departmentIsPrinted;
        for (int i = 1; i < 6; i++) {
            departmentIsPrinted = false;
            for (Employee employee : employees) {
                if (employee == null) continue;
                if (employee.getDepartment() != i) continue;

                // печать отдела использует дополнительную переменную, так как вполне может оказаться,
                // что в очередном отделе нет сотрудников, тогда название отдела печатать не нужно
                if (!departmentIsPrinted) {
                    System.out.println("============================");
                    System.out.println("Отдел " + i);
                    System.out.println("============================");
                    departmentIsPrinted = true;
                }
                System.out.println(employee.getFullName());
            }
        }
    }
}
