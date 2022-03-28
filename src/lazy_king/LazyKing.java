package lazy_king;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * В одной далекой стране правил крайне сумасбродный король, который больше всего на свете любил власть.
 * Ему подчинялось множество людей, но вот незадача, у его подчиненных тоже были свои слуги.
 * Король обезумел от мысли, что какой-нибудь дворянин или даже зажиточный холоп может иметь больше слуг, чем он сам.
 * И приказал всем людям на бумаге через запятую написать свое имя и имена своих прямых подчиненных.
 *
 * По результатам опроса король получил огромный список из имен (see "pollResults")
 *
 * У короля разболелась голова. Что с этими данными делать, король не знал и делегировал задачу невезучему слуге.

 * Помогите слуге правильно составить иерархию и подготовить  отчет для короля следующим образом:
 *
 * король
       ...
 *     дворянин Кузькин
           жена Кузькина
 *         управляющий Семен Семеныч
               доярка Нюра
 *             крестьянин Федя
 *         ...
 *     секретарь короля
 *         зажиточный холоп
 *         ...
 *     ...
 *
 * Помните:
 *  1. Те, у кого нет подчиненных, просто написали свое имя.
 *  2. Те, кого никто не указал как слугу, подчиняются напрямую королю (ну, пускай бедный король так думает).
 *  3. Итоговый список должен быть отсортирован в алфавитном порядке на каждом уровне иерархии.
 *
 * Ответ присылайте ссылкой на опубликованный приватный Gist.
 * */

public class LazyKing {
    private static List<String> pollResults = List.of(
            "служанка Аня",
            "управляющий Семен Семеныч: крестьянин Федя, доярка Нюра",
            "дворянин Кузькин: управляющий Семен Семеныч, жена Кузькина, экономка Лидия Федоровна",
            "экономка Лидия Федоровна: дворник Гена, служанка Аня",
            "доярка Нюра",
            "кот Василий: человеческая особь Катя",
            "дворник Гена: посыльный Тошка",
            "киллер Гена",
            "зажиточный холоп: крестьянка Таня",
            "секретарь короля: зажиточный холоп, шпион Т",
            "шпион Т: кучер Д",
            "посыльный Тошка: кот Василий",
            "аристократ Клаус",
            "просветленный Антон"
    );

    public static void main(String... args) {
        UnluckyVassal unluckyVassal = new UnluckyVassal();
        unluckyVassal.printReportForKing(pollResults);
    }
}

class UnluckyVassal {
    public void printReportForKing(List<String> pollResults) {
        VassalTree tree = new VassalTree();
        pollResults.forEach(tree::addSinglePollResult);
        System.out.println(tree);
    }
}

class VassalTree {

    private final Node root = new Node("король", null);

    private class Node {
        String name;
        Node owner;
        Map<String, Node> vassals;

        public Node(String name, Node owner) {
            this.name = name;
            this.owner = owner;
            this.vassals = new TreeMap<>();
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append(name);
            vassals.forEach((k, v) -> {
                str.append("\n");
                str.append("\t".repeat(getNodeDepth() + 1));
                str.append(v);
            });
            return str.toString();
        }

        private int getNodeDepth() {
            Node tmp = this;
            int depth = 0;
            while (tmp != root) {
                depth++;
                tmp = tmp.owner;
            }
            return depth;
        }
    }

    public Node findByName(String name) {
        return findByName(root, name);
    }

    private Node findByName(Node node, String name) {
        if (node.name.equals(name)) {
            return node;
        }
        if (node.vassals.containsKey(name)) {
            return node.vassals.get(name);
        }
        for (Node vassal : node.vassals.values()) {
            Node tmp = findByName(vassal, name);
            if (tmp != null) {
                return tmp;
            }
        }
        return null;
    }

    public void addSinglePollResult(String pollString) {
        String name;
        String[] vassals;

        if (pollString.contains(":")) {
            int pos = pollString.indexOf(":");
            name = pollString.substring(0, pos).trim();
            vassals = pollString.substring(pos + 2).split(", ");
        } else {
            name = pollString.trim();
            vassals = new String[0];
        }

        Node node = findByName(name);

        if (node == null) {
            node = new Node(name, root);
            root.vassals.put(name, node);
        }

        for (String vassalName : vassals) {
            Node vassal = findByName(vassalName.trim());
            if (vassal == null) {
                node.vassals.put(vassalName, new Node(vassalName, node));
            } else {
                vassal.owner.vassals.remove(vassalName);
                node.vassals.put(vassalName, vassal);
                vassal.owner = node;
            }
        }
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
