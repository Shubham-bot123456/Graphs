import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class kickoff {

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        rec(5, 1, 11, new ArrayList<>(), new ArrayList<>());
        System.out.println(result);
    }

    public static void rec(int team1, int team2, int p, List<Integer> result, List<Integer> currentList) {
        if (p < 0) {
            return;
        } else if (p == 0) {
            if (result.size() > currentList.size()) {
                result = currentList.stream().collect(Collectors.toList());
            }
            return;
        }
        currentList.add(team1);
        rec(team1, team2, (p - team1), result, currentList);
        currentList.remove(currentList.size() - 1);
        currentList.add(team2);
        rec(team1, team2, (p - team2), result, currentList);
        currentList.remove(currentList.size() - 1);
    }

}
