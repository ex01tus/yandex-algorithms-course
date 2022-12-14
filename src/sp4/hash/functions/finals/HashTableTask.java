package sp4.hash.functions.finals;

import java.io.*;
import java.util.Optional;

/**
 * Тимофей, как хороший руководитель, хранит информацию о зарплатах своих сотрудников в базе данных и постоянно её обновляет.
 * Он поручил вам написать реализацию хеш-таблицы, чтобы хранить в ней базу данных с зарплатами сотрудников.
 * Хеш-таблица должна поддерживать следующие операции:
 * put key value —– добавление пары ключ-значение. Если заданный ключ уже есть в таблице, то соответствующее ему значение обновляется.
 * get key –— получение значения по ключу. Если ключа нет в таблице, то вывести «None». Иначе вывести найденное значение.
 * delete key –— удаление ключа из таблицы. Если такого ключа нет, то вывести «None», иначе вывести хранимое по данному ключу значение и удалить ключ.
 * В таблице хранятся уникальные ключи.
 * Требования к реализации:
 * Нельзя использовать имеющиеся в языках программирования реализации хеш-таблиц (std::unordered_map в С++, dict в Python, HashMap в Java, и т. д.)
 * Число хранимых в таблице ключей не превосходит 10^5.
 * Разрешать коллизии следует с помощью метода цепочек или с помощью открытой адресации.
 * Все операции должны выполняться за O(1) в среднем.
 * Поддерживать рехеширование и масштабирование хеш-таблицы не требуется.
 * Ключи и значения, id сотрудников и их зарплата, —– целые числа. Поддерживать произвольные хешируемые типы не требуется.
 *
 * В первой строке задано общее число запросов к таблице n (1≤ n≤ 10^6).
 * В следующих n строках записаны запросы, которые бывают трех видов –— get, put, delete —– как описано в условии.
 * Все ключи и значения –— целые неотрицательные числа, не превосходящие 10^9.
 *
 * На каждый запрос вида get и delete выведите ответ на него в отдельной строке.
 *
 * https://contest.yandex.ru/contest/24414/run-report/68661656/
 *
 * Коллизии разрешаются методом цепочек.
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * O(1) – в среднем для всех операций. При росте числа коллизий возможна деградация до O(N). Подробнее ниже.
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(N) – несмотря на постоянный размер массива бакетов, расход памяти линейно возрастает в зависимости от количества
 * занятых ячеек.
 */
public class HashTableTask {

    private static HashTable hashTable;

    private static class HashTable {

        private final int size;
        private final Node[] buckets;

        public HashTable(int size) {
            this.size = size;
            this.buckets = new Node[size];
        }

        /**
         * Последовательно рассматриваем два кейса:
         * - Бакет пустой: O(1), пустой результат
         * - Бакет содержит N значений: O(N), последовательно итерируемся по цепочке нод, сравнивая ключи с искомым
         */
        public Optional<Integer> get(int key) {
            int hash = hash(key);
            Node head = buckets[hash];

            if (head == null) {
                return Optional.empty();
            }

            return findByKey(key, head)
                    .map(node -> node.value);
        }

        /**
         * Последовательно рассматриваем два кейса:
         * - Бакет пустой: O(1), нечего удалять
         * - Бакет содержит N значений: O(N), последовательно итерируемся по цепочке нод, сравнивая ключи с искомым.
         * Удаляем найденную ноду, перелинковывая указатели next и prev соседних нод. Если нода была первой в цепочке
         * обновляем значение бакета
         */
        public Optional<Integer> delete(int key) {
            int hash = hash(key);
            Node head = buckets[hash];

            if (head == null) {
                return Optional.empty();
            }

            return findByKey(key, head)
                    .map(this::deleteNode)
                    .map(node -> updateHeadIfNeeded(hash, node))
                    .map(node -> node.value);
        }

        /**
         * Последовательно рассматриваем три кейса:
         * - Бакет пустой: O(1), записываем новую ноду в бакет
         * - В бакете существует нода, с таким же ключом: O(N), последовательно итерируемся по цепочке нод,
         * сравнивая ключи с искомым. Перезаписываем значение для найденной ноды
         * - В бакете НЕ существует ноды, с таким же ключом: O(1) без учета времени, потраченного на предыдущий кейс.
         * Записываем новую ноду в качестве головы цепочки
         */
        public void put(int key, int value) {
            int hash = hash(key);
            Node head = buckets[hash];
            Node node = new Node(key, value);

            if (head == null) {
                buckets[hash] = node;
                return;
            }

            Optional<Node> existing = findByKey(key, head);
            if (existing.isPresent()) {
                existing.get().value = value;
                return;
            }

            node.next = head;
            head.prev = node;
            buckets[hash] = node;
        }

        private int hash(int key) {
            return key % size;
        }

        private Optional<Node> findByKey(int key, Node head) {
            while (head != null) {
                if (head.key == key) return Optional.of(head);
                head = head.next;
            }

            return Optional.empty();
        }

        private Node deleteNode(Node node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            }

            return node;
        }

        private Node updateHeadIfNeeded(int hash, Node node) {
            if (node.prev == null) {
                buckets[hash] = node.next;
            }

            return node;
        }
    }

    private static class Node {

        public final int key;
        public int value;
        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int commandsNumber = Integer.parseInt(reader.readLine());
            // Раз уж наша задача спровоцировать коллизии на любом тесте, будем просто брать заведомо меньший размер
            hashTable = new HashTable(commandsNumber > 10 ? commandsNumber / 10 : 1);

            for (int i = 0; i < commandsNumber; i++) {
                String command = reader.readLine();
                executeAndPrint(command, writer);
            }
        }
    }

    private static void executeAndPrint(String command, BufferedWriter writer) throws IOException {
        String[] tokens = command.split(" ");
        switch (tokens[0]) {
            case "get":
                Optional<Integer> value = hashTable.get(Integer.parseInt(tokens[1]));
                writer.write(value.map(Object::toString).orElse("None"));
                writer.newLine();
                break;
            case "put":
                hashTable.put(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                break;
            case "delete":
                Optional<Integer> deleted = hashTable.delete(Integer.parseInt(tokens[1]));
                writer.write(deleted.map(Object::toString).orElse("None"));
                writer.newLine();
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + command);
        }
    }
}
