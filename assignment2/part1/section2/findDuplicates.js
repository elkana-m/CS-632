function hasDuplicate(nums) {
  const seen = new Set();
  for (const num of nums) {
    if (seen.has(num)) return true;
    seen.add(num);
  }
  return false;
}

console.log(hasDuplicate([1, 2, 3, 4, 1]));