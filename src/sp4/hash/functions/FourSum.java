package sp4.hash.functions;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * У Гоши есть любимое число S. Помогите ему найти все уникальные четвёрки чисел в массиве, которые в сумме дают заданное число S.
 *
 * В первой строке дано общее количество элементов массива n (0 ≤ n ≤ 1000).
 * Во второй строке дано целое число S.
 * В третьей строке задан сам массив. Каждое число является целым и не превосходит по модулю 10^9.
 *
 * В первой строке выведите количество найденных четвёрок чисел.
 * В последующих строках выведите найденные четвёрки. Числа внутри одной четверки должны быть упорядочены по возрастанию.
 * Между собой четвёрки упорядочены лексикографически.
 */
public class FourSum {

    private static class Tuple implements Comparable<Tuple> {

        private static final Comparator<Tuple> COMPARATOR = Comparator
                .comparingLong(Tuple::getFirstNumber)
                .thenComparingLong(Tuple::getSecondNumber)
                .thenComparingLong(Tuple::getThirdNumber)
                .thenComparingLong(Tuple::getForthNumber);

        private final long firstNumber;
        private final long secondNumber;
        private final long thirdNumber;
        private final long forthNumber;

        public Tuple(long firstNumber, long secondNumber, long thirdNumber, long forthNumber) {
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
            this.thirdNumber = thirdNumber;
            this.forthNumber = forthNumber;
        }

        public long getFirstNumber() {
            return firstNumber;
        }

        public long getSecondNumber() {
            return secondNumber;
        }

        public long getThirdNumber() {
            return thirdNumber;
        }

        public long getForthNumber() {
            return forthNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Tuple other = (Tuple) o;
            return List.of(firstNumber, secondNumber, thirdNumber, forthNumber)
                    .equals(List.of(other.firstNumber, other.secondNumber, other.thirdNumber, other.forthNumber));
        }

        @Override
        public int hashCode() {
            return Objects.hash(List.of(firstNumber, secondNumber, thirdNumber, forthNumber));
        }

        @Override
        public String toString() {
            return firstNumber + " " + secondNumber + " " + thirdNumber + " " + forthNumber;
        }

        @Override
        public int compareTo(Tuple other) {
            return COMPARATOR.compare(this, other);
        }
    }

    private static List<Tuple> fourSum(List<Long> numbers, long sum) {
        Collections.sort(numbers);

        Set<Long> previous = new HashSet<>(numbers.size());
        Set<Tuple> tuples = new HashSet<>();

        for (int i = 0; i < numbers.size(); i++) {
            long secondNumber = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                long thirdNumber = numbers.get(j);
                for (int k = j + 1; k < numbers.size(); k++) {
                    long forthNumber = numbers.get(k);
                    long firstNumber = sum - secondNumber - thirdNumber - forthNumber;
                    if (previous.contains(firstNumber)) {
                        tuples.add(new Tuple(firstNumber, secondNumber, thirdNumber, forthNumber));
                    }
                }
            }

            previous.add(secondNumber);
        }

        List<Tuple> result = new ArrayList<>(tuples);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            long sum = Integer.parseInt(reader.readLine());

            if (n == 0) {
                System.out.println(0);
                return;
            }

            List<Long> numbers = Arrays.stream(reader.readLine().split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            List<Tuple> result = fourSum(numbers, sum);
            System.out.println(result.size());
            for (Tuple tuple : result) {
                writer.write(tuple.toString());
                writer.newLine();
            }
        }
    }
}
