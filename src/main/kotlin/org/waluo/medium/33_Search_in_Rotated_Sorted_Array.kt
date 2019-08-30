package org.waluo.medium

class Solution33 {
    fun search(nums: IntArray, target: Int): Int {
        fun find(lo: Int, hi: Int, mid: Int = (hi + lo) / 2): Int =
            when {
                nums[mid] == target -> mid
                lo >= hi -> -1
                target in (nums[mid]..nums[0]) -> find(mid + 1, hi)
                nums[mid] in (nums[0]..target) -> find(mid + 1, hi)
                nums[0] in (target..nums[mid]) -> find(mid + 1, hi)
                else -> find(lo, mid - 1)
            }
        return when{
            nums.isEmpty() -> -1
            nums[0] == target -> 0
            else -> find(0, nums.size - 1)
        }
    }
}