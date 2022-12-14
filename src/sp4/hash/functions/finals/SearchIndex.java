package sp4.hash.functions.finals;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * Тимофей пишет свою поисковую систему.
 * Имеется n документов, каждый из которых представляет собой текст из слов. По этим документам требуется построить поисковый индекс.
 * На вход системе будут подаваться запросы. Запрос —– некоторый набор слов. По запросу надо вывести 5 самых релевантных документов.
 * Релевантность документа оценивается следующим образом: для каждого уникального слова из запроса берётся число его вхождений в документ,
 * полученные числа для всех слов из запроса суммируются. Итоговая сумма и является релевантностью документа.
 * Чем больше сумма, тем больше документ подходит под запрос.
 * Сортировка документов на выдаче производится по убыванию релевантности. Если релевантности документов совпадают —– то по возрастанию
 * их порядковых номеров в базе (то есть во входных данных).
 * Подумайте над случаями, когда запросы состоят из слов, встречающихся в малом количестве документов.
 * Что если одно слово много раз встречается в одном документе?
 *
 * В первой строке дано натуральное число n —– количество документов в базе (1 ≤ n ≤ 10^4).
 * Далее в n строках даны документы по одному в строке. Каждый документ состоит из нескольких слов, слова отделяются друг
 * от друга одним пробелом и состоят из маленьких латинских букв. Длина одного текста не превосходит 1000 символов. Текст не бывает пустым.
 * В следующей строке дано число запросов —– натуральное число m (1 ≤ m ≤ 10^4). В следующих m строках даны запросы по одному в строке.
 * Каждый запрос состоит из одного или нескольких слов. Запрос не бывает пустым. Слова отделяются друг от друга одним пробелом
 * и состоят из маленьких латинских букв. Число символов в запросе не превосходит 100.
 *
 * Для каждого запроса выведите на одной строке номера пяти самых релевантных документов.
 * Если нашлось менее пяти документов, то выведите столько, сколько нашлось.
 * Документы с релевантностью 0 выдавать не нужно.
 *
 * https://contest.yandex.ru/contest/24414/run-report/68656286/
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * O(D * W) на создание поискового индекса, где D – количество документов, W – количество слов в каждом документе
 * O(R * W' * D') на выполнение всех поисковых запросов, где R – количество запросов, W' – количество уникальных слов
 * в каждом запросе, D' – количество документов, содержащих каждое из уникальных слов запроса
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(D * W) на поддержание поискового индекса
 * O(D') дополнительной памяти на выполнение каждого поискового запроса – для построения индекса релевантности
 */
public class SearchIndex {

    private static final String WORDS_SEPARATOR = " ";
    private static final int MIN_RELEVANCE = 1;
    private static final int RESULTS_LIMIT = 5;

    /**
     * Итерируемся по всем документам и всем словам в них.
     * Собираем мапу globalIndex = Map<Слово, Map<Номер_документа, Количество_вхождений_слова>>
     */
    public static Map<String, Map<Integer, Integer>> createGlobalIndex(List<String> documents) {
        Map<String, Map<Integer, Integer>> globalIndex = new HashMap<>();
        for (int i = 0; i < documents.size(); i++) {
            String[] words = documents.get(i).split(WORDS_SEPARATOR);
            for (String word : words) {
                globalIndex
                        .computeIfAbsent(word, w -> new HashMap<>())
                        .merge(i + 1, 1, Integer::sum);
            }
        }

        return globalIndex;
    }

    /**
     * 1. Выбираем только уникальные слова из запроса
     * 2. Собираем мапу relevanceIndex = Map<Номер_документа, Релевантность_документа>
     * 3. Отбрасываем вхождения с релевантностью меньше минимальной. Сортируем. Выбираем первые 5
     */
    public static List<SearchResult> search(
            Map<String, Map<Integer, Integer>> globalIndex, 
            String request) {
        Set<String> uniqueWords = filterUniqueWords(request);
        Map<Integer, Integer> relevanceIndex = createRelevanceIndex(globalIndex, uniqueWords);
        
        return relevanceIndex.entrySet().stream()
                .filter(e -> e.getValue() >= MIN_RELEVANCE)
                .map(e -> new SearchResult(e.getKey(), e.getValue()))
                .sorted()
                .limit(RESULTS_LIMIT)
                .collect(toList());
    }

    private static Set<String> filterUniqueWords(String request) {
        return Arrays.stream(request.split(WORDS_SEPARATOR)).collect(toSet());
    }

    /**
     * Для каждого слова получаем мапу wordIndex = Map<Номер_документа, Количество_вхождений_слова>.
     * Собираем мапу relevanceIndex = Map<Номер_документа, Релевантность_документа>
     */
    private static Map<Integer, Integer> createRelevanceIndex(
            Map<String, Map<Integer, Integer>> globalIndex, 
            Set<String> uniqueWords) {
        Map<Integer, Integer> relevanceIndex = new HashMap<>();
        for (String word : uniqueWords) {
            Map<Integer, Integer> wordIndex = globalIndex.getOrDefault(word, Map.of());
            for (Map.Entry<Integer, Integer> entry : wordIndex.entrySet()) {
                relevanceIndex.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        return relevanceIndex;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            List<String> documents = readDocuments(reader);
            Map<String, Map<Integer, Integer>> globalIndex = createGlobalIndex(documents);

            int requestsNumber = Integer.parseInt(reader.readLine());
            for (int i = 0; i < requestsNumber; i++) {
                String request = reader.readLine();
                List<SearchResult> searchResults = search(globalIndex, request);
                printResults(searchResults, writer);
            }
        }
    }

    private static List<String> readDocuments(BufferedReader reader) throws IOException {
        int documentsNumber = Integer.parseInt(reader.readLine());
        List<String> documents = new ArrayList<>(documentsNumber);
        for (int i = 0; i < documentsNumber; i++) {
            String document = reader.readLine();
            documents.add(document);
        }

        return documents;
    }

    private static void printResults(List<SearchResult> searchResults, BufferedWriter writer) throws IOException {
        String result = searchResults.stream()
                .map(SearchResult::getDocument)
                .map(Object::toString)
                .collect(joining(" "));

        writer.write(result);
        writer.newLine();
    }

    private static class SearchResult implements Comparable<SearchResult> {

        private static final Comparator<SearchResult> COMPARATOR = Comparator
                .comparing(SearchResult::getRelevance, Comparator.reverseOrder())
                .thenComparingInt(SearchResult::getDocument);

        private final int document;
        private final int relevance;

        public SearchResult(int document, int relevance) {
            this.document = document;
            this.relevance = relevance;
        }

        public int getDocument() {
            return document;
        }

        public int getRelevance() {
            return relevance;
        }

        @Override
        public int compareTo(SearchResult other) {
            return COMPARATOR.compare(this, other);
        }
    }
}
