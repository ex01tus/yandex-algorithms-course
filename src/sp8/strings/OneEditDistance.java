package sp8.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Представьте, что вы работаете пограничником и постоянно проверяете документы людей по записи из базы.
 * При этом допустима ситуация, когда имя человека в базе отличается от имени в паспорте на одну замену,
 * одно удаление или одну вставку символа. Если один вариант имени может быть получен из другого удалением одного символа,
 * то человека пропустят через границу. А вот если есть какое-либо второе изменение, то человек грустно поедет домой или в посольство.
 * Например, если первый вариант —– это «Лена», а второй — «Лера», то девушку пропустят.
 * Также человека пропустят, если в базе записано «Коля», а в паспорте — «оля».
 * Однако вариант, когда в базе числится «Иннокентий», а в паспорте написано «ннакентий», уже не сработает.
 * Не пропустят также человека, у которого в паспорте записан «Иинннокентий»,
 * а вот «Инннокентий» спокойно пересечёт границу.
 * Напишите программу, которая сравнивает имя в базе с именем в паспорте и решает, пропускать человека или нет.
 * В случае равенства двух строк — путешественника, естественно, пропускают.
 *
 * В первой строке дано имя из паспорта.
 * Во второй строке —- имя из базы.
 * Обе строки состоят из строчных букв английского алфавита. Размер каждой строки не превосходит 100 000 символов.
 *
 * Выведите «OK», если человека пропустят, или «FAIL» в противном случае.
 */
public class OneEditDistance {

    private static boolean isOneEditDistance(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        if (first.length() > second.length()) {
            return isOneEditDistance(second, first);
        }

        boolean hasEdit = false;

        int i = 0;
        int j = 0;
        while (i < first.length() && j < second.length()) {
            if (first.charAt(i) == second.charAt(j)) {
                i++;
                j++;
            } else {
                if (hasEdit) return false;
                hasEdit = true;

                if (first.length() == second.length()) {
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String first = reader.readLine();
            String second = reader.readLine();

            boolean isOneEditDistance = isOneEditDistance(first, second);
            System.out.println(isOneEditDistance ? "OK" : "FAIL");
        }
    }
}
