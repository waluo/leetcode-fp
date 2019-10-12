package org.waluo.medium

class Solution287 {
    fun findDuplicate(nums: IntArray): Int {
        fun findCycle(slow:Int, fast:Int):Int =
            if(slow == fast) slow
            else findCycle(nums[slow], nums[nums[fast]])
        fun findStart(begin: Int, slow:Int):Int =
            if(begin == slow) begin
            else findStart(nums[begin], nums[slow])
        return findStart(0, findCycle(nums[0], nums[nums[0]]))
    }
}