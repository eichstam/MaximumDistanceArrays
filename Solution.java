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
    //System.out.println("L:" + L + " Lindex:" + Lindex + " H:" + H + " Hindex:" + Hindex);
    //System.out.println("l:" + l + " h:" + h);
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

  public static void main(String[] args) {
    Solution s = new Solution();
    //    int[][] input = {{1,2,3},
    //     {4,5},
    //    {1,2,3}};
    //    int[][] input = {{1},
    //        {1}};
    int[][] input = {{1,5},{3,4}};

    System.out.println("maxDistance: " + s.maxDistance(input));
  }
}
