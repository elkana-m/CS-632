def hasDuplicate(nums):
        seen = set()

        for i in range(len(nums)):
            num = nums[i]

            if num in seen:
                return True
            seen.add(num)
        return False

print(hasDuplicate([1,2,3,4,1]))