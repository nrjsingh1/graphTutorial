package DSA.Graphs.Problems;

public class RemoveDuplicatesFromSortedArray {
    /*    *//*
    * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
    *  element appears only once. The relative order of the elements should be kept the same.
    * Then return the number of unique elements in nums.

Consider the number of unique elements of nums be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they
* were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k.*/
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        for (int i:nums)
            System.out.print(i+" ");
        System.out.println();
        System.out.println(removeDuplicates(nums));
        for (int i:nums)
            System.out.print(i+" ");
    }

    public static int removeDuplicates(int[] nums) {
        int count = 1;
        int shiftStartIndex=0;
        if (nums.length > 1) {
            int cur = nums[0];
            for(int i =1;i<nums.length ;i++){
                if(nums[i]!=cur){
                    cur = nums[i];
                    count++;
                }
            }
            int k =1;
            while(k <=count){
                cur = nums[0];
                for(int i =1;i<nums.length ;i++){
                    if(nums[i]==cur){
                        shiftStartIndex = i;
                        shift(nums, shiftStartIndex, nums.length-1);
                        k++;
                        break;
                    }
                    else{
                        cur = nums[i];
                    }
                }
            }
        }
        return count;
    }

    public static void shift(int[] nums, int start, int end){
        int temp = nums[start];
        for(int i = start; i < end; i++){
            nums[i] = nums[i+1];
        }
        nums[end] = temp;
    }
}
