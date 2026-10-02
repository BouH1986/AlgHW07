import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> a, Vertex<T> b) {
        // добавляем их друг друга в их списки смежности
        // ВАШ КОД

        //защита от добавления дубликатов, не понимал откуда берутся,
        // когда писал граф в уроках и сделал: graph.add("A","B"); graph.add("B","A");
        if (!a.getAdjacent().contains(b)) {
            a.getAdjacent().add(b);
        }
        if (!b.getAdjacent().contains(a)) {
            b.getAdjacent().add(a);
        }

    }

    public boolean isConnected(Vertex<T> a, Vertex<T> b) {
        return dfsFind(a, b, new HashSet<>()); // рекурсивный обход в глубину
    }

    // метод отвечает на вопрос, нашли ли мы обходом из v вершину target с учётом
    // посещённых вершин, которые записаны в visited
    private boolean dfsFind(Vertex<T> v, Vertex<T> target, Set<Vertex<T>> visited) {
        // если вершина в которую зашли (v) это та которую мы искали (target), то поиск закончен
        if (v.equals(target)) {
            return true; // нашли
        }
        visited.add(v); // запоминаем вершину которую посетили

        // ВАШ КОД
        // перебираем все смежные вершины у v
        // если такую вершину ещё не посещали, заходим рекурсивно в неё
        // если такой заход завершился нахождением target-а - выходим из метода с true

        for (Vertex<T> tmp : v.getAdjacent()) {
            if (!visited.contains(tmp)) {
                if (dfsFind(tmp, target, visited)) {
                    return true;
                }
            }
        }

        return false; // ничего не нашли
    }

    //todo delete
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < vertices.size(); i++) {
//            sb.append(vertices.get(i).getValue()).append("=[");
//            for (int j = 0; j < vertices.get(i).getAdjacent().size(); j++) {
//                sb.append(vertices.get(i).getAdjacent().get(j).getValue()).append(", ");
//            }
//            sb.append("]; ");
//        }
//        return sb.toString();
//    }
}