import java.util.Objects;

public class Employee {
    private static int nextId = 1;  // идентификаторы будут начинаться с единицы
    private String lastName;
    private String firstName;
    private String secondName;
    private int department;
    private double salary;
    private final int id;    // идентификатор не подлежит изменению после инициализации

    public Employee (String lastName, String firstName, String secondName, int department, double salary) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Отдел может принимать значение только от 1 до 5!");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.department = department;
        this.salary = salary;
        this.id = nextId;
        nextId++;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return secondName;
    }
    public int getDepartment() {
        return department;
    }
    public double getSalary() {
        return salary;
    }
    public int getId() {
        return id;
    }
    public void setDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Отдел может принимать значение только от 1 до 5!");
        }
        this.department = department;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getFullName() {
        return lastName + " " + firstName + " " + secondName;
    }

    @Override
    public String toString() {
        return "Сотрудник: " + lastName + " " + firstName + " " + secondName + " (id = " + id + "); отдел: " + department + "; зарплата: " + String.format("%.2f", salary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(employee.salary, salary) == 0 && Objects.equals(lastName, employee.lastName) && firstName.equals(employee.firstName) && Objects.equals(secondName, employee.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, secondName, department, salary);
    }
}
