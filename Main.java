import java.util.Scanner;

public class Main {
    //file names for saving/loading lists
    private static final String SINGLY_FILE = "singly.txt";
    private static final String DOUBLY_FILE = "doubly.txt";
    private static final String CIRCULAR_FILE = "circular.txt";
    public static void main(String[] args) {
        //to scan/read input
        Scanner scanner = new Scanner(System.in);

        SinglyLinkedList singlyList = new SinglyLinkedList();
        DoublyLinkedList doublyList = new DoublyLinkedList();
        CircularLinkedList circularList = new CircularLinkedList();

        FileStorage.loadFromFile(SINGLY_FILE, singlyList);
        FileStorage.loadFromFile(DOUBLY_FILE, doublyList);
        FileStorage.loadFromFile(CIRCULAR_FILE, circularList);

        boolean running = true;
        while (running) {
            System.out.println("\n=== Linked List Menu ===");
            System.out.println("1. Display all lists");
            System.out.println("2. Add value to a list");
            System.out.println("3. Insert at index");
            System.out.println("4. Remove value");
            System.out.println("5. Get value at index");
            System.out.println("6. Reverse (Doubly only)");
            System.out.println("7. Save and Exit");
            System.out.print("Enter choice: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1:
                    singlyList.display();
                    doublyList.display();
                    circularList.display();
                    break;

                case 2: {
                    AbstractLinkedList target = selectList(scanner, singlyList, doublyList, circularList);
                    if (target == null) break;
                    System.out.print("Enter value: ");
                    int value = readInt(scanner);
                    target.add(value);
                    System.out.println("Added " + value + " to " + target.getListType() + ".");
                    break;
                }

                case 3: {
                    AbstractLinkedList target = selectList(scanner, singlyList, doublyList, circularList);
                    if (target == null) break;
                    System.out.print("Enter index: ");
                    int index = readInt(scanner);
                    System.out.print("Enter value: ");
                    int value = readInt(scanner);
                    int before = target.size();
                    target.addAt(index, value);
                    if (target.size() > before) {
                        System.out.println("Inserted " + value + " at index " + index + " in " + target.getListType() + ".");
                    }
                    break;
                }

                case 4: {
                    AbstractLinkedList target = selectList(scanner, singlyList, doublyList, circularList);
                    if (target == null) break;
                    System.out.print("Enter value: ");
                    int value = readInt(scanner);
                    boolean removed = target.remove(value);
                    if (removed) {
                        System.out.println("Removed " + value + " from " + target.getListType() + ".");
                    } else {
                        System.out.println(value + " not found in " + target.getListType() + ".");
                    }
                    break;
                }

                case 5: {
                    AbstractLinkedList target = selectList(scanner, singlyList, doublyList, circularList);
                    if (target == null) break;
                    System.out.print("Enter index: ");
                    int index = readInt(scanner);
                    int value = target.get(index);
                    if (value == -1) {
                        System.out.println("Invalid index.");
                    } else {
                        System.out.println("Value at index " + index + " in " + target.getListType() + ": " + value);
                    }
                    break;
                }

                case 6: {
                    AbstractLinkedList target = selectList(scanner, singlyList, doublyList, circularList);
                    if (target == null) break;
                    if (target instanceof Reversible) {
                        ((Reversible) target).reverse();
                        StringBuilder sb = new StringBuilder("[ ");
                        for (int i = 0; i < target.size(); i++) {
                            sb.append(target.get(i)).append(" ");
                        }
                        sb.append("]");
                        System.out.println(target.getListType() + " reversed: " + sb);
                    } else {
                        System.out.println("This list is not Reversible.");
                    }
                    break;
                }

                case 7:
                    System.out.println("Saving lists to files... Done. Goodbye.");
                    FileStorage.saveToFile(SINGLY_FILE, singlyList);
                    FileStorage.saveToFile(DOUBLY_FILE, doublyList);
                    FileStorage.saveToFile(CIRCULAR_FILE, circularList);
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1-7.");
            }
        }
        scanner.close();
    }
    private static AbstractLinkedList selectList(Scanner scanner, SinglyLinkedList s,
                                                  DoublyLinkedList d, CircularLinkedList c) {
        System.out.print("Which list? (1=Singly, 2=Doubly, 3=Circular): ");
        int pick = readInt(scanner);
        switch (pick) {
            case 1: return s;
            case 2: return d;
            case 3: return c;
            default:
                System.out.println("Invalid selection.");
                return null;
        }
    }

    //a non-numeric line must not crash the menu loop.
    private static int readInt(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }
}
