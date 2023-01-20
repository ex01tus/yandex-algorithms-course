package sp7.greedy.and.dynamic;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Дано количество учебных занятий, проходящих в одной аудитории. Для каждого из них указано время начала и конца.
 * Нужно составить расписание, в соответствии с которым в классе можно будет провести как можно больше занятий.
 * Если возможно несколько оптимальных вариантов, то выведите любой.
 * Возможно одновременное проведение более чем одного занятия нулевой длительности.
 *
 * В первой строке задано число занятий. Оно не превосходит 1000.
 * Далее для каждого занятия в отдельной строке записано время начала и конца, разделённые пробелом.
 * Время задаётся одним целым числом h, если урок начинается/заканчивается ровно в h часов.
 * Если же урок начинается/заканчивается в h часов m минут, то время записывается как h.m.
 * Гарантируется, что каждое занятие начинается не позже, чем заканчивается.
 * Указываются только значащие цифры.
 *
 * Выведите в первой строке наибольшее число уроков, которое можно провести в аудитории.
 * Далее выведите время начала и конца каждого урока в отдельной строке в порядке их проведения.
 */
public class CourseSchedule {

    private static List<double[]> schedule(List<double[]> courses) {
        Comparator<double[]> comparator = Comparator
                .comparingDouble((double[] c) -> c[1])
                .thenComparingDouble(c -> c[0]);
        courses.sort(comparator);

        List<double[]> result = new ArrayList<>();

        double currentTime = 0.0;
        for (double[] course : courses) {
            if (course[0] >= currentTime) {
                result.add(course);
                currentTime = course[1];
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<double[]> courses = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] s = reader.readLine().split(" ");
                courses.add(new double[] {Double.parseDouble(s[0]), Double.parseDouble(s[1])});
            }

            List<double[]> result = schedule(courses);
            System.out.println(result.size());

            DecimalFormat format = new DecimalFormat("0.###");
            for (double[] r : result) {
                writer.write(format.format(r[0]) + " " + format.format(r[1]));
                writer.newLine();
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
