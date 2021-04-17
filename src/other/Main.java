package other;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            employees.add(new Employee(i, scanner.nextInt()));
        }
        scanner.close();

        for (int i = 0; i < n; i++) {
            Employee begin = employees.get(i);
            System.out.print(countReceiving(begin, employees) + " ");
        }
    }

    private static int countReceiving(Employee begin, List<Employee> employees) {
        int count = 1;
        Employee next = getNextRecipient(begin, employees);
        while (next != null) {
            next = getNextRecipient(next, employees);
            count++;
        }
        return count;
    }

    private static Employee getNextRecipient (Employee begin, List<Employee> employees) {
        PriorityQueue<Employee> recipients = new PriorityQueue<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee current = employees.get(i);
            if (current.number > begin.number && current.height >= begin.height) {
                recipients.add(current);
            }
        }

        if (recipients.isEmpty()) {
            return null;
        }

        return recipients.peek();
    }

    static class Employee implements Comparable<Employee> {
        int number;
        int height;

        public Employee(int number, int height) {
            this.number = number;
            this.height = height;
        }

        @Override
        public int compareTo(Employee o) {
            return Integer.compare(number, o.number);
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "number=" + number +
                    ", height=" + height +
                    '}';
        }
    }
}
