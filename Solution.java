/*
 * 624. Maximum Distance in Arrays

Given m arrays, and each array is sorted in ascending order.
Now you can pick up two integers from two different arrays
(each array picks one) and calculate the distance. We define
the distance between two integers a and b to be their
absolute difference |a-b|. Your task is to find the maximum
distance.

Example 1:

Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the
first or third array and pick 5 in the second array.

Note:

    Each given array will have at least 1 number. There
    will be at least two non-empty arrays.
    The total number of the integers in all the m arrays
    will be in the range of [2, 10000].
    The integers in the m arrays will be in the range
    of [-10000, 10000].

 */
public class Solution {

  public int maxDistance(int[][] arrays) {
    int L = Integer.MAX_VALUE;
    int Lindex = -1;
    int l = Integer.MAX_VALUE;
    int H = Integer.MIN_VALUE;
    int Hindex = -1;
    int h = Integer.MIN_VALUE;

    for(int i = 0; i < arrays.length; i++) {
      if(arrays[i][0] < L) {
        Lindex = i;
        l = L;
        L = arrays[i][0];
      } else if(arrays[i][0] < l) {
        l = arrays[i][0];
      }
      if(arrays[i][arrays[i].length-1] > H) {
        Hindex = i;
        h = H;
        H = arrays[i][arrays[i].length-1];
      } else if(arrays[i][arrays[i].length-1] > h) {
        h = arrays[i][arrays[i].length-1];
      }
    }
    if(Lindex != Hindex) {
      return Math.abs(L - H);
    } else if(Math.abs(L - h) > Math.abs(l - H)) {
      return Math.abs(L - h);
    } else {
      return Math.abs(l - H);
    }
  }

  /**
   * [Time limit exceeded]
  public int maxDistance(int[][] arrays) {
    int max = Integer.MIN_VALUE;

    for(int i = 0; i < arrays.length; i++) {
      for(int j = 0; j < arrays.length; j++) {
        if(i != j) {
          int distance = Math.abs(arrays[i][0] - arrays[j][arrays[j].length-1]);
          if(distance > max) {
            max = distance;
          }
        }
      }
    }
    return max;
  }
   */

  public int maxDistance2(int[][] list) {
    int res = 0, min_val = list[0][0], max_val = list[0][list[0].length - 1];
    for (int i = 1; i < list.length; i++) {
        res = Math.max(res, Math.max(Math.abs(list[i][list[i].length - 1] - min_val), Math.abs(max_val - list[i][0])));
        min_val = Math.min(min_val, list[i][0]);
        max_val = Math.max(max_val, list[i][list[i].length - 1]);
    }
    return res;
}

  public static void main(String[] args) {
    Solution s = new Solution();
    int[][] input1 = {{1,2,3},
        {4,5},
        {1,2,3}};
    int[][] input2 = {{1},
        {1}};
    int[][] input3 = {{1,5},{3,4}};
    int[][] input4 = {{5, 7},
        {3, 10},
        {5, 11},
        {2, 13}
    };

    System.out.println("maxDistance1: " + s.maxDistance(input1));
    System.out.println("maxDistance2: " + s.maxDistance(input2));
    System.out.println("maxDistance3: " + s.maxDistance(input3));
    System.out.println("maxDistance4: " + s.maxDistance2(input4));
  }
}

/**
 *
Approach #1 Brute Force [Time Limit Exceeded]

The simplest solution is to pick up every element of every array
from the listlistlist and find its distance from every element
in all the other arrays except itself and find the largest distance
from out of those.

Java

public class Solution {
    public int maxDistance(int[][] list) {
        int res = 0;
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                for (int k = i + 1; k < list.length; k++) {
                    for (int l = 0; l < list[k].length; l++) {
                        res = Math.max(res, Math.abs(list[i][j] - list[k][l]));
                    }
                }
            }
        }
        return res;
    }
}

Complexity Analysis

    Time complexity : O((n∗x)2). We traverse over all the arrays
    in listlistlist for every element of every array considered.
    Here, n refers to the number of arrays in the list and x
    refers to the average number of elements in each array in
    the list.

    Space complexity : O(1). Constant extra space is used.


Approach #2 Better Brute Force [Time Limit Exceeded]

Algorithm

In the last approach, we didn't make use of the fact that every
array in the listlistlist is sorted. Thus, instead of
considering the distances among all the elements of all the
arrays(except intra-array elements), we can consider only the
distances between the first(minimum element) element of an
array and the last(maximum element) element of the other
arrays and find out the maximum distance from among all
such distances.

Java

public class Solution {
    public int maxDistance(int[][] list) {
        int res = 0;
        for (int i = 0; i < list.length; i++) {
            for (int j = i + 1; j < list.length; j++) {
                res = Math.max(res, Math.abs(list[i][0] - list[j][list[j].length - 1]));
                res = Math.max(res, Math.abs(list[j][0] - list[i][list[i].length - 1]));
            }
        }
        return res;
    }
}

Complexity Analysis

    Time complexity : O(n2). We consider only max and min values
    directly for every array currently considered. Here, n
    refers to the number of arrays in the list.

    Space complexity : O(1). Constant extra space is used.


Approach #3 Single Scan [Accepted]

Algorithm

We need not consider every list pair to find the maximum
distance. Instead, we can keep on traversing over the
arrays in the list and keep a track of the maximum distance
found so far. To do so, we keep a track of the element with
minimum value(min_val) and the one with maximum
value(max_val) found so far. For every new array, a considered,
we find the distance a[n−1]−min_val and max_val−a[0]
to compete with the maximum distance found so far. Here, n
refers to the number of elements in the current array, a.
Further, we need to note that the points min_val and
max_val both need not always contribute to the maximum
distance found till now. But, such points could help in
maximizing the distance in the future. Thus, max_val and
min_val contribute to the global maximum and minimum values
found till now, irrespective of the points considered for
the contribution in the maximum distance found till now.

Java

public class Solution {
    public int maxDistance(int[][] list) {
        int res = 0, min_val = list[0][0], max_val = list[0][list[0].length - 1];
        for (int i = 1; i < list.length; i++) {
            res = Math.max(res, Math.max(Math.abs(list[i][list[i].length - 1] - min_val), Math.abs(max_val - list[i][0])));
            min_val = Math.min(min_val, list[i][0]);
            max_val = Math.max(max_val, list[i][list[i].length - 1]);
        }
        return res;
    }
}

Complexity Analysis

    Time complexity : O(n). We traverse over the list of length
    n once only.

    Space complexity : O(1). Constant extra space is used.

*/
