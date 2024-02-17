import java.util.*;

public class Sumoffour {
    public static void main(String[] args) {
        int[] nums = {1000000000, 1000000000, 1000000000, 1000000000};
        int target = -294967296;
        Set<String> hashSet = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int leftpointer = j + 1;
                int rightpointer = nums.length - 1;
                while (leftpointer < rightpointer) {
                    Long currentsum = Long.valueOf(nums[i]) + Long.valueOf(nums[j]) + Long.valueOf(nums[leftpointer]) + Long.valueOf(nums[rightpointer]);
//                    if(!(Long.valueOf(currentsum) <= 2147483647L && Long.valueOf(currentsum) >= -2147483648L)){
//                        return new ArrayList<>();
//                    }
                    if (currentsum == Long.valueOf(target)) {
                        List<Integer> currentList = new ArrayList<>();
                        currentList.add(nums[i]);
                        currentList.add(nums[j]);
                        currentList.add(nums[leftpointer]);
                        currentList.add(nums[rightpointer]);
                        if (!hashSet.contains(currentList.toString())) {
                            result.add(currentList);
                            hashSet.add(currentList.toString());
                        }
                        leftpointer++;
                        rightpointer--;
                    } else if (currentsum < target) {
                        leftpointer++;

                    } else if (currentsum > target) {
                        rightpointer--;
                    }


                }
            }

        }
        System.out.println(result);
    }
}

