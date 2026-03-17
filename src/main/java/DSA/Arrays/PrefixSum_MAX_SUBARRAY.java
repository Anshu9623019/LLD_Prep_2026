package DSA.Arrays;

public class PrefixSum_MAX_SUBARRAY {

    public static void main(String[] args) {
        int arr[] = {7,1,6,0};
        int k = 7;
        int n = arr.length;
        int left = 0,right=0;
        int sum = 0;
        int max = 0;
        while(right<n){
            sum+=arr[right];
            while(sum>k){
                sum -= arr[left];
                left++;
            }
            if (sum==k){
                max = Math.max(max,right-left+1);
            }
            right++;
        }

        System.out.println(max);
    }
}
