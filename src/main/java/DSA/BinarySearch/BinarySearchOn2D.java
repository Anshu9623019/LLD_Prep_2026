package DSA.BinarySearch;

public class BinarySearchOn2D {


//    Find row with maximum 1's

    public int rowWithMax1s(int[][] mat) {

        int rows = mat.length;
        int cols = mat[0].length;

        int maxOnes = 0;
        int answer = -1;

        for (int i = 0; i < rows; i++) {

            int firstOne = lowerBound(mat[i]);

            int ones = cols - firstOne;

            if (ones > maxOnes) {
                maxOnes = ones;
                answer = i;
            }
        }

        return answer;
    }

    private int lowerBound(int[] row) {

        int low = 0;
        int high = row.length - 1;

        int ans = row.length;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (row[mid] == 1) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    // 74. Search a 2D Matrix
        public boolean searchMatrix(int[][] matrix, int target) {

            int rows = matrix.length;
            int cols = matrix[0].length;

            int low = 0;
            int high = rows * cols - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                int row = mid / cols;
                int col = mid % cols;

                if (matrix[row][col] == target) {
                    return true;
                }

                if (matrix[row][col] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return false;
        }




    // 240. Search a 2D Matrix II

    public boolean searchMatrix1(int[][] matrix, int target) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0;
        int col = cols - 1;

        while (row < rows && col >= 0) {

            int current = matrix[row][col];

            if (current == target) {
                return true;
            }

            if (current > target) {
                // Move left
                col--;
            } else {
                // Move down
                row++;
            }
        }

        return false;
    }


    // Peak element in 2D matrix
    class Solution {

        public int[] findPeakGrid(int[][] mat) {

            int rows = mat.length;
            int cols = mat[0].length;

            int low = 0;
            int high = rows - 1;

            while (low <= high) {

                int midRow = low + (high - low) / 2;

                // Find maximum element in this row
                int maxCol = 0;

                for (int col = 1; col < cols; col++) {
                    if (mat[midRow][col] > mat[midRow][maxCol]) {
                        maxCol = col;
                    }
                }

                int current = mat[midRow][maxCol];

                int up = midRow > 0
                        ? mat[midRow - 1][maxCol]
                        : -1;

                int down = midRow < rows - 1
                        ? mat[midRow + 1][maxCol]
                        : -1;

                // Peak found
                if (current > up && current > down) {
                    return new int[]{midRow, maxCol};
                }

                // Bigger element is below
                if (down > current) {
                    low = midRow + 1;
                }

                // Bigger element is above
                else {
                    high = midRow - 1;
                }
            }

            return new int[]{-1, -1};
        }
    }

    // Median in metrix

        public int median(int[][] matrix) {

            int rows = matrix.length;
            int cols = matrix[0].length;

            int low = Integer.MAX_VALUE;
            int high = Integer.MIN_VALUE;

            // Minimum = minimum of first elements
            // Maximum = maximum of last elements
            for (int i = 0; i < rows; i++) {

                low = Math.min(low, matrix[i][0]);
                high = Math.max(high, matrix[i][cols - 1]);
            }

            int required = (rows * cols + 1) / 2;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                int count = countLessEqual(matrix, mid);

                if (count >= required) {

                    // mid could be the answer
                    high = mid - 1;

                } else {

                    // Need a bigger value
                    low = mid + 1;
                }
            }

            return low;
        }

        private int countLessEqual(int[][] matrix, int target) {

            int count = 0;

            for (int[] row : matrix) {
                count += upperBound(row, target);
            }

            return count;
        }

        private int upperBound(int[] row, int target) {

            int low = 0;
            int high = row.length;

            while (low < high) {

                int mid = low + (high - low) / 2;

                if (row[mid] <= target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return low;
        }






}
